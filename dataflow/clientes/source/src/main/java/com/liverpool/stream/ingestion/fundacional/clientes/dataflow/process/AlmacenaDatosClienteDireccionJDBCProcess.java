package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import java.sql.*;
import java.util.Objects;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.ClienteDireccion;

import javax.sql.DataSource;

public class AlmacenaDatosClienteDireccionJDBCProcess  extends DoFn<RegistraCliente, Cliente> {
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteDireccionJDBCProcess.class);
	private static final long serialVersionUID = 147123567L;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consulInsertClienteDireccion;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosClienteDireccion";

	public AlmacenaDatosClienteDireccionJDBCProcess(String jdbcUrl,
													String jdbcUsuario,
													String jdbcPassword,
													String consulInsertClienteDireccion,
													String instanceNameSM,
													Integer connectionPoolMaxSize,
													Integer connectionPoolMinIdle,
													Integer connectionPoolTimeout,
													Integer connectionPoolIdleTimeout,
													Integer connectionPoolMaxLifetime){
		this.jdbcUrl = jdbcUrl;
		this.jdbcUsuario = jdbcUsuario;
		this.jdbcPassword = jdbcPassword;
		this.consulInsertClienteDireccion = consulInsertClienteDireccion;
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
	public void processElement(DoFn<RegistraCliente, Cliente>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente Direccion");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Cliente clienteResponse = c.element().getCliente();

		try {
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consulInsertClienteDireccion);
			LOG.info("Creando el statement." + statement.toString());

			int id_cliente= c.element().getCliente().getId_cliente();

			ClienteDireccion clienteDireccion = c.element().getCliente().getDireccion();

			if ( Objects.isNull(clienteDireccion)  ){
				return;
			}

			if(Objects.isNull(clienteDireccion.getOperacion_crud()) ||
					clienteDireccion.getOperacion_crud().equals("INSERT")) {
				statement.setString(1, "INSERT");
				statement.setInt(2, 0);
			}
			if(Objects.nonNull(clienteDireccion.getOperacion_crud())) {
				if(clienteDireccion.getOperacion_crud().equals("UPDATE")) {
					statement.setString(1, "UPDATE");
					if(Objects.isNull(clienteDireccion.getId_direccion_del_cliente())){
						statement.setNull(2, java.sql.Types.INTEGER);
					}else {
						statement.setInt(2, clienteDireccion.getId_direccion_del_cliente());
					}
				}

				if(clienteDireccion.getOperacion_crud().equals("NONE")) {
					statement.setString(1, "NONE");
					if(Objects.isNull(clienteDireccion.getId_direccion_del_cliente())){
						statement.setNull(2, java.sql.Types.INTEGER);
					}else {
						statement.setInt(2, clienteDireccion.getId_direccion_del_cliente());
					}
				}
			}

			if (id_cliente == 0)
				statement.setNull(3, java.sql.Types.INTEGER);
			else
				statement.setInt(3, id_cliente);

			if(Objects.isNull(clienteDireccion.getEntre_calle_2()))
				statement.setNull(4, java.sql.Types.VARCHAR);
			else
				statement.setString(4, clienteDireccion.getEntre_calle_2());

			if(Objects.isNull(clienteDireccion.getEsta_borrado()))
				statement.setNull(5, java.sql.Types.VARCHAR);
			else
				statement.setString(5, clienteDireccion.getEsta_borrado());

			if(Objects.isNull(clienteDireccion.getAlias_de_direccion()))
				statement.setNull(6, java.sql.Types.VARCHAR);
			else
				statement.setString(6, clienteDireccion.getAlias_de_direccion());

			if(Objects.isNull(clienteDireccion.getEdificio()))
				statement.setNull(7, java.sql.Types.VARCHAR);
			else
				statement.setString(7, clienteDireccion.getEdificio());

			if(Objects.isNull(clienteDireccion.getId_colonia()))
				statement.setNull(8, java.sql.Types.INTEGER);
			else
				statement.setInt(8, clienteDireccion.getId_colonia());

			if(Objects.isNull(clienteDireccion.getId_municipio()))
				statement.setNull(9, java.sql.Types.INTEGER);
			else
				statement.setInt(9, clienteDireccion.getId_municipio());

			if(Objects.isNull(clienteDireccion.getId_estado_del_pais()))
				statement.setNull(10, java.sql.Types.INTEGER);
			else
				statement.setInt(10, clienteDireccion.getId_estado_del_pais());

			if (Objects.isNull(clienteDireccion.getCalle()))
				statement.setNull(11, java.sql.Types.VARCHAR);
			else
				statement.setString(11, clienteDireccion.getCalle());

			if(Objects.isNull(clienteDireccion.getNumero_interior()))
				statement.setNull(12, java.sql.Types.VARCHAR);
			else
				statement.setString(12, clienteDireccion.getNumero_interior());

			if(Objects.isNull(clienteDireccion.getEntre_calle_1()))
				statement.setNull(13, java.sql.Types.VARCHAR);
			else
				statement.setString(13, clienteDireccion.getEntre_calle_1());

			if(Objects.isNull(clienteDireccion.getId_tipo_direccion_cliente()))
				statement.setNull(14, java.sql.Types.INTEGER);
			else
				statement.setInt(14, clienteDireccion.getId_tipo_direccion_cliente());

			if(Objects.isNull(clienteDireccion.getCodigo_postal()))
				statement.setNull(15, java.sql.Types.VARCHAR);
			else
				statement.setString(15, clienteDireccion.getCodigo_postal());

			if(Objects.isNull(clienteDireccion.getNumero_exterior()))
				statement.setNull(16, java.sql.Types.VARCHAR);
			else
				statement.setString(16, clienteDireccion.getNumero_exterior());

			if(Objects.isNull(clienteDireccion.getId_sistema_origen()))
				statement.setNull(17, java.sql.Types.INTEGER);
			else
				statement.setInt(17, clienteDireccion.getId_sistema_origen());

			if(Objects.isNull(clienteDireccion.getId_transaccion()))
				statement.setNull(18, java.sql.Types.VARCHAR);
			else
				statement.setString(18, clienteDireccion.getId_transaccion());

			if(Objects.isNull(clienteDireccion.getId_operacion()))
				statement.setNull(19, java.sql.Types.VARCHAR);
			else
				statement.setString(19, clienteDireccion.getId_operacion());

			if(Objects.isNull(clienteDireccion.getDireccion_2()))
				statement.setNull(20, java.sql.Types.VARCHAR);
			else
				statement.setString(20, clienteDireccion.getDireccion_2());

			if(Objects.isNull(clienteDireccion.getDireccion_3()))
				statement.setNull(21, java.sql.Types.VARCHAR);
			else
				statement.setString(21, clienteDireccion.getDireccion_3());

			if(Objects.isNull(clienteDireccion.getDireccion_4()))
				statement.setNull(22, java.sql.Types.VARCHAR);
			else
				statement.setString(22, clienteDireccion.getDireccion_4());

			if(Objects.isNull(clienteDireccion.getDireccion_5()))
				statement.setNull(23, java.sql.Types.VARCHAR);
			else
				statement.setString(23, clienteDireccion.getDireccion_5());

			if(Objects.isNull(clienteDireccion.getTipo_de_direccion()))
				statement.setNull(24, java.sql.Types.VARCHAR);
			else
				statement.setString(24, clienteDireccion.getTipo_de_direccion());

			if(Objects.isNull(clienteDireccion.getCodigo_zip()))
				statement.setNull(25, java.sql.Types.VARCHAR);
			else
				statement.setString(25, clienteDireccion.getCodigo_zip());

			if(Objects.isNull(clienteDireccion.getId_distrito()))
				statement.setNull(26, java.sql.Types.INTEGER);
			else
				statement.setInt(26, clienteDireccion.getId_distrito());

			if(Objects.isNull(clienteDireccion.getId_provincia()))
				statement.setNull(27, java.sql.Types.INTEGER);
			else
				statement.setInt(27, clienteDireccion.getId_provincia());

			if(Objects.isNull(clienteDireccion.getId_condado()))
				statement.setNull(28, java.sql.Types.INTEGER);
			else
				statement.setInt(28, clienteDireccion.getId_condado());

			if(Objects.isNull(clienteDireccion.getSalida_numero_exterior()))
				statement.setNull(29, java.sql.Types.VARCHAR);
			else
				statement.setString(29, clienteDireccion.getSalida_numero_exterior());

			if(Objects.isNull(clienteDireccion.getSalida_calle()))
				statement.setNull(30, java.sql.Types.VARCHAR);
			else
				statement.setString(30, clienteDireccion.getSalida_calle());

			if(Objects.isNull(clienteDireccion.getSalida_direccion_2()))
				statement.setNull(31, java.sql.Types.VARCHAR);
			else
				statement.setString(31, clienteDireccion.getSalida_direccion_2());

			if(Objects.isNull(clienteDireccion.getSalida_direccion_3()))
				statement.setNull(32, java.sql.Types.VARCHAR);
			else
				statement.setString(32, clienteDireccion.getSalida_direccion_3());

			if(Objects.isNull(clienteDireccion.getSalida_direccion_4()))
				statement.setNull(33, java.sql.Types.VARCHAR);
			else
				statement.setString(33, clienteDireccion.getSalida_direccion_4());

			if(Objects.isNull(clienteDireccion.getSalida_direccion_5()))
				statement.setNull(34, java.sql.Types.VARCHAR);
			else
				statement.setString(34, clienteDireccion.getSalida_direccion_5());

			if(Objects.isNull(clienteDireccion.getSalida_tipo()))
				statement.setNull(35, java.sql.Types.VARCHAR);
			else
				statement.setString(35, clienteDireccion.getSalida_tipo());

			if(Objects.isNull(clienteDireccion.getSalida_codigo_zip()))
				statement.setNull(36, java.sql.Types.VARCHAR);
			else
				statement.setString(36, clienteDireccion.getSalida_codigo_zip());

			if(Objects.isNull(clienteDireccion.getSalida_id_distrito()))
				statement.setNull(37, java.sql.Types.INTEGER);
			else
				statement.setInt(37, clienteDireccion.getSalida_id_distrito());

			if(Objects.isNull(clienteDireccion.getSalida_id_provincia()))
				statement.setNull(38, java.sql.Types.INTEGER);
			else
				statement.setInt(38, clienteDireccion.getSalida_id_provincia());

			if(Objects.isNull(clienteDireccion.getSalida_id_estado_del_pais()))
				statement.setNull(39, java.sql.Types.INTEGER);
			else
				statement.setInt(39, clienteDireccion.getSalida_id_estado_del_pais());

			if(Objects.isNull(clienteDireccion.getSalida_id_condado()))
				statement.setNull(40, java.sql.Types.INTEGER);
			else
				statement.setInt(40, clienteDireccion.getSalida_id_condado());

			if(Objects.isNull(clienteDireccion.getBandera_calle()))
				statement.setNull(41, java.sql.Types.VARCHAR);
			else
				statement.setString(41, clienteDireccion.getBandera_calle());

			if(Objects.isNull(clienteDireccion.getBandera_emcccp()))
				statement.setNull(42, java.sql.Types.VARCHAR);
			else
				statement.setString(42, clienteDireccion.getBandera_emcccp());

			if(Objects.isNull(clienteDireccion.getBandera_termino()))
				statement.setNull(43, java.sql.Types.VARCHAR);
			else
				statement.setString(43, clienteDireccion.getBandera_termino());

			if(Objects.isNull(clienteDireccion.getToken_calle()))
				statement.setNull(44, java.sql.Types.VARCHAR);
			else
				statement.setString(44, clienteDireccion.getToken_calle());

			if(Objects.isNull(clienteDireccion.getFecha_de_operacion()))
				statement.setNull(45, java.sql.Types.TIMESTAMP);
			else
				statement.setTimestamp(45, java.sql.Timestamp.valueOf(clienteDireccion.getFecha_de_operacion()));

			if(Objects.isNull(clienteDireccion.getBaja_domicilio()))
				statement.setNull(46, java.sql.Types.VARCHAR);
			else
				statement.setString(46, clienteDireccion.getBaja_domicilio());


			if(Objects.isNull(clienteDireccion.getNumero_de_apartamento()))
				statement.setNull(47, java.sql.Types.VARCHAR);
			else
				statement.setString(47, clienteDireccion.getNumero_de_apartamento());

			if(Objects.isNull(clienteDireccion.getFecha_creacion()))
				statement.setNull(48, java.sql.Types.TIMESTAMP);
			else
				statement.setTimestamp(48, java.sql.Timestamp.valueOf(clienteDireccion.getFecha_creacion()));

			if(Objects.isNull(clienteDireccion.getFecha_actualizacion()))
				statement.setNull(49, java.sql.Types.TIMESTAMP);
			else
				statement.setTimestamp(49, java.sql.Timestamp.valueOf(clienteDireccion.getFecha_actualizacion()));

			if(Objects.isNull(clienteDireccion.getId_direccion_atg()))
				statement.setNull(50, java.sql.Types.VARCHAR);
			else
				statement.setString(50, clienteDireccion.getId_direccion_atg());

			boolean rowsInserted = statement.execute();
			LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

			if(rowsInserted) {
				resultSet = statement.getResultSet();

				if(resultSet.next()) {
					int id_cliente_direccion = resultSet.getInt(1);
					LOG.info("id_cliente_destinatario {}",id_cliente_direccion );

					Cliente cliente = c.element().getCliente();
					Cliente copyCliente = (Cliente) cliente.clone();

					ClienteDireccion  copyClienteDireccion = (ClienteDireccion) clienteDireccion.clone();
					copyClienteDireccion.setId_direccion_del_cliente(id_cliente_direccion);
					copyCliente.setDireccion(copyClienteDireccion);

					clienteResponse =  copyCliente;
				}

			}

		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query ClienteDireccion en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());

		} catch(Exception e) {
			LOG.error("Error al procesar ClienteDireccion:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente Direccion");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente Direccion:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente Direccion:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente Direccion:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente Direccion:" + es.getMessage(), es);
			} try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente Direccion:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente Direccion:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente Direccion");
			if(Objects.nonNull(clienteResponse)) {
				c.output(clienteResponse);
			}
		}
		LOG.info("Finalizando process en Almacena Datos Cliente Direccion");
	}

}


