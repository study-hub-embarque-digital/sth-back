package com.studyhub.sth.application.dtos.discussao;

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
public class DiscussaoDto {
    private UUID discussaoId;
    private String conteudo;
    private Date atualizadoEm;
    private String nomeUsuario;
}
