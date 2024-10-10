package com.studyhub.sth.services.representantes;

import com.studyhub.sth.dtos.representante.NovoRepresentanteDto;
import com.studyhub.sth.entities.Representante;
import com.studyhub.sth.exceptions.ElementoNaoEncontradoExcecao;

import java.util.List;
import java.util.UUID;

public interface IRepresentanteService {
    public Representante criarRepresentante(NovoRepresentanteDto dto) throws ElementoNaoEncontradoExcecao;
    public List<Representante> listarRepresentantes();
    public Representante obterRepresentantePorId(UUID id);
    public Representante atualizarRepresentante(UUID id, NovoRepresentanteDto dto);
    public void deletarRepresentante(UUID id);
}
