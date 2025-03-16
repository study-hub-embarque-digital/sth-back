package com.studyhub.sth.application.dtos.solucao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.studyhub.sth.application.dtos.tag.TagDto;
import com.studyhub.sth.domain.entities.Duvida;
import com.studyhub.sth.domain.entities.Usuario;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSolucaoDto {
    private String descricao;
}
