package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.services;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import org.apache.http.conn.ssl.TrustStrategy;
import javax.net.ssl.SSLContext;


public class CdpServices implements Serializable {
    private static CdpServices instance = null;
    private static final Logger logger = LoggerFactory.getLogger("BigData-CdpServices");

    // Constructor privado, implementación de Singleton.
    private CdpServices(){

    }

    public static CdpServices getInstance(){
        if(Objects.isNull(instance))
            instance = new CdpServices();
        return instance;
    }

    RestTemplate restTemplate(int mdmServiceTimeOut) {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext;
        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();
            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            requestFactory.setConnectTimeout(mdmServiceTimeOut);
            requestFactory.setReadTimeout(mdmServiceTimeOut);
            return new RestTemplate(requestFactory);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return new RestTemplate();
    }

    public void callMyMNotifyMDM(Integer idCliente, String sistemaOrigen, String mdmUrl, String mdmServiceEnpoint, Integer mdmServiceTimeOut, String numeroDocumento) throws ResourceAccessException, RestClientException, Exception {
        try {
                /** Build URL */
                StringBuilder url = new StringBuilder();
                url.append(mdmUrl);
                url.append(mdmServiceEnpoint);
                url.append(idCliente.toString());
                url.append("/");

                if (sistemaOrigen == null || sistemaOrigen.isEmpty())
                    sistemaOrigen = "SOMS";

                url.append(sistemaOrigen);

                if (numeroDocumento != null && !numeroDocumento.isEmpty()){
                    url.append("/");
                    url.append(numeroDocumento);
                }

                logger.info("Pedidourlmdm " + url);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity(headers);
                //RestTemplate restTemplate = new RestTemplate();
                restTemplate(mdmServiceTimeOut).exchange(url.toString(), HttpMethod.PUT, request, String.class);

                /*WebClient.create().put().uri(new URI(url.toString()))
                    .header("Content-Type", String.valueOf(MediaType.APPLICATION_JSON))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(String.class).block();*/

        }catch (ResourceAccessException e){
            logger.warn("Se envió pedido a MDM, pero este no respondió a tiempo, " + e.getMessage());
            throw new ResourceAccessException(e.getMessage(),new IOException(e));
        } catch (RestClientException e) {
            logger.error("Error conectarMdmPedido.. "  + e.getMessage());
            throw new RestClientException(e.getMessage(),e);
        } catch (Exception e) {
             logger.error("Error conectarMdmPedido... " + e);
             throw new Exception(e);
        }
    }
}
