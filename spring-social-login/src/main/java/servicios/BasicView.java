package servicios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name="dtBasicView")
@ViewScoped
public class BasicView implements Serializable {
	private String URL_Back = "http://localhost:8080/";
	
	private List<Usuario> usrs;
	
	private List<Usuario> filteredUsrs;
	
	/*private Recurso recurso;
	private List<Recurso> recursos;
	*/
	
	private List<Usuario> selectedUsrs;
	
	
    @ManagedProperty("#{usrService}")
    private UsrService service;
 
    @PostConstruct
    public void init() {
    	
        //usrs = service.createUsrs(25);
    	usrs = new ArrayList<Usuario>();
    	//ESTAS DOS OPERACIONES HAY QUE COMENTARLAS SI NO TE DA ERROR SI NO TENES EL SERVICIO PUBLICADO.
    	//obtenerRecurso();
    	//obtenerRecursos();
    	//RETORNA TODOS LOS CONTENIDOS
    	//contenidos = obtenerContenidos();
    	
    	
    	
        
//        elencos.add("jason statham");
//        elencos.add("scarlett johanson");
//        
//        directores.add("fede alvarez");
//        
//        Contenido cont = new Contenido();
//        cont.setTitulo("aaaaaassssdds");
//        cont.setDescripcion("sdadsa");
//        cont.setElenco(elencos);
//        cont.setDirectores(directores);
//        cont.setPortada("dsa");
//        
//    	Client client = ClientBuilder.newClient();
//    	Response postResponse = client
//    	.target("http://localhost:8180/ServidorTsi2-0.0.1-SNAPSHOT/contenido/")
//    	.request().post(Entity.json(cont));
    }
     
    public List<Usuario> retornarUsuarios(){
    	this.usrs  = service.createUsrs(25);
    	return usrs;
    }
    
    public List<Usuario> getUsrs() {
        return usrs;
    }
 
    public void setService(UsrService service) {
        this.service = service;
    }

	public List<Usuario> getFilteredUsrs() {
		return filteredUsrs;
	}

	public void setFilteredUsrs(List<Usuario> filteredUsrs) {
		this.filteredUsrs = filteredUsrs;
	}
    /*
    //RETORNA UN RECURSO SOLO. PRUEBA
    private void obtenerRecurso(){
    	Client client = ClientBuilder.newClient();
    	Recurso recurso = client
    	.target("http://localhost:3000/recursos/1")
    	.request(MediaType.APPLICATION_JSON).get(Recurso.class);
    	this.recurso = recurso;
    }
    
    //RETORNA TODOS LOS CONTENIDOS
    public void obtenerRecursos(){
		Client client = ClientBuilder.newClient();
    	List<Recurso> recursos = client
    	.target("http://localhost:3000/recursos/")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Recurso>>() {});
		
		this.recursos = recursos;
	}
    
    //RETORNA TODOS LOS CONTENIDOS
    public List<Contenido> obtenerContenidos(){
		Client client = ClientBuilder.newClient();
    	List<Contenido> contenidos = client
    	.target("http://172.16.145.186:8080/ServidorTsi2-0.0.1-SNAPSHOT/contenido/contenidos/")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Contenido>>() {});
			this.contenidos = contenidos;
		return contenidos;
	}
    
    //GUARDA UN RECURSO SOLO. PRUEBA
    public void actionSaveContain(){
        //private String nombre;
        //private String descripcion;
        //private String fecha_compra;
        //private int id;
    	Recurso guardar = new Recurso(); //= new Recurso("nombre", "descripcion", "20171107", 0);
    	guardar.setNombre("nombre");
    	guardar.setFecha_compra("20171107");
    	guardar.setDescripcion("descripcion");
    	guardar.setId(14);

    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target("http://localhost:3000/recursos/")
    	.request().post(Entity.json(guardar));
    	
    	if (postResponse.getStatus() != 201){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    	}
    }
    
    //GUARDA UN CONTENIDO SOLO. (contenido tiene titulo, descripcion, portada. Lo demas vacio o null.)
    public boolean guardarContenido(){
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target("http://172.16.145.186:8080/ServidorTsi2-0.0.1-SNAPSHOT/contenido/")
    	.request().post(Entity.json(contenido));
    	
    	if (postResponse.getStatus() != 201){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    	}
    	
		return true;
	}
    */
	public List<Usuario> getSelectedUsrs() {
		return selectedUsrs;
	}

	public void setSelectedUsrs(List<Usuario> selectedUsrs) {
		this.selectedUsrs = selectedUsrs;
	}
	/*
	public void createNew() {
        if(elencos.contains(elencoi)) {
            FacesMessage msg = new FacesMessage("Dublicated", "This book has already been added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
        else {
            elencos.add(elencoi);
            elencoi = null;
        }
    }*/	
	
}
