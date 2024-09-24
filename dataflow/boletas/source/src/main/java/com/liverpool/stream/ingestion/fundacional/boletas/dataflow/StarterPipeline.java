/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liverpool.stream.ingestion.fundacional.boletas.dataflow;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.stream.ingestion.fundacional.boletas.dataflow.model.RegistraBoleta;





/**
 * A starter example for writing Beam programs.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 *   --project=<YOUR_PROJECT_ID>
 *   --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 *   --runner=DataflowRunner
 */
public class StarterPipeline {
  private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);

  public static void main(String[] args) {
    /*Pipeline p = Pipeline.create(
        PipelineOptionsFactory.fromArgs(args).withValidation().create());

    p.apply(Create.of("Hello", "World"))
    .apply(MapElements.via(new SimpleFunction<String, String>() {
      @Override
      public String apply(String input) {
        return input.toUpperCase();
      }
    }))
    .apply(ParDo.of(new DoFn<String, Void>() {
      @ProcessElement
      public void processElement(ProcessContext c)  {
        LOG.info(c.element());
      }
    }));

    p.run();*/
	  //json cdp v2.5.2 full
	  //String myjson = "{\"boleta_cabeceras\": {\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"id_tipo_transaccion\": 1,\"id_canal_de_venta\": 1,\"id_venta_catalogo_extendido_y_otro\": 1,\"id_vendedor\": 1,\"id_tienda_original\": 1,\"id_tipo_de_evento\": 1,\"id_cliente\": 1,\"id_terminal_pos_cancelacion\": 1,\"cuenta_empleado\": 12356,\"codigo_facturacion\": \"SHCP01\",\"referencia_folio_agencia_de_viajes\": \"AGFTYU\",\"numero_evento\": \"ABCD\",\"numero_boleta_cancelacion\": \"TYOUG7\",\"monto_boleta\": 0.0,\"total_cancelacion\": 0.0,\"id_numero_centro_de_servicio\": 1,\"numero_paqueteria\": \"1223365\",\"leyenda_facturacion\": \"SIN\",\"codigo_de_barras\": \"UVXYZ\",\"fecha_nacimiento_garantia_extendida\": \"2020-01-01\",\"fecha_garantia_extemporanea\": \"2020-01-01\",\"numero_indicador_marketplace\": \"123999\",\"leyenda_tentativa\": \"NOLEY\",\"id_tipo_de_descuento_al_total\": 1,\"clave_homologada\": \"UVWPQR\",\"id_direccion_fiscal\": 1,\"id_atg\": \"100\",\"id_mdm\": \"100\",\"id_tienda_origen_gcp\": \"100\",\"id_terminal_pos_gcp\": \"100\",\"id_vendedor_original\": 1},\"boleta_detalles_sku\": [{\"id_linea_detalle\": 1,\"id_terminal_pos_boleta_cabeceras\": \"001\",\"numero_boleta_boleta_cabeceras\": \"1000\",\"fecha_fin_transaccion_boleta_cabeceras\": \"2020-03-26\",\"hora_fin_transaccion_boleta_cabeceras\": \"11:04:01\",\"id_tienda_origen_boleta_cabeceras\": \"INSUR\",\"id_tienda_reconocimiento_boleta_cabeceras\": \"INSUR\",\"id_sku\": 1,\"id_pep_r3\": \"ABCD\",\"telefono_cliente_garantia_extendida\": \"5556668899\",\"numero_orden_optica\": \"ABCD\",\"orden_de_reparacion\": \"EFGH\",\"numero_telefono_o_folio\": \"56897412\",\"numero_comanda_restaurant\": \"REST\",\"numero_poliza\": \"POL001\",\"cantidad_articulos\": 1,\"precio_subtotal_neto\": 0.0,\"id_motivo_devolucion\": 1,\"id_departamento\": 1,\"precio_unitario_del_sku\": 0.0,\"fisico_o_virtual\": \"F\",\"es_marca_liverpool\": \"S\",\"boleta_detalles_sku_descuentos\": [{\"id_linea_detalle_boleta_detalles_sku\": 1,\"id_terminal_pos_boleta_detalles_sku\": \"001\",\"numero_boleta_boleta_detalles_sku\": \"1000\",\"fecha_fin_transaccion_boleta_detalles_sku\": \"2020-03-26\",\"hora_fin_transaccion_boleta_detalles_sku\": \"11:04:01\",\"id_tienda_origen_boleta_detalles_sku\": \"INSUR\",\"id_tienda_reconocimiento_boleta_detalles_sku\": \"INSUR\",\"id_descuento\": 1,\"total_descuento_sku\": 0.0,\"porcentaje_descuento_sku\": 0.0}]}],\"boleta_detalle_pagos\": [{\"id_linea_detalle_pagos\": 1,\"id_terminal_pos_boleta_cabeceras\": \"001\",\"numero_boleta_boleta_cabeceras\": \"1000\",\"fecha_fin_transaccion_boleta_cabeceras\": \"2020-03-26\",\"hora_fin_transaccion_boleta_cabeceras\": \"11:04:01\",\"id_tienda_origen_boleta_cabeceras\": \"INSUR\",\"id_tienda_reconocimiento_boleta_cabeceras\": \"INSUR\",\"id_medios_de_pago\": 1,\"monto_monedero\": 0.0,\"id_cliente_tarjeta\": 1,\"numero_autorizacion\": \"AGDFT\",\"numero_orden_soms\": \"TRDFHK\",\"total_pagado_otromedio_o_cambio_en_efectivo\": 0.0,\"plan_de_credito\": \"24MESES\",\"total_pagado_efectivo\": 0.0,\"hash_tarjeta_externa\": \"4587-1020\",\"numero_monedero\": \"LIV-001\",\"monto_redimido_monedero\": 0.0,\"saldo_anterior_monedero\": 0.0,\"monto_obtenido_monedero\": 0.0,\"saldo_actual_monedero\": 0.0,\"numero_de_cupon\": \"1-A\",\"id_modo_ingreso\": 1}],\"boleta_detalle_abonos_segmento\": [{\"id_linea_detalle_abonos_segmento\": 1,\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"id_segmento\": 1,\"importe_abonado_al_segmento\": 0.0}],\"boleta_detalles_monedero\": [{\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"numero_monedero\": \"MON-01\",\"saldo_anterior_monedero\": 0.0,\"monto_monedero\": 0.0,\"monto_obtenido_monedero\": 0.0,\"saldo_actual_monedero\": 0.0}],\"boleta_detalle_cupones\": [{\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"codigo_cupon\": \"CUP-01\",\"es_redimido\": \"R\"}],\"boleta_detalle_tarjetas_prepago\": [{\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"codigo_upc\": \"UPC-01\",\"importe_tarjeta_prepago\": 0.0}]}";
	  //nofk
	  //String myjson = "{\"boleta_cabeceras\": {\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"id_tipo_transaccion\": 1,\"cuenta_empleado\": 12356,\"codigo_facturacion\": \"SHCP01\",\"referencia_folio_agencia_de_viajes\": \"AGFTYU\",\"numero_evento\": \"ABCD\",\"numero_boleta_cancelacion\": \"TYOUG7\",\"monto_boleta\": 0.0,\"total_cancelacion\": 0.0,\"numero_paqueteria\": \"1223365\",\"leyenda_facturacion\": \"SIN\",\"codigo_de_barras\": \"UVXYZ\",\"fecha_nacimiento_garantia_extendida\": \"2020-01-01\",\"fecha_garantia_extemporanea\": \"2020-01-01\",\"numero_indicador_marketplace\": \"123999\",\"leyenda_tentativa\": \"NOLEY\",\"clave_homologada\": \"UVWPQR\",\"id_atg\": \"100\",\"id_mdm\": \"100\"},\"boleta_detalles_sku\": [{\"id_linea_detalle\": 1,\"id_terminal_pos_boleta_cabeceras\": \"001\",\"numero_boleta_boleta_cabeceras\": \"1000\",\"fecha_fin_transaccion_boleta_cabeceras\": \"2020-03-26\",\"hora_fin_transaccion_boleta_cabeceras\": \"11:04:01\",\"id_tienda_origen_boleta_cabeceras\": \"INSUR\",\"id_tienda_reconocimiento_boleta_cabeceras\": \"INSUR\",\"id_sku\": 1,\"id_pep_r3\": \"ABCD\",\"telefono_cliente_garantia_extendida\": \"5556668899\",\"numero_orden_optica\": \"ABCD\",\"orden_de_reparacion\": \"EFGH\",\"numero_telefono_o_folio\": \"56897412\",\"numero_comanda_restaurant\": \"REST\",\"numero_poliza\": \"POL001\",\"cantidad_articulos\": 1,\"precio_subtotal_neto\": 0.0,\"id_departamento\": 1,\"precio_unitario_del_sku\": 0.0,\"fisico_o_virtual\": \"F\",\"es_marca_liverpool\": \"S\",\"boleta_detalles_sku_descuentos\": [{\"id_linea_detalle_boleta_detalles_sku\": 1,\"id_terminal_pos_boleta_detalles_sku\": \"001\",\"numero_boleta_boleta_detalles_sku\": \"1000\",\"fecha_fin_transaccion_boleta_detalles_sku\": \"2020-03-26\",\"hora_fin_transaccion_boleta_detalles_sku\": \"11:04:01\",\"id_tienda_origen_boleta_detalles_sku\": \"INSUR\",\"id_tienda_reconocimiento_boleta_detalles_sku\": \"INSUR\",\"id_descuento\": 1,\"total_descuento_sku\": 0.0,\"porcentaje_descuento_sku\": 0.0}]}],\"boleta_detalle_pagos\": [{\"id_linea_detalle_pagos\": 1,\"id_terminal_pos_boleta_cabeceras\": \"001\",\"numero_boleta_boleta_cabeceras\": \"1000\",\"fecha_fin_transaccion_boleta_cabeceras\": \"2020-03-26\",\"hora_fin_transaccion_boleta_cabeceras\": \"11:04:01\",\"id_tienda_origen_boleta_cabeceras\": \"INSUR\",\"id_tienda_reconocimiento_boleta_cabeceras\": \"INSUR\",\"id_medios_de_pago\": 10,\"monto_monedero\": 0.0,\"numero_autorizacion\": \"AGDFT\",\"numero_orden_soms\": \"TRDFHK\",\"total_pagado_otromedio_o_cambio_en_efectivo\": 0.0,\"plan_de_credito\": \"24MESES\",\"total_pagado_efectivo\": 0.0,\"hash_tarjeta_externa\": \"4587-1020\",\"numero_monedero\": \"LIV-001\",\"monto_redimido_monedero\": 0.0,\"saldo_anterior_monedero\": 0.0,\"monto_obtenido_monedero\": 0.0,\"saldo_actual_monedero\": 0.0,\"numero_de_cupon\": \"1-A\"}],\"boleta_detalle_abonos_segmento\": [{\"id_linea_detalle_abonos_segmento\": 1,\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"id_segmento\": 1,\"importe_abonado_al_segmento\": 0.0}],\"boleta_detalles_monedero\": [{\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"numero_monedero\": \"MON-01\",\"saldo_anterior_monedero\": 0.0,\"monto_monedero\": 0.0,\"monto_obtenido_monedero\": 0.0,\"saldo_actual_monedero\": 0.0}],\"boleta_detalle_cupones\": [{\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"codigo_cupon\": \"CUP-01\",\"es_redimido\": \"R\"}],\"boleta_detalle_tarjetas_prepago\": [{\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"codigo_upc\": \"UPC-01\",\"importe_tarjeta_prepago\": 0.0}]}";
	  String myjson = "{\"boleta_cabeceras\": {\"id_terminal_pos\": \"001\",\"numero_boleta\": \"1000\",\"fecha_fin_transaccion\": \"2020-03-26\",\"hora_fin_transaccion\": \"11:04:01\",\"id_tienda_origen\": \"INSUR\",\"id_tienda_reconocimiento\": \"INSUR\",\"id_tipo_transaccion\": 1,\"cuenta_empleado\": 12356,\"codigo_facturacion\": \"SHCP01\",\"referencia_folio_agencia_de_viajes\": \"AGFTYU\",\"numero_evento\": \"ABCD\",\"numero_boleta_cancelacion\": \"TYOUG7\",\"monto_boleta\": 0.0,\"total_cancelacion\": 0.0,\"numero_paqueteria\": \"1223365\",\"leyenda_facturacion\": \"SIN\",\"codigo_de_barras\": \"UVXYZ\",\"fecha_nacimiento_garantia_extendida\": \"2020-01-01\",\"fecha_garantia_extemporanea\": \"2020-01-01\",\"numero_indicador_marketplace\": \"123999\",\"leyenda_tentativa\": \"NOLEY\",\"clave_homologada\": \"UVWPQR\",\"id_atg\": \"100\",\"id_mdm\": \"100\"},\"boleta_detalles_sku\": [{\"id_linea_detalle\": 1,\"id_terminal_pos_boleta_cabeceras\": \"001\",\"numero_boleta_boleta_cabeceras\": \"1000\",\"fecha_fin_transaccion_boleta_cabeceras\": \"2020-03-26\",\"hora_fin_transaccion_boleta_cabeceras\": \"11:04:01\",\"id_tienda_origen_boleta_cabeceras\": \"INSUR\",\"id_tienda_reconocimiento_boleta_cabeceras\": \"INSUR\",\"id_sku\": 1,\"id_pep_r3\": \"ABCD\",\"telefono_cliente_garantia_extendida\": \"5556668899\",\"numero_orden_optica\": \"ABCD\",\"orden_de_reparacion\": \"EFGH\",\"numero_telefono_o_folio\": \"56897412\",\"numero_comanda_restaurant\": \"REST\",\"numero_poliza\": \"POL001\",\"cantidad_articulos\": 1,\"precio_subtotal_neto\": 0.0,\"id_departamento\": 1,\"precio_unitario_del_sku\": 0.0,\"fisico_o_virtual\": \"F\",\"es_marca_liverpool\": \"S\",\"boleta_detalles_sku_descuentos\": [{\"id_linea_detalle_boleta_detalles_sku\": 1,\"id_terminal_pos_boleta_detalles_sku\": \"001\",\"numero_boleta_boleta_detalles_sku\": \"1000\",\"fecha_fin_transaccion_boleta_detalles_sku\": \"2020-03-26\",\"hora_fin_transaccion_boleta_detalles_sku\": \"11:04:01\",\"id_tienda_origen_boleta_detalles_sku\": \"INSUR\",\"id_tienda_reconocimiento_boleta_detalles_sku\": \"INSUR\",\"id_descuento\": 1,\"total_descuento_sku\": 0.0,\"porcentaje_descuento_sku\": 0.0}]}],\"boleta_detalle_pagos\": [{\"id_linea_detalle_pagos\": 1,\"id_terminal_pos_boleta_cabeceras\": \"001\",\"numero_boleta_boleta_cabeceras\": \"1000\",\"fecha_fin_transaccion_boleta_cabeceras\": \"2020-03-26\",\"hora_fin_transaccion_boleta_cabeceras\": \"11:04:01\",\"id_tienda_origen_boleta_cabeceras\": \"INSUR\",\"id_tienda_reconocimiento_boleta_cabeceras\": \"INSUR\",\"id_medios_de_pago\": 10,\"monto_monedero\": 0.0,\"numero_autorizacion\": \"AGDFT\",\"numero_orden_soms\": \"TRDFHK\",\"total_pagado_otromedio_o_cambio_en_efectivo\": 0.0,\"plan_de_credito\": \"24MESES\",\"total_pagado_efectivo\": 0.0,\"hash_tarjeta_externa\": \"4587-1020\",\"numero_monedero\": \"LIV-001\",\"monto_redimido_monedero\": 0.0,\"saldo_anterior_monedero\": 0.0,\"monto_obtenido_monedero\": 0.0,\"saldo_actual_monedero\": 0.0,\"numero_de_cupon\": \"1-A\"}]}";
	  	ObjectMapper mapper = new ObjectMapper();
		

		
		 try {
				/*String networkTags = "cloud-sql";
				List<String> tagNetworkCloudSql = Arrays.asList(networkTags.split(","));
				
				System.out.println(tagNetworkCloudSql.toString());
				*/
			  
				RegistraBoleta registraBoleta = mapper.readValue(myjson, RegistraBoleta.class);
				System.out.println(registraBoleta.toString());
				
				//String x = mapper.writeValueAsString(registraBoleta);
				//System.out.println(x);
				
		 } catch (Exception ex) {
	            ex.printStackTrace();
	     } 
  	}
}
