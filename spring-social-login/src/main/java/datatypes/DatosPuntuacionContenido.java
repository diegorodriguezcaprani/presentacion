package datatypes;

public class DatosPuntuacionContenido {
	
	private String titulo;
	private double puntuacion;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	public DatosPuntuacionContenido(String titulo, double puntuacion) {
		super();
		this.titulo = titulo;
		this.puntuacion = puntuacion;
	}
	public DatosPuntuacionContenido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
