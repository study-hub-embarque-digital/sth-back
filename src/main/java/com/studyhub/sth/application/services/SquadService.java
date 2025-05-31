package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.alunos.AlunoListDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.graficos.EmpresaSquadCountDto;
import com.studyhub.sth.application.dtos.graficos.SquadsDemodayPorInstituicaoDTO;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoListDto;
import com.studyhub.sth.application.dtos.mentor.MentorListDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteListDto;
import com.studyhub.sth.application.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.squad.SquadListDTO;
import com.studyhub.sth.application.dtos.squad.SquadUpdateDTO;
import com.studyhub.sth.application.dtos.users.UsuarioDto;
import com.studyhub.sth.domain.entities.*;
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
    private IEmpresaRepository empresaRepository;

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

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
    public SquadListDTO findById(UUID id) {
        var squad = this.squadRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Squad não encontrado!"));
        SquadListDTO squadDTO = this.mapper.map(squad, SquadListDTO.class);

        List<AlunoListDto> alunos = squad.getAlunos().stream()
                .map(aluno -> {
                  AlunoListDto alunoListDto =  this.mapper.map(aluno, AlunoListDto.class);
                  UsuarioDto usuarioDto = this.mapper.map(aluno.getUsuario(), UsuarioDto.class);
                  alunoListDto.setUsuarioDto(usuarioDto);
                  return alunoListDto;
                }).toList();
        List<RepresentanteListDto> representantes = squad.getRepresentantes().stream().map(representante ->
        {RepresentanteListDto representanteListDto = this.mapper.map(representante, RepresentanteListDto.class);
            UsuarioDto usuarioDto = this.mapper.map(representante.getUsuario(), UsuarioDto.class);
            representanteListDto.setUsuarioDto(usuarioDto);
            return representanteListDto;
        }).toList();

        MentorListDto mentor = this.mapper.map(squad.getMentor(), MentorListDto.class);
        UsuarioDto usuarioDto = this.mapper.map(squad.getMentor().getUsuario(), UsuarioDto.class);
        mentor.setUsuarioDto(usuarioDto);

        InstituicaoEnsinoListDto instituicaoEnsino = this.mapper.map(squad.getInstituicaoEnsino(), InstituicaoEnsinoListDto.class);
        EmpresaDto empresa = this.mapper.map(squad.getEmpresa(), EmpresaDto.class);

        squadDTO.setAlunoListDtos(alunos);
        squadDTO.setRepresentanteListDtos(representantes);
        squadDTO.setMentorDto(mentor);
        squadDTO.setInstituicaoDeEnsinoDto(instituicaoEnsino);
        squadDTO.setEmpresaDto(empresa);

        return squadDTO;
    }

    @Override
    @Transactional
    public SquadDTO save(SquadCreateDTO dto) {
        List<Aluno> alunos = this.alunoRepository.findAllById(dto.getAlunosIds());
        List<Representante> representantes = this.representanteRepository.findAllById(dto.getRepresentantesIds());

        Mentor mentor = this.mentorRepository.findById(dto.getMentorId())
                .orElseThrow(() -> new EntityNotFoundException("Mentor não encontrado!"));

        InstituicaoEnsino instituicaoEnsino = this.instituicaoEnsinoRepository.findById(dto.getInstituicaoDeEnsinoId())
                .orElseThrow(() -> new EntityNotFoundException("IES não encontrada!"));

        Empresa empresa = this.empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada!"));

        Squad squad = new Squad(dto.getNome(), dto.getTipo(), dto.getSemestre(), dto.getCiclo());

        squad.setMentor(mentor);
        squad.setAlunos(alunos);
        squad.setEmpresa(empresa);
        squad.setInstituicaoEnsino(instituicaoEnsino);
        squad.setRepresentantes(representantes);

        for (Aluno aluno : alunos) {
            aluno.setSquad(squad);
        }

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
        if (dto.getSelecionadoParaDemoDay() != null) {
            squad.setSelecionadoParaDemoDay(dto.getSelecionadoParaDemoDay());
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

    @Override
    public List<EmpresaSquadCountDto> countSquadsPorEmpresa(){
        return  this.squadRepository.countSquadsPorEmpresa();
    }

    @Override
    public List<SquadsDemodayPorInstituicaoDTO> findSquadsSelecionadosPorInstituicao(){
        return this.squadRepository.findSquadsSelecionadosPorInstituicao();
    }


}
