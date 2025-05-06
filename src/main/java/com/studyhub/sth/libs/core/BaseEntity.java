package com.studyhub.sth.libs.core;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
public class BaseEntity {
    private final UUID id;
    private Date createdAt;
    private Date updatedAt;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

    @PrePersist
    private void onPersist() {
        Date now = new Date();
        this.updatedAt = now;
        this.createdAt = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = new Date();
    }
}
