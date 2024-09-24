
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
--  (1) Cambios en BOLETA_DETALLES_SKU   agrega :
--         FECHA_PROMESA_DE_ENTREGA_INICIAL
-- 
--
-- Current Patch v2.5.02 (18.02)
-- DELETE FROM shared.catalogos where CLAVE_HOMOLOGADA='PATCH_2.5.03.SQL' AND ENTIDAD_ORIGEN='DBA_REGISTRY_SQLPATCH';
 INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.03','PATCH_2.5.03.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);


/**
 * FK que hace referencia a la tabla de PRODUCTO SECCIONES para el id departamento
 */
--ALTER TABLE boletas.boleta_detalles_sku
--	ADD CONSTRAINT boleta_detalles_sku_id_departamento_fk FOREIGN KEY (ID_DEPARTAMENTO) 
--	REFERENCES SHARED.PRODUCTO_SECCIONES  
--	(ID_EMPRESA_SKU) 
--	MATCH FULL ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;


/**
 * Se agrega columna para guardar el campo es marca liverpool en la tabla de boleta_detalles_sku
 */
ALTER TABLE boletas.boleta_detalles_sku 
    ADD  COLUMN FECHA_PROMESA_DE_ENTREGA_INICIAL date;
COMMENT ON COLUMN BOLETAS.BOLETA_DETALLES_SKU.FECHA_PROMESA_DE_ENTREGA_INICIAL IS 'Fecha de entrega estimada';



