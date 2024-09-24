package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import io.netty.util.internal.StringUtil;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.values.KV;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstruirJsonHashPubSub extends DoFn<KV<String, CoGbkResult>, String> {
    private static final Logger LOG = LoggerFactory.getLogger(ConstruirJsonHashPubSub.class);
    private static final long serialVersionUID = 1L;


    @ProcessElement
    public void processElement(ProcessContext c) {

        LOG.info("Entra a construir JsonHash PubSub:  " + c.element().getKey());
        if( c.element().getKey().contains(",")){
            String[] data = c.element().getKey().split(",");
            if(  data.length == 5 && data[4] !=null && !data[4].equals("N") && !StringUtil.isNullOrEmpty( data[3])){
                JSONObject jo = new JSONObject();
                jo.put("hash", data[3]);
                jo.put("id", data[1]);
                jo.put("idCliente", data[2]);
                LOG.info("JSON: " + jo);
                c.output(jo.toString());
            }
        }

    }
}