CREATE SEQUENCE staging.seq_boletas_en_buzon_sku_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE staging.boletas_en_buzon_sku
(
    ID_BOLETAS_SKU integer NOT NULL DEFAULT nextval('staging.seq_boletas_en_buzon_sku_pk'::regclass),
    TICKET character varying(100) COLLATE pg_catalog."default",
	ID_TIENDA character varying(100) COLLATE pg_catalog."default",
	FECHA_TRANSACCION date,
	ID_TERMINAL_POS character varying(100) COLLATE pg_catalog."default",
	NUMERO_BOLETA character varying(100) COLLATE pg_catalog."default",
	NUMERO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	ID_EMPRESA character varying(100) COLLATE pg_catalog."default",
	ID_VENDEDOR character varying(100) COLLATE pg_catalog."default",
	ID_CLIENTE character varying(100) COLLATE pg_catalog."default",
	ID_TIPO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	ESTADO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	NUMERO_CUENTA character varying(100) COLLATE pg_catalog."default",
	NUMERO_CUENTA_SUBROGADA character varying(100) COLLATE pg_catalog."default",
	TOTAL_BOLETA numeric(16,2) DEFAULT 0.00,
	TOTAL_REBAJAS_SKU_BOLETA numeric(16,2) DEFAULT 0.00,
	TOTAL_DESCUENTO_BOLETA numeric(16,2) DEFAULT 0.00,
	ID_TIPO_DESCUENTO character varying(100) COLLATE pg_catalog."default",
	HORA_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	NUMERO_EVENTO character varying(100) COLLATE pg_catalog."default",
	NUMERO_ENTREGA_SOMS character varying(100) COLLATE pg_catalog."default",
	NUMERO_TELEFONO character varying(100) COLLATE pg_catalog."default",
	NUMERO_ORDEN_OPTICA character varying(100) COLLATE pg_catalog."default",
	PROMOCION_CRM character varying(100) COLLATE pg_catalog."default",
	ID_CLIENTE_PROMO_CRM character varying(100) COLLATE pg_catalog."default",
	ID_CLIENTE_KYBELA character varying(100) COLLATE pg_catalog."default",
	PROMO_ITUNES character varying(100) COLLATE pg_catalog."default",
	CERTIFICADO_ITUNES character varying(100) COLLATE pg_catalog."default",
	CANTIDAD_ARTICULOS integer,
	TOTAL_PAGADO_REAL numeric(16,2) DEFAULT 0.00,
	CLAVE_HOMOLOGADA character varying(100) COLLATE pg_catalog."default",
	MONTO_REBAJA numeric(16,2) DEFAULT 0.00,
	MONTO_DESCUENTO numeric(16,2) DEFAULT 0.00,
	TOTAL_POR_SKU numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO1 numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO2 numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO3 numeric(16,2) DEFAULT 0.00,
	TOTAL_ANTES_DE_IMPUESTO numeric(16,2) DEFAULT 0.00,
	BANDERA_ID_TRX_DE_MERCANCIA character varying(100) COLLATE pg_catalog."default",
	BANDERA_ID_TRX_CON_MONEDERO character varying(100) COLLATE pg_catalog."default",
	BANDERA_ID_TRX_CON_MESA_DE_REGALO character varying(100) COLLATE pg_catalog."default",
	BANDERA_ID_TRX_DE_BUZON character varying(100) COLLATE pg_catalog."default",
	ID_TIENDA_ORIGINAL character varying(100) COLLATE pg_catalog."default",
	FECHA_ORIGINAL_COMPRA date,
	ID_TERMINAL_POS_2 character varying(100) COLLATE pg_catalog."default",
	NUMERO_BOLETA_ORIGINAL character varying(100) COLLATE pg_catalog."default",
	ID_VENDEDOR_2 character varying(100) COLLATE pg_catalog."default",
	PRECIO_ACTUAL_SKU numeric(16,2) DEFAULT 0.00,
	BANDERA_ID_TRX_DE_INVITADO character varying(100) COLLATE pg_catalog."default",
	ID_MOTIVO_DEVOLUCION character varying(100) COLLATE pg_catalog."default",
	ID_TIENDA_RECEPCION character varying(100) COLLATE pg_catalog."default",
	ID_CANAL character varying(100) COLLATE pg_catalog."default",
	ID_DISPOSITIVO_VENTA character varying(100) COLLATE pg_catalog."default",
	ID_CLIENTE_ORIGINAL character varying(100) COLLATE pg_catalog."default",
	BANDERA_EJECUCION_TOKEN character varying(100) COLLATE pg_catalog."default",
	BANDERA_ORIGINAL_BUZON character varying(100) COLLATE pg_catalog."default",
	FECHA_ORIGINAL_BUZON character varying(100) COLLATE pg_catalog."default",
	HASH_TARJETA character varying(100) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE staging.seq_boletas_en_buzon_sku_pk OWNER TO postgres;

ALTER TABLE staging.boletas_en_buzon_sku ALTER COLUMN ID_BOLETAS_SKU SET DEFAULT nextval('staging.seq_boletas_en_buzon_sku_pk');