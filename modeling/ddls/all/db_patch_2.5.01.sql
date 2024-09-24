
-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 13
-- Project Site: CDP LIVERPOOL
-- DATAFIX PATCH#2.5.01 (18-01) Model V2.5 Author: Google ---
-- 		not cumulative.
-- 
--
-- System records
--
-- Current DF v2.5.01 (18.01)
-- DELETE FROM shared.catalogos where CLAVE_HOMOLOGADA='PATCH_2.5.01.SQL' AND ENTIDAD_ORIGEN='DBA_REGISTRY_SQLPATCH';
 INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.01','PATCH_2.5.01.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);

--
-- FIX 01
--

ALTER TABLE BOLETAS.BOLETA_CABECERAS
 ADD COLUMN TOTAL_SEGMENTO numeric(16,2) DEFAULT 0.00;
 
ALTER TABLE BOLETAS.BOLETA_CABECERAS
 ADD COLUMN TIPO_MEDIO_DE_PAGO varchar(100);
 
ALTER TABLE BOLETAS.BOLETA_CABECERAS
 ADD COLUMN ID_VENDEDOR_ORIGINAL integer;

-- object: BOLETA_CABECERAS | type: CONSTRAINT --
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD CONSTRAINT BOLETA_CABECERAS_id_vendedororig_fk FOREIGN KEY (ID_VENDEDOR_ORIGINAL)
REFERENCES SHARED.CATALOGOS (ID_CATALOGO) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE NOT DEFERRABLE;



--
-- FIX#02 Duplicated rows in CLIENTE_IDS
--       
--
DELETE FROM CLIENTES.CLIENTE_IDS WHERE ID_SISTEMA_ORIGEN IS NULL;
DELETE FROM CLIENTES.CLIENTE_IDS WHERE ID_ORIGEN IS NULL;
DELETE FROM CLIENTES.CLIENTE_IDS WHERE ID_ORIGEN || ID_SISTEMA_ORIGEN IN (SELECT  id_origen || id_sistema_origen FROM CLIENTES.CLIENTE_IDS GROUP BY id_origen || id_sistema_origen HAVING COUNT(id_origen || id_sistema_origen) > 1);
ALTER TABLE CLIENTES.CLIENTE_IDS ALTER COLUMN ID_ORIGEN SET NOT NULL;
ALTER TABLE CLIENTES.CLIENTE_IDS ALTER COLUMN ID_SISTEMA_ORIGEN SET NOT NULL;
ALTER TABLE CLIENTES.CLIENTE_IDS ADD CONSTRAINT CLIENTE_IDS_NODUP_ROWS UNIQUE (ID_ORIGEN,ID_SISTEMA_ORIGEN);

---
--- FIX#03 Ingesta de Boletas y sus Catalogos
--- REF.:Rafa
---
CREATE OR REPLACE FUNCTION shared.selert_catalogos(p_clave_homologada varchar(255), p_entidad VARCHAR(255))
  RETURNS TABLE (id_catalogo_r int
  				, descripcion_catalogo_r varchar(250)
  				, clave_homologada_r varchar(100)
  				, entidad_origen_r varchar(200)
  				, esquema_r varchar(100)) as
$func$
begin
	
	
	-- query catalogos 
  if exists (select id_catalogo as resultado
				from
					shared.catalogos c
				where
					c.clave_homologada = p_clave_homologada
						and c.entidad_origen = p_entidad) then  
			return query select id_catalogo as id_catalogo_r
			, descripcion_catalogo as descripcion_catalogo_r
			, clave_homologada as clave_homologada_r
			, entidad_origen as entidad_origen_r
			, esquema as esquema_r
 				from
					shared.catalogos
				where
					clave_homologada = p_clave_homologada
						and entidad_origen = p_entidad;
	else 
		insert into shared.catalogos(id_catalogo, descripcion_catalogo, clave_homologada, entidad_origen, esquema)
		values (nextval('shared.seq_catalogos_pk'), p_clave_homologada,p_clave_homologada, p_entidad,null );
	
		return query select id_catalogo as id_catalogo_r
			, descripcion_catalogo as descripcion_catalogo_r
			, clave_homologada as clave_homologada_r
			, entidad_origen as entidad_origen_r
			, esquema as esquema_r
 				from
					shared.catalogos
				where
					clave_homologada = p_clave_homologada
						and entidad_origen = p_entidad;
		
 	end if;
 
