package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import java.sql.*;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteTelefonoContacto;

import javax.sql.DataSource;

public class AlmacenaDatosClienteTelefonoContactoJDBCProcess extends DoFn<ClienteTelefonoContacto, KV<String,String>> {
	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteTelefonoContacto;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteTelefonoContacto";

	public AlmacenaDatosClienteTelefonoContactoJDBCProcess(String jdbcUrl,
														   String jdbcUsuario,
														   String jdbcPassword,
														   String consultaInsertClienteTelefonoContacto,
														   String instanceNameSM,
														   Integer connectionPoolMaxSize,
														   Integer connectionPoolMinIdle,
														   Integer connectionPoolTimeout,
														   Integer connectionPoolIdleTimeout,
														   Integer connectionPoolMaxLifetime )
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertClienteTelefonoContacto = consultaInsertClienteTelefonoContacto;
		this.instanceNameSM = instanceNameSM;
		this.connectionPoolMaxSize = connectionPoolMaxSize;
		this.connectionPoolMinIdle = connectionPoolMinIdle;
		this.connectionPoolTimeout = connectionPoolTimeout;
		this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
	}

	@Setup
	public void init() {

		dataSource = ConnectionPoolProvider.getInstance(this.jdbcUrl,
				this.jdbcPassword,
				this.jdbcUsuario,
				this.instanceNameSM,
				this.applicationName,
				this.connectionPoolMaxSize,
				this.connectionPoolMinIdle,
				this.connectionPoolTimeout,
				this.connectionPoolIdleTimeout,
				this.connectionPoolMaxLifetime,
				this.stepName).getDataSource();
	}

	@ProcessElement
	public void processElement(DoFn<ClienteTelefonoContacto, KV<String,String>>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Telefono Contacto");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			ClienteTelefonoContacto element = c.element();

			if(!Objects.isNull(element) && !Objects.isNull(element.getTelefono()) && element.getTelefono().trim().length() > 0){
				conn = dataSource.getConnection();
				LOG.info("Creando la conexion:." +  conn.toString());

				statement = conn.prepareStatement(this.consultaInsertClienteTelefonoContacto);
				LOG.info("Creando el statement." + statement.toString());

				if(Objects.isNull(element.getOperacion_crud()) ||
						element.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if(Objects.nonNull(element.getOperacion_crud())) {
					if(element.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, element.getId_cliente_telefonos_de_contacto());
					}

					if(element.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
						statement.setInt(2, element.getId_cliente_telefonos_de_contacto());
					}
				}

				if(Objects.isNull(element.getId_cliente()))
					statement.setNull(3, java.sql.Types.INTEGER);
				else
					statement.setInt(3, element.getId_cliente());

				if(Objects.isNull(element.getId_sistema_origen()))
					statement.setNull(4, java.sql.Types.INTEGER);
				else
					statement.setInt(4, element.getId_sistema_origen());

				if(Objects.isNull(element.getAlias_telefono()))
					statement.setNull(5, java.sql.Types.VARCHAR);
				else
					statement.setString(5, element.getAlias_telefono());

				if(Objects.isNull(element.getTelefono()))
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, element.getTelefono());

				if(Objects.isNull(element.getEsta_borrado()))
					statement.setNull(7, java.sql.Types.VARCHAR);
				else
					statement.setString(7, element.getEsta_borrado());

				if(Objects.isNull(element.getId_transaccion()))
					statement.setNull(8, java.sql.Types.VARCHAR);
				else
					statement.setString(8, element.getId_transaccion());

				if(Objects.isNull(element.getId_operacion()))
					statement.setNull(9, java.sql.Types.VARCHAR);
				else
					statement.setString(9, element.getId_operacion());

				if(Objects.isNull(element.getLada()))
					statement.setNull(10, java.sql.Types.VARCHAR);
				else
					statement.setString(10, element.getLada());

				if(Objects.isNull(element.getExtension_telefonica()))
					statement.setNull(11, java.sql.Types.VARCHAR);
				else
					statement.setString(11, element.getExtension_telefonica());

				if(Objects.isNull(element.getCodigo_zip()))
					statement.setNull(12, java.sql.Types.VARCHAR);
				else
					statement.setString(12, element.getCodigo_zip());

				if(Objects.isNull(element.getId_provincia()))
					statement.setNull(13, java.sql.Types.INTEGER);
				else
					statement.setInt(13, element.getId_provincia());

				if(Objects.isNull(element.getId_ciudad()))
					statement.setNull(14, java.sql.Types.INTEGER);
				else
					statement.setInt(14, element.getId_ciudad());

				if(Objects.isNull(element.getSalida_numero_de_telefono()))
					statement.setNull(15, java.sql.Types.VARCHAR);
				else
					statement.setString(15, element.getSalida_numero_de_telefono());

				if(Objects.isNull(element.getSalida_tipo_de_telefono()))
					statement.setNull(16, java.sql.Types.VARCHAR);
				else
					statement.setString(16, element.getSalida_tipo_de_telefono());

				if(Objects.isNull(element.getBandera_telefono()))
					statement.setNull(17, java.sql.Types.VARCHAR);
				else
					statement.setString(17, element.getBandera_telefono());

				if(Objects.isNull(element.getSalida_extension_telefonica()))
					statement.setNull(18, java.sql.Types.VARCHAR);
				else
					statement.setString(18, element.getSalida_extension_telefonica());

				if(Objects.isNull(element.getFecha_de_operacion()))
					statement.setNull(19, Types.TIMESTAMP);
				else
					statement.setTimestamp(19, java.sql.Timestamp.valueOf(element.getFecha_de_operacion()));

				if(Objects.isNull(element.getFecha_creacion()))
					statement.setNull(20, Types.TIMESTAMP);
				else
					statement.setTimestamp(20, java.sql.Timestamp.valueOf(element.getFecha_creacion()));

				if(Objects.isNull(element.getFecha_actualizacion()))
					statement.setNull(21, Types.TIMESTAMP);
				else
					statement.setTimestamp(21, java.sql.Timestamp.valueOf(element.getFecha_actualizacion()));

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int id_cliente_telefono_contacto = resultSet.getInt(1);
						LOG.info("id_cliente_telefono_contacto {}", id_cliente_telefono_contacto);

					}
				}
			}else{
				LOG.info("El cliente telefono de contacto no proporciono un numero correcto.");
				LOG.info("Datos del Cliente Telefono de Contacto: {}", element);
			}

		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Telefono Contacto en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente Telefono Contacto:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Telefono Contacto");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Telefono Contacto:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Telefono Contacto:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Telefono Contacto:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Telefono Contacto:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Telefono Contacto:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Telefono Contacto:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente Telefono Contacto");
			if(Objects.nonNull(c.element().getId_cliente())){
				c.output(KV.of(c.element().getId_cliente().toString(), "AlmacenaDatosClienteTelefonoContacto"));
			}
		}
		LOG.info("Finalizando process en Almacena Datos Cliente Telefono Contacto");

	}
}


