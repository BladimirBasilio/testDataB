package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.utils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;


public class ConnectionPoolProvider{

    private String JdbcUrl;
    private String PasswordJdbc;
    private String UsuarioJdbc;
    private String InstanceNameSM;
    private String applicationName;
    private Integer connectionPoolMaxSize;
    private Integer connectionPoolMinIdle;
    private Integer connectionPoolTimeout;
    private Integer connectionPoolIdleTimeout;
    private Integer connectionPoolMaxLifetime;
    private String poolName;


    DataSource dataSource;
    private static ConnectionPoolProvider instance = null;
    private final static Object lock = new Object();

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPoolProvider.class);

    public ConnectionPoolProvider(String JdbcUrl,
                                  String PasswordJdbc,
                                  String UsuarioJdbc,
                                  String InstanceNameSM,
                                  String applicationName,
                                  Integer connectionPoolMaxSize,
                                  Integer connectionPoolMinIdle,
                                  Integer connectionPoolTimeout,
                                  Integer connectionPoolIdleTimeout,
                                  Integer connectionPoolMaxLifetime,
                                  String poolName){

        this.JdbcUrl = JdbcUrl;
        this.PasswordJdbc = PasswordJdbc;
        this.UsuarioJdbc = UsuarioJdbc;
        this.InstanceNameSM = InstanceNameSM;
        this.applicationName = applicationName;
        this.connectionPoolMaxSize = connectionPoolMaxSize;
        this.connectionPoolMinIdle = connectionPoolMinIdle;
        this.connectionPoolTimeout = connectionPoolTimeout;
        this.connectionPoolIdleTimeout = connectionPoolIdleTimeout;
        this.connectionPoolMaxLifetime = connectionPoolMaxLifetime;
        this.poolName = poolName;
        this.dataSource = getHikariDataSource();


    }
//
//    private ConnectionPoolProvider(){
//        this.dataSource = getHikariDataSource();
//    }


    public static ConnectionPoolProvider getInstance(String JdbcUrl,
                                                     String PasswordJdbc,
                                                     String UsuarioJdbc,
                                                     String InstanceNameSM,
                                                     String applicationName,
                                                     Integer connectionPoolMaxSize,
                                                     Integer connectionPoolMinIdle,
                                                     Integer connectionPoolTimeout,
                                                     Integer connectionPoolIdleTimeout,
                                                     Integer connectionPoolMaxLifetime,
                                                     String poolName) {

        if (instance == null) {

            LOG.info("Creando instancia del pool de conexiones");
            synchronized (lock) {
                if (instance == null) {
                    LOG.info("Instancia vacia, recien creando conexion pool");
                    try {
                        instance = new ConnectionPoolProvider(JdbcUrl, PasswordJdbc, UsuarioJdbc, InstanceNameSM, applicationName,
                                connectionPoolMaxSize, connectionPoolMinIdle, connectionPoolTimeout, connectionPoolIdleTimeout,
                                connectionPoolMaxLifetime, poolName);
                    }catch (Exception e){
                        LOG.info("No fue posible conectar en la inicialización: " + e );

                    }
                }
            }
    }
            else{
            LOG.info("Conexion activa existente - Reutilizada");
        }

        return instance;
}


    private DataSource getHikariDataSource()  {


        LOG.info("iniciando datasource instance..");

        String url = this.JdbcUrl + "?cloudSqlInstance=" + this.InstanceNameSM + "&socketFactory=com.google.cloud.sql.postgres.SocketFactory&useSSL=false";
        DataSourceConnectionFactory connectionFactory;
        final BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(this.UsuarioJdbc);
        basicDataSource.setPassword(this.PasswordJdbc);
        connectionFactory = new DataSourceConnectionFactory(basicDataSource);
        final PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        final GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(1);
        poolConfig.setMinIdle(0);
        poolConfig.setMinEvictableIdleTimeMillis(10000);
        poolConfig.setSoftMinEvictableIdleTimeMillis(30000);
        final GenericObjectPool connectionPool = new GenericObjectPool(poolableConnectionFactory, poolConfig);
        poolableConnectionFactory.setPool(connectionPool);
        poolableConnectionFactory.setDefaultAutoCommit(true);
        poolableConnectionFactory.setDefaultReadOnly(false);

        LOG.info("Finalizando Datasource intance.");
        return new PoolingDataSource(connectionPool);

    }

    public DataSource getDataSource() {
        return dataSource;
    }

}