--DROP FUNCTION clientes.cliente_ids_crud;
--DROP FUNCTION clientes.cliente_ids_upd;
--DROP FUNCTION clientes.cliente_ids_ins;
--DROP FUNCTION pedidos.pedidos_detalle_sku_crud;
--DROP FUNCTION pedidos.pedidos_detalle_sku_upd;
--DROP FUNCTION pedidos.pedidos_detalle_sku_ins;
--DROP FUNCTION pedidos.pedidos_crud;
--DROP FUNCTION pedidos.pedidos_upd;
--DROP FUNCTION pedidos.pedidos_ins;
--DROP FUNCTION clientes.cliente_direcciones_crud;
--DROP FUNCTION clientes.cliente_direcciones_upd;
--DROP FUNCTION clientes.cliente_direcciones_ins;
--DROP FUNCTION clientes.cliente_telefonos_de_contacto_crud;
--DROP FUNCTION clientes.cliente_telefonos_de_contacto_upd;
--DROP FUNCTION clientes.cliente_telefonos_de_contacto_ins;
--DROP FUNCTION clientes.cliente_emails_de_contacto_crud;
--DROP FUNCTION clientes.cliente_emails_de_contacto_upd;
--DROP FUNCTION clientes.cliente_emails_de_contacto_ins;
--DROP FUNCTION clientes.clientes_crud;
--DROP FUNCTION clientes.clientes_upd;
--DROP FUNCTION clientes.clientes_ins;

--Pedidos

CREATE OR REPLACE FUNCTION clientes.clientes_ins
(
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_clave_homologada varchar(25)
)
RETURNS integer AS
$func$
	INSERT INTO clientes.clientes  (id_cliente, 
									fecha_creacion, 
									primer_nombre, 
									segundo_nombre, 
									apellido_paterno, 
									apellido_materno,
									clave_homologada) 
	VALUES (nextval('clientes.seq_clientes_pk'), 
			current_date, 
			p_primer_nombre, 
			p_segundo_nombre, 
			p_apellido_paterno, 
			p_apellido_materno,
			p_clave_homologada)
	RETURNING id_cliente;
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.clientes_upd
(
	p_id_cliente integer,
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_clave_homologada varchar(25)
)
RETURNS integer AS
$func$
	UPDATE clientes.clientes  
			SET 
				fecha_actualizacion = current_date, 
				primer_nombre = p_primer_nombre, 
				segundo_nombre = p_segundo_nombre, 
				apellido_paterno = p_apellido_paterno, 
				apellido_materno = p_apellido_materno,
				clave_homologada = p_clave_homologada
			WHERE id_cliente = p_id_cliente
	RETURNING id_cliente;
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION clientes.clientes_crud (
	p_operacion_crud varchar(30),
	p_id_cliente integer,
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_clave_homologada varchar(25)
) RETURNS SETOF integer AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			RETURN QUERY SELECT clientes.clientes_ins (p_primer_nombre, 
													   p_segundo_nombre, 
													   p_apellido_paterno, 
													   p_apellido_materno,
													   p_clave_homologada); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			RETURN QUERY SELECT clientes.clientes_upd (p_id_cliente,
													   p_primer_nombre, 
													   p_segundo_nombre, 
													   p_apellido_paterno, 
													   p_apellido_materno,
													   p_clave_homologada);  
		END IF;

END$$ LANGUAGE plpgsql;


--emails de contacto



