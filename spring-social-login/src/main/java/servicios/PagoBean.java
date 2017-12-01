package servicios;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.primefaces.context.RequestContext;

import datatypes.DatosContenido;
import datatypes.DatosJson;


@ManagedBean(name="pagoBean")
@RequestScoped
public class PagoBean {
	@ManagedProperty(value = "#{param.exito}")
	private boolean exito;
	@ManagedProperty(value = "#{param.exitoPPV}")
	private boolean exitoPPV;
	@ManagedProperty(value = "#{param.titulo}")
	private String titulo;
	@ManagedProperty(value = "#{homeBean.chequeo}") //
	private boolean chequeoHome;
	@ManagedProperty(value = "#{homeBean.nombreEmpresa}") //
	private String nombreEmpresa;
	@ManagedProperty(value = "#{homeBean.idFacebook}") //
	private String idFacebook;
	@ManagedProperty(value = "#{homeBean.URL}") //
	private String URL;	
	@ManagedProperty(value = "#{homeBean.tipoSuscripcionString}") //
	private String tipoSuscripcion;

	
	private String lala;
	
	@PostConstruct
    public void init() {
		System.out.println(exito+"elBooleano essssss");
		if (exito) {
			Suscribir();
			RequestContext requestContext = RequestContext.getCurrentInstance();    
			requestContext.execute("openPopUpExito();");
		}
		if (exitoPPV) {
			String jaja = titulo;
			//comprarPPV();
			RequestContext requestContext = RequestContext.getCurrentInstance();    
			requestContext.execute("openPopUpExito();");
		}
		
	}
	public boolean chequeo() {
		return chequearSuscripcion();
		//return true;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}
	public boolean isChequeoHome() {
		return chequeoHome;
	}
	public void setChequeoHome(boolean chequeoHome) {
		this.chequeoHome = chequeoHome;
	}
	
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getIdFacebook() {
		return idFacebook;
	}
	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public String getTipoSuscripcion() {
		return tipoSuscripcion;
	}
	public void setTipoSuscripcion(String tipoSuscripcion) {
		this.tipoSuscripcion = tipoSuscripcion;
	}
	/*********Servicios***/
//	public void comprarPPV() {
//
//		DatosJson dj= new DatosJson();
//		dj.addParameter("idFacebook", idFacebook);
//		dj.addParameter("titulo", );
//		dj.addParameter("empresa",nombreEmpresa);
//		
//    	Client client = ClientBuilder.newClient();
//    	Response postResponse = client
//    	.target(URL + "cliente/suscribir")
//    	.request().post(Entity.json(dj));
//    	
//    	//System.out.println(Entity.json(dj));
//    	
//    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
//    		System.out.println("Error al consumir mediante post.");
//    	}
//    	else{
//    		System.out.println("Se consumio correctamente mediante post.");
//    	}
//    	
//	}
	public void Suscribir() {

			DatosJson dj= new DatosJson();
			dj.addParameter("idFacebook", idFacebook);
			dj.addParameter("tipoSuscripcion", tipoSuscripcion);
			dj.addParameter("empresa",nombreEmpresa);
			
	    	Client client = ClientBuilder.newClient();
	    	Response postResponse = client
	    	.target(URL + "cliente/suscribir")
	    	.request().post(Entity.json(dj));
	    	
	    	//System.out.println(Entity.json(dj));
	    	
	    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
	    		System.out.println("Error al consumir mediante post.");
	    	}
	    	else{
	    		System.out.println("Se consumio correctamente mediante post.");
	    	}
	    	
	}
	public boolean chequearSuscripcion() {
		DatosJson dj= new DatosJson();
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("empresa",nombreEmpresa);
		
		
		System.out.println(idFacebook+"idFacebooksuscripcionnnnn");
		System.out.println(nombreEmpresa+"Empresaaaasuscripcionnnnn");
    	Client client = ClientBuilder.newClient();
    	Boolean postResponse = client
    	.target(URL+"cliente/verificarSuscripcionVigente")
    	.request().post(Entity.json(dj),Boolean.class);
    	
    	System.out.println(postResponse+"verificarSuscripcionnnnnnnnnn");
    	/*
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    	}*/
    	
		return postResponse.booleanValue();
	}
	public boolean isExitoPPV() {
		return exitoPPV;
	}
	public void setExitoPPV(boolean exitoPPV) {
		this.exitoPPV = exitoPPV;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getLala() {
		if (exitoPPV) {
			String jaja = titulo;
			//comprarPPV();
			RequestContext requestContext = RequestContext.getCurrentInstance();    
			requestContext.execute("openPopUpExito();");
		}
		return "";
	}
	public void setLala(String lala) {
		this.lala = lala;
	}
	
	
	
	

}
