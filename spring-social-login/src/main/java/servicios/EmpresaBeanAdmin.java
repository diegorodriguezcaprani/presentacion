package servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="empresaView")
@ViewScoped
public class EmpresaBeanAdmin {

	String empresa;
	String selectedEmpresa;
	
	@PostConstruct
	public void init(){
		empresa=null;
		selectedEmpresa=null;
		
	}
	
	public List<String> obtenerEmpresas(){
		return null;
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
		} catch (InterruptedException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
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
	
	
}
