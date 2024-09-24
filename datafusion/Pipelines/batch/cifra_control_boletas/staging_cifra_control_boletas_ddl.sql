CREATE SEQUENCE staging.seq_boletas_cifra_de_control_tmp_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE staging.boletas_cifra_de_control_tmp
(
    id_boletas_cifra_de_control integer NOT NULL DEFAULT nextval('boletas.seq_boletas_cifra_de_control_tmp_pk'::regclass),
    fecha_tlog date,
    centro character varying(100) COLLATE pg_catalog."default",
    id_tipo_de_transaccion integer,
    tipo_de_transaccion character varying(100) COLLATE pg_catalog."default",
    total_de_las_transacciones numeric(36,2) DEFAULT 0.00
)

TABLESPACE pg_default;

ALTER TABLE staging.seq_boletas_cifra_de_control_tmp_pk OWNER TO postgres;

ALTER TABLE staging.boletas_cifra_de_control_tmp ALTER COLUMN id_boletas_cifra_de_control SET DEFAULT nextval('staging.seq_boletas_cifra_de_control_tmp_pk');