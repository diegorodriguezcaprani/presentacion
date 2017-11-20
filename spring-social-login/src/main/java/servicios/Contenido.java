package servicios;

import java.util.List;
import java.util.Map;

public class Contenido {
	private String titulo;
	private String descripcion;
	private int cantPuntuaciones;
	private int puntuacion;
	private boolean destacado;
	private boolean bloqueado;
	private String portada;
	private String comienzo;
	private String fin;
	private List<String> categorias;
	private List<String> elenco;
	private List<String> directores;
	
	public Contenido() {
	}

	public Contenido(String titulo, String descripcion, int cantPuntuaciones, int puntuacion, boolean destacado,
			boolean bloqueado, String portada, String comienzo, String fin, List<String> categorias,
			List<String> elenco, List<String> directores) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.cantPuntuaciones = cantPuntuaciones;
		this.puntuacion = puntuacion;
		this.destacado = destacado;
		this.bloqueado = bloqueado;
		this.portada = portada;
		this.comienzo = comienzo;
		this.fin = fin;
		this.categorias = categorias;
		this.elenco = elenco;
		this.directores = directores;
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

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
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

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
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
	
}
