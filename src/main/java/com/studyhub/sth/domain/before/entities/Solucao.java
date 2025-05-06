package com.studyhub.sth.domain.before.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "solucoes")
@Table(name = "solucoes")
@EqualsAndHashCode(of = "solucaoId")

public class Solucao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID solucaoId;
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "duvida_id")
    private Duvida duvida;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Date criadoEm;
    private Date atualizadoEm;
    
    @ManyToOne
    @JoinColumn(name = "atualizado_por")
    private Usuario atualizadoPor;

    @PrePersist
    public void onPersist()
    {
        Date d = new Date();
        this.setCriadoEm(d);
        this.setAtualizadoEm(d);
    }

    @PreUpdate
    public void onUpdate()
    {
        this.setAtualizadoEm(new Date());
    }
}
