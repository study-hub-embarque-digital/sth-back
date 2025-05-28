package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.job.JobCreateDto;
import com.studyhub.sth.application.dtos.job.JobDto;
import com.studyhub.sth.application.dtos.job.JobListDto;
import com.studyhub.sth.application.dtos.job.JobUpdateDto;
import com.studyhub.sth.domain.entities.Aluno;
import com.studyhub.sth.domain.entities.Empregador;
import com.studyhub.sth.domain.entities.Job;
import com.studyhub.sth.domain.repositories.IAlunoRepository;
import com.studyhub.sth.domain.repositories.IEmpregadorRepository;
import com.studyhub.sth.domain.services.IJobService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.repositories.IJobRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService  implements IJobService {

    @Autowired
    private IMapper mapper;

    @Autowired
    private IJobRepository jobRepository;

    @Autowired
    private IAlunoRepository alunoRepository;

    @Autowired
    private IEmpregadorRepository empregadorRepository;

    @Override
    @Transactional
    public JobListDto criar(JobCreateDto dto) {
        Job job = new Job(dto);

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Empregador empregador = empregadorRepository.findById(dto.getEmpregadorId())
                .orElseThrow(() -> new EntityNotFoundException("Empregador não encontrado"));

        job.setAluno(aluno);
        job.setEmpregador(empregador);

        jobRepository.save(job);
        return mapper.map(job, JobListDto.class);
    }


    @Override
    public List<JobListDto> listar() {
        var lista = this.jobRepository.findAll();
        return lista.stream().map(job -> this.mapper.map(job, JobListDto.class)).collect(Collectors.toList());
    }

    @Override
    public JobDto buscarPorId(UUID id) {
        var job = this.jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job não encontrado"));
        return this.mapper.map(job, JobDto.class);
    }

    @Override
    @Transactional
    public JobListDto atualizar(UUID id, JobUpdateDto dto) {
        var job = this.jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job não encontrado"));
        if(dto.getCargo() != null){
            job.setCargo(dto.getCargo());
        }
        if(dto.getAreaAtuacao() != null){
            job.setAreaAtuacao(dto.getAreaAtuacao());
        }
        if(dto.getDataTermino() != null){
            job.setDataTermino(dto.getDataTermino());
        }
        if(dto.getAtividadesDesenvolvidas() != null){
            job.setAtividadesDesenvolvidas(dto.getAtividadesDesenvolvidas());
        }
        if(dto.getTipoVinculo() != null){
            job.setTipoVinculo(dto.getTipoVinculo());
        }
        this.jobRepository.save(job);
        return this.mapper.map(job, JobListDto.class);
    }

    @Override
    @Transactional
    public void deletar(UUID id) {
        var job = this.jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job não encontrado"));
        this.jobRepository.delete(job);
    }
}
