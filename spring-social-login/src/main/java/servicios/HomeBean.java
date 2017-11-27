package servicios;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Usuario;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import datatypes.DatosAtributoContenido;
import datatypes.DatosContenido;
import datatypes.DatosIdNombre;
import datatypes.DatosJson;
import datatypes.DatosSuscripcion;
import datatypes.DatosTipoContenido;

@ManagedBean(name="homeBean")
@SessionScoped
public class HomeBean {
	@ManagedProperty(name="userRepository", value="#{userRepository}")
	private UserRepository userRepository;
	private String idFacebook;
	private Usuario user;
	private List <DatosContenido> contenidos = new ArrayList<DatosContenido>();
	private List <DatosSuscripcion> suscripciones = new ArrayList<DatosSuscripcion>();
	private List <DatosContenido> contenidosSugeridos = new ArrayList<DatosContenido>();
	private String algo;//search algo
	private List <DatosContenido> contenidosFiltrados = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosDestacados = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosFavoritos = new ArrayList<DatosContenido>();
//	private List <DatosContenido> contenidosEnVivo = new ArrayList<DatosContenido>();
//	private List <DatosContenido> peliculas = new ArrayList<DatosContenido>();
//	private List <DatosContenido> series = new ArrayList<DatosContenido>();
	private List <String> imageURLs = new ArrayList<String>();
	private boolean updated;
	private boolean search;
	private boolean todos;
	private String URL= "http://localhost:8080/ServidorTsi2/";
	private String idContenidoFavorito;
	private String nombreEmpresa;
	private boolean suscripto;
	private boolean home;
	
	@PostConstruct
    public void init() {
		this.home= true;
		FacesContext fc = FacesContext.getCurrentInstance();
    	ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
    	ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
    	String nomEmpresa = applicationContext.getApplicationName();
    	this.nombreEmpresa= nomEmpresa.substring(1); // saco el /
		
		System.out.println("______holaaaaaaaaa");
    	//obtenerContenidos();
		
		
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	Usuario user = userRepository.findByUsername(currentPrincipalName);
    	this.idFacebook=user.getProfileUrl();
    	this.user= user;
    	//obtenerCliente();
    	boolean chequeo= chequearSuscripcion();
		setSuscripto(chequeo);
		
		if(!this.isSuscripto()) {
			RequestContext requestContext = RequestContext.getCurrentInstance();    
			requestContext.execute("openPopUp();");
		}
		obtenerSuscripciones();
		//DatosTipoContenido dtContenido2=new DatosTipoContenido("evento deportivo",null, null,true);
		DatosTipoContenido dtContenido1=new DatosTipoContenido("pelicula",null, null,false);
		DatosContenido contenido1= new DatosContenido("contenido1", "descripcion",2, (double) 4,true, false, "http://gfbrobot.com/wp-content/uploads/2011/08/true-blood3.png",null,null,null,dtContenido1, null,"./videoEnArchivo/spring-social-login/fish3.mp4","Fox",10.0);
		contenidos.add(contenido1);
		contenidosDestacados();
		//obtenerFavoritos();
//		//contenidosSugeridos.add(contenido1);
//		DatosContenido contenido2= new DatosContenido(" ","contenido2", "descripcion",2, (double) 1,true, false,"https://play3r.net/wp-content/uploads/2015/11/TTG_GoT_Logo.png",null,null,null,dtContenido2, null);
//		contenidos.add(contenido2);
//		contenidosSugeridos.add(contenido2);
//		Contenido contenido3= new Contenido("contenido3", "descripcion",2, 1,true, false, "https://static1.squarespace.com/static/56f5a63a2eeb81396607a47f/t/58e2d10046c3c48787772858/1491259668498.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido3);
//		contenidosSugeridos.add(contenido3);
//		Contenido contenido4= new Contenido("contenido4", "descripcion",2, 1,true, false,"http://posterposse.com/wp-content/uploads/2016/12/Screen-Shot-2016-12-05-at-9.53.19-AM.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido4);
//		Contenido contenido5= new Contenido("contenido5", "descripcion",2, 1,true, false,"http://cdn.gospelherald.com/data/images/full/17002/breaking-bad.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido5);
//		Contenido contenido6= new Contenido("contenido6", "descripcion",2, 1,true, false,"http://gfbrobot.com/wp-content/uploads/2011/08/true-blood3.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido6);
//		Contenido contenido7= new Contenido("contenido7", "descripcion",2, 1,true, false,"https://play3r.net/wp-content/uploads/2015/11/TTG_GoT_Logo.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido7);
//		Contenido contenido8= new Contenido("contenido8", "descripcion",2, 1,true, false,"https://static1.squarespace.com/static/56f5a63a2eeb81396607a47f/t/58e2d10046c3c48787772858/1491259668498.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido8);
//		Contenido contenido9= new Contenido("contenido9", "descripcion",2, 1,true, false, "http://posterposse.com/wp-content/uploads/2016/12/Screen-Shot-2016-12-05-at-9.53.19-AM.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido9);
//		Contenido contenido10= new Contenido("contenido10", "descripcion",2, 1,true, false,"http://cdn.gospelherald.com/data/images/full/17002/breaking-bad.png","comienzo", "fin",null,null,null,false);
//		contenidos.add(contenido10);
		
		//Cliente cliente= new Cliente("belen.remedi@gmail.com","Belen",24,'M',"Uruguay"," ",true);
		//this.cliente= cliente;
    }	
	public HomeBean() {
		// TODO Auto-generated constructor stub
	}
/*****************************************Servicios*********************************/	
	public void obtenerContenidos() {
		
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> cont = client
    	.target(URL+"contenido/Fox/obtenerContenidos")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
		this.setContenidos(cont);
	}
	
