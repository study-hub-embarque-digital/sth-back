package com.studyhub.sth.application.dtos.solucao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolucaoDto {
    private UUID solucaoId;
    private String descricao;
    private String nomeUsuario;
    private Date criadoEm;
    private Date atualizadoEm;
}
