package servicios;

public class Usuario {
	private String id;
	private String name;
	private int anioNac;
	private String apellido;
	private int anioReg;
	private boolean iscasado;
	
	Usuario (String id, String nombre, int anioNac, String apellido, int anioAct, boolean casado){
		this.id = id;
		this.name = nombre;
		this.anioNac = anioNac;
		this.apellido = apellido;
		this.anioReg = anioAct;
		this.iscasado = casado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAnioNac() {
		return anioNac;
	}

	public void setAnioNac(int anioNac) {
		this.anioNac = anioNac;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getAnioReg() {
		return anioReg;
	}

	public void setAnioReg(int anioReg) {
		this.anioReg = anioReg;
	}

	public boolean isIscasado() {
		return iscasado;
	}

	public void setIscasado(boolean iscasado) {
		this.iscasado = iscasado;
	}
	
	
}
