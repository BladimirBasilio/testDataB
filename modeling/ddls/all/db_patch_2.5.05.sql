
-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 13
-- Project Site: CDP LIVERPOOL
-- DATAFIX PATCH#2.5.05 (18-05) Model V2.5 Author: Google ---
-- 		not cumulative.
-- 
--
-- System records
--
--
-- INCLUYE:
--  (1) Cambios en PEDIDOS :
--         Actualiza funciones de Pedidos respecto a Cliente_Destinatario.
-- 
--
-- Current Patch v2.5.05 (18.05)
-- DELETE FROM shared.catalogos where CLAVE_HOMOLOGADA='PATCH_2.5.05.SQL' AND ENTIDAD_ORIGEN='DBA_REGISTRY_SQLPATCH';
 INSERT INTO shared.catalogos (ID_CATALOGO, DESCRIPCION_CATALOGO, CLAVE_HOMOLOGADA, ENTIDAD_ORIGEN, ESQUEMA, ESTA_BORRADO,FLEXFIELD0,FLEXFIELD1)
 VALUES  (nextval('shared.seq_catalogos_pk'),'APPLIED PATCH 2.5.05','PATCH_2.5.05.SQL','DBA_REGISTRY_SQLPATCH','SYS','N','Para propositos de Administracion',localtimestamp);
	
	
--
-- Pedidos
--	

--Actualizacion funciones CDP V2.5

--DROP FUNCTION pedidos.pedidos_detalle_sku_crud(character varying, character varying, character varying, date, time without time zone, character varying, date, integer, integer, date, numeric, character varying, integer, character varying, time without time zone, date, character varying, numeric, date, numeric, numeric, numeric, integer, character varying, date);
DROP FUNCTION pedidos.pedidos_detalle_sku_crud;

CREATE OR REPLACE FUNCTION pedidos.pedidos_detalle_sku_crud(
	p_operacion_crud character varying,
	p_numero_del_documento_pedidos character varying,
	p_estado_orden_pedidos character varying,
	p_fecha_modificacion_pedidos date,
	p_hora_modificacion_pedidos time without time zone,
	p_proveedor_de_mensajeria character varying,
	p_fecha_real_de_entrega date,
	p_id_locacion_surte integer,
	p_id_producto_productos integer,
	p_fecha_actualizacion_estado date,
	p_piezas numeric,
	p_numero_de_guia character varying,
	p_id_linea_detalle integer,
	p_fecha_promesa_de_entrega_inicial date,
	p_hora_actualizacion_estado time without time zone,
	p_fecha_ultimo_intento date,
	p_indicador_marketplace character varying,
	p_cantidad_recogida_orden_de_venta numeric,
	p_fecha_de_surtido date,
	p_numero_de_intentos_de_entrega numeric,
	p_cantidad_cancelada numeric,
	p_cantidad_entregada numeric,
	p_id_causa_de_noentrega integer,
	p_usuario_actualizador_de_estado character varying,
	p_fecha_recalculada date,
	p_fecha_promesa_de_entrega_final date,
	p_id_estado_por_linea integer
	)
    RETURNS void
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM pedidos.pedidos_detalle_sku_ins (p_numero_del_documento_pedidos, p_estado_orden_pedidos, p_fecha_modificacion_pedidos,
				p_hora_modificacion_pedidos, p_proveedor_de_mensajeria, p_fecha_real_de_entrega,
				p_id_locacion_surte, p_id_producto_productos, p_fecha_actualizacion_estado,
				p_piezas, p_numero_de_guia, p_id_linea_detalle,
				p_fecha_promesa_de_entrega_inicial, p_hora_actualizacion_estado, p_fecha_ultimo_intento,
				p_indicador_marketplace,  p_cantidad_recogida_orden_de_venta, p_fecha_de_surtido,
				p_numero_de_intentos_de_entrega, p_cantidad_cancelada, p_cantidad_entregada,
				p_id_causa_de_noentrega, p_usuario_actualizador_de_estado, p_fecha_recalculada,
				p_fecha_promesa_de_entrega_final, p_id_estado_por_linea
				);
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM pedidos.pedidos_detalle_sku_upd (p_numero_del_documento_pedidos, p_estado_orden_pedidos, p_fecha_modificacion_pedidos,
				p_hora_modificacion_pedidos, p_proveedor_de_mensajeria, p_fecha_real_de_entrega,
				p_id_locacion_surte, p_id_producto_productos, p_fecha_actualizacion_estado,
				p_piezas, p_numero_de_guia, p_id_linea_detalle,
				p_fecha_promesa_de_entrega_inicial, p_hora_actualizacion_estado, p_fecha_ultimo_intento,
				p_indicador_marketplace,  p_cantidad_recogida_orden_de_venta, p_fecha_de_surtido,
				p_numero_de_intentos_de_entrega, p_cantidad_cancelada, p_cantidad_entregada,
				p_id_causa_de_noentrega, p_usuario_actualizador_de_estado, p_fecha_recalculada,
				p_fecha_promesa_de_entrega_final, p_id_estado_por_linea
				);
		END IF;

