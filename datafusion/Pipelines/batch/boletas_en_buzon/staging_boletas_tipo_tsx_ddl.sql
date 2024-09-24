CREATE SEQUENCE staging.seq_boletas_en_buzon_tipo_tsx_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE staging.boletas_en_buzon_tipo_tsx
(
    ID_BOLETAS_TIPO_TSX integer NOT NULL DEFAULT nextval('staging.seq_boletas_en_buzon_tipo_tsx_pk'::regclass),
    ID_TIPO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	DESCRIPCION_CATALOGO character varying(100) COLLATE pg_catalog."default",
	ESTADO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	DESCRIPCION_ESTADO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	ID_OPERACION character varying(100) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE staging.seq_boletas_en_buzon_tipo_tsx_pk OWNER TO postgres;

ALTER TABLE staging.boletas_en_buzon_tipo_tsx ALTER COLUMN ID_BOLETAS_TIPO_TSX SET DEFAULT nextval('staging.seq_boletas_en_buzon_tipo_tsx_pk');