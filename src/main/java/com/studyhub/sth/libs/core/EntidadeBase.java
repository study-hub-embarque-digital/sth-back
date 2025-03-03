package com.studyhub.sth.libs.core;

import com.studyhub.sth.domain.entities.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn
    private Usuario criadoPor;

    @Column
    private Date criadoEm;

    @OneToOne
    @JoinColumn
    private Usuario atualizadoPor;

    @Column
    private Date atualizadoEm;

    @OneToOne
    @JoinColumn
    private Usuario excluidoPor;

    @Column
    private Date excluidoEm;

    @Column
    private boolean excluido;

    @PrePersist
    public void aoCriar() {
        Date now = new Date();
        this.criadoEm = now;
        this.atualizadoEm = now;
    }

    @PreUpdate
    public void aoAtualizar() {
        this.atualizadoEm = new Date();
    }
}
