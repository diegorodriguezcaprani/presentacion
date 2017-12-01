package datatypes;

import java.util.List;

public class DatosCompartirContenido {

	private String empresa;
	private String titulo;
	private String idFacebookQueComparte;
	private List<String> idFacebookReceptores;
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIdFacebookQueComparte() {
		return idFacebookQueComparte;
	}
	public void setIdFacebookQueComparte(String idFacebookQueComparte) {
		this.idFacebookQueComparte = idFacebookQueComparte;
	}
	public List<String> getIdFacebookReceptores() {
		return idFacebookReceptores;
	}
	public void setIdFacebookReceptores(List<String> idFacebookReceptores) {
		this.idFacebookReceptores = idFacebookReceptores;
	}
	public DatosCompartirContenido(String empresa, String titulo, String idFacebookQueComparte,
			List<String> idFacebookReceptores) {
		super();
		this.empresa = empresa;
		this.titulo = titulo;
		this.idFacebookQueComparte = idFacebookQueComparte;
		this.idFacebookReceptores = idFacebookReceptores;
	}
	public DatosCompartirContenido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
