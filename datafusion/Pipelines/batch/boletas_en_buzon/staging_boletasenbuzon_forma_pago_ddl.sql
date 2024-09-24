CREATE SEQUENCE staging.seq_boletas_en_buzon_forma_pago_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE staging.boletas_en_buzon_forma_pago
(
    ID_BOLETAS_FORMA_PAGO integer NOT NULL DEFAULT nextval('staging.seq_boletas_sku_pk'::regclass),
    ID_CLIENTE_CLIENTES character varying(100) COLLATE pg_catalog."default",
	HORA character varying(100) COLLATE pg_catalog."default",
	FECHA date,
	ID_TIENDA character varying(100) COLLATE pg_catalog."default",
	NUMERO_DE_TICKET character varying(100) COLLATE pg_catalog."default",
	ID_TERMINAL_POS character varying(100) COLLATE pg_catalog."default",
	NUMERO_BOLETA_BOLETAS_EN_BUZON character varying(100) COLLATE pg_catalog."default",
	NUMERO_DE_RENGLON character varying(100) COLLATE pg_catalog."default",
	ID_EMPRESA character varying(100) COLLATE pg_catalog."default",
	ID_TIPO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	ID_TARJETA character varying(100) COLLATE pg_catalog."default",
	LLAVE_SUBROGADA_TARJETA_ENMASCARADA character varying(100) COLLATE pg_catalog."default",
	ID_MEDIO_INGRESO_CLAVE_DE_TARJETA character varying(100) COLLATE pg_catalog."default",
	NUMERO_AUTORIZACION_BANCARIA character varying(100) COLLATE pg_catalog."default",
	ID_TIPO_DE_PAGO character varying(100) COLLATE pg_catalog."default",
	TOTAL_TRANSACCION numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO1 numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO2 numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO3 numeric(16,2) DEFAULT 0.00,
	TOTAL_ANTES_DE_IMPUESTO numeric(16,2) DEFAULT 0.00,
	COSTO_DE_MSI numeric(16,2) DEFAULT 0.00,
	MONTO_IMPUESTO4 numeric(16,2) DEFAULT 0.00,
	NUMERO_DE_CUENTA character varying(100) COLLATE pg_catalog."default",
	LLAVE_SUBROGADA_CUENTA_ENMASCARADA character varying(100) COLLATE pg_catalog."default",
	ID_PLAN  character varying(100) COLLATE pg_catalog."default",
	ID_VENDEDOR character varying(100) COLLATE pg_catalog."default",
	ID_BANCO character varying(100) COLLATE pg_catalog."default",
	ESTADO_TRANSACCION character varying(100) COLLATE pg_catalog."default",
	BANDERA_TRANSACCION_ES_DE_BUZON character varying(100) COLLATE pg_catalog."default",
	ID_NATURALEZA_DE_EMISION_DE_TARJETA character varying(100) COLLATE pg_catalog."default",
	ID_TIENDA_RECEPCION character varying(100) COLLATE pg_catalog."default",
	ID_CANAL character varying(100) COLLATE pg_catalog."default",
	ID_DISPOSITIVO_VENTA character varying(100) COLLATE pg_catalog."default",
	BANDERA_EJECUCION_TOKEN character varying(100) COLLATE pg_catalog."default",
	BOLETA_ORIGINAL_DE_BUZON character varying(100) COLLATE pg_catalog."default",
	FECHA_ORIGINAL_DE_BUZON character varying(100) COLLATE pg_catalog."default",
	HASH_TARJETA character varying(100) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE staging.seq_boletas_en_buzon_forma_pago_pk OWNER TO postgres;

ALTER TABLE staging.boletas_en_buzon_forma_pago ALTER COLUMN ID_BOLETAS_FORMA_PAGO SET DEFAULT nextval('staging.seq_boletas_en_buzon_forma_pago_pk');