END
$BODY$;






--DROP FUNCTION pedidos.pedidos_detalle_sku_ins(character varying, character varying, date, time without time zone, character varying, date, integer, integer, date, numeric, character varying, integer, character varying, time without time zone, date, character varying, numeric, date, numeric, numeric, numeric, integer, character varying, date);
DROP FUNCTION pedidos.pedidos_detalle_sku_ins;

CREATE OR REPLACE FUNCTION pedidos.pedidos_detalle_sku_ins(
	p_numero_del_documento_pedidos character varying,
	p_estado_orden_pedidos character varying,
	p_fecha_modificacion_pedidos date,
	p_hora_modificacion_pedidos time without time zone,
	p_proveedor_de_mensajeria character varying,
	p_fecha_real_de_entrega date,
	p_id_locacion_surte integer,
	p_id_producto_productos integer,
	p_fecha_actualizacion_estado date,
	p_piezas numeric,
	p_numero_de_guia character varying,
	p_id_linea_detalle integer,
	p_fecha_promesa_de_entrega_inicial date,
	p_hora_actualizacion_estado time without time zone,
	p_fecha_ultimo_intento date,
	p_indicador_marketplace character varying,
	p_cantidad_recogida_orden_de_venta numeric,
	p_fecha_de_surtido date,
	p_numero_de_intentos_de_entrega numeric,
	p_cantidad_cancelada numeric,
	p_cantidad_entregada numeric,
	p_id_causa_de_noentrega integer,
	p_usuario_actualizador_de_estado character varying,
	p_fecha_recalculada date,
	p_fecha_promesa_de_entrega_final date,
	p_id_estado_por_linea integer)
    RETURNS character varying
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
INSERT INTO pedidos.pedidos_detalle_sku  (
			numero_del_documento_pedidos, estado_orden_pedidos, fecha_modificacion_pedidos, 
			hora_modificacion_pedidos, proveedor_de_mensajeria, fecha_real_de_entrega, 
			id_locacion_surte, id_producto_productos, fecha_actualizacion_estado, 
			piezas, numero_de_guia, id_linea_detalle, 
			fecha_promesa_de_entrega_inicial, hora_actualizacion_estado, fecha_ultimo_intento, 
			indicador_marketplace, cantidad_recogida_orden_de_venta, fecha_de_surtido, 
			numero_de_intentos_de_entrega, cantidad_cancelada, cantidad_entregada, 
			id_causa_de_noentrega, usuario_actualizador_de_estado, fecha_recalculada,
			fecha_promesa_de_entrega_final, id_estado_por_linea
		) 
		 VALUES (p_numero_del_documento_pedidos, p_estado_orden_pedidos, p_fecha_modificacion_pedidos,
				p_hora_modificacion_pedidos, p_proveedor_de_mensajeria, p_fecha_real_de_entrega,
				p_id_locacion_surte, p_id_producto_productos, p_fecha_actualizacion_estado,
				p_piezas, p_numero_de_guia, p_id_linea_detalle,
				p_fecha_promesa_de_entrega_inicial, p_hora_actualizacion_estado, p_fecha_ultimo_intento,
				p_indicador_marketplace,  p_cantidad_recogida_orden_de_venta, p_fecha_de_surtido,
				p_numero_de_intentos_de_entrega, p_cantidad_cancelada, p_cantidad_entregada,
				p_id_causa_de_noentrega, p_usuario_actualizador_de_estado, p_fecha_recalculada, 
				p_fecha_promesa_de_entrega_final, p_id_estado_por_linea)
			RETURNING p_numero_del_documento_pedidos;
