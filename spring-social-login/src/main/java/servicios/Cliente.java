package servicios;

import java.util.Date;

public class Cliente {
	private String mail;
	private String nombre;
	private int edad;
	private char sexo;
	private String pais;
	private String fechaFinSuscripcion;
	private boolean bloqueado;
	
	public Cliente(String mail, String nombre, int edad, char sexo, String pais, String fechaFinSuscripcion,
			boolean bloqueado) {
		this.mail = mail;
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = sexo;
		this.pais = pais;
		this.fechaFinSuscripcion = fechaFinSuscripcion;
		this.bloqueado = bloqueado;
	}
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
	public String getFechaFinSuscripcion() {
		return fechaFinSuscripcion;
	}
	public void setFechaFinSuscripcion(String fechaFinSuscripcion) {
		this.fechaFinSuscripcion = fechaFinSuscripcion;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	

}
