package com.studyhub.sth.domain.services;

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
import com.studyhub.sth.application.dtos.squad.SquadListDTO;
import com.studyhub.sth.application.dtos.squad.SquadUpdateDTO;
import com.studyhub.sth.domain.enums.Periodo;
import com.studyhub.sth.domain.exceptions.ElementoNaoEncontradoExcecao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IAdminService {

    // Métodos para Aluno
    AlunoDto createAluno(AlunoCreateDto alunoCreateDto) throws Exception;
    AlunoDto updateAluno(UUID alunoId, AlunoUpdateDto alunoUpdateDto) throws ElementoNaoEncontradoExcecao, IOException;
    AlunoDto getAlunoById(UUID alunoId) throws ElementoNaoEncontradoExcecao;
    List<AlunoDto> listAlunos();
    List<AlunoDto> listAlunosByPeriodo(Periodo periodo);

    // Métodos para Squad
    List<SquadDTO> listAllSquads();
    SquadListDTO getSquadById(UUID id);
    SquadDTO createSquad(SquadCreateDTO squadCreateDto);
    void deleteSquad(UUID id);
    SquadDTO updateSquad(UUID id, SquadUpdateDTO squadUpdateDto);
    SquadDTO findSquadByName(String name);

    // Métodos para Instituição de Ensino
    List<InstituicaoEnsinoDto> listAllInstituicoesEnsino();
    List<InstituicaoEnsinoListDto> listInstituicoesEnsino();
    InstituicaoEnsinoDto getInstituicaoEnsinoById(UUID id);
    InstituicaoEnsinoDto updateInstituicaoEnsino(UUID id, InstituicaoEnsinoUpdateDto dto);
    InstituicaoEnsinoDto createInstituicaoEnsino(InstituicaoEnsinoCreateDto dto);
    void deleteInstituicaoEnsino(UUID id);

    // Métodos para Empresa
    List<EmpresaDto> listAllEmpresas();
    List<EmpresaListDto> listEmpresas();
    EmpresaDto getEmpresaById(UUID id);
    EmpresaDto updateEmpresa(UUID empresaId, EmpresaUpdateDto empresaDto);
    EmpresaDto createEmpresa(EmpresaCreateDto empresaCreateDto);
    void deleteEmpresa(UUID id);

    // Métodos para Mentor
    MentorDto createMentor(MentorCreateDto dto) throws Exception;
    List<MentorDto> listMentores();
    MentorDto getMentorById(UUID id) throws ElementoNaoEncontradoExcecao;
    void deleteMentorById(UUID id) throws ElementoNaoEncontradoExcecao;
    MentorDto updateMentor(UUID id, MentorUpdateDto dto) throws ElementoNaoEncontradoExcecao;
    List<SquadDTO> listSquadsForMentor(UUID id);
    MentorDto getMentorByName(String nome) throws ElementoNaoEncontradoExcecao;

    // Métodos para Representante
    RepresentanteDto createRepresentante(RepresentanteCreateDto dto) throws Exception;
    List<RepresentanteDto> listRepresentantes();
    RepresentanteDto getRepresentanteById(UUID id);
    RepresentanteDto updateRepresentante(UUID id, RepresentanteUpdateDto dto);
    void deleteRepresentante(UUID id);
}