$BODY$;





--DROP FUNCTION pedidos.pedidos_detalle_sku_upd(character varying, character varying, date, time without time zone, character varying, date, integer, integer, date, numeric, character varying, integer, character varying, time without time zone, date, character varying, numeric, date, numeric, numeric, numeric, integer, character varying, date);
DROP FUNCTION pedidos.pedidos_detalle_sku_upd;

CREATE OR REPLACE FUNCTION pedidos.pedidos_detalle_sku_upd(
	p_numero_del_documento_pedidos character varying,
	p_estado_orden_pedidos character varying,
	p_fecha_modificacion_pedidos date,
	p_hora_modificacion_pedidos time without time zone,
	p_proveedor_de_mensajeria character varying,
	p_fecha_real_de_entrega date,
	p_id_locacion_surte integer,
	p_id_producto_productos integer,
	p_fecha_actualizacion_estado date,
	p_piezas numeric,
	p_numero_de_guia character varying,
	p_id_linea_detalle integer,
	p_fecha_promesa_de_entrega_inicial date,
	p_hora_actualizacion_estado time without time zone,
	p_fecha_ultimo_intento date,
	p_indicador_marketplace character varying,
	p_cantidad_recogida_orden_de_venta numeric,
	p_fecha_de_surtido date,
	p_numero_de_intentos_de_entrega numeric,
	p_cantidad_cancelada numeric,
	p_cantidad_entregada numeric,
	p_id_causa_de_noentrega integer,
	p_usuario_actualizador_de_estado character varying,
	p_fecha_recalculada date,
	p_fecha_promesa_de_entrega_final date,
	p_id_estado_por_linea integer)
    RETURNS character varying
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
UPDATE pedidos.pedidos_detalle_sku
			SET 
				proveedor_de_mensajeria = p_proveedor_de_mensajeria, 
				fecha_real_de_entrega = p_fecha_real_de_entrega, 
				id_locacion_surte = p_id_locacion_surte, 
				id_producto_productos = p_id_producto_productos, 
				fecha_actualizacion_estado = p_fecha_actualizacion_estado, 
				piezas = p_piezas, 
				numero_de_guia = p_numero_de_guia, 
				fecha_promesa_de_entrega_inicial = p_fecha_promesa_de_entrega_inicial, 
				hora_actualizacion_estado = p_hora_actualizacion_estado, 
				fecha_ultimo_intento = p_fecha_ultimo_intento, 
				indicador_marketplace = p_indicador_marketplace, 
				cantidad_recogida_orden_de_venta = p_cantidad_recogida_orden_de_venta, 
				fecha_de_surtido = p_fecha_de_surtido, 
				numero_de_intentos_de_entrega = p_numero_de_intentos_de_entrega, 
				cantidad_cancelada = p_cantidad_cancelada, 
				cantidad_entregada = p_cantidad_entregada, 
				id_causa_de_noentrega = p_id_causa_de_noentrega, 
				usuario_actualizador_de_estado = p_usuario_actualizador_de_estado, 
				fecha_recalculada = p_fecha_recalculada,
				fecha_promesa_de_entrega_final = p_fecha_promesa_de_entrega_final,
				id_estado_por_linea = p_id_estado_por_linea
			WHERE numero_del_documento_pedidos = p_numero_del_documento_pedidos AND
					estado_orden_pedidos = p_estado_orden_pedidos AND
					fecha_modificacion_pedidos = p_fecha_modificacion_pedidos AND 
					hora_modificacion_pedidos = p_hora_modificacion_pedidos AND 
					id_linea_detalle = p_id_linea_detalle
	RETURNING numero_del_documento_pedidos;
