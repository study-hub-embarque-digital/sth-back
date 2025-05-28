package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.application.dtos.mentor.MentorDto;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.entities.Empresa;
import com.studyhub.sth.domain.entities.Mentor;
import com.studyhub.sth.domain.entities.Role;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.repositories.*;
import com.studyhub.sth.domain.services.IMentorService;
import com.studyhub.sth.libs.mapper.IMapper;
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
    private IEmpresaRepository empresaRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public MentorDto criar(MentorCreateDto dto) throws Exception {
        Optional<Usuario> usuarioExiste = usuarioRepositorio.findByEmail(dto.getUsuarioDto().getEmail());

        if (usuarioExiste.isPresent()) throw new Exception("Já existe um usuário cadastrado com este email.");

        List<Role> roles = roleRepository.findByNome("MENTOR");

        if (roles.isEmpty()) throw new ElementoNaoEncontradoExcecao("Não foi criar seu perfil de acesso.");

        Mentor mentor = this.mapper.map(dto, Mentor.class);

        Usuario usuario = this.mapper.map(mentor.getUsuario(), Usuario.class);
        usuario.setRoles(roles);
        usuario.setSenha(passwordEncoder.encode(dto.getUsuarioDto().getSenha()));
        this.usuarioRepositorio.save(usuario);

        Empresa empresa = this.empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Empresa não encontrada"));

        mentor.setEmpresa(empresa);
        mentor.setUsuario(usuario);
        this.mentorRepository.save(mentor);

        MentorDto mentorDto = this.mapper.map(mentor, MentorDto.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
        mentorDto.setUsuarioDto(usuarioDTO);

        return mentorDto;
    }


    @Override
    public List<MentorDto> listar() {
        var lista = this.mentorRepository.findAll();
        return lista.stream().map(mentor -> {
            MentorDto mentorDTO = this.mapper.map(mentor, MentorDto.class);
            UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);
            EmpresaDto empresaDto = this.mapper.map(mentor.getEmpresa(), EmpresaDto.class);

            mentorDTO.setEmpresaDto(empresaDto);
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

        EmpresaDto empresaDto = this.mapper.map(mentor.getEmpresa(), EmpresaDto.class);
        mentorDTO.setEmpresaDto(empresaDto);

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
        }

        if(dto.getEmpresaId() != null){
            Empresa empresa = this.empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Empresa não encontrada!"));
            mentor.setEmpresa(empresa);
        }
        MentorDto mentorDTO = this.mapper.map(mentor, MentorDto.class);
        UsuarioDto usuarioDTO = this.mapper.map(mentor.getUsuario(), UsuarioDto.class);

        if (mentor.getEmpresa() != null) {
            mentorDTO.setEmpresaDto(mapper.map(mentor.getEmpresa(), EmpresaDto.class));
        }
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
