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
import com.studyhub.sth.repositories.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MentorService implements IMentorService {
    @Autowired
    private IMentorRepository mentorRepository;

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IMapper mapper;

    @Override
    public MentorDTO criar(NovoMentorDTO dto) {
        Mentor mentor = this.mapper.map(dto, Mentor.class);
        Usuario usuario = this.mapper.map(mentor.getUsuario(), Usuario.class);
        this.usuarioRepositorio.save(usuario);
        mentor.setUsuario(usuario);
        this.mentorRepository.save(mentor);
        MentorDTO mentorDTO = this.mapper.map(mentor, MentorDTO.class);
        UsuarioDto usuarioDTO = this.mapper.map(usuario, UsuarioDto.class);
        mentorDTO.setUsuarioDto(usuarioDTO);
        return mentorDTO;
    }


    @Override
    public List<MentorDTO> listar() {
        var lista = this.mentorRepository.findAll();
        return lista.stream().map(mentor -> {
            MentorDTO mentorDTO = this.mapper.map(mentor, MentorDTO.class);
            UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
            mentorDTO.setUsuarioDto(usuarioDTO);
            return mentorDTO;
        }).collect(Collectors.toList());
    }


    @Override
    public MentorDTO buscarPorId(UUID id) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));
        MentorDTO mentorDTO = this.mapper.map(mentor, MentorDTO.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
        mentorDTO.setUsuarioDto(usuarioDTO);
        return mentorDTO;
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
        if (dto.getUsuarioDto() != null) {
            if (dto.getUsuarioDto().getNome() != null) {
                mentor.getUsuario().setNome(dto.getUsuarioDto().getNome());
            }
            if (dto.getUsuarioDto().getEmail() != null) {
                mentor.getUsuario().setEmail(dto.getUsuarioDto().getEmail());
            }
            if (dto.getUsuarioDto().getSenha() != null) {
                mentor.getUsuario().setSenha(dto.getUsuarioDto().getSenha());
            }
            if (dto.getUsuarioDto().getDataNascimento() != null) {
                mentor.getUsuario().setDataNascimento(dto.getUsuarioDto().getDataNascimento());
            }
        }
        MentorDTO mentorDTO = this.mapper.map(mentor, MentorDTO.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
        mentorDTO.setUsuarioDto(usuarioDTO);
        this.mentorRepository.save(mentor);

        return mentorDTO;
    }

//
//    @Override
//    public List<SquadDto> listarSquads(UUID id) {
//        //  var squads = this.mentorRepository.getAllSquad(id);
//        return squads;
//    }

    @Override
    public MentorDTO buscarPorNome(String nome) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findByUsuarioNomeContainsIgnoreCase(nome).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));
        MentorDTO mentorDTO = this.mapper.map(mentor, MentorDTO.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
        mentorDTO.setUsuarioDto(usuarioDTO);
        return mentorDTO;
    }

}
