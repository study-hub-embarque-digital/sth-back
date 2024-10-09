package com.studyhub.sth.libs.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper implements IMapper {
    @Autowired
    private ModelMapper mapper;

    @Override
    public <T, U> U map(T source, Class<U> destination) {
        return mapper.map(source, destination);
    }
}