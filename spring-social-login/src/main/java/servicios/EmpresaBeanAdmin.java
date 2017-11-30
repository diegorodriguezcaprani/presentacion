package servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datatypes.DatosCliente;
import datatypes.DatosJson;

@ManagedBean(name="empresaView")
@ViewScoped
public class EmpresaBeanAdmin {

	@ManagedProperty(value = "#{mainBean.URL_Back}")
	private String URL_Back;
	
	@ManagedProperty(value = "#{mainBean.nombreEmpresa}")
	private String nombreEmpresa;
	
	String empresa;
	String selectedEmpresa;
	
	@PostConstruct
	public void init(){
		empresa=null;
		selectedEmpresa=null;
		
	}
	
	public List<String> obtenerEmpresas(){
		Client client = ClientBuilder.newClient();
		List<String> empresas = client
    	.target(URL_Back+"/empresa/obtenerEmpresas")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>() {});
		return empresas;
	}
	
	public void eliminarEmpresa(String empresa){
		
	}
	
	public void crearNuevaEmpresa() 
	{
		String empresa = this.empresa;
		File archivo = new File ("../standalone/deployments/mantel.war");
		//System.out.println("initial contex"+initialContext.lookup("java:module/ModuleName")); 
		System.out.println("adentro del archivo");
		File nuevaEmpresa = new File("../standalone/deployments/"+ empresa +".war");
		try {
		Files.copy(archivo.toPath(), nuevaEmpresa.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		File dirEmpresa = new File("../../Empresas/"+ empresa);
		if (!dirEmpresa.exists())
		{
		System.out.println("No existe la empresa");
		dirEmpresa.mkdirs();
		}
		System.out.println("voy a ejecutar el script Psql");
		Runtime rt = Runtime.getRuntime();
		       try {
		       	Process proc = rt.exec("psql -h localhost -U postgres -c \"create database "+empresa+";\"");
				proc.waitFor();
				crearEmpresaBack(empresa);
		} catch (InterruptedException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void crearEmpresaBack(String name){
		DatosJson dj = new DatosJson();
    	dj.addParameter("empresa", name);
    	System.out.println("MI EMPRESA: "+name);
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/empresa/agregarEmpresa")
    	.request().post(Entity.json(dj));
	}


	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSelectedEmpresa() {
		return selectedEmpresa;
	}

	public void setSelectedEmpresa(String selectedEmpresa) {
		this.selectedEmpresa = selectedEmpresa;
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
	
	
}
