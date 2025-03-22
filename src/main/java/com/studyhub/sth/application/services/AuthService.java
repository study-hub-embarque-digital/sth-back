package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.admins.AdminCreateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.auth.AuthResponse;
import com.studyhub.sth.application.dtos.auth.RefreshRequest;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioCreateDto;
import com.studyhub.sth.application.dtos.users.UsuarioLoginDto;
import com.studyhub.sth.domain.entities.*;
import com.studyhub.sth.domain.enums.RolesUsuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.repositories.*;
import com.studyhub.sth.domain.services.IAuthService;
import com.studyhub.sth.domain.services.IRoleRepository;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;
    private IRepresentanteRepository representanteRepository;
    private IUsuarioRepository usuarioRepositorio;
    private IEmpresaRepository empresaRepository;
    private IMentorRepository mentorRepository;
    private IAlunoRepository alunoRepositorio;
    private IAdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;
    private IRoleRepository roleRepository;
    private TokenService tokenService;
    private IMapper mapper;

    @Autowired
    public AuthService(InstituicaoEnsinoRepository instituicaoEnsinoRepository, IRepresentanteRepository representanteRepository, IUsuarioRepository usuarioRepositorio, IEmpresaRepository empresaRepository, IMentorRepository mentorRepository, IAlunoRepository alunoRepositorio, IAdminRepository adminRepository, PasswordEncoder passwordEncoder, IRoleRepository roleRepository, TokenService tokenService, IMapper mapper) {
        this.instituicaoEnsinoRepository = instituicaoEnsinoRepository;
        this.representanteRepository = representanteRepository;
        this.usuarioRepositorio = usuarioRepositorio;
        this.empresaRepository = empresaRepository;
        this.mentorRepository = mentorRepository;
        this.alunoRepositorio = alunoRepositorio;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AuthResponse criaAluno(AlunoCreateDto novoAlunoDto) throws Exception {
        Usuario usuario = this.criaUsuario(novoAlunoDto.getNovoUsuarioDto(), RolesUsuario.aluno);
        InstituicaoEnsino instituicaoEnsino = instituicaoEnsinoRepository.findById(novoAlunoDto.getInstituicaoEnsinoId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a instituição de ensino do aluno."));

        Aluno aluno = this.mapper.map(novoAlunoDto, Aluno.class);
        aluno.setUsuario(usuario);
        aluno.setInstituicaoEnsino(instituicaoEnsino);

        this.alunoRepositorio.save(aluno);

        return new AuthResponse(tokenService.generateToken(usuario), tokenService.generateRefreshToken(usuario));
    }

    @Override
    @Transactional
    public AuthResponse criaMentor(MentorCreateDto dto) throws Exception {
        Usuario usuario = this.criaUsuario(dto.getUsuarioDto(), RolesUsuario.mentor);

        Mentor mentor = this.mapper.map(dto, Mentor.class);
        mentor.setUsuario(usuario);
        this.mentorRepository.save(mentor);

        return new AuthResponse(tokenService.generateToken(usuario), tokenService.generateRefreshToken(usuario));
    }

    @Override
    @Transactional
    public AuthResponse criaRepresentante(RepresentanteCreateDto dto) throws Exception {
        Usuario usuario = this.criaUsuario(dto.getNovoUsuarioDto(), RolesUsuario.representante);

        Empresa empresa = this.empresaRepository.findById(dto.getEmpresaId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Empresa não encontrada"));

        Representante representante = new Representante();
        representante.setUsuario(usuario);
        representante.setEmpresa(empresa);

        this.representanteRepository.save(representante);

        return new AuthResponse(tokenService.generateToken(usuario), tokenService.generateRefreshToken(usuario));
    }

    @Override
    @Transactional
    public AuthResponse criaAdmin(AdminCreateDto dto) throws Exception {
        Usuario usuario = this.criaUsuario(dto.getNovoUsuarioDto(), RolesUsuario.admin);

        Admin admin = new Admin();
        admin.setUsuario(usuario);

        this.adminRepository.save(admin);

        return new AuthResponse(tokenService.generateToken(usuario), tokenService.generateRefreshToken(usuario));
    }

    @Override
    public AuthResponse login(UsuarioLoginDto usuarioLoginDto) throws Exception {
        Usuario usuario = this.usuarioRepositorio.findByEmail(usuarioLoginDto.getEmail()).orElseThrow(() -> new Exception("Email ou senha incorretos"));

        if (!passwordEncoder.matches(usuarioLoginDto.getSenha(), usuario.getSenha())) throw new Exception("Usuário ou senha incorretos");

        return new AuthResponse(tokenService.generateToken(usuario), tokenService.generateRefreshToken(usuario));
    }

    @Override
    @Transactional
    public AuthResponse refresh(RefreshRequest refreshRequest) throws Exception {
        Usuario usuario = this.tokenService.validateRefreshToken(refreshRequest.refreshToken());
        return new AuthResponse(this.tokenService.generateToken(usuario), this.tokenService.generateRefreshToken(usuario));
    }

    private Usuario criaUsuario(UsuarioCreateDto usuarioCreateDto, String roleUsuario) throws Exception {
        Optional<Usuario> usuarioExiste = usuarioRepositorio.findByEmail(usuarioCreateDto.getEmail());

        if (usuarioExiste.isPresent()) throw new Exception("Já existe um usuário cadastrado com este email.");

        List<Role> roles = roleRepository.findByNome(roleUsuario);

        if (roles.isEmpty()) throw new ElementoNaoEncontradoExcecao("Não foi criar seu perfil de acesso.");

        Usuario usuario = this.mapper.map(usuarioCreateDto, Usuario.class);
        usuario.setRoles(roles);
        usuario.setSenha(passwordEncoder.encode(usuarioCreateDto.getSenha()));

        this.usuarioRepositorio.save(usuario);

        return usuario;
    }
}
