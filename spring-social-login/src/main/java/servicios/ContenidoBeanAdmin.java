package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.primefaces.context.RequestContext;

import datatypes.DatosContenido;
import datatypes.DatosIdNombre;
import datatypes.DatosTipoContenido;


@ManagedBean(name="contenidoView")
@ViewScoped
public class ContenidoBeanAdmin {
	
	@ManagedProperty(value = "#{mainBean.URL_Back}")
	private String URL_Back;
	
	@ManagedProperty(value = "#{mainBean.nombreEmpresa}")
	private String nombreEmpresa;
	
	private Map<Integer,List<DatosIdNombre>> data = new HashMap<Integer, List<DatosIdNombre>>();
	private DatosIdNombre categoria;
	private String[] selectedCategorias;
	private List<DatosIdNombre> categorias;
	private DatosIdNombre selectedCate;
	
	private String elencoi;
	private List<String> elencos;
	private String directori;
	private List<String> directores;
	
	//CONTENIDOS
	private DatosContenido selectedCont;
	List<DatosContenido> contenidos;
	List<DatosContenido> contenidoFiltrado;
	private DatosContenido contenido;
	String nombre_tipocontenido;
	
	//TIPOS CONTENIDO
	List<DatosTipoContenido> tiposcontenido;
	private DatosTipoContenido tipo;
	private String nombreTipoContenido;
	private String atributosTipoContenido;
	private String categoriasTipoContenido;
	private DatosTipoContenido selectedTipo;
	
	@PostConstruct
	public void init(){
		System.out.println("La empresa es: "+nombreEmpresa);
		
		tiposcontenido = new ArrayList<DatosTipoContenido>();
		tipo = new DatosTipoContenido();
		selectedTipo = new DatosTipoContenido();
		contenido = new DatosContenido();
		contenidos = new ArrayList<DatosContenido>();
		
		selectedCate = new DatosIdNombre();
		categoria = new DatosIdNombre();
		categorias  = new ArrayList<DatosIdNombre>();

    	elencoi = null;
        elencos = new ArrayList<String>();
    	directori = null;
        directores = new ArrayList<String>();
        nombre_tipocontenido=null;
	}
	
