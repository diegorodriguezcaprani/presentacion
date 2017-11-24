package datatypes;

import java.util.LinkedList;
import java.util.List;

public class DatosJson {

	private List<DatosParametro> parametros;

	public DatosJson(List<DatosParametro> parametros) {
		super();
		this.parametros = parametros;
	}

	public DatosJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<DatosParametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<DatosParametro> parametros) {
		this.parametros = parametros;
	}
	
	public String getParameter(String nombreParam) {
		if(parametros != null) {
			for(int i=0; i<parametros.size();i++) {
				if(parametros.get(i).getNombre().equals(nombreParam)) {
					return parametros.get(i).getValor();
				}
			}
		} 
		return null;
	}
	
	public boolean addParameter(String nombre, String valor) {
		DatosParametro param = new DatosParametro(nombre, valor);
		if(parametros == null) {
			parametros = new LinkedList<DatosParametro>();
			
		} 
		return parametros.add(param);
	}
	
	public boolean removeParameter(DatosParametro param) {
		if(parametros != null) {
			for(int i=0; i<parametros.size();i++) {
				if(parametros.get(i).getNombre().equals(param.getNombre())) {
					parametros.remove(i);
					return true;
				}
			}
		} 
		return false;
	}
	
}
