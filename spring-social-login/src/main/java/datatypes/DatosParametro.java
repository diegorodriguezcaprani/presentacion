package datatypes;

public class DatosParametro {
	
	private String nombre;
	private String valor;
	public DatosParametro(String nombre, String valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	public DatosParametro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
