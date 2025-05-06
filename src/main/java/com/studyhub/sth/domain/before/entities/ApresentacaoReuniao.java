package com.studyhub.sth.domain.before.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "apresentacoes_reunioes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "apresentacaoReuniaoId")
public class ApresentacaoReuniao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID apresentacaoReuniaoId;

    @Column
    private int ordem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "reuniao_id")
    private Reuniao reuniao;

    @Column
    private Date horaInicio;

    @Column
    private Date horaFim;

    @Column
    private boolean finalizada;

    @Column
    private Date inicioDiscussao;

    @Column
    private Date fimDiscussao;

    @Column
    private boolean discussaoFinalizada;

    @Column
    private String conteudo;


    @ManyToMany
    @JoinTable(name = "topicos_apresentacoes_reunioes",
            joinColumns = @JoinColumn(name = "apresentacao_reuniao_id"),
            inverseJoinColumns = @JoinColumn(name = "topico_id"))
    private List<Topico> topicos;


    public ApresentacaoReuniao(int ordem, Usuario usuario, Reuniao reuniao) {
        this.ordem = ordem;
        this.usuario = usuario;
        this.reuniao = reuniao;
    }

    public void addTopico(Topico topico) {
        if (this.topicos == null) {
            this.setTopicos(new ArrayList<>());
        }

        this.topicos.add(topico);
    }

    public void addTopicos(List<Topico> topicos) {
        if (this.topicos == null) {
            this.setTopicos(new ArrayList<>());
        }

        this.topicos.addAll(topicos);
    }

    public void iniciar() {
        this.setHoraInicio(new Date());
    }

    public void finalizar() {
        this.setHoraFim(new Date());
        this.setFinalizada(true);
    }

    public void iniciarDiscussao() {
        this.setInicioDiscussao(new Date());
    }

    public void finalizarDiscussao() {
        this.setFimDiscussao(new Date());
        this.setDiscussaoFinalizada(true);
    }
}
