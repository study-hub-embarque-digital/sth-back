package com.studyhub.sth.application.dtos.tag;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TagDto {
    private String nome;
    private UUID id;
}
