package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.application.dtos.mentor.MentorDto;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.entities.Mentor;
import com.studyhub.sth.domain.entities.Role;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IMentorService;
import com.studyhub.sth.domain.services.IRoleRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IMentorRepository;
import com.studyhub.sth.domain.repositories.ISquadRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MentorService implements IMentorService {
    @Autowired
    private IMentorRepository mentorRepository;
    @Autowired
    private IUsuarioRepository usuarioRepositorio;
    @Autowired
    private IMapper mapper;
    @Autowired
    private ISquadRepository squadRepositorio;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String criar(MentorCreateDto dto) throws Exception {
        Optional<Usuario> usuarioExiste = usuarioRepositorio.findByEmail(dto.getUsuarioDto().getEmail());

        if (usuarioExiste.isPresent()) throw new Exception("Já existe um usuário cadastrado com este email.");

        List<Role> roles = roleRepository.findByName("MENTOR");

        if (roles.isEmpty()) throw new ElementoNaoEncontradoExcecao("Não foi criar seu perfil de acesso.");

        Mentor mentor = this.mapper.map(dto, Mentor.class);

        Usuario usuario = this.mapper.map(mentor.getUsuario(), Usuario.class);
        usuario.setRoles(roles);
        usuario.setSenha(passwordEncoder.encode(dto.getUsuarioDto().getSenha()));
        this.usuarioRepositorio.save(usuario);

        mentor.setUsuario(usuario);
        this.mentorRepository.save(mentor);

        return tokenService.generateToken(usuario);
    }


    @Override
    public List<MentorDto> listar() {
        var lista = this.mentorRepository.findAll();
        return lista.stream().map(mentor -> {
            MentorDto mentorDTO = this.mapper.map(mentor, MentorDto.class);
            UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
            mentorDTO.setUsuarioDto(usuarioDTO);
            return mentorDTO;
        }).collect(Collectors.toList());
    }


    @Override
    public MentorDto buscarPorId(UUID id) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));
        MentorDto mentorDTO = this.mapper.map(mentor, MentorDto.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
        mentorDTO.setUsuarioDto(usuarioDTO);
        return mentorDTO;
    }

    @Override
    public void deletarPorId(UUID id) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));
        var squads = this.mentorRepository.getAllSquad(id);
        this.squadRepositorio.deleteAllInBatch(squads);
        this.mentorRepository.delete(mentor);

    }

    @Override
    public MentorDto atualizar(UUID id, MentorUpdateDto dto) throws ElementoNaoEncontradoExcecao {
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
        MentorDto mentorDTO = this.mapper.map(mentor, MentorDto.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
        mentorDTO.setUsuarioDto(usuarioDTO);
        this.mentorRepository.save(mentor);

        return mentorDTO;
    }


    @Override
    public List<SquadDTO> listarSquads(UUID id) {
        var squads = this.mentorRepository.getAllSquad(id);
        return squads.stream().map(squad -> this.mapper.map(squad, SquadDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MentorDto buscarPorNome(String nome) throws ElementoNaoEncontradoExcecao {
        var mentor = this.mentorRepository.findByUsuarioNomeContainsIgnoreCase(nome).orElseThrow(() -> new ElementoNaoEncontradoExcecao("O Mentor não foi encontrado!"));
        MentorDto mentorDTO = this.mapper.map(mentor, MentorDto.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
        mentorDTO.setUsuarioDto(usuarioDTO);
        return mentorDTO;
    }

}
