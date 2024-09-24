-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 13
-- Project Site: CDP LIVERPOOL
-- DATAFIX PATCH#2.5.07 (18-06) Model V2.5 Author: Google ---
-- 		not cumulative.
INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.07','PATCH_2.5.07.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);

ALTER TABLE shared.producto_grupos DROP COLUMN clave_homologada;
ALTER TABLE shared.producto_grupos RENAME COLUMN id_grupo_del_articulo to clave_homologada;