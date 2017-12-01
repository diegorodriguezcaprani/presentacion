package servicios;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@ManagedBean(name="mainBean")
@SessionScoped
public class SessionEmpresaBean {
	
	private String call=null;
	private String nombreEmpresa;
	private String URL_Back = "http://localhost:8180/ServidorTsi2-0.0.1-SNAPSHOT/";
	
	@PostConstruct
	public void init(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
		ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
		String name = applicationContext.getApplicationName();
		nombreEmpresa = name.substring(1, name.length());
		System.out.println("Nombre empresa es: "+nombreEmpresa);
		
	}
	
	public boolean ismantel(){
		return "mantel".equals(nombreEmpresa.toLowerCase());
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getURL_Back() {
		return this.URL_Back;
	}

	public void setURL_Back(String uRL_Back) {
		this.URL_Back = uRL_Back;
	}
	
	
}
