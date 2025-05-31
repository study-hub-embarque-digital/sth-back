package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.encontro.EncontroCreateAndUpdateDto;
import com.studyhub.sth.application.dtos.encontro.EncontroDto;
import com.studyhub.sth.domain.entities.Encontro;
import com.studyhub.sth.domain.entities.Mentoria;
import com.studyhub.sth.domain.repositories.IEncontroRepository;
import com.studyhub.sth.domain.repositories.IMentoriaRepository;
import com.studyhub.sth.domain.services.IEcontroService;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EncontroService implements IEcontroService {
    @Autowired
    private IEncontroRepository encontroRepository;

    @Autowired
    private IMentoriaRepository mentoriaRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public EncontroDto save(EncontroCreateAndUpdateDto dto, UUID mentoriaId) {
        var mentoria = this.mentoriaRepository.findById(mentoriaId).orElseThrow(() -> new EntityNotFoundException("Mentoria não encontrada!"));
        Encontro encontro = this.mapper.map(dto, Encontro.class);
        encontro.setMentoria(mentoria);
        this.encontroRepository.save(encontro);
        return this.mapper.map(encontro, EncontroDto.class);
    }

    @Override
    public List<EncontroDto> saveList(Mentoria mentoria) {
        List<Encontro> encontros = new ArrayList<>();

        LocalDate dataAtual = mentoria.getDataInicio();

        while (!dataAtual.isAfter(mentoria.getDataFim())) {
            if (dataAtual.getDayOfWeek().equals(mentoria.getDiaDaSemana())) {
                Encontro encontro = new Encontro();
                encontro.setData(dataAtual);
                encontro.setMentoria(mentoria);
                encontros.add(encontro);
            }
            dataAtual = dataAtual.plusDays(1);
        }

        var lista = encontros.stream().map(encontro -> {
            EncontroDto encontroDto = this.mapper.map(encontro, EncontroDto.class);
            return encontroDto;
        }).toList();

        return lista;
    }

    @Override
    public EncontroDto update(UUID id, EncontroCreateAndUpdateDto dto) {
        Encontro encontro = this.encontroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Econtro não encontrado!"));
        if (dto.getData() != null) {
            encontro.setData(dto.getData());
        }
        if (dto.getHorario() != null) {
            encontro.setHorario(dto.getHorario());
        }
        if (dto.getPlataforma() != null) {
            encontro.setPlataforma(dto.getPlataforma());
        }
        if (dto.getLinkReuniao() != null) {
            encontro.setLinkReuniao(dto.getLinkReuniao());
        }

        return this.mapper.map(encontro, EncontroDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        Encontro encontro = this.encontroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Econtro não encontrado!"));
        this.encontroRepository.delete(encontro);
    }

    @Override
    public List<EncontroDto> listarPorMentoriaId(UUID mentoriaId) {
        var encontros = encontroRepository.findByMentoriaId(mentoriaId);
        return encontros.stream()
                .map(encontro -> {
                    EncontroDto encontroDto = this.mapper.map(encontro, EncontroDto.class);
                    return encontroDto;
                })
                .collect(Collectors.toList());
    }

}
