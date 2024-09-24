package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.services;

public interface IRedisCache {
    public void add(String key, String value, int expire);

    public void update(String key, String value, int expire);

    public void delete(String key);

    public String get(String key);

    public Boolean exists(String key);
}