CREATE OR REPLACE FUNCTION clientes.cliente_emails_de_contacto_ins
(
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_email varchar(100)
)
RETURNS integer AS
$func$
	INSERT INTO clientes.cliente_emails_de_contacto (id_cliente_emails_de_contacto, 
													 fecha_creacion, 
													 id_cliente, 
													 id_sistema_origen, 
													 email,
													 tipo,
													 esta_borrado)
	VALUES (nextval('clientes.seq_cliente_emails_de_contacto_pk'),
			current_date, 
			p_id_cliente, 
			p_id_sistema_origen, 
			p_email,
			'PRINCIPAL',
			'N')
	RETURNING id_cliente_emails_de_contacto;
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.cliente_emails_de_contacto_upd
(
	p_id_cliente_emails_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_email varchar(100)
)
RETURNS integer AS
$func$
	UPDATE clientes.cliente_emails_de_contacto  
			SET 
				fecha_actualizacion = current_date, 
				id_cliente = p_id_cliente,
				id_sistema_origen = p_id_sistema_origen,
				email = p_email
			WHERE id_cliente_emails_de_contacto = p_id_cliente_emails_de_contacto
	RETURNING id_cliente_emails_de_contacto;
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION clientes.cliente_emails_de_contacto_crud (
	p_operacion_crud varchar(30),
	p_id_cliente_emails_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_email varchar(100)
) RETURNS void AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.cliente_emails_de_contacto_ins (p_id_cliente, 
															 p_id_sistema_origen, 
															 p_email); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.cliente_emails_de_contacto_upd (p_id_cliente_emails_de_contacto, 
															 p_id_cliente, 
															 p_id_sistema_origen, 
															 p_email); 
		END IF;

END$$ LANGUAGE plpgsql;




-- Telefonos de contacto


CREATE OR REPLACE FUNCTION clientes.cliente_telefonos_de_contacto_ins
(
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_telefono varchar(16),
	p_alias_telefono varchar(16)
)
RETURNS integer AS
$func$
	INSERT INTO clientes.cliente_telefonos_de_contacto (id_cliente_telefonos_de_contacto, 
													 fecha_creacion, 
													 id_cliente, 
													 id_sistema_origen, 
													 telefono,
													 alias_telefono) 
	VALUES (nextval('clientes.seq_cliente_telefonos_de_contacto_pk'), 
			current_date, 
			p_id_cliente, 
			p_id_sistema_origen, 
			p_telefono,
		    p_alias_telefono)
	RETURNING id_cliente_telefonos_de_contacto;
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.cliente_telefonos_de_contacto_upd
(
	p_id_cliente_telefonos_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_telefono varchar(16),
	p_alias_telefono varchar(16)
)
RETURNS integer AS
$func$
	UPDATE clientes.cliente_telefonos_de_contacto  
			SET 
				fecha_actualizacion = current_date, 
				id_cliente = p_id_cliente,
				id_sistema_origen = p_id_sistema_origen,
				telefono = p_telefono,
				alias_telefono = p_alias_telefono
			WHERE id_cliente_telefonos_de_contacto = p_id_cliente_telefonos_de_contacto
	RETURNING id_cliente_telefonos_de_contacto;
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION clientes.cliente_telefonos_de_contacto_crud (
	p_operacion_crud varchar(30),
	p_id_cliente_telefonos_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_telefono varchar(16),
	p_alias_telefono varchar(16)
) RETURNS void AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.cliente_telefonos_de_contacto_ins (p_id_cliente, 
															 p_id_sistema_origen, 
															 p_telefono,
															 p_alias_telefono); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.cliente_telefonos_de_contacto_upd (p_id_cliente_telefonos_de_contacto, 
															 p_id_cliente, 
															 p_id_sistema_origen, 
															 p_telefono,
															 p_alias_telefono); 
		END IF;

END$$ LANGUAGE plpgsql;



--cliente_direcciones