	public void guardarContenido(){
    	Client client = ClientBuilder.newClient();
    	System.out.println("El titulo es: "+contenido.getTitulo());
    	System.out.println("La descripcion es: "+contenido.getDescripcion());
    	System.out.println("El tipo es: "+contenido.getTipoContenido());
    	System.out.println("El nombre del contenido es: "+this.nombre_tipocontenido);
    	if (nombre_tipocontenido != null){
	    	for (DatosTipoContenido dtc: this.tiposcontenido){
	    		if (dtc.getNombre().equals(this.nombre_tipocontenido)){
	    			contenido.setTipoContenido(dtc);
	    			break;
	    		}
	    	}
    	}
    	contenido.setEmpresa("Fox");
    	//contenido.setUrl("hola");
    	//contenido.setElenco(elencos);
    	//contenido.setDirectores(directores);
    	Response postResponse = client
    	.target(URL_Back + "/contenido/agregarContenido")
    	.request().post(Entity.json(contenido));
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    		contenido = new DatosContenido();
    		elencos = new ArrayList<String>();
    		directores = new ArrayList<String>();
    		//reset("header-contenido");
    	}
	}
	
	public String salvar(){
		guardarContenido();
		return "subir";
	}
	
	public List<String> toList(List<DatosTipoContenido> array){
		List<String> lista = new ArrayList<String>();
		for(DatosTipoContenido c: array){
			lista.add(c.getNombre());
		}
		return lista;
	}
	
	public String toStr(List<String> lista){
		String dev = null;
		for(String l: lista){
			dev =dev+","+l;
		}
		return dev;
	}
	
	public boolean guardarTipoContenido(){
		System.out.println("NOMBRE de tipo: "+tipo.getNombre());
		System.out.println("ATRIBUTOS de tipo: "+tipo.getAtributos());
		tipo.setAtributos(null);
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/contenido/tiposcontenido/"+tipo.getNombre())
    	.request().post(Entity.json(tipo));
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    		tipo = new DatosTipoContenido();
    		reset("nuevodialogo");
    	}
    	
		return true;
	}
	
	public void changeState(){
    	boolean state = selectedCont.isBloqueado();
    	selectedCont.setBloqueado(!state);
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/contenido/bloquear")
    	.request().post(Entity.json(selectedCont));
    }
	
	public boolean guardarCategoria(){
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/contenido/categorias/")
    	.request().post(Entity.json(categoria));
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		categoria = new DatosIdNombre();
    		reset("nuevodialogo");
    		System.out.println("Se consumio correctamente mediante post.");
    	}
    	
		return true;
	}
	
	public List<DatosContenido> obtenerContenidos(){
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> contenidos = client
    	.target(URL_Back+"/contenido/Mantel/obtenerContenidos")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
			this.contenidos = contenidos;
		return contenidos;
	}
	
	public List<DatosTipoContenido> obtenerTiposContenidos(){
		Client client = ClientBuilder.newClient();
    	List<DatosTipoContenido> tiposcontenidos = client
    	.target(URL_Back+"/contenido/tipoContenido")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosTipoContenido>>() {});
			this.tiposcontenido = tiposcontenidos;
		return tiposcontenido;
	}
	
	public List<String> obtenerStringTiposContenidos(){
		return toList(obtenerTiposContenidos());
	}
	
	
	public List<DatosIdNombre> obtenerCategorias(){
		Client client = ClientBuilder.newClient();
    	List<DatosIdNombre> categorias = client
    	.target(URL_Back+"/contenido/getCategorias")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosIdNombre>>() {});
		this.categorias = categorias;
		return categorias;
	}
	
	public List<DatosIdNombre> obtenerCategoriasPorTipo(String nombTipo){
		Client client = ClientBuilder.newClient();
		System.out.println(URL_Back +"/contenido/getCategoriasTipoContenido");
		System.out.println("Paso: "+Entity.text(nombTipo));
		Response postResponse = client
		    	.target(URL_Back +"/contenido/getCategoriasTipoContenido")
		    	.request().post(Entity.text(nombTipo));
		System.out.println("Entity respuesta: "+postResponse.getEntity());
		List<DatosIdNombre> categorias = postResponse.readEntity(new GenericType<List<DatosIdNombre>>() {});
		if (!categorias.isEmpty()){
			this.categorias = categorias;
		}
		return categorias;
	}

	public boolean eliminarCategoria(int i){
		System.out.println("ID categoria: "+i);
		System.out.println("NOMBRE categoria: "+categoria.getNombre());
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/contenido/categorias/"+i)
    	.request().delete();
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante delete.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante delete.");
    	}
    	
		return true;
	}
	
	public void onTipoChange() {
        if((contenido.getTipoContenido() !=null) && (!contenido.getTipoContenido().equals(""))){
            categorias = contenido.getTipoContenido().getCategorias();
        }
        else{
            categorias = new ArrayList<DatosIdNombre>();
        }
    }
	
	public void reset(String lugar) {
        RequestContext.getCurrentInstance().reset(lugar);
    }

	public DatosIdNombre getCategoria() {
		return categoria;
	}

	public void setCategoria(DatosIdNombre categoria) {
		this.categoria = categoria;
	}

	public List<DatosIdNombre> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<DatosIdNombre> categorias) {
		this.categorias = categorias;
	}

	public String getElencoi() {
		return elencoi;
	}

	public void setElencoi(String elencoi) {
		this.elencoi = elencoi;
	}

	public List<String> getElencos() {
		return elencos;
	}

	public void setElencos(List<String> elencos) {
		this.elencos = elencos;
	}

	public String getDirectori() {
		return directori;
	}

	public void setDirectori(String directori) {
		this.directori = directori;
	}

	public List<String> getDirectores() {
		return directores;
	}

	public void setDirectores(List<String> directores) {
		this.directores = directores;
	}

	public List<DatosContenido> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List<DatosContenido> contenidos) {
		this.contenidos = contenidos;
	}

	public DatosContenido getContenido() {
		return contenido;
	}

	public void setContenido(DatosContenido contenido) {
		this.contenido = contenido;
	}

	public List<DatosContenido> getContenidoFiltrado() {
		return contenidoFiltrado;
	}

	public void setContenidoFiltrado(List<DatosContenido> contenidoFiltrado) {
		this.contenidoFiltrado = contenidoFiltrado;
	}

	public DatosContenido getSelectedCont() {
		return selectedCont;
	}

	public void setSelectedCont(DatosContenido selectedCont) {
		this.selectedCont = selectedCont;
	}

	public List<DatosTipoContenido> getTiposcontenido() {
		return tiposcontenido;
	}

	public void setTiposcontenido(List<DatosTipoContenido> tiposcontenido) {
		this.tiposcontenido = tiposcontenido;
	}

	public DatosTipoContenido getTipo() {
		return tipo;
	}

	public void setTipo(DatosTipoContenido tipo) {
		this.tipo = tipo;
	}

	public DatosIdNombre getSelectedCate() {
		return selectedCate;
	}

	public void setSelectedCate(DatosIdNombre selectedCate) {
		this.selectedCate = selectedCate;
	}

	public DatosTipoContenido getSelectedTipo() {
		return selectedTipo;
	}

	public void setSelectedTipo(DatosTipoContenido selectedTipo) {
		this.selectedTipo = selectedTipo;
	}
	
	public void updateTipoContenido(){
		this.tipo.setNombre(this.nombreTipoContenido);
		//this.tipo.setAtributos(Arrays.asList(this.atributosTipoContenido));
		guardarTipoContenido();
	}

	public String getNombreTipoContenido() {
		return nombreTipoContenido;
	}

	public void setNombreTipoContenido(String nombreTipoContenido) {
		this.nombreTipoContenido = nombreTipoContenido;
	}

	public String getAtributosTipoContenido() {
		return atributosTipoContenido;
	}

	public void setAtributosTipoContenido(String atributosTipoContenido) {
		this.atributosTipoContenido = atributosTipoContenido;
	}

	public String[] getSelectedCategorias() {
		return selectedCategorias;
	}

	public void setSelectedCategorias(String[] selectedCategorias) {
		this.selectedCategorias = selectedCategorias;
	}

	public String getCategoriasTipoContenido() {
		return categoriasTipoContenido;
	}

	public void setCategoriasTipoContenido(String categoriasTipoContenido) {
		this.categoriasTipoContenido = categoriasTipoContenido;
	}

	public String getNombre_tipocontenido() {
		return nombre_tipocontenido;
	}

	public void setNombre_tipocontenido(String nombre_tipocontenido) {
		this.nombre_tipocontenido = nombre_tipocontenido;
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
