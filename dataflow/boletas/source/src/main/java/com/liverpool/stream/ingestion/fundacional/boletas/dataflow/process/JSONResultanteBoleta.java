package com.liverpool.stream.ingestion.fundacional.boletas.dataflow.process;

import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;
import org.apache.beam.sdk.transforms.DoFn;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JSONResultanteBoleta extends DoFn<RegistraBoleta, String> {


    /**
     *
     */
    private static final long serialVersionUID = 4452377857922037430L;
    private static final Logger LOG = LoggerFactory.getLogger(JSONResultanteBoleta.class);


    public JSONResultanteBoleta(
    ) {
    }

    @ProcessElement
    public void processElement(DoFn<RegistraBoleta, String>.ProcessContext c) throws Exception {
        try {
            RegistraBoleta registraBoleta = c.element();

            LOG.info("Entré a construirJsonResultante");

            if (registraBoleta.getBoleta_cabeceras().getId_atg() != null) {
                JSONObject jo = new JSONObject();
                jo.put("codigoTienda", registraBoleta.getBoleta_cabeceras().getId_tienda_origen());
                jo.put("terminal", registraBoleta.getBoleta_cabeceras().getId_terminal_pos());
                jo.put("boleta", registraBoleta.getBoleta_cabeceras().getNumero_boleta());
                jo.put("fechaTransaccion", registraBoleta.getBoleta_cabeceras().getFecha_fin_transaccion());

                LOG.info("registro {}", jo);
                c.output(jo.toString());
            }

        } catch (Exception e) {
            LOG.error("Error al separar datos Cliente ATG:" + e.getMessage(), e);
        }


    }


    @Teardown
    public void tearDown() {

    }
}
