package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.BoletaDetalleTarjetasPrepago;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


public class AlmacenaDatosBoletaDetalleTarjetasPrepagoJDBCProcess extends DoFn<BoletaDetalleTarjetasPrepago, String> {


    private static final long serialVersionUID = 8235431239334125542L;
    private static final Logger LOG = LoggerFactory.getLogger(AlmacenaDatosBoletaDetalleTarjetasPrepagoJDBCProcess.class);
    private String jdbcUrl;
    private String jdbcUsuario;
    private String jdbcPassword;
    private String consulInsertBoletaDetalleTarjetasPrepago;
    private Properties props;
    private DataSource dataSource;
    private ObjectMapper mapper;
    private JavaTimeModule javaTimeModule;
    private String instanceNameSM;
    private String applicationName;
    private Integer connectionPoolMaxSize;
    private Integer connectionPoolMinIdle;
    private Integer connectionPoolTimeout;
    private Integer connectionPoolIdleTimeout;
    private Integer connectionPoolMaxLifetime;
    private String poolName;
    private final String stepName = "AlmacenaDatosBoletaDetalleTarjetasPrepagoJDBCProcess";


    public AlmacenaDatosBoletaDetalleTarjetasPrepagoJDBCProcess(String jdbcUrl,
                                                                String jdbcUsuario,
                                                                String jdbcPassword,
                                                                String consulInsertBoletaDetalleTarjetasPrepago,
                                                                String instanceNameSM,
                                                                Integer connectionPoolMaxSize,
                                                                Integer connectionPoolMinIdle,
                                                                Integer connectionPoolTimeout,
                                                                Integer connectionPoolIdleTimeout,
                                                                Integer connectionPoolMaxLifetime,
                                                                String poolName
    ) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsuario = jdbcUsuario;
        this.jdbcPassword = jdbcPassword;
        this.consulInsertBoletaDetalleTarjetasPrepago = consulInsertBoletaDetalleTarjetasPrepago;
        this.instanceNameSM=instanceNameSM;
        this.connectionPoolMaxSize=connectionPoolMaxSize;
        this.connectionPoolMinIdle=connectionPoolMinIdle;
        this.connectionPoolTimeout=connectionPoolTimeout;
        this.connectionPoolIdleTimeout=connectionPoolIdleTimeout;
        this.connectionPoolMaxLifetime=connectionPoolMaxLifetime;
        this.poolName=poolName;

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

        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        mapper.registerModule(javaTimeModule);

    }

    @ProcessElement
    public void processElement(DoFn<BoletaDetalleTarjetasPrepago, String>.ProcessContext c) throws Exception {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        BoletaDetalleTarjetasPrepago boletaDetalleTarjetasPrepago = c.element();
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(this.consulInsertBoletaDetalleTarjetasPrepago);

            if(boletaDetalleTarjetasPrepago != null){

                statement.setString(1, boletaDetalleTarjetasPrepago.getId_terminal_pos());
                statement.setString(2, boletaDetalleTarjetasPrepago.getNumero_boleta());
                statement.setDate(3, Date.valueOf(boletaDetalleTarjetasPrepago.getFecha_fin_transaccion()));
                statement.setTime(4, Time.valueOf(boletaDetalleTarjetasPrepago.getHora_fin_transaccion()));
                statement.setString(5, boletaDetalleTarjetasPrepago.getId_tienda_origen());
                statement.setString(6, boletaDetalleTarjetasPrepago.getId_tienda_reconocimiento());
                statement.setString(7, boletaDetalleTarjetasPrepago.getCodigo_upc());


                if (boletaDetalleTarjetasPrepago.getImporte_tarjeta_prepago() == null)
                    statement.setNull(8, java.sql.Types.NUMERIC);
                else
                    statement.setBigDecimal(8, boletaDetalleTarjetasPrepago.getImporte_tarjeta_prepago());
                boolean rowsInserted = statement.execute();
                LOG.info("Executando el statement, rowsInserted: " + rowsInserted);

                if(rowsInserted){
                    resultSet = statement.getResultSet();

                    if(resultSet.next()) {

                        int valorAlmacenaDatosBoletaCabecera = resultSet.getInt(1);
                        //LOG.info("valorAlmacenaDatosBoletaCabecera {}",valorAlmacenaDatosBoletaCabecera );


                    }
                }
            }

        } catch (Exception e) {
            LOG.error("Error al procesar Cliente ATG:" + e.getMessage(), e);
            throw new Exception(e.getMessage());
        } finally {
            //LOG.info("Entre al Finally de Almacena Datos Boleta Cabecera");
            try {
                if (resultSet != null) {
                    resultSet.close();
                    //LOG.info("Cerrando el resultset en Almacena Datos Boleta Cabecera:");
                }
            } catch (Exception er) {
                LOG.error("Error al cerrare el resultset en Almacena Datos Boleta Cabecera:" + er.getMessage(), er);
            }
            try {
                if (statement != null) {
                    // LOG.info("Cerrando el statement en Almacena Datos Boleta Cabecera:");
                    statement.close();
                }
            } catch (Exception es) {
                LOG.error("Error al cerrar el statement en Almacena Datos Boleta Cabecera:" + es.getMessage(), es);
            }
            try {
                if (conn != null) {
                    conn.close();
                    //LOG.info("Cerrando el conn en Almacena Datos Boleta Cabecera:");
                }
            } catch (Exception en) {
                LOG.error("Error al cerrar la conexion en Almacena Datos Boleta Cabecera:" + en.getMessage(), en);
            }
            //LOG.info("Sali al Finally de Almacena Datos Boleta Cabecera");

            if(boletaDetalleTarjetasPrepago != null){
                c.output("{\"boleta_detalle_tarjetas_prepago\": " + mapper.writeValueAsString(boletaDetalleTarjetasPrepago) + "}");
            }

        }
        LOG.info("Finalizando process en Almacena Datos Boleta Detalle Tarjetas Prepago");
    }
}