package org.baeldung.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.awt.PageAttributes.MediaType;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import servicios.TipoContenido;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    private static final String URL_Back = "http://172.20.10.12:8180/ServidorTsi2/";
	private static final String Separador = "/";
    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
    	String Target = "";
        System.out.println("signup === ");
        final Usuario user = new Usuario();
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));
        user.setImageUrl(connection.getImageUrl());
        user.setProfileUrl(connection.getProfileUrl());
        //connection.getProfileUrl();
        /*Client client = ClientBuilder.newClient();
        Target=(URL_Back)+("cliente/altaCliente/")+this.Separador+ (user.getProfileUrl())+(this.Separador)+(user.getImageUrl())+(this.Separador)+(user.getUsername().trim());
        
        System.out.println(Target);
                
    	Response postResponse = client
    	.target(Target)
    	.request().post(Entity.text("Pruebauser"));
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    		
    	}*/
        userRepository.save(user);
        return user.getUsername();
    }

}
