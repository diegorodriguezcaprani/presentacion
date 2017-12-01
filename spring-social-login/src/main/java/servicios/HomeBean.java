package servicios;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import datatypes.DatosNotificacion;
import datatypes.DatosSuscripcion;
import datatypes.DatosSuscripcionModificado;
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
	private List <DatosSuscripcionModificado> suscripcionesModificadas = new ArrayList<DatosSuscripcionModificado>();
	private List <DatosContenido> contenidosSugeridos = new ArrayList<DatosContenido>();
	private String algo;//search algo
	private List <DatosContenido> contenidosFiltrados = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosFiltradosTipoGenero = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosDestacados = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosFavoritos = new ArrayList<DatosContenido>();
	private List <DatosTipoContenido> tiposContenido = new ArrayList<DatosTipoContenido>();
	private List <DatosIdNombre> categorias = new ArrayList<DatosIdNombre>();
	private List <String> imageURLs = new ArrayList<String>();
	private String URL= "http://localhost:8180/ServidorTsi2-0.0.1-SNAPSHOT/";
	private String idContenidoFavorito;
	private String nombreEmpresa;
	private boolean suscripto;
	private boolean home;
	private String stripePublicKey;
	private int amount;
	private Currency currency;
	private boolean updated;
	private boolean search;
	private boolean todos;
	private boolean chequeo;
	private boolean contenidoFiltrado;
	private String tipoContenido;

	private List<DatosNotificacion> notificaciones;
	private int categoriaContenido;
	private boolean tipoSuscripcion;
	private String tipoSuscripcionString;
	
	
	 public enum Currency {
	        EUR, USD;
	 }
	 
	@PostConstruct
    public void init() {
		Map<String, String> env = System.getenv();
		
		System.out.println(env.get("STRIPE_PUBLIC_KEY")+"env___________");
		this.stripePublicKey= env.get("STRIPE_PUBLIC_KEY");
		this.currency= Currency.EUR;
		this.amount= 5000;
		this.home= true;
		FacesContext fc = FacesContext.getCurrentInstance();
    	ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
    	ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
    	String nomEmpresa = applicationContext.getApplicationName();
    	this.nombreEmpresa= nomEmpresa.substring(1); // saco el /
    	this.nombreEmpresa = "fox";
		
		System.out.println("______holaaaaaaaaa");
    	obtenerContenidos();
		
		
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	Usuario user = userRepository.findByUsername(currentPrincipalName);
    	this.idFacebook=user.getProfileUrl();
    	this.user= user;
    	//obtenerCliente();
    	this.chequeo= chequearSuscripcion();
		setSuscripto(chequeo);
    	//setSuscripto(true);
		if(!this.isSuscripto()) {
			RequestContext requestContext = RequestContext.getCurrentInstance();    
			requestContext.execute("openPopUp();");
		}
		obtenerSuscripciones();
		obtenerNotificaciones();
		//DatosTipoContenido dtContenido2=new DatosTipoContenido("evento deportivo",null, null,true);
		DatosTipoContenido dtContenido1=new DatosTipoContenido("pelicula",null, null,false);

		contenidosDestacados();
		obtenerTiposContenido();
		obtenerFavoritos();
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
	
//	public void borrarNotif() {
//		notificaciones = new LinkedList<DatosNotificacion>();
//	}
	
	public String borrarNotif(int id, String tituloContenido, String portada, String url) {
		int posicionBorrar = 0;
		for(DatosNotificacion notif : notificaciones) {
			if(notif.getId() == id) {
				break;
			}
			posicionBorrar++;
		}
		notificaciones.remove(posicionBorrar);
		
		
		DatosJson dj = new DatosJson();
		dj.addParameter("id", id+"");
		
		Client client = ClientBuilder.newClient();
		Boolean respuesta = client
    	.target(URL + "cliente/eliminarNotificacion")
    	.request().post(Entity.json(dj), new GenericType<Boolean>() {});
		
		String ret = "contenido.xhtml?id="+tituloContenido+"&amp;url="+portada+"&amp;urlvideo="+url+"/";
		try {
			FacesContext.getCurrentInstance().getExternalContext().dispatch(ret);
		} catch(Exception e) {
			
		}
		return ret;
	}
	
	public void obtenerNotificaciones() {
		
		DatosJson dj = new DatosJson();
		dj.addParameter("empresa", nombreEmpresa);
		dj.addParameter("idFacebook", idFacebook);
		
		Client client = ClientBuilder.newClient();
		notificaciones = client
    	.target(URL + "cliente/obtenerNotificaciones")
    	.request().post(Entity.json(dj), new GenericType<List<DatosNotificacion>>() {});
		
//		if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
//    		System.out.println("Error al consumir mediante post.");
//    	}
//    	else{
//    		notificaciones= (List<DatosNotificacion>)postResponse.getEntity();
//    	}
	
	}
	
	
	public void obtenerContenidos() {
		
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> cont = client
    	.target(URL+"contenido/"+nombreEmpresa+"/obtenerContenidosNoBloqueados")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
		this.setContenidos(cont);
	}
	public void obtenerTiposContenido() {
		
		Client client = ClientBuilder.newClient();
    	List<DatosTipoContenido> cont = client
    	.target(URL+"contenido/tipoContenido")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosTipoContenido>>() {});
		this.setTiposContenido(cont);
		//System.out.println(cont.get(1).getNombre()+"NombreeeX_______");
	}
	public void filtrarContenido() {
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> cont = client
    	.target(URL+"contenido/"+nombreEmpresa+"/filtrar/"+tipoContenido+"/-1")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
		this.setContenidosFiltradosTipoGenero(cont);
	}
	public void filtrarContenidoTipoCategoria() {
		System.out.println(tipoContenido+"tipoooooo");
		System.out.println(categoriaContenido+"categoriaaa");
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> cont = client
    	.target(URL+"contenido/"+nombreEmpresa+"/filtrar/"+tipoContenido+"/"+categoriaContenido)
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
    	contenidosFiltradosTipoGenero.clear();
		this.setContenidosFiltradosTipoGenero(cont);
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
		
		for(DatosSuscripcion sus : this.getSuscripciones()) {
			suscripcionesModificadas.add(new DatosSuscripcionModificado(sus.getTipo(), (int)(sus.getPrecio()*100), sus.getPrecio()));
			System.out.println((int)(sus.getPrecio()*100)+"aaaaaaaDoubleSusc");
		}
	}
	
	public List<DatosSuscripcionModificado> getSuscripcionesModificadas() {
		return suscripcionesModificadas;
	}
	public void setSuscripcionesModificadas(List<DatosSuscripcionModificado> suscripcionesModificadas) {
		this.suscripcionesModificadas = suscripcionesModificadas;
	}
	public void obtenerSugeridos() {

		DatosJson dj= new DatosJson();
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("empresa",nombreEmpresa);
	
    	Client client = ClientBuilder.newClient();
    	List<DatosContenido> postResponse = client
    	.target(URL+"cliente/obtenerContenidosSugeridos")
    	.request().post(Entity.json(dj),new GenericType<List<DatosContenido>>() {});
    	
		this.setContenidosSugeridos(postResponse);
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
	
	public boolean addToFavourite(boolean aeliminar) {
		//elimino o guardo dependiendo si es favorito
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String idContenidoFavorito = ec.getRequestParameterMap().get("idContenidoFavorito");
	   
		System.out.println("favoritoooooooooooooo"+idContenidoFavorito);
		System.out.println("favoritoooooooooooooo"+idFacebook);

		if(aeliminar) {
			System.out.println("lo eliminoooooo");
			contenidosFavoritos.remove((buscarContenidoId(idContenidoFavorito)));
			DatosJson dj= new DatosJson();
			dj.addParameter("idFacebook", idFacebook);
			dj.addParameter("titulo",idContenidoFavorito);
			dj.addParameter("empresa",nombreEmpresa);
			
	    	Client client = ClientBuilder.newClient();
	    	Response postResponse = client
	    	.target(URL + "cliente/quitarFavorito")
	    	.request().post(Entity.json(dj));
	    	
	    	System.out.println(Entity.json(dj));
	    	
	    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
	    		System.out.println("Error al consumir mediante post.");
	    	}
	    	else{
	    		System.out.println("Se consumio correctamente mediante post.");
	    	}
	    	
			return true;
			
		}else {
			contenidosFavoritos.add(buscarContenidoId(idContenidoFavorito));
			System.out.println("lo agregooooo");
			String target= URL + "cliente/agregarFavorito";
			System.out.println(target+"targetttttt");
			DatosJson dj= new DatosJson();
			dj.addParameter("idFacebook", idFacebook);
			dj.addParameter("titulo",idContenidoFavorito);
			dj.addParameter("empresa",nombreEmpresa);
			
	    	Client client = ClientBuilder.newClient();
	    	Response postResponse = client
	    	.target(target)
	    	.request().post(Entity.json(dj));
	    	
	    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
	    		System.out.println("Error al consumir mediante post.");
	    	}
	    	else{
	    		System.out.println("Se consumio correctamente mediante post.");
	    	}
	    	
			return true;
			
		}
		
	}
	
	public boolean puntuarContenido(int puntuacion) {
		//System.out.println(puntuacion+ "puntuacionnnnnnn");
		//return puntuacion;
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String idContenido = ec.getRequestParameterMap().get("idContenido");
		
		DatosJson dj= new DatosJson();
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("empresa",nombreEmpresa);
		dj.addParameter("puntuacion",puntuacion+"");
		dj.addParameter("titulo",idContenido);
		
    	Client client = ClientBuilder.newClient();
    	DatosContenido postResponse = client
    	.target(URL+"contenido/puntuar")
    	.request().post(Entity.json(dj),DatosContenido.class);
    	
    	//System.out.println(postResponse+"puntuacionServiciooooo");
    	DatosContenido contenido=buscarContenidoId(idContenido);
    	contenidos.remove(contenido);
    	contenido.setPuntuacion(postResponse.getPuntuacion());
    	contenido.setCantPuntuaciones(postResponse.getCantPuntuaciones());
    	contenidos.add(contenido);
    	
		return true;
	}
	
