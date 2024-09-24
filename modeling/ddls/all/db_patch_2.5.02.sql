
-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 13
-- Project Site: CDP LIVERPOOL
-- DATAFIX PATCH#2.5.02 (18-02) Model V2.5 Author: Google ---
-- 		not cumulative.
-- 
--
-- System records
--
--
-- INCLUYE:
--  (1) Cambios en BOLETA_DETALLES_SKU aumenta largo de columna ID_PEP_R3, agrega :
--         ID_MOTIVO_DEVOLUCION, ID_DEPARTAMENTO, PRECIO_UNITARIO_DEL_SKU, FISICO_O_VIRTUAL, y ES_MARCA_LIVERPOOL.
--  (2) Cambios en BOLETA_CABECERAS agrega ID_TIENDA_ORIGEN_GCP y ID_TERMINAL_POS_GCP para guardar convertidas
--      columnas de PK al modelo Google.
--
--
-- Current Patch v2.5.02 (18.02)
-- DELETE FROM shared.catalogos where CLAVE_HOMOLOGADA='PATCH_2.5.02.SQL' AND ENTIDAD_ORIGEN='DBA_REGISTRY_SQLPATCH';
 INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.02','PATCH_2.5.02.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);

--
-- 7-abr-2021
--

ALTER TABLE boletas.boleta_detalles_sku
    ALTER COLUMN id_pep_r3 type varchar(500) ;


/**
 * Se agrega columna para guardar el id de la tienda origen de acuerdo a la tabla de TIENDAS Y LOCACIONES
 */
ALTER TABLE boletas.boleta_cabeceras
    ADD COLUMN ID_TIENDA_ORIGEN_GCP integer;
COMMENT ON COLUMN BOLETAS.BOLETA_CABECERAS.ID_TIENDA_ORIGEN_GCP IS 'para guardar el id de la tienda origen de acuerdo a la tabla de TIENDAS Y LOCACIONES';

/**
 * FK que hace referencia a la tabla de TIENDAS Y LOCACIONES
 */
ALTER TABLE boletas.boleta_cabeceras
	ADD CONSTRAINT boleta_cabeceras_id_tienda_origen_gcp_fk FOREIGN KEY (ID_TIENDA_ORIGEN_GCP) 
	REFERENCES SHARED.TIENDAS_Y_LOCACIONES  
	(ID_TIENDA_O_LOCACION) 
	MATCH FULL ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;

/**
 * Se agrega columna para guardar el id terminal pos de acuerdo a la tabla de CATALOGOS
 */
ALTER TABLE boletas.boleta_cabeceras
    ADD  COLUMN ID_TERMINAL_POS_GCP integer;  
COMMENT ON COLUMN BOLETAS.BOLETA_CABECERAS.ID_TERMINAL_POS_GCP IS 'para guardar el id terminal pos de acuerdo a la tabla de CATALOGOS';

/**
 * FK que hace referencia a la tabla de CATALOGOS
 */
ALTER TABLE boletas.boleta_cabeceras
	ADD CONSTRAINT boleta_cabeceras_id_terminal_pos_gcp_fk FOREIGN KEY (ID_TERMINAL_POS_GCP) 
	REFERENCES SHARED.CATALOGOS  
	(ID_CATALOGO) 
	MATCH FULL ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;

/**
 * Se agrega columna para guardar el id motivo devolucion en la tabla de boleta_detalles_sku
 */
ALTER TABLE boletas.boleta_detalles_sku 
    ADD  COLUMN ID_MOTIVO_DEVOLUCION integer; 
COMMENT ON COLUMN BOLETAS.BOLETA_DETALLES_SKU.ID_MOTIVO_DEVOLUCION IS 'para guardar el id motivo devolucion en la tabla de boleta_detalles_sku';

/**
 * FK que hace referencia a la tabla de CATALOGOS
 */
ALTER TABLE boletas.boleta_detalles_sku
	ADD CONSTRAINT boleta_detalles_sku_id_motivo_dev_fk FOREIGN KEY (ID_MOTIVO_DEVOLUCION) 
	REFERENCES SHARED.CATALOGOS  
	(ID_CATALOGO) 
	MATCH FULL ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;

/**
 * Se agrega columna para guardar el id departamento n en la tabla de boleta_detalles_sku
 */
ALTER TABLE boletas.boleta_detalles_sku 
    ADD  COLUMN ID_DEPARTAMENTO integer;
COMMENT ON COLUMN BOLETAS.BOLETA_DETALLES_SKU.ID_DEPARTAMENTO IS 'para guardar la seccion';


/**
 * Se agrega columna para guardar el precio unitario del sku en la tabla de boleta_detalles_sku
 */
ALTER TABLE boletas.boleta_detalles_sku 
    ADD  COLUMN PRECIO_UNITARIO_DEL_SKU NUMERIC(16,2);
COMMENT ON COLUMN BOLETAS.BOLETA_DETALLES_SKU.PRECIO_UNITARIO_DEL_SKU IS 'precio unitario del producto';

/**
 * Se agrega columna para guardar el campo que indica si es un artículo físico o virtual en la tabla de boleta_detalles_sku
 */
ALTER TABLE boletas.boleta_detalles_sku 
    ADD  COLUMN FISICO_O_VIRTUAL VARCHAR(1);
COMMENT ON COLUMN BOLETAS.BOLETA_DETALLES_SKU.FISICO_O_VIRTUAL IS 'indica si es un artículo físico o virtual ';

/**
 * Se agrega columna para guardar el campo es marca liverpool en la tabla de boleta_detalles_sku
 */
ALTER TABLE boletas.boleta_detalles_sku 
    ADD  COLUMN ES_MARCA_LIVERPOOL varchar(1) DEFAULT 'N';
COMMENT ON COLUMN BOLETAS.BOLETA_DETALLES_SKU.ES_MARCA_LIVERPOOL IS 'Marca si es un producto con marca  LIVERPOOL  ';





