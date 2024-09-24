CREATE SEQUENCE staging.seq_cliente_formas_de_pago_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE staging.cliente_formas_de_pago
(
	ID_STAGING_CLIENTE_FORMAS_DE_PAGO integer NOT NULL DEFAULT nextval('staging.seq_cliente_formas_de_pago_pk'::regclass),
    NUMERO_TARJETA_CLIENTE character varying(100) COLLATE pg_catalog."default",
    ROWID character varying(100) COLLATE pg_catalog."default",
    SISTEMA_FUENTE character varying(100) COLLATE pg_catalog."default",
    BIN_TARJETA character varying(100) COLLATE pg_catalog."default",
    HASH_TARJETA_EXTERNA character varying(100) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE staging.seq_cliente_formas_de_pago_pk OWNER TO postgres;

ALTER TABLE staging.cliente_formas_de_pago ALTER COLUMN ID_STAGING_CLIENTE_FORMAS_DE_PAGO SET DEFAULT nextval('staging.seq_cliente_formas_de_pago_pk');
