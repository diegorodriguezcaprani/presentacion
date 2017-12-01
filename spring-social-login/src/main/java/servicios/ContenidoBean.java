package servicios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datatypes.DatosCliente;
import datatypes.DatosComentario;
import datatypes.DatosCompartirContenido;
import datatypes.DatosContenido;
import datatypes.DatosIdNombre;
import datatypes.DatosJson;
import datatypes.DatosNotificacion;
import datatypes.DatosParametro;

@ManagedBean(name="contenidoBean")
@RequestScoped
public class ContenidoBean {
	@ManagedProperty(value = "#{param.id}")// t�tulo
	private String id;
	@ManagedProperty(value = "#{homeBean.contenidos}") //todos los contenidos del beanhome
	private List <DatosContenido> contenidos;
	@ManagedProperty(value = "#{homeBean.idFacebook}") //idFacebook del BeanHome
	private String idFacebook;
	@ManagedProperty(value = "#{homeBean.contenidosFavoritos}")
	private List <DatosContenido> contenidosFavoritos = new ArrayList<DatosContenido>();
	@ManagedProperty(value = "#{param.url}")// portada
	private String url;// portada
	@ManagedProperty(value = "#{param.urlvideo}")// t�tulo
	private String urlvideo;
	@ManagedProperty(value = "#{homeBean.URL}") //
	private String URL;	
	private int maxEstrellas;
	
	private String descripcion;
	private int cantPuntuaciones;
	private int puntuacion;
	private boolean destacado;
	private boolean bloqueado;
	private boolean enVivo;
	private String comienzo;
	private String fin;
	private List<String> elenco;
	private List<String> directores;
	private List<DatosIdNombre> categorias;

	private String videoTime;
	private String empresaContenido;
	private List<DatosComentario> comentarios;
	private String comentarioEscrito;
	private List<String> clientesSeleccionados;
	private List<DatosCliente> clientes;
	private List<String> nombresClientes;
	private boolean esPPV;
	private double precioPPV;
	private boolean clienteComproPPV;
	private int precioPPVInt;
	
	private String textFavourite;
	private int puntuacionUsuario;

	@PostConstruct
    public void init() {

		
		this.comentarios = new LinkedList<DatosComentario>();
		esPPV = false;
		clienteComproPPV = false;
		

		this.puntuacionUsuario=0;
		System.out.println("_______"+"contonidoBean");
		System.out.println("_______"+this.getId());
		System.out.println("siiiiiiiiiiiiiii___"+this.getContenidos().get(0).getTitulo());
		System.out.println("____encontreeURL_"+this.getUrl());
		System.out.println(this.urlvideo+"URLVIDEO");
	
		//Busco contenido con titulo id
		for(DatosContenido contenido : contenidos) { 
			   if(contenido.getTitulo().equals(id)) { 
				   System.out.println("____encontree_"+contenido.getTitulo());
			       //found it!
					this.url= contenido.getPortada();
					System.out.println("____encontreePortada_"+contenido.getPortada());
					this.descripcion= contenido.getDescripcion();
					this.cantPuntuaciones= contenido.getCantPuntuaciones();
					this.puntuacion= contenido.getPuntuacion().intValue();
					System.out.println("Doubleeeee"+puntuacion);
					this.elenco= contenido.getElenco();
					this.directores= contenido.getDirectores();
					this.categorias= contenido.getCategorias();
					this.bloqueado= contenido.isBloqueado();
					this.destacado= contenido.isDestacado();
					this.enVivo= contenido.getTipoContenido().isVivo();
					this.empresaContenido = contenido.getEmpresa();
					esPPV = contenido.getPrecioPayPerView() != null;
					if(esPPV) {
						precioPPV = contenido.getPrecioPayPerView();
						precioPPVInt = (int)precioPPV;
						clienteComproPPV = tieneCompradoPPV();
					}
					
			   }
		}
		obtenerTiempoReproduccion(this.id);
		obtenerComentarios();
		nombresClientes = new LinkedList<String>();
		obtenerClientes();
		this.maxEstrellas= 5-1;
		//this.maxEstrellas= 5-this.puntuacion;
		if (estaEnFavoritos()) {
			this.textFavourite= "Quitar de Favoritos";
		}else {
			this.textFavourite= "Agregar a Favoritos";
		}
	}
	

