package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.job.JobCreateDto;
import com.studyhub.sth.application.dtos.job.JobListDto;
import com.studyhub.sth.application.dtos.job.JobUpdateDto;
import com.studyhub.sth.domain.before.entities.Job;
import com.studyhub.sth.domain.before.services.IJobService;
import com.studyhub.sth.libs.mapper.IMapper;
import com.studyhub.sth.domain.before.repositories.IJobRepository;
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

    @Override
    @Transactional
    public JobListDto criar(JobCreateDto dto) {
        Job job = this.mapper.map(dto, Job.class);
        this.jobRepository.save(job);
        return this.mapper.map(job, JobListDto.class);
    }


    @Override
    public List<JobListDto> listar() {
        var lista = this.jobRepository.findAll();
        return lista.stream().map(job -> this.mapper.map(job, JobListDto.class)).collect(Collectors.toList());
    }

    @Override
    public JobListDto buscarPorId(UUID id) {
        var job = this.jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job não encontrado"));
        return this.mapper.map(job, JobListDto.class);
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
