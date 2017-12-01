package datatypes;

import java.util.Date;

public class DatosComentario {

	private int id;
	private String mensaje;
	private Date fecha;
	private int spoilerCount;
	private String idfacebookCliente;
	private String nombreCliente;
	private String tituloContenido;
	
	
	public DatosComentario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DatosComentario(int id, String mensaje, Date fecha, int spoilerCount, String idfacebookCliente,
			String tituloContenido, String nombreCliente) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.fecha = fecha;
		this.spoilerCount = spoilerCount;
		this.idfacebookCliente = idfacebookCliente;
		this.tituloContenido = tituloContenido;
		this.nombreCliente = nombreCliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getIdfacebookCliente() {
		return idfacebookCliente;
	}
	public void setIdfacebookCliente(String idfacebookCliente) {
		this.idfacebookCliente = idfacebookCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getSpoilerCount() {
		return spoilerCount;
	}
	public void setSpoilerCount(int spoilerCount) {
		this.spoilerCount = spoilerCount;
	}
	public String getTituloContenido() {
		return tituloContenido;
	}
	public void setTituloContenido(String tituloContenido) {
		this.tituloContenido = tituloContenido;
	}
	
	
	
}
