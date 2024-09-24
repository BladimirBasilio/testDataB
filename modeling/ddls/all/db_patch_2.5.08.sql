-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 13
-- Project Site: CDP LIVERPOOL
-- DATAFIX PATCH#2.5.08 (18-08) Model V2.5 Author: Google ---
-- 		not cumulative.
--
--
-- INCLUYE:
--  (1) Renombra columnas de 
--         PRODUCTO_TALLAS.CLAVE_DE_TALLA A Clave_homologada,
--     Y   PRODUCTO_APLICA_SERVICIOS.CLAVE_NOSPOT A Clave_homologada;
--

INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.08','PATCH_2.5.08.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);

---
--- FIX ISSUE#10 
---
ALTER TABLE shared.PRODUCTO_TALLAS RENAME COLUMN CLAVE_DE_TALLA to clave_homologada;
ALTER TABLE shared.PRODUCTO_APLICA_SERVICIOS RENAME COLUMN CLAVE_NOSPOT to clave_homologada;


