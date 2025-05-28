package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.encontro.EncontroDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaCreateDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaCreateWithEncontrosDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaDto;
import com.studyhub.sth.application.dtos.mentoria.MentoriaUpdateDto;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.domain.entities.Encontro;
import com.studyhub.sth.domain.entities.Mentoria;
import com.studyhub.sth.domain.entities.Squad;
import com.studyhub.sth.domain.repositories.IMentoriaRepository;
import com.studyhub.sth.domain.repositories.ISquadRepository;
import com.studyhub.sth.domain.services.IEcontroService;
import com.studyhub.sth.domain.services.IMentoriaService;
import com.studyhub.sth.libs.mapper.IMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MentoriaService implements IMentoriaService {
    @Autowired
    private IMentoriaRepository mentoriaRepository;

    @Autowired
    private IMapper mapper;

    @Autowired
    private ISquadRepository squadRepository;

    @Autowired
    private IEcontroService econtroService;

    @Override
    public MentoriaDto save(MentoriaCreateDto dto){
        Mentoria mentoria = this.mapper.map(dto, Mentoria.class);
        Squad squad = this.squadRepository.findById(dto.getSquadId()).orElseThrow(()-> new EntityNotFoundException("Squad não encontrado!"));
        mentoria.setSquad(squad);
        this.mentoriaRepository.save(mentoria);

        MentoriaDto mentoriaDto = this.mapper.map(mentoria, MentoriaDto.class);
        SquadDTO squadDTO = this.mapper.map(squad, SquadDTO.class);
        mentoriaDto.setSquadDTO(squadDTO);

        return  mentoriaDto;
    }

    @Override
    public MentoriaDto findById(UUID id){
        Mentoria mentoria = this.mentoriaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Mentoria não encontrada!"));
        MentoriaDto mentoriaDto = this.mapper.map(mentoria, MentoriaDto.class);
        SquadDTO squadDTO = this.mapper.map(mentoria.getSquad(), SquadDTO.class);

        List<Encontro> encontros = mentoria.getEncontros();

        encontros.stream().forEach( encontro -> {
            EncontroDto encontroDto = this.mapper.map(encontro, EncontroDto.class);
            mentoriaDto.getEncontros().add(encontroDto);
        });


        mentoriaDto.setSquadDTO(squadDTO);

        return  mentoriaDto;
    }

    @Override
    public List<MentoriaDto> findAll(){
        var mentorias = this.mentoriaRepository.findAll();
        var lista = mentorias.stream().map(mentoria -> {
           MentoriaDto mentoriaDto = this.mapper.map(mentoria,MentoriaDto.class);
           SquadDTO squadDTO = this.mapper.map(mentoria.getSquad(), SquadDTO.class);
           mentoriaDto.setSquadDTO(squadDTO);
           List<Encontro> encontros = mentoria.getEncontros();
           encontros.stream().forEach( encontro -> {
                EncontroDto encontroDto = this.mapper.map(encontro, EncontroDto.class);
                mentoriaDto.getEncontros().add(encontroDto);
           });
            return mentoriaDto;
        }).toList();

        return lista;
    }

    @Override
    public MentoriaDto update(UUID id, MentoriaUpdateDto dto){
        var mentoria =  this.mentoriaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Mentoria não encontrada!"));

        if(dto.getDataInicio() != null){
            mentoria.setDataInicio(dto.getDataInicio());
        }
        if(dto.getDataFim() != null){
            mentoria.setDataFim(dto.getDataFim());
        }
        if(dto.getDiaDaSemana() != null){
            mentoria.setDiaDaSemana(dto.getDiaDaSemana());
        }

        MentoriaDto mentoriaDto = this.mapper.map(mentoria,MentoriaDto.class);
        return mentoriaDto;
    }

    @Override
    public void deleteById(UUID id){
        var mentoria =  this.mentoriaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Mentoria não encontrada!"));
        this.mentoriaRepository.delete(mentoria);
    }

    @Override
    public MentoriaDto saveWithEncontros(MentoriaCreateWithEncontrosDto dto) {
        Squad squad = this.squadRepository.findById(dto.getSquadId()).orElseThrow(()-> new EntityNotFoundException("Squad não encontrado!"));
        Mentoria mentoria = new Mentoria(squad, dto.getDiaDaSemana(), dto.getDataInicio(), dto.getDataFim());
        mentoria.setSquad(squad);
        this.mentoriaRepository.save(mentoria);
        this.econtroService.saveList(mentoria);

        MentoriaDto mentoriaDto = this.mapper.map(mentoria, MentoriaDto.class);
        SquadDTO squadDTO = this.mapper.map(mentoria.getSquad(), SquadDTO.class);
        mentoriaDto.setSquadDTO(squadDTO);

        List<Encontro> encontros = mentoria.getEncontros();
        encontros.stream().forEach( encontro -> {
            EncontroDto encontroDto = this.mapper.map(encontro, EncontroDto.class);
            mentoriaDto.getEncontros().add(encontroDto);
        });

        return mentoriaDto;
    }

}
