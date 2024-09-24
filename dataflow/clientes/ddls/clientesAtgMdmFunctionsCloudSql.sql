-- drop function clientes.am_cliente_destinatario_crud;
-- drop function clientes.am_cliente_destinatario_upd;
-- drop function clientes.am_cliente_destinatario_ins;
-- drop function clientes.am_cliente_formas_de_pago_crud;
-- drop function clientes.am_cliente_formas_de_pago_upd;
-- drop function clientes.am_cliente_formas_de_pago_ins;
-- drop function clientes.am_cliente_direcciones_crud;
-- drop function clientes.am_cliente_direcciones_upd;
-- drop function clientes.am_cliente_direcciones_ins;
-- drop function clientes.am_cliente_telefonos_de_contacto_crud;
-- drop function clientes.am_cliente_telefonos_de_contacto_upd;
-- drop function clientes.am_cliente_telefonos_de_contacto_ins;
-- drop function clientes.am_cliente_emails_de_contacto_crud;
-- drop function clientes.am_cliente_emails_de_contacto_upd;
-- drop function clientes.am_cliente_emails_de_contacto_ins;
-- drop function clientes.am_clientes_crud;
-- drop function clientes.am_clientes_upd;
-- drop function clientes.am_clientes_ins;

--clientes

CREATE OR REPLACE FUNCTION clientes.am_clientes_ins
(
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_fecha_de_nacimiento date,
	p_genero varchar,
	p_rfc varchar(25),
	p_numero_de_seguro_social varchar(50),
	p_fecha_de_registro date,
	p_id_sistema_origen integer,
	p_id_transaccion varchar(50),
	p_id_operacion varchar(50),
	p_salida_primer_nombre varchar(100),
	p_salida_segundo_nombre varchar(100),
	p_bandera_del_nombre varchar(50),
	p_salida_genero varchar,
	p_salida_fecha_de_nacimiento date,
	p_salida_rfc varchar(50),
	p_salida_apellido_paterno varchar(100),
	p_bandera_rfc varchar(50),
	p_bandera_ucm varchar(50),
	p_nombre_de_token varchar(100),
	p_fecha_de_la_operacion date,
	p_salida_bandera_fecha varchar(50),
	p_salida_token_primer_nombre varchar(50),
	p_salida_token_apellido_paterno varchar(50),
	p_salida_token_segundo_nombre varchar(50),
	p_salida_bandera_rfc varchar(50),
	p_id_pais integer
)
RETURNS integer AS
$func$
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
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.am_clientes_upd
(
	p_id_cliente integer,
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_fecha_de_nacimiento date,
	p_genero varchar,
	p_rfc varchar(25),
	p_numero_de_seguro_social varchar(50),
	p_fecha_de_registro date,
	p_id_sistema_origen integer,
	p_id_transaccion varchar(50),
	p_id_operacion varchar(50),
	p_salida_primer_nombre varchar(100),
	p_salida_segundo_nombre varchar(100),
	p_bandera_del_nombre varchar(50),
	p_salida_genero varchar,
	p_salida_fecha_de_nacimiento date,
	p_salida_rfc varchar(50),
	p_salida_apellido_paterno varchar(100),
	p_bandera_rfc varchar(50),
	p_bandera_ucm varchar(50),
	p_nombre_de_token varchar(100),
	p_fecha_de_la_operacion date,
	p_salida_bandera_fecha varchar(50),
	p_salida_token_primer_nombre varchar(50),
	p_salida_token_apellido_paterno varchar(50),
	p_salida_token_segundo_nombre varchar(50),
	p_salida_bandera_rfc varchar(50),
	p_id_pais integer
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
$func$  LANGUAGE sql;



CREATE OR REPLACE FUNCTION clientes.am_clientes_crud (
	p_operacion_crud varchar(30),
	p_id_cliente integer,
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_fecha_de_nacimiento date,
	p_genero varchar,
	p_rfc varchar(25),
	p_numero_de_seguro_social varchar(50),
	p_fecha_de_registro date,
	p_id_sistema_origen integer,
	p_id_transaccion varchar(50),
	p_id_operacion varchar(50),
	p_salida_primer_nombre varchar(100),
	p_salida_segundo_nombre varchar(100),
	p_bandera_del_nombre varchar(50),
	p_salida_genero varchar,
	p_salida_fecha_de_nacimiento date,
	p_salida_rfc varchar(50),
	p_salida_apellido_paterno varchar(100),
	p_bandera_rfc varchar(50),
	p_bandera_ucm varchar(50),
	p_nombre_de_token varchar(100),
	p_fecha_de_la_operacion date,
	p_salida_bandera_fecha varchar(50),
	p_salida_token_primer_nombre varchar(50),
	p_salida_token_apellido_paterno varchar(50),
	p_salida_token_segundo_nombre varchar(50),
	p_salida_bandera_rfc varchar(50),
	p_id_pais integer
	
) RETURNS SETOF integer AS $$
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

END$$ LANGUAGE plpgsql;


--emails de contacto



CREATE OR REPLACE FUNCTION clientes.am_cliente_emails_de_contacto_ins
(
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_tipo varchar(25),
	p_email varchar(100),
	p_esta_borrado varchar,
	p_id_transaccion varchar(100),
	p_id_operacion varchar(100),
	p_salida_correo_electronico varchar(100),
	p_salida_tipo varchar(100),
	p_bandera_tipo varchar(100),
	p_token_correo_electronico varchar(100),
	p_fecha_operacion date
)
RETURNS integer AS
$func$
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
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.am_cliente_emails_de_contacto_upd
(
	p_id_cliente_emails_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_tipo varchar(25),
	p_email varchar(100),
	p_esta_borrado varchar,
	p_id_transaccion varchar(100),
	p_id_operacion varchar(100),
	p_salida_correo_electronico varchar(100),
	p_salida_tipo varchar(100),
	p_bandera_tipo varchar(100),
	p_token_correo_electronico varchar(100),
	p_fecha_operacion date
)
RETURNS integer AS
$func$

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
$func$  LANGUAGE sql;
	
	
	
CREATE OR REPLACE FUNCTION clientes.am_cliente_emails_de_contacto_crud (
	p_operacion_crud varchar(30),
	p_id_cliente_emails_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_tipo varchar(25),
	p_email varchar(100),
	p_esta_borrado varchar,
	p_id_transaccion varchar(100),
	p_id_operacion varchar(100),
	p_salida_correo_electronico varchar(100),
	p_salida_tipo varchar(100),
	p_bandera_tipo varchar(100),
	p_token_correo_electronico varchar(100),
	p_fecha_operacion date
) RETURNS void AS $$
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

END$$ LANGUAGE plpgsql;




-- Telefonos de contacto


CREATE OR REPLACE FUNCTION clientes.am_cliente_telefonos_de_contacto_ins
(
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_alias_telefono varchar(16),
	p_telefono varchar(16),
	p_esta_borrado varchar,
	p_id_transaccion varchar(100),
	p_id_operacion varchar(100),
	p_lada varchar(100),
	p_extension_telefonica varchar(100),
	p_codigo_zip varchar(100),
	p_id_provincia integer,
	p_id_ciudad integer,
	p_salida_numero_de_telefono varchar(100),
	p_salida_tipo_de_telefono varchar(100),
	p_bandera_telefono varchar(100),
	p_salida_extension_telefonica varchar(100),
	p_fecha_de_operacion date
)
RETURNS integer AS
$func$
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
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.am_cliente_telefonos_de_contacto_upd
(
	p_id_cliente_telefonos_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_alias_telefono varchar(16),
	p_telefono varchar(16),
	p_esta_borrado varchar,
	p_id_transaccion varchar(100),
	p_id_operacion varchar(100),
	p_lada varchar(100),
	p_extension_telefonica varchar(100),
	p_codigo_zip varchar(100),
	p_id_provincia integer,
	p_id_ciudad integer,
	p_salida_numero_de_telefono varchar(100),
	p_salida_tipo_de_telefono varchar(100),
	p_bandera_telefono varchar(100),
	p_salida_extension_telefonica varchar(100),
	p_fecha_de_operacion date
)
RETURNS integer AS
$func$
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
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.am_cliente_telefonos_de_contacto_crud (
	p_operacion_crud varchar(30),
	p_id_cliente_telefonos_de_contacto integer,
	p_id_cliente integer,
	p_id_sistema_origen integer,
	p_alias_telefono varchar(16),
	p_telefono varchar(16),
	p_esta_borrado varchar,
	p_id_transaccion varchar(100),
	p_id_operacion varchar(100),
	p_lada varchar(100),
	p_extension_telefonica varchar(100),
	p_codigo_zip varchar(100),
	p_id_provincia integer,
	p_id_ciudad integer,
	p_salida_numero_de_telefono varchar(100),
	p_salida_tipo_de_telefono varchar(100),
	p_bandera_telefono varchar(100),
	p_salida_extension_telefonica varchar(100),
	p_fecha_de_operacion date
	
) RETURNS void AS $$
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

END$$ LANGUAGE plpgsql;



--cliente_direcciones


CREATE OR REPLACE FUNCTION clientes.am_cliente_direcciones_ins(p_id_cliente integer, p_entre_calle_2 character varying, p_esta_borrado character varying, p_alias_de_direccion character varying, p_edificio character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_calle character varying, p_numero_interior character varying, p_entre_calle_1 character varying, p_id_tipo_direccion_cliente integer, p_codigo_postal character varying, p_numero_exterior character varying, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_direccion_2 character varying, p_direccion_3 character varying, p_direccion_4 character varying, p_direccion_5 character varying, p_tipo_de_direccion character varying, p_codigo_zip character varying, p_id_distrito integer, p_id_provincia integer, p_id_condado integer, p_salida_numero_exterior character varying, p_salida_calle character varying, p_salida_direccion_2 character varying, p_salida_direccion_3 character varying, p_salida_direccion_4 character varying, p_salida_direccion_5 character varying, p_salida_tipo character varying, p_salida_codigo_zip character varying, p_salida_id_distrito integer, p_salida_id_provincia integer, p_salida_id_estado_del_pais integer, p_salida_id_condado integer, p_bandera_calle character varying, p_bandera_emcccp character varying, p_bandera_termino character varying, p_token_calle character varying, p_fecha_de_operacion date, p_baja_domicilio character varying, p_id_pais integer, p_numero_apartamento character varying, p_fecha_creacion date, p_fecha_actualizacion date, p_id_direccion_atg character varying)
 RETURNS integer
 LANGUAGE sql
AS $function$
insert
	into
	clientes.cliente_direcciones (id_direccion_del_cliente,
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
	id_pais,
	numero_apartamento,
	fecha_creacion,
	fecha_actualizacion,
	id_direccion_atg
	)
values (nextval('clientes.seq_cliente_direcciones_pk'),
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
p_id_pais,
p_numero_apartamento,
p_fecha_creacion,
p_fecha_actualizacion,
p_id_direccion_atg) returning id_direccion_del_cliente;

$function$
;


CREATE OR REPLACE FUNCTION clientes.am_cliente_direcciones_upd(p_id_direccion_del_cliente integer, p_id_cliente integer, p_entre_calle_2 character varying, p_esta_borrado character varying, p_alias_de_direccion character varying, p_edificio character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_calle character varying, p_numero_interior character varying, p_entre_calle_1 character varying, p_id_tipo_direccion_cliente integer, p_codigo_postal character varying, p_numero_exterior character varying, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_direccion_2 character varying, p_direccion_3 character varying, p_direccion_4 character varying, p_direccion_5 character varying, p_tipo_de_direccion character varying, p_codigo_zip character varying, p_id_distrito integer, p_id_provincia integer, p_id_condado integer, p_salida_numero_exterior character varying, p_salida_calle character varying, p_salida_direccion_2 character varying, p_salida_direccion_3 character varying, p_salida_direccion_4 character varying, p_salida_direccion_5 character varying, p_salida_tipo character varying, p_salida_codigo_zip character varying, p_salida_id_distrito integer, p_salida_id_provincia integer, p_salida_id_estado_del_pais integer, p_salida_id_condado integer, p_bandera_calle character varying, p_bandera_emcccp character varying, p_bandera_termino character varying, p_token_calle character varying, p_fecha_de_operacion date, p_baja_domicilio character varying, p_id_pais integer, p_numero_apartamento character varying, p_fecha_creacion date, p_fecha_actualizacion date, p_id_direccion_atg character varying)
 RETURNS integer
 LANGUAGE sql
AS $function$
update
	clientes.cliente_direcciones
set
	id_cliente = p_id_cliente,
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
	id_pais = p_id_pais,
	numero_apartamento = p_numero_apartamento,
	fecha_creacion = p_fecha_creacion,
	fecha_actualizacion = p_fecha_actualizacion,
	id_direccion_atg = p_id_direccion_atg
where
	id_direccion_del_cliente = p_id_direccion_del_cliente returning id_direccion_del_cliente;

$function$
;



CREATE OR REPLACE FUNCTION clientes.am_cliente_direcciones_crud(p_operacion_crud character varying, p_id_direccion_del_cliente integer, p_id_cliente integer, p_entre_calle_2 character varying, p_esta_borrado character varying, p_alias_de_direccion character varying, p_edificio character varying, p_id_colonia integer, p_id_municipio integer, p_id_estado_del_pais integer, p_calle character varying, p_numero_interior character varying, p_entre_calle_1 character varying, p_id_tipo_direccion_cliente integer, p_codigo_postal character varying, p_numero_exterior character varying, p_id_sistema_origen integer, p_id_transaccion character varying, p_id_operacion character varying, p_direccion_2 character varying, p_direccion_3 character varying, p_direccion_4 character varying, p_direccion_5 character varying, p_tipo_de_direccion character varying, p_codigo_zip character varying, p_id_distrito integer, p_id_provincia integer, p_id_condado integer, p_salida_numero_exterior character varying, p_salida_calle character varying, p_salida_direccion_2 character varying, p_salida_direccion_3 character varying, p_salida_direccion_4 character varying, p_salida_direccion_5 character varying, p_salida_tipo character varying, p_salida_codigo_zip character varying, p_salida_id_distrito integer, p_salida_id_provincia integer, p_salida_id_estado_del_pais integer, p_salida_id_condado integer, p_bandera_calle character varying, p_bandera_emcccp character varying, p_bandera_termino character varying, p_token_calle character varying, p_fecha_de_operacion date, p_baja_domicilio character varying, p_id_pais integer, p_numero_apartamento character varying, p_fecha_creacion date, p_fecha_actualizacion date, p_id_direccion_atg character varying)
 RETURNS SETOF integer
 LANGUAGE plpgsql
AS $function$ begin if p_operacion_crud = 'INSERT' then return QUERY select clientes.am_cliente_direcciones_ins (p_id_cliente,
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
p_id_pais,
p_numero_apartamento,
p_fecha_creacion,
p_fecha_actualizacion,
p_id_direccion_atg);
end if;

if p_operacion_crud = 'UPDATE' then return QUERY select clientes.am_cliente_direcciones_upd (p_id_direccion_del_cliente,
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
p_id_pais,
p_numero_apartamento,
p_fecha_creacion,
p_fecha_actualizacion,
p_id_direccion_atg);
end if;

END$function$
;




--cliente formas de pago

CREATE OR REPLACE FUNCTION clientes.am_cliente_formas_de_pago_ins(p_numero_tarjeta_cliente character varying, p_tipo_tarjeta character varying, p_esta_activa smallint, p_hash1_tarjeta character varying, p_id_cliente integer, p_id_banco_emisor_tarjeta integer, p_id_sistema_origen integer, p_fecha_creacion date, p_fecha_actualizacion date, p_id_direccion_de_tarjeta integer)
 RETURNS integer
 LANGUAGE sql
AS $function$
insert
	into
	clientes.cliente_formas_de_pago (id_cliente_tarjeta,
	numero_tarjeta_cliente,
	tipo_tarjeta,
	esta_activa,
	hash1_tarjeta,
	id_cliente,
	id_banco_emisor_tarjeta,
	id_sistema_origen,
	fecha_creacion,
	fecha_actualizacion,
	id_direccion_de_tarjeta)
values (nextval('clientes.seq_cliente_formas_de_pago_pk'),
p_numero_tarjeta_cliente,
p_tipo_tarjeta,
p_esta_activa,
p_hash1_tarjeta,
p_id_cliente,
p_id_banco_emisor_tarjeta,
p_id_sistema_origen,
p_fecha_creacion,
p_fecha_actualizacion,
p_id_direccion_de_tarjeta) returning id_cliente_tarjeta;

$function$
;


CREATE OR REPLACE FUNCTION clientes.am_cliente_formas_de_pago_upd(p_id_cliente_tarjeta integer, p_numero_tarjeta_cliente character varying, p_tipo_tarjeta character varying, p_esta_activa smallint, p_hash1_tarjeta character varying, p_id_cliente integer, p_id_banco_emisor_tarjeta integer, p_id_sistema_origen integer, p_fecha_creacion date, p_fecha_actualizacion date, p_id_direccion_de_tarjeta integer)
 RETURNS integer
 LANGUAGE sql
AS $function$
update
	clientes.cliente_formas_de_pago
set
	numero_tarjeta_cliente = p_numero_tarjeta_cliente,
	tipo_tarjeta = p_tipo_tarjeta,
	esta_activa = p_esta_activa,
	hash1_tarjeta = p_hash1_tarjeta,
	id_cliente = p_id_cliente,
	id_banco_emisor_tarjeta = p_id_banco_emisor_tarjeta,
	id_sistema_origen = p_id_sistema_origen,
	fecha_creacion = p_fecha_creacion,
	fecha_actualizacion = p_fecha_actualizacion,
	id_direccion_de_tarjeta = p_id_direccion_de_tarjeta
where
	id_cliente_tarjeta = p_id_cliente_tarjeta returning id_cliente_tarjeta;

$function$
;



CREATE OR REPLACE FUNCTION clientes.am_cliente_formas_de_pago_crud(p_operacion_crud character varying, p_id_cliente_tarjeta integer, p_numero_tarjeta_cliente character varying, p_tipo_tarjeta character varying, p_esta_activa smallint, p_hash1_tarjeta character varying, p_id_cliente integer, p_id_banco_emisor_tarjeta integer, p_id_sistema_origen integer, p_fecha_creacion date, p_fecha_actualizacion date, p_id_direccion_de_tarjeta integer)
 RETURNS void
 LANGUAGE plpgsql
AS $function$ begin


	if p_operacion_crud = 'INSERT' then perform clientes.am_cliente_formas_de_pago_ins (p_numero_tarjeta_cliente,
		p_tipo_tarjeta,
		p_esta_activa,
		p_hash1_tarjeta,
		p_id_cliente,
		p_id_banco_emisor_tarjeta,
		p_id_sistema_origen,
		p_fecha_creacion,
		p_fecha_actualizacion,
		p_id_direccion_de_tarjeta
	);
	end if;

if p_operacion_crud = 'UPDATE' then perform clientes.am_cliente_formas_de_pago_upd (p_id_cliente_tarjeta,
p_numero_tarjeta_cliente,
p_tipo_tarjeta,
p_esta_activa,
p_hash1_tarjeta,
p_id_cliente,
p_id_banco_emisor_tarjeta,
p_id_sistema_origen,
p_fecha_creacion,
p_fecha_actualizacion,
p_id_direccion_de_tarjeta);
end if;

END$function$
;


--cliente destinatario

CREATE OR REPLACE FUNCTION clientes.am_cliente_destinatario_ins
(
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_id_cliente_padre_del_destinatario integer
)
RETURNS integer AS
$func$
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
$func$  LANGUAGE sql;


CREATE OR REPLACE FUNCTION clientes.am_cliente_destinatario_upd
(
	p_id_cliente integer,
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_id_cliente_padre_del_destinatario integer
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
							id_cliente_padre_del_destinatario = p_id_cliente_padre_del_destinatario
			WHERE id_cliente = p_id_cliente
	RETURNING id_cliente;
$func$  LANGUAGE sql;


	
CREATE OR REPLACE FUNCTION clientes.am_cliente_destinatario_crud (
	p_operacion_crud varchar(30),
	p_id_cliente integer,
	p_primer_nombre varchar(100),
	p_segundo_nombre varchar(100),
	p_apellido_paterno varchar(100),
	p_apellido_materno varchar(100),
	p_id_cliente_padre_del_destinatario integer
	
) RETURNS void AS $$
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

END$$ LANGUAGE plpgsql;
