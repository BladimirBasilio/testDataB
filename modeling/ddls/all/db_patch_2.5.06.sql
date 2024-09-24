
-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 13
-- Project Site: CDP LIVERPOOL
-- DATAFIX PATCH#2.5.06 (18-06) Model V2.5 Author: Google ---
-- 		not cumulative.
-- 
--
-- System records
--
--
-- INCLUYE:
-- (1) RENAME de staging.productos_tmp, modifica el nombre de la columna "producto_submarcas1" por "id_producto_bigticket"
-- (2) RENAME de shared.productos, modifica "id_producto_submarcas1" por "id_producto_bigticket"
-- 
--
-- Current Patch v2.5.06 (18.06)
-- DELETE FROM shared.catalogos where CLAVE_HOMOLOGADA='PATCH_2.5.06.SQL' AND ENTIDAD_ORIGEN='DBA_REGISTRY_SQLPATCH';
 INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.06','PATCH_2.5.06.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);

--
-- Necesito que se modifiquen dos cosas:
-- 1 - en staging.productos_tmp, modificar el nombre de la columna "producto_submarcas1" por "id_producto_bigticket"
-- 2 - en shared.productos, modificar "id_producto_submarcas1" por "id_producto_bigticket"
--

ALTER TABLE staging.productos_tmp RENAME COLUMN producto_submarcas1 TO id_producto_bigticket;
ALTER TABLE shared.productos RENAME COLUMN id_producto_submarcas1 to id_producto_bigticket;

