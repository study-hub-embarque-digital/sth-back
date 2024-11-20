package com.studyhub.sth.services.artigos;

import com.studyhub.sth.dtos.artigos.ArtigoAtualizadoDto;
import com.studyhub.sth.dtos.artigos.ArtigoDto;
import com.studyhub.sth.dtos.artigos.NovoArtigoDto;
import com.studyhub.sth.entities.Artigo;

import java.util.List;
import java.util.UUID;

public interface IArtigoService {
    ArtigoDto criar(NovoArtigoDto novoArtigoDto);
    ArtigoDto atualizar(UUID artigoId, ArtigoAtualizadoDto artigoAtualizadoDto);
    ArtigoDto detalhar(UUID artigoId);
    List<ArtigoDto> listarTodos();
    void deletar(UUID artigoId);
    
}
