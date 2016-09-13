CREATE TABLE public.pessoa
(
  id integer NOT NULL DEFAULT nextval('pessoa_id_seq'::regclass),
  nome character varying(100) NOT NULL,
  idade integer,
  CONSTRAINT pessoa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.pessoa
  OWNER TO postgres;
