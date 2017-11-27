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

import datatypes.DatosCliente;
import datatypes.DatosContenido;
import datatypes.DatosJson;

@ManagedBean(name="dtBasicView")
@ViewScoped
public class BasicView implements Serializable {
	
	@ManagedProperty(value = "#{mainBean.URL_Back}")
	private String URL_Back;
	
	@ManagedProperty(value = "#{mainBean.nombreEmpresa}")
	private String nombreEmpresa;
	
	private DatosCliente usuarioSeleccionado;
	private List<DatosCliente> usrs;
	
	private List<DatosCliente> filteredUsrs;
	
	private List<DatosCliente> selectedUsrs;
 
    @PostConstruct
    public void init() {
    	usrs = new ArrayList<DatosCliente>();
    	
    }
     
    public List<DatosCliente> retornarUsuarios(){
    	Client client = ClientBuilder.newClient();
    	List<DatosCliente> usuarios = client
    	.target(URL_Back+"/cliente/"+nombreEmpresa+"/obtenerClientes")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosCliente>>() {});
		return usuarios;
    }
    
    public String changeState(DatosCliente cli){
    	String accion = "/desbloquear";
		if (!cli.isBloqueado()){
			accion = "/bloquear";
		}
		System.out.println("ID DE FACEBOOK: "+cli.getidfacebook());
    	DatosJson dj = new DatosJson();
    	dj.addParameter("idFacebook", cli.getidfacebook());
    	System.out.println("EMPRESA: "+cli.getNombreEmpresa());
    	dj.addParameter("empresa", nombreEmpresa);
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/cliente"+accion)
    	.request().post(Entity.json(dj));
    	
    	return null;
    }
    
    public List<DatosCliente> getUsrs() {
        return usrs;
    }

	public List<DatosCliente> getFilteredUsrs() {
		return filteredUsrs;
	}

	public void setFilteredUsrs(List<DatosCliente> filteredUsrs) {
		this.filteredUsrs = filteredUsrs;
	}
    
	public List<DatosCliente> getSelectedUsrs() {
		return selectedUsrs;
	}

	public void setSelectedUsrs(List<DatosCliente> selectedUsrs) {
		this.selectedUsrs = selectedUsrs;
	}

	public String getURL_Back() {
		return URL_Back;
	}

	public void setURL_Back(String uRL_Back) {
		URL_Back = uRL_Back;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public DatosCliente getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(DatosCliente usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	
}
