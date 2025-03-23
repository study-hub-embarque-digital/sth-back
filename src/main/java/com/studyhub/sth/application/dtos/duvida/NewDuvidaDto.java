package com.studyhub.sth.application.dtos.duvida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;
import com.studyhub.sth.application.dtos.tag.TagDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewDuvidaDto {
    private UUID usuario;
    private String titulo;
    private String descricao;
    private List<TagDto> tags;
}