CREATE OR REPLACE FUNCTION clientes.cliente_direcciones_ins
( 
	p_id_cliente integer, 
	p_calle varchar(100),
	p_numero_exterior varchar(15),
	p_numero_interior varchar(15),
	p_codigo_postal varchar(8),
	p_id_colonia integer,
	p_id_municipio integer,
	p_id_estado_del_pais integer,
	p_entre_calle_1 varchar(100),
	p_entre_calle_2 varchar(100)
)
RETURNS integer AS
$func$
	INSERT INTO clientes.cliente_direcciones  (
						id_direccion_del_cliente, fecha_creacion, id_cliente, 
						calle, numero_exterior, numero_interior, 
						codigo_postal, id_colonia, id_municipio, 
						id_estado_del_pais, entre_calle_1, entre_calle_2)  
	VALUES (nextval('clientes.seq_cliente_direcciones_pk'), current_date, p_id_cliente, 
						p_calle, p_numero_exterior, p_numero_interior, 
						p_codigo_postal, p_id_colonia, p_id_municipio, 
						p_id_estado_del_pais, p_entre_calle_1, p_entre_calle_2)
	RETURNING id_direccion_del_cliente;
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.cliente_direcciones_upd
(
	p_id_direccion_del_cliente integer, 
	p_id_cliente integer, 
	p_calle varchar(100),
	p_numero_exterior varchar(15),
	p_numero_interior varchar(15),
	p_codigo_postal varchar(8),
	p_id_colonia integer,
	p_id_municipio integer,
	p_id_estado_del_pais integer,
	p_entre_calle_1 varchar(100),
	p_entre_calle_2 varchar(100)
)
RETURNS integer AS
$func$
					
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
				entre_calle_2 = p_entre_calle_2
			WHERE id_direccion_del_cliente = p_id_direccion_del_cliente
	RETURNING id_direccion_del_cliente;
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION clientes.cliente_direcciones_crud (
	p_operacion_crud varchar(30),
	p_id_direccion_del_cliente integer, 
	p_id_cliente integer, 
	p_calle varchar(100),
	p_numero_exterior varchar(15),
	p_numero_interior varchar(15),
	p_codigo_postal varchar(8),
	p_id_colonia integer,
	p_id_municipio integer,
	p_id_estado_del_pais integer,
	p_entre_calle_1 varchar(100),
	p_entre_calle_2 varchar(100)
) RETURNS SETOF integer AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			RETURN QUERY SELECT clientes.cliente_direcciones_ins  (p_id_cliente, 
						p_calle, p_numero_exterior, p_numero_interior, 
						p_codigo_postal, p_id_colonia, p_id_municipio, 
						p_id_estado_del_pais, p_entre_calle_1, p_entre_calle_2);
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			RETURN QUERY SELECT clientes.cliente_direcciones_upd  (
						p_id_direccion_del_cliente, p_id_cliente, 
						p_calle, p_numero_exterior, p_numero_interior, 
						p_codigo_postal, p_id_colonia, p_id_municipio, 
						p_id_estado_del_pais, p_entre_calle_1, p_entre_calle_2);
		END IF;

END$$ LANGUAGE plpgsql;



--pedidos

