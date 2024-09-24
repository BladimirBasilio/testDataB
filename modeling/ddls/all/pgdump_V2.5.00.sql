--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: boletas; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA boletas;


--
-- Name: SCHEMA boletas; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA boletas IS 'Schema Ingesta de Boletas';


--
-- Name: clientes; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA clientes;


--
-- Name: SCHEMA clientes; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA clientes IS 'Schema CLIENTES';


--
-- Name: pedidos; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA pedidos;


--
-- Name: SCHEMA pedidos; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA pedidos IS 'Ingesta de Pedidos/SOMS';


--
-- Name: shared; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA shared;


--
-- Name: SCHEMA shared; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA shared IS 'Schema Non-Public Compartidos';


--
-- Name: staging; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA staging;


--
-- Name: SCHEMA staging; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA staging IS 'Schema for Staging Compartidos';


--
-- Name: am_cliente_destinatario_crud(character varying, integer, character varying, character varying, character varying, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_destinatario_crud(p_operacion_crud character varying, p_id_cliente integer, p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_id_cliente_padre_del_destinatario integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.am_cliente_destinatario_ins (p_primer_nombre,
															p_segundo_nombre,
															p_apellido_paterno,
															p_apellido_materno,
															p_id_cliente_padre_del_destinatario); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.am_cliente_destinatario_upd (p_id_cliente, 
															p_primer_nombre,
															p_segundo_nombre,
															p_apellido_paterno,
															p_apellido_materno,
															p_id_cliente_padre_del_destinatario);
		END IF;

END$$;


--
-- Name: am_cliente_destinatario_ins(character varying, character varying, character varying, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_destinatario_ins(p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_id_cliente_padre_del_destinatario integer) RETURNS integer
    LANGUAGE sql
    AS $$
	INSERT INTO clientes.clientes (id_cliente, 
												 fecha_creacion, 
												 primer_nombre,
												 segundo_nombre,
												 apellido_paterno,
												 apellido_materno,
												 id_cliente_padre_del_destinatario) 
	VALUES (nextval('clientes.seq_clientes_pk'),  
												 current_date, 
												 p_primer_nombre,
												 p_segundo_nombre,
												 p_apellido_paterno,
												 p_apellido_materno,
												 p_id_cliente_padre_del_destinatario) 
	RETURNING id_cliente;
$$;


--
-- Name: am_cliente_destinatario_upd(integer, character varying, character varying, character varying, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_destinatario_upd(p_id_cliente integer, p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_id_cliente_padre_del_destinatario integer) RETURNS integer
    LANGUAGE sql
    AS $$
	UPDATE clientes.clientes
			SET 
							fecha_actualizacion = current_date, 
							primer_nombre = p_primer_nombre,
							segundo_nombre = p_segundo_nombre,
							apellido_paterno = p_apellido_paterno,
							apellido_materno = p_apellido_materno,
							id_cliente_padre_del_destinatario = p_id_cliente_padre_del_destinatario
			WHERE id_cliente = p_id_cliente
	RETURNING id_cliente;
$$;


--
-- Name: am_cliente_direcciones_crud(character varying, integer, integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying, character varying, integer, character varying, character varying, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, integer, integer, character varying, character varying, character varying, character varying, date, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_direcciones_crud(p_operacion_crud character varying, p_id_direccion_del_cliente integer, p_id_cliente integer, p_entre_calle_2 character varying, p_esta_borrado character varying, p_alias_de_direccion character varying, p_edificio character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_calle character varying, p_numero_interior character varying, p_entre_calle_1 character varying, p_id_tipo_direccion_cliente integer, p_codigo_postal character varying, p_numero_exterior character varying, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_direccion_2 character varying, p_direccion_3 character varying, p_direccion_4 character varying, p_direccion_5 character varying, p_tipo_de_direccion character varying, p_codigo_zip character varying, p_id_distrito integer, p_id_provincia integer, p_id_condado integer, p_salida_numero_exterior character varying, p_salida_calle character varying, p_salida_direccion_2 character varying, p_salida_direccion_3 character varying, p_salida_direccion_4 character varying, p_salida_direccion_5 character varying, p_salida_tipo character varying, p_salida_codigo_zip character varying, p_salida_id_distrito integer, p_salida_id_provincia integer, p_salida_id_estado_del_pais integer, p_salida_id_condado integer, p_bandera_calle character varying, p_bandera_emcccp character varying, p_bandera_termino character varying, p_token_calle character varying, p_fecha_de_operacion date, p_baja_domicilio character varying, p_id_pais integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.am_cliente_direcciones_ins  (p_id_cliente, 
																		p_entre_calle_2,
																		p_esta_borrado,
																		p_alias_de_direccion,
																		p_edificio,
																		p_id_colonia,	
																		p_id_municipio,
																		p_id_estado_del_pais,
																		p_calle,
																		p_numero_interior,
																		p_entre_calle_1,
																		p_id_tipo_direccion_cliente,
																		p_codigo_postal,
																		p_numero_exterior,
																		p_id_sistema_origen,
																		p_id_transaccion,
																		p_id_operacion,
																		p_direccion_2,
																		p_direccion_3,
																		p_direccion_4,
																		p_direccion_5,
																		p_tipo_de_direccion,
																		p_codigo_zip,
																		p_id_distrito,
																		p_id_provincia,
																		p_id_condado,
																		p_salida_numero_exterior,
																		p_salida_calle,
																		p_salida_direccion_2,
																		p_salida_direccion_3,
																		p_salida_direccion_4,
																		p_salida_direccion_5,
																		p_salida_tipo,
																		p_salida_codigo_zip,
																		p_salida_id_distrito,
																		p_salida_id_provincia,
																		p_salida_id_estado_del_pais,
																		p_salida_id_condado,
																		p_bandera_calle,
																		p_bandera_emcccp,
																		p_bandera_termino,
																		p_token_calle,
																		p_fecha_de_operacion,
																		p_baja_domicilio,
																		p_id_pais);
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.am_cliente_direcciones_upd  (p_id_direccion_del_cliente,
																		p_id_cliente, 
																		p_entre_calle_2,
																		p_esta_borrado,
																		p_alias_de_direccion,
																		p_edificio,
																		p_id_colonia,	
																		p_id_municipio,
																		p_id_estado_del_pais,
																		p_calle,
																		p_numero_interior,
																		p_entre_calle_1,
																		p_id_tipo_direccion_cliente,
																		p_codigo_postal,
																		p_numero_exterior,
																		p_id_sistema_origen,
																		p_id_transaccion,
																		p_id_operacion,
																		p_direccion_2,
																		p_direccion_3,
																		p_direccion_4,
																		p_direccion_5,
																		p_tipo_de_direccion,
																		p_codigo_zip,
																		p_id_distrito,
																		p_id_provincia,
																		p_id_condado,
																		p_salida_numero_exterior,
																		p_salida_calle,
																		p_salida_direccion_2,
																		p_salida_direccion_3,
																		p_salida_direccion_4,
																		p_salida_direccion_5,
																		p_salida_tipo,
																		p_salida_codigo_zip,
																		p_salida_id_distrito,
																		p_salida_id_provincia,
																		p_salida_id_estado_del_pais,
																		p_salida_id_condado,
																		p_bandera_calle,
																		p_bandera_emcccp,
																		p_bandera_termino,
																		p_token_calle,
																		p_fecha_de_operacion,
																		p_baja_domicilio,
																		p_id_pais);
		END IF;

END$$;


--
-- Name: am_cliente_direcciones_ins(integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying, character varying, integer, character varying, character varying, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, integer, integer, character varying, character varying, character varying, character varying, date, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_direcciones_ins(p_id_cliente integer, p_entre_calle_2 character varying, p_esta_borrado character varying, p_alias_de_direccion character varying, p_edificio character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_calle character varying, p_numero_interior character varying, p_entre_calle_1 character varying, p_id_tipo_direccion_cliente integer, p_codigo_postal character varying, p_numero_exterior character varying, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_direccion_2 character varying, p_direccion_3 character varying, p_direccion_4 character varying, p_direccion_5 character varying, p_tipo_de_direccion character varying, p_codigo_zip character varying, p_id_distrito integer, p_id_provincia integer, p_id_condado integer, p_salida_numero_exterior character varying, p_salida_calle character varying, p_salida_direccion_2 character varying, p_salida_direccion_3 character varying, p_salida_direccion_4 character varying, p_salida_direccion_5 character varying, p_salida_tipo character varying, p_salida_codigo_zip character varying, p_salida_id_distrito integer, p_salida_id_provincia integer, p_salida_id_estado_del_pais integer, p_salida_id_condado integer, p_bandera_calle character varying, p_bandera_emcccp character varying, p_bandera_termino character varying, p_token_calle character varying, p_fecha_de_operacion date, p_baja_domicilio character varying, p_id_pais integer) RETURNS integer
    LANGUAGE sql
    AS $$
	INSERT INTO clientes.cliente_direcciones  (id_direccion_del_cliente, 
															fecha_creacion, 
															id_cliente, 
															entre_calle_2,
															esta_borrado,
															alias_de_direccion,
															edificio,
															id_colonia,	
															id_municipio,
															id_estado_del_pais,
															calle,
															numero_interior,
															entre_calle_1,
															id_tipo_direccion_cliente,
															codigo_postal,
															numero_exterior,
															id_sistema_origen,
															id_transaccion,
															id_operacion,
															direccion_2,
															direccion_3,
															direccion_4,
															direccion_5,
															tipo_de_direccion,
															codigo_zip,
															id_distrito,
															id_provincia,
															id_condado,
															salida_numero_exterior,
															salida_calle,
															salida_direccion_2,
															salida_direccion_3,
															salida_direccion_4,
															salida_direccion_5,
															salida_tipo,
															salida_codigo_zip,
															salida_id_distrito,
															salida_id_provincia,
															salida_id_estado_del_pais,
															salida_id_condado,
															bandera_calle,
															bandera_emcccp,
															bandera_termino,
															token_calle,
															fecha_de_operacion,
															baja_domicilio,
															id_pais)  
	VALUES (nextval('clientes.seq_cliente_direcciones_pk'), 
															current_date, 
															p_id_cliente, 
															p_entre_calle_2,
															p_esta_borrado,
															p_alias_de_direccion,
															p_edificio,
															p_id_colonia,	
															p_id_municipio,
															p_id_estado_del_pais,
															p_calle,
															p_numero_interior,
															p_entre_calle_1,
															p_id_tipo_direccion_cliente,
															p_codigo_postal,
															p_numero_exterior,
															p_id_sistema_origen,
															p_id_transaccion,
															p_id_operacion,
															p_direccion_2,
															p_direccion_3,
															p_direccion_4,
															p_direccion_5,
															p_tipo_de_direccion,
															p_codigo_zip,
															p_id_distrito,
															p_id_provincia,
															p_id_condado,
															p_salida_numero_exterior,
															p_salida_calle,
															p_salida_direccion_2,
															p_salida_direccion_3,
															p_salida_direccion_4,
															p_salida_direccion_5,
															p_salida_tipo,
															p_salida_codigo_zip,
															p_salida_id_distrito,
															p_salida_id_provincia,
															p_salida_id_estado_del_pais,
															p_salida_id_condado,
															p_bandera_calle,
															p_bandera_emcccp,
															p_bandera_termino,
															p_token_calle,
															p_fecha_de_operacion,
															p_baja_domicilio,
															p_id_pais)
	RETURNING id_direccion_del_cliente;
$$;


--
-- Name: am_cliente_direcciones_upd(integer, integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying, character varying, integer, character varying, character varying, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, integer, integer, character varying, character varying, character varying, character varying, date, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_direcciones_upd(p_id_direccion_del_cliente integer, p_id_cliente integer, p_entre_calle_2 character varying, p_esta_borrado character varying, p_alias_de_direccion character varying, p_edificio character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_calle character varying, p_numero_interior character varying, p_entre_calle_1 character varying, p_id_tipo_direccion_cliente integer, p_codigo_postal character varying, p_numero_exterior character varying, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_direccion_2 character varying, p_direccion_3 character varying, p_direccion_4 character varying, p_direccion_5 character varying, p_tipo_de_direccion character varying, p_codigo_zip character varying, p_id_distrito integer, p_id_provincia integer, p_id_condado integer, p_salida_numero_exterior character varying, p_salida_calle character varying, p_salida_direccion_2 character varying, p_salida_direccion_3 character varying, p_salida_direccion_4 character varying, p_salida_direccion_5 character varying, p_salida_tipo character varying, p_salida_codigo_zip character varying, p_salida_id_distrito integer, p_salida_id_provincia integer, p_salida_id_estado_del_pais integer, p_salida_id_condado integer, p_bandera_calle character varying, p_bandera_emcccp character varying, p_bandera_termino character varying, p_token_calle character varying, p_fecha_de_operacion date, p_baja_domicilio character varying, p_id_pais integer) RETURNS integer
    LANGUAGE sql
    AS $$
					
	UPDATE clientes.cliente_direcciones  
			SET 
				fecha_actualizacion = current_date, 
				id_cliente =  p_id_cliente, 
				entre_calle_2 = p_entre_calle_2,
				esta_borrado = p_esta_borrado,
				alias_de_direccion = p_alias_de_direccion,
				edificio = p_edificio,
				id_colonia = p_id_colonia,	
				id_municipio = p_id_municipio,
				id_estado_del_pais = p_id_estado_del_pais,
				calle = p_calle,
				numero_interior = p_numero_interior,
				entre_calle_1 = p_entre_calle_1,
				id_tipo_direccion_cliente = p_id_tipo_direccion_cliente,
				codigo_postal = p_codigo_postal,
				numero_exterior = p_numero_exterior,
				id_sistema_origen = p_id_sistema_origen,
				id_transaccion = p_id_transaccion,
				id_operacion = p_id_operacion,
				direccion_2 = p_direccion_2,
				direccion_3 = p_direccion_3,
				direccion_4 = p_direccion_4,
				direccion_5 = p_direccion_5,
				tipo_de_direccion = p_tipo_de_direccion,
				codigo_zip = p_codigo_zip,
				id_distrito = p_id_distrito,
				id_provincia = p_id_provincia,
				id_condado = p_id_condado,
				salida_numero_exterior = p_salida_numero_exterior,
				salida_calle = p_salida_calle,
				salida_direccion_2 = p_salida_direccion_2,
				salida_direccion_3 = p_salida_direccion_3,
				salida_direccion_4 = p_salida_direccion_4,
				salida_direccion_5 = p_salida_direccion_5,
				salida_tipo = p_salida_tipo,
				salida_codigo_zip = p_salida_codigo_zip,
				salida_id_distrito = p_salida_id_distrito,
				salida_id_provincia = p_salida_id_provincia,
				salida_id_estado_del_pais = p_salida_id_estado_del_pais,
				salida_id_condado = p_salida_id_condado,
				bandera_calle = p_bandera_calle,
				bandera_emcccp = p_bandera_emcccp,
				bandera_termino = p_bandera_termino,
				token_calle = p_token_calle,
				fecha_de_operacion = p_fecha_de_operacion,
				baja_domicilio = p_baja_domicilio,
				id_pais = p_id_pais
			WHERE id_direccion_del_cliente = p_id_direccion_del_cliente
	RETURNING id_direccion_del_cliente;
$$;


--
-- Name: am_cliente_emails_de_contacto_crud(character varying, integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, date); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_emails_de_contacto_crud(p_operacion_crud character varying, p_id_cliente_emails_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_tipo character varying, p_email character varying, p_esta_borrado character varying, p_id_transaccion character varying, p_id_operacion character varying, p_salida_correo_electronico character varying, p_salida_tipo character varying, p_bandera_tipo character varying, p_token_correo_electronico character varying, p_fecha_operacion date) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.am_cliente_emails_de_contacto_ins (p_id_cliente, 
															 p_id_sistema_origen, 
															 p_tipo,
														     p_email,
															 p_esta_borrado,
															 p_id_transaccion,
															 p_id_operacion,
															 p_salida_correo_electronico,
															 p_salida_tipo,
															 p_bandera_tipo,
															 p_token_correo_electronico,
															 p_fecha_operacion); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.am_cliente_emails_de_contacto_upd (p_id_cliente_emails_de_contacto, 
															 p_id_cliente, 
															 p_id_sistema_origen, 
															 p_tipo,
														     p_email,
															 p_esta_borrado,
															 p_id_transaccion,
															 p_id_operacion,
															 p_salida_correo_electronico,
															 p_salida_tipo,
															 p_bandera_tipo,
															 p_token_correo_electronico,
															 p_fecha_operacion); 
		END IF;

END$$;


--
-- Name: am_cliente_emails_de_contacto_ins(integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, date); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_emails_de_contacto_ins(p_id_cliente integer, p_id_sistema_origen integer, p_tipo character varying, p_email character varying, p_esta_borrado character varying, p_id_transaccion character varying, p_id_operacion character varying, p_salida_correo_electronico character varying, p_salida_tipo character varying, p_bandera_tipo character varying, p_token_correo_electronico character varying, p_fecha_operacion date) RETURNS integer
    LANGUAGE sql
    AS $$
	INSERT INTO clientes.cliente_emails_de_contacto (id_cliente_emails_de_contacto, 
													 fecha_creacion, 
													 id_cliente, 
													 id_sistema_origen, 
													 tipo,
													 email,
													 esta_borrado,
													 id_transaccion,
													 id_operacion,
													 salida_correo_electronico,
													 salida_tipo,
													 bandera_tipo,
													 token_correo_electronico,
													 fecha_operacion
													 ) 
	VALUES (nextval('clientes.seq_cliente_emails_de_contacto_pk'), 
													current_date, 
													p_id_cliente, 
													p_id_sistema_origen, 
													p_tipo,
													p_email,
													p_esta_borrado,
													p_id_transaccion,
													p_id_operacion,
													p_salida_correo_electronico,
													p_salida_tipo,
													p_bandera_tipo,
													p_token_correo_electronico,
													p_fecha_operacion)
	RETURNING id_cliente_emails_de_contacto;
$$;


--
-- Name: am_cliente_emails_de_contacto_upd(integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, date); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_emails_de_contacto_upd(p_id_cliente_emails_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_tipo character varying, p_email character varying, p_esta_borrado character varying, p_id_transaccion character varying, p_id_operacion character varying, p_salida_correo_electronico character varying, p_salida_tipo character varying, p_bandera_tipo character varying, p_token_correo_electronico character varying, p_fecha_operacion date) RETURNS integer
    LANGUAGE sql
    AS $$

			UPDATE clientes.cliente_emails_de_contacto  
			SET 
									fecha_actualizacion = current_date, 
									id_cliente = p_id_cliente,
									id_sistema_origen = p_id_sistema_origen, 
									tipo = p_tipo,
									email = p_email,
									esta_borrado = p_esta_borrado,
									id_transaccion = p_id_transaccion,
									id_operacion = p_id_operacion,
									salida_correo_electronico = p_salida_correo_electronico,
									salida_tipo = p_salida_tipo,
									bandera_tipo = p_bandera_tipo,
									token_correo_electronico = p_token_correo_electronico,
									fecha_operacion = p_fecha_operacion
			WHERE id_cliente_emails_de_contacto = p_id_cliente_emails_de_contacto
	RETURNING id_cliente_emails_de_contacto;
$$;


--
-- Name: am_cliente_formas_de_pago_crud(character varying, integer, character varying, character varying, smallint, character varying, integer, integer, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_formas_de_pago_crud(p_operacion_crud character varying, p_id_cliente_tarjeta integer, p_numero_tarjeta_cliente character varying, p_tipo_tarjeta character varying, p_esta_activa smallint, p_hash1_tarjeta character varying, p_id_cliente integer, p_id_banco_emisor_tarjeta integer, p_id_sistema_origen integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.am_cliente_formas_de_pago_ins (p_numero_tarjeta_cliente,
															p_tipo_tarjeta,
															p_esta_activa,
															p_hash1_tarjeta,
															p_id_cliente,
															p_id_banco_emisor_tarjeta,
															p_id_sistema_origen); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.am_cliente_formas_de_pago_upd (p_id_cliente_tarjeta, 
															p_numero_tarjeta_cliente,
															p_tipo_tarjeta,
															p_esta_activa,
															p_hash1_tarjeta,
															p_id_cliente,
															p_id_banco_emisor_tarjeta,
															p_id_sistema_origen);
		END IF;

END$$;


--
-- Name: am_cliente_formas_de_pago_ins(character varying, character varying, smallint, character varying, integer, integer, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_formas_de_pago_ins(p_numero_tarjeta_cliente character varying, p_tipo_tarjeta character varying, p_esta_activa smallint, p_hash1_tarjeta character varying, p_id_cliente integer, p_id_banco_emisor_tarjeta integer, p_id_sistema_origen integer) RETURNS integer
    LANGUAGE sql
    AS $$
	INSERT INTO clientes.cliente_formas_de_pago (id_cliente_tarjeta, 
												 fecha_creacion, 
												 numero_tarjeta_cliente,
												 tipo_tarjeta,
												 esta_activa,
												 hash1_tarjeta,
												 id_cliente,
												 id_banco_emisor_tarjeta,
												 id_sistema_origen) 
	VALUES (nextval('clientes.seq_cliente_formas_de_pago_pk'), 
												 current_date, 
												 p_numero_tarjeta_cliente,
												 p_tipo_tarjeta,
												 p_esta_activa,
												 p_hash1_tarjeta,
												 p_id_cliente,
												 p_id_banco_emisor_tarjeta,
												 p_id_sistema_origen) 
	RETURNING id_cliente_tarjeta;
$$;


--
-- Name: am_cliente_formas_de_pago_upd(integer, character varying, character varying, smallint, character varying, integer, integer, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_formas_de_pago_upd(p_id_cliente_tarjeta integer, p_numero_tarjeta_cliente character varying, p_tipo_tarjeta character varying, p_esta_activa smallint, p_hash1_tarjeta character varying, p_id_cliente integer, p_id_banco_emisor_tarjeta integer, p_id_sistema_origen integer) RETURNS integer
    LANGUAGE sql
    AS $$
	UPDATE clientes.cliente_formas_de_pago  
			SET 
							 fecha_actualizacion = current_date, 
							 numero_tarjeta_cliente = p_numero_tarjeta_cliente,
							 tipo_tarjeta = p_tipo_tarjeta,
							 esta_activa = p_esta_activa,
							 hash1_tarjeta = p_hash1_tarjeta,
							 id_cliente =  p_id_cliente,
							 id_banco_emisor_tarjeta = p_id_banco_emisor_tarjeta,
							 id_sistema_origen = p_id_sistema_origen
			WHERE id_cliente_tarjeta = p_id_cliente_tarjeta
	RETURNING id_cliente_tarjeta;
$$;


--
-- Name: am_cliente_telefonos_de_contacto_crud(character varying, integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, date); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_telefonos_de_contacto_crud(p_operacion_crud character varying, p_id_cliente_telefonos_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_alias_telefono character varying, p_telefono character varying, p_esta_borrado character varying, p_id_transaccion character varying, p_id_operacion character varying, p_lada character varying, p_extension_telefonica character varying, p_codigo_zip character varying, p_id_provincia integer, p_id_ciudad integer, p_salida_numero_de_telefono character varying, p_salida_tipo_de_telefono character varying, p_bandera_telefono character varying, p_salida_extension_telefonica character varying, p_fecha_de_operacion date) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.am_cliente_telefonos_de_contacto_ins (p_id_cliente, 
																 p_id_sistema_origen, 
																 p_alias_telefono,
																 p_telefono,
																 p_esta_borrado,
																 p_id_transaccion,
																 p_id_operacion,
																 p_lada,
																 p_extension_telefonica,
																 p_codigo_zip,
																 p_id_provincia,
																 p_id_ciudad,
																 p_salida_numero_de_telefono,
																 p_salida_tipo_de_telefono,
																 p_bandera_telefono,
																 p_salida_extension_telefonica,
																 p_fecha_de_operacion); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.am_cliente_telefonos_de_contacto_upd (p_id_cliente_telefonos_de_contacto, 
																 p_id_cliente, 
																 p_id_sistema_origen, 
																 p_alias_telefono,
																 p_telefono,
																 p_esta_borrado,
																 p_id_transaccion,
																 p_id_operacion,
																 p_lada,
																 p_extension_telefonica,
																 p_codigo_zip,
																 p_id_provincia,
																 p_id_ciudad,
																 p_salida_numero_de_telefono,
																 p_salida_tipo_de_telefono,
																 p_bandera_telefono,
																 p_salida_extension_telefonica,
																 p_fecha_de_operacion); 
		END IF;

END$$;


--
-- Name: am_cliente_telefonos_de_contacto_ins(integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, date); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_telefonos_de_contacto_ins(p_id_cliente integer, p_id_sistema_origen integer, p_alias_telefono character varying, p_telefono character varying, p_esta_borrado character varying, p_id_transaccion character varying, p_id_operacion character varying, p_lada character varying, p_extension_telefonica character varying, p_codigo_zip character varying, p_id_provincia integer, p_id_ciudad integer, p_salida_numero_de_telefono character varying, p_salida_tipo_de_telefono character varying, p_bandera_telefono character varying, p_salida_extension_telefonica character varying, p_fecha_de_operacion date) RETURNS integer
    LANGUAGE sql
    AS $$
	INSERT INTO clientes.cliente_telefonos_de_contacto (id_cliente_telefonos_de_contacto, 
														 fecha_creacion, 
														 id_cliente, 
														 id_sistema_origen, 
														 alias_telefono,
														 telefono,
														 esta_borrado,
														 id_transaccion,
														 id_operacion,
														 lada,
														 extension_telefonica,
														 codigo_zip,
														 id_provincia,
														 id_ciudad,
														 salida_numero_de_telefono,
														 salida_tipo_de_telefono,
														 bandera_telefono,
														 salida_extension_telefonica,
														 fecha_de_operacion) 
	VALUES (nextval('clientes.seq_cliente_telefonos_de_contacto_pk'), 
														current_date, 
														 p_id_cliente, 
														 p_id_sistema_origen, 
														 p_alias_telefono,
														 p_telefono,
														 p_esta_borrado,
														 p_id_transaccion,
														 p_id_operacion,
														 p_lada,
														 p_extension_telefonica,
														 p_codigo_zip,
														 p_id_provincia,
														 p_id_ciudad,
														 p_salida_numero_de_telefono,
														 p_salida_tipo_de_telefono,
														 p_bandera_telefono,
														 p_salida_extension_telefonica,
														 p_fecha_de_operacion)
	RETURNING id_cliente_telefonos_de_contacto;
$$;


--
-- Name: am_cliente_telefonos_de_contacto_upd(integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, integer, character varying, character varying, character varying, character varying, date); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_cliente_telefonos_de_contacto_upd(p_id_cliente_telefonos_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_alias_telefono character varying, p_telefono character varying, p_esta_borrado character varying, p_id_transaccion character varying, p_id_operacion character varying, p_lada character varying, p_extension_telefonica character varying, p_codigo_zip character varying, p_id_provincia integer, p_id_ciudad integer, p_salida_numero_de_telefono character varying, p_salida_tipo_de_telefono character varying, p_bandera_telefono character varying, p_salida_extension_telefonica character varying, p_fecha_de_operacion date) RETURNS integer
    LANGUAGE sql
    AS $$
	UPDATE clientes.cliente_telefonos_de_contacto  
			SET 
							 fecha_actualizacion = current_date, 
							 id_cliente = p_id_cliente, 
							 id_sistema_origen = p_id_sistema_origen, 
							 alias_telefono = p_alias_telefono,
							 telefono = p_telefono,
							 esta_borrado = p_esta_borrado,
							 id_transaccion = p_id_transaccion,
							 id_operacion = p_id_operacion,
							 lada = p_lada,
							 extension_telefonica = p_extension_telefonica,
							 codigo_zip = p_codigo_zip,
							 id_provincia = p_id_provincia,
							 id_ciudad =  p_id_ciudad,
							 salida_numero_de_telefono = p_salida_numero_de_telefono,
							 salida_tipo_de_telefono = p_salida_tipo_de_telefono,
							 bandera_telefono = p_bandera_telefono,
							 salida_extension_telefonica = p_salida_extension_telefonica,
							 fecha_de_operacion = p_fecha_de_operacion
			WHERE id_cliente_telefonos_de_contacto = p_id_cliente_telefonos_de_contacto
	RETURNING id_cliente_telefonos_de_contacto;
$$;


--
-- Name: am_clientes_crud(character varying, integer, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, date, integer, character varying, character varying, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, character varying, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_clientes_crud(p_operacion_crud character varying, p_id_cliente integer, p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_fecha_de_nacimiento date, p_genero character varying, p_rfc character varying, p_numero_de_seguro_social character varying, p_fecha_de_registro date, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_salida_primer_nombre character varying, p_salida_segundo_nombre character varying, p_bandera_del_nombre character varying, p_salida_genero character varying, p_salida_fecha_de_nacimiento date, p_salida_rfc character varying, p_salida_apellido_paterno character varying, p_bandera_rfc character varying, p_bandera_ucm character varying, p_nombre_de_token character varying, p_fecha_de_la_operacion date, p_salida_bandera_fecha character varying, p_salida_token_primer_nombre character varying, p_salida_token_apellido_paterno character varying, p_salida_token_segundo_nombre character varying, p_salida_bandera_rfc character varying, p_id_pais integer) RETURNS SETOF integer
    LANGUAGE plpgsql
    AS $$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			RETURN QUERY SELECT clientes.am_clientes_ins (p_primer_nombre, 
													   p_segundo_nombre, 
													   p_apellido_paterno, 
													   p_apellido_materno,
													   p_fecha_de_nacimiento,
													   p_genero,
													   p_rfc,
													   p_numero_de_seguro_social,
													   p_fecha_de_registro,
													   p_id_sistema_origen,
													   p_id_transaccion,
													   p_id_operacion,
													   p_salida_primer_nombre,
													   p_salida_segundo_nombre,
													   p_bandera_del_nombre,
													   p_salida_genero,
													   p_salida_fecha_de_nacimiento,
													   p_salida_rfc,
													   p_salida_apellido_paterno,
													   p_bandera_rfc,
													   p_bandera_ucm,
													   p_nombre_de_token,
													   p_fecha_de_la_operacion,
													   p_salida_bandera_fecha,
													   p_salida_token_primer_nombre,
													   p_salida_token_apellido_paterno,
													   p_salida_token_segundo_nombre,
													   p_salida_bandera_rfc,
													   p_id_pais); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			RETURN QUERY SELECT clientes.am_clientes_upd (p_id_cliente,
													   p_primer_nombre,
													   p_segundo_nombre, 
													   p_apellido_paterno, 
													   p_apellido_materno,
													   p_fecha_de_nacimiento,
													   p_genero,
													   p_rfc,
													   p_numero_de_seguro_social,
													   p_fecha_de_registro,
													   p_id_sistema_origen,
													   p_id_transaccion,
													   p_id_operacion,
													   p_salida_primer_nombre,
													   p_salida_segundo_nombre,
													   p_bandera_del_nombre,
													   p_salida_genero,
													   p_salida_fecha_de_nacimiento,
													   p_salida_rfc,
													   p_salida_apellido_paterno,
													   p_bandera_rfc,
													   p_bandera_ucm,
													   p_nombre_de_token,
													   p_fecha_de_la_operacion,
													   p_salida_bandera_fecha,
													   p_salida_token_primer_nombre,
													   p_salida_token_apellido_paterno,
													   p_salida_token_segundo_nombre,
													   p_salida_bandera_rfc,
													   p_id_pais);  
		END IF;

END$$;


--
-- Name: am_clientes_ins(character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, date, integer, character varying, character varying, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, character varying, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_clientes_ins(p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_fecha_de_nacimiento date, p_genero character varying, p_rfc character varying, p_numero_de_seguro_social character varying, p_fecha_de_registro date, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_salida_primer_nombre character varying, p_salida_segundo_nombre character varying, p_bandera_del_nombre character varying, p_salida_genero character varying, p_salida_fecha_de_nacimiento date, p_salida_rfc character varying, p_salida_apellido_paterno character varying, p_bandera_rfc character varying, p_bandera_ucm character varying, p_nombre_de_token character varying, p_fecha_de_la_operacion date, p_salida_bandera_fecha character varying, p_salida_token_primer_nombre character varying, p_salida_token_apellido_paterno character varying, p_salida_token_segundo_nombre character varying, p_salida_bandera_rfc character varying, p_id_pais integer) RETURNS integer
    LANGUAGE sql
    AS $$
	INSERT INTO clientes.clientes  (id_cliente, 
									fecha_creacion,
									primer_nombre,
									segundo_nombre,
									apellido_paterno,
									apellido_materno,
									fecha_de_nacimiento,
									genero,
									rfc,
									numero_de_seguro_social,
									fecha_de_registro,
									id_sistema_origen,
									id_transaccion,
									id_operacion,
									salida_primer_nombre,
									salida_segundo_nombre,
									bandera_del_nombre,
									salida_genero,
									salida_fecha_de_nacimiento,
									salida_rfc,
									salida_apellido_paterno,
									bandera_rfc,
									bandera_ucm,
									nombre_de_token,
									fecha_de_la_operacion,
									salida_bandera_fecha,
									salida_token_primer_nombre,
									salida_token_apellido_paterno,
									salida_token_segundo_nombre,
									salida_bandera_rfc,
									id_pais
									) 
	VALUES (nextval('clientes.seq_clientes_pk'), 
									current_date, 
									p_primer_nombre,
									p_segundo_nombre,
									p_apellido_paterno,
									p_apellido_materno,
									p_fecha_de_nacimiento,
									p_genero,
									p_rfc,
									p_numero_de_seguro_social,
									p_fecha_de_registro,
									p_id_sistema_origen,
									p_id_transaccion,
									p_id_operacion,
									p_salida_primer_nombre,
									p_salida_segundo_nombre,
									p_bandera_del_nombre,
									p_salida_genero,
									p_salida_fecha_de_nacimiento,
									p_salida_rfc,
									p_salida_apellido_paterno,
									p_bandera_rfc,
									p_bandera_ucm,
									p_nombre_de_token,
									p_fecha_de_la_operacion,
									p_salida_bandera_fecha,
									p_salida_token_primer_nombre,
									p_salida_token_apellido_paterno,
									p_salida_token_segundo_nombre,
									p_salida_bandera_rfc,
									p_id_pais)
	RETURNING id_cliente;
$$;


--
-- Name: am_clientes_upd(integer, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, date, integer, character varying, character varying, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, character varying, character varying, date, character varying, character varying, character varying, character varying, character varying, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.am_clientes_upd(p_id_cliente integer, p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_fecha_de_nacimiento date, p_genero character varying, p_rfc character varying, p_numero_de_seguro_social character varying, p_fecha_de_registro date, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_salida_primer_nombre character varying, p_salida_segundo_nombre character varying, p_bandera_del_nombre character varying, p_salida_genero character varying, p_salida_fecha_de_nacimiento date, p_salida_rfc character varying, p_salida_apellido_paterno character varying, p_bandera_rfc character varying, p_bandera_ucm character varying, p_nombre_de_token character varying, p_fecha_de_la_operacion date, p_salida_bandera_fecha character varying, p_salida_token_primer_nombre character varying, p_salida_token_apellido_paterno character varying, p_salida_token_segundo_nombre character varying, p_salida_bandera_rfc character varying, p_id_pais integer) RETURNS integer
    LANGUAGE sql
    AS $$
	UPDATE clientes.clientes  
			SET 
							fecha_actualizacion = current_date, 
							primer_nombre = p_primer_nombre,
							segundo_nombre = p_segundo_nombre,
							apellido_paterno = p_apellido_paterno,
							apellido_materno = p_apellido_materno,
							fecha_de_nacimiento = p_fecha_de_nacimiento,
							genero = p_genero,
							rfc = p_rfc,
							numero_de_seguro_social = p_numero_de_seguro_social,
							fecha_de_registro = p_fecha_de_registro,
							id_sistema_origen = p_id_sistema_origen,
							id_transaccion = p_id_transaccion,
							id_operacion = p_id_operacion,
							salida_primer_nombre = p_salida_primer_nombre,
							salida_segundo_nombre = p_salida_segundo_nombre,
							bandera_del_nombre = p_bandera_del_nombre,
							salida_genero = p_salida_genero,
							salida_fecha_de_nacimiento = p_salida_fecha_de_nacimiento,
							salida_rfc = p_salida_rfc,
							salida_apellido_paterno = p_salida_apellido_paterno,
							bandera_rfc = p_bandera_rfc,
							bandera_ucm = p_bandera_ucm,
							nombre_de_token = p_nombre_de_token,
							fecha_de_la_operacion = p_fecha_de_la_operacion,
							salida_bandera_fecha = p_salida_bandera_fecha,
							salida_token_primer_nombre = p_salida_token_primer_nombre,
							salida_token_apellido_paterno = p_salida_token_apellido_paterno,
							salida_token_segundo_nombre = p_salida_token_segundo_nombre,
							salida_bandera_rfc = p_salida_bandera_rfc,
							id_pais = p_id_pais
			WHERE id_cliente = p_id_cliente
	RETURNING id_cliente;
$$;


--
-- Name: cliente_direcciones_crud(character varying, integer, integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_direcciones_crud(p_operacion_crud character varying, p_id_direccion_del_cliente integer, p_id_cliente integer, p_calle character varying, p_numero_exterior character varying, p_numero_interior character varying, p_codigo_postal character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_entre_calle_1 character varying, p_entre_calle_2 character varying) RETURNS SETOF integer
    LANGUAGE plpgsql
    AS $$
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

END$$;


--
-- Name: cliente_direcciones_ins(integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_direcciones_ins(p_id_cliente integer, p_calle character varying, p_numero_exterior character varying, p_numero_interior character varying, p_codigo_postal character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_entre_calle_1 character varying, p_entre_calle_2 character varying) RETURNS integer
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: cliente_direcciones_upd(integer, integer, character varying, character varying, character varying, character varying, integer, integer, integer, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_direcciones_upd(p_id_direccion_del_cliente integer, p_id_cliente integer, p_calle character varying, p_numero_exterior character varying, p_numero_interior character varying, p_codigo_postal character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_entre_calle_1 character varying, p_entre_calle_2 character varying) RETURNS integer
    LANGUAGE sql
    AS $$
					
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
$$;


--
-- Name: cliente_emails_de_contacto_crud(character varying, integer, integer, integer, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_emails_de_contacto_crud(p_operacion_crud character varying, p_id_cliente_emails_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_email character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
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

END$$;


--
-- Name: cliente_emails_de_contacto_ins(integer, integer, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_emails_de_contacto_ins(p_id_cliente integer, p_id_sistema_origen integer, p_email character varying) RETURNS integer
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: cliente_emails_de_contacto_upd(integer, integer, integer, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_emails_de_contacto_upd(p_id_cliente_emails_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_email character varying) RETURNS integer
    LANGUAGE sql
    AS $$
	UPDATE clientes.cliente_emails_de_contacto  
			SET 
				fecha_actualizacion = current_date, 
				id_cliente = p_id_cliente,
				id_sistema_origen = p_id_sistema_origen,
				email = p_email
			WHERE id_cliente_emails_de_contacto = p_id_cliente_emails_de_contacto
	RETURNING id_cliente_emails_de_contacto;
$$;


--
-- Name: cliente_ids_crud(character varying, integer, character varying, integer, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_ids_crud(p_operacion_crud character varying, p_id_cliente_ids integer, p_id_origen character varying, p_id_cliente integer, p_id_sistema_origen integer) RETURNS void
    LANGUAGE plpgsql
    AS $$
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

END$$;


--
-- Name: cliente_ids_ins(character varying, integer, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_ids_ins(p_id_origen character varying, p_id_cliente integer, p_id_sistema_origen integer) RETURNS integer
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: cliente_ids_upd(integer, character varying, integer, integer); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_ids_upd(p_id_cliente_ids integer, p_id_origen character varying, p_id_cliente integer, p_id_sistema_origen integer) RETURNS integer
    LANGUAGE sql
    AS $$
	UPDATE clientes.cliente_ids 
			SET 
				fecha_actualizacion = current_date, 
				id_origen = p_id_origen,
				id_cliente = p_id_cliente,
				id_sistema_origen = p_id_sistema_origen
			WHERE id_cliente_ids = p_id_cliente_ids
	RETURNING id_cliente_ids;
$$;


--
-- Name: cliente_telefonos_de_contacto_crud(character varying, integer, integer, integer, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_telefonos_de_contacto_crud(p_operacion_crud character varying, p_id_cliente_telefonos_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_telefono character varying, p_alias_telefono character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
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

END$$;


--
-- Name: cliente_telefonos_de_contacto_ins(integer, integer, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_telefonos_de_contacto_ins(p_id_cliente integer, p_id_sistema_origen integer, p_telefono character varying, p_alias_telefono character varying) RETURNS integer
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: cliente_telefonos_de_contacto_upd(integer, integer, integer, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.cliente_telefonos_de_contacto_upd(p_id_cliente_telefonos_de_contacto integer, p_id_cliente integer, p_id_sistema_origen integer, p_telefono character varying, p_alias_telefono character varying) RETURNS integer
    LANGUAGE sql
    AS $$
	UPDATE clientes.cliente_telefonos_de_contacto  
			SET 
				fecha_actualizacion = current_date, 
				id_cliente = p_id_cliente,
				id_sistema_origen = p_id_sistema_origen,
				telefono = p_telefono,
				alias_telefono = p_alias_telefono
			WHERE id_cliente_telefonos_de_contacto = p_id_cliente_telefonos_de_contacto
	RETURNING id_cliente_telefonos_de_contacto;
$$;


--
-- Name: clientes_crud(character varying, integer, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.clientes_crud(p_operacion_crud character varying, p_id_cliente integer, p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_clave_homologada character varying) RETURNS SETOF integer
    LANGUAGE plpgsql
    AS $$
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

END$$;


--
-- Name: clientes_ins(character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.clientes_ins(p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_clave_homologada character varying) RETURNS integer
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: clientes_upd(integer, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: clientes; Owner: -
--

CREATE FUNCTION clientes.clientes_upd(p_id_cliente integer, p_primer_nombre character varying, p_segundo_nombre character varying, p_apellido_paterno character varying, p_apellido_materno character varying, p_clave_homologada character varying) RETURNS integer
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: pedidos_crud(character varying, character varying, character varying, date, time without time zone, character varying, date, time without time zone, character varying, smallint, smallint, integer, integer, integer, smallint, smallint, smallint, character varying, integer, smallint, date, integer, date); Type: FUNCTION; Schema: pedidos; Owner: -
--

CREATE FUNCTION pedidos.pedidos_crud(p_operacion_crud character varying, p_numero_del_documento character varying, p_estado_orden character varying, p_fecha_modificacion date, p_hora_modificacion time without time zone, p_estado_renglon_bitacora character varying, p_fecha_emision date, p_hora_emision time without time zone, p_orden_original character varying, p_id_tipo_entrega smallint, p_id_tipo_de_venta smallint, p_id_cliente_remitente integer, p_id_cliente_destinatario integer, p_id_direcciones_destinatario integer, p_id_tipo_de_act smallint, p_id_tipo_de_documento smallint, p_id_periodicidad smallint, p_observaciones character varying, p_id_tienda_y_locacion_destino integer, p_id_frecuencia_visita smallint, p_fecha_de_compra date, p_id_estado_del_documento integer, p_fecha_de_emision date) RETURNS SETOF character varying
    LANGUAGE plpgsql
    AS $$
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

END$$;


--
-- Name: pedidos_detalle_sku_crud(character varying, character varying, character varying, date, time without time zone, character varying, date, integer, integer, date, numeric, character varying, integer, character varying, time without time zone, date, character varying, numeric, date, numeric, numeric, numeric, integer, character varying, date); Type: FUNCTION; Schema: pedidos; Owner: -
--

CREATE FUNCTION pedidos.pedidos_detalle_sku_crud(p_operacion_crud character varying, p_numero_del_documento_pedidos character varying, p_estado_orden_pedidos character varying, p_fecha_modificacion_pedidos date, p_hora_modificacion_pedidos time without time zone, p_proveedor_de_mensajeria character varying, p_fecha_real_de_entrega date, p_id_locacion_surte integer, p_id_producto_productos integer, p_fecha_actualizacion_estado date, p_piezas numeric, p_numero_de_guia character varying, p_id_linea_detalle integer, p_fecha_rango_promesa_de_entrega character varying, p_hora_actualizacion_estado time without time zone, p_fecha_ultimo_intento date, p_indicador_marketplace character varying, p_cantidad_recogida_orden_de_venta numeric, p_fecha_de_surtido date, p_numero_de_intentos_de_entrega numeric, p_cantidad_cancelada numeric, p_cantidad_entregada numeric, p_id_causa_de_noentrega integer, p_usuario_actualizador_de_estado character varying, p_fecha_recalculada date) RETURNS void
    LANGUAGE plpgsql
    AS $$
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

END$$;


--
-- Name: pedidos_detalle_sku_ins(character varying, character varying, date, time without time zone, character varying, date, integer, integer, date, numeric, character varying, integer, character varying, time without time zone, date, character varying, numeric, date, numeric, numeric, numeric, integer, character varying, date); Type: FUNCTION; Schema: pedidos; Owner: -
--

CREATE FUNCTION pedidos.pedidos_detalle_sku_ins(p_numero_del_documento_pedidos character varying, p_estado_orden_pedidos character varying, p_fecha_modificacion_pedidos date, p_hora_modificacion_pedidos time without time zone, p_proveedor_de_mensajeria character varying, p_fecha_real_de_entrega date, p_id_locacion_surte integer, p_id_producto_productos integer, p_fecha_actualizacion_estado date, p_piezas numeric, p_numero_de_guia character varying, p_id_linea_detalle integer, p_fecha_rango_promesa_de_entrega character varying, p_hora_actualizacion_estado time without time zone, p_fecha_ultimo_intento date, p_indicador_marketplace character varying, p_cantidad_recogida_orden_de_venta numeric, p_fecha_de_surtido date, p_numero_de_intentos_de_entrega numeric, p_cantidad_cancelada numeric, p_cantidad_entregada numeric, p_id_causa_de_noentrega integer, p_usuario_actualizador_de_estado character varying, p_fecha_recalculada date) RETURNS character varying
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: pedidos_detalle_sku_upd(character varying, character varying, date, time without time zone, character varying, date, integer, integer, date, numeric, character varying, integer, character varying, time without time zone, date, character varying, numeric, date, numeric, numeric, numeric, integer, character varying, date); Type: FUNCTION; Schema: pedidos; Owner: -
--

CREATE FUNCTION pedidos.pedidos_detalle_sku_upd(p_numero_del_documento_pedidos character varying, p_estado_orden_pedidos character varying, p_fecha_modificacion_pedidos date, p_hora_modificacion_pedidos time without time zone, p_proveedor_de_mensajeria character varying, p_fecha_real_de_entrega date, p_id_locacion_surte integer, p_id_producto_productos integer, p_fecha_actualizacion_estado date, p_piezas numeric, p_numero_de_guia character varying, p_id_linea_detalle integer, p_fecha_rango_promesa_de_entrega character varying, p_hora_actualizacion_estado time without time zone, p_fecha_ultimo_intento date, p_indicador_marketplace character varying, p_cantidad_recogida_orden_de_venta numeric, p_fecha_de_surtido date, p_numero_de_intentos_de_entrega numeric, p_cantidad_cancelada numeric, p_cantidad_entregada numeric, p_id_causa_de_noentrega integer, p_usuario_actualizador_de_estado character varying, p_fecha_recalculada date) RETURNS character varying
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: pedidos_ins(character varying, character varying, date, time without time zone, character varying, date, time without time zone, character varying, smallint, smallint, integer, integer, integer, smallint, smallint, smallint, character varying, integer, smallint, date, integer, date); Type: FUNCTION; Schema: pedidos; Owner: -
--

CREATE FUNCTION pedidos.pedidos_ins(p_numero_del_documento character varying, p_estado_orden character varying, p_fecha_modificacion date, p_hora_modificacion time without time zone, p_estado_renglon_bitacora character varying, p_fecha_emision date, p_hora_emision time without time zone, p_orden_original character varying, p_id_tipo_entrega smallint, p_id_tipo_de_venta smallint, p_id_cliente_remitente integer, p_id_cliente_destinatario integer, p_id_direcciones_destinatario integer, p_id_tipo_de_act smallint, p_id_tipo_de_documento smallint, p_id_periodicidad smallint, p_observaciones character varying, p_id_tienda_y_locacion_destino integer, p_id_frecuencia_visita smallint, p_fecha_de_compra date, p_id_estado_del_documento integer, p_fecha_de_emision date) RETURNS character varying
    LANGUAGE sql
    AS $$
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
$$;


--
-- Name: pedidos_upd(character varying, character varying, date, time without time zone, character varying, date, time without time zone, character varying, smallint, smallint, integer, integer, integer, smallint, smallint, smallint, character varying, integer, smallint, date, integer, date); Type: FUNCTION; Schema: pedidos; Owner: -
--

CREATE FUNCTION pedidos.pedidos_upd(p_numero_del_documento character varying, p_estado_orden character varying, p_fecha_modificacion date, p_hora_modificacion time without time zone, p_estado_renglon_bitacora character varying, p_fecha_emision date, p_hora_emision time without time zone, p_orden_original character varying, p_id_tipo_entrega smallint, p_id_tipo_de_venta smallint, p_id_cliente_remitente integer, p_id_cliente_destinatario integer, p_id_direcciones_destinatario integer, p_id_tipo_de_act smallint, p_id_tipo_de_documento smallint, p_id_periodicidad smallint, p_observaciones character varying, p_id_tienda_y_locacion_destino integer, p_id_frecuencia_visita smallint, p_fecha_de_compra date, p_id_estado_del_documento integer, p_fecha_de_emision date) RETURNS character varying
    LANGUAGE sql
    AS $$
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
$$;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: boleta_cabeceras; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boleta_cabeceras (
    id_terminal_pos character varying(25) NOT NULL,
    numero_boleta character varying(25) NOT NULL,
    fecha_fin_transaccion date NOT NULL,
    hora_fin_transaccion time without time zone NOT NULL,
    id_tienda_origen character varying(25) NOT NULL,
    id_tienda_reconocimiento character varying(25) NOT NULL,
    monto_total_sku numeric(16,2) DEFAULT 0.00,
    monto_total_descuentos_sku numeric(16,2) DEFAULT 0.00,
    subtotal_boleta_respaldo numeric(16,2) DEFAULT 0.00,
    subtotal_boleta numeric(16,2) DEFAULT 0.00,
    estado_transaccion character varying(15),
    total_boleta numeric(16,2) DEFAULT 0.00,
    numero_linea_transaccion integer,
    id_tipo_transaccion integer NOT NULL,
    id_tipo_operacion smallint,
    id_seccion smallint,
    id_canal_de_venta smallint,
    id_subcanal_de_venta smallint,
    id_venta_catalogo_extendido_y_otro smallint,
    id_segmento smallint,
    id_vendedor smallint,
    id_tienda_original integer,
    id_medio_de_pago_medios_de_pago integer,
    id_tipo_de_descuento_al_total smallint,
    id_tipo_de_evento smallint,
    id_tipo_promocion smallint,
    id_numero_centro_de_servicio smallint,
    id_status_code_activacion smallint,
    id_mensaje_cancelacion smallint,
    id_direccion_seleccionada integer,
    id_cliente integer,
    id_del_servicio smallint,
    id_terminal_pos_cancelacion smallint,
    numero_autorizacion character varying(50),
    id_atg_del_cliente character varying(50),
    cuenta_empleado numeric(16,0),
    monto_emitido numeric(16,2) DEFAULT 0.00,
    codigo_facturacion character varying(50),
    cupon_informado_en_linea character varying(50),
    referencia_folio_agencia_de_viajes character varying(50),
    clave_devolucion character varying(50),
    es_cancelable smallint,
    status_code_autorizacion character varying(50),
    precio_real numeric(16,2) DEFAULT 0.00,
    codigo_barras_devolucion character varying(50),
    precio_unitario numeric(16,2) DEFAULT 0.00,
    numero_de_unidades numeric(16,0),
    numero_cuenta character varying(50),
    numero_evento character varying(50),
    numero_monedero character varying(50),
    cuenta_del_servicio character varying(50),
    numero_control_emision character varying(50),
    evento_mesa_de_regalo character varying(50),
    id_mdm_del_cliente character varying(50),
    codigo_cupon character varying(50),
    numero_canasta_de_navidad character varying(50),
    cliente_llevo_marcaderia smallint,
    numero_tarjeta_nupcial_o_bebe character varying(50),
    cuenta_dilisa character varying(50),
    numero_boleta_cancelacion character varying(25),
    monto_boleta numeric(16,2) DEFAULT 0.00,
    total_cancelacion numeric(16,2),
    precio_subtotal_neto numeric(16,2) DEFAULT 0.00,
    numero_del_documento_pedidos character varying(10),
    fecha_modificacion_pedidos date,
    hora_modificacion_pedidos time without time zone,
    estado_orden_pedidos character varying(2),
    id_atg character varying(200) NOT NULL,
    id_mdm character varying(200) NOT NULL
);


--
-- Name: TABLE boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boleta_cabeceras IS 'Tablas de Boletas de la Ingesta de TLOG';


--
-- Name: COLUMN boleta_cabeceras.id_terminal_pos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_terminal_pos IS 'Terminal';


--
-- Name: COLUMN boleta_cabeceras.numero_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_boleta IS 'Numero de Boleta';


--
-- Name: COLUMN boleta_cabeceras.fecha_fin_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.fecha_fin_transaccion IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boleta_cabeceras.hora_fin_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.hora_fin_transaccion IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boleta_cabeceras.id_tienda_origen; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tienda_origen IS 'Identificador de la tienda origen';


--
-- Name: COLUMN boleta_cabeceras.id_tienda_reconocimiento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tienda_reconocimiento IS 'Identificador de la tienda de reconocimiento';


--
-- Name: COLUMN boleta_cabeceras.monto_total_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.monto_total_sku IS 'Monto Total de la Boleta de todos los SKU';


--
-- Name: COLUMN boleta_cabeceras.monto_total_descuentos_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.monto_total_descuentos_sku IS 'Monto Total Descuentos de todos los SKU';


--
-- Name: COLUMN boleta_cabeceras.subtotal_boleta_respaldo; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.subtotal_boleta_respaldo IS 'Subtotal de la boleta respaldo Neto';


--
-- Name: COLUMN boleta_cabeceras.subtotal_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.subtotal_boleta IS 'Subtotal de la boleta';


--
-- Name: COLUMN boleta_cabeceras.estado_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.estado_transaccion IS 'INDICAT0 con estado de transaccion';


--
-- Name: COLUMN boleta_cabeceras.total_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.total_boleta IS 'Total de la Boleta';


--
-- Name: COLUMN boleta_cabeceras.numero_linea_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_linea_transaccion IS 'NUMSTRIN del TLOG';


--
-- Name: COLUMN boleta_cabeceras.id_tipo_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tipo_transaccion IS 'Identificador';


--
-- Name: COLUMN boleta_cabeceras.id_tipo_operacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tipo_operacion IS 'Tipo de operacion';


--
-- Name: COLUMN boleta_cabeceras.id_seccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_seccion IS 'Seccion de la Boleta';


--
-- Name: COLUMN boleta_cabeceras.id_canal_de_venta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_canal_de_venta IS 'Canal de Venta (10 = POS y APP / 30 = WEB)';


--
-- Name: COLUMN boleta_cabeceras.id_subcanal_de_venta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_subcanal_de_venta IS 'Subcanal de Venta (11 = POS / 12 = APP / 31 = WEB)';


--
-- Name: COLUMN boleta_cabeceras.id_venta_catalogo_extendido_y_otro; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_venta_catalogo_extendido_y_otro IS '(0001 = Venta Catalogo Extendido / 0000 = Cualquier otra venta)';


--
-- Name: COLUMN boleta_cabeceras.id_segmento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_segmento IS 'Segmento al que se abona';


--
-- Name: COLUMN boleta_cabeceras.id_vendedor; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_vendedor IS 'Vendedor';


--
-- Name: COLUMN boleta_cabeceras.id_tienda_original; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tienda_original IS 'PK de Tienda o Locacion';


--
-- Name: COLUMN boleta_cabeceras.id_medio_de_pago_medios_de_pago; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_medio_de_pago_medios_de_pago IS 'PK de Medios de Pago';


--
-- Name: COLUMN boleta_cabeceras.id_tipo_de_descuento_al_total; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tipo_de_descuento_al_total IS 'Tipo de descuento al Total de la Boleta';


--
-- Name: COLUMN boleta_cabeceras.id_tipo_de_evento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tipo_de_evento IS 'Tipo de Evento en tabla CATALOGOS';


--
-- Name: COLUMN boleta_cabeceras.id_tipo_promocion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_tipo_promocion IS 'Tipo de Promocion (% de Monedero Id para Sistema de Monedero
 45  75
 40  61
 35   58
 30  56
 25  63
  20  69 
  15  65 
 10  72
  5   54)';


--
-- Name: COLUMN boleta_cabeceras.id_numero_centro_de_servicio; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_numero_centro_de_servicio IS 'Numero de Centro de Servicio';


--
-- Name: COLUMN boleta_cabeceras.id_status_code_activacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_status_code_activacion IS '01 Activación en Línea
 04 Activación SAF
 09 Time Out';


--
-- Name: COLUMN boleta_cabeceras.id_mensaje_cancelacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_mensaje_cancelacion IS 'ID de Mensaje de Cancelacion de tabla CATALOGOS';


--
-- Name: COLUMN boleta_cabeceras.id_direccion_seleccionada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_direccion_seleccionada IS 'ID Direccion del cliente';


--
-- Name: COLUMN boleta_cabeceras.id_cliente; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN boleta_cabeceras.id_del_servicio; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_del_servicio IS 'ID del servicio';


--
-- Name: COLUMN boleta_cabeceras.id_terminal_pos_cancelacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_terminal_pos_cancelacion IS 'Terminal de la transaccion original';


--
-- Name: COLUMN boleta_cabeceras.numero_autorizacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_autorizacion IS 'Justificado a la izquierda para numeros de autorizacion menores a la longitud del campo. Pueden tener caracteres alfanumericos.';


--
-- Name: COLUMN boleta_cabeceras.id_atg_del_cliente; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_atg_del_cliente IS 'ID_ATG que viene del Cliente';


--
-- Name: COLUMN boleta_cabeceras.cuenta_empleado; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.cuenta_empleado IS 'Numero de cuenta de empleado';


--
-- Name: COLUMN boleta_cabeceras.monto_emitido; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.monto_emitido IS 'Monto emitido';


--
-- Name: COLUMN boleta_cabeceras.codigo_facturacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.codigo_facturacion IS 'Codigo de facturacion CALCULADO';


--
-- Name: COLUMN boleta_cabeceras.cupon_informado_en_linea; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.cupon_informado_en_linea IS 'Indica si el cupon se informo en linea en un mensaje de cobro con DILISA.1 informado en linea
 0 no se informo en linea';


--
-- Name: COLUMN boleta_cabeceras.referencia_folio_agencia_de_viajes; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.referencia_folio_agencia_de_viajes IS 'Depende del Servicio.
 Ej. En agencia de viajes escribe el folio.';


--
-- Name: COLUMN boleta_cabeceras.clave_devolucion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.clave_devolucion IS 'Clave de devolucion';


--
-- Name: COLUMN boleta_cabeceras.es_cancelable; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.es_cancelable IS '1= Permite Cancelar.
 2= No permite cancelar';


--
-- Name: COLUMN boleta_cabeceras.status_code_autorizacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.status_code_autorizacion IS 'Justificado a la izquierda para numeros de autorizacion menores a la longitud del campo.';


--
-- Name: COLUMN boleta_cabeceras.precio_real; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.precio_real IS 'Precio Real';


--
-- Name: COLUMN boleta_cabeceras.codigo_barras_devolucion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.codigo_barras_devolucion IS 'Datos extraidos del codigo de barras de la venta que se esta devolviendo -
AA ano
MM mes
DD dia
TTT No. de terminal de la venta
BBBB No. de transaccion de la venta original.';


--
-- Name: COLUMN boleta_cabeceras.precio_unitario; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.precio_unitario IS 'Precio unitario';


--
-- Name: COLUMN boleta_cabeceras.numero_de_unidades; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_de_unidades IS 'Numero de unidades';


--
-- Name: COLUMN boleta_cabeceras.numero_cuenta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_cuenta IS 'Justificado con ceros a la izquierda para las cuentas de longitud menor a 16';


--
-- Name: COLUMN boleta_cabeceras.numero_evento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_evento IS 'Identificador de Evento';


--
-- Name: COLUMN boleta_cabeceras.numero_monedero; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_monedero IS 'Número de Monedero a Abonar la aportacion';


--
-- Name: COLUMN boleta_cabeceras.cuenta_del_servicio; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.cuenta_del_servicio IS 'Depende del servicio.
  
 Ej.Disposicion de efectivo lleva numero de empleado.';


--
-- Name: COLUMN boleta_cabeceras.numero_control_emision; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_control_emision IS 'No. Monedero al que se emite';


--
-- Name: COLUMN boleta_cabeceras.evento_mesa_de_regalo; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.evento_mesa_de_regalo IS 'Los primeros 2 caracteres despues de los dos puntos es el identificador del string y los siguientes 16 es el numero de Evento de Mesa de Regalos (justificado a la derecha)';


--
-- Name: COLUMN boleta_cabeceras.id_mdm_del_cliente; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_mdm_del_cliente IS 'ID_MDM que viene del Cliente';


--
-- Name: COLUMN boleta_cabeceras.codigo_cupon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.codigo_cupon IS 'Codigo de cupon, dato del codigo de barras';


--
-- Name: COLUMN boleta_cabeceras.numero_canasta_de_navidad; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_canasta_de_navidad IS 'Numero de canasta justificada a la derecha.';


--
-- Name: COLUMN boleta_cabeceras.cliente_llevo_marcaderia; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.cliente_llevo_marcaderia IS 'Indicador.
  
 1 = Se llevo la mercancia ( Lista Europea, opcion Normal )
 0 = No se llevo la mercancia el cliente (0020Lista Europea, opcion Paqueteria )';


--
-- Name: COLUMN boleta_cabeceras.numero_tarjeta_nupcial_o_bebe; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_tarjeta_nupcial_o_bebe IS 'Numero de barras de la tarjeta Nupcial o de Bebes cuando exista';


--
-- Name: COLUMN boleta_cabeceras.cuenta_dilisa; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.cuenta_dilisa IS 'Numero de cuenta a la cual se le entrego ese cupon. Es el mismo que viene en el codigo de barras.';


--
-- Name: COLUMN boleta_cabeceras.numero_boleta_cancelacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_boleta_cancelacion IS 'Número de boleta';


--
-- Name: COLUMN boleta_cabeceras.monto_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.monto_boleta IS 'Los dos ultimos digitos en la derecha indican los centavos';


--
-- Name: COLUMN boleta_cabeceras.total_cancelacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.total_cancelacion IS 'Cantidad de la cancelación';


--
-- Name: COLUMN boleta_cabeceras.precio_subtotal_neto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.precio_subtotal_neto IS 'Precio por todos los artículos/PRICEOV';


--
-- Name: COLUMN boleta_cabeceras.numero_del_documento_pedidos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.numero_del_documento_pedidos IS 'Remision / Orden de venta.';


--
-- Name: COLUMN boleta_cabeceras.fecha_modificacion_pedidos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.fecha_modificacion_pedidos IS 'Fecha de modificacion';


--
-- Name: COLUMN boleta_cabeceras.hora_modificacion_pedidos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.hora_modificacion_pedidos IS 'Hora de la modificacion';


--
-- Name: COLUMN boleta_cabeceras.estado_orden_pedidos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.estado_orden_pedidos IS 'Estado de la Orden';


--
-- Name: COLUMN boleta_cabeceras.id_atg; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_atg IS 'Identificador del Cliente en ATG';


--
-- Name: COLUMN boleta_cabeceras.id_mdm; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_cabeceras.id_mdm IS 'Identificador del Cliente en MDM';


--
-- Name: boleta_detalle_abono_tarjetas; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boleta_detalle_abono_tarjetas (
    id_linea_detalle integer NOT NULL,
    id_terminal_pos_boleta_cabeceras character varying(25) NOT NULL,
    numero_boleta_boleta_cabeceras character varying(25) NOT NULL,
    fecha_fin_transaccion_boleta_cabeceras date NOT NULL,
    hora_fin_transaccion_boleta_cabeceras time without time zone NOT NULL,
    id_tienda_origen_boleta_cabeceras character varying(25) NOT NULL,
    id_tienda_reconocimiento_boleta_cabeceras character varying(25) NOT NULL,
    numero_tarjeta character varying(50),
    total_pagado numeric(16,2),
    id_cliente_tarjeta integer
);


--
-- Name: TABLE boleta_detalle_abono_tarjetas; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boleta_detalle_abono_tarjetas IS 'Tabla que guarda 1 Abono de Tarjeta por Boleta';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.id_linea_detalle; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.id_linea_detalle IS 'PK de Boleta Detalle de Abono de Tarjeta';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.id_terminal_pos_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.id_terminal_pos_boleta_cabeceras IS 'Terminal';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.numero_boleta_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.numero_boleta_boleta_cabeceras IS 'Numero de Boleta';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.fecha_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.fecha_fin_transaccion_boleta_cabeceras IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.hora_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.hora_fin_transaccion_boleta_cabeceras IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.id_tienda_origen_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.id_tienda_origen_boleta_cabeceras IS 'Identificador de la tienda origen';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.id_tienda_reconocimiento_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.id_tienda_reconocimiento_boleta_cabeceras IS 'Identificador de la tienda de reconocimiento';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.numero_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.numero_tarjeta IS 'numero de tarjeta abonada';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.total_pagado; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.total_pagado IS 'Cantidad pagada';


--
-- Name: COLUMN boleta_detalle_abono_tarjetas.id_cliente_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_abono_tarjetas.id_cliente_tarjeta IS 'PK de Cliente Tarjetas';


--
-- Name: boleta_detalle_impuestos; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boleta_detalle_impuestos (
    id_boleta_detalle_impuestos integer NOT NULL,
    comentarios character varying(50),
    id_terminal_pos_boleta_cabeceras character varying(25),
    numero_boleta_boleta_cabeceras character varying(25),
    fecha_fin_transaccion_boleta_cabeceras date,
    hora_fin_transaccion_boleta_cabeceras time without time zone,
    id_tienda_origen_boleta_cabeceras character varying(25),
    id_tienda_reconocimiento_boleta_cabeceras character varying(25),
    id_impuesto integer,
    monto_impuesto numeric(16,2) DEFAULT 0.00,
    monto_base_impositiva numeric(16,2) DEFAULT 0.00
);


--
-- Name: TABLE boleta_detalle_impuestos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boleta_detalle_impuestos IS 'Detalle de impuestos de la Boleta';


--
-- Name: COLUMN boleta_detalle_impuestos.id_boleta_detalle_impuestos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.id_boleta_detalle_impuestos IS 'ID del Detalle de impuestos de la Boleta';


--
-- Name: COLUMN boleta_detalle_impuestos.comentarios; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.comentarios IS 'Comentarios o glosa';


--
-- Name: COLUMN boleta_detalle_impuestos.id_terminal_pos_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.id_terminal_pos_boleta_cabeceras IS 'Terminal';


--
-- Name: COLUMN boleta_detalle_impuestos.numero_boleta_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.numero_boleta_boleta_cabeceras IS 'Numero de Boleta';


--
-- Name: COLUMN boleta_detalle_impuestos.fecha_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.fecha_fin_transaccion_boleta_cabeceras IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boleta_detalle_impuestos.hora_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.hora_fin_transaccion_boleta_cabeceras IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boleta_detalle_impuestos.id_tienda_origen_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.id_tienda_origen_boleta_cabeceras IS 'Identificador de la tienda origen';


--
-- Name: COLUMN boleta_detalle_impuestos.id_tienda_reconocimiento_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.id_tienda_reconocimiento_boleta_cabeceras IS 'Identificador de la tienda de reconocimiento';


--
-- Name: COLUMN boleta_detalle_impuestos.id_impuesto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.id_impuesto IS 'ID del Impuesto';


--
-- Name: COLUMN boleta_detalle_impuestos.monto_impuesto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.monto_impuesto IS 'Monto del Impuesto ';


--
-- Name: COLUMN boleta_detalle_impuestos.monto_base_impositiva; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_impuestos.monto_base_impositiva IS 'Monto base sobre el cual se calcula el impuesto.';


--
-- Name: boleta_detalle_pagos; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boleta_detalle_pagos (
    id_linea_detalle_pagos integer NOT NULL,
    id_terminal_pos_boleta_cabeceras character varying(25) NOT NULL,
    numero_boleta_boleta_cabeceras character varying(25) NOT NULL,
    fecha_fin_transaccion_boleta_cabeceras date NOT NULL,
    hora_fin_transaccion_boleta_cabeceras time without time zone NOT NULL,
    id_tienda_origen_boleta_cabeceras character varying(25) NOT NULL,
    id_tienda_reconocimiento_boleta_cabeceras character varying(25) NOT NULL,
    id_medios_de_pago integer NOT NULL,
    dispositivo_entrada smallint,
    monto_monedero numeric(16,2) DEFAULT 0.00,
    id_cliente_tarjeta integer,
    id_cliente integer,
    numero_autorizacion character varying(50),
    tiene_cobro_1 character varying(15),
    id_segmento integer,
    tiene_cobro character varying(15),
    monto_promocion numeric(16,2) DEFAULT 0.00,
    hash2_tarjeta_usada character varying(50),
    numero_orden_soms character varying(50),
    monto_devolucion numeric(16,2) DEFAULT 0.00,
    total_pagado_otromedio_o_cambio_en_efectivo numeric(16,2) DEFAULT 0.00,
    estado_cobro character varying(15),
    plan_de_credito character varying(15),
    hash1_tarjeta_usada character varying(50),
    total_pagado_efectivo numeric(16,2) DEFAULT 0.00
);


--
-- Name: TABLE boleta_detalle_pagos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boleta_detalle_pagos IS 'Informacion de la ingesta de boletas desde POS/TLOG.txt';


--
-- Name: COLUMN boleta_detalle_pagos.id_linea_detalle_pagos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_linea_detalle_pagos IS 'PK de la tabla BOLETA_DETALLE_PAGOS';


--
-- Name: COLUMN boleta_detalle_pagos.id_terminal_pos_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_terminal_pos_boleta_cabeceras IS 'Terminal';


--
-- Name: COLUMN boleta_detalle_pagos.numero_boleta_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.numero_boleta_boleta_cabeceras IS 'Numero de Boleta';


--
-- Name: COLUMN boleta_detalle_pagos.fecha_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.fecha_fin_transaccion_boleta_cabeceras IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boleta_detalle_pagos.hora_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.hora_fin_transaccion_boleta_cabeceras IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boleta_detalle_pagos.id_tienda_origen_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_tienda_origen_boleta_cabeceras IS 'Identificador de la tienda origen';


--
-- Name: COLUMN boleta_detalle_pagos.id_tienda_reconocimiento_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_tienda_reconocimiento_boleta_cabeceras IS 'Identificador de la tienda de reconocimiento';


--
-- Name: COLUMN boleta_detalle_pagos.id_medios_de_pago; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_medios_de_pago IS 'PK de Medios de Pago';


--
-- Name: COLUMN boleta_detalle_pagos.dispositivo_entrada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.dispositivo_entrada IS 'Indica el dispositivo por el que se introdujo la cuenta. 
 1 = Teclado  7 = Fall Back
 3 = Scanner 
 4 = MSR 
 5 = PinPad
 6 = eWallet.';


--
-- Name: COLUMN boleta_detalle_pagos.monto_monedero; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.monto_monedero IS 'Monto originado por Abono directo a Monedero';


--
-- Name: COLUMN boleta_detalle_pagos.id_cliente_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_cliente_tarjeta IS 'PK de Cliente Tarjetas';


--
-- Name: COLUMN boleta_detalle_pagos.id_cliente; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN boleta_detalle_pagos.numero_autorizacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.numero_autorizacion IS 'numero autorizacion';


--
-- Name: COLUMN boleta_detalle_pagos.tiene_cobro_1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.tiene_cobro_1 IS 'Status del cobro/INDICAT1.TEN';


--
-- Name: COLUMN boleta_detalle_pagos.id_segmento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.id_segmento IS 'Identificador';


--
-- Name: COLUMN boleta_detalle_pagos.tiene_cobro; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.tiene_cobro IS 'Status del cobro/INDICAT0.TEN';


--
-- Name: COLUMN boleta_detalle_pagos.monto_promocion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.monto_promocion IS 'Monto originado por promocion';


--
-- Name: COLUMN boleta_detalle_pagos.hash2_tarjeta_usada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.hash2_tarjeta_usada IS 'HASH 2';


--
-- Name: COLUMN boleta_detalle_pagos.numero_orden_soms; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.numero_orden_soms IS 'Número de Folio ';


--
-- Name: COLUMN boleta_detalle_pagos.monto_devolucion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.monto_devolucion IS 'Monto originado por devolucion';


--
-- Name: COLUMN boleta_detalle_pagos.total_pagado_otromedio_o_cambio_en_efectivo; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.total_pagado_otromedio_o_cambio_en_efectivo IS 'Monto (o cambio en caso de efectivo)';


--
-- Name: COLUMN boleta_detalle_pagos.estado_cobro; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.estado_cobro IS 'Status del cobro';


--
-- Name: COLUMN boleta_detalle_pagos.plan_de_credito; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.plan_de_credito IS 'Plan de credito';


--
-- Name: COLUMN boleta_detalle_pagos.hash1_tarjeta_usada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.hash1_tarjeta_usada IS 'HASH 1';


--
-- Name: COLUMN boleta_detalle_pagos.total_pagado_efectivo; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalle_pagos.total_pagado_efectivo IS 'Cantidad pagada con efectivo';


--
-- Name: boleta_detalles_sku; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boleta_detalles_sku (
    id_linea_detalle integer NOT NULL,
    id_terminal_pos_boleta_cabeceras character varying(25) NOT NULL,
    numero_boleta_boleta_cabeceras character varying(25) NOT NULL,
    fecha_fin_transaccion_boleta_cabeceras date NOT NULL,
    hora_fin_transaccion_boleta_cabeceras time without time zone NOT NULL,
    id_tienda_origen_boleta_cabeceras character varying(25) NOT NULL,
    id_tienda_reconocimiento_boleta_cabeceras character varying(25) NOT NULL,
    id_impuesto integer,
    id_sku integer NOT NULL,
    sku_venta_extemporanea character varying(50),
    total_descuento numeric(16,2),
    porcentaje_descuento numeric(6,2),
    id_pep_r3 character varying(50),
    telefono_cliente_garantia_extendida character varying(50),
    precio_subtotal_neto numeric(16,2) DEFAULT 0,
    monto_por_activacion_tarjeta numeric(16,2) DEFAULT 0.00,
    fecha_compra_original date,
    codigo_aprobacion_1 character varying(50),
    id_seccion integer,
    id_tipo_de_descuento smallint,
    id_tipo_de_promocion smallint,
    producto_servicio character varying(16),
    total_descuento_sku numeric(16,2),
    monto_base_impositiva1 numeric(16,2) DEFAULT 0.00,
    monto_base_impositiva numeric(16,2) DEFAULT 0.00,
    monto_por_activacion_1 numeric(16,2) DEFAULT 0.00,
    id_tipo_de_descuento_al_total smallint,
    codigo_barras_cupon_crm_redimido character varying(50),
    id_centro_de_servicio smallint,
    referencia character varying(50),
    codigo_aprobacion character varying(50),
    monto_impuesto numeric(16,2) DEFAULT 0.00,
    precio_cobrado numeric(16,2) DEFAULT 0.00,
    numero_orden_optica character varying(50),
    orden_de_reparacion character varying(50),
    numero_telefono_o_folio character varying(50),
    upc_ean_numero_tarjeta character varying(50),
    monto_impuesto1 numeric(16,2) DEFAULT 0.00,
    codigo_barras_tarjeta_prepago character varying(50),
    id_status_code_activacion smallint,
    status_code_activacion_1 character varying(50),
    numero_comanda_restaurant character varying(50),
    numero_poliza character varying(50),
    precio_neto_antes_de_descuento numeric(16,2) DEFAULT 0.00,
    porcentaje_descuento_sku smallint,
    es_valido character varying(10),
    cantidad_articulos numeric(8,0) DEFAULT 0,
    precio_neto_despues_de_descuento numeric(16,2) DEFAULT 0.00,
    cuenta_dilisa_cupon_crm_redimido character varying(50)
);


--
-- Name: TABLE boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boleta_detalles_sku IS 'Informacion de ingesta De boletas con lineas por producto sku';


--
-- Name: COLUMN boleta_detalles_sku.id_linea_detalle; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_linea_detalle IS 'Linea del detalle de la boleta';


--
-- Name: COLUMN boleta_detalles_sku.id_terminal_pos_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_terminal_pos_boleta_cabeceras IS 'Terminal';


--
-- Name: COLUMN boleta_detalles_sku.numero_boleta_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.numero_boleta_boleta_cabeceras IS 'Numero de Boleta';


--
-- Name: COLUMN boleta_detalles_sku.fecha_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.fecha_fin_transaccion_boleta_cabeceras IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boleta_detalles_sku.hora_fin_transaccion_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.hora_fin_transaccion_boleta_cabeceras IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boleta_detalles_sku.id_tienda_origen_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_tienda_origen_boleta_cabeceras IS 'Identificador de la tienda origen';


--
-- Name: COLUMN boleta_detalles_sku.id_tienda_reconocimiento_boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_tienda_reconocimiento_boleta_cabeceras IS 'Identificador de la tienda de reconocimiento';


--
-- Name: COLUMN boleta_detalles_sku.id_impuesto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_impuesto IS 'ID del Impuesto';


--
-- Name: COLUMN boleta_detalles_sku.id_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_sku IS 'ID de Producto';


--
-- Name: COLUMN boleta_detalles_sku.sku_venta_extemporanea; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.sku_venta_extemporanea IS 'Para el caso de Venta de Garantia extendida en otra Boleta';


--
-- Name: COLUMN boleta_detalles_sku.total_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.total_descuento IS 'Monto de descuento';


--
-- Name: COLUMN boleta_detalles_sku.porcentaje_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.porcentaje_descuento IS 'Porcentaje de descuento';


--
-- Name: COLUMN boleta_detalles_sku.id_pep_r3; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_pep_r3 IS 'El numero que identifica la promoción y beneficio del sistema PEP';


--
-- Name: COLUMN boleta_detalles_sku.telefono_cliente_garantia_extendida; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.telefono_cliente_garantia_extendida IS 'Telefono solicitado al cliente en la operacion de la venta de Gar. Extendida';


--
-- Name: COLUMN boleta_detalles_sku.precio_subtotal_neto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.precio_subtotal_neto IS 'Precio por todos los artículos';


--
-- Name: COLUMN boleta_detalles_sku.monto_por_activacion_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.monto_por_activacion_tarjeta IS 'Importe en centavos de la activacion de la tarjeta registrada.';


--
-- Name: COLUMN boleta_detalles_sku.fecha_compra_original; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.fecha_compra_original IS 'DDMMAA';


--
-- Name: COLUMN boleta_detalles_sku.codigo_aprobacion_1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.codigo_aprobacion_1 IS 'Justificado a la izquierda para numeros de autorizacion menores a la longitud del campo. Pueden tener caracteres alfanumericos.';


--
-- Name: COLUMN boleta_detalles_sku.id_seccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_seccion IS 'Identificador';


--
-- Name: COLUMN boleta_detalles_sku.id_tipo_de_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_tipo_de_descuento IS 'Identificador de tipo de descuento';


--
-- Name: COLUMN boleta_detalles_sku.id_tipo_de_promocion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_tipo_de_promocion IS 'FK a Tipo de Promocion en tabla Catalogos ';


--
-- Name: COLUMN boleta_detalles_sku.producto_servicio; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.producto_servicio IS '01 = BIAANI - Club Pinguin';


--
-- Name: COLUMN boleta_detalles_sku.total_descuento_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.total_descuento_sku IS 'Cantidad correspondiente a cada uno de los descuentos al total del SKU que le precede. 8 enteros 2 decimales.';


--
-- Name: COLUMN boleta_detalles_sku.monto_base_impositiva1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.monto_base_impositiva1 IS 'Monto base sobre el cual se calcula el impuesto.';


--
-- Name: COLUMN boleta_detalles_sku.monto_base_impositiva; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.monto_base_impositiva IS 'Monto base sobre el cual se calcula el impuesto.';


--
-- Name: COLUMN boleta_detalles_sku.monto_por_activacion_1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.monto_por_activacion_1 IS 'Importe en centavos de la activacion de la tarjeta registrada.';


--
-- Name: COLUMN boleta_detalles_sku.id_tipo_de_descuento_al_total; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_tipo_de_descuento_al_total IS 'Tipo de descuento aplicado. 
 Tabla de valores 
 -01 Primer dia 
 -02 Desc. Al total (porcentaje)
 -03 Desc. Al total ($)
 -04 Tentative.';


--
-- Name: COLUMN boleta_detalles_sku.codigo_barras_cupon_crm_redimido; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.codigo_barras_cupon_crm_redimido IS 'Numero del codigo de barras impreso en el cupon CRM.
 (Cuando el beneficio sea por este concepto; sino viaja vacio )';


--
-- Name: COLUMN boleta_detalles_sku.id_centro_de_servicio; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_centro_de_servicio IS 'Numero de Centro de Servicio';


--
-- Name: COLUMN boleta_detalles_sku.referencia; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.referencia IS 'NUmero de Tienda (4), Terminal (3), NUmero Consecutivo de Ticket (5), Fecha de Compra de la Garantia Extendida (6)';


--
-- Name: COLUMN boleta_detalles_sku.codigo_aprobacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.codigo_aprobacion IS 'Justificado a la izquierda para numeros de autorizacion menores a la longitud del campo. Pueden tener caracteres alfanumericos.';


--
-- Name: COLUMN boleta_detalles_sku.monto_impuesto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.monto_impuesto IS 'Monto del impuesto';


--
-- Name: COLUMN boleta_detalles_sku.precio_cobrado; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.precio_cobrado IS 'Precio o importe';


--
-- Name: COLUMN boleta_detalles_sku.numero_orden_optica; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.numero_orden_optica IS 'orden optica';


--
-- Name: COLUMN boleta_detalles_sku.orden_de_reparacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.orden_de_reparacion IS 'Numero de Orden de Reparacion justificada a la derecha.';


--
-- Name: COLUMN boleta_detalles_sku.numero_telefono_o_folio; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.numero_telefono_o_folio IS 'Numero de Telefono al cual se abono. Si es abono NIP. Num. De Folio';


--
-- Name: COLUMN boleta_detalles_sku.upc_ean_numero_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.upc_ean_numero_tarjeta IS '1-12 UPC(Primeros 12 del codigo 128) , Justificados a la izquierda
 13-31 No. De Tarjeta ( ultimos 18 de codigo 128 ), Justificados a la izquierda';


--
-- Name: COLUMN boleta_detalles_sku.monto_impuesto1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.monto_impuesto1 IS 'Monto del impuesto';


--
-- Name: COLUMN boleta_detalles_sku.codigo_barras_tarjeta_prepago; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.codigo_barras_tarjeta_prepago IS 'Graba las barras de la tarjeta prepagada tal como se incluye en el plastico del producto.';


--
-- Name: COLUMN boleta_detalles_sku.id_status_code_activacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.id_status_code_activacion IS '01 Activacion en Linea
 04 Activacion SAF
 09 Time Out';


--
-- Name: COLUMN boleta_detalles_sku.status_code_activacion_1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.status_code_activacion_1 IS '01 Activacion en Linea
 04 Activacion SAF
 09 Time Out';


--
-- Name: COLUMN boleta_detalles_sku.numero_comanda_restaurant; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.numero_comanda_restaurant IS 'Numero de comanda justificada a la derecha.';


--
-- Name: COLUMN boleta_detalles_sku.numero_poliza; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.numero_poliza IS 'numero de poliza/';


--
-- Name: COLUMN boleta_detalles_sku.precio_neto_antes_de_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.precio_neto_antes_de_descuento IS 'Precio del artículo (neto sin descuentos)';


--
-- Name: COLUMN boleta_detalles_sku.porcentaje_descuento_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.porcentaje_descuento_sku IS 'Identifica el porcentaje de beneficio otorgado al artículo.';


--
-- Name: COLUMN boleta_detalles_sku.es_valido; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.es_valido IS 'Indicador si el SKU es válido o no/INDICAT0.MES';


--
-- Name: COLUMN boleta_detalles_sku.cantidad_articulos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.cantidad_articulos IS 'Cantidad de articulos';


--
-- Name: COLUMN boleta_detalles_sku.precio_neto_despues_de_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.precio_neto_despues_de_descuento IS 'El importe del precio neto calculado. Considerando descuentos y rebajas al Sku, prorrateo de descuentos y rebajas al total.';


--
-- Name: COLUMN boleta_detalles_sku.cuenta_dilisa_cupon_crm_redimido; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku.cuenta_dilisa_cupon_crm_redimido IS 'Numero de cuenta DILISA incluido en el codigo de barras del cupon CRM.
 ( Cuando el beneficio sea por este concepto; sino viaja vacio )';


--
-- Name: boleta_detalles_sku_descuentos; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boleta_detalles_sku_descuentos (
    id_linea_detalle_boleta_detalles_sku integer NOT NULL,
    id_terminal_pos_boleta_detalles_sku character varying(25) NOT NULL,
    numero_boleta_boleta_detalles_sku character varying(25) NOT NULL,
    fecha_fin_transaccion_boleta_detalles_sku date NOT NULL,
    hora_fin_transaccion_boleta_detalles_sku time without time zone NOT NULL,
    id_tienda_origen_boleta_detalles_sku character varying(25) NOT NULL,
    id_tienda_reconocimiento_boleta_detalles_sku character varying(25) NOT NULL,
    id_descuento integer NOT NULL,
    id_tipo_de_descuento smallint,
    id_tipo_de_descuento_al_total smallint,
    precio_neto_antes_de_descuento numeric(16,2) DEFAULT 0.00,
    precio_neto_despues_de_descuento numeric(16,2) DEFAULT 0.00,
    porcentaje_descuento numeric(6,2),
    total_descuento_sku numeric(16,2),
    porcentaje_descuento_sku smallint
);


--
-- Name: COLUMN boleta_detalles_sku_descuentos.id_linea_detalle_boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.id_linea_detalle_boleta_detalles_sku IS 'PK de Tabla de los Descuentos para Boletas SKU';


--
-- Name: COLUMN boleta_detalles_sku_descuentos.id_terminal_pos_boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.id_terminal_pos_boleta_detalles_sku IS 'PK de Tabla de los Descuentos para Boletas SKU';


--
-- Name: COLUMN boleta_detalles_sku_descuentos.numero_boleta_boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.numero_boleta_boleta_detalles_sku IS 'PK de Tabla de los Descuentos para Boletas SKU';


--
-- Name: COLUMN boleta_detalles_sku_descuentos.fecha_fin_transaccion_boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.fecha_fin_transaccion_boleta_detalles_sku IS 'PK de Tabla de los Descuentos para Boletas SKU';


--
-- Name: COLUMN boleta_detalles_sku_descuentos.hora_fin_transaccion_boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.hora_fin_transaccion_boleta_detalles_sku IS 'PK de Tabla de los Descuentos para Boletas SKU';


--
-- Name: COLUMN boleta_detalles_sku_descuentos.id_tienda_origen_boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.id_tienda_origen_boleta_detalles_sku IS 'PK de Tabla de los Descuentos para Boletas SKU';


--
-- Name: COLUMN boleta_detalles_sku_descuentos.id_tienda_reconocimiento_boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.id_tienda_reconocimiento_boleta_detalles_sku IS 'PK de Tabla de los Descuentos para Boletas SKU';


--
-- Name: COLUMN boleta_detalles_sku_descuentos.id_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boleta_detalles_sku_descuentos.id_descuento IS 'PK de Descuentos';


--
-- Name: boletas_cifra_de_control; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boletas_cifra_de_control (
    id_boletas_cifra_de_control integer,
    fecha_tlog date,
    centro character varying(100),
    id_tipo_de_transaccion integer,
    tipo_de_transaccion character varying(100),
    total_de_las_transacciones numeric(36,2) DEFAULT 0.00
);


--
-- Name: TABLE boletas_cifra_de_control; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boletas_cifra_de_control IS 'BOLETAS_CIFRA_DE_CONTROL';


--
-- Name: COLUMN boletas_cifra_de_control.id_boletas_cifra_de_control; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_cifra_de_control.id_boletas_cifra_de_control IS 'Autoincremental';


--
-- Name: COLUMN boletas_cifra_de_control.fecha_tlog; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_cifra_de_control.fecha_tlog IS 'Fecha de tlog';


--
-- Name: COLUMN boletas_cifra_de_control.centro; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_cifra_de_control.centro IS 'Centro';


--
-- Name: COLUMN boletas_cifra_de_control.id_tipo_de_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_cifra_de_control.id_tipo_de_transaccion IS 'ID Tipo de Transaccion';


--
-- Name: COLUMN boletas_cifra_de_control.tipo_de_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_cifra_de_control.tipo_de_transaccion IS 'Tipo de Transaccion';


--
-- Name: COLUMN boletas_cifra_de_control.total_de_las_transacciones; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_cifra_de_control.total_de_las_transacciones IS 'Total de transacciones';


--
-- Name: boletas_en_buzon; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boletas_en_buzon (
    id_terminal_pos character varying(25) NOT NULL,
    numero_boleta character varying(25) NOT NULL,
    fecha_transaccion date NOT NULL,
    hora_transaccion time without time zone NOT NULL,
    id_tienda_original character varying(25) NOT NULL,
    id_tienda character varying(25) NOT NULL,
    monto_total_sku numeric(16,2) DEFAULT 0.00,
    total_rebajas_sku_boleta numeric(16,2) DEFAULT 0.00,
    estado_transaccion character varying(15),
    total_boleta numeric(16,2) DEFAULT 0.00,
    numero_transaccion integer,
    numero_de_unidades numeric(16,0),
    numero_celular character varying(50),
    numero_boleta_original character varying(25),
    monto_boleta numeric(16,2) DEFAULT 0.00,
    precio_real numeric(16,2) DEFAULT 0.00,
    monto_emitido numeric(16,2) DEFAULT 0.00,
    precio_unitario numeric(16,2) DEFAULT 0.00,
    codigo_facturacion character varying(50),
    numero_evento character varying(50),
    total_descuento_boleta numeric(16,2),
    total_antes_de_impuesto numeric(16,2) DEFAULT 0.00,
    clave_devolucion character varying(50),
    ticket character varying(100),
    id_empresa integer,
    id_vendedor integer,
    id_cliente integer,
    id_tipo_transaccion integer,
    fecha_inicio_transaccion date,
    id_tipo_descuento integer,
    descripcion_estado_transaccion character varying(100),
    bandera_id_trx_de_invitado character varying(25),
    id_cliente_promo_crm integer,
    id_cliente_kybela integer,
    numero_entrega_soms character varying(50),
    fecha_original_buzon date,
    promo_itunes character varying(50),
    hash_tarjeta character varying(50),
    fecha_fin_transaccion date,
    id_canal integer,
    id_dispositivo_venta integer,
    bandera_original_buzon character varying(25),
    id_tienda_recepcion integer,
    bandera_ejecucion_token character varying(25),
    id_cliente_original integer,
    bandera_id_trx_de_mercancia character varying(25),
    bandera_id_trx_con_monedero character varying(25),
    fecha_original_compra date,
    id_operacion integer,
    certificado_itunes character varying(100),
    numero_cuenta_subrogada character varying(50),
    promocion_crm character varying(100),
    bandera_id_trx_de_buzon character varying(25),
    bandera_id_trx_con_mesa_de_regalo character varying(25),
    id_atg character varying(200) NOT NULL,
    id_mdm character varying(200) NOT NULL,
    numero_cuenta character varying(100),
    id_terminal_pos_original integer
);


--
-- Name: TABLE boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boletas_en_buzon IS 'Tablas de Boletas de la Ingesta de TLOG';


--
-- Name: COLUMN boletas_en_buzon.id_terminal_pos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_terminal_pos IS 'Terminal';


--
-- Name: COLUMN boletas_en_buzon.numero_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_boleta IS 'Numero de Boleta';


--
-- Name: COLUMN boletas_en_buzon.fecha_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.fecha_transaccion IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boletas_en_buzon.hora_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.hora_transaccion IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boletas_en_buzon.id_tienda_original; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_tienda_original IS 'Identificador de la tienda original';


--
-- Name: COLUMN boletas_en_buzon.id_tienda; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_tienda IS 'Identificador de la tienda';


--
-- Name: COLUMN boletas_en_buzon.monto_total_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.monto_total_sku IS 'Monto Total de la Boleta de todos los SKU';


--
-- Name: COLUMN boletas_en_buzon.total_rebajas_sku_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.total_rebajas_sku_boleta IS 'Monto Total Descuentos de todos los SKU';


--
-- Name: COLUMN boletas_en_buzon.estado_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.estado_transaccion IS 'INDICAT0 con estado de transaccion';


--
-- Name: COLUMN boletas_en_buzon.total_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.total_boleta IS 'Total de la Boleta';


--
-- Name: COLUMN boletas_en_buzon.numero_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_transaccion IS 'NUMSTRIN del TLOG';


--
-- Name: COLUMN boletas_en_buzon.numero_de_unidades; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_de_unidades IS 'Numero de unidades';


--
-- Name: COLUMN boletas_en_buzon.numero_celular; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_celular IS 'Numero de celular';


--
-- Name: COLUMN boletas_en_buzon.numero_boleta_original; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_boleta_original IS 'Número de boleta ORIGINAL';


--
-- Name: COLUMN boletas_en_buzon.monto_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.monto_boleta IS 'Los dos ultimos digitos en la derecha indican los centavos';


--
-- Name: COLUMN boletas_en_buzon.precio_real; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.precio_real IS 'Precio Real';


--
-- Name: COLUMN boletas_en_buzon.monto_emitido; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.monto_emitido IS 'Monto emitido';


--
-- Name: COLUMN boletas_en_buzon.precio_unitario; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.precio_unitario IS 'Precio unitario';


--
-- Name: COLUMN boletas_en_buzon.codigo_facturacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.codigo_facturacion IS 'Codigo de facturacion CALCULADO';


--
-- Name: COLUMN boletas_en_buzon.numero_evento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_evento IS 'Identificador de Evento';


--
-- Name: COLUMN boletas_en_buzon.total_descuento_boleta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.total_descuento_boleta IS 'TOTAL_DESCUENTO_BOLETA';


--
-- Name: COLUMN boletas_en_buzon.total_antes_de_impuesto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.total_antes_de_impuesto IS 'TOTAL_ANTES_DE_IMPUESTO';


--
-- Name: COLUMN boletas_en_buzon.clave_devolucion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.clave_devolucion IS 'Clave de devolucion';


--
-- Name: COLUMN boletas_en_buzon.ticket; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.ticket IS 'Ticket trx de sistema POS';


--
-- Name: COLUMN boletas_en_buzon.id_empresa; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_empresa IS 'Identificador';


--
-- Name: COLUMN boletas_en_buzon.id_vendedor; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_vendedor IS 'FK de VENDEDORES';


--
-- Name: COLUMN boletas_en_buzon.id_cliente; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN boletas_en_buzon.id_tipo_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_tipo_transaccion IS 'FK de CATALOGOS TIPOS_DE_TRANSACCION';


--
-- Name: COLUMN boletas_en_buzon.fecha_inicio_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.fecha_inicio_transaccion IS 'FECHA_INICIO ';


--
-- Name: COLUMN boletas_en_buzon.id_tipo_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_tipo_descuento IS 'FK a CATALOGOS';


--
-- Name: COLUMN boletas_en_buzon.descripcion_estado_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.descripcion_estado_transaccion IS 'DESCRIPCION_ESTADO_TRANSACCION';


--
-- Name: COLUMN boletas_en_buzon.bandera_id_trx_de_invitado; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.bandera_id_trx_de_invitado IS 'BANDERA_ID_TRX_DE_INVITADO';


--
-- Name: COLUMN boletas_en_buzon.id_cliente_promo_crm; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_cliente_promo_crm IS 'ID_CLIENTE_PROMO_CRM';


--
-- Name: COLUMN boletas_en_buzon.id_cliente_kybela; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_cliente_kybela IS 'ID_CLIENTE_KYBELA';


--
-- Name: COLUMN boletas_en_buzon.fecha_original_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.fecha_original_buzon IS 'FECHA_ORIGINAL_BUZON';


--
-- Name: COLUMN boletas_en_buzon.promo_itunes; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.promo_itunes IS 'PROMO_ITUNES';


--
-- Name: COLUMN boletas_en_buzon.hash_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.hash_tarjeta IS 'HASH tarjeta';


--
-- Name: COLUMN boletas_en_buzon.fecha_fin_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.fecha_fin_transaccion IS 'FECHA_FIN';


--
-- Name: COLUMN boletas_en_buzon.id_canal; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_canal IS 'ID_CANAL';


--
-- Name: COLUMN boletas_en_buzon.id_dispositivo_venta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_dispositivo_venta IS 'ID_DISPOSITIVO_VENTA';


--
-- Name: COLUMN boletas_en_buzon.bandera_original_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.bandera_original_buzon IS 'BANDERA_ORIGINAL_BUZON';


--
-- Name: COLUMN boletas_en_buzon.id_tienda_recepcion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_tienda_recepcion IS 'PK de Tienda o Locacion';


--
-- Name: COLUMN boletas_en_buzon.bandera_ejecucion_token; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.bandera_ejecucion_token IS 'BANDERA_EJECUCION_TOKEN';


--
-- Name: COLUMN boletas_en_buzon.id_cliente_original; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_cliente_original IS 'ID_CLIENTE_ORIGINAL';


--
-- Name: COLUMN boletas_en_buzon.bandera_id_trx_de_mercancia; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.bandera_id_trx_de_mercancia IS 'BANDERA_ID_TRX_DE_MERCANCIA';


--
-- Name: COLUMN boletas_en_buzon.bandera_id_trx_con_monedero; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.bandera_id_trx_con_monedero IS 'BANDERA_ID_TRX_CON_MONEDERO';


--
-- Name: COLUMN boletas_en_buzon.fecha_original_compra; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.fecha_original_compra IS 'FECHA_ORIGINAL_COMPRA';


--
-- Name: COLUMN boletas_en_buzon.id_operacion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_operacion IS 'ID_OPERACION';


--
-- Name: COLUMN boletas_en_buzon.certificado_itunes; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.certificado_itunes IS 'CERTIFICADO_ITUNES';


--
-- Name: COLUMN boletas_en_buzon.numero_cuenta_subrogada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_cuenta_subrogada IS 'NUMERO CUENTA SUBROGADA';


--
-- Name: COLUMN boletas_en_buzon.promocion_crm; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.promocion_crm IS 'Promoción de CRM';


--
-- Name: COLUMN boletas_en_buzon.bandera_id_trx_de_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.bandera_id_trx_de_buzon IS 'BANDERA_ID_TRX_DE_BUZON';


--
-- Name: COLUMN boletas_en_buzon.bandera_id_trx_con_mesa_de_regalo; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.bandera_id_trx_con_mesa_de_regalo IS 'BANDERA_ID_TRX_CON_MESA_DE_REGALO';


--
-- Name: COLUMN boletas_en_buzon.id_atg; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_atg IS 'Identificador del Cliente en ATG';


--
-- Name: COLUMN boletas_en_buzon.id_mdm; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_mdm IS 'Identificador del Cliente en MDM';


--
-- Name: COLUMN boletas_en_buzon.numero_cuenta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.numero_cuenta IS 'Numero de la cuenta';


--
-- Name: COLUMN boletas_en_buzon.id_terminal_pos_original; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon.id_terminal_pos_original IS 'ID de Empresa';


--
-- Name: boletas_en_buzon_detalle_pagos; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boletas_en_buzon_detalle_pagos (
    id_linea_de_detalle smallint NOT NULL,
    id_terminal_pos_boletas_en_buzon character varying(25) NOT NULL,
    numero_boleta_boletas_en_buzon character varying(25) NOT NULL,
    fecha_transaccion_boletas_en_buzon date NOT NULL,
    hora_transaccion_boletas_en_buzon time without time zone NOT NULL,
    id_tienda_original_boletas_en_buzon character varying(25) NOT NULL,
    id_tienda_boletas_en_buzon character varying(25) NOT NULL,
    id_terminal_pos integer,
    id_cliente_clientes integer NOT NULL,
    id_tipo_transaccion smallint,
    bandera_ejecucion_token character varying(100),
    id_tienda integer,
    id_tipo_de_pago integer,
    numero_de_cuenta character varying(50),
    id_empresa integer,
    id_naturaleza_de_emision_de_tarjeta character varying(50),
    numero_de_ticket character varying(50),
    numero_de_renglon character varying(50),
    id_medio_ingreso_clave_de_tarjeta character varying(50),
    monto_impuesto1 numeric(16,2) DEFAULT 0.00,
    monto_impuesto2 numeric(16,2) DEFAULT 0.00,
    estado_transaccion character varying(25),
    boleta_original_de_buzon character varying(100),
    id_tienda_recepcion integer,
    id_plan integer,
    id_vendedor integer,
    id_banco integer,
    numero_autorizacion_bancaria character varying(50),
    total_transaccion numeric(16,2) DEFAULT 0.00,
    monto_impuesto4 numeric(16,2) DEFAULT 0.00,
    numero_tarjeta character varying(100),
    id_canal integer,
    id_dispositivo_entrada integer,
    costo_de_msi numeric(16,2) DEFAULT 0.00,
    total_antes_de_impuesto numeric(16,2) DEFAULT 0.00,
    llave_subrogada_cuenta_enmascarada character varying(100),
    fecha date,
    hora time without time zone,
    monto_impuesto3 numeric(16,2) DEFAULT 0.00,
    hash_tarjeta character varying(100),
    bandera_transaccion_es_de_buzon character varying(100),
    llave_subrogada_tarjeta_enmascarada character varying(50),
    fecha_original_de_buzon date
);


--
-- Name: TABLE boletas_en_buzon_detalle_pagos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boletas_en_buzon_detalle_pagos IS 'Pagos de BOLETAS_EN_BUZON';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_linea_de_detalle; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_linea_de_detalle IS 'Linea de Detalle Pagos BOLETAS_EN_BUZON';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_terminal_pos_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_terminal_pos_boletas_en_buzon IS 'Terminal';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.numero_boleta_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.numero_boleta_boletas_en_buzon IS 'Numero de Boleta';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.fecha_transaccion_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.fecha_transaccion_boletas_en_buzon IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.hora_transaccion_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.hora_transaccion_boletas_en_buzon IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_tienda_original_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_tienda_original_boletas_en_buzon IS 'Identificador de la tienda original';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_tienda_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_tienda_boletas_en_buzon IS 'Identificador de la tienda';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_terminal_pos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_terminal_pos IS 'Identificador';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_cliente_clientes; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_cliente_clientes IS 'PK de la tabla Clientes';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_tipo_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_tipo_transaccion IS 'Clave de tipo de transacción';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.bandera_ejecucion_token; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.bandera_ejecucion_token IS 'Bandera de ejecucion de token';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_tienda; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_tienda IS 'PK de Tienda o Locacion';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_tipo_de_pago; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_tipo_de_pago IS 'Clave de tipo de pago';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.numero_de_cuenta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.numero_de_cuenta IS 'NUMERO_DE_CUENTA';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_empresa; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_empresa IS 'Identificador de la Empresa';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_naturaleza_de_emision_de_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_naturaleza_de_emision_de_tarjeta IS 'Clave de la naturaleza de emision de la tarjeta';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.numero_de_ticket; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.numero_de_ticket IS 'Numero de ticket';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.numero_de_renglon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.numero_de_renglon IS 'Numero de renglon';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_medio_ingreso_clave_de_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_medio_ingreso_clave_de_tarjeta IS 'Clave de medio de ingreso de la tarjeta';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.monto_impuesto1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.monto_impuesto1 IS 'Impuesto de IVA';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.monto_impuesto2; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.monto_impuesto2 IS 'Importe del impuesto IEP';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.estado_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.estado_transaccion IS 'Status de la transaccion';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.boleta_original_de_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.boleta_original_de_buzon IS 'Boleta original de buzon';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_tienda_recepcion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_tienda_recepcion IS 'ID de Tiendas en CATALOGOS';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_plan; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_plan IS 'Clave de plan';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_vendedor; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_vendedor IS 'Clave de vendedor';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_banco; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_banco IS 'Clave del Banco';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.numero_autorizacion_bancaria; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.numero_autorizacion_bancaria IS 'Numero de autorización bancaria';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.total_transaccion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.total_transaccion IS 'Importe de transacción';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.monto_impuesto4; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.monto_impuesto4 IS 'Importe del impuesto EMI';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.numero_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.numero_tarjeta IS 'NUMERO_TARJETA';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_canal; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_canal IS 'ID del Canal en tabla CATALOGOS';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.id_dispositivo_entrada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.id_dispositivo_entrada IS 'ID Dispositivo de Venta de tabla CATALOGOS';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.costo_de_msi; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.costo_de_msi IS 'Costo de MSI';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.total_antes_de_impuesto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.total_antes_de_impuesto IS 'Importe sin impuesto';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.llave_subrogada_cuenta_enmascarada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.llave_subrogada_cuenta_enmascarada IS 'Llave subrogada cuando la cuenta esta enmascarada';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.fecha; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.fecha IS 'Fecha Boleta';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.hora; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.hora IS 'Hora de la boleta';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.monto_impuesto3; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.monto_impuesto3 IS 'Importe del Impuesto Suntuario';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.hash_tarjeta; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.hash_tarjeta IS 'HASH tarjeta';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.bandera_transaccion_es_de_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.bandera_transaccion_es_de_buzon IS 'Bandera de identifcacion se la transaccion es de buzon';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.llave_subrogada_tarjeta_enmascarada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.llave_subrogada_tarjeta_enmascarada IS 'Llave subrogada cuando la tarjeta esta enmascarada';


--
-- Name: COLUMN boletas_en_buzon_detalle_pagos.fecha_original_de_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_pagos.fecha_original_de_buzon IS 'Fecha original de buzon';


--
-- Name: boletas_en_buzon_detalle_sku; Type: TABLE; Schema: boletas; Owner: -
--

CREATE TABLE boletas.boletas_en_buzon_detalle_sku (
    id_linea_de_detalle smallint NOT NULL,
    id_terminal_pos_boletas_en_buzon character varying(25) NOT NULL,
    numero_boleta_boletas_en_buzon character varying(25) NOT NULL,
    fecha_transaccion_boletas_en_buzon date NOT NULL,
    hora_transaccion_boletas_en_buzon time without time zone NOT NULL,
    id_tienda_original_boletas_en_buzon character varying(25) NOT NULL,
    id_tienda_boletas_en_buzon character varying(25) NOT NULL,
    numero_orden_optica character varying(100),
    cantidad_articulos numeric(16,0) DEFAULT 0,
    total_pagado_real numeric(16,2) DEFAULT 0.00,
    clave_homologada character varying(50),
    monto_rebaja numeric(16,2) DEFAULT 0.00,
    precio_actual_sku numeric(16,2) DEFAULT 0.00,
    monto_descuento numeric(16,2) DEFAULT 0.00,
    monto_impuesto numeric(16,2) DEFAULT 0.00,
    monto_impuesto1 numeric(16,2) DEFAULT 0.00,
    monto_impuesto2 numeric(16,2) DEFAULT 0.00,
    monto_impuesto3 numeric(16,2),
    total_por_sku numeric(16,2) DEFAULT 0.00,
    id_motivo_devolucion integer NOT NULL,
    id_sku integer
);


--
-- Name: TABLE boletas_en_buzon_detalle_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON TABLE boletas.boletas_en_buzon_detalle_sku IS 'Tabla BOLETAS_EN_BUZON_DETALLE_SKU';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.id_linea_de_detalle; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.id_linea_de_detalle IS 'ID_LINEA_DETALLE';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.id_terminal_pos_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.id_terminal_pos_boletas_en_buzon IS 'Terminal';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.numero_boleta_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.numero_boleta_boletas_en_buzon IS 'Numero de Boleta';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.fecha_transaccion_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.fecha_transaccion_boletas_en_buzon IS 'Fecha fin de la transaccion';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.hora_transaccion_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.hora_transaccion_boletas_en_buzon IS 'Hora fin de la transaccion';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.id_tienda_original_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.id_tienda_original_boletas_en_buzon IS 'Identificador de la tienda original';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.id_tienda_boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.id_tienda_boletas_en_buzon IS 'Identificador de la tienda';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.numero_orden_optica; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.numero_orden_optica IS 'ORDEN OPTICA';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.cantidad_articulos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.cantidad_articulos IS 'Cantidad de skus';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.total_pagado_real; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.total_pagado_real IS 'Precio Real Pagado';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.clave_homologada; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.clave_homologada IS 'Clave de Producto';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.monto_rebaja; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.monto_rebaja IS 'Importe de Rebaja';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.precio_actual_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.precio_actual_sku IS 'Precio Actual de Producto';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.monto_descuento; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.monto_descuento IS 'Importe del descuento';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.monto_impuesto; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.monto_impuesto IS 'Impuesto de IVA';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.monto_impuesto1; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.monto_impuesto1 IS 'Importe del impuesto IEP';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.monto_impuesto2; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.monto_impuesto2 IS 'Importe del Impuesto Suntuario';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.monto_impuesto3; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.monto_impuesto3 IS 'Importe del Impuesto EST';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.total_por_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.total_por_sku IS 'Importe total por SKU';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.id_motivo_devolucion; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.id_motivo_devolucion IS 'Identificador';


--
-- Name: COLUMN boletas_en_buzon_detalle_sku.id_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON COLUMN boletas.boletas_en_buzon_detalle_sku.id_sku IS 'ID de la tabla Productos';


--
-- Name: seq_boletas_cifra_de_control_pk; Type: SEQUENCE; Schema: boletas; Owner: -
--

CREATE SEQUENCE boletas.seq_boletas_cifra_de_control_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_boletas_cifra_de_control_pk; Type: SEQUENCE OWNED BY; Schema: boletas; Owner: -
--

ALTER SEQUENCE boletas.seq_boletas_cifra_de_control_pk OWNED BY boletas.boletas_cifra_de_control.id_boletas_cifra_de_control;


--
-- Name: cliente_atg; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_atg (
    id_cliente_atg integer NOT NULL,
    id_atg character varying(25),
    sistema_fuente_atg character varying(100),
    id_cliente integer NOT NULL,
    hash_1 character varying(100),
    id_tipo_tarjeta character varying(50),
    realm_id character varying(50),
    baja_de_tarjeta character varying(50),
    id_facebook character varying(200),
    id_sistema_operativo_dispositivo integer,
    id_apple character varying(100),
    alta_de_tarjeta character varying(50)
);


--
-- Name: TABLE cliente_atg; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_atg IS 'tabla de Clientes ATG';


--
-- Name: COLUMN cliente_atg.id_cliente_atg; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.id_cliente_atg IS 'PK de la tabla Clientes ATG';


--
-- Name: COLUMN cliente_atg.id_atg; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.id_atg IS 'Id del sistema  ATG';


--
-- Name: COLUMN cliente_atg.sistema_fuente_atg; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.sistema_fuente_atg IS 'Sistema Fuente (ATG), (SATG)';


--
-- Name: COLUMN cliente_atg.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_atg.hash_1; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.hash_1 IS 'Tarjeta (hash) y en internas la cuenta en claro';


--
-- Name: COLUMN cliente_atg.id_tipo_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.id_tipo_tarjeta IS 'REALM_ID (identificador suburbia, o vacio para Liverpool, WS, GAP, etc)';


--
-- Name: COLUMN cliente_atg.realm_id; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.realm_id IS 'REALM_ID (identificador suburbia, o vacio para Liverpool, WS, GAP, etc)';


--
-- Name: COLUMN cliente_atg.baja_de_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.baja_de_tarjeta IS 'BAJA DE TARJETA';


--
-- Name: COLUMN cliente_atg.id_facebook; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.id_facebook IS 'ID_FACEBOOK';


--
-- Name: COLUMN cliente_atg.id_sistema_operativo_dispositivo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.id_sistema_operativo_dispositivo IS 'Identificador';


--
-- Name: COLUMN cliente_atg.id_apple; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.id_apple IS 'ID_APPLE';


--
-- Name: COLUMN cliente_atg.alta_de_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_atg.alta_de_tarjeta IS 'ALTA DE TARJETA';


--
-- Name: cliente_direcciones; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_direcciones (
    id_direccion_del_cliente integer NOT NULL,
    descripcion_direccion character varying(50),
    id_cliente integer,
    es_billing_address character varying,
    baja_domicilio character varying(50),
    bandera_termino character varying(15),
    es_shipping_address character varying,
    fecha_de_operacion date,
    salida_tipo character varying(25),
    fecha_actualizacion date,
    tipo_de_direccion character varying(100),
    calle character varying(100),
    direccion_4 character varying(100),
    id_colonia integer,
    id_municipio integer,
    id_estado_del_pais integer,
    id_tipo_direccion_cliente integer,
    id_entidad_federativa integer,
    id_sistema_origen integer,
    id_pais integer,
    direccion_2 character varying(100),
    codigo_postal character varying(8),
    salida_direccion_3 character varying(100),
    bandera_emcccp character varying(15),
    token_calle character varying(25),
    codigo_zip character varying(100),
    edificio character varying(15),
    fecha_creacion date,
    id_distrito integer,
    id_provincia integer,
    id_condado integer,
    bandera_calle character varying(15),
    direccion_5 character varying(100),
    salida_id_distrito integer,
    salida_id_provincia integer,
    salida_id_estado_del_pais integer,
    salida_id_condado integer,
    salida_direccion_2 character varying(100),
    salida_direccion_4 character varying(100),
    salida_direccion_5 character varying(100),
    entre_calle_1 character varying(100),
    numero_exterior character varying(15),
    alias_de_direccion character varying(100),
    numero_interior character varying(15),
    id_operacion character varying(100),
    salida_codigo_zip character varying(25),
    salida_numero_exterior character varying(50),
    salida_calle character varying(100),
    direccion_3 character varying(100),
    id_transaccion character varying(100),
    entre_calle_2 character varying(100),
    esta_borrado character varying,
    id_cliente_destinatario integer
);


--
-- Name: TABLE cliente_direcciones; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_direcciones IS 'Direcciones del Cliente';


--
-- Name: COLUMN cliente_direcciones.id_direccion_del_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_direccion_del_cliente IS 'ID Direccion del cliente';


--
-- Name: COLUMN cliente_direcciones.descripcion_direccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.descripcion_direccion IS 'Descripcion';


--
-- Name: COLUMN cliente_direcciones.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_direcciones.es_billing_address; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.es_billing_address IS 'ES_BILLING_ADDRESS (S/N)';


--
-- Name: COLUMN cliente_direcciones.baja_domicilio; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.baja_domicilio IS 'BAJA DE DOMICILIO - CLIENTE ATG';


--
-- Name: COLUMN cliente_direcciones.bandera_termino; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.bandera_termino IS 'FLAG_ENDING';


--
-- Name: COLUMN cliente_direcciones.es_shipping_address; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.es_shipping_address IS 'ES_SHIPPING_ADDRESS (S/N)';


--
-- Name: COLUMN cliente_direcciones.fecha_de_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.fecha_de_operacion IS 'DATE_OPERATION';


--
-- Name: COLUMN cliente_direcciones.salida_tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_tipo IS 'OUT_TYPE';


--
-- Name: COLUMN cliente_direcciones.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_direcciones.tipo_de_direccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.tipo_de_direccion IS 'IN_TYPE';


--
-- Name: COLUMN cliente_direcciones.calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.calle IS 'Calle destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones.direccion_4; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.direccion_4 IS 'IN_ADDR_4 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.id_colonia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_colonia IS 'ID_COLONIA';


--
-- Name: COLUMN cliente_direcciones.id_municipio; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_municipio IS 'ID_MUNICIPIO';


--
-- Name: COLUMN cliente_direcciones.id_estado_del_pais; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_estado_del_pais IS 'ID_ESTADO_DEL_PAIS';


--
-- Name: COLUMN cliente_direcciones.id_tipo_direccion_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_tipo_direccion_cliente IS 'TIPO_DIRECCION_CLIENTE';


--
-- Name: COLUMN cliente_direcciones.id_entidad_federativa; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_entidad_federativa IS 'ENTIDAD_FEDERATIVA';


--
-- Name: COLUMN cliente_direcciones.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_sistema_origen IS 'SISTEMA_ORIGEN';


--
-- Name: COLUMN cliente_direcciones.id_pais; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_pais IS 'PAIS';


--
-- Name: COLUMN cliente_direcciones.direccion_2; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.direccion_2 IS 'IN_ADDR_2 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.codigo_postal; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.codigo_postal IS 'Código Postal destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones.salida_direccion_3; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_direccion_3 IS 'OUT_ADDR_3 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.bandera_emcccp; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.bandera_emcccp IS 'FLAG_EMCCCP';


--
-- Name: COLUMN cliente_direcciones.token_calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.token_calle IS 'TOKEN_STREET';


--
-- Name: COLUMN cliente_direcciones.codigo_zip; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.codigo_zip IS 'IN_ZIPCODE MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.edificio; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.edificio IS 'Edificio';


--
-- Name: COLUMN cliente_direcciones.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_direcciones.id_distrito; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_distrito IS 'DISTRITO - MDM';


--
-- Name: COLUMN cliente_direcciones.id_provincia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_provincia IS 'PROVINCIA - MDM';


--
-- Name: COLUMN cliente_direcciones.id_condado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_condado IS 'COUNTY';


--
-- Name: COLUMN cliente_direcciones.bandera_calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.bandera_calle IS 'FLAG_STREET';


--
-- Name: COLUMN cliente_direcciones.direccion_5; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.direccion_5 IS 'IN_ADDR_5';


--
-- Name: COLUMN cliente_direcciones.salida_id_distrito; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_id_distrito IS 'OUT_DISTRIC';


--
-- Name: COLUMN cliente_direcciones.salida_id_provincia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_id_provincia IS 'OUT_PROVINCE';


--
-- Name: COLUMN cliente_direcciones.salida_id_estado_del_pais; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_id_estado_del_pais IS 'OUT_STATE';


--
-- Name: COLUMN cliente_direcciones.salida_id_condado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_id_condado IS 'OUT_COUNTY';


--
-- Name: COLUMN cliente_direcciones.salida_direccion_2; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_direccion_2 IS 'OUT_ADDR_2 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.salida_direccion_4; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_direccion_4 IS 'OUT_ADDR_4 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.salida_direccion_5; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_direccion_5 IS 'OUT_ADDR_5 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.entre_calle_1; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.entre_calle_1 IS 'Entre calle destinatario';


--
-- Name: COLUMN cliente_direcciones.numero_exterior; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.numero_exterior IS 'Número ext. destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones.alias_de_direccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.alias_de_direccion IS 'Alias de direccion';


--
-- Name: COLUMN cliente_direcciones.numero_interior; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.numero_interior IS 'Número int. destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_operacion IS 'ID_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.salida_codigo_zip; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_codigo_zip IS 'OUT_ZIPCODE';


--
-- Name: COLUMN cliente_direcciones.salida_numero_exterior; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_numero_exterior IS 'OUT_EXT_NUM';


--
-- Name: COLUMN cliente_direcciones.salida_calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.salida_calle IS 'OUT_STREET';


--
-- Name: COLUMN cliente_direcciones.direccion_3; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.direccion_3 IS 'IN_ADDR_3';


--
-- Name: COLUMN cliente_direcciones.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.id_transaccion IS 'ID_TRANSACTION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones.entre_calle_2; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.entre_calle_2 IS 'Entre calle destinatario Y CALLE';


--
-- Name: COLUMN cliente_direcciones.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones.esta_borrado IS 'Borrado logico - S o N';


--
-- Name: cliente_direcciones_bitacora; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_direcciones_bitacora (
    id_direccion_del_cliente integer NOT NULL,
    descripcion_direccion character varying(50),
    direccion_4 character varying(100),
    direccion_2 character varying(100),
    direccion_5 character varying(100),
    direccion_3 character varying(100),
    id_transaccion character varying(100),
    entre_calle_2 character varying(100),
    codigo_postal character varying(8),
    salida_direccion_2 character varying(100),
    baja_domicilio character varying(50),
    bandera_termino character varying(15),
    es_shipping_address character varying,
    salida_direccion_3 character varying(100),
    bandera_emcccp character varying(15),
    token_calle character varying(25),
    codigo_zip character varying(100),
    edificio character varying(15),
    fecha_creacion date,
    salida_codigo_zip character varying(25),
    bandera_calle character varying(15),
    fecha_de_operacion date,
    salida_direccion_4 character varying(100),
    salida_direccion_5 character varying(100),
    entre_calle_1 character varying(100),
    numero_exterior character varying(15),
    alias_de_direccion character varying(100),
    numero_interior character varying(15),
    salida_numero_exterior character varying(50),
    id_operacion character varying(100),
    es_billing_address character varying,
    esta_borrado character varying,
    salida_calle character varying(100),
    salida_tipo character varying(25),
    fecha_actualizacion date,
    tipo_de_direccion character varying(100),
    calle character varying(100),
    id_catalogo_catalogos integer,
    id_pais integer,
    id_colonia integer,
    id_municipio integer,
    id_estado_del_pais integer,
    id_tipo_direccion_cliente integer,
    id_entidad_federativa integer,
    id_distrito integer,
    id_provincia integer,
    id_condado integer,
    salida_id_distrito integer,
    salida_id_provincia integer,
    salida_id_estado_del_pais integer,
    salida_id_condado integer,
    id_cliente_bitacora integer,
    id_sistema_origen integer
);


--
-- Name: TABLE cliente_direcciones_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_direcciones_bitacora IS 'Direcciones del Cliente';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_direccion_del_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_direccion_del_cliente IS 'ID Direccion del cliente';


--
-- Name: COLUMN cliente_direcciones_bitacora.descripcion_direccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.descripcion_direccion IS 'Descripcion';


--
-- Name: COLUMN cliente_direcciones_bitacora.direccion_4; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.direccion_4 IS 'IN_ADDR_4 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.direccion_2; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.direccion_2 IS 'IN_ADDR_2 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.direccion_5; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.direccion_5 IS 'IN_ADDR_5';


--
-- Name: COLUMN cliente_direcciones_bitacora.direccion_3; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.direccion_3 IS 'IN_ADDR_3';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_transaccion IS 'ID_TRANSACTION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.entre_calle_2; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.entre_calle_2 IS 'Entre calle destinatario Y CALLE';


--
-- Name: COLUMN cliente_direcciones_bitacora.codigo_postal; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.codigo_postal IS 'Código Postal destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_direccion_2; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_direccion_2 IS 'OUT_ADDR_2 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.baja_domicilio; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.baja_domicilio IS 'BAJA DE DOMICILIO - CLIENTE ATG';


--
-- Name: COLUMN cliente_direcciones_bitacora.bandera_termino; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.bandera_termino IS 'FLAG_ENDING';


--
-- Name: COLUMN cliente_direcciones_bitacora.es_shipping_address; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.es_shipping_address IS 'ES_SHIPPING_ADDRESS (S/N)';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_direccion_3; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_direccion_3 IS 'OUT_ADDR_3 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.bandera_emcccp; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.bandera_emcccp IS 'FLAG_EMCCCP';


--
-- Name: COLUMN cliente_direcciones_bitacora.token_calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.token_calle IS 'TOKEN_STREET';


--
-- Name: COLUMN cliente_direcciones_bitacora.codigo_zip; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.codigo_zip IS 'IN_ZIPCODE MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.edificio; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.edificio IS 'Edificio';


--
-- Name: COLUMN cliente_direcciones_bitacora.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_codigo_zip; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_codigo_zip IS 'OUT_ZIPCODE';


--
-- Name: COLUMN cliente_direcciones_bitacora.bandera_calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.bandera_calle IS 'FLAG_STREET';


--
-- Name: COLUMN cliente_direcciones_bitacora.fecha_de_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.fecha_de_operacion IS 'DATE_OPERATION';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_direccion_4; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_direccion_4 IS 'OUT_ADDR_4 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_direccion_5; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_direccion_5 IS 'OUT_ADDR_5 MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.entre_calle_1; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.entre_calle_1 IS 'Entre calle destinatario';


--
-- Name: COLUMN cliente_direcciones_bitacora.numero_exterior; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.numero_exterior IS 'Número ext. destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones_bitacora.alias_de_direccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.alias_de_direccion IS 'Alias de direccion';


--
-- Name: COLUMN cliente_direcciones_bitacora.numero_interior; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.numero_interior IS 'Número int. destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_numero_exterior; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_numero_exterior IS 'OUT_EXT_NUM';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_operacion IS 'ID_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.es_billing_address; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.es_billing_address IS 'ES_BILLING_ADDRESS (S/N)';


--
-- Name: COLUMN cliente_direcciones_bitacora.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.esta_borrado IS 'Borrado logico - S o N';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_calle IS 'OUT_STREET';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_tipo IS 'OUT_TYPE';


--
-- Name: COLUMN cliente_direcciones_bitacora.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_direcciones_bitacora.tipo_de_direccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.tipo_de_direccion IS 'IN_TYPE';


--
-- Name: COLUMN cliente_direcciones_bitacora.calle; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.calle IS 'Calle destinatario o remitente';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_catalogo_catalogos; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_catalogo_catalogos IS 'Identificador';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_pais; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_pais IS 'ID_PAIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_colonia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_colonia IS 'ID_COLONIA';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_municipio; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_municipio IS 'ID_MUNICIPIO';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_estado_del_pais; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_estado_del_pais IS 'Estados';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_tipo_direccion_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_tipo_direccion_cliente IS 'ID_TIPO_DIRECCION_CLIENTE';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_entidad_federativa; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_entidad_federativa IS 'ID_ENTIDAD_FEDERATIVA';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_distrito; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_distrito IS 'ID_DISTRITO';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_provincia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_provincia IS 'ID_PROVINCIA';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_condado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_condado IS 'ID_CONDADO';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_id_distrito; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_id_distrito IS 'SALIDA_ID_DISTRITO';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_id_provincia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_id_provincia IS 'SALIDA_ID_PROVINCIA';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_id_estado_del_pais; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_id_estado_del_pais IS 'SALIDA_ID_ESTADO_DEL_PAIS';


--
-- Name: COLUMN cliente_direcciones_bitacora.salida_id_condado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.salida_id_condado IS 'SALIDA_ID_CONDADO';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_cliente_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_cliente_bitacora IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_direcciones_bitacora.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_direcciones_bitacora.id_sistema_origen IS 'ID_SISTEMA_ORIGEN';


--
-- Name: cliente_emails_de_contacto; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_emails_de_contacto (
    id_cliente_emails_de_contacto integer NOT NULL,
    id_cliente integer NOT NULL,
    id_sistema_origen integer,
    fecha_operacion date,
    fecha_creacion date,
    id_transaccion character varying(100),
    fecha_actualizacion date,
    tipo character varying(25),
    id_operacion character varying(100),
    email character varying(100),
    esta_borrado character varying,
    salida_correo_electronico character varying(100),
    salida_tipo character varying(100),
    bandera_tipo character varying(100),
    token_correo_electronico character varying(100)
);


--
-- Name: TABLE cliente_emails_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_emails_de_contacto IS 'INFO de Casilla de Correos electronicos del Cliente';


--
-- Name: COLUMN cliente_emails_de_contacto.id_cliente_emails_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.id_cliente_emails_de_contacto IS 'PK tabla CLIENTE_EMAILS_DE_CONTACTO';


--
-- Name: COLUMN cliente_emails_de_contacto.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_emails_de_contacto.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN cliente_emails_de_contacto.fecha_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.fecha_operacion IS 'DATE_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_emails_de_contacto.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_emails_de_contacto.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.id_transaccion IS 'ID_TRANSACTION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_emails_de_contacto.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_emails_de_contacto.tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.tipo IS 'Tipo - PERSONAL  - TRABAJO';


--
-- Name: COLUMN cliente_emails_de_contacto.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.id_operacion IS 'ID_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_emails_de_contacto.email; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.email IS 'Email ';


--
-- Name: COLUMN cliente_emails_de_contacto.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.esta_borrado IS 'Borrado logico - S o N';


--
-- Name: COLUMN cliente_emails_de_contacto.salida_correo_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.salida_correo_electronico IS 'OUT_EMAIL';


--
-- Name: COLUMN cliente_emails_de_contacto.salida_tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.salida_tipo IS 'OUT_TYPE';


--
-- Name: COLUMN cliente_emails_de_contacto.bandera_tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.bandera_tipo IS 'FLAG_TYPE';


--
-- Name: COLUMN cliente_emails_de_contacto.token_correo_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto.token_correo_electronico IS 'TOKEN_EMAIL MDM ZONA GRIS';


--
-- Name: cliente_emails_de_contacto_bitacora; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_emails_de_contacto_bitacora (
    id_cliente_emails_de_contacto integer NOT NULL,
    fecha_actualizacion date,
    tipo character varying(25),
    email character varying(100),
    esta_borrado character varying,
    fecha_creacion date,
    id_sistema_origen integer,
    id_operacion character varying(100),
    salida_correo_electronico character varying(100),
    salida_tipo character varying(100),
    bandera_tipo character varying(100),
    token_correo_electronico character varying(100),
    fecha_operacion date,
    id_transaccion character varying(100),
    id_cliente_clientes_bitacora integer NOT NULL
);


--
-- Name: TABLE cliente_emails_de_contacto_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_emails_de_contacto_bitacora IS 'INFO de Casilla de Correos electronicos del Cliente';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.id_cliente_emails_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.id_cliente_emails_de_contacto IS 'PK tabla CLIENTE_EMAILS_DE_CONTACTO';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.tipo IS 'Tipo - PERSONAL - CELULAR - CASA';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.email; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.email IS 'Email ';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.esta_borrado IS 'Borrado logico - S o N';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.id_operacion IS 'ID_OPERACION';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.salida_correo_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.salida_correo_electronico IS 'SALIDA_CORREO_ELECTRONICO';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.salida_tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.salida_tipo IS 'SALIDA_TIPO';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.bandera_tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.bandera_tipo IS 'BANDERA_TIPO';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.token_correo_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.token_correo_electronico IS 'TOKEN_CORREO_ELECTRONICO';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.fecha_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.fecha_operacion IS 'FECHA_OPERACION';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.id_transaccion IS 'ID_TRANSACCION';


--
-- Name: COLUMN cliente_emails_de_contacto_bitacora.id_cliente_clientes_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_emails_de_contacto_bitacora.id_cliente_clientes_bitacora IS 'PK de la tabla Clientes';


--
-- Name: cliente_formas_de_pago; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_formas_de_pago (
    id_cliente_tarjeta integer NOT NULL,
    numero_tarjeta_cliente character varying(50),
    tipo_tarjeta character varying(15),
    esta_activa smallint,
    hash1_tarjeta character varying(50),
    hash2_tarjeta character varying(50),
    id_cliente integer,
    id_tipo_producto_bancario character varying(25),
    bin_tarjeta character varying(6),
    fecha_creacion date,
    hash_tarjeta_externa character varying(200),
    fecha_actualizacion date,
    id_banco_emisor_tarjeta integer,
    id_sistema_origen smallint,
    sistema_fuente character varying(25),
    tarjeta_es_debito_o_credito character varying(25),
    id_formas_de_pago smallint
);


--
-- Name: TABLE cliente_formas_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_formas_de_pago IS 'Tabla con las Tarjetas del Cliente';


--
-- Name: COLUMN cliente_formas_de_pago.id_cliente_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.id_cliente_tarjeta IS 'PK de Cliente Tarjetas';


--
-- Name: COLUMN cliente_formas_de_pago.numero_tarjeta_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.numero_tarjeta_cliente IS 'Numero de la Tarjeta del Cliente';


--
-- Name: COLUMN cliente_formas_de_pago.tipo_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.tipo_tarjeta IS 'Tipo de Tarjeta del Cliente';


--
-- Name: COLUMN cliente_formas_de_pago.esta_activa; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.esta_activa IS 'Tarjeta esta activa?';


--
-- Name: COLUMN cliente_formas_de_pago.hash1_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.hash1_tarjeta IS 'Hash 1';


--
-- Name: COLUMN cliente_formas_de_pago.hash2_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.hash2_tarjeta IS 'HASH 2';


--
-- Name: COLUMN cliente_formas_de_pago.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_formas_de_pago.id_tipo_producto_bancario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.id_tipo_producto_bancario IS 'Indica el tipo de producto bancario ( ej. Banamex gold)';


--
-- Name: COLUMN cliente_formas_de_pago.bin_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.bin_tarjeta IS 'Primeros 6 digitos del numero de tarjeta, identifica el banco y producto emitido.';


--
-- Name: COLUMN cliente_formas_de_pago.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_formas_de_pago.hash_tarjeta_externa; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.hash_tarjeta_externa IS 'Codigo hash de las tarjetas externas';


--
-- Name: COLUMN cliente_formas_de_pago.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_formas_de_pago.id_banco_emisor_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.id_banco_emisor_tarjeta IS 'Identificador';


--
-- Name: COLUMN cliente_formas_de_pago.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.id_sistema_origen IS 'FK a Sistema de Origen';


--
-- Name: COLUMN cliente_formas_de_pago.sistema_fuente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.sistema_fuente IS 'Codigo como el sistema fuente conoce al cliente';


--
-- Name: COLUMN cliente_formas_de_pago.tarjeta_es_debito_o_credito; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.tarjeta_es_debito_o_credito IS 'Indica si la tarjeta es credito o debito';


--
-- Name: COLUMN cliente_formas_de_pago.id_formas_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago.id_formas_de_pago IS 'FK de FORMAS_DE_PAGO';


--
-- Name: cliente_formas_de_pago_bitacora; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_formas_de_pago_bitacora (
    id_cliente_tarjeta integer NOT NULL,
    numero_tarjeta_cliente character varying(50),
    tipo_tarjeta character varying(15),
    esta_activa smallint,
    hash1_tarjeta character varying(50),
    hash2_tarjeta character varying(50),
    bin_tarjeta character varying(6),
    fecha_creacion date,
    hash_tarjeta_externa character varying(50),
    fecha_actualizacion date,
    sistema_fuente character varying(25),
    tarjeta_es_debito_o_credito character varying(25),
    id_tipo_producto_bancario character varying(25),
    id_sistema_origen integer,
    id_banco_emisor_tarjeta integer,
    id_formas_de_pago integer,
    id_cliente_clientes_bitacora integer NOT NULL
);


--
-- Name: TABLE cliente_formas_de_pago_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_formas_de_pago_bitacora IS 'Tabla con las Tarjetas del Cliente';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.id_cliente_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.id_cliente_tarjeta IS 'PK de Cliente Tarjetas';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.numero_tarjeta_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.numero_tarjeta_cliente IS 'Numero de la Tarjeta del Cliente';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.tipo_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.tipo_tarjeta IS 'Tipo de Tarjeta del Cliente';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.esta_activa; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.esta_activa IS 'Tarjeta esta activa?';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.hash1_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.hash1_tarjeta IS 'Hash 1';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.hash2_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.hash2_tarjeta IS 'HASH 2';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.bin_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.bin_tarjeta IS 'Primeros 6 digitos del numero de tarjeta, identifica el banco y producto emitido.';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.hash_tarjeta_externa; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.hash_tarjeta_externa IS 'Codigo hash de las tarjetas externas';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.sistema_fuente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.sistema_fuente IS 'Codigo como el sistema fuente conoce al cliente';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.tarjeta_es_debito_o_credito; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.tarjeta_es_debito_o_credito IS 'Indica si la tarjeta es credito o debito';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.id_tipo_producto_bancario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.id_tipo_producto_bancario IS 'Indica el tipo de producto bancario ( ej. Banamex gold)';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.id_banco_emisor_tarjeta; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.id_banco_emisor_tarjeta IS 'ID_BANCO_EMISOR_TARJETA';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.id_formas_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.id_formas_de_pago IS 'ID_FORMAS_DE_PAGO';


--
-- Name: COLUMN cliente_formas_de_pago_bitacora.id_cliente_clientes_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_formas_de_pago_bitacora.id_cliente_clientes_bitacora IS 'PK de la tabla Clientes';


--
-- Name: cliente_ids; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_ids (
    id_cliente_ids integer NOT NULL,
    id_origen character varying(100),
    id_cliente integer NOT NULL,
    id_sistema_origen integer,
    fecha_actualizacion date,
    fecha_creacion date
);


--
-- Name: TABLE cliente_ids; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_ids IS 'Tabla con los IDs de Sistemas Fuente';


--
-- Name: COLUMN cliente_ids.id_cliente_ids; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids.id_cliente_ids IS 'PK de tabla CLIENTE_IDS';


--
-- Name: COLUMN cliente_ids.id_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids.id_origen IS 'ID del sistemas Origen';


--
-- Name: COLUMN cliente_ids.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_ids.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN cliente_ids.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_ids.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids.fecha_creacion IS 'Auditoria';


--
-- Name: cliente_ids_bitacora; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_ids_bitacora (
    id_cliente_ids integer NOT NULL,
    id_origen character varying(100),
    fecha_actualizacion date,
    fecha_creacion date,
    id_sistema_origen integer,
    id_cliente_clientes_bitacora integer NOT NULL
);


--
-- Name: TABLE cliente_ids_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_ids_bitacora IS 'Tabla con los IDs de Sistemas Fuente';


--
-- Name: COLUMN cliente_ids_bitacora.id_cliente_ids; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids_bitacora.id_cliente_ids IS 'PK de tabla CLIENTE_IDS';


--
-- Name: COLUMN cliente_ids_bitacora.id_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids_bitacora.id_origen IS 'ID del sistemas Origen';


--
-- Name: COLUMN cliente_ids_bitacora.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids_bitacora.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_ids_bitacora.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids_bitacora.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_ids_bitacora.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids_bitacora.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN cliente_ids_bitacora.id_cliente_clientes_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ids_bitacora.id_cliente_clientes_bitacora IS 'PK de la tabla Clientes';


--
-- Name: cliente_telefonos_de_contacto; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_telefonos_de_contacto (
    id_cliente_telefonos_de_contacto integer NOT NULL,
    id_cliente integer NOT NULL,
    id_sistema_origen integer,
    fecha_actualizacion date,
    salida_numero_de_telefono character varying(100),
    esta_borrado character varying,
    id_operacion character varying(100),
    telefono character varying(16),
    extension_telefonica character varying(100),
    id_ciudad integer,
    id_provincia integer,
    id_transaccion character varying(100),
    salida_extension_telefonica character varying(100),
    codigo_zip character varying(100),
    alias_telefono character varying(16),
    lada character varying(100),
    fecha_de_operacion date,
    salida_tipo_de_telefono character varying(100),
    fecha_creacion date,
    bandera_telefono character varying(100)
);


--
-- Name: TABLE cliente_telefonos_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_telefonos_de_contacto IS 'Informacion del cliente';


--
-- Name: COLUMN cliente_telefonos_de_contacto.id_cliente_telefonos_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.id_cliente_telefonos_de_contacto IS 'PK  de CLIENTE_TELEFONOS_DE_CONTACTO';


--
-- Name: COLUMN cliente_telefonos_de_contacto.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_telefonos_de_contacto.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN cliente_telefonos_de_contacto.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_telefonos_de_contacto.salida_numero_de_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.salida_numero_de_telefono IS 'OUT_PHONE MDM ZONA GRIS';


--
-- Name: COLUMN cliente_telefonos_de_contacto.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.esta_borrado IS 'Borrado Logico - S o N';


--
-- Name: COLUMN cliente_telefonos_de_contacto.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.id_operacion IS 'ID_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_telefonos_de_contacto.telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.telefono IS 'Numero del telefono';


--
-- Name: COLUMN cliente_telefonos_de_contacto.extension_telefonica; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.extension_telefonica IS 'IN_EXT';


--
-- Name: COLUMN cliente_telefonos_de_contacto.id_ciudad; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.id_ciudad IS 'IN_CITY';


--
-- Name: COLUMN cliente_telefonos_de_contacto.id_provincia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.id_provincia IS 'IN_PROVINCE';


--
-- Name: COLUMN cliente_telefonos_de_contacto.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.id_transaccion IS 'ID_TRANSACTION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_telefonos_de_contacto.salida_extension_telefonica; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.salida_extension_telefonica IS 'OUT_EXT MDM ZONA GRIS';


--
-- Name: COLUMN cliente_telefonos_de_contacto.codigo_zip; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.codigo_zip IS 'IN_ZIPCODE';


--
-- Name: COLUMN cliente_telefonos_de_contacto.alias_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.alias_telefono IS 'Lista de Valores: CELULAR, CASA , TRABAJO, ADICIONAL, FAX , PAGER.';


--
-- Name: COLUMN cliente_telefonos_de_contacto.lada; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.lada IS 'IN_LADA MDM ZONA GRIS';


--
-- Name: COLUMN cliente_telefonos_de_contacto.fecha_de_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.fecha_de_operacion IS 'DATE_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN cliente_telefonos_de_contacto.salida_tipo_de_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.salida_tipo_de_telefono IS 'OUT_TYPE MDM ZONA GRIS';


--
-- Name: COLUMN cliente_telefonos_de_contacto.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_telefonos_de_contacto.bandera_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto.bandera_telefono IS 'FLAG_PHONE MDM ZONA GRIS';


--
-- Name: cliente_telefonos_de_contacto_bitacora; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_telefonos_de_contacto_bitacora (
    id_cliente_telefonos_de_contacto integer NOT NULL,
    fecha_actualizacion date,
    telefono character varying(16),
    esta_borrado character varying,
    fecha_creacion date,
    alias_telefono character varying(16),
    id_sistema_origen integer,
    lada character varying(100),
    salida_numero_de_telefono character varying(100),
    id_transaccion character varying(100),
    id_operacion character varying(100),
    extension_telefonica character varying(100),
    salida_extension_telefonica character varying(100),
    bandera_telefono character varying(100),
    salida_tipo_telefono character varying(100),
    fecha_operacion date,
    codigo_zip character varying(100),
    id_ciudad integer,
    id_provincia integer,
    id_cliente_clientes_bitacora integer NOT NULL
);


--
-- Name: TABLE cliente_telefonos_de_contacto_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_telefonos_de_contacto_bitacora IS 'Informacion del cliente';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.id_cliente_telefonos_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.id_cliente_telefonos_de_contacto IS 'PK  de CLIENTE_TELEFONOS_DE_CONTACTO';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.telefono IS 'Numero del telefono';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.esta_borrado IS 'Borrado Logico - S o N';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.fecha_creacion IS 'Auditoria';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.alias_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.alias_telefono IS 'Lista de Valores: CELULAR, CASA , TRABAJO, ADICIONAL, FAX , PAGER.';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.lada; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.lada IS 'LADA';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.salida_numero_de_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.salida_numero_de_telefono IS 'SALIDA_NUMERO_DE_TELEFONO';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.id_transaccion IS 'ID_TRANSACCION';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.id_operacion IS 'ID_OPERACION';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.extension_telefonica; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.extension_telefonica IS 'EXTENSION_TELEFONICA';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.salida_extension_telefonica; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.salida_extension_telefonica IS 'SALIDA_EXTENSION_TELEFONICA';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.bandera_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.bandera_telefono IS 'BANDERA_TELEFONO';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.salida_tipo_telefono; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.salida_tipo_telefono IS 'SALIDA_TIPO_TELEFONO';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.fecha_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.fecha_operacion IS 'FECHA_OPERACION';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.codigo_zip; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.codigo_zip IS 'CODIGO_ZIP';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.id_ciudad; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.id_ciudad IS 'Ciudad';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.id_provincia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.id_provincia IS 'Provincia';


--
-- Name: COLUMN cliente_telefonos_de_contacto_bitacora.id_cliente_clientes_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_telefonos_de_contacto_bitacora.id_cliente_clientes_bitacora IS 'PK de la tabla Clientes';


--
-- Name: cliente_ticket_electronico; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_ticket_electronico (
    id_cliente_ticket_electronico integer NOT NULL,
    id_ticket_electronico character varying(50),
    forma_de_pago character varying(50),
    tipo_de_forma_de_pago character varying(25),
    id_cliente integer NOT NULL,
    id_tipo_forma_de_pago integer,
    fecha_creacion date,
    fecha_actualizacion date,
    esta_borrado character varying(1) DEFAULT 'N'::character varying NOT NULL
);


--
-- Name: TABLE cliente_ticket_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.cliente_ticket_electronico IS 'Tabla CLIENTES_TICKET_ELECTRONICO';


--
-- Name: COLUMN cliente_ticket_electronico.id_cliente_ticket_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico.id_cliente_ticket_electronico IS 'PK de Tabla de CLIENTE_TICKET_ELECTRONICO';


--
-- Name: COLUMN cliente_ticket_electronico.id_ticket_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico.id_ticket_electronico IS 'ID Ticket Electronico';


--
-- Name: COLUMN cliente_ticket_electronico.forma_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico.forma_de_pago IS '-Forma de pago';


--
-- Name: COLUMN cliente_ticket_electronico.tipo_de_forma_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico.tipo_de_forma_de_pago IS '-Tipo de forma de pago';


--
-- Name: COLUMN cliente_ticket_electronico.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN cliente_ticket_electronico.id_tipo_forma_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico.id_tipo_forma_de_pago IS 'Identificador';


--
-- Name: cliente_ticket_electronico_medios_de_pago; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.cliente_ticket_electronico_medios_de_pago (
    id_cliente_ticket_electronico_cliente_ticket_electronico integer NOT NULL,
    id_medio_de_pago_medios_de_pago integer NOT NULL,
    fecha_creacion date,
    fecha_actualizacion date,
    esta_borrado character varying(1) DEFAULT 'N'::character varying NOT NULL
);


--
-- Name: COLUMN cliente_ticket_electronico_medios_de_pago.id_cliente_ticket_electronico_cliente_ticket_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico_medios_de_pago.id_cliente_ticket_electronico_cliente_ticket_electronico IS 'PK de Tabla de CLIENTE_TICKET_ELECTRONICO';


--
-- Name: COLUMN cliente_ticket_electronico_medios_de_pago.id_medio_de_pago_medios_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.cliente_ticket_electronico_medios_de_pago.id_medio_de_pago_medios_de_pago IS 'PK de Medios de Pago';


--
-- Name: clientes; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.clientes (
    id_cliente integer NOT NULL,
    primer_nombre character varying(100),
    segundo_nombre character varying(100),
    apellido_paterno character varying(100) DEFAULT NULL::character varying,
    apellido_materno character varying(100) DEFAULT NULL::character varying,
    fecha_de_nacimiento date,
    alias character varying(50),
    lada character varying(25) DEFAULT NULL::character varying,
    genero character varying,
    rfc character varying(25),
    numero_de_seguro_social character varying(50),
    codigo_facturacion character varying(50),
    nombre_sistema_externo character varying(100),
    id_sistema_externo character varying(100),
    operacion character varying(25),
    clave_homologada character varying(100),
    id_mod character varying(25),
    id_contacto character varying(50),
    id_pais integer,
    salida_fecha_de_nacimiento date,
    medio_contacto character varying(100),
    salida_bandera_fecha character varying(50),
    id_duplicado smallint,
    bandera_cliente_incompleto smallint,
    referencia_contacto_sistema_origen character varying(100),
    sistema_origen character varying(25),
    ano_calendario smallint,
    mes_calendario smallint,
    clave_cuenta_numero_plastico numeric(16,0),
    tarjeta_identificacion_nacional character varying(50),
    esta_borrado character varying,
    es_victima character varying DEFAULT 'N'::character varying,
    tipo_tarjeta_identificacion_nacional character varying(50),
    actualizado character varying(50),
    bloque integer,
    track_cliente_unico character varying,
    actualizado_por character varying(50),
    id_transaccion character varying(50),
    id_operacion character varying(50),
    homonimo character varying(100),
    fecha_creacion date,
    salida_primer_nombre character varying(100),
    salida_genero character varying,
    salida_rfc character varying(50),
    bandera_rfc character varying(50),
    nombre_de_token character varying(100),
    referencia character varying(100),
    id_sistema_origen integer,
    fecha_actualizacion date,
    id_origen_externo character varying(100),
    salida_apellido_paterno character varying(100),
    fecha_de_registro date,
    bandera_del_nombre character varying(50),
    id_cliente_padre_del_destinatario integer,
    salida_token_primer_nombre character varying(50),
    salida_token_segundo_nombre character varying(50),
    salida_token_apellido_paterno character varying(50),
    salida_bandera_rfc character varying(50),
    tipo character varying(100),
    bandera_ucm character varying(50),
    fuente character varying(100),
    fecha_de_la_operacion date,
    tipo_contacto character varying(100),
    salida_segundo_nombre character varying(100),
    nombre_segmento character varying(100),
    row_id character varying(100),
    alias_row_id character varying(100),
    id_mdm character varying(100),
    alias_id_mdm character varying(100)
);


--
-- Name: TABLE clientes; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.clientes IS 'Tabla Clientes cargada desde varios origenes/Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN clientes.segundo_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.segundo_nombre IS 'SEGUNDO NOMBRE';


--
-- Name: COLUMN clientes.apellido_paterno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.apellido_paterno IS 'Apellido Paterno//Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes.apellido_materno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.apellido_materno IS 'Apellido Materno/Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes.fecha_de_nacimiento; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.fecha_de_nacimiento IS 'BirthDate';


--
-- Name: COLUMN clientes.alias; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.alias IS 'ALIAS';


--
-- Name: COLUMN clientes.lada; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.lada IS 'Lada/Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes.genero; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.genero IS 'MF o genero (masculino/femenino)';


--
-- Name: COLUMN clientes.rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.rfc IS 'RFC (Si aplica)';


--
-- Name: COLUMN clientes.numero_de_seguro_social; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.numero_de_seguro_social IS 'SocialSecurityNumber';


--
-- Name: COLUMN clientes.codigo_facturacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.codigo_facturacion IS 'Codigo de Facturacion';


--
-- Name: COLUMN clientes.nombre_sistema_externo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.nombre_sistema_externo IS 'ExternalSystemName';


--
-- Name: COLUMN clientes.id_sistema_externo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_sistema_externo IS 'ExternalSystemId';


--
-- Name: COLUMN clientes.operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.operacion IS 'Operation';


--
-- Name: COLUMN clientes.clave_homologada; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.clave_homologada IS 'Id de MDMm Publish and Subscribe';


--
-- Name: COLUMN clientes.id_mod; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_mod IS 'ModId';


--
-- Name: COLUMN clientes.id_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_contacto IS 'ContactId';


--
-- Name: COLUMN clientes.id_pais; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_pais IS 'Identificador';


--
-- Name: COLUMN clientes.salida_fecha_de_nacimiento; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_fecha_de_nacimiento IS 'OUT_BIRTH_DT';


--
-- Name: COLUMN clientes.medio_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.medio_contacto IS 'MEDIO_CONTACTO';


--
-- Name: COLUMN clientes.salida_bandera_fecha; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_bandera_fecha IS 'OUT_FLAG_DATE';


--
-- Name: COLUMN clientes.id_duplicado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_duplicado IS 'ID_CLIENTE para identificar duplicados';


--
-- Name: COLUMN clientes.bandera_cliente_incompleto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.bandera_cliente_incompleto IS 'BANDERA para Clientes que deben ser completados por Campanas';


--
-- Name: COLUMN clientes.referencia_contacto_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.referencia_contacto_sistema_origen IS 'identificador de contacto en la fuente';


--
-- Name: COLUMN clientes.sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.sistema_origen IS 'Fuente que genero el xreference';


--
-- Name: COLUMN clientes.ano_calendario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.ano_calendario IS 'ANO_CALENDARIO';


--
-- Name: COLUMN clientes.mes_calendario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.mes_calendario IS 'MES_CALENDARIO';


--
-- Name: COLUMN clientes.clave_cuenta_numero_plastico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.clave_cuenta_numero_plastico IS 'Cuanta, normalmente viene vacias, en algunas fuente tiene un numero de plastico';


--
-- Name: COLUMN clientes.tarjeta_identificacion_nacional; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.tarjeta_identificacion_nacional IS 'NationalID';


--
-- Name: COLUMN clientes.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.esta_borrado IS 'Borrado logico - S o N';


--
-- Name: COLUMN clientes.es_victima; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.es_victima IS 'Es_victima_MDM_o_fue_fusionado_en_mdm (S/N)';


--
-- Name: COLUMN clientes.tipo_tarjeta_identificacion_nacional; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.tipo_tarjeta_identificacion_nacional IS 'NationalIDType';


--
-- Name: COLUMN clientes.actualizado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.actualizado IS 'Updated';


--
-- Name: COLUMN clientes.bloque; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.bloque IS 'Bloque DWH';


--
-- Name: COLUMN clientes.track_cliente_unico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.track_cliente_unico IS 'Lista de Valores : A - D y X - Z';


--
-- Name: COLUMN clientes.actualizado_por; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.actualizado_por IS 'Updated';


--
-- Name: COLUMN clientes.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_transaccion IS 'ID_TRANSACTION MDM ZONA GRIS';


--
-- Name: COLUMN clientes.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_operacion IS 'ID_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN clientes.homonimo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.homonimo IS 'HOMONIMO';


--
-- Name: COLUMN clientes.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.fecha_creacion IS 'Auditoria Fecha Actualizacion';


--
-- Name: COLUMN clientes.salida_primer_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_primer_nombre IS 'OUT_FST_NAME';


--
-- Name: COLUMN clientes.salida_genero; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_genero IS 'OUT_SEX';


--
-- Name: COLUMN clientes.salida_rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_rfc IS 'OUT_RFC';


--
-- Name: COLUMN clientes.bandera_rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.bandera_rfc IS 'FLAG_RFC';


--
-- Name: COLUMN clientes.nombre_de_token; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.nombre_de_token IS 'TOKEN_NAME';


--
-- Name: COLUMN clientes.referencia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.referencia IS 'XREF';


--
-- Name: COLUMN clientes.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_sistema_origen IS 'SYST_ID';


--
-- Name: COLUMN clientes.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN clientes.id_origen_externo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_origen_externo IS 'EXT_SRC_ID';


--
-- Name: COLUMN clientes.salida_apellido_paterno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_apellido_paterno IS 'OUT_LAST_NAME';


--
-- Name: COLUMN clientes.fecha_de_registro; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.fecha_de_registro IS 'REGISTRATION DATE (YYYY/MM/DD)';


--
-- Name: COLUMN clientes.bandera_del_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.bandera_del_nombre IS 'FLAG_NAME';


--
-- Name: COLUMN clientes.id_cliente_padre_del_destinatario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_cliente_padre_del_destinatario IS 'ID_CLIENTE_PADRE (cuando es destinatario)';


--
-- Name: COLUMN clientes.salida_token_primer_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_token_primer_nombre IS 'OUT_TOKEN_FST_NAME';


--
-- Name: COLUMN clientes.salida_token_segundo_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_token_segundo_nombre IS 'OUT_TOKEN_MIDD_NAME';


--
-- Name: COLUMN clientes.salida_token_apellido_paterno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_token_apellido_paterno IS 'OUT_TOKEN_LAST_NAME';


--
-- Name: COLUMN clientes.salida_bandera_rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_bandera_rfc IS 'OUT_FLAG_RFC';


--
-- Name: COLUMN clientes.tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.tipo IS 'TIPO';


--
-- Name: COLUMN clientes.bandera_ucm; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.bandera_ucm IS 'FLAG_UCM';


--
-- Name: COLUMN clientes.fuente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.fuente IS 'FUENTE MDM CARGA INICIAL';


--
-- Name: COLUMN clientes.fecha_de_la_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.fecha_de_la_operacion IS 'DATE_OPERATION';


--
-- Name: COLUMN clientes.tipo_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.tipo_contacto IS 'TIPO_CONTACTO CARGA INICIAN MDM';


--
-- Name: COLUMN clientes.salida_segundo_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.salida_segundo_nombre IS 'OUT_MIDD_NAME';


--
-- Name: COLUMN clientes.nombre_segmento; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.nombre_segmento IS 'Nombre de la segmentacion';


--
-- Name: COLUMN clientes.row_id; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.row_id IS 'Row ID del Cliente MDM';


--
-- Name: COLUMN clientes.alias_row_id; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.alias_row_id IS 'Alias nuevo del mergeado Row ID original del Cliente MDM';


--
-- Name: COLUMN clientes.id_mdm; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.id_mdm IS 'ID del Cliente en MDM';


--
-- Name: COLUMN clientes.alias_id_mdm; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes.alias_id_mdm IS 'Alias nuevo del mergeado ID_MDM original del Cliente MDM';


--
-- Name: clientes_bitacora; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.clientes_bitacora (
    id_cliente integer NOT NULL,
    primer_nombre character varying(100),
    segundo_nombre character varying(100),
    apellido_paterno character varying(100) DEFAULT NULL::character varying,
    apellido_materno character varying(100) DEFAULT NULL::character varying,
    fecha_de_nacimiento date,
    alias character varying(50),
    lada character varying(25) DEFAULT NULL::character varying,
    genero character varying,
    rfc character varying(25),
    numero_de_seguro_social character varying(50),
    codigo_facturacion character varying(50),
    nombre_sistema_externo character varying(100),
    id_sistema_externo character varying(100),
    operacion character varying(25),
    clave_homologada character varying(25),
    id_mod character varying(25),
    id_contacto character varying(50),
    homonimo character varying(100),
    fecha_creacion date,
    salida_primer_nombre character varying(100),
    salida_genero character varying,
    salida_rfc character varying(50),
    bandera_rfc character varying(50),
    nombre_de_token character varying(100),
    referencia character varying(100),
    fecha_actualizacion date,
    salida_apellido_paterno character varying(100),
    bandera_del_nombre character varying(50),
    salida_token_primer_nombre character varying(50),
    salida_token_apellido_paterno character varying(50),
    tipo character varying(100),
    fuente character varying(100),
    tipo_contacto character varying(100),
    nombre_segmento character varying(100),
    id_origen_externo character varying(100),
    fecha_de_registro date,
    id_cliente_padre_del_destinatario integer,
    salida_token_segundo_nombre character varying(50),
    salida_bandera_rfc character varying(50),
    bandera_ucm character varying(50),
    fecha_de_la_operacion date,
    salida_segundo_nombre character varying(100),
    salida_fecha_de_nacimiento date,
    medio_contacto character varying(100),
    salida_bandera_fecha character varying(50),
    id_duplicado smallint,
    es_victima character varying DEFAULT 'N'::character varying,
    bandera_cliente_incompleto smallint,
    tipo_tarjeta_identificacion_nacional character varying(50),
    referencia_contacto_sistema_origen character varying(100),
    actualizado character varying(50),
    sistema_origen character varying(25),
    bloque integer,
    ano_calendario smallint,
    track_cliente_unico character varying,
    mes_calendario smallint,
    actualizado_por character varying(50),
    clave_cuenta_numero_plastico numeric(16,0),
    id_transaccion character varying(50),
    tarjeta_identificacion_nacional character varying(50),
    id_operacion character varying(50),
    esta_borrado character varying,
    id_sistema_origen integer NOT NULL,
    row_id character varying(100),
    alias_row_id character varying(100),
    id_mdm character varying(100),
    alias_id_mdm character varying(100)
);


--
-- Name: TABLE clientes_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.clientes_bitacora IS 'Bitacora - Tabla Clientes cargada desde varios origenes/Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes_bitacora.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN clientes_bitacora.segundo_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.segundo_nombre IS 'SEGUNDO NOMBRE';


--
-- Name: COLUMN clientes_bitacora.apellido_paterno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.apellido_paterno IS 'Apellido Paterno//Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes_bitacora.apellido_materno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.apellido_materno IS 'Apellido Materno/Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes_bitacora.fecha_de_nacimiento; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.fecha_de_nacimiento IS 'BirthDate';


--
-- Name: COLUMN clientes_bitacora.alias; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.alias IS 'ALIAS';


--
-- Name: COLUMN clientes_bitacora.lada; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.lada IS 'Lada/Ingesta de Boletas via TLOG';


--
-- Name: COLUMN clientes_bitacora.genero; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.genero IS 'MF o genero (masculino/femenino)';


--
-- Name: COLUMN clientes_bitacora.rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.rfc IS 'RFC (Si aplica)';


--
-- Name: COLUMN clientes_bitacora.numero_de_seguro_social; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.numero_de_seguro_social IS 'SocialSecurityNumber';


--
-- Name: COLUMN clientes_bitacora.codigo_facturacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.codigo_facturacion IS 'Codigo de Facturacion';


--
-- Name: COLUMN clientes_bitacora.nombre_sistema_externo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.nombre_sistema_externo IS 'ExternalSystemName';


--
-- Name: COLUMN clientes_bitacora.id_sistema_externo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_sistema_externo IS 'ExternalSystemId';


--
-- Name: COLUMN clientes_bitacora.operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.operacion IS 'Operation';


--
-- Name: COLUMN clientes_bitacora.clave_homologada; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.clave_homologada IS 'Id de MDMm Publish and Subscribe';


--
-- Name: COLUMN clientes_bitacora.id_mod; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_mod IS 'ModId';


--
-- Name: COLUMN clientes_bitacora.id_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_contacto IS 'ContactId';


--
-- Name: COLUMN clientes_bitacora.homonimo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.homonimo IS 'HOMONIMO';


--
-- Name: COLUMN clientes_bitacora.fecha_creacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.fecha_creacion IS 'Auditoria Fecha Actualizacion';


--
-- Name: COLUMN clientes_bitacora.salida_primer_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_primer_nombre IS 'OUT_FST_NAME';


--
-- Name: COLUMN clientes_bitacora.salida_genero; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_genero IS 'OUT_SEX';


--
-- Name: COLUMN clientes_bitacora.salida_rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_rfc IS 'OUT_RFC';


--
-- Name: COLUMN clientes_bitacora.bandera_rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.bandera_rfc IS 'FLAG_RFC';


--
-- Name: COLUMN clientes_bitacora.nombre_de_token; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.nombre_de_token IS 'TOKEN_NAME';


--
-- Name: COLUMN clientes_bitacora.referencia; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.referencia IS 'XREF';


--
-- Name: COLUMN clientes_bitacora.fecha_actualizacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.fecha_actualizacion IS 'Auditoria';


--
-- Name: COLUMN clientes_bitacora.salida_apellido_paterno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_apellido_paterno IS 'OUT_LAST_NAME';


--
-- Name: COLUMN clientes_bitacora.bandera_del_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.bandera_del_nombre IS 'FLAG_NAME';


--
-- Name: COLUMN clientes_bitacora.salida_token_primer_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_token_primer_nombre IS 'OUT_TOKEN_FST_NAME';


--
-- Name: COLUMN clientes_bitacora.salida_token_apellido_paterno; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_token_apellido_paterno IS 'OUT_TOKEN_LAST_NAME';


--
-- Name: COLUMN clientes_bitacora.tipo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.tipo IS 'TIPO';


--
-- Name: COLUMN clientes_bitacora.fuente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.fuente IS 'FUENTE MDM CARGA INICIAL';


--
-- Name: COLUMN clientes_bitacora.tipo_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.tipo_contacto IS 'TIPO_CONTACTO CARGA INICIAN MDM';


--
-- Name: COLUMN clientes_bitacora.nombre_segmento; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.nombre_segmento IS 'Nombre de la segmentacion';


--
-- Name: COLUMN clientes_bitacora.id_origen_externo; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_origen_externo IS 'EXT_SRC_ID';


--
-- Name: COLUMN clientes_bitacora.fecha_de_registro; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.fecha_de_registro IS 'REGISTRATION DATE (YYYY/MM/DD)';


--
-- Name: COLUMN clientes_bitacora.id_cliente_padre_del_destinatario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_cliente_padre_del_destinatario IS 'ID_CLIENTE_PADRE (cuando es destinatario)';


--
-- Name: COLUMN clientes_bitacora.salida_token_segundo_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_token_segundo_nombre IS 'OUT_TOKEN_MIDD_NAME';


--
-- Name: COLUMN clientes_bitacora.salida_bandera_rfc; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_bandera_rfc IS 'OUT_FLAG_RFC';


--
-- Name: COLUMN clientes_bitacora.bandera_ucm; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.bandera_ucm IS 'FLAG_UCM';


--
-- Name: COLUMN clientes_bitacora.fecha_de_la_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.fecha_de_la_operacion IS 'DATE_OPERATION';


--
-- Name: COLUMN clientes_bitacora.salida_segundo_nombre; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_segundo_nombre IS 'OUT_MIDD_NAME';


--
-- Name: COLUMN clientes_bitacora.salida_fecha_de_nacimiento; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_fecha_de_nacimiento IS 'OUT_BIRTH_DT';


--
-- Name: COLUMN clientes_bitacora.medio_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.medio_contacto IS 'MEDIO_CONTACTO';


--
-- Name: COLUMN clientes_bitacora.salida_bandera_fecha; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.salida_bandera_fecha IS 'OUT_FLAG_DATE';


--
-- Name: COLUMN clientes_bitacora.id_duplicado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_duplicado IS 'ID_CLIENTE para identificar duplicados';


--
-- Name: COLUMN clientes_bitacora.es_victima; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.es_victima IS 'Es_victima_MDM_o_fue_fusionado_en_mdm (S/N)';


--
-- Name: COLUMN clientes_bitacora.bandera_cliente_incompleto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.bandera_cliente_incompleto IS 'BANDERA para Clientes que deben ser completados por Campanas';


--
-- Name: COLUMN clientes_bitacora.tipo_tarjeta_identificacion_nacional; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.tipo_tarjeta_identificacion_nacional IS 'NationalIDType';


--
-- Name: COLUMN clientes_bitacora.referencia_contacto_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.referencia_contacto_sistema_origen IS 'identificador de contacto en la fuente';


--
-- Name: COLUMN clientes_bitacora.actualizado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.actualizado IS 'Updated';


--
-- Name: COLUMN clientes_bitacora.sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.sistema_origen IS 'Fuente que genero el xreference';


--
-- Name: COLUMN clientes_bitacora.bloque; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.bloque IS 'Bloque DWH';


--
-- Name: COLUMN clientes_bitacora.ano_calendario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.ano_calendario IS 'ANO_CALENDARIO';


--
-- Name: COLUMN clientes_bitacora.track_cliente_unico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.track_cliente_unico IS 'Lista de Valores : A - D y X - Z';


--
-- Name: COLUMN clientes_bitacora.mes_calendario; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.mes_calendario IS 'MES_CALENDARIO';


--
-- Name: COLUMN clientes_bitacora.actualizado_por; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.actualizado_por IS 'Updated';


--
-- Name: COLUMN clientes_bitacora.clave_cuenta_numero_plastico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.clave_cuenta_numero_plastico IS 'Cuanta, normalmente viene vacias, en algunas fuente tiene un numero de plastico';


--
-- Name: COLUMN clientes_bitacora.id_transaccion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_transaccion IS 'ID_TRANSACTION MDM ZONA GRIS';


--
-- Name: COLUMN clientes_bitacora.tarjeta_identificacion_nacional; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.tarjeta_identificacion_nacional IS 'NationalID';


--
-- Name: COLUMN clientes_bitacora.id_operacion; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_operacion IS 'ID_OPERATION MDM ZONA GRIS';


--
-- Name: COLUMN clientes_bitacora.esta_borrado; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.esta_borrado IS 'Borrado logico - S o N';


--
-- Name: COLUMN clientes_bitacora.id_sistema_origen; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_sistema_origen IS 'Identificador';


--
-- Name: COLUMN clientes_bitacora.row_id; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.row_id IS 'Row ID del Cliente MDM';


--
-- Name: COLUMN clientes_bitacora.alias_row_id; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.alias_row_id IS 'Alias nuevo del mergeado Row ID original del Cliente MDM';


--
-- Name: COLUMN clientes_bitacora.id_mdm; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.id_mdm IS 'ID del Cliente en MDM';


--
-- Name: COLUMN clientes_bitacora.alias_id_mdm; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_bitacora.alias_id_mdm IS 'Alias nuevo del mergeado ID_MDM original del Cliente MDM';


--
-- Name: clientes_merge; Type: TABLE; Schema: clientes; Owner: -
--

CREATE TABLE clientes.clientes_merge (
    id_cliente_unico integer NOT NULL,
    rowid character varying(25),
    componente numeric(16,0),
    bandera_cu smallint,
    id_cliente integer,
    vic_row_id character varying(100),
    fuente character varying(100),
    fecha_de_carga date,
    sup_row_id character varying(100),
    cu_track character varying(200)
);


--
-- Name: TABLE clientes_merge; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON TABLE clientes.clientes_merge IS 'Tabla de Clientes Unicos de DWH';


--
-- Name: COLUMN clientes_merge.id_cliente_unico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.id_cliente_unico IS 'PK de CLIENTE_UNICO';


--
-- Name: COLUMN clientes_merge.rowid; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.rowid IS 'ROW_ID de DWH';


--
-- Name: COLUMN clientes_merge.componente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.componente IS 'COMPONENTE de DWH';


--
-- Name: COLUMN clientes_merge.bandera_cu; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.bandera_cu IS 'FLAG_CU';


--
-- Name: COLUMN clientes_merge.id_cliente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.id_cliente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN clientes_merge.vic_row_id; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.vic_row_id IS 'VIC_ROW_ID';


--
-- Name: COLUMN clientes_merge.fuente; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.fuente IS 'FUENTE';


--
-- Name: COLUMN clientes_merge.fecha_de_carga; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.fecha_de_carga IS 'fecha de carga';


--
-- Name: COLUMN clientes_merge.sup_row_id; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.sup_row_id IS 'SUP_ROW_ID';


--
-- Name: COLUMN clientes_merge.cu_track; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON COLUMN clientes.clientes_merge.cu_track IS 'Track Cliente Unico';


--
-- Name: seq_cliente_atg_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_atg_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_atg_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_atg_pk OWNED BY clientes.cliente_atg.id_cliente_atg;


--
-- Name: seq_cliente_direcciones_bitacora_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_direcciones_bitacora_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_direcciones_bitacora_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_direcciones_bitacora_pk OWNED BY clientes.cliente_direcciones_bitacora.id_direccion_del_cliente;


--
-- Name: seq_cliente_direcciones_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_direcciones_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_direcciones_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_direcciones_pk OWNED BY clientes.cliente_direcciones.id_direccion_del_cliente;


--
-- Name: seq_cliente_emails_de_contacto_bitacora_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_emails_de_contacto_bitacora_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_emails_de_contacto_bitacora_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_emails_de_contacto_bitacora_pk OWNED BY clientes.cliente_emails_de_contacto_bitacora.id_cliente_emails_de_contacto;


--
-- Name: seq_cliente_emails_de_contacto_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_emails_de_contacto_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_emails_de_contacto_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_emails_de_contacto_pk OWNED BY clientes.cliente_emails_de_contacto.id_cliente_emails_de_contacto;


--
-- Name: seq_cliente_formas_de_pago_bitacora_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_formas_de_pago_bitacora_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_formas_de_pago_bitacora_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_formas_de_pago_bitacora_pk OWNED BY clientes.cliente_formas_de_pago_bitacora.id_cliente_tarjeta;


--
-- Name: seq_cliente_formas_de_pago_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_formas_de_pago_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_formas_de_pago_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_formas_de_pago_pk OWNED BY clientes.cliente_formas_de_pago.id_cliente_tarjeta;


--
-- Name: seq_cliente_ids_bitacora_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_ids_bitacora_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_ids_bitacora_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_ids_bitacora_pk OWNED BY clientes.cliente_ids_bitacora.id_cliente_ids;


--
-- Name: seq_cliente_ids_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_ids_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_ids_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_ids_pk OWNED BY clientes.cliente_ids.id_cliente_ids;


--
-- Name: seq_cliente_telefonos_de_contacto_bitacora_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_telefonos_de_contacto_bitacora_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_telefonos_de_contacto_bitacora_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_telefonos_de_contacto_bitacora_pk OWNED BY clientes.cliente_telefonos_de_contacto_bitacora.id_cliente_telefonos_de_contacto;


--
-- Name: seq_cliente_telefonos_de_contacto_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_telefonos_de_contacto_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_telefonos_de_contacto_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_telefonos_de_contacto_pk OWNED BY clientes.cliente_telefonos_de_contacto.id_cliente_telefonos_de_contacto;


--
-- Name: seq_cliente_ticket_electronico_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_cliente_ticket_electronico_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_cliente_ticket_electronico_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_cliente_ticket_electronico_pk OWNED BY clientes.cliente_ticket_electronico.id_cliente_ticket_electronico;


--
-- Name: seq_clientes_bitacora_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_clientes_bitacora_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_clientes_bitacora_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_clientes_bitacora_pk OWNED BY clientes.clientes_bitacora.id_cliente;


--
-- Name: seq_clientes_merge_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_clientes_merge_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_clientes_merge_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_clientes_merge_pk OWNED BY clientes.clientes_merge.id_cliente_unico;


--
-- Name: seq_clientes_pk; Type: SEQUENCE; Schema: clientes; Owner: -
--

CREATE SEQUENCE clientes.seq_clientes_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_clientes_pk; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: -
--

ALTER SEQUENCE clientes.seq_clientes_pk OWNED BY clientes.clientes.id_cliente;


--
-- Name: pedidos; Type: TABLE; Schema: pedidos; Owner: -
--

CREATE TABLE pedidos.pedidos (
    numero_del_documento character varying(10) NOT NULL,
    estado_orden character varying(4) NOT NULL,
    fecha_modificacion date NOT NULL,
    hora_modificacion time without time zone NOT NULL,
    estado_renglon_bitacora character varying(5) NOT NULL,
    fecha_emision date,
    hora_emision time without time zone,
    id_estado_del_documento integer,
    observaciones character varying(200),
    id_tipo_entrega smallint,
    id_tipo_de_venta smallint,
    id_cliente_remitente integer NOT NULL,
    id_direcciones_destinatario integer NOT NULL,
    id_tipo_de_act smallint,
    id_tipo_de_documento smallint,
    id_periodicidad smallint,
    fecha_de_compra date,
    id_tienda_y_locacion_destino integer,
    id_tienda_y_locacion_surte smallint,
    hora_de_emision time without time zone,
    id_frecuencia_visita smallint,
    orden_original character varying(50)
);


--
-- Name: TABLE pedidos; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON TABLE pedidos.pedidos IS 'Pedidos del sistema SOMS';


--
-- Name: COLUMN pedidos.numero_del_documento; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.numero_del_documento IS 'Remision / Orden de venta.';


--
-- Name: COLUMN pedidos.estado_orden; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.estado_orden IS 'Estado de la Orden';


--
-- Name: COLUMN pedidos.fecha_modificacion; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.fecha_modificacion IS 'Fecha de modificacion';


--
-- Name: COLUMN pedidos.hora_modificacion; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.hora_modificacion IS 'Hora de la modificacion';


--
-- Name: COLUMN pedidos.estado_renglon_bitacora; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.estado_renglon_bitacora IS 'Estado del renglon de la bitacora';


--
-- Name: COLUMN pedidos.fecha_emision; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.fecha_emision IS 'Fecha de emision';


--
-- Name: COLUMN pedidos.hora_emision; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.hora_emision IS 'Hora de emision';


--
-- Name: COLUMN pedidos.id_estado_del_documento; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_estado_del_documento IS 'Identificador';


--
-- Name: COLUMN pedidos.observaciones; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.observaciones IS 'Observaciones';


--
-- Name: COLUMN pedidos.id_tipo_entrega; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_tipo_entrega IS 'Tipo de entrega.';


--
-- Name: COLUMN pedidos.id_tipo_de_venta; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_tipo_de_venta IS 'Tipo de venta.';


--
-- Name: COLUMN pedidos.id_cliente_remitente; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_cliente_remitente IS 'PK de la tabla Clientes';


--
-- Name: COLUMN pedidos.id_direcciones_destinatario; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_direcciones_destinatario IS 'ID Direccion del cliente';


--
-- Name: COLUMN pedidos.id_tipo_de_act; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_tipo_de_act IS 'Tipo de act';


--
-- Name: COLUMN pedidos.id_tipo_de_documento; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_tipo_de_documento IS 'OV=Orden de venta. RM=Remision';


--
-- Name: COLUMN pedidos.id_periodicidad; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_periodicidad IS 'Periodicidad';


--
-- Name: COLUMN pedidos.fecha_de_compra; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.fecha_de_compra IS 'Fecha de compra.';


--
-- Name: COLUMN pedidos.id_tienda_y_locacion_destino; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_tienda_y_locacion_destino IS 'PK de Tienda o Locacion';


--
-- Name: COLUMN pedidos.id_tienda_y_locacion_surte; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_tienda_y_locacion_surte IS 'LocaciOn que surte';


--
-- Name: COLUMN pedidos.hora_de_emision; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.hora_de_emision IS 'Hora emision';


--
-- Name: COLUMN pedidos.id_frecuencia_visita; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.id_frecuencia_visita IS 'Frecuencia visita';


--
-- Name: COLUMN pedidos.orden_original; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos.orden_original IS 'Orden original';


--
-- Name: pedidos_detalle_sku; Type: TABLE; Schema: pedidos; Owner: -
--

CREATE TABLE pedidos.pedidos_detalle_sku (
    numero_del_documento_pedidos character varying(10) NOT NULL,
    estado_orden_pedidos character varying(2) NOT NULL,
    fecha_modificacion_pedidos date NOT NULL,
    hora_modificacion_pedidos time without time zone NOT NULL,
    numero_de_intentos_de_entrega numeric(8,0) DEFAULT 0,
    id_locacion_surte integer,
    id_producto_productos integer,
    fecha_actualizacion_estado date,
    fecha_de_surtido date,
    proveedor_de_mensajeria character varying(50),
    cantidad_recogida_orden_de_venta numeric(8,0),
    fecha_recalculada date,
    usuario_actualizador_de_estado character varying(25),
    cantidad_cancelada numeric(16,2) DEFAULT 0.00,
    id_linea_detalle integer NOT NULL,
    cantidad_entregada numeric(16,2) DEFAULT 0.00,
    hora_actualizacion_estado time without time zone,
    numero_de_guia character varying(25),
    fecha_real_de_entrega date,
    fecha_ultimo_intento date,
    id_causa_de_noentrega integer,
    indicador_marketplace character varying(50),
    piezas numeric(16,2) DEFAULT 0.00,
    id_estado_por_linea integer,
    fecha_promesa_de_entrega_inicial date,
    fecha_promesa_de_entrega_final date
);


--
-- Name: TABLE pedidos_detalle_sku; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON TABLE pedidos.pedidos_detalle_sku IS 'Detalle de la tabla PEDIDOS';


--
-- Name: COLUMN pedidos_detalle_sku.numero_del_documento_pedidos; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.numero_del_documento_pedidos IS 'Remision / Orden de venta.';


--
-- Name: COLUMN pedidos_detalle_sku.estado_orden_pedidos; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.estado_orden_pedidos IS 'Estado de la Orden';


--
-- Name: COLUMN pedidos_detalle_sku.fecha_modificacion_pedidos; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.fecha_modificacion_pedidos IS 'Fecha de modificacion';


--
-- Name: COLUMN pedidos_detalle_sku.hora_modificacion_pedidos; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.hora_modificacion_pedidos IS 'Hora de la modificacion';


--
-- Name: COLUMN pedidos_detalle_sku.numero_de_intentos_de_entrega; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.numero_de_intentos_de_entrega IS 'Las entregas pueden ser parciales entre articulos';


--
-- Name: COLUMN pedidos_detalle_sku.id_locacion_surte; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.id_locacion_surte IS 'PK de Tienda o Locacion';


--
-- Name: COLUMN pedidos_detalle_sku.id_producto_productos; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.id_producto_productos IS 'ID de Producto';


--
-- Name: COLUMN pedidos_detalle_sku.fecha_actualizacion_estado; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.fecha_actualizacion_estado IS 'Los cambios de estatus serán a nivel línea';


--
-- Name: COLUMN pedidos_detalle_sku.fecha_de_surtido; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.fecha_de_surtido IS 'Fecha de surtido';


--
-- Name: COLUMN pedidos_detalle_sku.proveedor_de_mensajeria; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.proveedor_de_mensajeria IS 'Atributo de la Remision';


--
-- Name: COLUMN pedidos_detalle_sku.cantidad_recogida_orden_de_venta; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.cantidad_recogida_orden_de_venta IS 'Cantidad recogida';


--
-- Name: COLUMN pedidos_detalle_sku.fecha_recalculada; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.fecha_recalculada IS 'Fecha recalculada.';


--
-- Name: COLUMN pedidos_detalle_sku.usuario_actualizador_de_estado; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.usuario_actualizador_de_estado IS 'Los cambios de estatus serán a nivel línea';


--
-- Name: COLUMN pedidos_detalle_sku.cantidad_cancelada; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.cantidad_cancelada IS 'Cantidad cancelada';


--
-- Name: COLUMN pedidos_detalle_sku.id_linea_detalle; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.id_linea_detalle IS 'PK de la tabla PEDIDOS_DETALLE_SKU';


--
-- Name: COLUMN pedidos_detalle_sku.cantidad_entregada; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.cantidad_entregada IS 'Cantidad entregada';


--
-- Name: COLUMN pedidos_detalle_sku.hora_actualizacion_estado; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.hora_actualizacion_estado IS 'Los cambios de estatus serán a nivel línea';


--
-- Name: COLUMN pedidos_detalle_sku.numero_de_guia; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.numero_de_guia IS 'Atributo de la Remision';


--
-- Name: COLUMN pedidos_detalle_sku.fecha_real_de_entrega; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.fecha_real_de_entrega IS 'Fecha real de entrega.';


--
-- Name: COLUMN pedidos_detalle_sku.fecha_ultimo_intento; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.fecha_ultimo_intento IS 'Fecha ultimo intento';


--
-- Name: COLUMN pedidos_detalle_sku.id_causa_de_noentrega; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.id_causa_de_noentrega IS 'Identificador';


--
-- Name: COLUMN pedidos_detalle_sku.indicador_marketplace; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.indicador_marketplace IS 'Indicador de MKP';


--
-- Name: COLUMN pedidos_detalle_sku.piezas; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.piezas IS 'Piezas.';


--
-- Name: COLUMN pedidos_detalle_sku.id_estado_por_linea; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON COLUMN pedidos.pedidos_detalle_sku.id_estado_por_linea IS 'Track Cliente Unico';


--
-- Name: seq_catalogos_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_catalogos_pk
    START WITH 1060
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: catalogos; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.catalogos (
    id_catalogo integer DEFAULT nextval('shared.seq_catalogos_pk'::regclass) NOT NULL,
    descripcion_catalogo character varying(250) NOT NULL,
    clave_homologada character varying(100) NOT NULL,
    entidad_origen character varying(200) NOT NULL,
    esquema character varying(100),
    id_catalogo_padre integer,
    esta_borrado character varying(1) DEFAULT 'N'::character varying NOT NULL,
    flexfield0 character varying(100),
    flexfield1 character varying(100),
    flexfield2 character varying(100),
    flexfield3 character varying(100),
    flexfield4 character varying(100),
    flexfield5 character varying(100),
    flexfield6 character varying(100),
    flexfield7 character varying(100),
    flexfield8 character varying(100),
    flexfield9 character varying(100)
);


--
-- Name: TABLE catalogos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.catalogos IS 'Tabla con catalogo de entidades/multiples tablas.';


--
-- Name: COLUMN catalogos.id_catalogo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.id_catalogo IS 'Identificador';


--
-- Name: COLUMN catalogos.descripcion_catalogo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.descripcion_catalogo IS 'Descripcion';


--
-- Name: COLUMN catalogos.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.clave_homologada IS 'PK (homologa) original/externa de tabla almacenada';


--
-- Name: COLUMN catalogos.entidad_origen; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.entidad_origen IS 'Tabla almacenada';


--
-- Name: COLUMN catalogos.esquema; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.esquema IS 'schema user de la base de datos';


--
-- Name: COLUMN catalogos.id_catalogo_padre; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.id_catalogo_padre IS 'Self relationship';


--
-- Name: COLUMN catalogos.esta_borrado; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.esta_borrado IS 'IS DELETED: si o no';


--
-- Name: COLUMN catalogos.flexfield0; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield0 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield1; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield1 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield2; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield2 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield3; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield3 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield4; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield4 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield5; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield5 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield6; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield6 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield7; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield7 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield8; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield8 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: COLUMN catalogos.flexfield9; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.catalogos.flexfield9 IS 'Campo Flexible customizable para extender la tabla segun necesidad';


--
-- Name: impuestos; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.impuestos (
    id_impuesto integer NOT NULL,
    descripcion_impuesto character varying(15),
    tipo_impuesto numeric(15,0),
    categoria_del_impuesto character varying(15),
    es_impuesto_porcentual_o_fijo character varying,
    tasa_del_impuesto numeric(8,2) DEFAULT 0.00
);


--
-- Name: TABLE impuestos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.impuestos IS 'Impuestos';


--
-- Name: COLUMN impuestos.id_impuesto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.impuestos.id_impuesto IS 'ID del Impuesto';


--
-- Name: COLUMN impuestos.descripcion_impuesto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.impuestos.descripcion_impuesto IS 'Descripcion impuesto';


--
-- Name: COLUMN impuestos.tipo_impuesto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.impuestos.tipo_impuesto IS 'Tipo de Impuesto / Ingesta de Boleta';


--
-- Name: COLUMN impuestos.categoria_del_impuesto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.impuestos.categoria_del_impuesto IS 'Categoria del Impuesto';


--
-- Name: COLUMN impuestos.es_impuesto_porcentual_o_fijo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.impuestos.es_impuesto_porcentual_o_fijo IS 'Modo de aplicación del impuesto (“F” fijo o “P” porcentaje)';


--
-- Name: COLUMN impuestos.tasa_del_impuesto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.impuestos.tasa_del_impuesto IS 'Porcentaje o monto fijo del impuesto. El porcentaje incluye dos dígitos decimales, sin separador.';


--
-- Name: medios_de_pago; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.medios_de_pago (
    id_medio_de_pago integer NOT NULL,
    descripcion_medio_de_pago character varying(50),
    clave_homologada character varying(100)
);


--
-- Name: TABLE medios_de_pago; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.medios_de_pago IS 'Medios de Pagos';


--
-- Name: COLUMN medios_de_pago.id_medio_de_pago; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.medios_de_pago.id_medio_de_pago IS 'PK de Medios de Pago';


--
-- Name: COLUMN medios_de_pago.descripcion_medio_de_pago; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.medios_de_pago.descripcion_medio_de_pago IS 'Descripcion';


--
-- Name: COLUMN medios_de_pago.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.medios_de_pago.clave_homologada IS 'Codigo de Medios de Pagos o Formas de Pago que vienen de sistemas ed Liverpool';


--
-- Name: producto_aplica_servicios; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_aplica_servicios (
    id_producto_aplica_servicios integer NOT NULL,
    clave_nospot character varying(50),
    descripcion_nospot character varying(50),
    id_empresa_sku integer
);


--
-- Name: TABLE producto_aplica_servicios; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_aplica_servicios IS 'Tabla de Jerarquia de Servicios';


--
-- Name: COLUMN producto_aplica_servicios.id_producto_aplica_servicios; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_aplica_servicios.id_producto_aplica_servicios IS 'Tabla extension de PRODUCTOS: Producto Aplica Servicios';


--
-- Name: COLUMN producto_aplica_servicios.clave_nospot; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_aplica_servicios.clave_nospot IS 'Clave de Nospot';


--
-- Name: COLUMN producto_aplica_servicios.descripcion_nospot; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_aplica_servicios.descripcion_nospot IS 'Descripción de Nospot';


--
-- Name: COLUMN producto_aplica_servicios.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_aplica_servicios.id_empresa_sku IS 'Identificador';


--
-- Name: producto_bigticket; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_bigticket (
    id_producto_bigticket integer NOT NULL,
    clave_homologada character varying(50),
    descripcion_indice_bigticket character varying(50),
    fecha_actualizacion date
);


--
-- Name: TABLE producto_bigticket; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_bigticket IS 'Extension de la tabla PRODUCTOS ';


--
-- Name: COLUMN producto_bigticket.id_producto_bigticket; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_bigticket.id_producto_bigticket IS 'PK de tabla PRODUCTO_BIGTICKET';


--
-- Name: COLUMN producto_bigticket.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_bigticket.clave_homologada IS 'Clave del índice de Big Ticket';


--
-- Name: COLUMN producto_bigticket.descripcion_indice_bigticket; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_bigticket.descripcion_indice_bigticket IS 'Descripcion del Indice de Big Ticket';


--
-- Name: COLUMN producto_bigticket.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_bigticket.fecha_actualizacion IS 'Fecha de carga';


--
-- Name: producto_direcciones; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_direcciones (
    id_producto_direccion integer NOT NULL,
    clave_homologada character varying(25),
    descripcion_direccion character varying(100),
    fecha_actualizacion date,
    id_empresa_sku integer
);


--
-- Name: TABLE producto_direcciones; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_direcciones IS 'Departamentos en los cuales se Vende un Producto (ej.ROPA DEPORTIVA)';


--
-- Name: COLUMN producto_direcciones.id_producto_direccion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_direcciones.id_producto_direccion IS 'PK de la tabla  PRODUCTO_DIRECCIONES';


--
-- Name: COLUMN producto_direcciones.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_direcciones.clave_homologada IS 'Clave de la direccion/division';


--
-- Name: COLUMN producto_direcciones.descripcion_direccion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_direcciones.descripcion_direccion IS 'Descripcion de la direccion';


--
-- Name: COLUMN producto_direcciones.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_direcciones.fecha_actualizacion IS 'Fecha de carga';


--
-- Name: COLUMN producto_direcciones.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_direcciones.id_empresa_sku IS 'Identificador';


--
-- Name: producto_grupos; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_grupos (
    id_producto_grupo integer NOT NULL,
    id_grupo_del_articulo character varying(50),
    descripcion_del_grupo character varying(50),
    id_empresa_sku integer NOT NULL,
    id_seccion_sku smallint,
    id_clase_sku smallint,
    id_mundo_negocios smallint,
    id_comprador_corporativo smallint,
    fecha_actualizacion date,
    id_producto_secciones_sku integer NOT NULL,
    clave_homologada character varying(100)
);


--
-- Name: TABLE producto_grupos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_grupos IS 'Tabla de Jerarquia de Productos';


--
-- Name: COLUMN producto_grupos.id_producto_grupo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_producto_grupo IS 'Tabla de Grupos de Productos';


--
-- Name: COLUMN producto_grupos.id_grupo_del_articulo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_grupo_del_articulo IS 'Clave del grupo de artículo';


--
-- Name: COLUMN producto_grupos.descripcion_del_grupo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.descripcion_del_grupo IS 'Descripción del grupo de artículo';


--
-- Name: COLUMN producto_grupos.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_empresa_sku IS 'Identificador';


--
-- Name: COLUMN producto_grupos.id_seccion_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_seccion_sku IS 'FK de Secciones SKU de Productos';


--
-- Name: COLUMN producto_grupos.id_clase_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_clase_sku IS 'FK de Clases SKU';


--
-- Name: COLUMN producto_grupos.id_mundo_negocios; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_mundo_negocios IS 'FK Mundo Negocios de Productos';


--
-- Name: COLUMN producto_grupos.id_comprador_corporativo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_comprador_corporativo IS 'FK de Comprador Corporativo';


--
-- Name: COLUMN producto_grupos.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.fecha_actualizacion IS 'Fecha de carga';


--
-- Name: COLUMN producto_grupos.id_producto_secciones_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.id_producto_secciones_sku IS 'PK de las Secciones del Producto';


--
-- Name: COLUMN producto_grupos.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_grupos.clave_homologada IS 'Clave PK del Grupo en sistema interno de Liverpool';


--
-- Name: producto_marcas; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_marcas (
    id_producto_marcas integer NOT NULL,
    descripcion_marca_sku character varying(100),
    fecha_actualizacion date,
    id_empresa_sku integer,
    clave_homologada character varying(100)
);


--
-- Name: TABLE producto_marcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_marcas IS 'Tabla de la Marcas de los Productos';


--
-- Name: COLUMN producto_marcas.id_producto_marcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_marcas.id_producto_marcas IS 'PK de la tabla PRODUCTO_MARCAS';


--
-- Name: COLUMN producto_marcas.descripcion_marca_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_marcas.descripcion_marca_sku IS 'Descripcion de la marca del producto';


--
-- Name: COLUMN producto_marcas.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_marcas.fecha_actualizacion IS 'Fecha de carga';


--
-- Name: COLUMN producto_marcas.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_marcas.id_empresa_sku IS 'Identificador';


--
-- Name: COLUMN producto_marcas.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_marcas.clave_homologada IS 'Clave PK de la Marca en sistema interno de Liverpool';


--
-- Name: producto_secciones; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_secciones (
    id_producto_seccion_sku integer NOT NULL,
    clave_homologada character varying(25),
    descripcion_seccion character varying(50),
    fecha_actualizacion date,
    id_empresa_sku integer NOT NULL,
    id_producto_direccion integer NOT NULL
);


--
-- Name: TABLE producto_secciones; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_secciones IS 'Secciones a la que pertenecen los Grupos de Productos';


--
-- Name: COLUMN producto_secciones.id_producto_seccion_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_secciones.id_producto_seccion_sku IS 'PK de las Secciones del Producto';


--
-- Name: COLUMN producto_secciones.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_secciones.clave_homologada IS 'Clave de la seccion';


--
-- Name: COLUMN producto_secciones.descripcion_seccion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_secciones.descripcion_seccion IS 'Descripcion de la seccion';


--
-- Name: COLUMN producto_secciones.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_secciones.fecha_actualizacion IS 'Fecha de carga';


--
-- Name: COLUMN producto_secciones.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_secciones.id_empresa_sku IS 'Identificador';


--
-- Name: COLUMN producto_secciones.id_producto_direccion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_secciones.id_producto_direccion IS 'PK de la tabla  PRODUCTO_DIRECCIONES';


--
-- Name: producto_submarcas; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_submarcas (
    id_producto_submarcas integer NOT NULL,
    clave_homologada character varying(50),
    descripcion_submarca character varying(50),
    fecha_actualizacion date,
    id_empresa_sku integer,
    id_producto_marcas integer NOT NULL
);


--
-- Name: TABLE producto_submarcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_submarcas IS 'Tabla que tiene las SubMarcas (ligada a las Marcas) de un producto';


--
-- Name: COLUMN producto_submarcas.id_producto_submarcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_submarcas.id_producto_submarcas IS 'PK de PRODUCTO_SUBMARCAS';


--
-- Name: COLUMN producto_submarcas.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_submarcas.clave_homologada IS 'Clave de la submarca';


--
-- Name: COLUMN producto_submarcas.descripcion_submarca; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_submarcas.descripcion_submarca IS 'Descripcion de la submarca';


--
-- Name: COLUMN producto_submarcas.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_submarcas.fecha_actualizacion IS 'Fecha de carga';


--
-- Name: COLUMN producto_submarcas.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_submarcas.id_empresa_sku IS 'Identificador';


--
-- Name: COLUMN producto_submarcas.id_producto_marcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_submarcas.id_producto_marcas IS 'PK de la tabla PRODUCTO_MARCAS';


--
-- Name: producto_tallas; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.producto_tallas (
    id_producto_tallas integer NOT NULL,
    clave_de_talla character varying(25),
    id_empresa_sku integer NOT NULL,
    descripcion_generica_talla character varying(50),
    fecha_actualizacion date,
    clave_de_talla_sistema_origen character varying(25),
    descripcion_talla character varying(50)
);


--
-- Name: TABLE producto_tallas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.producto_tallas IS 'Tabla de Jerarquia de Productos con las tallas de Productos';


--
-- Name: COLUMN producto_tallas.id_producto_tallas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_tallas.id_producto_tallas IS 'PK de la tabla de PRODUCTO_TALLAS';


--
-- Name: COLUMN producto_tallas.clave_de_talla; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_tallas.clave_de_talla IS 'Clave de la talla';


--
-- Name: COLUMN producto_tallas.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_tallas.id_empresa_sku IS 'Identificador';


--
-- Name: COLUMN producto_tallas.descripcion_generica_talla; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_tallas.descripcion_generica_talla IS 'Descripcion generica de la talla';


--
-- Name: COLUMN producto_tallas.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_tallas.fecha_actualizacion IS 'Fecha de carga';


--
-- Name: COLUMN producto_tallas.clave_de_talla_sistema_origen; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_tallas.clave_de_talla_sistema_origen IS 'Clave de la talla original del sistema fuente';


--
-- Name: COLUMN producto_tallas.descripcion_talla; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.producto_tallas.descripcion_talla IS 'Descripcion de la talla';


--
-- Name: productos; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.productos (
    id_producto integer NOT NULL,
    codigo_de_barras character varying(50),
    id_generico character varying(15),
    descripcion character varying(50),
    costo_neto numeric(16,2) DEFAULT 0.00,
    fecha_modificacion date,
    id_importacion character varying(25),
    id_producto_nospot integer,
    descripcion_sector character varying(100),
    precio_venta_sugerido numeric(16,2) DEFAULT 0.00,
    id_lic character varying(25),
    id_coordinado character varying(25),
    id_producto_grupos integer NOT NULL,
    id_proveedor integer,
    precio_venta numeric(16,2) DEFAULT 0.00,
    precio_sugerido numeric(16,2) DEFAULT 0.00,
    id_empresa_sku integer NOT NULL,
    id_pais_sku smallint,
    id_estado_sku smallint,
    fecha_inicial_registrado date,
    id_producto_tallas integer,
    id_producto_submarcas integer,
    id_rango_precios_sku smallint,
    texto_adicional character varying(50),
    descripcion_larga character varying(100),
    clave_homologada character varying(100),
    id_tipo_de_negocio_sku smallint,
    id_producto_colores smallint,
    id_temporada_sku smallint,
    id_tipo_mercado_sku smallint,
    costo numeric(16,2) DEFAULT 0.00,
    tipo_producto character varying(15),
    fecha_actualizacion date,
    variante_coordinado character varying(25),
    id_categoria smallint,
    id_producto_submarcas1 integer
);


--
-- Name: TABLE productos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.productos IS 'Tabla de Productos';


--
-- Name: COLUMN productos.id_producto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_producto IS 'ID de Producto';


--
-- Name: COLUMN productos.codigo_de_barras; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.codigo_de_barras IS 'CODIGO DE BARRAS';


--
-- Name: COLUMN productos.id_generico; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_generico IS 'Clave del Genérico del Material';


--
-- Name: COLUMN productos.descripcion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.descripcion IS 'Descripcion del material/producto';


--
-- Name: COLUMN productos.costo_neto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.costo_neto IS 'Importe del costo neto del producto';


--
-- Name: COLUMN productos.fecha_modificacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.fecha_modificacion IS 'Fecha Modificacion';


--
-- Name: COLUMN productos.id_importacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_importacion IS 'Clave de importación/DIM_PROD_EXTR.SKU_TIP_NEG_CVE';


--
-- Name: COLUMN productos.id_producto_nospot; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_producto_nospot IS 'Tabla extension de PRODUCTOS: Producto Aplica Servicios';


--
-- Name: COLUMN productos.descripcion_sector; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.descripcion_sector IS 'Descripcion del sector';


--
-- Name: COLUMN productos.precio_venta_sugerido; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.precio_venta_sugerido IS 'Importe sugerido de venta del producto';


--
-- Name: COLUMN productos.id_lic; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_lic IS 'CLave de LIC';


--
-- Name: COLUMN productos.id_coordinado; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_coordinado IS 'Clave de Coordinado';


--
-- Name: COLUMN productos.id_producto_grupos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_producto_grupos IS 'Tabla de Grupos de Productos';


--
-- Name: COLUMN productos.id_proveedor; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_proveedor IS 'PK del Proveedor';


--
-- Name: COLUMN productos.precio_venta; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.precio_venta IS 'Importe de venta del producto';


--
-- Name: COLUMN productos.precio_sugerido; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.precio_sugerido IS 'Importe sugerido del producto';


--
-- Name: COLUMN productos.id_empresa_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_empresa_sku IS 'Identificador';


--
-- Name: COLUMN productos.id_pais_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_pais_sku IS 'Clave del país al que pertenece el producto';


--
-- Name: COLUMN productos.id_estado_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_estado_sku IS 'Clave del Estado del SKU';


--
-- Name: COLUMN productos.fecha_inicial_registrado; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.fecha_inicial_registrado IS 'Fecha inicial del registro del producto';


--
-- Name: COLUMN productos.id_producto_tallas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_producto_tallas IS 'PK de la tabla de PRODUCTO_TALLAS';


--
-- Name: COLUMN productos.id_producto_submarcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_producto_submarcas IS 'PK de PRODUCTO_SUBMARCAS';


--
-- Name: COLUMN productos.id_rango_precios_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_rango_precios_sku IS 'Clave del rango de precio';


--
-- Name: COLUMN productos.texto_adicional; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.texto_adicional IS 'TEXTO ADICIONAL';


--
-- Name: COLUMN productos.descripcion_larga; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.descripcion_larga IS 'Descripcion larga del producto';


--
-- Name: COLUMN productos.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.clave_homologada IS 'Clave SKU en el sistema origen';


--
-- Name: COLUMN productos.id_tipo_de_negocio_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_tipo_de_negocio_sku IS 'Clave del tipo del negocio del producto/DIM_PROD_EXTR.SKU_TIP_NEG_CVE';


--
-- Name: COLUMN productos.id_producto_colores; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_producto_colores IS 'Clave del color';


--
-- Name: COLUMN productos.id_temporada_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_temporada_sku IS 'Clave de la temporada';


--
-- Name: COLUMN productos.id_tipo_mercado_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_tipo_mercado_sku IS 'Clave de Tipo Mercado';


--
-- Name: COLUMN productos.costo; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.costo IS 'Importe de costo del producto';


--
-- Name: COLUMN productos.tipo_producto; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.tipo_producto IS 'Tipo de Producto';


--
-- Name: COLUMN productos.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.fecha_actualizacion IS 'Fecha de Carga';


--
-- Name: COLUMN productos.variante_coordinado; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.variante_coordinado IS 'Variante de Coordinado';


--
-- Name: COLUMN productos.id_categoria; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_categoria IS 'Clave de la Categoria del Producto';


--
-- Name: COLUMN productos.id_producto_submarcas1; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.productos.id_producto_submarcas1 IS 'PK de la tabla PRODUCTO_MARCAS';


--
-- Name: proveedores; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.proveedores (
    id_proveedor integer NOT NULL,
    clave_homologada character varying(50),
    nombre_proveedor character varying(200),
    fecha_ingreso date,
    referencia character varying(100),
    clave_stt character varying,
    tipo_proveedor character varying(50),
    fecha_alta_en_sap date,
    clase character varying(50),
    fecha_actualizacion date,
    id_empresas_sku integer
);


--
-- Name: TABLE proveedores; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.proveedores IS 'Proveedores de Productos';


--
-- Name: COLUMN proveedores.id_proveedor; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.id_proveedor IS 'PK del Proveedor';


--
-- Name: COLUMN proveedores.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.clave_homologada IS 'Clave del proveedor';


--
-- Name: COLUMN proveedores.nombre_proveedor; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.nombre_proveedor IS 'PROV_CVE';


--
-- Name: COLUMN proveedores.fecha_ingreso; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.fecha_ingreso IS 'PROV_FCH_ING';


--
-- Name: COLUMN proveedores.referencia; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.referencia IS 'PROV_RFC';


--
-- Name: COLUMN proveedores.clave_stt; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.clave_stt IS 'PROV_STT_CVE';


--
-- Name: COLUMN proveedores.tipo_proveedor; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.tipo_proveedor IS 'PROV_TIPO';


--
-- Name: COLUMN proveedores.fecha_alta_en_sap; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.fecha_alta_en_sap IS 'PROV_FCH_ALT_SAP';


--
-- Name: COLUMN proveedores.clase; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.clase IS 'PROV_CLA';


--
-- Name: COLUMN proveedores.fecha_actualizacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.fecha_actualizacion IS 'FCH_CARGA';


--
-- Name: COLUMN proveedores.id_empresas_sku; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.proveedores.id_empresas_sku IS 'ID de Empresa';


--
-- Name: seq_impuestos_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_impuestos_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_impuestos_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_impuestos_pk OWNED BY shared.impuestos.id_impuesto;


--
-- Name: seq_medios_de_pago_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_medios_de_pago_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_medios_de_pago_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_medios_de_pago_pk OWNED BY shared.medios_de_pago.id_medio_de_pago;


--
-- Name: seq_producto_aplica_servicios_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_aplica_servicios_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_aplica_servicios_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_aplica_servicios_pk OWNED BY shared.producto_aplica_servicios.id_producto_aplica_servicios;


--
-- Name: seq_producto_bigticket_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_bigticket_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_bigticket_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_bigticket_pk OWNED BY shared.producto_bigticket.id_producto_bigticket;


--
-- Name: seq_producto_direcciones_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_direcciones_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_direcciones_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_direcciones_pk OWNED BY shared.producto_direcciones.id_producto_direccion;


--
-- Name: seq_producto_grupos_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_grupos_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_grupos_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_grupos_pk OWNED BY shared.producto_grupos.id_producto_grupo;


--
-- Name: seq_producto_marcas_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_marcas_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_marcas_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_marcas_pk OWNED BY shared.producto_marcas.id_producto_marcas;


--
-- Name: seq_producto_secciones_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_secciones_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_secciones_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_secciones_pk OWNED BY shared.producto_secciones.id_producto_seccion_sku;


--
-- Name: seq_producto_submarcas_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_submarcas_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_submarcas_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_submarcas_pk OWNED BY shared.producto_submarcas.id_producto_submarcas;


--
-- Name: seq_producto_tallas_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_producto_tallas_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_producto_tallas_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_producto_tallas_pk OWNED BY shared.producto_tallas.id_producto_tallas;


--
-- Name: seq_productos_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_productos_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_productos_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_productos_pk OWNED BY shared.productos.id_producto;


--
-- Name: seq_proveedores_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_proveedores_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_proveedores_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_proveedores_pk OWNED BY shared.proveedores.id_proveedor;


--
-- Name: tiendas_y_locaciones; Type: TABLE; Schema: shared; Owner: -
--

CREATE TABLE shared.tiendas_y_locaciones (
    id_tienda_o_locacion integer NOT NULL,
    descripcion_tienda_o_locacion character varying(50),
    zona_de_ventas smallint,
    id_tipo_tienda character varying(25),
    clave_homologada character varying(100),
    id_colonia integer NOT NULL,
    id_ciudad integer,
    id_municipio integer,
    id_entidad_federativa integer,
    cantidad_trailers integer,
    cantidad_envios_cort integer,
    longitud character varying(50),
    punto_venta_metros_cuadrados integer,
    total_metros_cuadrados integer,
    cantidad_empleados integer,
    fecha_inicio date,
    fecha_fin date,
    fecha_apertura date,
    fecha_cierre date,
    fecha_apertura_credito date,
    banner character varying(100),
    fecha_modificacion_banner date,
    nombre_direccion character varying(50),
    telefono1 character varying(50),
    telefono character varying(50),
    pisos character varying(50),
    codigo_postal character varying(50),
    latitud character varying(50),
    fecha_carga date,
    renta_liverpool character varying(100),
    cantidad_entradas smallint,
    cantidad_estacionamientos integer,
    id_zona_locacion integer,
    id_pais integer,
    id_grupo_tienda integer,
    cantidad_recepciones_cort smallint,
    calle character varying(200),
    fecha_ultima_remodelacion date
);


--
-- Name: TABLE tiendas_y_locaciones; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON TABLE shared.tiendas_y_locaciones IS 'Tabla de Tiendas';


--
-- Name: COLUMN tiendas_y_locaciones.id_tienda_o_locacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_tienda_o_locacion IS 'PK de Tienda o Locacion';


--
-- Name: COLUMN tiendas_y_locaciones.descripcion_tienda_o_locacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.descripcion_tienda_o_locacion IS 'Descripcion de la tienda o locacion';


--
-- Name: COLUMN tiendas_y_locaciones.zona_de_ventas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.zona_de_ventas IS 'Zona de Ventas y BODEGA NACIONAL, BODEGA REGIONAL o TIENDA';


--
-- Name: COLUMN tiendas_y_locaciones.id_tipo_tienda; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_tipo_tienda IS 'Tipo de tienda';


--
-- Name: COLUMN tiendas_y_locaciones.clave_homologada; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.clave_homologada IS 'Clave de la tienda (en el sistema origen)';


--
-- Name: COLUMN tiendas_y_locaciones.id_colonia; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_colonia IS 'Identificador';


--
-- Name: COLUMN tiendas_y_locaciones.id_ciudad; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_ciudad IS 'Ciudad';


--
-- Name: COLUMN tiendas_y_locaciones.id_municipio; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_municipio IS 'Municipio';


--
-- Name: COLUMN tiendas_y_locaciones.id_entidad_federativa; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_entidad_federativa IS 'FK de Entidad Federativa';


--
-- Name: COLUMN tiendas_y_locaciones.cantidad_trailers; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.cantidad_trailers IS 'Cantidad de trailers';


--
-- Name: COLUMN tiendas_y_locaciones.cantidad_envios_cort; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.cantidad_envios_cort IS 'Cantidad de envíos de cort';


--
-- Name: COLUMN tiendas_y_locaciones.longitud; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.longitud IS 'LONGITUD';


--
-- Name: COLUMN tiendas_y_locaciones.punto_venta_metros_cuadrados; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.punto_venta_metros_cuadrados IS 'Punto de venta metros cuadrados';


--
-- Name: COLUMN tiendas_y_locaciones.total_metros_cuadrados; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.total_metros_cuadrados IS 'Total de metros cuadrados';


--
-- Name: COLUMN tiendas_y_locaciones.cantidad_empleados; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.cantidad_empleados IS 'Cantidad de empleados';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_inicio; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_inicio IS 'Fecha inicio';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_fin; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_fin IS 'FECHA_FIN';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_apertura; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_apertura IS 'FECHA_APERTURA';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_cierre; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_cierre IS 'FECHA_CIERRE';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_apertura_credito; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_apertura_credito IS 'FECHA_APERTURA_CREDITO';


--
-- Name: COLUMN tiendas_y_locaciones.banner; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.banner IS 'BANNER';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_modificacion_banner; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_modificacion_banner IS 'FECHA_MODIFICACION_BANNER';


--
-- Name: COLUMN tiendas_y_locaciones.nombre_direccion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.nombre_direccion IS 'Nombre de direccion';


--
-- Name: COLUMN tiendas_y_locaciones.telefono1; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.telefono1 IS 'TELEFONO';


--
-- Name: COLUMN tiendas_y_locaciones.telefono; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.telefono IS 'Numero de telefono';


--
-- Name: COLUMN tiendas_y_locaciones.pisos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.pisos IS 'PISOS';


--
-- Name: COLUMN tiendas_y_locaciones.codigo_postal; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.codigo_postal IS 'Codigo postal';


--
-- Name: COLUMN tiendas_y_locaciones.latitud; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.latitud IS 'LATITUD';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_carga; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_carga IS 'Fecha de carga';


--
-- Name: COLUMN tiendas_y_locaciones.renta_liverpool; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.renta_liverpool IS 'Renta Liverpool';


--
-- Name: COLUMN tiendas_y_locaciones.cantidad_entradas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.cantidad_entradas IS 'Cantidad de entradas';


--
-- Name: COLUMN tiendas_y_locaciones.cantidad_estacionamientos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.cantidad_estacionamientos IS 'Cantidad lugares de estacionamiento';


--
-- Name: COLUMN tiendas_y_locaciones.id_zona_locacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_zona_locacion IS 'Zona de la locacion';


--
-- Name: COLUMN tiendas_y_locaciones.id_pais; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_pais IS 'PAIS';


--
-- Name: COLUMN tiendas_y_locaciones.id_grupo_tienda; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.id_grupo_tienda IS 'Grupo de tienda';


--
-- Name: COLUMN tiendas_y_locaciones.cantidad_recepciones_cort; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.cantidad_recepciones_cort IS 'Cantidad de recepciones de cort';


--
-- Name: COLUMN tiendas_y_locaciones.calle; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.calle IS 'Calle TDA_DES_CLL';


--
-- Name: COLUMN tiendas_y_locaciones.fecha_ultima_remodelacion; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON COLUMN shared.tiendas_y_locaciones.fecha_ultima_remodelacion IS 'Ultima remodelacion TDA_FCH_ULT_REMOD';


--
-- Name: seq_tiendas_y_locaciones_pk; Type: SEQUENCE; Schema: shared; Owner: -
--

CREATE SEQUENCE shared.seq_tiendas_y_locaciones_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: seq_tiendas_y_locaciones_pk; Type: SEQUENCE OWNED BY; Schema: shared; Owner: -
--

ALTER SEQUENCE shared.seq_tiendas_y_locaciones_pk OWNED BY shared.tiendas_y_locaciones.id_tienda_o_locacion;


--
-- Name: seq_boletas_cifra_de_control_tmp_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_boletas_cifra_de_control_tmp_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: boletas_cifra_de_control_tmp; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.boletas_cifra_de_control_tmp (
    id_boletas_cifra_de_control integer DEFAULT nextval('staging.seq_boletas_cifra_de_control_tmp_pk'::regclass) NOT NULL,
    fecha_tlog date,
    centro character varying(100),
    id_tipo_de_transaccion integer,
    tipo_de_transaccion character varying(100),
    total_de_las_transacciones numeric(36,2) DEFAULT 0.00
);


--
-- Name: seq_boletas_en_buzon_forma_pago_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_boletas_en_buzon_forma_pago_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: boletas_en_buzon_forma_pago; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.boletas_en_buzon_forma_pago (
    id_boletas_forma_pago integer DEFAULT nextval('staging.seq_boletas_en_buzon_forma_pago_pk'::regclass) NOT NULL,
    id_cliente_clientes character varying(100),
    hora character varying(100),
    fecha date,
    id_tienda character varying(100),
    numero_de_ticket character varying(100),
    id_terminal_pos character varying(100),
    numero_boleta_boletas_en_buzon character varying(100),
    numero_de_renglon character varying(100),
    id_empresa character varying(100),
    id_tipo_transaccion character varying(100),
    id_tarjeta character varying(100),
    llave_subrogada_tarjeta_enmascarada character varying(100),
    id_medio_ingreso_clave_de_tarjeta character varying(100),
    numero_autorizacion_bancaria character varying(100),
    id_tipo_de_pago character varying(100),
    total_transaccion numeric(16,2) DEFAULT 0.00,
    monto_impuesto1 numeric(16,2) DEFAULT 0.00,
    monto_impuesto2 numeric(16,2) DEFAULT 0.00,
    monto_impuesto3 numeric(16,2) DEFAULT 0.00,
    total_antes_de_impuesto numeric(16,2) DEFAULT 0.00,
    costo_de_msi numeric(16,2) DEFAULT 0.00,
    monto_impuesto4 numeric(16,2) DEFAULT 0.00,
    numero_de_cuenta character varying(100),
    llave_subrogada_cuenta_enmascarada character varying(100),
    id_plan character varying(100),
    id_vendedor character varying(100),
    id_banco character varying(100),
    estado_transaccion character varying(100),
    bandera_transaccion_es_de_buzon character varying(100),
    id_naturaleza_de_emision_de_tarjeta character varying(100),
    id_tienda_recepcion character varying(100),
    id_canal character varying(100),
    id_dispositivo_venta character varying(100),
    bandera_ejecucion_token character varying(100),
    boleta_original_de_buzon character varying(100),
    fecha_original_de_buzon character varying(100),
    hash_tarjeta character varying(100)
);


--
-- Name: seq_boletas_en_buzon_motivo_dev_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_boletas_en_buzon_motivo_dev_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: boletas_en_buzon_motivo_dev; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.boletas_en_buzon_motivo_dev (
    id_boletas_motivo_dev integer DEFAULT nextval('staging.seq_boletas_en_buzon_motivo_dev_pk'::regclass) NOT NULL,
    clave_devolucion character varying(100),
    motivo_devolucion character varying(100),
    fecha_inicio_transaccion character varying(100),
    fecha_fin_transaccion character varying(100)
);


--
-- Name: seq_boletas_en_buzon_sku_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_boletas_en_buzon_sku_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: boletas_en_buzon_sku; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.boletas_en_buzon_sku (
    id_boletas_sku integer DEFAULT nextval('staging.seq_boletas_en_buzon_sku_pk'::regclass) NOT NULL,
    ticket character varying(100),
    id_tienda character varying(100),
    fecha_transaccion date,
    id_terminal_pos character varying(100),
    numero_boleta character varying(100),
    numero_transaccion character varying(100),
    id_empresa character varying(100),
    id_vendedor character varying(100),
    id_cliente character varying(100),
    id_tipo_transaccion character varying(100),
    estado_transaccion character varying(100),
    numero_cuenta character varying(100),
    numero_cuenta_subrogada character varying(100),
    total_boleta numeric(16,2) DEFAULT 0.00,
    total_rebajas_sku_boleta numeric(16,2) DEFAULT 0.00,
    total_descuento_boleta numeric(16,2) DEFAULT 0.00,
    id_tipo_descuento character varying(100),
    hora_transaccion character varying(100),
    numero_evento character varying(100),
    numero_entrega_soms character varying(100),
    numero_telefono character varying(100),
    numero_orden_optica character varying(100),
    promocion_crm character varying(100),
    id_cliente_promo_crm character varying(100),
    id_cliente_kybela character varying(100),
    promo_itunes character varying(100),
    certificado_itunes character varying(100),
    cantidad_articulos integer,
    total_pagado_real numeric(16,2) DEFAULT 0.00,
    clave_homologada character varying(100),
    monto_rebaja numeric(16,2) DEFAULT 0.00,
    monto_descuento numeric(16,2) DEFAULT 0.00,
    total_por_sku numeric(16,2) DEFAULT 0.00,
    monto_impuesto numeric(16,2) DEFAULT 0.00,
    monto_impuesto1 numeric(16,2) DEFAULT 0.00,
    monto_impuesto2 numeric(16,2) DEFAULT 0.00,
    monto_impuesto3 numeric(16,2) DEFAULT 0.00,
    total_antes_de_impuesto numeric(16,2) DEFAULT 0.00,
    bandera_id_trx_de_mercancia character varying(100),
    bandera_id_trx_con_monedero character varying(100),
    bandera_id_trx_con_mesa_de_regalo character varying(100),
    bandera_id_trx_de_buzon character varying(100),
    id_tienda_original character varying(100),
    fecha_original_compra date,
    id_terminal_pos_2 character varying(100),
    numero_boleta_original character varying(100),
    id_vendedor_2 character varying(100),
    precio_actual_sku numeric(16,2) DEFAULT 0.00,
    bandera_id_trx_de_invitado character varying(100),
    id_motivo_devolucion character varying(100),
    id_tienda_recepcion character varying(100),
    id_canal character varying(100),
    id_dispositivo_venta character varying(100),
    id_cliente_original character varying(100),
    bandera_ejecucion_token character varying(100),
    bandera_original_buzon character varying(100),
    fecha_original_buzon character varying(100),
    hash_tarjeta character varying(100)
);


--
-- Name: seq_boletas_en_buzon_tipo_tsx_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_boletas_en_buzon_tipo_tsx_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: boletas_en_buzon_tipo_tsx; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.boletas_en_buzon_tipo_tsx (
    id_boletas_tipo_tsx integer DEFAULT nextval('staging.seq_boletas_en_buzon_tipo_tsx_pk'::regclass) NOT NULL,
    id_tipo_transaccion character varying(100),
    descripcion_catalogo character varying(100),
    estado_transaccion character varying(100),
    descripcion_estado_transaccion character varying(100),
    id_operacion character varying(100)
);


--
-- Name: seq_catalogos_producto_tmp_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_catalogos_producto_tmp_pk
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: catalogos_producto_tmp; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.catalogos_producto_tmp (
    id_catalogos_producto_tmp integer DEFAULT nextval('staging.seq_catalogos_producto_tmp_pk'::regclass) NOT NULL,
    clave_homologada character varying(25),
    descripcion character varying(50),
    descripcion_2 character varying(50),
    fecha_actualizacion date,
    empresa_sku character varying(25),
    catalogo_padre character varying(25),
    estado integer DEFAULT 0 NOT NULL
);


--
-- Name: seq_cliente_formas_de_pago_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_cliente_formas_de_pago_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cliente_formas_de_pago; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.cliente_formas_de_pago (
    id_staging_cliente_formas_de_pago integer DEFAULT nextval('staging.seq_cliente_formas_de_pago_pk'::regclass) NOT NULL,
    numero_tarjeta_cliente character varying(100),
    rowid character varying(100),
    sistema_fuente character varying(100),
    bin_tarjeta character varying(100),
    hash_tarjeta_externa character varying(100)
);


--
-- Name: cliente_mdm; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.cliente_mdm (
    id_cliente_mdm integer DEFAULT nextval('clientes.seq_clientes_pk'::regclass) NOT NULL,
    row_id character varying(25),
    id_origen character varying(100),
    primer_nombre character varying(100),
    apellido_paterno character varying(100),
    apellido_materno character varying(100),
    fecha_de_nacimiento date,
    genero character varying(25),
    rfc character varying(25),
    bloque integer,
    homonimo character varying(100),
    fecha_creacion date,
    fecha_actualizacion date
);


--
-- Name: seq_clientes_atg_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_clientes_atg_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: clientes_atg; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.clientes_atg (
    id_clientes_atg integer DEFAULT nextval('staging.seq_clientes_atg_pk'::regclass) NOT NULL,
    sistema_fuente_atg character varying(100),
    id_atg character varying(100),
    primer_nombre character varying(100),
    segundo_nombre character varying(100),
    apellido_paterno character varying(100),
    apellido_materno character varying(100),
    genero character varying(100),
    fecha_de_nacimiento character varying(100),
    rfc character varying(100),
    fecha_de_registro character varying(100),
    email_tipo_cuentas character varying(100),
    email_tipo_notificaciones character varying(100),
    telefono_alias_telefono_casa character varying(100),
    telefono_alias_telefono_celular character varying(100),
    telefono_alias_telefono_trabajo character varying(100),
    telefono_alias_telefono_adicional character varying(100),
    id_tipo_direccion_cliente character varying(100),
    calle character varying(100),
    numero_exterior character varying(100),
    numero_interior character varying(100),
    edificio character varying(100),
    id_estado_del_pais character varying(100),
    id_municipio character varying(100),
    id_colonia character varying(100),
    codigo_postal character varying(100),
    alias_de_direccion character varying(100),
    hash_1 character varying(100),
    id_tipo_tarjeta character varying(100),
    realm_id character varying(100),
    id_facebook character varying(100),
    id_apple character varying(100),
    id_sistema_operativo_dispositivo character varying(100),
    baja_de_tarjeta character varying(100),
    alta_de_tarjeta character varying(100),
    baja_domicilio character varying(100)
);


--
-- Name: seq_contacto_mdm_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_contacto_mdm_pk
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: contacto_mdm; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.contacto_mdm (
    id_contacto_mdm integer DEFAULT nextval('staging.seq_contacto_mdm_pk'::regclass) NOT NULL,
    row_id character varying(25),
    medio_contacto character varying(100),
    tipo character varying(100),
    fuente character varying(100),
    tipo_contacto character varying(100),
    fecha_creacion date,
    fecha_actualizacion date,
    id_origen_externo character varying(100)
);


--
-- Name: seq_cu_match_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_cu_match_pk
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: cu_match; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.cu_match (
    id_staging_cu_match integer DEFAULT nextval('staging.seq_cu_match_pk'::regclass) NOT NULL,
    rowid character varying(100),
    componente bigint,
    bandera_cu integer,
    cu_track character varying(100)
);


--
-- Name: seq_direccion_mdm_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_direccion_mdm_pk
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: direccion_mdm; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.direccion_mdm (
    id_direccion_mdm integer DEFAULT nextval('staging.seq_direccion_mdm_pk'::regclass) NOT NULL,
    row_id character varying(25),
    calle character varying(100),
    numero_exterior character varying(100),
    numero_interior character varying(100),
    edificio character varying(100),
    id_estado character varying(100),
    estado character varying(100),
    id_municipio character varying(100),
    municipio character varying(100),
    id_colonia character varying(100),
    colonia character varying(100),
    codigo_postal character varying(100),
    entre_calle1 character varying(100),
    entre_calle2 character varying(100),
    pais character varying(100),
    id_sistema_origen character varying(100),
    fecha_creacion date,
    fecha_actualizacion date
);


--
-- Name: pedidos_seq; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.pedidos_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: pedidos_soms; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.pedidos_soms (
    pedidos_id integer DEFAULT nextval('staging.pedidos_seq'::regclass) NOT NULL,
    numero_del_documento character varying(10) NOT NULL,
    id_sku integer NOT NULL,
    piezas numeric(16,2) DEFAULT 0.00,
    numero_de_intentos_de_entrega integer,
    estado_por_linea character varying(255),
    fecha_de_compra date,
    fecha_actualizacion_estado date,
    hora_actualizacion_estado time without time zone,
    usuario_actualizador_estado character varying(255),
    id_tipo_entrega character varying(255),
    numero_de_guia character varying(255),
    proveedor_de_mensajeria character varying(255),
    id_cliente_remitente integer,
    nombre_remitente character varying(255),
    telefono_celular_remitente character varying(255),
    telefono_fijo_remitente character varying(255),
    telefono_oficina_remitente character varying(255),
    correo_electronico_remitente character varying(255),
    id_cliente_destinatario integer,
    nombre_destinatario character varying(255),
    apellido_paterno_destinatario character varying(255),
    apellido_materno_destinatario character varying(255),
    calle_destinatario character varying(255),
    numero_exterior_destinatario character varying(255),
    numero_interior_destinatario character varying(255),
    codigo_postal_destinatario character varying(255),
    colonia_destinatario character varying(255),
    municipio_destinatario character varying(255),
    estado_destinatario character varying(255),
    entre_calle_destinatario character varying(255),
    y_calle_destinatario character varying(255),
    telefono_celular_destinatario character varying(255),
    telefono_fijo_destinatario character varying(255),
    telefono_oficina_destinatario character varying(255),
    correo_electronico_destinatario character varying(255),
    fecha_rango_promesa_entrega date,
    fecha_recalculada date,
    fecha_real_entrega date,
    indicador_mkp character varying(255),
    id_tipo_documento character varying(255),
    id_tipo_act character varying(255),
    hora_emision time without time zone,
    fecha_emision date,
    locacion_destino integer,
    locacion_que_surte integer,
    fecha_surtido date,
    cantidad_recogida numeric(16,2) DEFAULT 0.00,
    causa_no_entrega character varying(255),
    frecuencia_visita character varying(255),
    periodicidad character varying(255),
    cantidad_cancelada numeric(16,2) DEFAULT 0.00,
    cantidad_entregada numeric(16,2) DEFAULT 0.00,
    observaciones character varying(255),
    orden_original character varying(255)
);


--
-- Name: TABLE pedidos_soms; Type: COMMENT; Schema: staging; Owner: -
--

COMMENT ON TABLE staging.pedidos_soms IS 'Informacion temporal para Ingesta de Pedidos';


--
-- Name: seq_producto_grupos_tmp_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_producto_grupos_tmp_pk
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: producto_grupos_tmp; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.producto_grupos_tmp (
    id_producto_grupos_tmp integer DEFAULT nextval('staging.seq_producto_grupos_tmp_pk'::regclass) NOT NULL,
    grupo_del_articulo character varying(50),
    descripcion_del_grupo character varying(50),
    empresa_sku character varying(50),
    producto_secciones_sku character varying(50),
    clase_sku character varying(50),
    mundo_negocios character varying(50),
    comprador_corporativo character varying(50),
    fecha_actualizacion date,
    estado integer DEFAULT 0 NOT NULL
);


--
-- Name: seq_productos_tmp_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_productos_tmp_pk
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: productos_tmp; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.productos_tmp (
    id_productos_tmp integer DEFAULT nextval('staging.seq_productos_tmp_pk'::regclass) NOT NULL,
    codigo_de_barras character varying(50),
    id_generico character varying(15),
    descripcion character varying(50),
    costo_neto numeric(16,2) DEFAULT 0.00,
    fecha_modificacion date,
    id_importacion character varying(25),
    producto_nospot character varying(100),
    descripcion_sector character varying(100),
    precio_venta_sugerido numeric(16,2) DEFAULT 0.00,
    id_lic character varying(25),
    id_coordinado character varying(25),
    producto_grupos character varying(100),
    proveedor character varying(100),
    precio_venta numeric(16,2) DEFAULT 0.00,
    precio_sugerido numeric(16,2) DEFAULT 0.00,
    empresa_sku character varying(100),
    pais_sku character varying(100),
    estado_sku character varying(100),
    fecha_inicial_registrado date,
    producto_tallas character varying(100),
    producto_submarcas character varying(100),
    rango_precios_sku character varying(100),
    texto_adicional character varying(50),
    descripcion_larga character varying(100),
    clave_homologada character varying(100),
    tipo_de_negocio_sku character varying(100),
    producto_colores character varying(100),
    temporada_sku character varying(100),
    tipo_mercado_sku character varying(100),
    costo numeric(16,2) DEFAULT 0.00,
    tipo_producto character varying(15),
    fecha_actualizacion date,
    variante_coordinado character varying(25),
    categoria character varying(100),
    producto_submarcas1 character varying(100)
);


--
-- Name: seq_referencia_mdm_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_referencia_mdm_pk
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: referencia_mdm; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.referencia_mdm (
    id_referencia_mdm integer DEFAULT nextval('staging.seq_referencia_mdm_pk'::regclass) NOT NULL,
    row_id character varying(25),
    id_origen character varying(100),
    referencia character varying(100),
    fuente character varying(100),
    fecha_de_carga date,
    fecha_actualizacion date
);


--
-- Name: seq_tiendas_y_locaciones_tmp_pk; Type: SEQUENCE; Schema: staging; Owner: -
--

CREATE SEQUENCE staging.seq_tiendas_y_locaciones_tmp_pk
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


--
-- Name: tiendas_y_locaciones_tmp; Type: TABLE; Schema: staging; Owner: -
--

CREATE TABLE staging.tiendas_y_locaciones_tmp (
    id_tiendas_y_locaciones_tmp integer DEFAULT nextval('staging.seq_tiendas_y_locaciones_tmp_pk'::regclass) NOT NULL,
    clave_homologada character varying(100),
    descripcion_tienda_o_locacion character varying(100),
    id_tipo_tienda character varying(100),
    calle character varying(100),
    colonia character varying(100),
    ciudad character varying(100),
    municipio character varying(100),
    entidad_federativa character varying(100),
    telefono character varying(100),
    pisos character varying(100),
    codigo_postal character varying(100),
    latitud character varying(100),
    longitud character varying(100),
    punto_venta_metros_cuadrados integer,
    total_metros_cuadrados integer,
    cantidad_empleados integer,
    banner character varying(100),
    fecha_modificacion_banner date,
    nombre_direccion character varying(100),
    telefono1 character varying(100),
    renta_liverpool character varying(100),
    cantidad_entradas integer,
    cantidad_estacionamientos integer,
    cantidad_recepciones_cort integer,
    cantidad_trailers integer,
    cantidad_envios_cort integer,
    fecha_ultima_remodelacion date,
    fecha_inicio date,
    fecha_fin date,
    fecha_apertura date,
    fecha_cierre date,
    fecha_apertura_credito date,
    zona_locacion character varying(100),
    pais character varying(100),
    grupo_tienda character varying(100),
    fecha_carga date
);


--
-- Name: boletas_cifra_de_control id_boletas_cifra_de_control; Type: DEFAULT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_cifra_de_control ALTER COLUMN id_boletas_cifra_de_control SET DEFAULT nextval('boletas.seq_boletas_cifra_de_control_pk'::regclass);


--
-- Name: cliente_direcciones id_direccion_del_cliente; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones ALTER COLUMN id_direccion_del_cliente SET DEFAULT nextval('clientes.seq_cliente_direcciones_pk'::regclass);


--
-- Name: cliente_direcciones_bitacora id_direccion_del_cliente; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones_bitacora ALTER COLUMN id_direccion_del_cliente SET DEFAULT nextval('clientes.seq_cliente_direcciones_bitacora_pk'::regclass);


--
-- Name: cliente_emails_de_contacto id_cliente_emails_de_contacto; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto ALTER COLUMN id_cliente_emails_de_contacto SET DEFAULT nextval('clientes.seq_cliente_emails_de_contacto_pk'::regclass);


--
-- Name: cliente_emails_de_contacto_bitacora id_cliente_emails_de_contacto; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto_bitacora ALTER COLUMN id_cliente_emails_de_contacto SET DEFAULT nextval('clientes.seq_cliente_emails_de_contacto_bitacora_pk'::regclass);


--
-- Name: cliente_formas_de_pago id_cliente_tarjeta; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago ALTER COLUMN id_cliente_tarjeta SET DEFAULT nextval('clientes.seq_cliente_formas_de_pago_pk'::regclass);


--
-- Name: cliente_formas_de_pago_bitacora id_cliente_tarjeta; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago_bitacora ALTER COLUMN id_cliente_tarjeta SET DEFAULT nextval('clientes.seq_cliente_formas_de_pago_bitacora_pk'::regclass);


--
-- Name: cliente_ids id_cliente_ids; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids ALTER COLUMN id_cliente_ids SET DEFAULT nextval('clientes.seq_cliente_ids_pk'::regclass);


--
-- Name: cliente_ids_bitacora id_cliente_ids; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids_bitacora ALTER COLUMN id_cliente_ids SET DEFAULT nextval('clientes.seq_cliente_ids_bitacora_pk'::regclass);


--
-- Name: cliente_telefonos_de_contacto id_cliente_telefonos_de_contacto; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto ALTER COLUMN id_cliente_telefonos_de_contacto SET DEFAULT nextval('clientes.seq_cliente_telefonos_de_contacto_pk'::regclass);


--
-- Name: cliente_telefonos_de_contacto_bitacora id_cliente_telefonos_de_contacto; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto_bitacora ALTER COLUMN id_cliente_telefonos_de_contacto SET DEFAULT nextval('clientes.seq_cliente_telefonos_de_contacto_bitacora_pk'::regclass);


--
-- Name: cliente_ticket_electronico id_cliente_ticket_electronico; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ticket_electronico ALTER COLUMN id_cliente_ticket_electronico SET DEFAULT nextval('clientes.seq_cliente_ticket_electronico_pk'::regclass);


--
-- Name: clientes id_cliente; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('clientes.seq_clientes_pk'::regclass);


--
-- Name: clientes_bitacora id_cliente; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes_bitacora ALTER COLUMN id_cliente SET DEFAULT nextval('clientes.seq_clientes_bitacora_pk'::regclass);


--
-- Name: clientes_merge id_cliente_unico; Type: DEFAULT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes_merge ALTER COLUMN id_cliente_unico SET DEFAULT nextval('clientes.seq_clientes_merge_pk'::regclass);


--
-- Name: impuestos id_impuesto; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.impuestos ALTER COLUMN id_impuesto SET DEFAULT nextval('shared.seq_impuestos_pk'::regclass);


--
-- Name: medios_de_pago id_medio_de_pago; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.medios_de_pago ALTER COLUMN id_medio_de_pago SET DEFAULT nextval('shared.seq_medios_de_pago_pk'::regclass);


--
-- Name: producto_aplica_servicios id_producto_aplica_servicios; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_aplica_servicios ALTER COLUMN id_producto_aplica_servicios SET DEFAULT nextval('shared.seq_producto_aplica_servicios_pk'::regclass);


--
-- Name: producto_bigticket id_producto_bigticket; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_bigticket ALTER COLUMN id_producto_bigticket SET DEFAULT nextval('shared.seq_producto_bigticket_pk'::regclass);


--
-- Name: producto_direcciones id_producto_direccion; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_direcciones ALTER COLUMN id_producto_direccion SET DEFAULT nextval('shared.seq_producto_direcciones_pk'::regclass);


--
-- Name: producto_grupos id_producto_grupo; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_grupos ALTER COLUMN id_producto_grupo SET DEFAULT nextval('shared.seq_producto_grupos_pk'::regclass);


--
-- Name: producto_marcas id_producto_marcas; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_marcas ALTER COLUMN id_producto_marcas SET DEFAULT nextval('shared.seq_producto_marcas_pk'::regclass);


--
-- Name: producto_secciones id_producto_seccion_sku; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_secciones ALTER COLUMN id_producto_seccion_sku SET DEFAULT nextval('shared.seq_producto_secciones_pk'::regclass);


--
-- Name: producto_submarcas id_producto_submarcas; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_submarcas ALTER COLUMN id_producto_submarcas SET DEFAULT nextval('shared.seq_producto_submarcas_pk'::regclass);


--
-- Name: producto_tallas id_producto_tallas; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_tallas ALTER COLUMN id_producto_tallas SET DEFAULT nextval('shared.seq_producto_tallas_pk'::regclass);


--
-- Name: productos id_producto; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos ALTER COLUMN id_producto SET DEFAULT nextval('shared.seq_productos_pk'::regclass);


--
-- Name: proveedores id_proveedor; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.proveedores ALTER COLUMN id_proveedor SET DEFAULT nextval('shared.seq_proveedores_pk'::regclass);


--
-- Name: tiendas_y_locaciones id_tienda_o_locacion; Type: DEFAULT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones ALTER COLUMN id_tienda_o_locacion SET DEFAULT nextval('shared.seq_tiendas_y_locaciones_pk'::regclass);

 
--
-- Name: seq_boletas_cifra_de_control_pk; Type: SEQUENCE SET; Schema: boletas; Owner: -
--

SELECT pg_catalog.setval('boletas.seq_boletas_cifra_de_control_pk', 50, true);


--
-- Name: seq_cliente_atg_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_atg_pk', 40, true);


--
-- Name: seq_cliente_direcciones_bitacora_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_direcciones_bitacora_pk', 1, false);


--
-- Name: seq_cliente_direcciones_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_direcciones_pk', 92, true);


--
-- Name: seq_cliente_emails_de_contacto_bitacora_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_emails_de_contacto_bitacora_pk', 1, false);


--
-- Name: seq_cliente_emails_de_contacto_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_emails_de_contacto_pk', 12349, true);


--
-- Name: seq_cliente_formas_de_pago_bitacora_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_formas_de_pago_bitacora_pk', 1, false);


--
-- Name: seq_cliente_formas_de_pago_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_formas_de_pago_pk', 105, true);


--
-- Name: seq_cliente_ids_bitacora_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_ids_bitacora_pk', 1, false);


--
-- Name: seq_cliente_ids_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_ids_pk', 27, true);


--
-- Name: seq_cliente_telefonos_de_contacto_bitacora_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_telefonos_de_contacto_bitacora_pk', 1, false);


--
-- Name: seq_cliente_telefonos_de_contacto_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_telefonos_de_contacto_pk', 19093, true);


--
-- Name: seq_cliente_ticket_electronico_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_cliente_ticket_electronico_pk', 15, true);


--
-- Name: seq_clientes_bitacora_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_clientes_bitacora_pk', 1, false);


--
-- Name: seq_clientes_merge_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_clientes_merge_pk', 33, true);


--
-- Name: seq_clientes_pk; Type: SEQUENCE SET; Schema: clientes; Owner: -
--

SELECT pg_catalog.setval('clientes.seq_clientes_pk', 158, true);


--
-- Name: seq_catalogos_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_catalogos_pk', 1311, true);


--
-- Name: seq_impuestos_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_impuestos_pk', 1, false);


--
-- Name: seq_medios_de_pago_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_medios_de_pago_pk', 9, true);


--
-- Name: seq_producto_aplica_servicios_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_aplica_servicios_pk', 70, true);


--
-- Name: seq_producto_bigticket_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_bigticket_pk', 24, true);


--
-- Name: seq_producto_direcciones_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_direcciones_pk', 13, true);


--
-- Name: seq_producto_grupos_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_grupos_pk', 3, true);


--
-- Name: seq_producto_marcas_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_marcas_pk', 18, true);


--
-- Name: seq_producto_secciones_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_secciones_pk', 3, true);


--
-- Name: seq_producto_submarcas_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_submarcas_pk', 2, true);


--
-- Name: seq_producto_tallas_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_producto_tallas_pk', 23, true);


--
-- Name: seq_productos_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_productos_pk', 2, true);


--
-- Name: seq_proveedores_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_proveedores_pk', 60, true);


--
-- Name: seq_tiendas_y_locaciones_pk; Type: SEQUENCE SET; Schema: shared; Owner: -
--

SELECT pg_catalog.setval('shared.seq_tiendas_y_locaciones_pk', 108, true);


--
-- Name: pedidos_seq; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.pedidos_seq', 792, true);


--
-- Name: seq_boletas_cifra_de_control_tmp_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_boletas_cifra_de_control_tmp_pk', 40, true);


--
-- Name: seq_boletas_en_buzon_forma_pago_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_boletas_en_buzon_forma_pago_pk', 50, true);


--
-- Name: seq_boletas_en_buzon_motivo_dev_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_boletas_en_buzon_motivo_dev_pk', 20, true);


--
-- Name: seq_boletas_en_buzon_sku_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_boletas_en_buzon_sku_pk', 30, true);


--
-- Name: seq_boletas_en_buzon_tipo_tsx_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_boletas_en_buzon_tipo_tsx_pk', 10, true);


--
-- Name: seq_catalogos_producto_tmp_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_catalogos_producto_tmp_pk', 455, true);


--
-- Name: seq_cliente_formas_de_pago_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_cliente_formas_de_pago_pk', 54, true);


--
-- Name: seq_clientes_atg_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_clientes_atg_pk', 8, true);


--
-- Name: seq_contacto_mdm_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_contacto_mdm_pk', 10, true);


--
-- Name: seq_cu_match_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_cu_match_pk', 40, true);


--
-- Name: seq_direccion_mdm_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_direccion_mdm_pk', 50, true);


--
-- Name: seq_producto_grupos_tmp_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_producto_grupos_tmp_pk', 40, true);


--
-- Name: seq_productos_tmp_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_productos_tmp_pk', 10, true);


--
-- Name: seq_referencia_mdm_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_referencia_mdm_pk', 1, false);


--
-- Name: seq_tiendas_y_locaciones_tmp_pk; Type: SEQUENCE SET; Schema: staging; Owner: -
--

SELECT pg_catalog.setval('staging.seq_tiendas_y_locaciones_tmp_pk', 50, true);


--
-- Name: boleta_cabeceras ak_boleta_cabeceras; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT ak_boleta_cabeceras UNIQUE (codigo_facturacion) WITH (fillfactor='10');


--
-- Name: CONSTRAINT ak_boleta_cabeceras ON boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT ak_boleta_cabeceras ON boletas.boleta_cabeceras IS 'UNIQUE KEY Alternativo';


--
-- Name: boletas_en_buzon ak_boleta_en_buzon; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT ak_boleta_en_buzon UNIQUE (codigo_facturacion);


--
-- Name: CONSTRAINT ak_boleta_en_buzon ON boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT ak_boleta_en_buzon ON boletas.boletas_en_buzon IS 'UNIQUE KEY Alternativo';


--
-- Name: boleta_cabeceras boleta_cabeceras_uq; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_uq UNIQUE (numero_del_documento_pedidos, fecha_modificacion_pedidos, hora_modificacion_pedidos, estado_orden_pedidos);


--
-- Name: boleta_detalles_sku_descuentos boleta_detalles_sku_descuentos_pk; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku_descuentos
    ADD CONSTRAINT boleta_detalles_sku_descuentos_pk PRIMARY KEY (id_linea_detalle_boleta_detalles_sku, id_terminal_pos_boleta_detalles_sku, numero_boleta_boleta_detalles_sku, fecha_fin_transaccion_boleta_detalles_sku, hora_fin_transaccion_boleta_detalles_sku, id_tienda_origen_boleta_detalles_sku, id_tienda_reconocimiento_boleta_detalles_sku, id_descuento);


--
-- Name: boleta_cabeceras pk_boleta_cabeceras; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT pk_boleta_cabeceras PRIMARY KEY (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_boleta_cabeceras ON boleta_cabeceras; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boleta_cabeceras ON boletas.boleta_cabeceras IS 'PK Contraint de la tabla BOLETA_CABECERAS';


--
-- Name: boleta_detalle_abono_tarjetas pk_boleta_detalle_abono_tarjetas; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_abono_tarjetas
    ADD CONSTRAINT pk_boleta_detalle_abono_tarjetas PRIMARY KEY (id_linea_detalle, id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_boleta_detalle_abono_tarjetas ON boleta_detalle_abono_tarjetas; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boleta_detalle_abono_tarjetas ON boletas.boleta_detalle_abono_tarjetas IS 'PK De BOLETA_DETALLE_ABONO_TARJETAS';


--
-- Name: boleta_detalle_impuestos pk_boleta_detalle_impuestos; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_impuestos
    ADD CONSTRAINT pk_boleta_detalle_impuestos PRIMARY KEY (id_boleta_detalle_impuestos);


--
-- Name: CONSTRAINT pk_boleta_detalle_impuestos ON boleta_detalle_impuestos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boleta_detalle_impuestos ON boletas.boleta_detalle_impuestos IS 'PK de la BOLETA_DETALLE_IMPUESTOS';


--
-- Name: boleta_detalle_pagos pk_boleta_detalle_pagos; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_pagos
    ADD CONSTRAINT pk_boleta_detalle_pagos PRIMARY KEY (id_linea_detalle_pagos, id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_boleta_detalle_pagos ON boleta_detalle_pagos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boleta_detalle_pagos ON boletas.boleta_detalle_pagos IS 'PK Constraint de la tabla BOLETA_DETALLE_PAGOS';


--
-- Name: boleta_detalles_sku pk_boleta_detalles_sku; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT pk_boleta_detalles_sku PRIMARY KEY (id_linea_detalle, id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_boleta_detalles_sku ON boleta_detalles_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boleta_detalles_sku ON boletas.boleta_detalles_sku IS 'PK Constraint de la tabla BOLETA_DETALLES_SKU';


--
-- Name: boletas_en_buzon pk_boleta_en_buzon; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT pk_boleta_en_buzon PRIMARY KEY (id_terminal_pos, numero_boleta, fecha_transaccion, hora_transaccion, id_tienda_original, id_tienda) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_boleta_en_buzon ON boletas_en_buzon; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boleta_en_buzon ON boletas.boletas_en_buzon IS 'PK Contraint de la tabla BOLETA_CABECERAS';


--
-- Name: boletas_en_buzon_detalle_pagos pk_boletas_en_buzon_detalle_pagos; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT pk_boletas_en_buzon_detalle_pagos PRIMARY KEY (id_linea_de_detalle, id_terminal_pos_boletas_en_buzon, numero_boleta_boletas_en_buzon, fecha_transaccion_boletas_en_buzon, hora_transaccion_boletas_en_buzon, id_tienda_original_boletas_en_buzon, id_tienda_boletas_en_buzon) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_boletas_en_buzon_detalle_pagos ON boletas_en_buzon_detalle_pagos; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boletas_en_buzon_detalle_pagos ON boletas.boletas_en_buzon_detalle_pagos IS 'PK de BOLETAS_EN_BUZON_DETALLE_PAGOS';


--
-- Name: boletas_en_buzon_detalle_sku pk_boletas_en_buzon_detalle_sku; Type: CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_sku
    ADD CONSTRAINT pk_boletas_en_buzon_detalle_sku PRIMARY KEY (id_linea_de_detalle, id_terminal_pos_boletas_en_buzon, numero_boleta_boletas_en_buzon, fecha_transaccion_boletas_en_buzon, hora_transaccion_boletas_en_buzon, id_tienda_original_boletas_en_buzon, id_tienda_boletas_en_buzon) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_boletas_en_buzon_detalle_sku ON boletas_en_buzon_detalle_sku; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON CONSTRAINT pk_boletas_en_buzon_detalle_sku ON boletas.boletas_en_buzon_detalle_sku IS 'PK de BOLETAS_EN_BUZON_DETALLE_SKU';


--
-- Name: cliente_ticket_electronico_medios_de_pago cliente_ticket_electronico_medios_de_pago_pk; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ticket_electronico_medios_de_pago
    ADD CONSTRAINT cliente_ticket_electronico_medios_de_pago_pk PRIMARY KEY (id_cliente_ticket_electronico_cliente_ticket_electronico, id_medio_de_pago_medios_de_pago);


--
-- Name: clientes_merge clientes_merge_uq; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes_merge
    ADD CONSTRAINT clientes_merge_uq UNIQUE (id_cliente);


--
-- Name: cliente_direcciones pk_cliente_direcciones; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT pk_cliente_direcciones PRIMARY KEY (id_direccion_del_cliente);


--
-- Name: CONSTRAINT pk_cliente_direcciones ON cliente_direcciones; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_direcciones ON clientes.cliente_direcciones IS 'PK de las Direcciones del Cliente';


--
-- Name: cliente_direcciones_bitacora pk_cliente_direcciones_bitacora; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones_bitacora
    ADD CONSTRAINT pk_cliente_direcciones_bitacora PRIMARY KEY (id_direccion_del_cliente);


--
-- Name: CONSTRAINT pk_cliente_direcciones_bitacora ON cliente_direcciones_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_direcciones_bitacora ON clientes.cliente_direcciones_bitacora IS 'PK de las Direcciones del CLIENTE_DIRECCIONES_BITACORA';


--
-- Name: cliente_emails_de_contacto pk_cliente_emails_de_contacto; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto
    ADD CONSTRAINT pk_cliente_emails_de_contacto PRIMARY KEY (id_cliente_emails_de_contacto);


--
-- Name: CONSTRAINT pk_cliente_emails_de_contacto ON cliente_emails_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_emails_de_contacto ON clientes.cliente_emails_de_contacto IS 'PK de CLIENTE_EMAILS_DE_CONTACTO';


--
-- Name: cliente_emails_de_contacto_bitacora pk_cliente_emails_de_contacto_bitacora; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto_bitacora
    ADD CONSTRAINT pk_cliente_emails_de_contacto_bitacora PRIMARY KEY (id_cliente_emails_de_contacto);


--
-- Name: CONSTRAINT pk_cliente_emails_de_contacto_bitacora ON cliente_emails_de_contacto_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_emails_de_contacto_bitacora ON clientes.cliente_emails_de_contacto_bitacora IS 'PK de CLIENTE_EMAILS_DE_CONTACTO_BITACORA';


--
-- Name: cliente_formas_de_pago_bitacora pk_cliente_formas_de_pago_bitacora; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago_bitacora
    ADD CONSTRAINT pk_cliente_formas_de_pago_bitacora PRIMARY KEY (id_cliente_tarjeta) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_cliente_formas_de_pago_bitacora ON cliente_formas_de_pago_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_formas_de_pago_bitacora ON clientes.cliente_formas_de_pago_bitacora IS 'PK de Cliente Tarjetas';


--
-- Name: cliente_ids pk_cliente_ids; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids
    ADD CONSTRAINT pk_cliente_ids PRIMARY KEY (id_cliente_ids) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_cliente_ids ON cliente_ids; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_ids ON clientes.cliente_ids IS 'PK de CLIENTE_IDS';


--
-- Name: cliente_ids_bitacora pk_cliente_ids_bitacora; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids_bitacora
    ADD CONSTRAINT pk_cliente_ids_bitacora PRIMARY KEY (id_cliente_ids) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_cliente_ids_bitacora ON cliente_ids_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_ids_bitacora ON clientes.cliente_ids_bitacora IS 'PK de CLIENTE_IDS_BITACORA';


--
-- Name: cliente_formas_de_pago pk_cliente_tarjetas; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago
    ADD CONSTRAINT pk_cliente_tarjetas PRIMARY KEY (id_cliente_tarjeta);


--
-- Name: CONSTRAINT pk_cliente_tarjetas ON cliente_formas_de_pago; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_tarjetas ON clientes.cliente_formas_de_pago IS 'PK de Cliente Tarjetas';


--
-- Name: cliente_telefonos_de_contacto pk_cliente_telefonos_de_contacto; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto
    ADD CONSTRAINT pk_cliente_telefonos_de_contacto PRIMARY KEY (id_cliente_telefonos_de_contacto) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_cliente_telefonos_de_contacto ON cliente_telefonos_de_contacto; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_telefonos_de_contacto ON clientes.cliente_telefonos_de_contacto IS 'PK de CLIENTE_TELEFONOS_DE_CONTACTO';


--
-- Name: cliente_telefonos_de_contacto_bitacora pk_cliente_telefonos_de_contacto_bitacora; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto_bitacora
    ADD CONSTRAINT pk_cliente_telefonos_de_contacto_bitacora PRIMARY KEY (id_cliente_telefonos_de_contacto) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_cliente_telefonos_de_contacto_bitacora ON cliente_telefonos_de_contacto_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_telefonos_de_contacto_bitacora ON clientes.cliente_telefonos_de_contacto_bitacora IS 'PK de CLIENTE_TELEFONOS_DE_CONTACTO_BITACORA';


--
-- Name: cliente_ticket_electronico pk_cliente_ticket_electronico; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ticket_electronico
    ADD CONSTRAINT pk_cliente_ticket_electronico PRIMARY KEY (id_cliente_ticket_electronico) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_cliente_ticket_electronico ON cliente_ticket_electronico; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_cliente_ticket_electronico ON clientes.cliente_ticket_electronico IS 'PK de la tabla CLIENTE_TICKET_ELECTRONICO';


--
-- Name: clientes pk_clientes; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes
    ADD CONSTRAINT pk_clientes PRIMARY KEY (id_cliente);


--
-- Name: CONSTRAINT pk_clientes ON clientes; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_clientes ON clientes.clientes IS 'PK de Clientes';


--
-- Name: cliente_atg pk_clientes_atg; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_atg
    ADD CONSTRAINT pk_clientes_atg PRIMARY KEY (id_cliente_atg) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_clientes_atg ON cliente_atg; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_clientes_atg ON clientes.cliente_atg IS 'PK de la tabla CLIENTES_ATG';


--
-- Name: clientes_bitacora pk_clientes_bitacora; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes_bitacora
    ADD CONSTRAINT pk_clientes_bitacora PRIMARY KEY (id_cliente);


--
-- Name: CONSTRAINT pk_clientes_bitacora ON clientes_bitacora; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_clientes_bitacora ON clientes.clientes_bitacora IS 'PK de Clientes_Bitacora';


--
-- Name: clientes_merge pk_clientes_unico; Type: CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes_merge
    ADD CONSTRAINT pk_clientes_unico PRIMARY KEY (id_cliente_unico) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_clientes_unico ON clientes_merge; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON CONSTRAINT pk_clientes_unico ON clientes.clientes_merge IS 'PK de Tabla de CLIENTES_UNICO';


--
-- Name: pedidos pk_pedidos; Type: CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos
    ADD CONSTRAINT pk_pedidos PRIMARY KEY (numero_del_documento, fecha_modificacion, hora_modificacion, estado_orden) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_pedidos ON pedidos; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON CONSTRAINT pk_pedidos ON pedidos.pedidos IS 'PK de tabla Pedidos';


--
-- Name: pedidos_detalle_sku pk_pedidos_detalle_sku; Type: CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos_detalle_sku
    ADD CONSTRAINT pk_pedidos_detalle_sku PRIMARY KEY (id_linea_detalle, numero_del_documento_pedidos, fecha_modificacion_pedidos, hora_modificacion_pedidos, estado_orden_pedidos) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_pedidos_detalle_sku ON pedidos_detalle_sku; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON CONSTRAINT pk_pedidos_detalle_sku ON pedidos.pedidos_detalle_sku IS 'PK de tabla PEDIDOS_DETALLE_SKU';


--
-- Name: catalogos catalogos_nodup_rows; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.catalogos
    ADD CONSTRAINT catalogos_nodup_rows UNIQUE (entidad_origen, clave_homologada);


--
-- Name: tiendas_y_locaciones jerarquia_tiendas_locacion_pk; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT jerarquia_tiendas_locacion_pk PRIMARY KEY (id_tienda_o_locacion);


--
-- Name: CONSTRAINT jerarquia_tiendas_locacion_pk ON tiendas_y_locaciones; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT jerarquia_tiendas_locacion_pk ON shared.tiendas_y_locaciones IS 'PK de Tienda o Locacion';


--
-- Name: catalogos pk_catalogos; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.catalogos
    ADD CONSTRAINT pk_catalogos PRIMARY KEY (id_catalogo) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_catalogos ON catalogos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_catalogos ON shared.catalogos IS 'PK Catalogos';


--
-- Name: impuestos pk_impuestos; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.impuestos
    ADD CONSTRAINT pk_impuestos PRIMARY KEY (id_impuesto) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_impuestos ON impuestos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_impuestos ON shared.impuestos IS 'PK de Impuestos';


--
-- Name: productos pk_jerarquia_producto; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT pk_jerarquia_producto PRIMARY KEY (id_producto) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_jerarquia_producto ON productos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_jerarquia_producto ON shared.productos IS 'PK de productos';


--
-- Name: medios_de_pago pk_medios_de_pago; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.medios_de_pago
    ADD CONSTRAINT pk_medios_de_pago PRIMARY KEY (id_medio_de_pago) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_medios_de_pago ON medios_de_pago; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_medios_de_pago ON shared.medios_de_pago IS 'PK Medios de Pago';


--
-- Name: producto_aplica_servicios pk_producto_aplica_servicios; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_aplica_servicios
    ADD CONSTRAINT pk_producto_aplica_servicios PRIMARY KEY (id_producto_aplica_servicios) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_producto_aplica_servicios ON producto_aplica_servicios; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_producto_aplica_servicios ON shared.producto_aplica_servicios IS 'PK de la tabla PRODUCTO_APLICA_SERVICIOS';


--
-- Name: producto_bigticket pk_producto_bigticket; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_bigticket
    ADD CONSTRAINT pk_producto_bigticket PRIMARY KEY (id_producto_bigticket) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_producto_bigticket ON producto_bigticket; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_producto_bigticket ON shared.producto_bigticket IS 'PK de la tabla PRODUCTO_BIGTICKET';


--
-- Name: producto_direcciones pk_producto_direcciones; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_direcciones
    ADD CONSTRAINT pk_producto_direcciones PRIMARY KEY (id_producto_direccion);


--
-- Name: CONSTRAINT pk_producto_direcciones ON producto_direcciones; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_producto_direcciones ON shared.producto_direcciones IS 'PK de la tabla PRODUCTO_DIRECCIONES';


--
-- Name: producto_grupos pk_producto_grupos; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_grupos
    ADD CONSTRAINT pk_producto_grupos PRIMARY KEY (id_producto_grupo) WITH (fillfactor='10');


--
-- Name: CONSTRAINT pk_producto_grupos ON producto_grupos; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_producto_grupos ON shared.producto_grupos IS 'PK de la tabla PRODUCTO_GRUPOS';


--
-- Name: producto_marcas pk_producto_marcas; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_marcas
    ADD CONSTRAINT pk_producto_marcas PRIMARY KEY (id_producto_marcas);


--
-- Name: CONSTRAINT pk_producto_marcas ON producto_marcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_producto_marcas ON shared.producto_marcas IS 'PK de la tabla PRODUCTO_MARCAS';


--
-- Name: producto_secciones pk_producto_secciones; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_secciones
    ADD CONSTRAINT pk_producto_secciones PRIMARY KEY (id_producto_seccion_sku) WITH (fillfactor='10');


--
-- Name: producto_submarcas pk_producto_submarcas; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_submarcas
    ADD CONSTRAINT pk_producto_submarcas PRIMARY KEY (id_producto_submarcas, id_producto_marcas) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_producto_submarcas ON producto_submarcas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_producto_submarcas ON shared.producto_submarcas IS 'PK de PRODUCTO_SUBMARCAS';


--
-- Name: producto_tallas pk_producto_tallas; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_tallas
    ADD CONSTRAINT pk_producto_tallas PRIMARY KEY (id_producto_tallas) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_producto_tallas ON producto_tallas; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_producto_tallas ON shared.producto_tallas IS 'PK de PRODUCTO_TALLAS';


--
-- Name: proveedores pk_proveedores; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.proveedores
    ADD CONSTRAINT pk_proveedores PRIMARY KEY (id_proveedor) WITH (fillfactor='100');


--
-- Name: CONSTRAINT pk_proveedores ON proveedores; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON CONSTRAINT pk_proveedores ON shared.proveedores IS 'PK de PROVEEDORES';


--
-- Name: productos productos_uq; Type: CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_uq UNIQUE (id_producto_nospot);


--
-- Name: cliente_mdm pk_cliente_mdm; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.cliente_mdm
    ADD CONSTRAINT pk_cliente_mdm PRIMARY KEY (id_cliente_mdm) WITH (fillfactor='100');


--
-- Name: clientes_atg pk_clientes_atg; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.clientes_atg
    ADD CONSTRAINT pk_clientes_atg PRIMARY KEY (id_clientes_atg);


--
-- Name: contacto_mdm pk_contacto_mdm; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.contacto_mdm
    ADD CONSTRAINT pk_contacto_mdm PRIMARY KEY (id_contacto_mdm) WITH (fillfactor='100');


--
-- Name: direccion_mdm pk_direccion_mdm; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.direccion_mdm
    ADD CONSTRAINT pk_direccion_mdm PRIMARY KEY (id_direccion_mdm) WITH (fillfactor='100');


--
-- Name: catalogos_producto_tmp pk_id_catalogos_producto_tmp; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.catalogos_producto_tmp
    ADD CONSTRAINT pk_id_catalogos_producto_tmp PRIMARY KEY (id_catalogos_producto_tmp) WITH (fillfactor='100');


--
-- Name: producto_grupos_tmp pk_id_producto_grupos_tmp; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.producto_grupos_tmp
    ADD CONSTRAINT pk_id_producto_grupos_tmp PRIMARY KEY (id_producto_grupos_tmp) WITH (fillfactor='100');


--
-- Name: pedidos_soms pk_pedidos_soms; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.pedidos_soms
    ADD CONSTRAINT pk_pedidos_soms PRIMARY KEY (pedidos_id) WITH (fillfactor='100');


--
-- Name: productos_tmp pk_productos_tmp; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.productos_tmp
    ADD CONSTRAINT pk_productos_tmp PRIMARY KEY (id_productos_tmp) WITH (fillfactor='10');


--
-- Name: referencia_mdm pk_referencia_mdm; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.referencia_mdm
    ADD CONSTRAINT pk_referencia_mdm PRIMARY KEY (id_referencia_mdm) WITH (fillfactor='100');


--
-- Name: tiendas_y_locaciones_tmp pk_tiendas_y_locaciones_tmp; Type: CONSTRAINT; Schema: staging; Owner: -
--

ALTER TABLE ONLY staging.tiendas_y_locaciones_tmp
    ADD CONSTRAINT pk_tiendas_y_locaciones_tmp PRIMARY KEY (id_tiendas_y_locaciones_tmp) WITH (fillfactor='100');


--
-- Name: ak_boleta_cabeceras_idx; Type: INDEX; Schema: boletas; Owner: -
--

CREATE INDEX ak_boleta_cabeceras_idx ON boletas.boleta_cabeceras USING btree (codigo_facturacion);


--
-- Name: INDEX ak_boleta_cabeceras_idx; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON INDEX boletas.ak_boleta_cabeceras_idx IS 'KEY Alternativo de BOLETA_CABECERAS por CODIGO_FACTURACION ';


--
-- Name: ak_boleta_en_buzon_idx; Type: INDEX; Schema: boletas; Owner: -
--

CREATE INDEX ak_boleta_en_buzon_idx ON boletas.boletas_en_buzon USING btree (codigo_facturacion);


--
-- Name: INDEX ak_boleta_en_buzon_idx; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON INDEX boletas.ak_boleta_en_buzon_idx IS 'KEY Alternativo de BOLETA_CABECERAS por CODIGO_FACTURACION ';


--
-- Name: pk_boleta_cabeceras_idx; Type: INDEX; Schema: boletas; Owner: -
--

CREATE UNIQUE INDEX pk_boleta_cabeceras_idx ON boletas.boleta_cabeceras USING btree (id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento);


--
-- Name: INDEX pk_boleta_cabeceras_idx; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON INDEX boletas.pk_boleta_cabeceras_idx IS 'Index de la PK de la tabla Boleta_Cabeceras';


--
-- Name: pk_boleta_detalle_abono_tarjetas_idx; Type: INDEX; Schema: boletas; Owner: -
--

CREATE INDEX pk_boleta_detalle_abono_tarjetas_idx ON boletas.boleta_detalle_abono_tarjetas USING btree (id_linea_detalle);


--
-- Name: INDEX pk_boleta_detalle_abono_tarjetas_idx; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON INDEX boletas.pk_boleta_detalle_abono_tarjetas_idx IS 'Index de PK de BOLETA_DETALLE_ABONO_TARJETAS';


--
-- Name: pk_boleta_detalle_impuestos_idx; Type: INDEX; Schema: boletas; Owner: -
--

CREATE INDEX pk_boleta_detalle_impuestos_idx ON boletas.boleta_detalle_impuestos USING btree (id_boleta_detalle_impuestos);


--
-- Name: INDEX pk_boleta_detalle_impuestos_idx; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON INDEX boletas.pk_boleta_detalle_impuestos_idx IS 'Index de PK de BOLETA_DETALLE_IMPUESTOS';


--
-- Name: pk_boleta_en_buzon_idx; Type: INDEX; Schema: boletas; Owner: -
--

CREATE UNIQUE INDEX pk_boleta_en_buzon_idx ON boletas.boletas_en_buzon USING btree (id_terminal_pos, numero_boleta, fecha_transaccion, hora_transaccion, id_tienda_original, id_tienda);


--
-- Name: INDEX pk_boleta_en_buzon_idx; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON INDEX boletas.pk_boleta_en_buzon_idx IS 'Index de la PK de la tabla Boleta_Cabeceras';


--
-- Name: pk_boletas_en_buzon_detalle_sku_idx; Type: INDEX; Schema: boletas; Owner: -
--

CREATE INDEX pk_boletas_en_buzon_detalle_sku_idx ON boletas.boletas_en_buzon_detalle_sku USING btree (id_linea_de_detalle);


--
-- Name: INDEX pk_boletas_en_buzon_detalle_sku_idx; Type: COMMENT; Schema: boletas; Owner: -
--

COMMENT ON INDEX boletas.pk_boletas_en_buzon_detalle_sku_idx IS 'Index de PK de BOLETAS_EN_BUZON_DETALLE_SKU';


--
-- Name: pk_cliente_direcciones_bitacora_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_direcciones_bitacora_idx ON clientes.cliente_direcciones_bitacora USING btree (id_direccion_del_cliente);


--
-- Name: INDEX pk_cliente_direcciones_bitacora_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_direcciones_bitacora_idx IS 'Index del PK de CLIENTE_DIRECCIONES';


--
-- Name: pk_cliente_direcciones_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_direcciones_idx ON clientes.cliente_direcciones USING btree (id_direccion_del_cliente);


--
-- Name: INDEX pk_cliente_direcciones_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_direcciones_idx IS 'Index del PK de CLIENTE_DIRECCIONES';


--
-- Name: pk_cliente_emails_de_contacto_bitacora_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_emails_de_contacto_bitacora_idx ON clientes.cliente_emails_de_contacto_bitacora USING btree (id_cliente_emails_de_contacto);


--
-- Name: INDEX pk_cliente_emails_de_contacto_bitacora_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_emails_de_contacto_bitacora_idx IS 'Index de PK de CLIENTE_EMAILS_DE_CONTACTO_BITACORA';


--
-- Name: pk_cliente_emails_de_contacto_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_emails_de_contacto_idx ON clientes.cliente_emails_de_contacto USING btree (id_cliente_emails_de_contacto);


--
-- Name: INDEX pk_cliente_emails_de_contacto_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_emails_de_contacto_idx IS 'Index de PK de CLIENTE_EMAILS_DE_CONTACTO';


--
-- Name: pk_cliente_formas_de_pago_bitacora_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_formas_de_pago_bitacora_idx ON clientes.cliente_formas_de_pago_bitacora USING btree (id_cliente_tarjeta);


--
-- Name: INDEX pk_cliente_formas_de_pago_bitacora_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_formas_de_pago_bitacora_idx IS 'Index de PK de CLIENTE_FORMAS_DE_PAGO_BITACORA';


--
-- Name: pk_cliente_formas_de_pago_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_formas_de_pago_idx ON clientes.cliente_formas_de_pago USING btree (id_cliente_tarjeta);


--
-- Name: INDEX pk_cliente_formas_de_pago_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_formas_de_pago_idx IS 'Index de PK de CLIENTE_FORMAS_DE_PAGO';


--
-- Name: pk_cliente_ids_bitacora_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_ids_bitacora_idx ON clientes.cliente_ids_bitacora USING btree (id_cliente_ids);


--
-- Name: INDEX pk_cliente_ids_bitacora_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_ids_bitacora_idx IS 'Index de PK de CLIENTE_IDS_BITACORA';


--
-- Name: pk_cliente_ids_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_ids_idx ON clientes.cliente_ids USING btree (id_cliente_ids);


--
-- Name: INDEX pk_cliente_ids_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_ids_idx IS 'Index de PK de CLIENTE_IDS';


--
-- Name: pk_cliente_telefonos_de_contacto_bitacora_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_telefonos_de_contacto_bitacora_idx ON clientes.cliente_telefonos_de_contacto_bitacora USING btree (id_cliente_telefonos_de_contacto);


--
-- Name: INDEX pk_cliente_telefonos_de_contacto_bitacora_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_telefonos_de_contacto_bitacora_idx IS 'Index de PK de CLIENTE_TELEFONOS_DE_CONTACTO_BITACORA';


--
-- Name: pk_cliente_telefonos_de_contacto_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_telefonos_de_contacto_idx ON clientes.cliente_telefonos_de_contacto USING btree (id_cliente_telefonos_de_contacto);


--
-- Name: INDEX pk_cliente_telefonos_de_contacto_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_telefonos_de_contacto_idx IS 'Index de PK de CLIENTE_TELEFONOS_DE_CONTACTO';


--
-- Name: pk_cliente_ticket_electronico_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_cliente_ticket_electronico_idx ON clientes.cliente_ticket_electronico USING btree (id_cliente_ticket_electronico);


--
-- Name: INDEX pk_cliente_ticket_electronico_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_cliente_ticket_electronico_idx IS 'Index de la PK de CLIENTE_TICKET_ELECTRONICO';


--
-- Name: pk_clientes_atg_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_clientes_atg_idx ON clientes.cliente_atg USING btree (id_cliente_atg);


--
-- Name: INDEX pk_clientes_atg_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_clientes_atg_idx IS 'Index de la PK de CLIENTES_ATG';


--
-- Name: pk_clientes_bitacora_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_clientes_bitacora_idx ON clientes.clientes_bitacora USING btree (id_cliente);


--
-- Name: INDEX pk_clientes_bitacora_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_clientes_bitacora_idx IS 'Index de PK de CLIENTES_BITACORA';


--
-- Name: pk_clientes_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_clientes_idx ON clientes.clientes USING btree (id_cliente);


--
-- Name: INDEX pk_clientes_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_clientes_idx IS 'Index de PK de Clientes';


--
-- Name: pk_clientes_unico_idx; Type: INDEX; Schema: clientes; Owner: -
--

CREATE INDEX pk_clientes_unico_idx ON clientes.clientes_merge USING btree (id_cliente_unico);


--
-- Name: INDEX pk_clientes_unico_idx; Type: COMMENT; Schema: clientes; Owner: -
--

COMMENT ON INDEX clientes.pk_clientes_unico_idx IS 'Index de PK de CLIENTES_UNICO';


--
-- Name: pk_pedidos_detalle_sku_idx; Type: INDEX; Schema: pedidos; Owner: -
--

CREATE INDEX pk_pedidos_detalle_sku_idx ON pedidos.pedidos_detalle_sku USING btree (id_linea_detalle);


--
-- Name: INDEX pk_pedidos_detalle_sku_idx; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON INDEX pedidos.pk_pedidos_detalle_sku_idx IS 'Index de PK de PEDIDOS_DETALLE_SKU';


--
-- Name: pk_pedidos_idx; Type: INDEX; Schema: pedidos; Owner: -
--

CREATE INDEX pk_pedidos_idx ON pedidos.pedidos USING btree (numero_del_documento, estado_orden, fecha_modificacion, hora_modificacion);


--
-- Name: INDEX pk_pedidos_idx; Type: COMMENT; Schema: pedidos; Owner: -
--

COMMENT ON INDEX pedidos.pk_pedidos_idx IS 'Index de la PK de la tabla PEDIDOS';


--
-- Name: pk_catalogos_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_catalogos_idx ON shared.catalogos USING btree (id_catalogo);


--
-- Name: INDEX pk_catalogos_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_catalogos_idx IS 'Indice de PK Tabla Catalogo';


--
-- Name: pk_impuestos_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_impuestos_idx ON shared.impuestos USING btree (id_impuesto);


--
-- Name: INDEX pk_impuestos_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_impuestos_idx IS 'Index de PK de Impuestos';


--
-- Name: pk_medios_de_pago_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_medios_de_pago_idx ON shared.medios_de_pago USING btree (id_medio_de_pago);


--
-- Name: INDEX pk_medios_de_pago_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_medios_de_pago_idx IS 'Indice de PK MEdios de Pago';


--
-- Name: pk_producto_aplica_servicios_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_aplica_servicios_idx ON shared.producto_aplica_servicios USING btree (id_producto_aplica_servicios);


--
-- Name: INDEX pk_producto_aplica_servicios_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_aplica_servicios_idx IS 'Indice de PK de la tabla PRODUCTO_APLICA_SERVICIOS';


--
-- Name: pk_producto_bigticket_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_bigticket_idx ON shared.producto_bigticket USING btree (id_producto_bigticket);


--
-- Name: INDEX pk_producto_bigticket_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_bigticket_idx IS 'Index de la PK de PRODUCTO_BIGTICKET';


--
-- Name: pk_producto_direcciones_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_direcciones_idx ON shared.producto_direcciones USING btree (id_producto_direccion);


--
-- Name: INDEX pk_producto_direcciones_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_direcciones_idx IS 'Index de la PK de PRODUCTO_DIRECCIONES';


--
-- Name: pk_producto_grupos_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_grupos_idx ON shared.producto_grupos USING btree (id_producto_grupo);


--
-- Name: INDEX pk_producto_grupos_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_grupos_idx IS 'Index de la PK de la tabla PRODUCTO_GRUPOS';


--
-- Name: pk_producto_marcas_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_marcas_idx ON shared.producto_marcas USING btree (id_producto_marcas);


--
-- Name: INDEX pk_producto_marcas_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_marcas_idx IS 'Index de la PK de la tabla PRODUCTO_MARCAS';


--
-- Name: pk_producto_secciones_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_secciones_idx ON shared.producto_secciones USING btree (id_producto_seccion_sku);


--
-- Name: INDEX pk_producto_secciones_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_secciones_idx IS 'Index de PK de PRODUCTO_SECCIONES';


--
-- Name: pk_producto_submarcas_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_submarcas_idx ON shared.producto_submarcas USING btree (id_producto_submarcas);


--
-- Name: INDEX pk_producto_submarcas_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_submarcas_idx IS 'Index de PK de PRODUCTO_SUBMARCAS';


--
-- Name: pk_producto_tallas_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_producto_tallas_idx ON shared.producto_tallas USING btree (id_producto_tallas);


--
-- Name: INDEX pk_producto_tallas_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_producto_tallas_idx IS 'Index de la PK de PRODUCTO_TALLAS';


--
-- Name: pk_productos_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_productos_idx ON shared.productos USING btree (id_producto);


--
-- Name: INDEX pk_productos_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_productos_idx IS 'Indice de Productos';


--
-- Name: pk_proveedores_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_proveedores_idx ON shared.proveedores USING btree (id_proveedor);


--
-- Name: INDEX pk_proveedores_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_proveedores_idx IS 'Index de PK de PROVEEDORES';


--
-- Name: pk_tiendas_y_locaciones_idx; Type: INDEX; Schema: shared; Owner: -
--

CREATE INDEX pk_tiendas_y_locaciones_idx ON shared.tiendas_y_locaciones USING btree (id_tienda_o_locacion);


--
-- Name: INDEX pk_tiendas_y_locaciones_idx; Type: COMMENT; Schema: shared; Owner: -
--

COMMENT ON INDEX shared.pk_tiendas_y_locaciones_idx IS 'Index de la PK de TIENDAS_Y_LOCACIONES';


--
-- Name: boleta_detalles_sku boleta_cabeceras_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT boleta_cabeceras_fk FOREIGN KEY (id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boleta_detalle_pagos boleta_cabeceras_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_pagos
    ADD CONSTRAINT boleta_cabeceras_fk FOREIGN KEY (id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boleta_detalle_impuestos boleta_cabeceras_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_impuestos
    ADD CONSTRAINT boleta_cabeceras_fk FOREIGN KEY (id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_detalle_abono_tarjetas boleta_cabeceras_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_abono_tarjetas
    ADD CONSTRAINT boleta_cabeceras_fk FOREIGN KEY (id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) REFERENCES boletas.boleta_cabeceras(id_terminal_pos, numero_boleta, fecha_fin_transaccion, hora_fin_transaccion, id_tienda_origen, id_tienda_reconocimiento) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boleta_cabeceras boleta_cabeceras_id_cana_vta_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_cana_vta_fk FOREIGN KEY (id_canal_de_venta) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_centro_s_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_centro_s_fk FOREIGN KEY (id_numero_centro_de_servicio) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_del_serv_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_del_serv_fk FOREIGN KEY (id_del_servicio) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_msj_canc_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_msj_canc_fk FOREIGN KEY (id_mensaje_cancelacion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_seccion_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_seccion_fk FOREIGN KEY (id_seccion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_segmento_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_segmento_fk FOREIGN KEY (id_segmento) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_sta_code_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_sta_code_fk FOREIGN KEY (id_status_code_activacion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_subc_vta_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_subc_vta_fk FOREIGN KEY (id_subcanal_de_venta) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_term_pos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_term_pos_fk FOREIGN KEY (id_terminal_pos_cancelacion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_tip_dcto_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_tip_dcto_fk FOREIGN KEY (id_tipo_de_descuento_al_total) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_tipoevto_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_tipoevto_fk FOREIGN KEY (id_tipo_de_evento) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_tipopper_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_tipopper_fk FOREIGN KEY (id_tipo_operacion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_tpopromo_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_tpopromo_fk FOREIGN KEY (id_tipo_promocion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_vendedor_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras boleta_cabeceras_id_vtacatex_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT boleta_cabeceras_id_vtacatex_fk FOREIGN KEY (id_venta_catalogo_extendido_y_otro) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_detalles_sku_descuentos boleta_detalles_sku_descuentos_descuento_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku_descuentos
    ADD CONSTRAINT boleta_detalles_sku_descuentos_descuento_fk FOREIGN KEY (id_descuento) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boleta_detalles_sku_descuentos boleta_detalles_sku_descuentos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku_descuentos
    ADD CONSTRAINT boleta_detalles_sku_descuentos_fk FOREIGN KEY (id_linea_detalle_boleta_detalles_sku, id_terminal_pos_boleta_detalles_sku, numero_boleta_boleta_detalles_sku, fecha_fin_transaccion_boleta_detalles_sku, hora_fin_transaccion_boleta_detalles_sku, id_tienda_origen_boleta_detalles_sku, id_tienda_reconocimiento_boleta_detalles_sku) REFERENCES boletas.boleta_detalles_sku(id_linea_detalle, id_terminal_pos_boleta_cabeceras, numero_boleta_boleta_cabeceras, fecha_fin_transaccion_boleta_cabeceras, hora_fin_transaccion_boleta_cabeceras, id_tienda_origen_boleta_cabeceras, id_tienda_reconocimiento_boleta_cabeceras) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boleta_detalles_sku boleta_detalles_sku_id_cen_serv_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT boleta_detalles_sku_id_cen_serv_fk FOREIGN KEY (id_centro_de_servicio) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_detalles_sku boleta_detalles_sku_id_disp_vta_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT boleta_detalles_sku_id_disp_vta_fk FOREIGN KEY (id_status_code_activacion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_detalles_sku boleta_detalles_sku_id_tipdctot_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT boleta_detalles_sku_id_tipdctot_fk FOREIGN KEY (id_tipo_de_descuento_al_total) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_detalles_sku boleta_detalles_sku_id_tipo_des_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT boleta_detalles_sku_id_tipo_des_fk FOREIGN KEY (id_tipo_de_descuento) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_detalles_sku boleta_detalles_sku_id_tippromo_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT boleta_detalles_sku_id_tippromo_fk FOREIGN KEY (id_tipo_de_promocion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_detalles_sku_descuentos boleta_detalles_sku_tipo_de_descuento_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku_descuentos
    ADD CONSTRAINT boleta_detalles_sku_tipo_de_descuento_fk FOREIGN KEY (id_tipo_de_descuento) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boleta_detalles_sku_descuentos boleta_detalles_sku_tipo_de_descuento_tot_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku_descuentos
    ADD CONSTRAINT boleta_detalles_sku_tipo_de_descuento_tot_fk FOREIGN KEY (id_tipo_de_descuento_al_total) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_banco_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_banco_fk FOREIGN KEY (id_banco) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_canal_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_canal_fk FOREIGN KEY (id_canal) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_disp_ent_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_disp_ent_fk FOREIGN KEY (id_dispositivo_entrada) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_empresa_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_empresa_fk FOREIGN KEY (id_empresa) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_plan_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_plan_fk FOREIGN KEY (id_plan) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_tipo_pag_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_tipo_pag_fk FOREIGN KEY (id_tipo_de_pago) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_tipo_trx_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_tipo_trx_fk FOREIGN KEY (id_tipo_transaccion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_detalle_pagos_id_vendedor_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_detalle_pagos_id_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_sku boletas_en_buzon_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_sku
    ADD CONSTRAINT boletas_en_buzon_fk FOREIGN KEY (id_terminal_pos_boletas_en_buzon, numero_boleta_boletas_en_buzon, fecha_transaccion_boletas_en_buzon, hora_transaccion_boletas_en_buzon, id_tienda_original_boletas_en_buzon, id_tienda_boletas_en_buzon) REFERENCES boletas.boletas_en_buzon(id_terminal_pos, numero_boleta, fecha_transaccion, hora_transaccion, id_tienda_original, id_tienda) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boletas_en_buzon_detalle_pagos boletas_en_buzon_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT boletas_en_buzon_fk FOREIGN KEY (id_terminal_pos_boletas_en_buzon, numero_boleta_boletas_en_buzon, fecha_transaccion_boletas_en_buzon, hora_transaccion_boletas_en_buzon, id_tienda_original_boletas_en_buzon, id_tienda_boletas_en_buzon) REFERENCES boletas.boletas_en_buzon(id_terminal_pos, numero_boleta, fecha_transaccion, hora_transaccion, id_tienda_original, id_tienda) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boletas_en_buzon boletas_en_buzon_id_canal_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT boletas_en_buzon_id_canal_fk FOREIGN KEY (id_canal) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon boletas_en_buzon_id_disp_vta_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT boletas_en_buzon_id_disp_vta_fk FOREIGN KEY (id_dispositivo_venta) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon boletas_en_buzon_id_operacin_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT boletas_en_buzon_id_operacin_fk FOREIGN KEY (id_operacion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon_detalle_sku boletas_en_buzon_id_sku_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_sku
    ADD CONSTRAINT boletas_en_buzon_id_sku_fk FOREIGN KEY (id_sku) REFERENCES shared.productos(id_producto);


--
-- Name: boletas_en_buzon boletas_en_buzon_id_terminal_pos_orig_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT boletas_en_buzon_id_terminal_pos_orig_fk FOREIGN KEY (id_terminal_pos_original) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon boletas_en_buzon_id_tipo_des_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT boletas_en_buzon_id_tipo_des_fk FOREIGN KEY (id_tipo_descuento) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon boletas_en_buzon_id_tipo_trx_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT boletas_en_buzon_id_tipo_trx_fk FOREIGN KEY (id_tipo_transaccion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boletas_en_buzon boletas_en_buzon_id_vendedor_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT boletas_en_buzon_id_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: boleta_cabeceras catalogos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_tipo_transaccion) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boleta_detalles_sku catalogos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_seccion) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_detalle_pagos catalogos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_pagos
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_segmento) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boletas_en_buzon catalogos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boletas_en_buzon_detalle_sku catalogos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_sku
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_motivo_devolucion) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boletas_en_buzon_detalle_pagos catalogos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_terminal_pos) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_cabeceras cliente_direcciones_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT cliente_direcciones_fk FOREIGN KEY (id_direccion_seleccionada) REFERENCES clientes.cliente_direcciones(id_direccion_del_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_detalle_pagos cliente_formas_de_pago_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_pagos
    ADD CONSTRAINT cliente_formas_de_pago_fk FOREIGN KEY (id_cliente_tarjeta) REFERENCES clientes.cliente_formas_de_pago(id_cliente_tarjeta) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_detalle_abono_tarjetas cliente_formas_de_pago_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_abono_tarjetas
    ADD CONSTRAINT cliente_formas_de_pago_fk FOREIGN KEY (id_cliente_tarjeta) REFERENCES clientes.cliente_formas_de_pago(id_cliente_tarjeta) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_cabeceras clientes_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_detalle_pagos clientes_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_pagos
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boletas_en_buzon clientes_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boletas_en_buzon_detalle_pagos clientes_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente_clientes) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boleta_detalles_sku impuestos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT impuestos_fk FOREIGN KEY (id_impuesto) REFERENCES shared.impuestos(id_impuesto) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_detalle_impuestos impuestos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_impuestos
    ADD CONSTRAINT impuestos_fk FOREIGN KEY (id_impuesto) REFERENCES shared.impuestos(id_impuesto) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_cabeceras medios_de_pago_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT medios_de_pago_fk FOREIGN KEY (id_medio_de_pago_medios_de_pago) REFERENCES shared.medios_de_pago(id_medio_de_pago) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boleta_detalle_pagos medios_de_pago_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalle_pagos
    ADD CONSTRAINT medios_de_pago_fk FOREIGN KEY (id_medios_de_pago) REFERENCES shared.medios_de_pago(id_medio_de_pago) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boleta_cabeceras pedidos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT pedidos_fk FOREIGN KEY (numero_del_documento_pedidos, fecha_modificacion_pedidos, hora_modificacion_pedidos, estado_orden_pedidos) REFERENCES pedidos.pedidos(numero_del_documento, fecha_modificacion, hora_modificacion, estado_orden) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boleta_detalles_sku productos_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_detalles_sku
    ADD CONSTRAINT productos_fk FOREIGN KEY (id_sku) REFERENCES shared.productos(id_producto) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: boleta_cabeceras tiendas_y_locaciones_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boleta_cabeceras
    ADD CONSTRAINT tiendas_y_locaciones_fk FOREIGN KEY (id_tienda_original) REFERENCES shared.tiendas_y_locaciones(id_tienda_o_locacion) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boletas_en_buzon tiendas_y_locaciones_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon
    ADD CONSTRAINT tiendas_y_locaciones_fk FOREIGN KEY (id_tienda_recepcion) REFERENCES shared.tiendas_y_locaciones(id_tienda_o_locacion) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: boletas_en_buzon_detalle_pagos tiendas_y_locaciones_fk; Type: FK CONSTRAINT; Schema: boletas; Owner: -
--

ALTER TABLE ONLY boletas.boletas_en_buzon_detalle_pagos
    ADD CONSTRAINT tiendas_y_locaciones_fk FOREIGN KEY (id_tienda) REFERENCES shared.tiendas_y_locaciones(id_tienda_o_locacion) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_ticket_electronico catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ticket_electronico
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_tipo_forma_de_pago) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: clientes catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_pais) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_formas_de_pago catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_banco_emisor_tarjeta) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_ids catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_telefonos_de_contacto catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_emails_de_contacto catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_ids_bitacora catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids_bitacora
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_telefonos_de_contacto_bitacora catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto_bitacora
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_emails_de_contacto_bitacora catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto_bitacora
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_formas_de_pago_bitacora catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago_bitacora
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_atg catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_atg
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_operativo_dispositivo) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: clientes_bitacora catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes_bitacora
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_direcciones_bitacora catalogos_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones_bitacora
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_catalogo_catalogos) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_direcciones cliente_direcciones_clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_clientes_fk FOREIGN KEY (id_cliente_destinatario) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_direcciones cliente_direcciones_id_colonia_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_colonia_fk FOREIGN KEY (id_colonia) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_condado_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_condado_fk FOREIGN KEY (id_condado) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_distri1_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_distri1_fk FOREIGN KEY (salida_id_distrito) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_distrit_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_distrit_fk FOREIGN KEY (id_distrito) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_entfede_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_entfede_fk FOREIGN KEY (id_entidad_federativa) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_estadop_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_estadop_fk FOREIGN KEY (id_estado_del_pais) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_municip_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_municip_fk FOREIGN KEY (id_municipio) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_pais_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_pais_fk FOREIGN KEY (id_pais) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_provinc_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_provinc_fk FOREIGN KEY (id_provincia) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_sal_con_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_sal_con_fk FOREIGN KEY (salida_id_condado) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_sal_est_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_sal_est_fk FOREIGN KEY (salida_id_estado_del_pais) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_sal_prv_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_sal_prv_fk FOREIGN KEY (salida_id_provincia) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_sisorig_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_sisorig_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_direcciones cliente_direcciones_id_tipodir_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT cliente_direcciones_id_tipodir_fk FOREIGN KEY (id_tipo_direccion_cliente) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_telefonos_de_contacto_bitacora cliente_formas_de_pago_id_ciudad_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto_bitacora
    ADD CONSTRAINT cliente_formas_de_pago_id_ciudad_fk FOREIGN KEY (id_ciudad) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_formas_de_pago cliente_formas_de_pago_id_formas_de_pago_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago
    ADD CONSTRAINT cliente_formas_de_pago_id_formas_de_pago_fk FOREIGN KEY (id_formas_de_pago) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_telefonos_de_contacto_bitacora cliente_formas_de_pago_id_provincia_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto_bitacora
    ADD CONSTRAINT cliente_formas_de_pago_id_provincia_fk FOREIGN KEY (id_provincia) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_formas_de_pago cliente_formas_de_pago_id_sistema_origen_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago
    ADD CONSTRAINT cliente_formas_de_pago_id_sistema_origen_fk FOREIGN KEY (id_sistema_origen) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: cliente_ticket_electronico_medios_de_pago cliente_ticket_electronico_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ticket_electronico_medios_de_pago
    ADD CONSTRAINT cliente_ticket_electronico_fk FOREIGN KEY (id_cliente_ticket_electronico_cliente_ticket_electronico) REFERENCES clientes.cliente_ticket_electronico(id_cliente_ticket_electronico) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_ids_bitacora clientes_bitacora_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids_bitacora
    ADD CONSTRAINT clientes_bitacora_fk FOREIGN KEY (id_cliente_clientes_bitacora) REFERENCES clientes.clientes_bitacora(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_telefonos_de_contacto_bitacora clientes_bitacora_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto_bitacora
    ADD CONSTRAINT clientes_bitacora_fk FOREIGN KEY (id_cliente_clientes_bitacora) REFERENCES clientes.clientes_bitacora(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_emails_de_contacto_bitacora clientes_bitacora_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto_bitacora
    ADD CONSTRAINT clientes_bitacora_fk FOREIGN KEY (id_cliente_clientes_bitacora) REFERENCES clientes.clientes_bitacora(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_formas_de_pago_bitacora clientes_bitacora_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago_bitacora
    ADD CONSTRAINT clientes_bitacora_fk FOREIGN KEY (id_cliente_clientes_bitacora) REFERENCES clientes.clientes_bitacora(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_direcciones_bitacora clientes_bitacora_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones_bitacora
    ADD CONSTRAINT clientes_bitacora_fk FOREIGN KEY (id_cliente_bitacora) REFERENCES clientes.clientes_bitacora(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_direcciones clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_direcciones
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_formas_de_pago clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_formas_de_pago
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: cliente_ticket_electronico clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ticket_electronico
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_atg clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_atg
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: clientes_merge clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.clientes_merge
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_ids clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ids
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_telefonos_de_contacto clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_telefonos_de_contacto
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_emails_de_contacto clientes_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_emails_de_contacto
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: cliente_ticket_electronico_medios_de_pago medios_de_pago_fk; Type: FK CONSTRAINT; Schema: clientes; Owner: -
--

ALTER TABLE ONLY clientes.cliente_ticket_electronico_medios_de_pago
    ADD CONSTRAINT medios_de_pago_fk FOREIGN KEY (id_medio_de_pago_medios_de_pago) REFERENCES shared.medios_de_pago(id_medio_de_pago) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: pedidos catalogos_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_estado_del_documento) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: pedidos_detalle_sku catalogos_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos_detalle_sku
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_causa_de_noentrega) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: pedidos cliente_direcciones_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos
    ADD CONSTRAINT cliente_direcciones_fk FOREIGN KEY (id_direcciones_destinatario) REFERENCES clientes.cliente_direcciones(id_direccion_del_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: pedidos clientes_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos
    ADD CONSTRAINT clientes_fk FOREIGN KEY (id_cliente_remitente) REFERENCES clientes.clientes(id_cliente) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: pedidos_detalle_sku pedidos_detalle_sku_estado_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos_detalle_sku
    ADD CONSTRAINT pedidos_detalle_sku_estado_fk FOREIGN KEY (id_estado_por_linea) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: pedidos_detalle_sku pedidos_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos_detalle_sku
    ADD CONSTRAINT pedidos_fk FOREIGN KEY (numero_del_documento_pedidos, fecha_modificacion_pedidos, hora_modificacion_pedidos, estado_orden_pedidos) REFERENCES pedidos.pedidos(numero_del_documento, fecha_modificacion, hora_modificacion, estado_orden) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: pedidos pedidos_id_tiendaylocac_surte_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos
    ADD CONSTRAINT pedidos_id_tiendaylocac_surte_fk FOREIGN KEY (id_tienda_y_locacion_surte) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: pedidos_detalle_sku productos_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos_detalle_sku
    ADD CONSTRAINT productos_fk FOREIGN KEY (id_producto_productos) REFERENCES shared.productos(id_producto) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: pedidos_detalle_sku tiendas_y_locaciones_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos_detalle_sku
    ADD CONSTRAINT tiendas_y_locaciones_fk FOREIGN KEY (id_locacion_surte) REFERENCES shared.tiendas_y_locaciones(id_tienda_o_locacion) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: pedidos tiendas_y_locaciones_fk; Type: FK CONSTRAINT; Schema: pedidos; Owner: -
--

ALTER TABLE ONLY pedidos.pedidos
    ADD CONSTRAINT tiendas_y_locaciones_fk FOREIGN KEY (id_tienda_y_locacion_destino) REFERENCES shared.tiendas_y_locaciones(id_tienda_o_locacion) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: producto_tallas catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_tallas
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: producto_grupos catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_grupos
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: productos catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: producto_aplica_servicios catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_aplica_servicios
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: producto_submarcas catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_submarcas
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: producto_marcas catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_marcas
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: producto_direcciones catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_direcciones
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: producto_secciones catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_secciones
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_empresa_sku) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: tiendas_y_locaciones catalogos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT catalogos_fk FOREIGN KEY (id_colonia) REFERENCES shared.catalogos(id_catalogo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: productos producto_aplica_servicios_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT producto_aplica_servicios_fk FOREIGN KEY (id_producto_nospot) REFERENCES shared.producto_aplica_servicios(id_producto_aplica_servicios) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: producto_secciones producto_direcciones_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_secciones
    ADD CONSTRAINT producto_direcciones_fk FOREIGN KEY (id_producto_direccion) REFERENCES shared.producto_direcciones(id_producto_direccion) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: productos producto_grupos_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT producto_grupos_fk FOREIGN KEY (id_producto_grupos) REFERENCES shared.producto_grupos(id_producto_grupo) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: producto_submarcas producto_marcas_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_submarcas
    ADD CONSTRAINT producto_marcas_fk FOREIGN KEY (id_producto_marcas) REFERENCES shared.producto_marcas(id_producto_marcas) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: producto_grupos producto_secciones_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.producto_grupos
    ADD CONSTRAINT producto_secciones_fk FOREIGN KEY (id_producto_secciones_sku) REFERENCES shared.producto_secciones(id_producto_seccion_sku) MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: productos producto_submarcas_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT producto_submarcas_fk FOREIGN KEY (id_producto_submarcas, id_producto_submarcas1) REFERENCES shared.producto_submarcas(id_producto_submarcas, id_producto_marcas) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: productos producto_tallas_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT producto_tallas_fk FOREIGN KEY (id_producto_tallas) REFERENCES shared.producto_tallas(id_producto_tallas) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: productos productos_id_categoria_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_categoria_fk FOREIGN KEY (id_categoria) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos productos_id_estad_sku_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_estad_sku_fk FOREIGN KEY (id_estado_sku) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos productos_id_pais_sku_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_pais_sku_fk FOREIGN KEY (id_pais_sku) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos productos_id_prod_color_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_prod_color_fk FOREIGN KEY (id_producto_colores) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos productos_id_rangpresku_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_rangpresku_fk FOREIGN KEY (id_rango_precios_sku) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos productos_id_tempor_sku_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_tempor_sku_fk FOREIGN KEY (id_temporada_sku) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos productos_id_tipmer_sku_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_tipmer_sku_fk FOREIGN KEY (id_tipo_mercado_sku) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos productos_id_tipneg_sku_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT productos_id_tipneg_sku_fk FOREIGN KEY (id_tipo_de_negocio_sku) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: productos proveedores_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.productos
    ADD CONSTRAINT proveedores_fk FOREIGN KEY (id_proveedor) REFERENCES shared.proveedores(id_proveedor) MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: proveedores proveedores_id_empresas_sku_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.proveedores
    ADD CONSTRAINT proveedores_id_empresas_sku_fk FOREIGN KEY (id_empresas_sku) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: tiendas_y_locaciones tiendas_id_ciudad_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT tiendas_id_ciudad_fk FOREIGN KEY (id_ciudad) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: tiendas_y_locaciones tiendas_id_entidad_fd_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT tiendas_id_entidad_fd_fk FOREIGN KEY (id_entidad_federativa) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: tiendas_y_locaciones tiendas_id_grup_tiend_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT tiendas_id_grup_tiend_fk FOREIGN KEY (id_grupo_tienda) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: tiendas_y_locaciones tiendas_id_municipio_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT tiendas_id_municipio_fk FOREIGN KEY (id_municipio) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: tiendas_y_locaciones tiendas_id_pais_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT tiendas_id_pais_fk FOREIGN KEY (id_pais) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: tiendas_y_locaciones tiendas_id_zona_locac_fk; Type: FK CONSTRAINT; Schema: shared; Owner: -
--

ALTER TABLE ONLY shared.tiendas_y_locaciones
    ADD CONSTRAINT tiendas_id_zona_locac_fk FOREIGN KEY (id_zona_locacion) REFERENCES shared.catalogos(id_catalogo);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM cloudsqladmin;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO cloudsqlsuperuser;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

