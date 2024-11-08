package com.dbserver.desafiovotacao.modules.sessao.domain.entity;

import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.CreateSessaoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
@NoArgsConstructor
@Data
public class Sessao {
    private static final String MSG_DATA_INICIO = "A data/hora de início precisa ser igual ou superior a data/hora atual!";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @FutureOrPresent(message = MSG_DATA_INICIO)
    @Column(name = "datahorainicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "datahoratermino")
    private LocalDateTime dataHoraTermino;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    public Sessao(CreateSessaoDTO createSessao) {
        this.dataHoraInicio = createSessao.dataHoraInicio();
        this.dataHoraTermino = createSessao.dataHoraTermino();
    }
}
