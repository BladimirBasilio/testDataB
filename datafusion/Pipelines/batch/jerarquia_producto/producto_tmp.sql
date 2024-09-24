CREATE SEQUENCE staging.seq_catalogos_producto_tmp_pk
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE staging.catalogos_producto_tmp
(
    id_catalogos_producto_tmp integer NOT NULL DEFAULT nextval('staging.seq_catalogos_producto_tmp_pk'::regclass),
    clave_homologada character varying(25) COLLATE pg_catalog."default",
    descripcion character varying(50) COLLATE pg_catalog."default",
    descripcion_2 character varying(50) COLLATE pg_catalog."default",
    fecha_actualizacion date,
    empresa_sku character varying(25) COLLATE pg_catalog."default",
    catalogo_padre character varying(25) COLLATE pg_catalog."default",
    estado integer NOT NULL DEFAULT 0,
    CONSTRAINT pk_id_catalogos_producto_tmp PRIMARY KEY (id_catalogos_producto_tmp)
        WITH (FILLFACTOR=100)
);

CREATE SEQUENCE staging.seq_producto_grupos_tmp_pk
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE staging.producto_grupos_tmp
(
    id_producto_grupos_tmp integer NOT NULL DEFAULT nextval('staging.seq_producto_grupos_tmp_pk'::regclass),
    grupo_del_articulo character varying(50) COLLATE pg_catalog."default",
    descripcion_del_grupo character varying(50) COLLATE pg_catalog."default",
    empresa_sku character varying(50) COLLATE pg_catalog."default",
    producto_secciones_sku character varying(50) COLLATE pg_catalog."default",
    clase_sku character varying(50) COLLATE pg_catalog."default",
    mundo_negocios character varying(50) COLLATE pg_catalog."default",
    comprador_corporativo character varying(50) COLLATE pg_catalog."default",
    fecha_actualizacion date,
    estado integer NOT NULL DEFAULT 0,
    CONSTRAINT pk_id_producto_grupos_tmp PRIMARY KEY (id_producto_grupos_tmp)
        WITH (FILLFACTOR=100)
);


CREATE SEQUENCE staging.seq_productos_tmp_pk
    INCREMENT 1
    START 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


CREATE TABLE staging.productos_tmp
(
    id_productos_tmp integer NOT NULL DEFAULT nextval('staging.seq_productos_tmp_pk'::regclass),
    codigo_de_barras character varying(50) COLLATE pg_catalog."default",
    id_generico character varying(15) COLLATE pg_catalog."default",
    descripcion character varying(50) COLLATE pg_catalog."default",
    costo_neto numeric(16,2) DEFAULT 0.00,
    fecha_modificacion date,
    id_importacion character varying(25) COLLATE pg_catalog."default",
    producto_nospot character varying(100) COLLATE pg_catalog."default",
    descripcion_sector character varying(100) COLLATE pg_catalog."default",
    precio_venta_sugerido numeric(16,2) DEFAULT 0.00,
    id_lic character varying(25) COLLATE pg_catalog."default",
    id_coordinado character varying(25) COLLATE pg_catalog."default",
    producto_grupos character varying(100) COLLATE pg_catalog."default",
    proveedor character varying(100) COLLATE pg_catalog."default",
    precio_venta numeric(16,2) DEFAULT 0.00,
    precio_sugerido numeric(16,2) DEFAULT 0.00,
    empresa_sku character varying(100) COLLATE pg_catalog."default",
    pais_sku character varying(100) COLLATE pg_catalog."default",
    estado_sku character varying(100) COLLATE pg_catalog."default",
    fecha_inicial_registrado date,
    producto_tallas character varying(100) COLLATE pg_catalog."default",
    producto_submarcas character varying(100) COLLATE pg_catalog."default",
    rango_precios_sku character varying(100) COLLATE pg_catalog."default",
    texto_adicional character varying(50) COLLATE pg_catalog."default",
    descripcion_larga character varying(100) COLLATE pg_catalog."default",
    clave_homologada character varying(100) COLLATE pg_catalog."default",
    tipo_de_negocio_sku character varying(100) COLLATE pg_catalog."default",
    producto_colores character varying(100) COLLATE pg_catalog."default",
    temporada_sku character varying(100) COLLATE pg_catalog."default",
    tipo_mercado_sku character varying(100) COLLATE pg_catalog."default",
    costo numeric(16,2) DEFAULT 0.00,
    tipo_producto character varying(15) COLLATE pg_catalog."default",
    fecha_actualizacion date,
    variante_coordinado character varying(25) COLLATE pg_catalog."default",
    categoria character varying(100) COLLATE pg_catalog."default",
    producto_submarcas1 character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT pk_productos_tmp PRIMARY KEY (id_productos_tmp)
        WITH (FILLFACTOR=10)
);