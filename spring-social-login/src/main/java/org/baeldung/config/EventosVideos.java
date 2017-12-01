package org.baeldung.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import datatypes.DatosJson;
@RestController
@RequestMapping("/eventosVideo")
public class EventosVideos {
	@Autowired 
	private ConfigurableApplicationContext context;
	@Autowired
    private UserRepository userRepository;
	private String URL= "http://localhost:8180/ServidorTsi2-0.0.1-SNAPSHOT/";
	
	@RequestMapping(method = RequestMethod.POST, value = "/pausa")
	@ResponseStatus(HttpStatus.NO_CONTENT)
		public void guardarEstadoPausa(WebRequest request, Principal principal){
		    System.out.println(request.getParameter("paused"));
		    System.out.println(request.getParameter("video"));
		    Usuario user = userRepository.findByUsername(principal.getName());
			DatosJson dj = new DatosJson();
			dj.addParameter("idFacebook",user.getProfileUrl());
			String nomEmpresa = context.getDisplayName();
	        nomEmpresa = nomEmpresa.substring(1); // saco el /
	        dj.addParameter("empresa", nomEmpresa);
	        dj.addParameter("tiempo",request.getParameter("paused") );
	        dj.addParameter("titulo",request.getParameter("video"));
		    
		    Client client = ClientBuilder.newClient();
	    	Response postResponse = client
		    	.target(URL+"/contenido/guardarTiempoReproduccion")
		    	.request().post(Entity.json(dj));

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/play")
	@ResponseStatus(HttpStatus.NO_CONTENT)
		public void guardarEstadoPlay(WebRequest request, Principal principal){
		    System.out.println(request.getParameter("played"));
		    //System.out.println(request.getParameter("video"));
		    Usuario user = userRepository.findByUsername(principal.getName());
			DatosJson dj = new DatosJson();
			dj.addParameter("idFacebook",user.getProfileUrl());
			String nomEmpresa = context.getDisplayName();
	        nomEmpresa = nomEmpresa.substring(1); // saco el /
	        dj.addParameter("empresa", nomEmpresa);
	        dj.addParameter("tiempo","0" );
	        dj.addParameter("titulo",request.getParameter("video"));
		    
		    Client client = ClientBuilder.newClient();
	    	Response postResponse = client
		    	.target(URL+"/contenido/guardarTiempoReproduccion")
		    	.request().post(Entity.json(dj));
	    	
	    	 DatosJson djh = new DatosJson();
	    	 djh.addParameter("idFacebook",user.getProfileUrl());
	    	 djh.addParameter("titulo",request.getParameter("video"));
	    	 dj.addParameter("empresa",nomEmpresa);
	    	 Boolean agreghist = client
		    	.target(URL+"/cliente/agregarHistorico")
		    	.request().post(Entity.json(djh),Boolean.class);
	}
	


}