$BODY$;


--Pedidos

--DROP FUNCTION pedidos.pedidos_crud(character varying, character varying, character varying, date, time without time zone, character varying, date, time without time zone, character varying, smallint, smallint, integer, integer, integer, smallint, smallint, smallint, character varying, integer, smallint, date, integer, date);
DROP FUNCTION pedidos.pedidos_crud;

CREATE OR REPLACE FUNCTION pedidos.pedidos_crud(
	p_operacion_crud character varying,
	p_numero_del_documento character varying,
	p_estado_orden character varying,
	p_fecha_modificacion date,
	p_hora_modificacion time without time zone,
	p_estado_renglon_bitacora character varying,
	p_fecha_emision date,
	p_hora_emision time without time zone,
	p_orden_original character varying,
	p_id_tipo_entrega smallint,
	p_id_tipo_de_venta smallint,
	p_id_cliente_remitente integer,
	p_id_direcciones_destinatario integer,
	p_id_tipo_de_act smallint,
	p_id_tipo_de_documento smallint,
	p_id_periodicidad smallint,
	p_observaciones character varying,
	p_id_tienda_y_locacion_destino integer,
	p_id_frecuencia_visita smallint,
	p_fecha_de_compra date,
	p_id_estado_del_documento integer)
    RETURNS SETOF character varying 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			RETURN QUERY SELECT pedidos.pedidos_ins (p_numero_del_documento, p_estado_orden, p_fecha_modificacion, p_hora_modificacion, 
				 p_estado_renglon_bitacora, p_fecha_emision, p_hora_emision, p_orden_original, 
				 p_id_tipo_entrega, p_id_tipo_de_venta, p_id_cliente_remitente, 
				 p_id_direcciones_destinatario, p_id_tipo_de_act, p_id_tipo_de_documento, p_id_periodicidad, 
				 p_observaciones, p_id_tienda_y_locacion_destino, p_id_frecuencia_visita, p_fecha_de_compra, 
				 p_id_estado_del_documento);
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			RETURN QUERY SELECT pedidos.pedidos_upd (p_numero_del_documento, p_estado_orden, p_fecha_modificacion, p_hora_modificacion, 
				 p_estado_renglon_bitacora, p_fecha_emision, p_hora_emision, p_orden_original, 
				 p_id_tipo_entrega, p_id_tipo_de_venta, p_id_cliente_remitente,
				 p_id_direcciones_destinatario, p_id_tipo_de_act, p_id_tipo_de_documento, p_id_periodicidad, 
				 p_observaciones, p_id_tienda_y_locacion_destino, p_id_frecuencia_visita, p_fecha_de_compra, 
				 p_id_estado_del_documento); 
		END IF;

END
$BODY$;


--DROP FUNCTION pedidos.pedidos_ins(character varying, character varying, date, time without time zone, character varying, date, time without time zone, character varying, smallint, smallint, integer, integer, integer, smallint, smallint, smallint, character varying, integer, smallint, date, integer, date);
DROP FUNCTION pedidos.pedidos_ins;

