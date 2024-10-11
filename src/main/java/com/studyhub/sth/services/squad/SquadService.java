package com.studyhub.sth.services.squad;

import com.studyhub.sth.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.dtos.squad.SquadDTO;
import com.studyhub.sth.dtos.squad.SquadUpdateDTO;
import com.studyhub.sth.entities.Empresa;
import com.studyhub.sth.entities.Mentor;
import com.studyhub.sth.entities.Squad;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.repositories.EmpresaRepository;
import com.studyhub.sth.repositories.IMentorRepository;
import com.studyhub.sth.repositories.ISquadRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Optional<SquadDTO> findById(UUID id) {
        return squadRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional
    public SquadDTO save(SquadCreateDTO squadCreateDTO) {
        Squad squad = new Squad();
        Empresa empresa = this.empresaRepository.findById(squadCreateDTO.getEmpresaId()).orElseThrow();
        Mentor mentor = this.mentorRepository.findById(squadCreateDTO.getMentorId()).orElseThrow();

        squad.setNome(squadCreateDTO.getNome());
        squad.setTipo(squadCreateDTO.getTipo());
        squad.setEmpresa(empresa);
        squad.setMentor(mentor);

        Squad savedSquad = squadRepository.save(squad);

        return convertToDTO(savedSquad);
    }

    @Override
    @Transactional
    public Optional<SquadDTO> update(UUID id, SquadUpdateDTO squadUpdateDTO) {
        return squadRepository.findById(id).map(squad -> {
            Empresa empresa = this.empresaRepository.findById(squadUpdateDTO.getEmpresaId())
                    .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada com ID: " + squadUpdateDTO.getEmpresaId()));
            Mentor mentor = this.mentorRepository.findById(squadUpdateDTO.getMentorId())
                    .orElseThrow(() -> new NoSuchElementException("Mentor não encontrado com ID: " + squadUpdateDTO.getMentorId()));

            squad.setNome(squadUpdateDTO.getNome());
            squad.setTipo(squadUpdateDTO.getTipo());
            squad.setEmpresa(empresa);
            squad.setMentor(mentor);

            Squad savedSquad = squadRepository.save(squad);

            return convertToDTO(savedSquad);
        });
    }


    @Override
    @Transactional
    public void deleteById(UUID id) {
        squadRepository.deleteById(id);
    }

    private SquadDTO convertToDTO(Squad squad) {
        if (squad == null) {
            return null;
        }


        UUID empresaId = (squad.getEmpresa() != null) ? squad.getEmpresa().getEmpresaId() : null;

        return SquadDTO.builder()
                .id(squad.getSquadId())
                .nome(squad.getNome())
                .tipo(squad.getTipo())
                .empresaId(empresaId)
                .build();
    }

    public SquadDTO findBySquadNomeContainsIgnoreCase(String nome) {
        Squad squad = squadRepository.findByNome(nome)
                .orElseThrow(() -> new NoSuchElementException("Squad não encontrado com o nome: " + nome));
        return this.mapper.map(squad, SquadDTO.class);
    }
}
