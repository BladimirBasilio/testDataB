package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.option;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils.ConnectionPoolProvider;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.ValueProvider;

public interface RegistraPedidoOptions extends DataflowPipelineOptions  {

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

    @Description("Consulta Insert Pedidos")
    String getConsultaInsertPedidos();
    void setConsultaInsertPedidos(String value);	 	

    @Description("Consulta Insert Pedidos Detalle Sku")
    String getConsultaInsertPedidosDetalleSku();
    void setConsultaInsertPedidosDetalleSku(String value);

    @Description("Consulta Insert Cliente ID")
    String getConsultaInsertClienteId();
    void setConsultaInsertClienteId(String value);

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

    @Description("MDM Url")
    String getMdmUrl();
    void setMdmUrl(String value);

    @Description("MDM Services TimeOut")
    Integer getMdmServicesTimeOut();
    void setMdmServicesTimeOut(Integer value);

    @Description("MYM services endpoint")
    String getMdmServicesEndpoint();
    void setMdmServicesEndpoint(String value);

    @Description("Redis Host")
    String getRedisHost();
    void setRedisHost(String value);

    @Description("Redis Port")
    Integer getRedisPort();
    void setRedisPort(Integer value);

    @Description("Redis Password")
    String getRedisPassword();
    void setRedisPassword(String value);

    @Description("Redis Expire Key")
    Integer getRedisExpireKey();
    void setRedisExpireKey(Integer value);

    @Description("Redis Timeout")
    Integer getRedisTimeout();
    void setRedisTimeout(Integer value);

    @Description("Jedis Pool Max Idle")
    Integer getJedisPoolMaxIdle();
    void setJedisPoolMaxIdle(Integer value);

    @Description("Jedis Pool Min Idle")
    Integer getJedisPoolMinIdle();
    void setJedisPoolMinIdle(Integer value);

    @Description("Jedis Pool Max Active")
    Integer getJedisPoolMaxActive();
    void setJedisPoolMaxActive(Integer value);

    @Description("Jedis Pool Max Wait")
    Integer getJedisPoolMaxWait();
    void setJedisPoolMaxWait(Integer value);
    @Description("Pub/Sub vinculación topic")
    String getVinculacionTopic();
    void setVinculacionTopic(String value);

    @Description("PubSub vinculacion projectId")
    String getVinculacionProjectId();
    void setVinculacionProjectId(String value);

}
