package servicios;

import java.util.List;

public class TipoContenido {
	
	private int id;
	private String nombre;
	private List<String> atributos;
	
	public TipoContenido(){
	}
	
	public TipoContenido(int id, String nombre, List<String> atributos){
		this.id = id;
		this.nombre = nombre;
		this.atributos = atributos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<String> atributos) {
		this.atributos = atributos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
