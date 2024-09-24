package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.option;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface RegistraClienteLOptions extends PipelineOptions {

 	
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
    
    
    @Description("Consulta Insert Cliente Destinatario")
    String getConsultaInsertClienteDestinatario();
    void setConsultaInsertClienteDestinatario(String value);

    @Description("Pub/Sub Nombre Topico Cliente Insertado")
    String getNombreTopicoClienteInsertado();
    void setNombreTopicoClienteInsertado(String value);

    @Description("Pub/Sub Nombre Topico Hash Insertado")
    String getNombreTopicoClienteHash();
    void setNombreTopicoClienteHash(String value);


    @Description("Pub/Sub Nombre Suscripcion Cliente Insertado")
    String getNombreSuscripcionClienteInsertado();
    void setNombreSuscripcionClienteInsertado(String value);
    
}
