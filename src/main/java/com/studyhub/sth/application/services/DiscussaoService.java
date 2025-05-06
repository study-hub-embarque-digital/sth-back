package com.studyhub.sth.application.services;

//import com.studyhub.sth.application.annotations.CurrentUser;
import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.UpdatedDiscussaoDto;
import com.studyhub.sth.domain.before.entities.Discussao;
import com.studyhub.sth.domain.before.entities.Usuario;
import com.studyhub.sth.domain.before.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.before.repositories.IDiscussaoRepository;
import com.studyhub.sth.domain.before.services.IDiscussaoService;
import com.studyhub.sth.libs.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiscussaoService implements IDiscussaoService {
    @Autowired
    private IDiscussaoRepository discussaoRepository;

    @Autowired
    private IMapper mapper;


    @Override
    public DiscussaoDto create(NewDiscussaoDto discussao, Usuario usuarioAtual) {
        Discussao d = this.mapper.map(discussao, Discussao.class);
        d.setUsuario(usuarioAtual);

        Discussao discussaoSalva = this.discussaoRepository.save(d);

        DiscussaoDto dto = this.mapper.map(discussaoSalva, DiscussaoDto.class);
        dto.setNomeUsuario(discussaoSalva.getUsuario().getNome());
        return dto;
    }

    @Override
    public DiscussaoDto createChild(NewDiscussaoDto discussao, UUID discussaoId, Usuario usuarioAtual) throws ElementoNaoEncontradoExcecao {
        Discussao d = this.mapper.map(discussao, Discussao.class);
        Discussao discussaoPai = this.discussaoRepository.findById(discussaoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a discussão a qual essa deveria ser vinculada"));

        d.setUsuario(usuarioAtual);
        d.setDiscussaoPai(discussaoPai);

        Discussao discussaoSalva = this.discussaoRepository.save(d);

        DiscussaoDto dto = this.mapper.map(discussaoSalva, DiscussaoDto.class);
        dto.setNomeUsuario(discussaoSalva.getUsuario().getNome());
        return dto;
    }

    @Override
    public List<DiscussaoDto> findAll() {
        return this.discussaoRepository
                .findAll()
                .stream()
                .map(d -> {
                    DiscussaoDto dto = this.mapper.map(d, DiscussaoDto.class);
                    dto.setNomeUsuario(d.getUsuario().getNome());
                    return dto;
                })
                .toList();
    }

    @Override
    public List<DiscussaoDto> findAllChild(UUID discussaoId) throws ElementoNaoEncontradoExcecao {
        Discussao d = this.discussaoRepository.findById(discussaoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a discussão selecionada."));

        return this.discussaoRepository
                .findAllByDiscussaoPai(d)
                .stream()
                .map(df -> {
                    DiscussaoDto dto = this.mapper.map(df, DiscussaoDto.class);
                    dto.setNomeUsuario(df.getUsuario().getNome());
                    return dto;
                })
                .toList();
    }

    @Override
    public DiscussaoDto update(UUID discussaoId, UpdatedDiscussaoDto updatedDiscussao, Usuario usuarioAtual) throws Exception {
        Discussao d = this.discussaoRepository.findById(discussaoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a discussão selecionada para atualizar"));

        if (d.getUsuario().getUsuarioId() != usuarioAtual.getUsuarioId()) throw new Exception("Você não possui permissão para alterar discussões de outros usuários");
        if (updatedDiscussao.getConteudo().isEmpty()) throw new Exception("Você não pode colocar um texto vazio na discussão.");

        d.setConteudo(updatedDiscussao.getConteudo());

        Discussao discussaoSalva = this.discussaoRepository.save(d);

        DiscussaoDto dto = this.mapper.map(discussaoSalva, DiscussaoDto.class);
        dto.setNomeUsuario(discussaoSalva.getUsuario().getNome());
        return dto;
    }
}
