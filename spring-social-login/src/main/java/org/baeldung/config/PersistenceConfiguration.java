package org.baeldung.config;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PersistenceConfiguration {
   
@Autowired
private ConfigurableApplicationContext context;


@Bean
   @ConfigurationProperties(prefix="spring.datasource")
   public DataSource dataSource() {
   	/*ManejadorDeArchivos ma = new ManejadorDeArchivos();
    ma.levantarDatosArchivo();*/
System.out.println("esta es la url de base" + "jdbc:postgresql://localhost:5432"+context.getApplicationName()+"?createDatabaseIfNotExist=true");
   	return DataSourceBuilder.create().url("jdbc:postgresql://localhost:5432"+context.getApplicationName()+"?createDatabaseIfNotExist=true").build();
   }
}

