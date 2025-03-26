package com.studyhub.sth.application.dtos.duvida;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.studyhub.sth.application.dtos.solucao.SolucaoDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DuvidaSolucoesDto {
    private DuvidaDto duvida;
    private List<SolucaoDto> solucoes;
}
