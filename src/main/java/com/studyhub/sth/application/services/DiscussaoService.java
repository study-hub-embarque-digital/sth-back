package com.studyhub.sth.application.services;

import com.studyhub.sth.application.annotations.CurrentUser;
import com.studyhub.sth.application.dtos.discussao.DiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.NewDiscussaoDto;
import com.studyhub.sth.application.dtos.discussao.UpdatedDiscussaoDto;
import com.studyhub.sth.domain.entities.Discussao;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;
import com.studyhub.sth.domain.repositories.IDiscussaoRepository;
import com.studyhub.sth.domain.services.IDiscussaoService;
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

    @CurrentUser
    private Usuario usuarioAtual;


    @Override
    public DiscussaoDto create(NewDiscussaoDto discussao) {
        Discussao d = this.mapper.map(discussao, Discussao.class);

        d.setUsuario(usuarioAtual);

        Discussao discussaoSalva = this.discussaoRepository.save(d);

        return this.mapper.map(discussaoSalva, DiscussaoDto.class);
    }

    @Override
    public DiscussaoDto createChild(NewDiscussaoDto discussao, UUID discussaoId) throws ElementoNaoEncontradoExcecao {
        Discussao d = this.mapper.map(discussao, Discussao.class);
        Discussao discussaoPai = this.discussaoRepository.findById(discussaoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a discussão a qual essa deveria ser vinculada"));

        d.setUsuario(usuarioAtual);
        d.setDiscussaoPai(discussaoPai);

        Discussao discussaoSalva = this.discussaoRepository.save(d);

        return this.mapper.map(discussaoSalva, DiscussaoDto.class);
    }

    @Override
    public List<DiscussaoDto> findAll() {
        return this.discussaoRepository
                .findAll()
                .stream()
                .map(d -> this.mapper.map(d, DiscussaoDto.class))
                .toList();
    }

    @Override
    public List<DiscussaoDto> findAllChild(UUID discussaoId) throws ElementoNaoEncontradoExcecao {
        Discussao d = this.discussaoRepository.findById(discussaoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a discussão selecionada."));

        return this.discussaoRepository
                .findAllByDiscussaoPai(d)
                .stream()
                .map(df -> this.mapper.map(df, DiscussaoDto.class))
                .toList();
    }

    @Override
    public DiscussaoDto update(UUID discussaoId, UpdatedDiscussaoDto updatedDiscussao) throws Exception {
        Discussao d = this.discussaoRepository.findById(discussaoId).orElseThrow(() -> new ElementoNaoEncontradoExcecao("Não foi possível encontrar a discussão selecionada para atualizar"));

        if (d.getUsuario().getUsuarioId() != this.usuarioAtual.getUsuarioId()) throw new Exception("Você não possui permissão para alterar discussões de outros usuários");
        if (updatedDiscussao.getConteudo().isEmpty()) throw new Exception("Você não pode colocar um texto vazio na discussão.");

        d.setConteudo(updatedDiscussao.getConteudo());

        Discussao discussaoSalva = this.discussaoRepository.save(d);

        return this.mapper.map(discussaoSalva, DiscussaoDto.class);
    }
}
