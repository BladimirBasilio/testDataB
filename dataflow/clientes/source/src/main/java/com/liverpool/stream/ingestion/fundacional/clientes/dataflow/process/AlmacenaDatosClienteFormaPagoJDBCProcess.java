package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import java.sql.*;
import java.util.Objects;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteFormaPago;

import javax.sql.DataSource;

public class AlmacenaDatosClienteFormaPagoJDBCProcess extends DoFn<ClienteFormaPago, KV<String,String>> {
	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteJDBCProcess.class);
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertClienteFormaPago;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteFormaPago";
	
	public AlmacenaDatosClienteFormaPagoJDBCProcess(String jdbcUrl,
												 String jdbcUsuario,
												 String jdbcPassword,
												 String consultaInsertClienteFormaPago,
												 String instanceNameSM,
												 Integer connectionPoolMaxSize,
												 Integer connectionPoolMinIdle,
												 Integer connectionPoolTimeout,
												 Integer connectionPoolIdleTimeout,
												 Integer connectionPoolMaxLifetime)
	{
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consultaInsertClienteFormaPago = consultaInsertClienteFormaPago;
		this.connectionPoolMaxSize = connectionPoolMaxSize;
		this.connectionPoolMinIdle = connectionPoolMinIdle;
		this.connectionPoolTimeout = connectionPoolTimeout;
		this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
		this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
		this.instanceNameSM = instanceNameSM;
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
	public void processElement(DoFn<ClienteFormaPago, KV<String,String>>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Forma Pago");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try{
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertClienteFormaPago);
			LOG.info("Creando el statement." + statement.toString());

			ClienteFormaPago clienteFormaPago = c.element();

			if(Objects.isNull(clienteFormaPago.getOperacion_crud()) ||
					clienteFormaPago.getOperacion_crud().equals("INSERT")) {
				statement.setString(1, "INSERT");
				statement.setInt(2, 0);
			}
			if(Objects.nonNull(clienteFormaPago.getOperacion_crud())) {
				if(clienteFormaPago.getOperacion_crud().equals("UPDATE")) {
					statement.setString(1, "UPDATE");
					statement.setInt(2, clienteFormaPago.getId_cliente_tarjeta());
				}
				if(clienteFormaPago.getOperacion_crud().equals("NONE")) {
					statement.setString(1, "NONE");
					statement.setInt(2, clienteFormaPago.getId_cliente_tarjeta());
				}
			}

			if(Objects.isNull(clienteFormaPago.getNumero_tarjeta_cliente()))
				statement.setNull(3, java.sql.Types.VARCHAR);
			else
				statement.setString(3, clienteFormaPago.getNumero_tarjeta_cliente());

			if(Objects.isNull(clienteFormaPago.getTipo_tarjeta()))
				statement.setNull(4, java.sql.Types.VARCHAR);
			else
				statement.setString(4, clienteFormaPago.getTipo_tarjeta());

			if(Objects.isNull(clienteFormaPago.getEsta_borrado()))
				statement.setNull(5, java.sql.Types.VARCHAR);
			else
				statement.setString(5, clienteFormaPago.getEsta_borrado());

			if(Objects.isNull(clienteFormaPago.getHash1_tarjeta()))
				statement.setNull(6, java.sql.Types.VARCHAR);
			else
				statement.setString(6, clienteFormaPago.getHash1_tarjeta());

			if(clienteFormaPago.getId_cliente()== null)
				statement.setNull(7, java.sql.Types.INTEGER);
			else
				statement.setInt(7, clienteFormaPago.getId_cliente());

			if(Objects.isNull(clienteFormaPago.getId_banco_emisor_tarjeta()))
				statement.setNull(8, java.sql.Types.INTEGER);
			else
				statement.setInt(8, clienteFormaPago.getId_banco_emisor_tarjeta());

			if(Objects.isNull(clienteFormaPago.getId_sistema_origen()))
				statement.setNull(9, java.sql.Types.INTEGER);
			else
				statement.setInt(9, clienteFormaPago.getId_sistema_origen());

			if(Objects.isNull(clienteFormaPago.getFecha_creacion()))
				statement.setNull(10, Types.TIMESTAMP);
			else
				statement.setTimestamp(10, java.sql.Timestamp.valueOf(clienteFormaPago.getFecha_creacion()));

			if(Objects.isNull(clienteFormaPago.getFecha_actualizacion()))
				statement.setNull(11, Types.TIMESTAMP);
			else
				statement.setTimestamp(11, java.sql.Timestamp.valueOf(clienteFormaPago.getFecha_actualizacion()));

			if(Objects.isNull(clienteFormaPago.getId_direccion_de_tarjeta()))
				statement.setNull(12, java.sql.Types.INTEGER);
			else
				statement.setInt(12, clienteFormaPago.getId_direccion_de_tarjeta());

			boolean rowsInserted = statement.execute();
			LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

			if(rowsInserted){
				resultSet = statement.getResultSet();

				if(resultSet.next()) {

					int id_cliente_forma_pago = resultSet.getInt(1);
					LOG.info("id_cliente_forma_pago {}",id_cliente_forma_pago );

				}
			}
		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente Forma Pago en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e) {
			LOG.error("Error al procesar Cliente Forma Pago:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Forma Pago");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Forma Pago:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Forma Pago:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Forma Pago:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Forma Pago:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Forma Pago:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Forma Pago:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente Forma Pago");
			if(Objects.nonNull(c.element().getId_cliente())) {
				c.output(KV.of(c.element().getId_cliente().toString(), "AlmacenaDatosClienteFormaPago"));
			}
		}
		LOG.info("Finalizando process en Almacena Datos Cliente Forma Pago");

	}
	
}


