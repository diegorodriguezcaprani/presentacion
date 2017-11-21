package servicios;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="verContenidoBean")
@RequestScoped
public class VerContenidoBean {
	@ManagedProperty(value = "#{param.urlvideo}")// t�tulo
	private String urlvideo;
	

	public String getUrlvideo() {
		return urlvideo;
	}


	public void setUrlvideo(String urlvideo) {
		this.urlvideo = urlvideo;
	}


	@PostConstruct
    public void init() {
	
	}
}