END;
$func$  LANGUAGE plpgsql;

--
-- Validation
--
--select * from shared.catalogos c where entidad_origen = 'CANALES_DE_VENTA'
--select * from shared.selert_catalogos('1','CANALES_DE_VENTA')

--
-- FIX#04 missed columns in BOLETAS sub data model for Ingesta de Boletas/Parse of JSON
--       
-- 
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD COLUMN numero_paqueteria varchar(100);
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD COLUMN leyenda_facturacion varchar(500);
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD COLUMN codigo_de_barras varchar(100);
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD COLUMN fecha_nacimiento_garantia_extendida date;
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD COLUMN fecha_garantia_extemporanea date;
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD COLUMN numero_indicador_marketplace varchar(100);
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD COLUMN leyenda_tentativa varchar(500);
ALTER TABLE BOLETAS.BOLETA_CABECERAS  ADD COLUMN clave_homologada varchar(50);
ALTER TABLE BOLETAS.BOLETA_CABECERAS  ADD COLUMN id_direccion_fiscal  integer; --fk CATALOGOS
--
ALTER TABLE SHARED.TIENDAS_Y_LOCACIONES ADD COLUMN direccion varchar(500);
--
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN hash_tarjeta_externa varchar(300);
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN numero_monedero varchar(50);
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN monto_redimido_monedero numeric(16,2);
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN saldo_anterior_monedero numeric(16,2);
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN monto_obtenido_monedero numeric(16,2);
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN saldo_actual_monedero numeric(16,2);
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN numero_de_cupon varchar(50);
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS  ADD COLUMN id_modo_ingreso integer; --fk CATALOGOS
--

-- object: BOLETA_CABECERAS_ID_DIRECCION_FISCAL_fk | type: CONSTRAINT --
ALTER TABLE BOLETAS.BOLETA_CABECERAS ADD CONSTRAINT BOLETA_CABECERAS_ID_DIRECCION_FISCAL_fk FOREIGN KEY 
(id_direccion_fiscal)
 REFERENCES SHARED.CATALOGOS  
	(ID_CATALOGO) 
	MATCH FULL ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --

-- object: BOLETA_CABECERAS_ID_MODO_INGRESO_fk | type: CONSTRAINT --
ALTER TABLE BOLETAS.BOLETA_DETALLE_PAGOS ADD CONSTRAINT BOLETA_DETALLE_PAGOS_ID_MODO_INGRESO_fk FOREIGN KEY 
(id_modo_ingreso)
 REFERENCES SHARED.CATALOGOS  
	(ID_CATALOGO) 
	MATCH FULL ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --
---------------------------------


/*********************
	Creación de tabla boleta_detalle_abonos_segmento
*/
CREATE TABLE boletas.boleta_detalle_abonos_segmento (
	id_linea_detalle_abonos_segmento integer NOT NULL,
	id_terminal_pos varchar(25) NOT NULL,
	numero_boleta varchar(25) NOT NULL,
	fecha_fin_transaccion date NOT NULL,
	hora_fin_transaccion time NOT NULL,
	id_tienda_origen varchar(25) NOT NULL,
	id_tienda_reconocimiento varchar(25) NOT NULL,
	id_segmento integer NOT NULL,
	importe_abonado_al_segmento numeric(16,2),
	CONSTRAINT pk_boleta_detalle_abonos_segmento PRIMARY KEY (id_linea_detalle_abonos_segmento, id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, id_segmento)
);

CREATE UNIQUE INDEX pk_boleta_detalle_abonos_segmento_idx ON boletas.boleta_detalle_abonos_segmento USING btree (id_linea_detalle_abonos_segmento, id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, id_segmento);
COMMENT ON INDEX boletas.pk_boleta_detalle_abonos_segmento_idx IS 'Index de la PK de la tabla boleta_detalle_abonos_segmento';

