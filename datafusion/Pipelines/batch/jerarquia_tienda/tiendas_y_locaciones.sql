CREATE SEQUENCE staging.seq_tiendas_y_locaciones_tmp_pk
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


CREATE TABLE staging.tiendas_y_locaciones_tmp
(
    id_tiendas_y_locaciones_tmp integer NOT NULL DEFAULT nextval('staging.seq_tiendas_y_locaciones_tmp_pk'::regclass),
    clave_homologada character varying(100) COLLATE pg_catalog."default",
    descripcion_tienda_o_locacion character varying(100) COLLATE pg_catalog."default",
    id_tipo_tienda character varying(100) COLLATE pg_catalog."default",
    calle character varying(100) COLLATE pg_catalog."default",
    colonia character varying(100) COLLATE pg_catalog."default",
    ciudad character varying(100) COLLATE pg_catalog."default",
    municipio character varying(100) COLLATE pg_catalog."default",
    entidad_federativa character varying(100) COLLATE pg_catalog."default",
    telefono character varying(100) COLLATE pg_catalog."default",
    pisos character varying(100) COLLATE pg_catalog."default",
    codigo_postal character varying(100) COLLATE pg_catalog."default",
    latitud character varying(100) COLLATE pg_catalog."default",
    longitud character varying(100) COLLATE pg_catalog."default",
    punto_venta_metros_cuadrados integer,
    total_metros_cuadrados integer,
    cantidad_empleados integer,
    banner character varying(100) COLLATE pg_catalog."default",
    fecha_modificacion_banner date,
    nombre_direccion character varying(100) COLLATE pg_catalog."default",
    telefono1 character varying(100) COLLATE pg_catalog."default",
    renta_liverpool character varying(100) COLLATE pg_catalog."default",
    cantidad_entradas integer,
    cantidad_estacionamientos integer,
    cantidad_recepciones_cort integer,
    cantidad_trailers integer,
    cantidad_envios_cort integer,
    fecha_ultima_remodelacion date,
    fecha_inicio date,
    fecha_fin date,
    fecha_apertura date,
    fecha_cierre date,
    fecha_apertura_credito date,
    zona_locacion character varying(100) COLLATE pg_catalog."default",
    pais character varying(100) COLLATE pg_catalog."default",
    grupo_tienda character varying(100) COLLATE pg_catalog."default",
    fecha_carga date,
    CONSTRAINT pk_tiendas_y_locaciones_tmp PRIMARY KEY (id_tiendas_y_locaciones_tmp)
        WITH (FILLFACTOR=100)
);
