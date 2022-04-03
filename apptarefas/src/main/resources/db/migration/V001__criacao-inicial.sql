CREATE TABLE departamento (
    id bigint NOT NULL,
    titulo character varying(50),
	CONSTRAINT departamento_pkey PRIMARY KEY (id)
);

CREATE TABLE pessoa (
    id bigint NOT NULL,
    nome character varying(50),
    departamento_id bigint,
	CONSTRAINT pessoa_pkey PRIMARY KEY (id),
	CONSTRAINT departamento_dpkey FOREIGN KEY (departamento_id) REFERENCES departamento(id)
);


CREATE TABLE tarefa (
    id bigint NOT NULL,
    descricao character varying(50),
    duracao integer NOT NULL,
    finalizado boolean NOT NULL,
    prazo date NOT NULL,
    titulo character varying(50),
    departamento_id bigint,
    pessoa_id bigint,
	CONSTRAINT tarefa_pkey PRIMARY KEY (id),
	CONSTRAINT departamento_dtkey FOREIGN KEY (departamento_id) REFERENCES departamento(id),
	CONSTRAINT pessoa_ptkey FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);

INSERT INTO departamento VALUES (2, 'Comercial');
INSERT INTO departamento VALUES (1, 'Financeiro');
INSERT INTO departamento VALUES (3, 'Desenvolvimento');

INSERT INTO pessoa VALUES (1, 'Camila', 1);
INSERT INTO pessoa VALUES (2, 'Pedro', 2);
INSERT INTO pessoa VALUES (3, 'Fabiano', 3);
INSERT INTO pessoa VALUES (4, 'Raquel', 3);
INSERT INTO pessoa VALUES (5, 'Patricia', 3);
INSERT INTO pessoa VALUES (6, 'Joaquim', 1);

INSERT INTO tarefa VALUES (1, 'Validar notas recebidas no mês de Janeiro', 14, true, '2022-02-15', 'Validar NF Janeiro', 1, 1);
INSERT INTO tarefa VALUES (2, 'Corrigir bug 352 na versão 1.25', 25, false, '2022-05-10', 'Bug 352', 3, NULL);
INSERT INTO tarefa VALUES (3, 'Disponibilizar pacote para testes', 2, false, '2022-02-02', 'Liberação da versão 1.24', 3, 3);
INSERT INTO tarefa VALUES (4, 'Reunião com cliente A para apresentação do produto', 5, false, '2022-02-05', 'Reunião A', 2, NULL);
INSERT INTO tarefa VALUES (5, 'Fechamento contrato', 6, false, '2022-03-28', 'Reunião final', 2, NULL);
INSERT INTO tarefa VALUES (6, 'Realizar pagamento dos fornecedores', 6, true, '2022-01-31', 'Pagamento 01/2022', 1, 1);
INSERT INTO tarefa VALUES (7, 'Corrigir bug 401 na versão 1.20', 2, true, '2022-02-01', 'Bug 401', 3, 4);
INSERT INTO tarefa VALUES (8, 'Corrigir bug 399 na versão 1.20', 6, true, '2022-01-28', 'Bug 399', 3, 5);
INSERT INTO tarefa VALUES (9, 'Reunião com cliente B para apresentação do produto', 5, true, '2022-01-31', 'Reunião B', 2, 2);
INSERT INTO tarefa VALUES (11, 'Validar notas recebidas no mês de Fevereiro', 14, false, '2022-03-15', 'Validar NF Fevereiro', 1, 6);


