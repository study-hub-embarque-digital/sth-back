package com.studyhub.sth.services.mentor;

import com.studyhub.sth.dtos.mentor.MentorAtualizadoDTO;
import com.studyhub.sth.dtos.mentor.MentorDTO;
import com.studyhub.sth.dtos.mentor.NovoMentorDTO;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Mentor;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.IMentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MentorService implements  IMentorService{
    @Autowired
    private IMentorRepository mentorRepository;
    @Autowired
    private IMapper mapper;

    @Override
    public MentorDTO criar(NovoMentorDTO dto) {
        Mentor mentor = this.mapper.map(dto, Mentor.class);
        return this.mapper.map(mentor, MentorDTO.class);
    }

    @Override
    public List<MentorDTO> listar() {
        var lista = this.mentorRepository.findAll();
        return lista.stream().map( item -> this.mapper.map(item,MentorDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MentorDTO encontrarPorId(UUID id){
        var mentor = this.mentorRepository.findById(id);
        return this.mapper.map(mentor, MentorDTO.class);
    }

    @Override
    public void deletarPorId(UUID id) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));
//        var squads = this.mentorRepository.getAllSquad(id);
        //// deleteAllInBatch
        this.mentorRepository.delete(mentor);

    }

    @Override
    public MentorDTO atualizar(UUID id, MentorAtualizadoDTO dto) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));
        if (dto.getUsuarioDto().getNome() != null){
            mentor.getUsuario().setNome(dto.getUsuarioDto().getNome());
        }
        if (dto.getUsuarioDto().getEmail() != null){
            mentor.getUsuario().setEmail(dto.getUsuarioDto().getEmail());
        }
        if (dto.getUsuarioDto().getSenha() != null){
            mentor.getUsuario().setSenha(dto.getUsuarioDto().getSenha());
        }
        if (dto.getUsuarioDto().getDataNascimento() != null){
            mentor.getUsuario().setDataNascimento(dto.getUsuarioDto().getDataNascimento());
        }
        this.mentorRepository.save(mentor);
        return this.mapper.map(mentor, MentorDTO.class);
    }

    @Override
    public MentorDTO listarSquads(UUID id) {
      //  var squads = this.mentorRepository.getAllSquad(id);
        return null;
    }

    @Override
    public MentorDTO buscarPorNome(String nome) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findByUsuarioNomeContainsIgnoreCase(nome).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));;
        return this.mapper.map(mentor, MentorDTO.class);
    }
}
