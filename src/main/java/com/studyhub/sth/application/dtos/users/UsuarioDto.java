package com.studyhub.sth.application.dtos.users;

import com.studyhub.sth.application.dtos.job.JobDto;
import com.studyhub.sth.domain.enums.Ethnicity;
import com.studyhub.sth.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private UUID usuarioId;
    private String nome;
    private String email;
    private Boolean isActive;
    private String phone;
    private Gender gender;
    private Ethnicity ethnicity;
    private String fotoPerfil;
    private List<JobDto> jobs;
    private Date dataNascimento;
    private Boolean hasJob;
}
