package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.graficos.AlunosAtivosPorInstituicaoDto;
import com.studyhub.sth.application.dtos.graficos.AlunosPeriodoTrabalhoDto;
import com.studyhub.sth.application.dtos.graficos.AlunosPorGeneroDto;
import com.studyhub.sth.application.dtos.graficos.CursoCountDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoSemReferenciaDto;
import com.studyhub.sth.application.dtos.job.JobDto;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.entities.Aluno;
import com.studyhub.sth.domain.entities.InstituicaoEnsino;
import com.studyhub.sth.domain.entities.Role;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.services.IAlunoService;
import com.studyhub.sth.domain.repositories.IRoleRepository;
import com.studyhub.sth.domain.services.IUsuarioService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IAlunoRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.domain.repositories.InstituicaoEnsinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService implements IAlunoService {
    @Autowired
    private IAlunoRepository alunoRepositorio;
    @Autowired
    private IUsuarioRepository usuarioRepositorio;
    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;
    @Autowired
    private IMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    @Transactional
    public AlunoDto criar(AlunoCreateDto novoAlunoDto) throws Exception {
        Optional<Usuario> usuarioExiste = usuarioRepositorio.findByEmail(novoAlunoDto.getNovoUsuarioDto().getEmail());

        if (usuarioExiste.isPresent()) throw new Exception("Já existe um usuário cadastrado com este email.");

        List<Role> roles = roleRepository.findByNome("ALUNO");

        if (roles.isEmpty()) throw new ElementoNaoEncontradoExcecao("Não foi criar seu perfil de acesso.");

        Usuario usuario = this.mapper.map(novoAlunoDto.getNovoUsuarioDto(), Usuario.class);
        usuario.setRoles(roles);
        usuario.setSenha(passwordEncoder.encode(novoAlunoDto.getNovoUsuarioDto().getSenha()));
        this.usuarioRepositorio.save(usuario);
        InstituicaoEnsino instituicaoEnsino = instituicaoEnsinoRepository.findById(novoAlunoDto.getInstituicaoEnsinoId()).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a instituição de ensino do aluno."));

        Aluno aluno = this.mapper.map(novoAlunoDto, Aluno.class);
        aluno.setUsuario(usuario);
        aluno.setInstituicaoEnsino(instituicaoEnsino);

        this.alunoRepositorio.save(aluno);

        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuarioDto(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));

        return alunoDto;
    }

    @Override
    @Transactional
    public AlunoDto atualizar(UUID alunoId, AlunoUpdateDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao, IOException {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível atualizar o aluno."));
        Usuario usuario = aluno.getUsuario();

        aluno.atualizar(alunoAtualizadoDto);
        usuario.atualizar(alunoAtualizadoDto.getUsuarioAtualizadoDto());
        this.usuarioRepositorio.save(usuario);
        this.alunoRepositorio.save(aluno);

        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuarioDto(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));

        return alunoDto;
    }

    @Override
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar o aluno."));
        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuarioDto(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));
        alunoDto.setInstituicaoEnsinoDto(this.mapper.map(aluno.getInstituicaoEnsino(), InstituicaoEnsinoSemReferenciaDto.class));

        List<JobDto> jobsDto = aluno.getJobs()
                .stream()
                .map(job -> this.mapper.map(job, JobDto.class))
                .toList();

        alunoDto.setJobs(jobsDto);

        return alunoDto;
    }

    @Override
    public List<AlunoDto> listarTodos() {
        List<Aluno> alunos = this.alunoRepositorio.findAll();

        return alunos.stream().map(aluno -> {
            AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
            UsuarioDto u = this.mapper.map(aluno.getUsuario(), UsuarioDto.class);
            InstituicaoEnsinoSemReferenciaDto iesdto = this.mapper.map(aluno.getInstituicaoEnsino(), InstituicaoEnsinoSemReferenciaDto.class);
            alunoDto.setUsuarioDto(u);
            alunoDto.setInstituicaoEnsinoDto(iesdto);

            return alunoDto;
        }).toList();
    }

    @Override
    public List<AlunoDto> listarPorPeriodo(Periodo periodo) {
        List<AlunoDto> alunos = this.alunoRepositorio.findAlunosByPeriodo(periodo).stream().map(aluno -> this.mapper.map(aluno, AlunoDto.class)).toList();

        return alunos;
    }

    @Override
    public List<CursoCountDto> buscarQuantidadePorCurso() {
        return this.alunoRepositorio.countAlunosByCurso();
    }

    @Override
    public List<AlunosAtivosPorInstituicaoDto> buscarAlunosAtivosPorIes() {
        return this.alunoRepositorio.buscarAlunosAtivosPorInstituicao();
    }

    @Override
    public List<AlunosPeriodoTrabalhoDto> countAlunosPorPeriodoTrabalhando(){
        return this.alunoRepositorio.countAlunosPorPeriodoComTrabalhando();
    }

    @Override
    public List<AlunosPorGeneroDto> countAlunosPorGenero(){
        return this.alunoRepositorio.countAlunosPorGenero();
    }
}