ALTER TABLE boletas.boleta_detalle_abonos_segmento ADD CONSTRAINT boleta_detalles_abono_sgmto_cabecera_fk FOREIGN KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) ON DELETE RESTRICT ON UPDATE CASCADE;

/*********************
	Creación de tabla boleta_detalles_monedero
*/
CREATE TABLE boletas.boleta_detalles_monedero (
	id_terminal_pos varchar(25) NOT NULL,
	numero_boleta varchar(25) NOT NULL,
	fecha_fin_transaccion date NOT NULL,
	hora_fin_transaccion time NOT NULL,
	id_tienda_origen varchar(25) NOT NULL,
	id_tienda_reconocimiento varchar(25) NOT NULL,
	numero_monedero varchar(50) NOT NULL,
	saldo_anterior_monedero numeric(16,2) NULL,
	monto_monedero numeric(16,2) NULL,
	monto_obtenido_monedero numeric(16,2) NULL,
	saldo_actual_monedero numeric(16,2) NULL,
	CONSTRAINT pk_boleta_detalles_monedero PRIMARY KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, numero_monedero)
);

CREATE UNIQUE INDEX pk_boleta_detalles_monedero_idx ON boletas.boleta_detalles_monedero USING btree (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, numero_monedero);
COMMENT ON INDEX boletas.pk_boleta_detalles_monedero_idx IS 'Index de la PK de la tabla boleta_detalles_monedero';

ALTER TABLE boletas.boleta_detalles_monedero ADD CONSTRAINT boleta_detalles_monedero_cabecera_fk FOREIGN KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) ON DELETE RESTRICT ON UPDATE CASCADE;

/*********************
	Creación de tabla boleta_detalle_tarjetas_prepago
*/
CREATE TABLE boletas.boleta_detalle_tarjetas_prepago (
	id_terminal_pos varchar(25) NOT NULL,
	numero_boleta varchar(25) NOT NULL,
	fecha_fin_transaccion date NOT NULL,
	hora_fin_transaccion time NOT NULL,
	id_tienda_origen varchar(25) NOT NULL,
	id_tienda_reconocimiento varchar(25) NOT NULL,
	codigo_upc varchar(50) NOT NULL,
	importe_tarjeta_prepago numeric(16,2) NULL,
	CONSTRAINT pk_boleta_detalle_tarjetas_prepago PRIMARY KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, codigo_upc)
);

CREATE UNIQUE INDEX pk_boleta_detalle_tarjetas_prepago_idx ON boletas.boleta_detalle_tarjetas_prepago USING btree (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, codigo_upc);
COMMENT ON INDEX boletas.pk_boleta_detalle_tarjetas_prepago_idx IS 'Index de la PK de la tabla boleta_detalle_tarjetas_prepago';

ALTER TABLE boletas.boleta_detalle_tarjetas_prepago ADD CONSTRAINT boleta_detalle_tarjetas_prepago_cabecera_fk FOREIGN KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) ON DELETE RESTRICT ON UPDATE CASCADE;

/*********************
	Creación de tabla boleta_detalle_cupones
*/
CREATE TABLE boletas.boleta_detalle_cupones (
	id_terminal_pos varchar(25) NOT NULL,
	numero_boleta varchar(25) NOT NULL,
	fecha_fin_transaccion date NOT NULL,
	hora_fin_transaccion time NOT NULL,
	id_tienda_origen varchar(25) NOT NULL,
	id_tienda_reconocimiento varchar(25) NOT NULL,
	codigo_cupon varchar(50) NOT NULL,
	es_redimido varchar(1) NULL DEFAULT 'S',
	CONSTRAINT pk_boleta_detalle_cupones PRIMARY KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, codigo_cupon)
);

CREATE UNIQUE INDEX pk_boleta_detalle_cupones_idx ON boletas.boleta_detalle_cupones USING btree (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento, codigo_cupon);
COMMENT ON INDEX boletas.pk_boleta_detalle_cupones_idx IS 'Index de la PK de la tabla boleta_detalle_cupones';

ALTER TABLE boletas.boleta_detalle_cupones ADD CONSTRAINT boleta_detalle_cupones_cabecera_fk FOREIGN KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) ON DELETE RESTRICT ON UPDATE CASCADE;



 




















