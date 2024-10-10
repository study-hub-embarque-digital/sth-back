package com.studyhub.sth.services.alunos;

import com.studyhub.sth.dtos.alunos.AlunoAtualizadoDto;
import com.studyhub.sth.dtos.alunos.AlunoDto;
import com.studyhub.sth.dtos.alunos.NovoAlunoDto;
import com.studyhub.sth.dtos.users.UsuarioDto;
import com.studyhub.sth.entities.Aluno;
import com.studyhub.sth.entities.Usuario;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.IAlunoRepositorio;
import com.studyhub.sth.repositories.IUsuarioRepositorio;
import com.studyhub.sth.services.usuarios.IUsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService implements IAlunoService {
    @Autowired
    private IAlunoRepositorio alunoRepositorio;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;
    @Autowired
    private IMapper mapper;

    @Override
    @Transactional
    public AlunoDto criar(NovoAlunoDto novoAlunoDto) {
        Usuario usuario = this.mapper.map(novoAlunoDto.getNovoUsuarioDto(), Usuario.class);
        this.usuarioRepositorio.save(usuario);

        Aluno aluno = this.mapper.map(novoAlunoDto, Aluno.class);
        aluno.setUsuario(usuario);

        this.alunoRepositorio.save(aluno);
        return this.mapper.map(aluno, AlunoDto.class);
    }

    @Override
    @Transactional
    public AlunoDto atualizar(UUID alunoId, AlunoAtualizadoDto alunoAtualizadoDto) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível atualizar o aluno."));
        Usuario usuario = aluno.getUsuario();

        aluno.atualizar(alunoAtualizadoDto);
        usuario.atualizar(alunoAtualizadoDto.getUsuarioAtualizadoDto());

        this.usuarioRepositorio.save(usuario);
        this.alunoRepositorio.save(aluno);

        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuario(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));

        return alunoDto;
    }

    @Override
    public AlunoDto detalhar(UUID alunoId) throws ElementoNaoEncontradoExcecao {
        Aluno aluno = this.alunoRepositorio.findById(alunoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar o aluno."));
        AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
        alunoDto.setUsuario(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));

        return alunoDto;
    }

    @Override
    public List<AlunoDto> listarTodos() {
        List<Aluno> alunos = this.alunoRepositorio.findAll();

        return alunos.stream().map(aluno -> {
            AlunoDto alunoDto = this.mapper.map(aluno, AlunoDto.class);
            alunoDto.setUsuario(this.mapper.map(aluno.getUsuario(), UsuarioDto.class));

            return alunoDto;
        }).toList();
    }
}
