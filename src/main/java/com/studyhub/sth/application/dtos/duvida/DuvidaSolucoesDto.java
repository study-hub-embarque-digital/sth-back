package com.studyhub.sth.application.dtos.duvida;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.studyhub.sth.application.dtos.solucao.SolucaoDto;
import com.studyhub.sth.application.dtos.tag.TagDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DuvidaSolucoesDto {
    private UUID duvidaId;
    private String titulo;
    private String descricao;
    private List<TagDto> tags;
    private String nomeUsuario;
    private Date criadoEm;
    private Date atualizadoEm;
    private List<SolucaoDto> solucoes;
    
}
