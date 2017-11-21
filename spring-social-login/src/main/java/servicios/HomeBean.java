package servicios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@ManagedBean(name="homeBean")
@SessionScoped
public class HomeBean {
	
	private List <DatosContenido> contenidos = new ArrayList<DatosContenido>();
	private DatosContenido contenido;
	private Cliente cliente;
	private List <DatosContenido> contenidosSugeridos = new ArrayList<DatosContenido>();
	private String algo;
	private List <DatosContenido> contenidosFiltrados = new ArrayList<DatosContenido>();
	private List <DatosContenido> contenidosTodos = new ArrayList<DatosContenido>();
	private List <String> imageURLs = new ArrayList<String>();
	private boolean updated;
	private boolean search;
	private boolean todos;
	private String URL= "http://172.16.138.135:8180/ServidorTsi2/";
	//private String URL="http://localhost:3000/contenidos";


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
		System.out.println("______holaaaaaaaaa");
    	//obtenerContenidos();
    	
    	//obtenerCliente();
		DatosContenido contenido1= new DatosContenido("http://192.168.1.43:8080/spring-social-login/video/streaming1","contenido1", "descripcion",2, (double) 4,true, false, "http://gfbrobot.com/wp-content/uploads/2011/08/true-blood3.png",null,null,null,"pelicula", null);
		contenidos.add(contenido1);
		//contenidosSugeridos.add(contenido1);
		//Contenido contenido2= new Contenido("contenido2", "descripcion",2, (double) 1,true, false,"https://play3r.net/wp-content/uploads/2015/11/TTG_GoT_Logo.png",null,null,null,"serie", null);
		//contenidos.add(contenido2);
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
    	.target(URL+"contenido/obtenerContenidos")
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
