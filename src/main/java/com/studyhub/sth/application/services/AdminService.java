package com.studyhub.sth.application.services;

import com.studyhub.sth.application.dtos.alunos.AlunoCreateDto;
import com.studyhub.sth.application.dtos.alunos.AlunoDto;
import com.studyhub.sth.application.dtos.alunos.AlunoUpdateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaCreateDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaListDto;
import com.studyhub.sth.application.dtos.empresas.EmpresaUpdateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoCreateDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoListDto;
import com.studyhub.sth.application.dtos.instituicaoEnsino.InstituicaoEnsinoUpdateDto;
import com.studyhub.sth.application.dtos.mentor.MentorCreateDto;
import com.studyhub.sth.application.dtos.mentor.MentorDto;
import com.studyhub.sth.application.dtos.mentor.MentorUpdateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteDto;
import com.studyhub.sth.application.dtos.representante.RepresentanteUpdateDto;

import com.studyhub.sth.application.dtos.squad.SquadCreateDTO;
import com.studyhub.sth.application.dtos.squad.SquadDTO;
import com.studyhub.sth.application.dtos.squad.SquadUpdateDTO;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import com.studyhub.sth.domain.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAlunoService alunoService;
    @Autowired
    private ISquadService squadService;
    @Autowired
    private IInstituicaoEnsinoService instituicaoEnsinoService;
    @Autowired
    private IEmpresaService empresaService;
    @Autowired
    private IMentorService mentorService;
    @Autowired
    private IRepresentanteService representanteService;

    // Métodos para Aluno
    @Override
    public String createAluno(AlunoCreateDto alunoCreateDto) throws Exception {
        return alunoService.criar(alunoCreateDto);
    }

    @Override
    public AlunoDto updateAluno(UUID alunoId, AlunoUpdateDto alunoUpdateDto) throws ElementoNaoEncontradoExcecao, IOException {
        return alunoService.atualizar(alunoId, alunoUpdateDto);
    }

    @Override
    public AlunoDto getAlunoById(UUID alunoId) throws ElementoNaoEncontradoExcecao {
        return alunoService.detalhar(alunoId);
    }

    @Override
    public List<AlunoDto> listAlunos() {
        return alunoService.listarTodos();
    }

    @Override
    public List<AlunoDto> listAlunosByPeriodo(Periodo periodo) {
        return alunoService.listarPorPeriodo(periodo);
    }

    // Métodos para Squad
    @Override
    public List<SquadDTO> listAllSquads() {
        return squadService.findAll();
    }

    @Override
    public SquadDTO getSquadById(UUID id) {
        return squadService.findById(id);
    }

    @Override
    public SquadDTO createSquad(SquadCreateDTO squadCreateDto) {
        return squadService.save(squadCreateDto);
    }

    @Override
    public void deleteSquad(UUID id) {
        squadService.deleteById(id);
    }

    @Override
    public SquadDTO updateSquad(UUID id, SquadUpdateDTO squadUpdateDto) {
        return squadService.update(id, squadUpdateDto);
    }

    @Override
    public SquadDTO findSquadByName(String name) {
        return squadService.findBySquadNomeContainsIgnoreCase(name);
    }

    // Métodos para Instituição de Ensino
    @Override
    public List<InstituicaoEnsinoDto> listAllInstituicoesEnsino() {
        return instituicaoEnsinoService.findAll();
    }

    @Override
    public List<InstituicaoEnsinoListDto> listInstituicoesEnsino() {
        return instituicaoEnsinoService.list();
    }

    @Override
    public InstituicaoEnsinoDto getInstituicaoEnsinoById(UUID id) {
        return instituicaoEnsinoService.findById(id);
    }

    @Override
    public InstituicaoEnsinoDto updateInstituicaoEnsino(UUID id, InstituicaoEnsinoUpdateDto dto) {
        return instituicaoEnsinoService.update(id, dto);
    }

    @Override
    public InstituicaoEnsinoDto createInstituicaoEnsino(InstituicaoEnsinoCreateDto dto) {
        return instituicaoEnsinoService.save(dto);
    }

    @Override
    public void deleteInstituicaoEnsino(UUID id) {
        instituicaoEnsinoService.delete(id);
    }

    // Métodos para Empresa
    @Override
    public List<EmpresaDto> listAllEmpresas() {
        return empresaService.findAll();
    }

    @Override
    public List<EmpresaListDto> listEmpresas() {
        return empresaService.list();
    }

    @Override
    public EmpresaDto getEmpresaById(UUID id) {
        return empresaService.findById(id);
    }

    @Override
    public EmpresaDto updateEmpresa(UUID empresaId, EmpresaUpdateDto empresaDto) {
        return empresaService.update(empresaId, empresaDto);
    }

    @Override
    public EmpresaDto createEmpresa(EmpresaCreateDto empresaCreateDto) {
        return empresaService.save(empresaCreateDto);
    }

    @Override
    public void deleteEmpresa(UUID id) {
        empresaService.delete(id);
    }

    // Métodos para Mentor
    @Override
    public String createMentor(MentorCreateDto dto) throws Exception {
        return mentorService.criar(dto);
    }

    @Override
    public List<MentorDto> listMentores() {
        return mentorService.listar();
    }

    @Override
    public MentorDto getMentorById(UUID id) throws ElementoNaoEncontradoExcecao {
        return mentorService.buscarPorId(id);
    }

    @Override
    public void deleteMentorById(UUID id) throws ElementoNaoEncontradoExcecao {
        mentorService.deletarPorId(id);
    }

    @Override
    public MentorDto updateMentor(UUID id, MentorUpdateDto dto) throws ElementoNaoEncontradoExcecao {
        return mentorService.atualizar(id, dto);
    }

    @Override
    public List<SquadDTO> listSquadsForMentor(UUID id) {
        return mentorService.listarSquads(id);
    }

    @Override
    public MentorDto getMentorByName(String nome) throws ElementoNaoEncontradoExcecao {
        return mentorService.buscarPorNome(nome);
    }

    // Métodos para Representante
    @Override
    public String createRepresentante(RepresentanteCreateDto dto) throws Exception {
        return representanteService.criarRepresentante(dto);
    }

    @Override
    public List<RepresentanteDto> listRepresentantes() {
        return representanteService.listarRepresentantes();
    }

    @Override
    public RepresentanteDto getRepresentanteById(UUID id) {
        return representanteService.obterRepresentantePorId(id);
    }

    @Override
    public RepresentanteDto updateRepresentante(UUID id, RepresentanteUpdateDto dto) {
        return representanteService.atualizarRepresentante(id, dto);
    }

    @Override
    public void deleteRepresentante(UUID id) {
        representanteService.deletarRepresentante(id);
    }
}
