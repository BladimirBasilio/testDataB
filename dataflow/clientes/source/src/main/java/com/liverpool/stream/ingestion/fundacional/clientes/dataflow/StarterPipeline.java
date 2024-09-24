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
package com.liverpool.stream.ingestion.fundacional.clientes.dataflow;

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
import com.liverpool.stream.ingestion.fundacional.clientes.dataflow.model.*;



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
	  
	  String myjson = "{\r\n" + 
	  		"	\"cliente\": {\r\n" + 
	  		"		\"id_cliente\": 0,\r\n" + 
	  		"		\"primer_nombre\": \"NICOLAS\",\r\n" + 
	  		"		\"segundo_nombre\": \"Ejemplo\",\r\n" + 
	  		"		\"apellido_paterno\": \"CAUICH\",\r\n" + 
	  		"		\"apellido_materno\": \"SANTANA\",\r\n" + 
	  		"		\"fecha_de_nacimiento\": \"1995-02-02\",\r\n" + 
	  		"		\"genero\": \"M\",\r\n" + 
	  		"		\"rfc\": \"NICS19950202\",\r\n" + 
	  		"		\"fecha_de_registro\": \"2020-02-23\",\r\n" + 
	  		"		\"telefono_de_contacto\": [\r\n" + 
	  		"			{\r\n" + 
	  		"				\"id_cliente_telefonos_de_contacto\": 0,\r\n" + 
	  		"				\"id_cliente\": 0,\r\n" + 
	  		"				\"id_sistema_origen\": 1,\r\n" + 
	  		"				\"telefono\": \"5552683000\",\r\n" + 
	  		"				\"alias_telefono\": \"celular\"\r\n" + 
	  		"			},\r\n" + 
	  		"			{\r\n" + 
	  		"				\"id_cliente_telefonos_de_contacto\": 0,\r\n" + 
	  		"				\"id_cliente\": 0,\r\n" + 
	  		"				\"id_sistema_origen\": 1,\r\n" + 
	  		"				\"telefono\": \"9992713828\",\r\n" + 
	  		"				\"alias_telefono\": \"casa\"\r\n" + 
	  		"			}\r\n" + 
	  		"		],\r\n" + 
	  		"		\"email_de_contacto\": [{\r\n" + 
	  		"			\"id_cliente_emails_de_contacto\": 0,\r\n" + 
	  		"			\"id_cliente\": 0,\r\n" + 
	  		"			\"id_sistema_origen\": 1,\r\n" + 
	  		"			\"tipo\": \"cuenta\",			\r\n" + 
	  		"			\"email\": \"COMUNICANICOLAS@HOTMAIL.COM\"	\r\n" + 
	  		"		},\r\n" + 
	  		"		{\r\n" + 
	  		"			\"id_cliente_emails_de_contacto\": 0,\r\n" + 
	  		"			\"id_cliente\": 0,\r\n" + 
	  		"			\"id_sistema_origen\": 1,\r\n" + 
	  		"			\"tipo\": \"notificacion\",			\r\n" + 
	  		"			\"email\": \"COMUNICANICOLAS@HOTMAIL.COM\"		\r\n" + 
	  		"		}\r\n" + 
	  		"		],\r\n" + 
	  		"		\"direccion\": {\r\n" + 
	  		"			\"id_direccion_del_cliente\": 0,\r\n" + 
	  		"			\"id_cliente\": 0,\r\n" + 
	  		"			\"esta_borrado\": \"N\",\r\n" + 
	  		"			\"alias_de_direccion\": \"casa\",\r\n" + 
	  		"			\"edificio\": \"X5\",\r\n" + 
	  		"			\"id_colonia\": 1,\r\n" + 
	  		"			\"id_municipio\": 0,\r\n" + 
	  		"			\"id_estado_del_pais\": 0,\r\n" + 
	  		"			\"calle\": \"cerrada 221\",\r\n" + 
	  		"			\"numero_interior\": \"221\",\r\n" + 
	  		"			\"codigo_postal\": \"97130\",\r\n" + 
	  		"			\"numero_exterior\": \"22\",\r\n" + 
	  		"			\"id_tipo_direccion_cliente\": 0\r\n" + 
	  		"		},\r\n" + 
	  		"		\"cliente_formas_de_pago\": {\r\n" + 
	  		"			\"id_cliente_tarjeta\": 0,\r\n" + 
	  		"			\"tipo_tarjeta\": \"bana\",\r\n" + 
	  		"			\"esta_activa\": 1,\r\n" + 
	  		"			\"hash1_tarjeta\": \"RRDD-ERRR-SDSA\",\r\n" + 
	  		"			\"id_cliente\": 0,\r\n" + 
	  		"			\"id_banco_emisor_tarjeta\": 0\r\n" + 
	  		"		}\r\n" + 
	  		"	},\r\n" + 
	  		"	\"cliente_atg\": \r\n" + 
	  		"		{\r\n" + 
	  		"			\"id_cliente_atg\": 0,\r\n" + 
	  		"			\"id_atg\": \"01ATG\",\r\n" + 
	  		"			\"sistema_fuente_atg\": \"ATG\",\r\n" + 
	  		"			\"id_cliente\": 0\r\n" + 
	  		"		}\r\n" + 
	  		"}";
	  
	  
	  	ObjectMapper mapper = new ObjectMapper();
		

		
		 try {
							        	
				RegistraCliente registraCliente = mapper.readValue(myjson, RegistraCliente.class);
				System.out.println(registraCliente.toString());
				System.out.println(registraCliente.getCliente().toString());

				
				
		 } catch (Exception ex) {
	            ex.printStackTrace();
	     } 
  	}
}
