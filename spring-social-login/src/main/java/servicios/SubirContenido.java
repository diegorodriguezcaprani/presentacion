package servicios;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="subirView")
@ViewScoped
public class SubirContenido {
	
	private String nombreContenido;
	
	@PostConstruct
	public void init(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        this.nombreContenido = ec.getRequestMap().get("titulo").toString();
	}

	public String getNombreContenido() {
		return nombreContenido;
	}

	public void setNombreContenido(String nombreContenido) {
		this.nombreContenido = nombreContenido;
	}
	
	
}
