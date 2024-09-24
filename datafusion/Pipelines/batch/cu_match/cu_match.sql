CREATE SEQUENCE staging.seq_cu_match_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE staging.cu_match
(
	id_staging_cu_match integer NOT NULL DEFAULT nextval('staging.seq_cu_match_pk'::regclass),
    rowid character varying(100) COLLATE pg_catalog."default",
    componente bigint,
    bandera_cu integer,
    cu_track character varying(100) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE staging.seq_cu_match_pk OWNER TO postgres;

ALTER TABLE staging.cu_match ALTER COLUMN id_staging_cu_match SET DEFAULT nextval('staging.seq_cu_match_pk');
