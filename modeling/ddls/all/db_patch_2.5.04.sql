
-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 13
-- Project Site: CDP LIVERPOOL
-- DATAFIX PATCH#2.5.04 (18-04) Model V2.5 Author: Google ---
-- 		not cumulative.
-- 
--
-- System records
--
--
-- INCLUYE:
--  (1) Cambios en BOLETA_DETALLES_SKU   amplia :
--         ES_MARCA_LIVERPOOL de VARCHAR(1) a VARCHAR(100)
-- 
--
-- Current Patch v2.5.04 (18.04)
-- DELETE FROM shared.catalogos where CLAVE_HOMOLOGADA='PATCH_2.5.03.SQL' AND ENTIDAD_ORIGEN='DBA_REGISTRY_SQLPATCH';
 INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.04','PATCH_2.5.04.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);
	
-- Issue column  ES_MARCA_LIVERPOOL   varchar(1) change dto varchar(100).
ALTER TABLE boletas.boleta_detalles_sku  ALTER COLUMN ES_MARCA_LIVERPOOL DROP DEFAULT;
ALTER TABLE boletas.boleta_detalles_sku  ALTER COLUMN ES_MARCA_LIVERPOOL TYPE varchar(100);
