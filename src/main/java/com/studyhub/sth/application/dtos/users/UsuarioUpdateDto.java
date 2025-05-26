package com.studyhub.sth.application.dtos.users;

import com.studyhub.sth.domain.entities.Job;
import com.studyhub.sth.domain.enums.Ethnicity;
import com.studyhub.sth.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDto {
    private String nome;
    private String email;
    private String senha;
    private Ethnicity ethnicity;
    private Boolean isActive;
    private String phone;
    private Gender gender;
    private Boolean hasJob;
}