package com.studyhub.sth.domain.before.services;

import com.studyhub.sth.application.dtos.job.JobCreateDto;
import com.studyhub.sth.application.dtos.job.JobListDto;
import com.studyhub.sth.application.dtos.job.JobUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IJobService {
    JobListDto criar(JobCreateDto dto);

    List<JobListDto> listar();

    JobListDto buscarPorId(UUID id);

    JobListDto atualizar(UUID id, JobUpdateDto dto);

    void deletar(UUID id);
}