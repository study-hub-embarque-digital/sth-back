package com.studyhub.sth.services.representantes;

import com.studyhub.sth.dtos.representante.RepresentanteCreateDto;
import com.studyhub.sth.dtos.representante.RepresentanteDto;
import com.studyhub.sth.dtos.representante.RepresentanteUpdateDto;
import com.studyhub.sth.entities.Representante;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IRepresentanteService {
    public RepresentanteDto criarRepresentante(RepresentanteCreateDto dto) throws ElementoNaoEncontradoExcecao;
    public List<RepresentanteDto> listarRepresentantes();
    public RepresentanteDto obterRepresentantePorId(UUID id);
    public RepresentanteDto atualizarRepresentante(UUID id, RepresentanteUpdateDto dto);
    public void deletarRepresentante(UUID id);
}
