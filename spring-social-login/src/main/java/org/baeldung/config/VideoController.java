package org.baeldung.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import org.baeldung.config.enviarContenidos.MultipartFileSender;
import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import datatypes.DatosJson;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video")
public class VideoController {

	@Autowired 
	private ConfigurableApplicationContext context;
	@Autowired
    private UserRepository userRepository;
	
	private String target = "http://localhost:8080/ServidorTsi2";


	@RequestMapping(method = RequestMethod.GET, value = "/{empresa:.+}/{video:.+}")
	public StreamingResponseBody stream(@PathVariable String empresa, @PathVariable String video, Principal principal, HttpServletRequest request, HttpServletResponse response)
			throws MalformedURLException, IOException {
		
		//File videoFile = new File(videoLocation+empresa+"/"+video);
		Usuario user = userRepository.findByUsername(principal.getName());
		Client client = ClientBuilder.newClient();
		DatosJson dj = new DatosJson();
		DatosJson djbloqueado = new DatosJson();
		DatosJson djVerificarppv = new DatosJson();
		DatosJson djesPPV = new DatosJson();
		
		djbloqueado.addParameter("idFacebook", user.getProfileUrl());
		djVerificarppv.addParameter("idFacebook", user.getProfileUrl());
		dj.addParameter("idFacebook",user.getProfileUrl());
		
		String nomEmpresa = context.getApplicationName();
        nomEmpresa = nomEmpresa.substring(1); // saco el /
        System.out.println(nomEmpresa);
        
        djbloqueado.addParameter("empresa", nomEmpresa);
        dj.addParameter("empresa", nomEmpresa);
		djVerificarppv.addParameter("empresa", nomEmpresa);
		djesPPV.addParameter("empresa", nomEmpresa);
		
		djVerificarppv.addParameter("titulo", video);
		djesPPV.addParameter("titulo", video);
		
		Boolean esContenidoPPV = client
		    	.target(this.target+ "/contenido/contenidoEsPPV")
		    	.request().post(Entity.json(djesPPV), Boolean.class);
		
		Boolean conenidoPPVComprado = client
		    	.target(this.target+ "/cliente/tieneCompradoPPV")
		    	.request().post(Entity.json(djVerificarppv), Boolean.class);
		Boolean clienteSubscripto = client
		    	.target(this.target+ "/cliente/verificarSuscripcionVigente")
		    	.request().post(Entity.json(dj), Boolean.class);
		Boolean clienteBloqueado = client
		    	.target(this.target+ "/cliente/clienteEstaBloqueado")
		    	.request().post(Entity.json(djbloqueado),Boolean.class);
		
		System.out.println("cliente bloqueado" + clienteBloqueado.toString());
		
		System.out.println("es contenido ppv" + esContenidoPPV.toString());
		
		System.out.println("cliente subscripto" + clienteSubscripto.toString());
		
		System.out.println("cliente PPV Comprado" + conenidoPPVComprado.toString());
		final InputStream videoFileStream = new URL("http://172.20.10.3:8080/").openStream();
		return (os) -> {
			if (!clienteBloqueado && ((!esContenidoPPV && clienteSubscripto) || (esContenidoPPV && conenidoPPVComprado)))
			{
				readAndWrite(videoFileStream, os);
			}
		};
		/*if (!clienteBloqueado && ((!esContenidoPPV && clienteSubscripto) || (esContenidoPPV && conenidoPPVComprado)))		
		{
			try {
				MultipartFileSender.fromPath(videoFile.toPath())
				.with(request)
				.with(response)
				.serveResource();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("El usuario no esta suscripto");
			try {
				response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//final InputStream videoFileStream = new FileInputStream(videoFile);
		final InputStream videoFileStream = new URL("http://172.20.10.3:8080/").openStream();
		return (os) -> {
			readAndWrite(videoFileStream, os);
		};*/
	}
	
	private void readAndWrite(InputStream is, OutputStream os)
            throws IOException {
        byte[] data = new byte[2048];
        int read = 0;
        System.out.println("Paso y leyo"+Integer.toString(read));
        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read);
            //System.out.println("Paso y leyo"+Integer.toString(read));
        }
        System.out.println("salio del while =="+Integer.toString(read));
        os.flush();
    }
	
}
