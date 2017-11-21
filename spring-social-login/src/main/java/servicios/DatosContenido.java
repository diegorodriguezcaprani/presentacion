package servicios;

import java.util.List;

public class DatosContenido {
	private String url;
	private String titulo;
	private String descripcion;
	private int cantPuntuaciones;
	private Double puntuacion;
	private boolean destacado;
	private boolean bloqueado;
	private String portada;
	private List<String> elenco;
	private List<String> directores;
	private List<DatosIdNombre> categorias;
	private String tipoContenido;
	private List<DatosAtributoContenido> atributos;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	public Double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Double puntuacion) {
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
	public String getPortada() {
		return portada;
	}
	public void setPortada(String portada) {
		this.portada = portada;
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
	public void setCategorias(List<DatosIdNombre> categorias) {
		this.categorias = categorias;
	}
	public String getTipoContenido() {
		return tipoContenido;
	}
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	public List<DatosAtributoContenido> getAtributos() {
		return atributos;
	}
	public void setAtributos(List<DatosAtributoContenido> atributos) {
		this.atributos = atributos;
	}
	public DatosContenido(String url,String titulo, String descripcion, int cantPuntuaciones, Double puntuacion, boolean destacado,
			boolean bloqueado, String portada, List<String> elenco, List<String> directores, List<DatosIdNombre> categorias,
			String tipoContenido, List<DatosAtributoContenido> atributos) {
		
		this.titulo = titulo;
		this.url= url;
		this.descripcion = descripcion;
		this.cantPuntuaciones = cantPuntuaciones;
		this.puntuacion = puntuacion;
		this.destacado = destacado;
		this.bloqueado = bloqueado;
		this.portada = portada;
		this.elenco = elenco;
		this.directores = directores;
		this.categorias = categorias;
		this.tipoContenido = tipoContenido;
		this.atributos = atributos;
	}
	public DatosContenido() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatosContenido(String titulo, String descripcion, String portada, List<String> elenco,
			List<String> directores, List<DatosIdNombre> categorias, String tipoContenido, List<DatosAtributoContenido> atributos) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.portada = portada;
		this.elenco = elenco;
		this.directores = directores;
		this.categorias = categorias;
		this.tipoContenido = tipoContenido;
		this.atributos = atributos;
	}
	
	
	
	
}
