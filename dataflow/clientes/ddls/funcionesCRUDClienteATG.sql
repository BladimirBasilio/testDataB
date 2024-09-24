CREATE OR REPLACE FUNCTION clientes.cliente_atg_crud(
	p_operacion_crud character varying
	, p_id_cliente_atg integer
	, p_id_atg character varying
	, p_sistema_fuente_atg character varying
	, p_id_cliente integer
	, p_hash_1 character varying
	, p_id_tipo_tarjeta character varying
	, p_realm_id character varying
	, p_baja_de_tarjeta character varying
	, p_id_facebook character varying
	, p_id_sistema_operativo_dispositivo integer
	, p_id_apple character varying
	, p_alta_de_tarjeta character varying
	)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
BEGIN		
		IF p_operacion_crud = 'INSERT' THEN
			PERFORM clientes.cliente_atg_ins (p_id_atg,
					p_sistema_fuente_atg,
					p_id_cliente,
					p_hash_1,
					p_id_tipo_tarjeta,
					p_realm_id,
					p_baja_de_tarjeta,
					p_id_facebook,
					p_id_sistema_operativo_dispositivo,
					p_id_apple,
					p_alta_de_tarjeta); 
		END IF;
		
		IF p_operacion_crud = 'UPDATE' THEN
			PERFORM clientes.cliente_atg_upd (p_id_cliente_atg,
										p_id_atg,
										p_sistema_fuente_atg,
										p_id_cliente,
										p_hash_1,
										p_id_tipo_tarjeta,
										p_realm_id,
										p_baja_de_tarjeta,
										p_id_facebook,
										p_id_sistema_operativo_dispositivo,
										p_id_apple,
										p_alta_de_tarjeta);  
		END IF;

END$function$
;

CREATE OR REPLACE FUNCTION clientes.cliente_atg_ins(p_id_atg character varying, p_sistema_fuente_atg character varying, p_id_cliente integer, p_hash_1 character varying, p_id_tipo_tarjeta character varying, p_realm_id character varying, p_baja_de_tarjeta character varying, p_id_facebook character varying, p_id_sistema_operativo_dispositivo integer, p_id_apple character varying, p_alta_de_tarjeta character varying)
 RETURNS integer
 LANGUAGE sql
AS $function$
	insert
	into
	clientes.cliente_atg (id_cliente_atg,
	id_atg,
	sistema_fuente_atg,
	id_cliente,
	hash_1,
	id_tipo_tarjeta,
	realm_id,
	baja_de_tarjeta,
	id_facebook,
	id_sistema_operativo_dispositivo,
	id_apple,
	alta_de_tarjeta)
values(nextval('clientes.seq_cliente_atg_pk'),
p_id_atg,
p_sistema_fuente_atg,
p_id_cliente,
p_hash_1,
p_id_tipo_tarjeta,
p_realm_id,
p_baja_de_tarjeta,
p_id_facebook,
p_id_sistema_operativo_dispositivo,
p_id_apple,
p_alta_de_tarjeta)
	RETURNING id_cliente_atg;
$function$
;

CREATE OR REPLACE FUNCTION clientes.cliente_atg_upd(p_id_cliente_atg integer, p_id_atg character varying, p_sistema_fuente_atg character varying, p_id_cliente integer, p_hash_1 character varying, p_id_tipo_tarjeta character varying, p_realm_id character varying, p_baja_de_tarjeta character varying, p_id_facebook character varying, p_id_sistema_operativo_dispositivo integer, p_id_apple character varying, p_alta_de_tarjeta character varying)
 RETURNS integer
 LANGUAGE sql
AS $function$
	update
	clientes.cliente_atg
set
	id_atg = p_id_atg,
	sistema_fuente_atg = p_sistema_fuente_atg,
	id_cliente = p_id_cliente,
	hash_1 = p_hash_1,
	id_tipo_tarjeta = p_id_tipo_tarjeta,
	realm_id = p_realm_id,
	baja_de_tarjeta = p_id_tipo_tarjeta,
	id_facebook = p_id_facebook,
	id_sistema_operativo_dispositivo = p_id_sistema_operativo_dispositivo,
	id_apple = p_id_apple,
	alta_de_tarjeta = p_alta_de_tarjeta
where
	id_cliente_atg = p_id_cliente_atg
RETURNING p_id_cliente_atg;
$function$
;