CREATE TABLE staging.cliente_mdm
(
    id_cliente_mdm integer NOT NULL DEFAULT nextval('clientes.seq_clientes_pk'::regclass),
    row_id character varying(25) COLLATE pg_catalog."default",
    id_origen character varying(100) COLLATE pg_catalog."default",
    primer_nombre character varying(100) COLLATE pg_catalog."default",
    apellido_paterno character varying(100) COLLATE pg_catalog."default",
    apellido_materno character varying(100) COLLATE pg_catalog."default",
    fecha_de_nacimiento date,
    genero character varying(25) COLLATE pg_catalog."default",
    rfc character varying(25) COLLATE pg_catalog."default",
    bloque integer,
    homonimo character varying(100) COLLATE pg_catalog."default",
    fecha_creacion date,
    fecha_actualizacion date,
    CONSTRAINT pk_cliente_mdm PRIMARY KEY (id_cliente_mdm)
        WITH (FILLFACTOR=100)
);


CREATE SEQUENCE staging.seq_referencia_mdm_pk
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE staging.referencia_mdm
(
    id_referencia_mdm integer NOT NULL DEFAULT nextval('staging.seq_referencia_mdm_pk'::regclass),
    row_id character varying(25) COLLATE pg_catalog."default",
    id_origen character varying(100) COLLATE pg_catalog."default",
    referencia character varying(100) COLLATE pg_catalog."default",
    fuente character varying(100) COLLATE pg_catalog."default",
    fecha_de_carga date,
    fecha_actualizacion date,
    CONSTRAINT pk_referencia_mdm PRIMARY KEY (id_referencia_mdm)
        WITH (FILLFACTOR=100)
);


CREATE SEQUENCE staging.seq_contacto_mdm_pk
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE staging.contacto_mdm
(
    id_contacto_mdm integer NOT NULL DEFAULT nextval('staging.seq_contacto_mdm_pk'::regclass),
    row_id character varying(25) COLLATE pg_catalog."default",
    medio_contacto character varying(100) COLLATE pg_catalog."default",
    tipo character varying(100) COLLATE pg_catalog."default",
    fuente character varying(100) COLLATE pg_catalog."default",
    tipo_contacto character varying(100) COLLATE pg_catalog."default",
    fecha_creacion date,
    fecha_actualizacion date,
    id_origen_externo character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT pk_contacto_mdm PRIMARY KEY (id_contacto_mdm)
        WITH (FILLFACTOR=100)
);


CREATE SEQUENCE staging.seq_direccion_mdm_pk
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


CREATE TABLE staging.direccion_mdm
(
    id_direccion_mdm integer NOT NULL DEFAULT nextval('staging.seq_direccion_mdm_pk'::regclass),
    row_id character varying(25) COLLATE pg_catalog."default",
    calle character varying(100) COLLATE pg_catalog."default",
    numero_exterior character varying(100) COLLATE pg_catalog."default",
    numero_interior character varying(100) COLLATE pg_catalog."default",
    edificio character varying(100) COLLATE pg_catalog."default",
    id_estado character varying(100) COLLATE pg_catalog."default",
    estado character varying(100) COLLATE pg_catalog."default",
    id_municipio character varying(100) COLLATE pg_catalog."default",
    municipio character varying(100) COLLATE pg_catalog."default",
    id_colonia character varying(100) COLLATE pg_catalog."default",
    colonia character varying(100) COLLATE pg_catalog."default",
    codigo_postal character varying(100) COLLATE pg_catalog."default",
    entre_calle1 character varying(100) COLLATE pg_catalog."default",
    entre_calle2 character varying(100) COLLATE pg_catalog."default",
    pais character varying(100) COLLATE pg_catalog."default",
    id_sistema_origen character varying(100) COLLATE pg_catalog."default",
    fecha_creacion date,
    fecha_actualizacion date,
    CONSTRAINT pk_direccion_mdm PRIMARY KEY (id_direccion_mdm)
        WITH (FILLFACTOR=100)
);
