package datatypes;

public class DatosSuscripcionModificado {

	private String tipo;
	private int precio;
	private double precioOriginal;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public DatosSuscripcionModificado(String tipo, int precio, double precioOrig) {
		super();
		this.tipo = tipo;
		this.precio = precio;
		this.precioOriginal = precioOrig;
	}
	public DatosSuscripcionModificado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getPrecioOriginal() {
		return precioOriginal;
	}
	public void setPrecioOriginal(double precioOriginal) {
		this.precioOriginal = precioOriginal;
	}
	
	
}
