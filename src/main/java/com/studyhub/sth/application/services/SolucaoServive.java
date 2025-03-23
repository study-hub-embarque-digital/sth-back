package com.studyhub.sth.application.services;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studyhub.sth.application.dtos.solucao.CreateSolucaoDto;
import com.studyhub.sth.application.dtos.solucao.SolucaoDto;
import com.studyhub.sth.application.dtos.solucao.UpdateSolucaoDto;
import com.studyhub.sth.domain.entities.Duvida;
import com.studyhub.sth.domain.entities.Solucao;
import com.studyhub.sth.domain.entities.Usuario;
import com.studyhub.sth.domain.repositories.ISolucaoRepository;
import com.studyhub.sth.domain.repositories.IUsuarioRepository;
import com.studyhub.sth.domain.repositories.IDuvidaRepository;
import com.studyhub.sth.domain.services.ISolucaoService;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.transaction.Transactional;

@Service
public class SolucaoServive implements ISolucaoService{
    @Autowired
    private ISolucaoRepository solucaoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IDuvidaRepository duvidaRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public List<SolucaoDto> findAll() {
        return this.solucaoRepository
                .findAll()
                .stream()
                .map(d -> {
                    SolucaoDto dto = this.mapper.map(d, SolucaoDto.class);
                    dto.setNomeUsuario(d.getUsuario().getNome());
                    return dto;
                })
                .toList();
    } 

    @Override
    @Transactional
    public SolucaoDto create(CreateSolucaoDto solucaoDto){
        Usuario usuario = usuarioRepository.findById(solucaoDto.getUsuario()).orElseThrow(() -> new RuntimeException("Solucao não encontrada"));
        Duvida duvida = duvidaRepository.findById(solucaoDto.getDuvida()).orElseThrow(() -> new RuntimeException("Solucao não encontrada"));
        Solucao newSolucao = this.mapper.map(solucaoDto,Solucao.class);
        newSolucao.setUsuario(usuario);
        newSolucao.setDuvida(duvida);
        this.solucaoRepository.save(newSolucao);

        SolucaoDto dto = this.mapper.map(newSolucao, SolucaoDto.class);
        dto.setNomeUsuario(usuario.getNome());
        return dto;

    }

    @Override
    @Transactional
    public SolucaoDto update(UpdateSolucaoDto solucaoDto, UUID id){
        Solucao solucao =  solucaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Solucao não encontrada"));
        solucao.setDescricao(solucaoDto.getDescricao());
        this.solucaoRepository.save(solucao);

        SolucaoDto dto = this.mapper.map(solucao, SolucaoDto.class);
        return dto;
    }

    @Override
    public void delete(UUID solucaoId){
        Solucao solucao =  solucaoRepository.findById(solucaoId).orElseThrow(() -> new RuntimeException("Solucao não encontrada"));
        this.solucaoRepository.delete(solucao);
    }

}
