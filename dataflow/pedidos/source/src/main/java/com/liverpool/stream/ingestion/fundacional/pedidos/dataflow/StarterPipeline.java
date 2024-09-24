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
package com.liverpool.stream.ingestion.fundacional.pedidos.dataflow;

import java.util.Arrays;
import java.util.List;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liverpool.stream.ingestion.fundacional.pedidos.dataflow.model.*;



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
	  
	  String myjson = "{\"pedido\": {\"numero_del_documento\": \"T-800\",\"estado_orden\": \"ES\",\"fecha_modificacion\": \"2021-03-02\",\"hora_modificacion\": \"12:00:00\",\"estado_renglon_bitacora\": \"\",\"id_tipo_entrega\": 107,\"id_tipo_de_venta\": 109,\"id_tipo_de_act\": 119,\"id_tipo_de_documento\": 60,\"fecha_de_compra\": \"2021-03-02\",\"operacion_crud\": \"INSERT\"},\"clienteRemitente\": {\"primer_nombre\": \"Maichael\",\"segundo_nombre\": \"Doe\",\"apellido_paterno\": \"Jordan\",\"apellido_materno\": \"Noone\",\"clave_homologada\": \"SOMS1R\",\"telefono_de_contacto\": [{\"telefono\": \"4415529\",\"alias_telefono\": \"fijo\",\"operacion_crud\": \"INSERT\"},{\"telefono\": \"6639969\",\"alias_telefono\": \"oficina\",\"operacion_crud\": \"INSERT\"},{\"telefono\": \"9986653329\",\"alias_telefono\": \"celular\",\"operacion_crud\": \"INSERT\"}],\"email_de_contacto\": {\"email\": \"johndoe@gmail.com9\",\"operacion_crud\": \"INSERT\"},\"operacion_crud\": \"INSERT\",\"cliente_id\": {\"id_cliente_ids\": 0,\"id_cliente\": 0,\"id_sistema_origen\": 1,\"operacion_crud\": \"INSERT\"}},\"clienteDestinatario\": {\"primer_nombre\": \"Lebron\",\"segundo_nombre\": \"Doe\",\"apellido_paterno\": \"James\",\"apellido_materno\": \"Nobody\",\"clave_homologada\": \"SOMS1D\",\"telefono_de_contacto\": [{\"telefono\": \"441552199\",\"alias_telefono\": \"fijo\",\"operacion_crud\": \"INSERT\"},{\"telefono\": \"663996299\",\"alias_telefono\": \"oficina\",\"operacion_crud\": \"INSERT\"},{\"telefono\": \"998665332199\",\"alias_telefono\": \"celular\",\"operacion_crud\": \"INSERT\"}],\"email_de_contacto\": {\"email\": \"johndoed@gmail.com9\",\"operacion_crud\": \"INSERT\"},\"direccion\": {\"calle\": \"Wall Street Of. 3029\",\"numero_exterior\": \"3029\",\"numero_interior\": \"1009\",\"codigo_postal\": \"0019\",\"operacion_crud\": \"INSERT\"},\"operacion_crud\": \"INSERT\",\"cliente_id\": {\"id_sistema_origen\": 1,\"operacion_crud\": \"INSERT\"}},\"pedido_detalle_sku\": [{\"numero_del_documento_pedidos\": \"T-800\",\"estado_orden_pedidos\": \"ES\",\"fecha_modificacion_pedidos\": \"2021-03-02\",\"hora_modificacion_pedidos\": \"12:00:00\",\"fecha_actualizacion_estado\": \"2021-03-02\",\"piezas\": 2,\"id_linea_detalle\": 1,\"hora_actualizacion_estado\": \"12:00:00\",\"fecha_promesa_de_entrega_inicial\": \"2021-03-02\",\"fecha_promesa_de_entrega_final\": \"2021-03-03\",\"id_estado_por_linea\": 1,\"operacion_crud\": \"INSERT\"},{\"numero_del_documento_pedidos\": \"T-800\",\"estado_orden_pedidos\": \"ES\",\"fecha_modificacion_pedidos\": \"2021-03-02\",\"hora_modificacion_pedidos\": \"12:00:00\",\"fecha_actualizacion_estado\": \"2021-03-02\",\"piezas\": 25,\"id_linea_detalle\": 2,\"hora_actualizacion_estado\": \"12:00:00\",\"fecha_promesa_de_entrega_inicial\": \"2021-03-02\",\"fecha_promesa_de_entrega_final\": \"2021-03-03\",\"id_estado_por_linea\": 1,\"operacion_crud\": \"INSERT\"}]}";
	  
	  
	  	ObjectMapper mapper = new ObjectMapper();
		

		
		 try {
				/*String networkTags = "cloud-sql";
				List<String> tagNetworkCloudSql = Arrays.asList(networkTags.split(","));
				
				System.out.println(tagNetworkCloudSql.toString());
				*/
			  
				RegistraPedido registroPedido = mapper.readValue(myjson, RegistraPedido.class);
				System.out.println(registroPedido.toString());
				System.out.println(registroPedido.getPedido().toString());
				System.out.println(registroPedido.getClienteRemitente().toString());
				System.out.println(registroPedido.getClienteDestinatario().toString());
				System.out.println(registroPedido.getPedido_detalle_sku().toString());
				
		 } catch (Exception ex) {
	            ex.printStackTrace();
	     } 
  	}
}
