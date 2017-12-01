package org.baeldung.config;

import java.io.File;

import javax.sql.DataSource;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import datatypes.DatosJson;


@Configuration
public class PersistenceConfiguration {
   
@Autowired
private ConfigurableApplicationContext context;
String URL_Back="http://localhost:8180/ServidorTsi2-0.0.1-SNAPSHOT/";

@Bean
   @ConfigurationProperties(prefix="spring.datasource")
   public DataSource dataSource() {
   	/*ManejadorDeArchivos ma = new ManejadorDeArchivos();
    ma.levantarDatosArchivo();*/
	String name = context.getApplicationName().substring(1);
	if (name.toLowerCase().equals("mantel"))
	{
		DatosJson dj = new DatosJson();
    	dj.addParameter("empresa", name);
    	System.out.println("MI EMPRESA: "+name);
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/empresa/agregarEmpresa")
    	.request().post(Entity.json(dj));
	}
		
System.out.println("esta es la url de base" + "jdbc:postgresql://localhost:5432"+context.getApplicationName());
   	return DataSourceBuilder.create().url("jdbc:postgresql://localhost:5432"+context.getApplicationName()).build();
   }
}

