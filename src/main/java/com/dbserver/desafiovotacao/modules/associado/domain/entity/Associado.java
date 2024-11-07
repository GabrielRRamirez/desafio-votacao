package com.dbserver.desafiovotacao.modules.associado.domain.entity;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.CreateAssociadoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "associado")
@NoArgsConstructor
@Data
public class Associado {

    private static final String MSG_VALIDACAO_NOME = "O nome precisa ter entre 5 e 255 caracteres!";
    private static final String MSG_VALIDACAO_CPF = "O CPF não pode ser vazio!";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = MSG_VALIDACAO_NOME)
    private String nome;

    @NotBlank(message = MSG_VALIDACAO_CPF)
    private String cpf;

    public Associado(CreateAssociadoDTO createAssociado) {
        this.nome = createAssociado.nome();
        this.cpf = createAssociado.cpf();
    }
}
