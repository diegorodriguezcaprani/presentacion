package datatypes;

import java.util.Date;

public class DatosCliente {

	private String idfacebook;
	private String nombre;
	private int edad;
	private char sexo;
	private String pais;
	private Date fechaFinSuscripcion;
	private boolean bloqueado;
	private String urlFoto;
	private String nombreEmpresa;
	
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public String getidfacebook() {
		return idfacebook;
	}
	public void setidfacebook(String idfacebook) {
		this.idfacebook = idfacebook;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Date getFechaFinSuscripcion() {
		return fechaFinSuscripcion;
	}
	public void setFechaFinSuscripcion(Date fechaFinSuscripcion) {
		this.fechaFinSuscripcion = fechaFinSuscripcion;
	}
	
	public DatosCliente(String idfacebook, String nombre, int edad, char sexo, String pais, Date fechaFinSuscripcion, boolean bloqueado, String urlFoto, String nombreEmpresa) {
		super();
		this.idfacebook = idfacebook;
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.bloqueado = bloqueado;
		this.pais = pais;
		this.fechaFinSuscripcion = fechaFinSuscripcion;
		this.urlFoto = urlFoto;
		this.nombreEmpresa= nombreEmpresa;
	}
	public DatosCliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	
}
