package servicios;

import java.util.Date;

public class DatosCliente {

	private String mail;
	private String nombre;
	private int edad;
	private char sexo;
	private String pais;
	private Date fechaFinSuscripcion;
	private boolean bloqueado;
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	
	public DatosCliente(String mail, String nombre, int edad, char sexo, String pais, Date fechaFinSuscripcion, boolean bloqueado) {
		super();
		this.mail = mail;
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.bloqueado = bloqueado;
		this.pais = pais;
		this.fechaFinSuscripcion = fechaFinSuscripcion;
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
