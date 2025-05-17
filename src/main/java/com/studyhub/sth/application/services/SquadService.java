package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.squad.SquadUpdateDTO;
import com.studyhub.sth.domain.entities.Aluno;
import com.studyhub.sth.domain.entities.Representante;
import com.studyhub.sth.domain.entities.Squad;
import com.studyhub.sth.domain.repositories.*;
import com.studyhub.sth.domain.services.ISquadService;
import com.studyhub.sth.libs.mapper.IMapper;
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
    private ISquadRepository squadRepository;

    @Autowired
    private IAlunoRepository alunoRepository;

    @Autowired
    private IRepresentanteRepository representanteRepository;

    @Autowired
    private IEmpresaRepository IEmpresaRepository;

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
        List<Aluno> alunos = alunoRepository.findAllById(dto.getAlunosIds());
        List<Representante> representantes = representanteRepository.findAllById(dto.getRepresentantesIds());

        squad.setAlunos(alunos);
        squad.setRepresentantes(representantes);

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
            squad.setEmpresa(this.IEmpresaRepository.findById(dto.getEmpresaId()).get());
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