CREATE OR REPLACE FUNCTION pedidos.pedidos_ins
(
		p_numero_del_documento varchar(10), 
		p_estado_orden varchar(2), 
		p_fecha_modificacion date, 
		p_hora_modificacion time, 
		p_estado_renglon_bitacora varchar(5), 
		p_fecha_emision date,
		p_hora_emision time, 
		p_orden_original varchar(50), 
		p_id_tipo_entrega smallint, 
		p_id_tipo_de_venta smallint, 
		p_id_cliente_remitente integer, 
		p_id_cliente_destinatario integer, 
		p_id_direcciones_destinatario integer, 
		p_id_tipo_de_act smallint, 
		p_id_tipo_de_documento smallint, 
	    p_id_periodicidad smallint, 
		p_observaciones varchar(200),
		p_id_tienda_y_locacion_destino integer, 
		p_id_frecuencia_visita smallint,
	    p_fecha_de_compra date, 
		p_id_estado_del_documento integer, 
		p_fecha_de_emision date
)
RETURNS varchar(10) AS
$func$
		INSERT INTO pedidos.pedidos 
		(numero_del_documento, estado_orden, fecha_modificacion, hora_modificacion, 
		 estado_renglon_bitacora, fecha_emision, hora_emision, orden_original, 
		 id_tipo_entrega, id_tipo_de_venta, id_cliente_remitente, id_cliente_destinatario, 
		 id_direcciones_destinatario, id_tipo_de_act, id_tipo_de_documento, id_periodicidad, 
		 observaciones, id_tienda_y_locacion_destino, id_frecuencia_visita, fecha_de_compra, 
		 id_estado_del_documento, fecha_de_emision) 
		 VALUES (p_numero_del_documento, p_estado_orden, p_fecha_modificacion, p_hora_modificacion, 
				 p_estado_renglon_bitacora, p_fecha_emision, p_hora_emision, p_orden_original, 
				 p_id_tipo_entrega, p_id_tipo_de_venta, p_id_cliente_remitente, p_id_cliente_destinatario, 
				 p_id_direcciones_destinatario, p_id_tipo_de_act, p_id_tipo_de_documento, p_id_periodicidad, 
				 p_observaciones, p_id_tienda_y_locacion_destino, p_id_frecuencia_visita, p_fecha_de_compra, 
				 p_id_estado_del_documento, p_fecha_de_emision)
			RETURNING numero_del_documento;
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION pedidos.pedidos_upd
(
		p_numero_del_documento varchar(10), 
		p_estado_orden varchar(2), 
		p_fecha_modificacion date, 
		p_hora_modificacion time, 
		p_estado_renglon_bitacora varchar(5), 
		p_fecha_emision date,
		p_hora_emision time, 
		p_orden_original varchar(50), 
		p_id_tipo_entrega smallint, 
		p_id_tipo_de_venta smallint, 
		p_id_cliente_remitente integer, 
		p_id_cliente_destinatario integer, 
		p_id_direcciones_destinatario integer, 
		p_id_tipo_de_act smallint, 
		p_id_tipo_de_documento smallint, 
	    p_id_periodicidad smallint, 
		p_observaciones varchar(200),
		p_id_tienda_y_locacion_destino integer, 
		p_id_frecuencia_visita smallint,
	    p_fecha_de_compra date, 
		p_id_estado_del_documento integer, 
		p_fecha_de_emision date
)
RETURNS varchar(10) AS
$func$
	UPDATE pedidos.pedidos  
			SET 
				fecha_emision = p_fecha_emision, 
				hora_emision = p_hora_emision, 
				orden_original = p_orden_original, 
				id_tipo_entrega = p_id_tipo_entrega, 
				id_tipo_de_venta = p_id_tipo_de_venta, 
				id_cliente_destinatario = p_id_cliente_destinatario, 
				id_tipo_de_act = p_id_tipo_de_act, 
				id_tipo_de_documento = p_id_tipo_de_documento, 
				id_periodicidad = p_id_periodicidad, 
				observaciones = p_observaciones, 
				id_tienda_y_locacion_destino = p_id_tienda_y_locacion_destino, 
				id_frecuencia_visita = p_id_frecuencia_visita, 
				fecha_de_compra = p_fecha_de_compra, 
				id_estado_del_documento = p_id_estado_del_documento, 
				fecha_de_emision =  p_fecha_de_emision
			WHERE numero_del_documento = p_numero_del_documento AND 
					  estado_orden =  p_estado_orden AND 
					  fecha_modificacion = p_fecha_modificacion AND 
					  hora_modificacion = p_hora_modificacion AND
					  estado_renglon_bitacora = p_estado_renglon_bitacora AND
					  id_cliente_remitente = p_id_cliente_remitente AND
					  id_direcciones_destinatario= p_id_direcciones_destinatario
	RETURNING numero_del_documento;
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION pedidos.pedidos_crud (
		p_operacion_crud varchar(30),
		p_numero_del_documento varchar(10), 
		p_estado_orden varchar(2), 
		p_fecha_modificacion date, 
		p_hora_modificacion time, 
		p_estado_renglon_bitacora varchar(5), 
		p_fecha_emision date,
		p_hora_emision time, 
		p_orden_original varchar(50), 
		p_id_tipo_entrega smallint, 
		p_id_tipo_de_venta smallint, 
		p_id_cliente_remitente integer, 
		p_id_cliente_destinatario integer, 
		p_id_direcciones_destinatario integer, 
		p_id_tipo_de_act smallint, 
		p_id_tipo_de_documento smallint, 
	    p_id_periodicidad smallint, 
		p_observaciones varchar(200),
		p_id_tienda_y_locacion_destino integer, 
		p_id_frecuencia_visita smallint,
	    p_fecha_de_compra date, 
		p_id_estado_del_documento integer, 
		p_fecha_de_emision date
) RETURNS SETOF varchar(10) AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			RETURN QUERY SELECT pedidos.pedidos_ins (p_numero_del_documento, p_estado_orden, p_fecha_modificacion, p_hora_modificacion, 
				 p_estado_renglon_bitacora, p_fecha_emision, p_hora_emision, p_orden_original, 
				 p_id_tipo_entrega, p_id_tipo_de_venta, p_id_cliente_remitente, p_id_cliente_destinatario, 
				 p_id_direcciones_destinatario, p_id_tipo_de_act, p_id_tipo_de_documento, p_id_periodicidad, 
				 p_observaciones, p_id_tienda_y_locacion_destino, p_id_frecuencia_visita, p_fecha_de_compra, 
				 p_id_estado_del_documento, p_fecha_de_emision);
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			RETURN QUERY SELECT pedidos.pedidos_upd (p_numero_del_documento, p_estado_orden, p_fecha_modificacion, p_hora_modificacion, 
				 p_estado_renglon_bitacora, p_fecha_emision, p_hora_emision, p_orden_original, 
				 p_id_tipo_entrega, p_id_tipo_de_venta, p_id_cliente_remitente, p_id_cliente_destinatario, 
				 p_id_direcciones_destinatario, p_id_tipo_de_act, p_id_tipo_de_documento, p_id_periodicidad, 
				 p_observaciones, p_id_tienda_y_locacion_destino, p_id_frecuencia_visita, p_fecha_de_compra, 
				 p_id_estado_del_documento, p_fecha_de_emision); 
		END IF;

