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
    private DuvidaDto duvida;
    private List<SolucaoDto> solucoes;
    
}
