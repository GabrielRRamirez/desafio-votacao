package com.dbserver.desafiovotacao.modules.pauta.domain.entity;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.CreatePautaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pauta")
@NoArgsConstructor
@Data
public class Pauta {
    private static final String MSG_DESCRICAO_VAZIA = "A Descricão deve conter entre 10 e 255 caracteres!";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = MSG_DESCRICAO_VAZIA)
    @Pattern(regexp = "\\d{10,255}", message = MSG_DESCRICAO_VAZIA)
    private String descricao;

    public Pauta(CreatePautaDTO createPauta) {
        this.descricao = createPauta.descricao();
    }
}
