CREATE TABLE IF NOT EXISTS voto (
    id SERIAL NOT NULL,
    id_pauta INTEGER NOT NULL,
    id_associado INTEGER NOT NULL,
    escolha VARCHAR (3) NOT NULL,
    CONSTRAINT pk_voto PRIMARY KEY (id),
    CONSTRAINT fk_id_pauta_voto FOREIGN KEY (id_pauta) REFERENCES pauta (id),
    CONSTRAINT fk_id_associado_voto FOREIGN KEY (id_associado) REFERENCES associado (id));