	private boolean tieneCompradoPPV() {
		DatosJson dj = new DatosJson();
		dj.addParameter("empresa", empresaContenido);
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("titulo", id);
		
		Client client = ClientBuilder.newClient();
		Boolean respuesta = client
    	.target(URL + "cliente/tieneCompradoPPV")
    	.request().post(Entity.json(dj), new GenericType<Boolean>() {});
		
		return respuesta;
	}
	
	public String marcarComentarioSpam(int idComent) {
		
		DatosJson dj = new DatosJson();
		dj.addParameter("idComentario", idComent+"");
		dj.addParameter("empresa", empresaContenido);
		dj.addParameter("idFacebook", idFacebook);
		
		Client client = ClientBuilder.newClient();
		Response postResponse = client
    	.target(URL + "comentario/denunciarSpoiler")
    	.request().post(Entity.json(dj));
		
		if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		obtenerComentarios();
    	}
		
		return "";
	}
	
	public String compartirContenido() {
		
		DatosCompartirContenido dcc = new DatosCompartirContenido(empresaContenido, id, idFacebook, obtenerIdFacebookReceptores());		
		Client client = ClientBuilder.newClient();
		Response postResponse = client
    	.target(URL + "contenido/compartirContenido")
    	.request().post(Entity.json(dcc));
		
		return "";
	}
	
	private List<String> obtenerIdFacebookReceptores() {
		List<String> retorno = new LinkedList<String>();
		
		for(DatosCliente cli : clientes) {
			if(clientesSeleccionados.contains(cli.getNombre())) {
				retorno.add(cli.getidfacebook());
			}
		}
		
		return retorno;
	}
	
	public String agregarComentario() {
		
		DatosJson dj = new DatosJson();
		dj.addParameter("texto", comentarioEscrito);
		dj.addParameter("titulo", id);
		dj.addParameter("empresa", empresaContenido);
		dj.addParameter("idFacebook", idFacebook);
		
		Client client = ClientBuilder.newClient();
		Response postResponse = client
    	.target(URL + "comentario/agregarComentario")
    	.request().post(Entity.json(dj));
		
		if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		obtenerComentarios();
    		
    	}
		
		return "";
	}
	
	public void obtenerComentarios() {
		
		Client client = ClientBuilder.newClient();
    	List<DatosComentario> cont = client
    	.target(URL+"comentario/"+empresaContenido+"/obtenerComentarios/"+id)
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosComentario>>() {});
		this.setComentarios(cont);
	}
	
	public void obtenerClientes() {
		Client client = ClientBuilder.newClient();
    	List<DatosCliente> cont = client
    	.target(URL+"cliente/"+empresaContenido+"/obtenerClientes")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosCliente>>() {});
		this.setClientes(cont);
		obtenerNombresClientes();
	}
	
	public void obtenerNombresClientes() {
		for(DatosCliente cli : this.getClientes()) {
			if(!((cli.getidfacebook()).equals(idFacebook))) {
				nombresClientes.add(cli.getNombre());
			}
		}
	}
	public boolean agregarQuitarFavorito() {
		boolean favorito=false;
		this.textFavourite= "Quitar de Favoritos";
		for(DatosContenido contenido : contenidosFavoritos) { 
			   if(contenido.getTitulo().equals(id)) { 
				   favorito= true;
				   this.textFavourite=  "Añadir a Favoritos";
			   }
		}
		return favorito;
	}
	public boolean estaEnFavoritos() {
		boolean favorito=false;
		for(DatosContenido contenido : contenidosFavoritos) { 
			   if(contenido.getTitulo().equals(id)) { 
				   favorito= true;
			   }
		}
		return favorito;
	}

	public String getIdFacebook() {
		return idFacebook;
	}



	public List<DatosCliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<DatosCliente> clientes) {
		this.clientes = clientes;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}



	public List<String> getNombresClientes() {
		return nombresClientes;
	}

	public void setNombresClientes(List<String> nombresClientes) {
		this.nombresClientes = nombresClientes;
	}

	

	public List<String> getClientesSeleccionados() {
		return clientesSeleccionados;
	}

	public void setClientesSeleccionados(List<String> clientesSeleccionados) {
		this.clientesSeleccionados = clientesSeleccionados;
	}

	public String getComentarioEscrito() {
		return comentarioEscrito;
	}

	public void setComentarioEscrito(String comentarioEscrito) {
		this.comentarioEscrito = comentarioEscrito;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String currentVideoTime) {
		this.videoTime = currentVideoTime;
	}


	public String getEmpresaContenido() {
		return empresaContenido;
	}

	public void setEmpresaContenido(String empresaContenido) {
		this.empresaContenido = empresaContenido;
	}

	public void obtenerTiempoReproduccion(String id) {
		
		//this.setVideoTime("5");
		DatosJson dj = new DatosJson();
		dj.addParameter("idFacebook",idFacebook);
		String nomEmpresa = "fox";
        dj.addParameter("empresa", nomEmpresa);
        dj.addParameter("titulo",id);
	    
	    Client client = ClientBuilder.newClient();
    	int postResponse = client
	    	.target(URL+"contenido/obtenerTiempoReproduccion")
	    	.request().post(Entity.json(dj),int.class);
    	
    	//String cont= (String)postResponse.getEntity();
    	System.out.println("TIEMPO DE REPRODUCCION: "+ postResponse);
    	this.setVideoTime(Integer.toString(postResponse));
	
	}

	
	
	public String getUrlvideo() {
		return urlvideo;
	}



	public void setUrlvideo(String urlvideo) {
		this.urlvideo = urlvideo;
	}



	public boolean isEnVivo() {
		return enVivo;
	}

	public void setEnVivo(boolean enVivo) {
		this.enVivo = enVivo;
	}

	public ContenidoBean() {
		// TODO Auto-generated constructor stub
	}
	
	public int getMaxEstrellas() {
		return maxEstrellas;
	}
	public void setMaxEstrellas(int maxEstrellas) {
		this.maxEstrellas = maxEstrellas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantPuntuaciones() {
		return cantPuntuaciones;
	}
	public void setCantPuntuaciones(int cantPuntuaciones) {
		this.cantPuntuaciones = cantPuntuaciones;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public boolean isDestacado() {
		return destacado;
	}
	public void setDestacado(boolean destacado) {
		this.destacado = destacado;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	public int getPrecioPPVInt() {
		return precioPPVInt;
	}


	public void setPrecioPPVInt(int precioPPVInt) {
		this.precioPPVInt = precioPPVInt;
	}


	public String getComienzo() {
		return comienzo;
	}
	public void setComienzo(String comienzo) {
		this.comienzo = comienzo;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public List<String> getElenco() {
		return elenco;
	}
	public void setElenco(List<String> elenco) {
		this.elenco = elenco;
	}
	public List<String> getDirectores() {
		return directores;
	}
	public void setDirectores(List<String> directores) {
		this.directores = directores;
	}
	public List<DatosIdNombre> getCategorias() {
		return categorias;
	}
	public void setCategorias( List<DatosIdNombre> categorias) {
		this.categorias = categorias;
	}
	
	public List<DatosComentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<DatosComentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getUrl() {
		return url;
	}

	public boolean isEsPPV() {
		return esPPV;
	}

	public void setEsPPV(boolean esPPV) {
		this.esPPV = esPPV;
	}

	public double getPrecioPPV() {
		return precioPPV;
	}

	public void setPrecioPPV(double precioPPV) {
		this.precioPPV = precioPPV;
	}

	public boolean isClienteComproPPV() {
		return clienteComproPPV;
	}

	public void setClienteComproPPV(boolean clienteComproPPV) {
		this.clienteComproPPV = clienteComproPPV;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DatosContenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List<DatosContenido> contenidos) {
		this.contenidos = contenidos;
	}

	public String getTextFavourite() {
		return textFavourite;
	}

	public void setTextFavourite(String textFavourite) {
		this.textFavourite = textFavourite;
	}

	public List<DatosContenido> getContenidosFavoritos() {
		return contenidosFavoritos;
	}

	public void setContenidosFavoritos(List<DatosContenido> contenidosFavoritos) {
		this.contenidosFavoritos = contenidosFavoritos;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getPuntuacionUsuario() {
		System.out.println(puntuacionUsuario+"puntuacionnUsuarioo");
		this.puntuacionUsuario= puntuacionUsuario+1;
		return puntuacionUsuario;
	}

	public void setPuntuacionUsuario(int puntuacionUsuario) {
		this.puntuacionUsuario = puntuacionUsuario;
	}
	
	

}
