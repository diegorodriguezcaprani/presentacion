package datatypes;

public class DatosIngresoMensual {
	
	private int posicion;
	private String mes;
	private double ingreso;
	private int anio;
	
	
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public double getIngreso() {
		return ingreso;
	}
	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}
	public DatosIngresoMensual(String mes, double ingreso, int posicion) {
		super();
		this.mes = mes;
		this.ingreso = ingreso;
		this.posicion = posicion;
	}
	public DatosIngresoMensual() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
