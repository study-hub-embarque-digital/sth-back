package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.squad.SquadUpdateDTO;
import com.studyhub.sth.domain.entities.Squad;
import com.studyhub.sth.domain.services.ISquadService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.EmpresaRepository;
import com.studyhub.sth.domain.repositories.IMentorRepository;
import com.studyhub.sth.domain.repositories.ISquadRepositorio;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SquadService implements ISquadService {

    @Autowired
    private ISquadRepositorio squadRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private IMentorRepository mentorRepository;

    @Autowired
    private IMapper mapper;

    @Override
    public List<SquadDTO> findAll() {
        List<Squad> squads = squadRepository.findAll();
        return squads.stream()
                .map(squad -> this.mapper.map(squad,SquadDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SquadDTO findById(UUID id) {
        var squad = this.squadRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Squad não encontrado!"));
        return this.mapper.map(squad, SquadDTO.class);
    }

    @Override
    @Transactional
    public SquadDTO save(SquadCreateDTO dto) {
        Squad squad = this.mapper.map(dto, Squad.class);
        this.squadRepository.save(squad);
        return this.mapper.map(squad, SquadDTO.class);
    }

    @Override
    @Transactional
    public SquadDTO update(UUID id, SquadUpdateDTO dto) {
        var squad = this.squadRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Squad não encontrado!"));
        if (dto.getMentorId() != null) {
            squad.setMentor(this.mentorRepository.findById(dto.getMentorId()).get());
        }
        if (dto.getEmpresaId() != null) {
            squad.setEmpresa(this.empresaRepository.findById(dto.getEmpresaId()).get());
        }
        if (dto.getTipo() != null) {
            squad.setTipo(dto.getTipo());
        }
        if (dto.getNome() != null) {
            squad.setNome(dto.getNome());
        }
        this.squadRepository.save(squad);
        return this.mapper.map(squad, SquadDTO.class);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        var squad = this.squadRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Squad não encontrado!"));
        squadRepository.delete(squad);
    }

    @Override
    public SquadDTO findBySquadNomeContainsIgnoreCase(String nome) {
        Squad squad = squadRepository.findByNomeContainsIgnoreCase(nome)
                .orElseThrow(() -> new NoSuchElementException("Squad não encontrado com o nome: " + nome));
        return this.mapper.map(squad, SquadDTO.class);
    }
}