END$$ LANGUAGE plpgsql;



--pedidos sku

CREATE OR REPLACE FUNCTION pedidos.pedidos_detalle_sku_ins
(
		p_numero_del_documento_pedidos varchar(10), 
		p_estado_orden_pedidos varchar(2), 
		p_fecha_modificacion_pedidos date, 
		p_hora_modificacion_pedidos time, 
		p_proveedor_de_mensajeria varchar(50), 
		p_fecha_real_de_entrega date, 
		p_id_locacion_surte integer, 
		p_id_producto_productos integer, 
		p_fecha_actualizacion_estado date, 
		p_piezas numeric(16,2), 
		p_numero_de_guia varchar(25),
		p_id_linea_detalle integer,
		p_fecha_rango_promesa_de_entrega varchar(100),
		p_hora_actualizacion_estado time, 
		p_fecha_ultimo_intento date,
		p_indicador_marketplace varchar(50),
		p_cantidad_recogida_orden_de_venta numeric(8),
		p_fecha_de_surtido date,
		p_numero_de_intentos_de_entrega numeric(8),
		p_cantidad_cancelada numeric(16,2),
		p_cantidad_entregada numeric(16,2),
		p_id_causa_de_noentrega integer,
		p_usuario_actualizador_de_estado varchar(25),
		p_fecha_recalculada date
)
RETURNS varchar(10) AS
$func$
		INSERT INTO pedidos.pedidos_detalle_sku  (
			numero_del_documento_pedidos, estado_orden_pedidos, fecha_modificacion_pedidos, 
			hora_modificacion_pedidos, proveedor_de_mensajeria, fecha_real_de_entrega, 
			id_locacion_surte, id_producto_productos, fecha_actualizacion_estado, 
			piezas, numero_de_guia, id_linea_detalle, 
			fecha_rango_promesa_de_entrega, hora_actualizacion_estado, fecha_ultimo_intento, 
			indicador_marketplace, cantidad_recogida_orden_de_venta, fecha_de_surtido, 
			numero_de_intentos_de_entrega, cantidad_cancelada, cantidad_entregada, 
			id_causa_de_noentrega, usuario_actualizador_de_estado, fecha_recalculada
		) 
		 VALUES (p_numero_del_documento_pedidos, p_estado_orden_pedidos, p_fecha_modificacion_pedidos,
				p_hora_modificacion_pedidos, p_proveedor_de_mensajeria, p_fecha_real_de_entrega,
				p_id_locacion_surte, p_id_producto_productos, p_fecha_actualizacion_estado,
				p_piezas, p_numero_de_guia, p_id_linea_detalle,
				p_fecha_rango_promesa_de_entrega, p_hora_actualizacion_estado, p_fecha_ultimo_intento,
				p_indicador_marketplace,  p_cantidad_recogida_orden_de_venta, p_fecha_de_surtido,
				p_numero_de_intentos_de_entrega, p_cantidad_cancelada, p_cantidad_entregada,
				p_id_causa_de_noentrega, p_usuario_actualizador_de_estado, p_fecha_recalculada 
				)
			RETURNING p_numero_del_documento_pedidos;
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION pedidos.pedidos_detalle_sku_upd
(
		p_numero_del_documento_pedidos varchar(10), 
		p_estado_orden_pedidos varchar(2), 
		p_fecha_modificacion_pedidos date, 
		p_hora_modificacion_pedidos time, 
		p_proveedor_de_mensajeria varchar(50), 
		p_fecha_real_de_entrega date, 
		p_id_locacion_surte integer, 
		p_id_producto_productos integer, 
		p_fecha_actualizacion_estado date, 
		p_piezas numeric(16,2), 
		p_numero_de_guia varchar(25),
		p_id_linea_detalle integer,
		p_fecha_rango_promesa_de_entrega varchar(100),
		p_hora_actualizacion_estado time, 
		p_fecha_ultimo_intento date,
		p_indicador_marketplace varchar(50),
		p_cantidad_recogida_orden_de_venta numeric(8),
		p_fecha_de_surtido date,
		p_numero_de_intentos_de_entrega numeric(8),
		p_cantidad_cancelada numeric(16,2),
		p_cantidad_entregada numeric(16,2),
		p_id_causa_de_noentrega integer,
		p_usuario_actualizador_de_estado varchar(25),
		p_fecha_recalculada date
)
RETURNS varchar(10) AS
$func$
	UPDATE pedidos.pedidos_detalle_sku
			SET 
				proveedor_de_mensajeria = p_proveedor_de_mensajeria, 
				fecha_real_de_entrega = p_fecha_real_de_entrega, 
				id_locacion_surte = p_id_locacion_surte, 
				id_producto_productos = p_id_producto_productos, 
				fecha_actualizacion_estado = p_fecha_actualizacion_estado, 
				piezas = p_piezas, 
				numero_de_guia = p_numero_de_guia, 
				fecha_rango_promesa_de_entrega = p_fecha_rango_promesa_de_entrega, 
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
				fecha_recalculada = p_fecha_recalculada
			WHERE numero_del_documento_pedidos = p_numero_del_documento_pedidos AND
					estado_orden_pedidos = p_estado_orden_pedidos AND
					fecha_modificacion_pedidos = p_fecha_modificacion_pedidos AND 
					hora_modificacion_pedidos = p_hora_modificacion_pedidos AND 
					id_linea_detalle = p_id_linea_detalle
	RETURNING numero_del_documento_pedidos;
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION pedidos.pedidos_detalle_sku_crud (
		p_operacion_crud varchar(30),
		p_numero_del_documento_pedidos varchar(10), 
		p_estado_orden_pedidos varchar(2), 
		p_fecha_modificacion_pedidos date, 
		p_hora_modificacion_pedidos time, 
		p_proveedor_de_mensajeria varchar(50), 
		p_fecha_real_de_entrega date, 
		p_id_locacion_surte integer, 
		p_id_producto_productos integer, 
		p_fecha_actualizacion_estado date, 
		p_piezas numeric(16,2), 
		p_numero_de_guia varchar(25),
		p_id_linea_detalle integer,
		p_fecha_rango_promesa_de_entrega varchar(100),
		p_hora_actualizacion_estado time, 
		p_fecha_ultimo_intento date,
		p_indicador_marketplace varchar(50),
		p_cantidad_recogida_orden_de_venta numeric(8),
		p_fecha_de_surtido date,
		p_numero_de_intentos_de_entrega numeric(8),
		p_cantidad_cancelada numeric(16,2),
		p_cantidad_entregada numeric(16,2),
		p_id_causa_de_noentrega integer,
		p_usuario_actualizador_de_estado varchar(25),
		p_fecha_recalculada date
) RETURNS void AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM pedidos.pedidos_detalle_sku_ins (p_numero_del_documento_pedidos, p_estado_orden_pedidos, p_fecha_modificacion_pedidos,
				p_hora_modificacion_pedidos, p_proveedor_de_mensajeria, p_fecha_real_de_entrega,
				p_id_locacion_surte, p_id_producto_productos, p_fecha_actualizacion_estado,
				p_piezas, p_numero_de_guia, p_id_linea_detalle,
				p_fecha_rango_promesa_de_entrega, p_hora_actualizacion_estado, p_fecha_ultimo_intento,
				p_indicador_marketplace,  p_cantidad_recogida_orden_de_venta, p_fecha_de_surtido,
				p_numero_de_intentos_de_entrega, p_cantidad_cancelada, p_cantidad_entregada,
				p_id_causa_de_noentrega, p_usuario_actualizador_de_estado, p_fecha_recalculada 
				);
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM pedidos.pedidos_detalle_sku_upd (p_numero_del_documento_pedidos, p_estado_orden_pedidos, p_fecha_modificacion_pedidos,
				p_hora_modificacion_pedidos, p_proveedor_de_mensajeria, p_fecha_real_de_entrega,
				p_id_locacion_surte, p_id_producto_productos, p_fecha_actualizacion_estado,
				p_piezas, p_numero_de_guia, p_id_linea_detalle,
				p_fecha_rango_promesa_de_entrega, p_hora_actualizacion_estado, p_fecha_ultimo_intento,
				p_indicador_marketplace,  p_cantidad_recogida_orden_de_venta, p_fecha_de_surtido,
				p_numero_de_intentos_de_entrega, p_cantidad_cancelada, p_cantidad_entregada,
				p_id_causa_de_noentrega, p_usuario_actualizador_de_estado, p_fecha_recalculada 
				);
		END IF;

