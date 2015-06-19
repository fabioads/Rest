CREATE TABLE cliente
(
  id integer NOT NULL DEFAULT nextval('cliente_id_seq'::regclass),
  nome character varying(100),
  cpf character varying(11),
  endereco character varying(200),
  CONSTRAINT cliente_pkey PRIMARY KEY (id)
)
