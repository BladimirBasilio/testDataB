package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.services.impl;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.services.IRedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.Serializable;
import java.util.Objects;

public class RedisCache implements IRedisCache, Serializable {
    private static RedisCache instance = null;

    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);
    private JedisPool jedisPool;

    private RedisCache(String host,int port,int timeout,String password,int maxActive,int maxIdle,int minIdle){
        super();
        log.info("host: " + host);
        log.info("port: " + port);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setJmxEnabled(false);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        //poolConfig.setMaxWait(Duration.ofMillis(maxWaitMillis));
        poolConfig.setBlockWhenExhausted(Boolean.TRUE);
        JedisPool jedisPool = null;
        if(!password.isEmpty()) {
            jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
        }else {
            jedisPool = new JedisPool(poolConfig, host, port, timeout);
        }
        log.info("Active in pool " + jedisPool.getNumActive());
        this.jedisPool=jedisPool;
    }

    public static RedisCache getInstance(String host,int port,int timeout,String password,int maxActive,int maxIdle,int minIdle){
        if(Objects.isNull(instance))
            instance=new RedisCache(host,port,timeout,password,maxActive,maxIdle,minIdle);
        return instance;
    }

    @Override
    public void add(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key, expire, value);
        } catch (JedisConnectionException ex) {
            log.error(String.valueOf(ex));
        } catch (Exception e) {
            log.warn("Error en metodo add de redis: " + e);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void update(String key, String value, int expire) {
        add( key,  value,  expire);
    }

    @Override
    public void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (JedisConnectionException ex) {
            log.error(String.valueOf(ex));
        } catch (Exception e) {
            log.warn("Error en metodo delete de redis: " + e);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if(key == null) {
                return null;
            }
            return jedis.get(key);
        } catch (JedisConnectionException ex) {
            log.error(String.valueOf(ex));
            return "-1";
        } catch (Exception e) {
            log.warn("Error en metodo get de redis:: " + e);
            return "-1";
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (JedisConnectionException ex) {
            log.error(String.valueOf(ex));
            return false;
        } catch (Exception e) {
            log.warn("Error en metodo exist de redis: " + e);
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
