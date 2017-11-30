package datatypes;

public class DatosNotificacion {
	
	private String nombreClienteQueComparte;
	private String tituloContenido;
	private String url;
	private String portada;
	public String getNombreClienteQueComparte() {
		return nombreClienteQueComparte;
	}
	public void setNombreClienteQueComparte(String nombreClienteQueComparte) {
		this.nombreClienteQueComparte = nombreClienteQueComparte;
	}
	public String getTituloContenido() {
		return tituloContenido;
	}
	public void setTituloContenido(String tituloContenido) {
		this.tituloContenido = tituloContenido;
	}
	public DatosNotificacion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatosNotificacion(String nombreClienteQueComparte, String tituloContenido, String url, String portada) {
		super();
		this.nombreClienteQueComparte = nombreClienteQueComparte;
		this.tituloContenido = tituloContenido;
		this.url = url;
		this.portada = portada;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPortada() {
		return portada;
	}
	public void setPortada(String portada) {
		this.portada = portada;
	}
	
	

}