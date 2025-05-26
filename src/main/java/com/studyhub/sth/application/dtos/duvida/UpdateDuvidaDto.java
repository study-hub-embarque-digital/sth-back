package com.studyhub.sth.application.dtos.duvida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.studyhub.sth.application.dtos.tag.TagDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDuvidaDto {
    private String titulo;
    private String descricao;
    private List<TagDto> tags;
}
