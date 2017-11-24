package servicios;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="verContenidoBean")
@RequestScoped
public class VerContenidoBean {
	@ManagedProperty(value = "#{param.urlvideo}")
	private String urlvideo;
	@ManagedProperty(value = "#{param.enVivo}")
	private boolean enVivo;
	
	private String videoType;

	public boolean isEnVivo() {
		return enVivo;
	}


	public void setEnVivo(boolean enVivo) {
		this.enVivo = enVivo;
	}


	public String getVideoType() {
		return videoType;
	}


	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}


	public String getUrlvideo() {
		return urlvideo;
	}


	public void setUrlvideo(String urlvideo) {
		this.urlvideo = urlvideo;
	}

	@PostConstruct
    public void init() {
		if (this.isEnVivo()) {
			this.videoType= "video/ogg";
		}else {
			this.videoType= "video/mp4";
		}
	}
}