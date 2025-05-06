package com.studyhub.sth.unitarios.application.services;

import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.UpdatedDiscussaoDto;
import com.studyhub.sth.application.services.DiscussaoService;
import com.studyhub.sth.domain.before.entities.Discussao;
import com.studyhub.sth.domain.before.entities.Usuario;
import com.studyhub.sth.domain.before.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.before.repositories.IDiscussaoRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiscussaoServiceTest {

    @Mock
    private IDiscussaoRepository discussaoRepository;

    @Mock
    private IMapper mapper;

    @InjectMocks
    private DiscussaoService discussaoService;

    private Usuario usuario;
    private NewDiscussaoDto newDiscussaoDto;
    private Discussao discussao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setUsuarioId(UUID.randomUUID());
        usuario.setNome("Test User");

        newDiscussaoDto = new NewDiscussaoDto();
        newDiscussaoDto.setConteudo("Conteúdo de teste");

        discussao = new Discussao();
        discussao.setDiscussaoId(UUID.randomUUID());
        discussao.setConteudo("Conteúdo de teste");
        discussao.setUsuario(usuario);  // Garantindo que o usuário seja associado
    }

    @Test
    void createDeveSalvarDiscussaoERetornarDto() {
        NewDiscussaoDto newDiscussaoDto = new NewDiscussaoDto();
        Usuario usuario = new Usuario();
        usuario.setNome("Usuário Teste");

        Discussao discussao = new Discussao();
        Discussao discussaoSalva = new Discussao();
        discussaoSalva.setUsuario(usuario);

        DiscussaoDto discussaoDto = new DiscussaoDto();

        when(mapper.map(newDiscussaoDto, Discussao.class)).thenReturn(discussao);
        when(discussaoRepository.save(discussao)).thenReturn(discussaoSalva);
        when(mapper.map(discussaoSalva, DiscussaoDto.class)).thenReturn(discussaoDto);

        DiscussaoDto resultado = discussaoService.create(newDiscussaoDto, usuario);

        assertNotNull(resultado);
        assertEquals(discussaoDto, resultado);
        verify(mapper).map(newDiscussaoDto, Discussao.class);
        verify(discussaoRepository).save(discussao);
        verify(mapper).map(discussaoSalva, DiscussaoDto.class);
    }

    @Test
    void createChildDeveSalvarDiscussaoFilhaERetornarDto() throws ElementoNaoEncontradoExcecao {
        NewDiscussaoDto newDiscussaoDto = new NewDiscussaoDto();
        UUID discussaoPaiId = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setNome("Usuário Teste");

        Discussao discussao = new Discussao();
        Discussao discussaoPai = new Discussao();
        Discussao discussaoSalva = new Discussao();
        discussaoSalva.setUsuario(usuario);

        DiscussaoDto discussaoDto = new DiscussaoDto();

        when(mapper.map(newDiscussaoDto, Discussao.class)).thenReturn(discussao);
        when(discussaoRepository.findById(discussaoPaiId)).thenReturn(Optional.of(discussaoPai));
        when(discussaoRepository.save(discussao)).thenReturn(discussaoSalva);
        when(mapper.map(discussaoSalva, DiscussaoDto.class)).thenReturn(discussaoDto);

        DiscussaoDto resultado = discussaoService.createChild(newDiscussaoDto, discussaoPaiId, usuario);

        assertNotNull(resultado);
        assertEquals(discussaoDto, resultado);
        verify(mapper).map(newDiscussaoDto, Discussao.class);
        verify(discussaoRepository).findById(discussaoPaiId);
        verify(discussaoRepository).save(discussao);
        verify(mapper).map(discussaoSalva, DiscussaoDto.class);
    }

    @Test
    void findAllDeveRetornarListaDeDiscussaoDto() {
        // Criando objetos de Discussao com Usuario não-nulo
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste");

        Discussao discussao1 = new Discussao();
        discussao1.setUsuario(usuario); // Setando o Usuario na Discussao
        discussao1.setConteudo("Conteúdo da discussão 1");

        Discussao discussao2 = new Discussao();
        discussao2.setUsuario(usuario); // Setando o Usuario na Discussao
        discussao2.setConteudo("Conteúdo da discussão 2");

        List<Discussao> discussoes = List.of(discussao1, discussao2);
        List<DiscussaoDto> discussoesDto = List.of(new DiscussaoDto(), new DiscussaoDto());

        when(discussaoRepository.findAll()).thenReturn(discussoes);
        when(mapper.map(any(Discussao.class), eq(DiscussaoDto.class)))
                .thenReturn(new DiscussaoDto())
                .thenReturn(new DiscussaoDto());

        // Chamando o método a ser testado
        List<DiscussaoDto> resultado = discussaoService.findAll();

        // Verificações
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(discussaoRepository).findAll();
        verify(mapper, times(2)).map(any(Discussao.class), eq(DiscussaoDto.class));
    }

    @Test
    void findAllChildDeveRetornarListaDeFilhas() throws ElementoNaoEncontradoExcecao {
        // Mockando o retorno do repositório
        Discussao pai = new Discussao();
        pai.setDiscussaoId(UUID.randomUUID());
        pai.setUsuario(usuario);  // Também associando o usuário ao pai

        when(discussaoRepository.findById(any(UUID.class))).thenReturn(Optional.of(pai));
        when(discussaoRepository.findAllByDiscussaoPai(any(Discussao.class)))
                .thenReturn(List.of(discussao));

        when(mapper.map(discussao, DiscussaoDto.class)).thenReturn(new DiscussaoDto());

        // Chamando o método a ser testado
        List<DiscussaoDto> result = discussaoService.findAllChild(pai.getDiscussaoId());

        // Verificando o resultado
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size()); // A lista deve ter uma DiscussaoDto

        // Verificando que o nome do usuário foi mapeado corretamente
        assertEquals("Test User", result.get(0).getNomeUsuario());
    }

    @Test
    void updateDeveAtualizarConteudoDaDiscussao() throws Exception {
        UUID discussaoId = UUID.randomUUID();
        UpdatedDiscussaoDto updatedDiscussaoDto = new UpdatedDiscussaoDto();
        updatedDiscussaoDto.setConteudo("Conteúdo atualizado");
        Usuario usuarioAtual = new Usuario();
        usuarioAtual.setUsuarioId(UUID.randomUUID());

        Discussao discussao = new Discussao();
        discussao.setUsuario(usuarioAtual);

        Discussao discussaoAtualizada = new Discussao();
        discussaoAtualizada.setUsuario(usuarioAtual);
        discussaoAtualizada.setConteudo("Conteúdo atualizado");

        DiscussaoDto discussaoDto = new DiscussaoDto();

        when(discussaoRepository.findById(discussaoId)).thenReturn(Optional.of(discussao));
        when(discussaoRepository.save(discussao)).thenReturn(discussaoAtualizada);
        when(mapper.map(discussaoAtualizada, DiscussaoDto.class)).thenReturn(discussaoDto);

        DiscussaoDto resultado = discussaoService.update(discussaoId, updatedDiscussaoDto, usuarioAtual);

        assertNotNull(resultado);
        assertEquals(discussaoDto, resultado);
        verify(discussaoRepository).findById(discussaoId);
        verify(discussaoRepository).save(discussao);
        verify(mapper).map(discussaoAtualizada, DiscussaoDto.class);
    }
}