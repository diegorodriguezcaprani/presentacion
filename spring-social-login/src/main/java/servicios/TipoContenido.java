package servicios;

import java.util.List;

public class TipoContenido {
	
	private String nombre;
	private List<DatosIdNombre> atributos;
	private List<Categoria> categorias;
	
	public TipoContenido(){
	}
	
	public TipoContenido(String nombre, List<DatosIdNombre> atributos, List<Categoria> categorias){
		this.nombre = nombre;
		this.atributos = atributos;
		this.categorias = categorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<DatosIdNombre> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<DatosIdNombre> atributos) {
		this.atributos = atributos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
}
