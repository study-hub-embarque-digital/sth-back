package com.studyhub.sth.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyhub.sth.application.dtos.rooms.GeneratedRoomDto;
import com.studyhub.sth.application.dtos.rooms.RoomCreateDto;
import com.studyhub.sth.application.dtos.rooms.RoomUpdateDto;
import com.studyhub.sth.application.dtos.rooms.RoomDto;
import com.studyhub.sth.domain.before.entities.Room;
import com.studyhub.sth.domain.before.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.before.services.IRoomService;
import com.studyhub.sth.libs.ai.AIClient;
import com.studyhub.sth.libs.ai.builders.deepseak.DeepSeakPromptBuilder;
import com.studyhub.sth.libs.ai.messages.PromptMessage;
import com.studyhub.sth.libs.ai.messages.PromptMessageRole;
import com.studyhub.sth.libs.ai.messages.PromptResponseFormat;
import com.studyhub.sth.libs.ai.messages.deepseak.DSPromptMessage;
import com.studyhub.sth.libs.ai.messages.deepseak.DSPromptResponse;
import com.studyhub.sth.libs.ai.providers.DeepSeakProvider;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.before.repositories.IConteudoEstudoRepository;
import com.studyhub.sth.domain.before.repositories.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IConteudoEstudoRepository conteudoEstudoRepository;

    @Autowired
    private IMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public RoomDto criar(RoomCreateDto novoRoomDto) {
        Room room = this.mapper.map(novoRoomDto, Room.class);
        this.roomRepository.save(room);
        return this.mapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto atualizar(UUID roomId, RoomUpdateDto roomAtualizadaDto) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId)
                .orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Room não foi encontrado!"));
        if (roomAtualizadaDto.getConteudosRecomendados() != null) {
            var conteudoEstudoDto = roomAtualizadaDto.getConteudosRecomendados();
            var conteudoEstudo = this.roomRepository.findConteudoEstudoByRoomIdAndConteudoEstudoId(
                            roomId, conteudoEstudoDto.getRoomId())
                    .orElseThrow(() -> new ElementoNaoEncontradoExcecao("Conteúdo de Estudo não encontrado!"));

            conteudoEstudo.setLink(conteudoEstudoDto.getLink());
            this.conteudoEstudoRepository.save(conteudoEstudo);
        }
        if (roomAtualizadaDto.getDescription() != null){
            room.setDescription(roomAtualizadaDto.getDescription());
        }
        if(roomAtualizadaDto.getImage() != null){
            room.setImage(roomAtualizadaDto.getImage());
        }
        if(roomAtualizadaDto.getTitle() != null){
            room.setTitle(roomAtualizadaDto.getTitle());
        }
        this.roomRepository.save(room);
        return this.mapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto detalhar(UUID roomId) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId).orElseThrow(()-> new ElementoNaoEncontradoExcecao("O Room não foi encontrado!"));
        return this.mapper.map(room, RoomDto.class);
    }

    @Override
    public void deletar(UUID roomId) throws ElementoNaoEncontradoExcecao {
        var room = this.roomRepository.findById(roomId).orElseThrow(()-> new ElementoNaoEncontradoExcecao("O Room não foi encontrado!"));
        this.roomRepository.delete(room);
    }

    @Override
    public List<RoomDto> listar() {
        var lista = this.roomRepository.findAll();
        return lista.stream().map(room -> this.mapper.map(room, RoomDto.class)).collect(Collectors.toList());
    }

    public GeneratedRoomDto generateRoom() throws JsonProcessingException {
        List<Room> rooms = this.roomRepository.findAll();
        List<String> roomsParaExcluir = rooms.stream().map(Room::getTitle).toList();

        List<PromptMessage> messages = new ArrayList<>();
        messages.add(new DSPromptMessage(systemMesssage(roomsParaExcluir), PromptMessageRole.system));
        messages.add(new DSPromptMessage(userMesssage(), PromptMessageRole.user));

        AIClient client = new AIClient(new DeepSeakProvider("sk-4dba111b2eff4bb5a107479799b2af08"));

        DeepSeakPromptBuilder.DeepSeakPromptBuilderBuilder<?, ?> deepSeakPromptBuilder = DeepSeakPromptBuilder.builder()
                .messages(messages)
                .model("deepseek-chat")
                .frequencyPenalty(0)
                .maxTokens(8192)
                .presencePenalty(0)
                .responseFormat(new PromptResponseFormat("text")) // ou crie um builder p/ ResponseFormat
                .stop(null)
                .stream(false)
                .streamOptions(null)
                .temperature(1)
                .topP(1)
                .tools(null)
                .toolChoice("none")
                .logprobs(false)
                .topLogprobs(null);

        DSPromptResponse promptReturn = (DSPromptResponse) client.send(deepSeakPromptBuilder.build());

        String json = promptReturn.choices.get(0)
                .message
                .getContent()
                .split("```json")[1]
                .replaceAll("```", "");

        GeneratedRoomDto generatedRoomDto = objectMapper.readValue(json, GeneratedRoomDto.class);

        return generatedRoomDto;
    }

    private String userMesssage() {
        return "Eu tenho um projeto educacional que segue o formato de sala de aula invertida. Nesse projeto, existem 'rooms', que funcionam como módulos temáticos amplos dentro da área de desenvolvimento web (abrangendo frontend, backend, testes, ferramentas, boas práticas, etc.). Cada room é dividido em tópicos, que são chamados de salas temáticas dentro do room. Cada sala temática, por sua vez, possui subtópicos que serão distribuídos entre os participantes da sala. As salas temáticas funcionam como sessões de videoconferência com até 6 participantes (mínimo 2). Cada encontro possui 3 momentos: Aguardando participantes (5 minutos) – se houver ao menos 2 pessoas após esse tempo, o encontro começa. Etapa de estudo (15 minutos) – cada participante recebe até 2 subtopicos da sala temática para pesquisar e estudar. Apresentações e debate – cada participante tem 5 minutos para apresentar o que aprendeu, seguido por 5 minutos de debate entre todos os participantes. Agora, com base nesse formato e focado exclusivamente em temas dentro do universo de desenvolvimento web (como HTML, CSS, JS, frameworks, API REST, bancos de dados, autenticação, testes automatizados, etc.), gere 1 objeto JSON no seguinte formato: O objeto deve representar um único room. O room deve conter: Um nome e uma descrição (entre 300 e 1000 caracteres). Uma biblioteca com pelo menos 5 conteúdos reais e atuais (priorize conteúdos em português do Brasil, podendo ser vídeos do YouTube ou artigos). Uma lista de pelo menos 9 tópicos: 3 tópicos de nível INICIANTE 3 tópicos de nível INTERMEDIÁRIO 3 tópicos de nível AVANÇADO Cada tópico deve conter entre 6 e 20 subtopicos, sendo 10 subtopicos o ideal. Os subtopicos são usados para o momento de estudo nas salas temáticas, então devem ser bem divididos e específicos. Importante: Os conteúdos fornecidos na biblioteca devem conter links válidos e acessíveis no momento da resposta. A resposta deve ser apenas o JSON, exatamente no formato abaixo, sem mensagens adicionais. Formato do JSON esperado: { 'nome': 'Nome do Room', 'descricao': 'Texto entre 300 e 1000 caracteres.', 'stack': 'em um room como manipulando dom com javascript seria javascript, em um com context api com react seria react, e etc..., caso tenha mais de uma, escolha so a maior, por exemplo algo de react teria react e javascript, mas nesse caso a escolha é react. Precisa que seja apenas uma stack e a que seja principal', 'cor': 'o hexadecimal de uma cor que combine com a stack e o nome do room', 'cor_fonte': 'um hexadecimal de uma cor para fonte de texto que contraste bem com a cor de fundo escolhida para a stack', 'biblioteca': [ { 'tipo': 'ARTIGO' | 'VIDEO', 'imagem': 'url da thumbnail', 'url': 'url real e funcional do conteúdo', 'titulo': 'título do conteúdo', 'descricao': 'breve descrição' } ], 'topicos': [ { 'titulo': 'Título do tópico', 'dificuldade': 'INICIANTE' | 'INTERMEDIARIO' | 'AVANCADO', 'subtopicos': ['subtopico 1', 'subtopico 2', '...'] } ] }";
    }

    private String systemMesssage(List<String> roomsParaNaoRepetir) {
        String roomsToIgnore = String.join(", ", roomsParaNaoRepetir);
        return "Voce DEVE verificar e validar que todos os links enviados ainda estao utilizaveis e nao estao retornam not found 404 e voce NAO deve fazer os rooms de forma geral como Desenvolvimento Web Moderno, mas sim fazer rooms mais específicos como React.js do Zero ao Avançado ou Javascript: Manipulando o DOM - Além disso, rooms já existentes e que não devem ser repetidos: " + roomsToIgnore;
    }

}
