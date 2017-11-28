package org.baeldung.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    private static final String URL_Back ="localhost:8080/ServidorTsi2/";
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ConfigurableApplicationContext context;

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
        Client client = ClientBuilder.newClient();
        Target=URL_Back + "cliente/altaCliente";
        datatypes.DatosJson dj = new datatypes.DatosJson();
        String nomEmpresa = context.getApplicationName();
        nomEmpresa = nomEmpresa.substring(1); // saco el /
        System.out.println("Empresa============="+nomEmpresa);
        dj.addParameter("idFacebook", user.getProfileUrl());
        dj.addParameter("urlFoto", user.getImageUrl());
        dj.addParameter("nombre", user.getUsername());
        dj.addParameter("empresa", nomEmpresa);
                
        System.out.println(Target);
                
    	Response postResponse = client
    	.target(Target)
    	.request().post(Entity.json(dj));
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    		
    	}
        userRepository.save(user);
        return user.getUsername();
    }

}
