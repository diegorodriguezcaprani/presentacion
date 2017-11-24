package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import datatypes.DatosContenido;
import datatypes.DatosIdNombre;

@ManagedBean(name="contenidoBean")
@RequestScoped
public class ContenidoBean {
	@ManagedProperty(value = "#{param.id}")// t�tulo
	private String id;
	@ManagedProperty(value = "#{homeBean.contenidos}") //todos los contenidos del beanhome
	private List <DatosContenido> contenidos;
	@ManagedProperty(value = "#{param.url}")// portada
	private String url;// portada
	@ManagedProperty(value = "#{param.urlvideo}")// t�tulo
	private String urlvideo;
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

	@PostConstruct
    public void init() {
		this.maxEstrellas=(int) (5-this.puntuacion-1);
		System.out.println("_______"+"contonidoBean");
		System.out.println("_______"+this.getId());
		System.out.println("siiiiiiiiiiiiiii___"+this.getContenidos().get(0).getTitulo());
		 System.out.println("____encontreeURL_"+this.getUrl());
	
		//Busco contenido con titulo id
		for(DatosContenido contenido : contenidos) { 
			   if(contenido.getTitulo().equals(id)) { 
				   System.out.println("____encontree_"+contenido.getTitulo());
			       //found it!
					this.url= contenido.getPortada();
					System.out.println("____encontreePortada_"+contenido.getPortada());
					this.descripcion= contenido.getDescripcion();
					this.cantPuntuaciones= contenido.getCantPuntuaciones();
					//this.puntuacion= contenido.getPuntuacion().intValue();
					this.puntuacion= 4;
					this.elenco= contenido.getElenco();
					this.directores= contenido.getDirectores();
					this.categorias= contenido.getCategorias();
					this.bloqueado= contenido.isBloqueado();
					this.destacado= contenido.isDestacado();
					this.enVivo= contenido.getTipoContenido().isVivo();
			   }
			}
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

	public String getUrl() {
		return url;
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
	

}
