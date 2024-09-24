package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.option;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Description;


public interface RegistraClienteOptions extends  DataflowPipelineOptions  {

    @Description("Pub/Sub Nombre Topico")
    String getNombreTopico();
    void setNombreTopico(String value);

    @Description("Pub/Sub Nombre Suscripcion")
    String getNombreSuscripcion();
    void setNombreSuscripcion(String value);

    @Description("Nombre Jdbc Driver")
    String getNombreJdbcDriver();
    void setNombreJdbcDriver(String value);

    @Description("Jdbc Url")
    String getJdbcUrl();
    void setJdbcUrl(String value);

    @Description("Usuario Jdbc")
    String getUsuarioJdbc();
    void setUsuarioJdbc(String value);

    @Description("Password Jdbc")
    String getPasswordJdbc();
    void setPasswordJdbc(String value);

    @Description("Consulta Insert Clientes")
    String getConsultaInsertClientes();
    void setConsultaInsertClientes(String value);

    @Description("Consulta Insert Clientes Direccion")
    String getConsultaInsertClientesDireccion();
    void setConsultaInsertClientesDireccion(String value);

    @Description("Consulta Insert Clientes Telefono")
    String getConsultaInsertClientesTelefono();
    void setConsultaInsertClientesTelefono(String value);

    @Description("Consulta Insert Clientes Email")
    String getConsultaInsertClientesEmail();
    void setConsultaInsertClientesEmail(String value);

    @Description("Consulta Insert Clientes Formas de Pago")
    String getConsultaInsertClientesFormaPago();
    void setConsultaInsertClientesFormaPago(String value);

    @Description("Consulta Insert Cliente Merge")
    String getConsultaInsertClienteMerge();
    void setConsultaInsertClienteMerge(String value);

    @Description("Consulta Insert Cliente Atg")
    String getConsultaInsertClienteAtg();
    void setConsultaInsertClienteAtg(String value);


    @Description("Consulta Insert Cliente Id")
    String getConsultaInsertClienteId();
    void setConsultaInsertClienteId(String value);

    @Description("Consulta Datos Cliente por id_clinete_padre_destinatario")
    String getConsultaDataClienteByIdPadreDestinatario();
    void setConsultaDataClienteByIdPadreDestinatario(String value);

    @Description("Consulta Insert Cliente Destinatario")
    String getConsultaInsertClienteDestinatario();
    void setConsultaInsertClienteDestinatario(String value);

    @Description("Pub/Sub Nombre Topico Cliente Insertado")
    String getNombreTopicoClienteInsertado();
    void setNombreTopicoClienteInsertado(String value);

    @Description("Pub/Sub Nombre Suscripcion Cliente Insertado")
    String getNombreSuscripcionClienteInsertado();
    void setNombreSuscripcionClienteInsertado(String value);

    @Description("Pub/Sub Nombre Topico Hash Insertado")
    String getNombreTopicoClienteHash();
    void setNombreTopicoClienteHash(String value);

    @Description("Instance Name SM")
    String getInstanceNameSM();
    void setInstanceNameSM(String value);

    @Description("Connection Pool Max Size")
    Integer getConnectionPoolMaxSize();
    void setConnectionPoolMaxSize(Integer value);

    @Description("Connection Pool Min Idle")
    Integer getConnectionPoolMinIdle();
    void setConnectionPoolMinIdle(Integer value);

    @Description("Connection Pool Timeout")
    Integer getConnectionPoolTimeout();
    void setConnectionPoolTimeout(Integer value);

    @Description("Connection Pool Idle Timeout")
    Integer getConnectionPoolIdleTimeout();
    void setConnectionPoolIdleTimeout(Integer value);

    @Description("Connection Pool Max Lifetime")
    Integer getConnectionPoolMaxLifetime();
    void setConnectionPoolMaxLifetime(Integer value);

    @Description("Jdbc DataBase Name")
    String getDataBaseName();
    void setDataBaseName(String value);


}
