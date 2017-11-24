package datatypes;

public class DatosAtributoContenido {

	private int id;
	private String nombre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public DatosAtributoContenido(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public DatosAtributoContenido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
