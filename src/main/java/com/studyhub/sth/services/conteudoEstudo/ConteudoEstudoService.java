package com.studyhub.sth.services.conteudoEstudo;

import com.studyhub.sth.dtos.conteudoEstudo.ConteudoEstudoDto;
import com.studyhub.sth.entities.ConteudoEstudo;
import com.studyhub.sth.repositories.IConteudoEstudoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConteudoEstudoService {
    @Autowired
    private IConteudoEstudoRepository conteudoEstudoRepository;

    public ConteudoEstudo criarConteudoEstudo(ConteudoEstudoDto dto) {
        ConteudoEstudo conteudoEstudo = new ConteudoEstudo();
        // conteudoEstudo.setLink(dto.getLink());
        // conteudoEstudo.setRoom(dto.getRoom());
        // diretamente ou buscado
        return conteudoEstudoRepository.save(conteudoEstudo);
    }

    public List<ConteudoEstudo> listarConteudosEstudo() {
        return conteudoEstudoRepository.findAll();
    }

    public ConteudoEstudo obterConteudoEstudoPorId(UUID id) {
        return conteudoEstudoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo de estudo não encontrado"));
    }

    public ConteudoEstudo atualizarConteudoEstudo(UUID id, ConteudoEstudoDto dto) {
        ConteudoEstudo conteudoEstudo = obterConteudoEstudoPorId(id);
        conteudoEstudo.setLink(dto.getLink());
        // conteudoEstudo.setRoom(dto.getRoom());
        return conteudoEstudoRepository.save(conteudoEstudo);
    }

    public void deletarConteudoEstudo(UUID id) {
        conteudoEstudoRepository.deleteById(id);
    }
}
