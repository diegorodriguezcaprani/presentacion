package servicios;

import java.util.List;
import java.util.Map;

public class Contenido {
	private String titulo;
	private String descripcion;
	private int cantPuntuaciones;
	private Double puntuacion;
	private boolean destacado;
	private boolean bloqueado;
	private String portada;
	private List<Categoria> categorias;
	private List<String> elenco;
	private List<String> directores;
	private String tipoContenido;
	private List<DatosAtributoContenido> atributos;
	
	public Contenido() {
	}

	public Contenido(String titulo, String descripcion, int cantPuntuaciones, Double puntuacion, boolean destacado,
			boolean bloqueado, String portada, List<Categoria> categorias, List<String> elenco,
			List<String> directores, String tipoContenido, List<DatosAtributoContenido> atributos) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.cantPuntuaciones = cantPuntuaciones;
		this.puntuacion = puntuacion;
		this.destacado = destacado;
		this.bloqueado = bloqueado;
		this.portada = portada;
		this.categorias = categorias;
		this.elenco = elenco;
		this.directores = directores;
		this.tipoContenido = tipoContenido;
		this.atributos = atributos;
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
	
}
