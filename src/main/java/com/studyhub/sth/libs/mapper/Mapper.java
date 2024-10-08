package com.studyhub.sth.libs.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class Mapper implements IMapper {
    private final ModelMapper mapper;

    public Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T, U> U map(T source, Class<U> destination) {
        return mapper.map(source, destination);
    }
}