	public void obtenerFavoritos() {
		DatosJson dj= new DatosJson();
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("empresa",nombreEmpresa);
		
		Client client = ClientBuilder.newClient();
		List<DatosContenido> postResponse = client
    	.target(URL+"cliente/obtenerFavoritos")
    	.request().post(Entity.json(dj),new GenericType<List<DatosContenido>>() {});
    	
		this.setContenidosFavoritos(postResponse);
	}
	
	public void obtenerSuscripciones() {
		Client client = ClientBuilder.newClient();
    	List<DatosSuscripcion> cont = client
    	.target(URL+"empresa/"+nombreEmpresa+"/obtenerSuscripciones")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosSuscripcion>>() {});
		this.setSuscripciones(cont);
	}
	
	public boolean chequearSuscripcion() {
		DatosJson dj= new DatosJson();
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("empresa",nombreEmpresa);
		
		
		System.out.println(idFacebook+"idFacebooksuscripcionnnnn");
		System.out.println(nombreEmpresa+"Empresaaaasuscripcionnnnn");
    	Client client = ClientBuilder.newClient();
    	Boolean postResponse = client
    	.target(URL+"cliente/verificarSuscripcionVigente")
    	.request().post(Entity.json(dj),Boolean.class);
    	
    	System.out.println(postResponse+"verificarSuscripcionnnnnnnnnn");
    	/*
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    	}*/
    	
		return postResponse.booleanValue();
	}
	
	public boolean addToFavourite() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String idContenidoFavorito = ec.getRequestParameterMap().get("idContenidoFavorito");	       
		
		System.out.println("favoritoooooooooooooo"+idContenidoFavorito);
		System.out.println("favoritoooooooooooooo"+idFacebook);
		contenidosFavoritos.add(buscarContenidoId(idContenidoFavorito));
		System.out.println(URL + "cliente/agregarFavorito");
		String target= URL + "cliente/agregarFavorito";
		System.out.println(target+"targetttttt");
		DatosJson dj= new DatosJson();
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("titulo",idContenidoFavorito);
		dj.addParameter("empresa","Fox");
		
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(target)
    	.request().post(Entity.json(dj));
    	
    	System.out.println(Entity.json(dj));
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    	}
    	
		return true;
	}
	
/*******auxiliares*******************************************************************************************/	
	public void contenidosDestacados() {
		for(DatosContenido contenido : contenidos) { 
			   if(contenido.isDestacado()) { 
				   contenidosDestacados.add(contenido);
			   }
		}
	}
	
	public DatosContenido buscarContenidoId(String id) {
		//Busco contenido con titulo id
		DatosContenido contenidoEncontrado=null;
		for(DatosContenido contenido : contenidos) { 
			   if(contenido.getTitulo().equals(id)) { 
				   contenidoEncontrado=contenido;
			   }
		}
		return contenidoEncontrado;
	}
	
	public void searchContenido() {
		this.updated= true;
		this.search= true;
		contenidosFiltrados.clear();
		for(DatosContenido contenido : contenidos) { 
			   if(contenido.getTitulo().equals(algo)) { 
				 contenidosFiltrados.add(contenido);
			   }
			}
	}
	
	public void Todos() {
		this.updated= false;
		this.search= false;
	}
	
/*******************getters y setters***************************************************************************************/
	
	
	public List<DatosSuscripcion> getSuscripciones() {
		return suscripciones;
	}

	public boolean isHome() {
		return home;
	}
	public void setHome(boolean home) {
		this.home = home;
	}
	public List<DatosContenido> getContenidosDestacados() {
		return contenidosDestacados;
	}
	
	public void setContenidosDestacados(List<DatosContenido> contenidosDestacados) {
		this.contenidosDestacados = contenidosDestacados;
	}
	
	public void setSuscripciones(List<DatosSuscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}
	
	public boolean isSuscripto() {
		return suscripto;
	}

	public void setSuscripto(boolean suscripto) {
		this.suscripto = suscripto;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public List<DatosContenido> getContenidosFavoritos() {
		return contenidosFavoritos;
	}

	public void setContenidosFavoritos(List<DatosContenido> contenidosFavoritos) {
		this.contenidosFavoritos = contenidosFavoritos;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<String> getImageURLs() {
		return imageURLs;
	}

	public void setImageURLs(List<String> imageURLs) {
		this.imageURLs = imageURLs;
	}

	
	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public List<DatosContenido> getContenidosFiltrados() {
		return contenidosFiltrados;
	}

	public void setContenidosFiltrados(List<DatosContenido> contenidosFiltrados) {
		this.contenidosFiltrados = contenidosFiltrados;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public List<DatosContenido> getContenidosSugeridos() {
		return contenidosSugeridos;
	}

	public void setContenidosSugeridos(List<DatosContenido> contenidosSugeridos) {
		this.contenidosSugeridos = contenidosSugeridos;
	}

	public List <DatosContenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List <DatosContenido> contenidos) {
		this.contenidos = contenidos;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}
	
}