CREATE OR REPLACE FUNCTION pedidos.pedidos_ins(
	p_numero_del_documento character varying,
	p_estado_orden character varying,
	p_fecha_modificacion date,
	p_hora_modificacion time without time zone,
	p_estado_renglon_bitacora character varying,
	p_fecha_emision date,
	p_hora_emision time without time zone,
	p_orden_original character varying,
	p_id_tipo_entrega smallint,
	p_id_tipo_de_venta smallint,
	p_id_cliente_remitente integer,
	p_id_direcciones_destinatario integer,
	p_id_tipo_de_act smallint,
	p_id_tipo_de_documento smallint,
	p_id_periodicidad smallint,
	p_observaciones character varying,
	p_id_tienda_y_locacion_destino integer,
	p_id_frecuencia_visita smallint,
	p_fecha_de_compra date,
	p_id_estado_del_documento integer)
    RETURNS character varying
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
INSERT INTO pedidos.pedidos 
		(numero_del_documento, estado_orden, fecha_modificacion, hora_modificacion, 
		 estado_renglon_bitacora, fecha_emision, hora_emision, orden_original, 
		 id_tipo_entrega, id_tipo_de_venta, id_cliente_remitente, 
		 id_direcciones_destinatario, id_tipo_de_act, id_tipo_de_documento, id_periodicidad, 
		 observaciones, id_tienda_y_locacion_destino, id_frecuencia_visita, fecha_de_compra, 
		 id_estado_del_documento) 
		 VALUES (p_numero_del_documento, p_estado_orden, p_fecha_modificacion, p_hora_modificacion, 
				 p_estado_renglon_bitacora, p_fecha_emision, p_hora_emision, p_orden_original, 
				 p_id_tipo_entrega, p_id_tipo_de_venta, p_id_cliente_remitente, 
				 p_id_direcciones_destinatario, p_id_tipo_de_act, p_id_tipo_de_documento, p_id_periodicidad, 
				 p_observaciones, p_id_tienda_y_locacion_destino, p_id_frecuencia_visita, p_fecha_de_compra, 
				 p_id_estado_del_documento)
			RETURNING numero_del_documento;
$BODY$;




--DROP FUNCTION pedidos.pedidos_upd(character varying, character varying, date, time without time zone, character varying, date, time without time zone, character varying, smallint, smallint, integer, integer, integer, smallint, smallint, smallint, character varying, integer, smallint, date, integer, date);
DROP FUNCTION pedidos.pedidos_upd;

CREATE OR REPLACE FUNCTION pedidos.pedidos_upd(
	p_numero_del_documento character varying,
	p_estado_orden character varying,
	p_fecha_modificacion date,
	p_hora_modificacion time without time zone,
	p_estado_renglon_bitacora character varying,
	p_fecha_emision date,
	p_hora_emision time without time zone,
	p_orden_original character varying,
	p_id_tipo_entrega smallint,
	p_id_tipo_de_venta smallint,
	p_id_cliente_remitente integer,
	p_id_direcciones_destinatario integer,
	p_id_tipo_de_act smallint,
	p_id_tipo_de_documento smallint,
	p_id_periodicidad smallint,
	p_observaciones character varying,
	p_id_tienda_y_locacion_destino integer,
	p_id_frecuencia_visita smallint,
	p_fecha_de_compra date,
	p_id_estado_del_documento integer)
    RETURNS character varying
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
UPDATE pedidos.pedidos  
			SET 
				fecha_emision = p_fecha_emision, 
				hora_emision = p_hora_emision, 
				orden_original = p_orden_original, 
				id_tipo_entrega = p_id_tipo_entrega, 
				id_tipo_de_venta = p_id_tipo_de_venta, 
				id_tipo_de_act = p_id_tipo_de_act, 
				id_tipo_de_documento = p_id_tipo_de_documento, 
				id_periodicidad = p_id_periodicidad, 
				observaciones = p_observaciones, 
				id_tienda_y_locacion_destino = p_id_tienda_y_locacion_destino, 
				id_frecuencia_visita = p_id_frecuencia_visita, 
				fecha_de_compra = p_fecha_de_compra, 
				id_estado_del_documento = p_id_estado_del_documento
			WHERE numero_del_documento = p_numero_del_documento AND 
					  estado_orden =  p_estado_orden AND 
					  fecha_modificacion = p_fecha_modificacion AND 
					  hora_modificacion = p_hora_modificacion AND
					  estado_renglon_bitacora = p_estado_renglon_bitacora AND
					  id_cliente_remitente = p_id_cliente_remitente AND
					  id_direcciones_destinatario= p_id_direcciones_destinatario
	RETURNING numero_del_documento;
$BODY$;



--cliente direcciones

--DROP FUNCTION clientes.cliente_direcciones_crud(character varying, integer, integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying);
DROP FUNCTION clientes.cliente_direcciones_crud;

