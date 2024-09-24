package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import java.sql.*;
import java.util.Objects;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.Cliente;
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.RegistraCliente;

import javax.sql.DataSource;


public class AlmacenaDatosClienteJDBCProcess extends DoFn<RegistraCliente, RegistraCliente>    {


	private static final long serialVersionUID = 1462827258L;
	private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosClienteJDBCProcess.class);
	private Connection conn;
	private PreparedStatement statement;
	private String jdbcUrl;
	private String jdbcUsuario;
	private String jdbcPassword;
	private String consultaInsertCliente;
	private String instanceNameSM;
	private String applicationName;
	private Integer connectionPoolMaxSize;
	private Integer connectionPoolMinIdle;
	private Integer connectionPoolTimeout;
	private Integer connectionPoolIdleTimeout;
	private Integer connectionPoolMaxLifetime;
	private DataSource dataSource;
	private final String stepName = "AlmacenaDatosCliente";

	
	public AlmacenaDatosClienteJDBCProcess(String jdbcUrl,
										  String jdbcUsuario,
										  String jdbcPassword,
										  String consultaInsertCliente,
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
		this.consultaInsertCliente = consultaInsertCliente;
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
	public void processElement(DoFn<RegistraCliente, RegistraCliente>.ProcessContext c) throws Exception {

		LOG.info("Iniciando process en Almacena Datos Cliente");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		RegistraCliente registraCliente = c.element();

		//RegistraCliente responseRegistraCliente = (RegistraCliente) registraCliente.clone();
		RegistraCliente copyRegistraCliente = (RegistraCliente) registraCliente.clone();
		Cliente cliente = registraCliente.getCliente();

		try {
			conn = dataSource.getConnection();
			LOG.info("Creando la conexion:." +  conn.toString());

			statement = conn.prepareStatement(this.consultaInsertCliente);
			LOG.info("Creando el statement." + statement.toString());

				//RegistraCliente registraCliente = c.element();

				//Cliente cliente = registraCliente.getCliente();


			if(Objects.nonNull(copyRegistraCliente)&&Objects.nonNull(cliente)){
				if (Objects.isNull(cliente.getOperacion_crud()) ||
						cliente.getOperacion_crud().equals("INSERT")) {
					statement.setString(1, "INSERT");
					statement.setInt(2, 0);
				}
				if (Objects.nonNull(cliente.getOperacion_crud())) {
					if (cliente.getOperacion_crud().equals("UPDATE")) {
						statement.setString(1, "UPDATE");
						statement.setInt(2, cliente.getId_cliente());
					}

					if (cliente.getOperacion_crud().equals("NONE")) {
						statement.setString(1, "NONE");
						statement.setInt(2, cliente.getId_cliente());
					}
				}


				if (Objects.isNull(cliente.getPrimer_nombre()))
					statement.setNull(3, java.sql.Types.VARCHAR);
				else
					statement.setString(3, cliente.getPrimer_nombre());

				if (Objects.isNull(cliente.getSegundo_nombre()))
					statement.setNull(4, java.sql.Types.VARCHAR);
				else
					statement.setString(4, cliente.getSegundo_nombre());

				if (Objects.isNull(cliente.getApellido_paterno()))
					statement.setNull(5, java.sql.Types.VARCHAR);
				else
					statement.setString(5, cliente.getApellido_paterno());

				if (Objects.isNull(cliente.getApellido_materno()))
					statement.setNull(6, java.sql.Types.VARCHAR);
				else
					statement.setString(6, cliente.getApellido_materno());

				if (Objects.isNull(cliente.getFecha_de_nacimiento()))
					statement.setNull(7, java.sql.Types.TIMESTAMP);
				else
					statement.setTimestamp(7, java.sql.Timestamp.valueOf(cliente.getFecha_de_nacimiento()));

				if (Objects.isNull(cliente.getGenero()))
					statement.setNull(8, java.sql.Types.VARCHAR);
				else
					statement.setString(8, cliente.getGenero());

				if (Objects.isNull(cliente.getRfc()))
					statement.setNull(9, java.sql.Types.VARCHAR);
				else
					statement.setString(9, cliente.getRfc());

				if (Objects.isNull(cliente.getNumero_de_seguro_social()))
					statement.setNull(10, java.sql.Types.VARCHAR);
				else
					statement.setString(10, cliente.getNumero_de_seguro_social());

				if (Objects.isNull(cliente.getFecha_de_registro()))
					statement.setNull(11, Types.TIMESTAMP);
				else
					statement.setTimestamp(11, java.sql.Timestamp.valueOf(cliente.getFecha_de_registro()));

				if (Objects.isNull(cliente.getId_sistema_origen()))
					statement.setNull(12, java.sql.Types.INTEGER);
				else
					statement.setInt(12, cliente.getId_sistema_origen());

				if (Objects.isNull(cliente.getId_transaccion()))
					statement.setNull(13, java.sql.Types.VARCHAR);
				else
					statement.setString(13, cliente.getId_transaccion());

				if (Objects.isNull(cliente.getId_operacion()))
					statement.setNull(14, java.sql.Types.VARCHAR);
				else
					statement.setString(14, cliente.getId_operacion());

				if (Objects.isNull(cliente.getSalida_primer_nombre()))
					statement.setNull(15, java.sql.Types.VARCHAR);
				else
					statement.setString(15, cliente.getSalida_primer_nombre());

				if (Objects.isNull(cliente.getSalida_apellido_materno()))
					statement.setNull(16, java.sql.Types.VARCHAR);
				else
					statement.setString(16, cliente.getSalida_apellido_materno());

				if (Objects.isNull(cliente.getBandera_del_nombre()))
					statement.setNull(17, java.sql.Types.VARCHAR);
				else
					statement.setString(17, cliente.getBandera_del_nombre());

				if (Objects.isNull(cliente.getSalida_genero()))
					statement.setNull(18, java.sql.Types.VARCHAR);
				else
					statement.setString(18, cliente.getSalida_genero());

				if (Objects.isNull(cliente.getSalida_fecha_de_nacimiento()))
					statement.setNull(19, Types.TIMESTAMP);
				else
					statement.setTimestamp(19, java.sql.Timestamp.valueOf(cliente.getSalida_fecha_de_nacimiento()));

				if (Objects.isNull(cliente.getSalida_rfc()))
					statement.setNull(20, java.sql.Types.VARCHAR);
				else
					statement.setString(20, cliente.getSalida_rfc());

				if (Objects.isNull(cliente.getSalida_apellido_paterno()))
					statement.setNull(21, java.sql.Types.VARCHAR);
				else
					statement.setString(21, cliente.getSalida_apellido_paterno());

				if (Objects.isNull(cliente.getBandera_rfc()))
					statement.setNull(22, java.sql.Types.VARCHAR);
				else
					statement.setString(22, cliente.getBandera_rfc());

				if (Objects.isNull(cliente.getBandera_ucm()))
					statement.setNull(23, java.sql.Types.VARCHAR);
				else
					statement.setString(23, cliente.getBandera_ucm());

				if (Objects.isNull(cliente.getNombre_de_token()))
					statement.setNull(24, java.sql.Types.VARCHAR);
				else
					statement.setString(24, cliente.getNombre_de_token());

				if (Objects.isNull(cliente.getFecha_de_la_operacion()))
					statement.setNull(25, Types.TIMESTAMP);
				else
					statement.setTimestamp(25, java.sql.Timestamp.valueOf(cliente.getFecha_de_la_operacion()));

				if (Objects.isNull(cliente.getSalida_bandera_fecha()))
					statement.setNull(26, java.sql.Types.VARCHAR);
				else
					statement.setString(26, cliente.getSalida_bandera_fecha());

				if (Objects.isNull(cliente.getSalida_token_primer_nombre()))
					statement.setNull(27, java.sql.Types.VARCHAR);
				else
					statement.setString(27, cliente.getSalida_token_primer_nombre());

				if (Objects.isNull(cliente.getSalida_token_apellido_paterno() ))
					statement.setNull(28, java.sql.Types.VARCHAR);
				else
					statement.setString(28, cliente.getSalida_token_apellido_paterno());

				if (Objects.isNull(cliente.getSalida_token_apellido_materno() ))
					statement.setNull(29, java.sql.Types.VARCHAR);
				else
					statement.setString(29, cliente.getSalida_token_apellido_materno());

				if (Objects.isNull(cliente.getSalida_bandera_rfc() ))
					statement.setNull(30, java.sql.Types.VARCHAR);
				else
					statement.setString(30, cliente.getSalida_bandera_rfc());

				if (Objects.isNull(cliente.getId_pais()))
					statement.setNull(31, java.sql.Types.INTEGER);
				else
					statement.setInt(31, cliente.getId_pais());

				if (Objects.isNull(cliente.getRow_id()))
					statement.setNull(32, java.sql.Types.VARCHAR);
				else
					statement.setString(32, cliente.getRow_id());

				if (Objects.isNull(cliente.getAlias_row_id()))
					statement.setNull(33, java.sql.Types.VARCHAR);
				else
					statement.setString(33, cliente.getAlias_row_id());

				if (Objects.isNull(cliente.getId_mdm()))
					statement.setNull(34, java.sql.Types.VARCHAR);
				else
					statement.setString(34, cliente.getId_mdm());

				if (Objects.isNull(cliente.getAlias_id_mdm()))
					statement.setNull(35, java.sql.Types.VARCHAR);
				else
					statement.setString(35, cliente.getAlias_id_mdm());

				if (Objects.isNull(cliente.getFecha_de_nacimiento_area_gris()))
					statement.setNull(36, java.sql.Types.VARCHAR);
				else
					statement.setString(36, cliente.getFecha_de_nacimiento_area_gris());

				if (Objects.isNull(cliente.getFecha_creacion() ))
					statement.setNull(37, Types.TIMESTAMP);
				else
					statement.setTimestamp(37, java.sql.Timestamp.valueOf(cliente.getFecha_creacion()));

				if (Objects.isNull(cliente.getFecha_actualizacion()))
					statement.setNull(38, Types.TIMESTAMP);
				else
					statement.setTimestamp(38, java.sql.Timestamp.valueOf(cliente.getFecha_actualizacion()));

				if (Objects.isNull(cliente.getId_sistema_externo()))
					statement.setNull(39, Types.VARCHAR);
				else
					statement.setString(39, cliente.getId_sistema_externo());

				if (Objects.isNull(cliente.getNombre_sistema_externo()))
					statement.setNull(40, Types.VARCHAR);
				else
					statement.setString(40, cliente.getNombre_sistema_externo());

				if (Objects.isNull(cliente.getId_atg() ))
					statement.setNull(41, Types.VARCHAR);
				else
					statement.setString(41, cliente.getId_atg());

				boolean rowsInserted = statement.execute();
				LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

				if(rowsInserted){
					resultSet = statement.getResultSet();

					if(resultSet.next()) {

						int id_cliente = resultSet.getInt(1);
						LOG.info("id_cliente {}",id_cliente );

						//RegistraCliente copyRegistraCliente = (RegistraCliente) registraCliente.clone();
						//Cliente copyCliente = (Cliente) cliente.clone();

						//copyCliente.setId_cliente(id_cliente);
						cliente.setId_cliente(id_cliente);
						copyRegistraCliente.setCliente(cliente);

						//responseRegistraCliente = copyRegistraCliente;

					}
				}
			}

		} catch(SQLException sqlex) {
			LOG.error("Error al intentar query Cliente en la base de datos:" + sqlex.getMessage(), sqlex);
			throw new Exception(sqlex.getMessage());
		} catch(Exception e){
			LOG.error("Error al procesar Cliente:" + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			LOG.info("Entre al Finally de Almacena Datos Cliente");
			try {
				if (Objects.nonNull(resultSet)) {
					resultSet.close();
					LOG.info("Cerrando el resultset en Almacena Datos Cliente:");
				}
			} catch (Exception er) {
				LOG.error("Error al cerrare el resultset en Almacena Datos Cliente:" + er.getMessage(), er);
			}
			try {
				if (Objects.nonNull(statement)) {
					LOG.info("Cerrando el statement en Almacena Datos Cliente:");
					statement.close();
				}
			} catch (Exception es) {
				LOG.error("Error al cerrar el statement en Almacena Datos Cliente:" + es.getMessage(), es);
			}
			try {
				if (Objects.nonNull(conn)) {
					conn.close();
					LOG.info("Cerrando el conn en Almacena Datos Cliente:" );
				}
			} catch (Exception en) {
				LOG.error("Error al cerrar la conexion en Almacena Datos Cliente:" + en.getMessage(), en);
			}
			LOG.info("Sali al Finally de Almacena Datos Cliente");
			if(Objects.nonNull(copyRegistraCliente)) {
				c.output(copyRegistraCliente);
			}
		}
		LOG.info("Finalizando process en Almacena Datos Cliente");
	}
}
