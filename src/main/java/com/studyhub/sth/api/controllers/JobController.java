package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.dtos.job.JobCreateDto;
import com.studyhub.sth.application.dtos.job.JobDto;
import com.studyhub.sth.application.dtos.job.JobListDto;
import com.studyhub.sth.application.dtos.job.JobUpdateDto;
import com.studyhub.sth.domain.services.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    @Autowired
    private IJobService jobService;

    @PostMapping
    public ResponseEntity<JobListDto> criar(@RequestBody JobCreateDto dto) {
        JobListDto jobDto = jobService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobDto);
    }

    @GetMapping
    public ResponseEntity<List<JobListDto>> listar() {
        List<JobListDto> jobs = jobService.listar();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> buscarPorId(@PathVariable UUID id) {
        JobDto jobDto = jobService.buscarPorId(id);
        return ResponseEntity.ok(jobDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobListDto> atualizar(@PathVariable UUID id, @RequestBody JobUpdateDto dto) {
        JobListDto jobDto = jobService.atualizar(id, dto);
        return ResponseEntity.ok(jobDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        jobService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
