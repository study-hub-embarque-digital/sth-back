package com.studyhub.sth.domain.entities;

import com.studyhub.sth.domain.enums.StatusReuniao;
import com.studyhub.sth.domain.enums.TipoReuniao;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reunioes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "reuniaoId")
public class Reuniao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reuniaoId;

    @ManyToOne
    @JoinColumn(nullable = true, name = "sala_tematica_id")
    private SalaTematica salaTematica;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reuniao_participantes")
    private List<Usuario> participantes;

    @Column
    private Date criadoEm;

    @Column
    private Date statusAtualizadoEm;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario criadoPor;

    @Column
    private StatusReuniao status;

    @Column
    private TipoReuniao tipo;



    public Reuniao(Usuario criador, List<Usuario> participantes) {
        this.setStatus(StatusReuniao.AGENDADA);
        this.setTipo(TipoReuniao.Mentoria);
        this.setCriadoEm(new Date());
        this.setCriadoPor(criador);
        this.setStatusAtualizadoEm(new Date());

        if (this.participantes == null) {
            this.participantes = new ArrayList<>();
        }

        this.participantes.addAll(participantes);
    }

    public Reuniao(SalaTematica salaTematica, Usuario participanteInicial) {
        this.setStatus(StatusReuniao.EM_ESPERA);
        this.setSalaTematica(salaTematica);
        this.setTipo(TipoReuniao.SalaTematica);
        this.setCriadoEm(new Date());
        this.setCriadoPor(participanteInicial);
        this.setStatusAtualizadoEm(new Date());

        if (this.participantes == null) {
            this.participantes = new ArrayList<>();
        }

        this.participantes.add(participanteInicial);
    }

    public void addParticipante(Usuario participante) {
        boolean hasParticipant = this.participantes.stream().anyMatch(usuario -> usuario.equals(participante));

        if (hasParticipant) {
            return;
        }

        this.participantes.add(participante);

        if (this.participantes.size() >= 2 && this.status == StatusReuniao.EM_ESPERA) {
            this.setStatus(StatusReuniao.AGUARDANDO_PARTICIPANTES);
        }
    }

    public void cancelar() {
        this.setStatusAtualizadoEm(new Date());
        this.setStatus(StatusReuniao.CANCELADA);
    }

    public void iniciar() {
        this.setStatusAtualizadoEm(new Date());
        this.setStatus(StatusReuniao.ATIVA);
    }

    public void iniciarEstudo() {
        this.setStatusAtualizadoEm(new Date());
        this.setStatus(StatusReuniao.EM_ESTUDO);
    }

    public void iniciarApresentacoes() {
        this.setStatusAtualizadoEm(new Date());
        this.setStatus(StatusReuniao.EM_APRESENTACAO);
    }

    public void encerrar() {
        this.setStatusAtualizadoEm(new Date());
        this.setStatus(StatusReuniao.ENCERRADA);
    }
}
