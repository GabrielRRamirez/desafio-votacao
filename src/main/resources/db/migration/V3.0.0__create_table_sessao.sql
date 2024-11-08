CREATE TABLE IF NOT EXISTS sessao (
    id SERIAL NOT NULL,
    datahorainicio TIMESTAMP NOT NULL,
    datahoratermino TIMESTAMP NOT NULL,
    id_pauta INTEGER NOT NULL,
    CONSTRAINT pk_sessao PRIMARY KEY (id),
    CONSTRAINT id_pauta FOREIGN KEY (id_pauta) REFERENCES pauta (id));