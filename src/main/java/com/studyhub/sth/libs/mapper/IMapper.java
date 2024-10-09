package com.studyhub.sth.libs.mapper;

import org.springframework.stereotype.Service;

public interface IMapper {
    public <T, U> U map(T source, Class<U> destination);
}
