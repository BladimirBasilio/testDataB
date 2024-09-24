CREATE SEQUENCE staging.seq_clientes_atg_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE staging.clientes_atg
(
    id_clientes_atg integer NOT NULL DEFAULT nextval('staging.seq_clientes_atg_pk'::regclass),
    sistema_fuente_atg character varying(100) COLLATE pg_catalog."default",
    id_atg character varying(100) COLLATE pg_catalog."default",
    primer_nombre character varying(100) COLLATE pg_catalog."default",
    segundo_nombre character varying(100) COLLATE pg_catalog."default",
    apellido_paterno character varying(100) COLLATE pg_catalog."default",
    apellido_materno character varying(100) COLLATE pg_catalog."default",
    genero character varying(100) COLLATE pg_catalog."default",
    fecha_de_nacimiento character varying(100) COLLATE pg_catalog."default",
    rfc character varying(100) COLLATE pg_catalog."default",
    fecha_de_registro character varying(100) COLLATE pg_catalog."default",
    email_tipo_cuentas character varying(100) COLLATE pg_catalog."default",
    email_tipo_notificaciones character varying(100) COLLATE pg_catalog."default",
    telefono_alias_telefono_casa character varying(100) COLLATE pg_catalog."default",
    telefono_alias_telefono_celular character varying(100) COLLATE pg_catalog."default",
    telefono_alias_telefono_trabajo character varying(100) COLLATE pg_catalog."default",
    telefono_alias_telefono_adicional character varying(100) COLLATE pg_catalog."default",
    id_tipo_direccion_cliente character varying(100) COLLATE pg_catalog."default",
    calle character varying(100) COLLATE pg_catalog."default",
    numero_exterior character varying(100) COLLATE pg_catalog."default",
    numero_interior character varying(100) COLLATE pg_catalog."default",
    edificio character varying(100) COLLATE pg_catalog."default",
    id_estado_del_pais character varying(100) COLLATE pg_catalog."default",
    id_municipio character varying(100) COLLATE pg_catalog."default",
    id_colonia character varying(100) COLLATE pg_catalog."default",
    codigo_postal character varying(100) COLLATE pg_catalog."default",
    alias_de_direccion character varying(100) COLLATE pg_catalog."default",
    hash_1 character varying(100) COLLATE pg_catalog."default",
    id_tipo_tarjeta character varying(100) COLLATE pg_catalog."default",
    realm_id character varying(100) COLLATE pg_catalog."default",
    id_facebook character varying(100) COLLATE pg_catalog."default",
    id_apple character varying(100) COLLATE pg_catalog."default",
    id_sistema_operativo_dispositivo character varying(100) COLLATE pg_catalog."default",
    baja_de_tarjeta character varying(100) COLLATE pg_catalog."default",
    alta_de_tarjeta character varying(100) COLLATE pg_catalog."default",
    baja_domicilio character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT pk_clientes_atg PRIMARY KEY (id_clientes_atg)
)

TABLESPACE pg_default;


ALTER TABLE staging.seq_clientes_atg_pk OWNER TO postgres;

ALTER TABLE staging.clientes_atg ALTER COLUMN ID_CLIENTES_ATG SET DEFAULT nextval('staging.seq_clientes_atg_pk');
