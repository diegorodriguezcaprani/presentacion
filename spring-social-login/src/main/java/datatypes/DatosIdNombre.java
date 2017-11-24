package datatypes;

public class DatosIdNombre {

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
	public DatosIdNombre(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public DatosIdNombre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
