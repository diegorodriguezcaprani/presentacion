package datatypes;

public class DatosSuscripcion {

	private String tipo;
	private double precio;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public DatosSuscripcion(String tipo, double precio) {
		super();
		this.tipo = tipo;
		this.precio = precio;
	}
	public DatosSuscripcion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
