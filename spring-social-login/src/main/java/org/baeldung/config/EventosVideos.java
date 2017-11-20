package org.baeldung.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/eventosVideo")
public class EventosVideos {
	
	@RequestMapping(method = RequestMethod.POST, value = "/pausa")
	@ResponseStatus(HttpStatus.NO_CONTENT)
		public void guardarEstadoPausa(WebRequest request){
		    System.out.println(request.getParameter("paused"));
		    System.out.println(request.getParameter("video"));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/play")
	@ResponseStatus(HttpStatus.NO_CONTENT)
		public void guardarEstadoPlay(WebRequest request){
		    System.out.println(request.getParameter("played"));
		    //System.out.println(request.getParameter("video"));
	}
	


}
