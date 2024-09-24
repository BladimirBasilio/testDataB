package com.liverpool.stream.ingestion.fundacional.clientes.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.option.RegistraClienteOptions;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TupleTag;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ConstruirJsonResultante extends DoFn<KV<String, CoGbkResult>, String> {
    private static final Logger LOG = LoggerFactory.getLogger(ConstruirJsonResultante.class);
    private static final long serialVersionUID = 1L;

    private RegistraClienteOptions options;

    @ProcessElement
    public void processElement(ProcessContext c) {

        try {

            String clientid= "";
            LOG.info("Entré a construirJsonResultante");

            if( c.element().getKey().contains(",")){
                clientid = c.element().getKey().split(",")[0];
            }else {
                clientid = c.element().getKey();
            }

            JSONObject jo = new JSONObject();
            jo.put("clienteID",clientid );
            jo.put("clienteTipo", getTipoCliente(c.element().getValue()));

            LOG.info("registro {}",jo.toString());
            c.output(jo.toString());

        }
        catch (Exception e) {
            LOG.error("Error al separar datos Cliente ATG:" + e.getMessage(), e);
        }
    }



    private String getTipoCliente(CoGbkResult values){

        List<TupleTag<?>> allTag = values.getSchema().getTupleTagList().getAll();
        String tipoClienteInsertado = "tipoCliente=ATG";
        String tipoClienteNoInsertado = "tipoCliente=MDM";

        AtomicReference<String> result = new AtomicReference<>("");
        for (TupleTag<?> current :allTag){
            Iterable<?> iterator = values.getAll(current);

            String allElement="";
            for(Object element :iterator){
                LOG.info("element {}", element);
                allElement=allElement+element;
            }

            if(allElement.contains(tipoClienteNoInsertado)){
                result.set("MDM");
                LOG.info("cliente_MDM");
            }else if (allElement.contains(tipoClienteInsertado)) {
                result.set("ATG");
                LOG.info("cliente_ATG");

            }


        }

        LOG.info("result {}",result.get());
        return result.get();
    }


}