/*******auxiliares*******************************************************************************************/	
	
	public void changeActive(String tipoSuscripcion) {
		System.out.println("yeahhhhh____");
		System.out.println("yeahhhhh____"+ tipoSuscripcion);
		this.tipoSuscripcionString= tipoSuscripcion;
	}
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
		this.contenidoFiltrado = false;
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
		this.contenidoFiltrado = false;
	}
	public void Tipos(DatosTipoContenido o) {
		this.updated= true;
		this.search= false;
		this.tipoContenido= o.getNombre();
		this.categorias= o.getCategorias();
		this.filtrarContenido();
		this.contenidoFiltrado = true;
	}
	
	public void Genero(int idCategoria) {
		System.out.println("hola"+"jajajaj");
		this.updated= true;
		this.search= false;
		this.categoriaContenido= idCategoria;
		this.filtrarContenidoTipoCategoria();
		this.contenidoFiltrado = true;
	}
	
/*******************getters y setters***************************************************************************************/
	
	
	public List<DatosSuscripcion> getSuscripciones() {
		return suscripciones;
	}

	public int getCategoriaContenido() {
		return categoriaContenido;
	}
	public void setCategoriaContenido(int categoriaContenido) {
		this.categoriaContenido = categoriaContenido;
	}
	public List<DatosIdNombre> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<DatosIdNombre> categorias) {
		this.categorias = categorias;
	}
	public List<DatosContenido> getContenidosFiltradosTipoGenero() {
		return contenidosFiltradosTipoGenero;
	}
	public void setContenidosFiltradosTipoGenero(List<DatosContenido> contenidosFiltradosTipoGenero) {
		this.contenidosFiltradosTipoGenero = contenidosFiltradosTipoGenero;
	}
	public String getTipoContenido() {
		return tipoContenido;
	}
	public void setTipoContenido(String tipo) {
		this.tipoContenido = tipo;
	}
	public List<DatosTipoContenido> getTiposContenido() {
		return tiposContenido;
	}
	public void setTiposContenido(List<DatosTipoContenido> tiposContenido) {
		this.tiposContenido = tiposContenido;
	}
	public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public Currency getCurrency() {
	return currency;
}
public void setCurrency(Currency currency) {
	this.currency = currency;
}
	public String getStripePublicKey() {
		return stripePublicKey;
	}
	public void setStripePublicKey(String stripePublicKey) {
		this.stripePublicKey = stripePublicKey;
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

	public List<DatosNotificacion> getNotificaciones() {
		return notificaciones;
	}
	public void setNotificaciones(List<DatosNotificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
	public void setAlgo(String algo) {
		this.algo = algo;
	}
	public boolean isContenidoFiltrado() {
		return contenidoFiltrado;
	}
	public void setContenidoFiltrado(boolean contenidoFiltrado) {
		this.contenidoFiltrado = contenidoFiltrado;
	}
	public String getIdContenidoFavorito() {
		return idContenidoFavorito;
	}
	public void setIdContenidoFavorito(String idContenidoFavorito) {
		this.idContenidoFavorito = idContenidoFavorito;
	}
	public boolean isChequeo() {
		return chequeo;
	}
	public void setChequeo(boolean chequeo) {
		this.chequeo = chequeo;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public void setTipoSuscripcion(boolean tipoSusc) {
		this.tipoSuscripcion= tipoSusc;
		System.out.println(tipoSusc+"tipoSuscBooleannnncheckkkk_____");
	}
	public boolean getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public String getTipoSuscripcionString() {
		return tipoSuscripcionString;
	}
	public void setTipoSuscripcionString(String tipoSuscripcionString) {
		this.tipoSuscripcionString = tipoSuscripcionString;
	}
	
	
	
	
	
}
