CREATE SEQUENCE staging.seq_boletas_en_buzon_motivo_dev_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE staging.boletas_en_buzon_motivo_dev
(
    ID_BOLETAS_MOTIVO_DEV integer NOT NULL DEFAULT nextval('staging.seq_boletas_motivo_dev_pk'::regclass),
    CLAVE_DEVOLUCION character varying(100) COLLATE pg_catalog."default",
	MOTIVO_DEVOLUCION character varying(100) COLLATE pg_catalog."default",
	FECHA_INICIO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	FECHA_FIN_TRANSACCION character varying(100) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE staging.seq_boletas_en_buzon_motivo_dev_pk OWNER TO postgres;

ALTER TABLE staging.boletas_en_buzon_motivo_dev ALTER COLUMN ID_BOLETAS_MOTIVO_DEV SET DEFAULT nextval('staging.seq_boletas_en_buzon_motivo_dev_pk');