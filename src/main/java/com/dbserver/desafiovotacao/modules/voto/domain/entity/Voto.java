package com.dbserver.desafiovotacao.modules.voto.domain.entity;

import com.dbserver.desafiovotacao.modules.associado.domain.entity.Associado;
import com.dbserver.desafiovotacao.modules.pauta.domain.entity.Pauta;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.CreateVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.enums.TipoEscolhaVoto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voto")
@NoArgsConstructor
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_associado")
    private Associado associado;

    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @Enumerated(EnumType.STRING)
    private TipoEscolhaVoto escolha;

    public Voto (CreateVotoDTO createVoto) {
        this.escolha = TipoEscolhaVoto.valueOf(createVoto.escolha());
    }
}