END$$ LANGUAGE plpgsql;



--Cliente_ids



CREATE OR REPLACE FUNCTION clientes.cliente_ids_ins
(
	p_id_origen varchar(100),
	p_id_cliente integer,
	p_id_sistema_origen integer
)
RETURNS integer AS
$func$
	INSERT INTO clientes.cliente_ids  (id_cliente_ids,
	                                   fecha_creacion,
									   id_origen, 
									   id_cliente,
									   id_sistema_origen) 
	VALUES (nextval('clientes.seq_cliente_ids_pk'), 
			current_date,
			p_id_origen,
			p_id_cliente,
			p_id_sistema_origen
			)
	RETURNING id_cliente_ids;
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.cliente_ids_upd
(
	p_id_cliente_ids integer,
	p_id_origen varchar(100),
	p_id_cliente integer,
	p_id_sistema_origen integer
)
RETURNS integer AS
$func$
	UPDATE clientes.cliente_ids 
			SET 
				fecha_actualizacion = current_date, 
				id_origen = p_id_origen,
				id_cliente = p_id_cliente,
				id_sistema_origen = p_id_sistema_origen
			WHERE id_cliente_ids = p_id_cliente_ids
	RETURNING id_cliente_ids;
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION clientes.cliente_ids_crud (
	p_operacion_crud varchar(30),
	p_id_cliente_ids integer,
	p_id_origen varchar(100),
	p_id_cliente integer,
	p_id_sistema_origen integer
) RETURNS void AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.cliente_ids_ins (p_id_origen, 
													   p_id_cliente, 
													   p_id_sistema_origen); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.cliente_ids_upd (p_id_cliente_ids,
													   p_id_origen, 
													   p_id_cliente, 
													   p_id_sistema_origen);  
		END IF;

END$$ LANGUAGE plpgsql;
