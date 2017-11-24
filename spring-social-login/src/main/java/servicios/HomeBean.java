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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import datatypes.DatosAtributoContenido;
import datatypes.DatosContenido;
import datatypes.DatosIdNombre;
import datatypes.DatosJson;
import datatypes.DatosTipoContenido;

@ManagedBean(name="homeBean")
@SessionScoped
public class HomeBean {
	@ManagedProperty(name="userRepository", value="#{userRepository}")
	private UserRepository userRepository;
	private String idFacebook;
	private List <DatosContenido> contenidos = new ArrayList<DatosContenido>();
	private DatosContenido contenido;
	private Cliente cliente;
	private List <DatosContenido> contenidosSugeridos = new ArrayList<DatosContenido>();
	private String algo;//search algo
	private List <DatosContenido> contenidosFiltrados = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosFavoritos = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosTodos = new ArrayList<DatosContenido>();
	private List <String> imageURLs = new ArrayList<String>();
	private boolean updated;
	private boolean search;
	private boolean todos;
	private String URL= "http://172.20.10.2:8180/ServidorTsi2/";
	private String idContenidoFavorito;
	//private String URL="http://localhost:3000/contenidos";
	
	
	public boolean addToFavourite() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String idContenidoFavorito = ec.getRequestParameterMap().get("idContenidoFavorito");	       
		
		System.out.println("favoritoooooooooooooo"+idContenidoFavorito);
		System.out.println("favoritoooooooooooooo"+idFacebook);
		contenidosFavoritos.add(buscarContenidoId(idContenidoFavorito));
//		DatosJson dj= new DatosJson();
//		dj.addParameter("idFacebook", " ");
//		dj.addParameter("titulo",idContenidoFavorito);
//		dj.addParameter("empresa","Fox");
//		
//    	Client client = ClientBuilder.newClient();
//    	Response postResponse = client
//    	.target(URL +"/cliente/agegarFavorito/")
//    	.request().post(Entity.json(dj));
//    	
//    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
//    		System.out.println("Error al consumir mediante post.");
//    	}
//    	else{
//    		System.out.println("Se consumio correctamente mediante post.");
//    	}
    	
		return true;
	}
	
	
	
	public String getIdFacebook() {
		return idFacebook;
	}



	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
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

	public HomeBean() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
    public void init() {
		//Principal principal=(Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		//System.out.println("______"+principal.getName());
		//Usuario user = userRepository.findByUsername(principal.getName());
		//this.idFacebook=user.getProfileUrl();
		
		System.out.println("______holaaaaaaaaa");
    	//obtenerContenidos();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	Usuario user = userRepository.findByUsername(currentPrincipalName);
    	this.idFacebook=user.getProfileUrl();
    	
    	//obtenerCliente();
  
		//DatosTipoContenido dtContenido2=new DatosTipoContenido("evento deportivo",null, null,true);
		DatosTipoContenido dtContenido1=new DatosTipoContenido("pelicula",null, null,false);
		DatosContenido contenido1= new DatosContenido("contenido1", "descripcion",2, (double) 4,true, false, "http://gfbrobot.com/wp-content/uploads/2011/08/true-blood3.png",null,null,null,dtContenido1, null,"./videoEnArchivo/fish3.mp4","Fox");
		contenidos.add(contenido1);
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
		
		Cliente cliente= new Cliente("belen.remedi@gmail.com","Belen",24,'M',"Uruguay"," ",true);
		this.cliente= cliente;
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
	
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public List<DatosContenido> getContenidosTodos() {
		return contenidosTodos;
	}

	public void setContenidosTodos(List<DatosContenido> contenidosTodos) {
		this.contenidosTodos = contenidosTodos;
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

	public void obtenerContenidos() {
		
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> cont = client
    	.target(URL+"contenido/Fox/obtenerContenidos")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
		this.setContenidos(cont);
	}

	public void obtenerCliente() {
		
		Client client = ClientBuilder.newClient();
    	Cliente cont = client
    	.target(URL+"contenido/obtenerContenidos")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<Cliente>() {});
		
		this.setCliente(cont);

	}
	public List <DatosContenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List <DatosContenido> contenidos) {
		this.contenidos = contenidos;
	}

	public DatosContenido getContenido() {
		return contenido;
	}

	public void setContenido(DatosContenido contenido) {
		this.contenido = contenido;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}
	
	
}
