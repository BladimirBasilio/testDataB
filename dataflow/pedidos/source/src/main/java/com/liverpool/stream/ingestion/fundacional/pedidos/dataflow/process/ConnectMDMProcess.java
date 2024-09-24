package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.process;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.services.CdpServices;
import org.apache.beam.sdk.transforms.DoFn;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.Pedido;
import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.RegistraPedido;

import java.io.IOException;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

public class ConnectMDMProcess extends DoFn<RegistraPedido, RegistraPedido> {

    private static final long serialVersionUID = 2638997564899862722L;
    private static final Logger LOG = LoggerFactory.getLogger(ConnectMDMProcess.class);
    private String mdmUrl;
    private String mdmServiceEnpoint;
    private Integer mdmServiceTimeOut;

    private CdpServices cdpServices = CdpServices.getInstance();

    private AsyncHttpClient _HttpClientAsync;

    @Setup
    public void init() {
        AsyncHttpClientConfig cg = config()
                .setKeepAlive(true)
                .setDisableHttpsEndpointIdentificationAlgorithm(true)
                .setUseInsecureTrustManager(true)
                .setReadTimeout(mdmServiceTimeOut)
                .setConnectTimeout(mdmServiceTimeOut)
                .build();

        _HttpClientAsync=asyncHttpClient(cg);

    }

    public ConnectMDMProcess(String mdmUrl, String mdmServiceEnpoint, Integer mdmServiceTimeOut){
        LOG.info("Inicio Conectar MDM Pedido");
        this.mdmUrl = mdmUrl;
        this.mdmServiceEnpoint = mdmServiceEnpoint;
        this.mdmServiceTimeOut = mdmServiceTimeOut;
    };

    @ProcessElement
    public void processElement(DoFn<RegistraPedido, RegistraPedido>.ProcessContext c) throws Exception {
        LOG.info("Conectar MDM desde Pedido ");
        RegistraPedido registraPedido = c.element();
        Pedido pedido = registraPedido.getPedido();

        try {

            LOG.info("Id_cliente_remitente " + pedido.getId_cliente_remitente() +
                    " Clave_homologada " + pedido.getClave_homologada() +
                    " mdmUrl " + mdmUrl +
                    " mdmServiceEnpoint " + mdmServiceEnpoint +
                    " Numero_del_documento " + pedido.getNumero_del_documento());

            if (pedido.getClave_homologada() == null){
                LOG.error("Error, valor de id Clave homologada null ");
                return;
            }

            /*if (pedido.getId_cliente_destinatario() == null){
                LOG.error("Error, valor de id cliente destinatario null ");
                return;
            }*/

            if (pedido.getId_cliente_remitente() == null){
                LOG.error("Error, valor de id cliente remitente null ");
                return;
            }

            if (mdmUrl == null){
                LOG.error("Error, valor de mdmUrl null ");
                return;
            }

            if (mdmServiceEnpoint == null){
                LOG.error("Error, valor de mdmServiceEnpoint null ");
                return;
            }

            /** Build URL */
            StringBuilder url = new StringBuilder();
            url.append(mdmUrl);
            url.append(mdmServiceEnpoint);
            url.append(pedido.getId_cliente_remitente().toString());
            url.append("/");
            String sistemaOrigen = pedido.getClave_homologada();

            if (sistemaOrigen == null || sistemaOrigen.isEmpty())
                sistemaOrigen = "SOMS";

            url.append(sistemaOrigen);

            String numeroDocumento = pedido.getNumero_del_documento();
            if (numeroDocumento != null && !numeroDocumento.isEmpty()){
                url.append("/");
                url.append(numeroDocumento);
            }

            LOG.info("Pedidourlmdm " + url);

            _HttpClientAsync.preparePut(url.toString())
                    .setHeader("Content-Type","application/json")
                    .execute();

        }catch (Exception e) {
            LOG.error("Error al Conectar MDM desde Pedido" + e.getMessage());
            throw new Exception(e);
        }finally {
            c.output(registraPedido);
        }
    }

}