CREATE OR REPLACE FUNCTION clientes.cliente_direcciones_crud(
	p_operacion_crud character varying,
	p_id_direccion_del_cliente integer,
	p_id_cliente integer,
	p_calle character varying,
	p_numero_exterior character varying,
	p_numero_interior character varying,
	p_codigo_postal character varying,
	p_id_colonia integer,
	p_id_municipio integer,
	p_id_estado_del_pais integer,
	p_entre_calle_1 character varying,
	p_entre_calle_2 character varying,
	p_id_cliente_destinatario integer)
    RETURNS SETOF integer 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			RETURN QUERY SELECT clientes.cliente_direcciones_ins  (p_id_cliente, 
						p_calle, p_numero_exterior, p_numero_interior, 
						p_codigo_postal, p_id_colonia, p_id_municipio, 
						p_id_estado_del_pais, p_entre_calle_1, p_entre_calle_2,
						p_id_cliente_destinatario);
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			RETURN QUERY SELECT clientes.cliente_direcciones_upd  (
						p_id_direccion_del_cliente, p_id_cliente, 
						p_calle, p_numero_exterior, p_numero_interior, 
						p_codigo_postal, p_id_colonia, p_id_municipio, 
						p_id_estado_del_pais, p_entre_calle_1, p_entre_calle_2,
						p_id_cliente_destinatario);
		END IF;

END
$BODY$;



--DROP FUNCTION clientes.cliente_direcciones_ins(integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying);
DROP FUNCTION clientes.cliente_direcciones_ins;

CREATE OR REPLACE FUNCTION clientes.cliente_direcciones_ins(
	p_id_cliente integer,
	p_calle character varying,
	p_numero_exterior character varying,
	p_numero_interior character varying,
	p_codigo_postal character varying,
	p_id_colonia integer,
	p_id_municipio integer,
	p_id_estado_del_pais integer,
	p_entre_calle_1 character varying,
	p_entre_calle_2 character varying,
	p_id_cliente_destinatario integer)
    RETURNS integer
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
INSERT INTO clientes.cliente_direcciones  (
						id_direccion_del_cliente, fecha_creacion, id_cliente, 
						calle, numero_exterior, numero_interior, 
						codigo_postal, id_colonia, id_municipio, 
						id_estado_del_pais, entre_calle_1, entre_calle_2,
						id_cliente_destinatario)  
	VALUES (nextval('clientes.seq_cliente_direcciones_pk'), current_date, p_id_cliente, 
						p_calle, p_numero_exterior, p_numero_interior, 
						p_codigo_postal, p_id_colonia, p_id_municipio, 
						p_id_estado_del_pais, p_entre_calle_1, p_entre_calle_2,
						p_id_cliente_destinatario)
	RETURNING id_direccion_del_cliente;
$BODY$;



--DROP FUNCTION clientes.cliente_direcciones_upd(integer, integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying);
DROP FUNCTION clientes.cliente_direcciones_upd;

CREATE OR REPLACE FUNCTION clientes.cliente_direcciones_upd(
	p_id_direccion_del_cliente integer,
	p_id_cliente integer,
	p_calle character varying,
	p_numero_exterior character varying,
	p_numero_interior character varying,
	p_codigo_postal character varying,
	p_id_colonia integer,
	p_id_municipio integer,
	p_id_estado_del_pais integer,
	p_entre_calle_1 character varying,
	p_entre_calle_2 character varying,
	p_id_cliente_destinatario integer)
    RETURNS integer
    LANGUAGE 'sql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
UPDATE clientes.cliente_direcciones  
			SET 
				fecha_actualizacion = current_date, 
				id_cliente = p_id_cliente,
				calle = p_calle, 
				numero_exterior = p_numero_exterior,  
				numero_interior = p_numero_interior, 
				codigo_postal = p_codigo_postal, 
				id_colonia = p_id_colonia, 
				id_municipio = p_id_municipio, 
				id_estado_del_pais = p_id_estado_del_pais, 
				entre_calle_1 = p_entre_calle_1, 
				entre_calle_2 = p_entre_calle_2,
				id_cliente_destinatario = p_id_cliente_destinatario
			WHERE id_direccion_del_cliente = p_id_direccion_del_cliente
	RETURNING id_direccion_del_cliente;
$BODY$;
