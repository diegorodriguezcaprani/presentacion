package servicios;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import datatypes.DatosContenido;

@ManagedBean(name="favoritosBean")
@ViewScoped
public class FavoritosBean {
	@ManagedProperty(value = "#{homeBean.contenidosFavoritos}") //contenidos del beanhome
	private List <DatosContenido> contenidosFavoritos;
//	@ManagedProperty(value = "#{homeBean.home}") //contenidos del beanhome
//	private boolean home;
	private List <String> imageURLs = new ArrayList<String>();
	
	@PostConstruct
    public void init() {
		System.out.println("_holaaaaaBeanFavoritos");
		imageURLs.add("http://gfbrobot.com/wp-content/uploads/2011/08/true-blood3.png");
		imageURLs.add("https://play3r.net/wp-content/uploads/2015/11/TTG_GoT_Logo.png");
		imageURLs.add("https://static1.squarespace.com/static/56f5a63a2eeb81396607a47f/t/58e2d10046c3c48787772858/1491259668498.png");
		imageURLs.add("http://posterposse.com/wp-content/uploads/2016/12/Screen-Shot-2016-12-05-at-9.53.19-AM.png");
		imageURLs.add("http://cdn.gospelherald.com/data/images/full/17002/breaking-bad.png");
		imageURLs.add("http://gfbrobot.com/wp-content/uploads/2011/08/true-blood3.png");
		imageURLs.add("https://play3r.net/wp-content/uploads/2015/11/TTG_GoT_Logo.png");
		imageURLs.add("https://static1.squarespace.com/static/56f5a63a2eeb81396607a47f/t/58e2d10046c3c48787772858/1491259668498.png");
		imageURLs.add("http://posterposse.com/wp-content/uploads/2016/12/Screen-Shot-2016-12-05-at-9.53.19-AM.png");
		imageURLs.add("http://cdn.gospelherald.com/data/images/full/17002/breaking-bad.png");
    }

	public FavoritosBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<DatosContenido> getContenidosFavoritos() {
		return contenidosFavoritos;
	}

	public void setContenidosFavoritos(List<DatosContenido> contenidosFavoritos) {
		this.contenidosFavoritos = contenidosFavoritos;
	}

	public List<String> getImageURLs() {
		return imageURLs;
	}

	public void setImageURLs(List<String> imageURLs) {
		this.imageURLs = imageURLs;
	}

	public FavoritosBean(List<String> imageURLs) {
		this.imageURLs = imageURLs;
	}
}