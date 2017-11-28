package servicios;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="subirView")
@ViewScoped
public class SubirContenido {

	@ManagedProperty(value = "#{param.urlvideo}")
	private String urlvideo;
	
}
