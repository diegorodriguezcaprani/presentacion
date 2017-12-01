package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.primefaces.context.RequestContext;

import datatypes.DatosContenido;
import datatypes.DatosIdNombre;
import datatypes.DatosJson;
import datatypes.DatosTipoContenido;


@ManagedBean(name="contenidoView")
@ViewScoped
public class ContenidoBeanAdmin {
	
	@ManagedProperty(value = "#{mainBean.URL_Back}")
	private String URL_Back;
	
	@ManagedProperty(value = "#{mainBean.nombreEmpresa}")
	private String nombreEmpresa;
	
	String empresaSeleccionada;
	List<DatosContenido> contenidosempresa;
	
	private DatosIdNombre categoria;
	private String[] selectedCategorias;
	private List<DatosIdNombre> categorias;
	private List<String> categoriasStr;
	private List<String> categoriasSeleccionadas;
	private String categ;
	private DatosIdNombre selectedCate;
	String nombre_categoria;
	
	private String elencoi;
	private List<String> elencos;
	private String directori;
	private List<String> directores;
	private boolean payperview;
	private Double precioPayPerView;
	
	//CONTENIDOS
	private DatosContenido selectedCont;
	List<DatosContenido> contenidos;
	List<DatosContenido> contenidosvivo;
	List<String> contvivostr;
	List<DatosContenido> contenidoFiltrado;
	private DatosContenido contenido;
	private String new_nombrecontenido;
	String contenidoseleccionado;
	String URLcontenidovivo;
	String nombre_tipocontenido;
	
	//TIPOS CONTENIDO
	List<DatosTipoContenido> tiposcontenido;
	private DatosTipoContenido tipo;
	private String nombreTipoContenido;
	private List<String> atributosTipoContenido;
	private List<String> categoriasTipoContenido;
	private DatosTipoContenido selectedTipo;
	String nombre_atributo;
	List<String> nuevosatributos;
	
	@PostConstruct
	public void init(){
		System.out.println("La empresa es: "+nombreEmpresa);
		empresaSeleccionada = null;
		contenidosempresa = new ArrayList<DatosContenido>();
		
		tiposcontenido = new ArrayList<DatosTipoContenido>();
		tipo = new DatosTipoContenido();
		selectedTipo = new DatosTipoContenido();
		contenido = new DatosContenido();
		contenidos = new ArrayList<DatosContenido>();
		contenidosvivo = new ArrayList<DatosContenido>();
		contvivostr = new ArrayList<String>();
		
		selectedCate = new DatosIdNombre();
		categoria = new DatosIdNombre();
		categorias  = new ArrayList<DatosIdNombre>();

    	elencoi = null;
        elencos = new ArrayList<String>();
    	directori = null;
        directores = new ArrayList<String>();
        nombre_tipocontenido=null;
        new_nombrecontenido=null;
        nombre_categoria=null;
        nombre_atributo=null;
    	contenidoseleccionado = null;
    	URLcontenidovivo = null;
        atributosTipoContenido = new ArrayList<String>();
        categoriasTipoContenido = new ArrayList<String>();
        categoriasSeleccionadas = new ArrayList<String>();
        nuevosatributos = new ArrayList<String>();
        
        obtenerContenidosVivoPorHabilitar();
        
	}
	
	public void guardarContenido(){
    	Client client = ClientBuilder.newClient();
    	System.out.println("El titulo es: "+contenido.getTitulo());
    	System.out.println("La descripcion es: "+contenido.getDescripcion());
    	System.out.println("El tipo es: "+nombre_tipocontenido);
    	if (nombre_tipocontenido != null){
    		List<DatosIdNombre> catg = new ArrayList<DatosIdNombre>();
	    	for (DatosTipoContenido dtc: this.tiposcontenido){
	    		if (dtc.getNombre().equals(this.nombre_tipocontenido)){
	    			contenido.setTipoContenido(dtc);
	    			for(String cat: categoriasSeleccionadas){
	    				for(DatosIdNombre din: dtc.getCategorias()){
	    					if (din.getNombre().equals(cat)){
	    						catg.add(new DatosIdNombre(din.getId(),din.getNombre()));
	    					}
	    				}
	    			}
	    			break;
	    		}
	    	}
	    	contenido.setCategorias(catg);
    	}
    	contenido.setEmpresa(nombreEmpresa);
    	contenido.setUrl("./videoEnArchivo/"+nombreEmpresa+"/"+contenido.getTitulo());
    	//contenido.setUrl("hola");
    	//contenido.setElenco(elencos);
    	//contenido.setDirectores(directores);
        if (nombre_tipocontenido != null){
        	new_nombrecontenido = contenido.getTitulo();
        	if (payperview){
        		contenido.setPrecioPayPerView(precioPayPerView);
        	}
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
	    		reset("form-contenido:header-contenido");
	    	}
        }
	}
	
	public void habilitarContVIVO(){
			DatosJson in = new DatosJson();
			in.addParameter("titulo", contenidoseleccionado);
			System.out.println("Contenido a actualizar: "+contenidoseleccionado);
			in.addParameter("empresa", nombreEmpresa);
			System.out.println("Empresa a actualizar: "+nombreEmpresa);
			in.addParameter("url", URLcontenidovivo);
			System.out.println("URL a actualizar: "+URLcontenidovivo);
			
			Client client = ClientBuilder.newClient();
	    	Boolean habilitado = client
	    	.target(URL_Back +"/contenido/setearURL")
	    	.request().post(Entity.json(in),Boolean.class);
	    	
	    	boolean conthabilitado = habilitado.booleanValue();
	    	
	    	if (conthabilitado){
		    	List<String> actualizada = new ArrayList<String>();
		    	for(String cont: contvivostr){
		    		if (!cont.equals(contenidoseleccionado)){
		    			actualizada.add(cont);
		    		}
		    	}
		    	contvivostr = actualizada;
		    	URLcontenidovivo=null;
		    	contenidoseleccionado=null;
		    	
		    	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Contenido habilitado correctamente."));
		    	
	    	}
	    	else{
	    		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("No se ha podido habilitar su contenido."));
	    	}
	}
	
	public void obtenerContenidosVivoPorHabilitar(){
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> contenidos = client
    	.target(URL_Back+"/contenido/"+nombreEmpresa+"/contenidosParaTransmitir")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
    	this.contenidosvivo = contenidos;
    	this.contvivostr = toListCont(contenidos);
	}
	
	public void guardarContenidoVivo(){
    	Client client = ClientBuilder.newClient();
    	System.out.println("El titulo es: "+contenido.getTitulo());
    	System.out.println("La descripcion es: "+contenido.getDescripcion());
    	System.out.println("El tipo es: "+nombre_tipocontenido);
    	if (nombre_tipocontenido != null){
    		List<DatosIdNombre> catg = new ArrayList<DatosIdNombre>();
	    	for (DatosTipoContenido dtc: this.tiposcontenido){
	    		if (dtc.getNombre().equals(this.nombre_tipocontenido)){
	    			contenido.setTipoContenido(dtc);
	    			for(String cat: categoriasSeleccionadas){
	    				for(DatosIdNombre din: dtc.getCategorias()){
	    					if (din.getNombre().equals(cat)){
	    						catg.add(new DatosIdNombre(din.getId(),din.getNombre()));
	    					}
	    				}
	    			}
	    			break;
	    		}
	    	}
	    	contenido.setCategorias(catg);
    	}
    	contenido.setEmpresa(nombreEmpresa);
    	contenido.setUrl("./video/"+contenido.getTitulo());
    	//contenido.setUrl("hola");
    	//contenido.setElenco(elencos);
    	//contenido.setDirectores(directores);
        if (nombre_tipocontenido != null){
        	new_nombrecontenido = contenido.getTitulo();
        	if (payperview){
        		contenido.setPrecioPayPerView(precioPayPerView);
        	}
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
	    		reset("form-contenido:header-contenido");
	    	}
        }
	}
	
	
	public String salvar(){
		new_nombrecontenido = contenido.getTitulo();
		System.out.println("Seteado nombre con: "+new_nombrecontenido);
		guardarContenido();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.getRequestMap().put("titulo", new_nombrecontenido);
		return "adminSubir";
	}
	
	public List<String> toListCont(List<DatosContenido> array){
		List<String> lista = new ArrayList<String>();
		for(DatosContenido c: array){
			lista.add(c.getTitulo());
		}
		return lista;
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
	
	public List<String> toListStr(List<DatosIdNombre> datos){
		List<String> lst = new ArrayList<String>();
		for(DatosIdNombre d: datos){
			lst.add(d.getNombre());
		}
		return lst;
	}
	
	public List<DatosIdNombre> toListDatIdNom(List<String> datos){
		List<DatosIdNombre> lst = new ArrayList<DatosIdNombre>();
		for(String d: datos){
			lst.add(new DatosIdNombre(1,d));
		}
		return lst;
	}
	
	public boolean guardarTipoContenido(){
		System.out.println("NOMBRE de tipo: "+tipo.getNombre());
		tipo.setAtributos(toListDatIdNom(atributosTipoContenido));
		tipo.setCategorias(toListDatIdNom(categoriasTipoContenido));
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/contenido/crearTipoContenido")
    	.request().post(Entity.json(tipo));
    	
    	if ((postResponse.getStatus() != 201) && (postResponse.getStatus() != 200)){
    		System.out.println("Error al consumir mediante post.");
    	}
    	else{
    		System.out.println("Se consumio correctamente mediante post.");
    		tipo = new DatosTipoContenido();
    		atributosTipoContenido = new ArrayList<String>();
    		categoriasTipoContenido = new ArrayList<String>();
            nombre_categoria=null;
            nombre_atributo=null;
    		reset("nuevodialogo");
    	}
    	
		return true;
	}
	
	public void agregarNewAtributo(){
		System.out.println("Pase por aca!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		nuevosatributos.add(nombre_atributo);
	}
	
	public String changeState(DatosContenido cont){
		String accion = "/desbloquear";
		if (!cont.isBloqueado()){
			accion = "/bloquear";
		}
    	DatosJson dj = new DatosJson();
    	dj.addParameter("titulo", cont.getTitulo());
    	System.out.println("MI TITULO: "+cont.getTitulo());
    	dj.addParameter("empresa", cont.getEmpresa());
    	System.out.println("MI EMPRESA: "+cont.getEmpresa());
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/contenido"+accion)
    	.request().post(Entity.json(dj));
    	
    	return null;
    }
	
	public String changeDest(DatosContenido cont){
		String accion = "/quitarDestacado";
		if (!cont.isDestacado()){
			accion = "/destacar";
		}
    	DatosJson dj = new DatosJson();
    	dj.addParameter("titulo", cont.getTitulo());
    	System.out.println("MI TITULO: "+cont.getTitulo());
    	dj.addParameter("empresa", cont.getEmpresa());
    	System.out.println("MI EMPRESA: "+cont.getEmpresa());
    	Client client = ClientBuilder.newClient();
    	Response postResponse = client
    	.target(URL_Back +"/contenido"+accion)
    	.request().post(Entity.json(dj));
    	
    	return null;
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
	
	public void onEmpresaChange(){
		if (empresaSeleccionada != null){
		contenidosempresa = obtenerContenidosPorEmpresa(empresaSeleccionada);
		}
		else{
			contenidosempresa = new ArrayList<DatosContenido>();
		}
	}
	
	public List<DatosContenido> obtenerContenidosPorEmpresa(String empresa){
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> contenidos = client
    	.target(URL_Back+"/contenido/"+empresa+"/obtenerContenidos")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosContenido>>() {});
		return contenidos;
	}
	
	public List<DatosContenido> obtenerContenidos(){
		Client client = ClientBuilder.newClient();
    	List<DatosContenido> contenidos = client
    	.target(URL_Back+"/contenido/"+nombreEmpresa+"/obtenerContenidos")
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
	
	public void seleccionarTipoContenido(DatosTipoContenido tipocont){
		nombreTipoContenido = tipocont.getNombre();
		atributosTipoContenido = toListStr(tipocont.getAtributos());
		categoriasTipoContenido = toListStr(tipocont.getCategorias());
		System.out.println("PASE POR ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}
	
	public void onTipoChange() {
        if((nombre_tipocontenido !=null) && (!nombre_tipocontenido.equals(""))){
        	for(DatosTipoContenido dtc: tiposcontenido){
        		if (dtc.getNombre().equals(nombre_tipocontenido)){
        			categoriasStr = toListStr(dtc.getCategorias());
        			break;
        		}
        	}
        }
        else{
            categorias = new ArrayList<DatosIdNombre>();
            categoriasStr = new ArrayList<String>();
            categoriasSeleccionadas = new ArrayList<String>();
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

	public List<String> getAtributosTipoContenido() {
		return atributosTipoContenido;
	}

	public void setAtributosTipoContenido(List<String> atributosTipoContenido) {
		this.atributosTipoContenido = atributosTipoContenido;
	}

	public String[] getSelectedCategorias() {
		return selectedCategorias;
	}

	public void setSelectedCategorias(String[] selectedCategorias) {
		this.selectedCategorias = selectedCategorias;
	}

	public List<String> getCategoriasTipoContenido() {
		return categoriasTipoContenido;
	}

	public void setCategoriasTipoContenido(List<String> categoriasTipoContenido) {
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

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

	public String getNombre_atributo() {
		return nombre_atributo;
	}

	public void setNombre_atributo(String nombre_atributo) {
		this.nombre_atributo = nombre_atributo;
	}

	public List<String> getNuevosatributos() {
		return nuevosatributos;
	}

	public void setNuevosatributos(List<String> nuevosatributos) {
		this.nuevosatributos = nuevosatributos;
	}

	public boolean isPayperview() {
		return payperview;
	}

	public void setPayperview(boolean payperview) {
		this.payperview = payperview;
	}

	public List<String> getCategoriasStr() {
		return categoriasStr;
	}

	public void setCategoriasStr(List<String> categoriasStr) {
		this.categoriasStr = categoriasStr;
	}

	public String getCateg() {
		return categ;
	}

	public void setCateg(String categ) {
		this.categ = categ;
	}

	public String getNew_nombrecontenido() {
		return new_nombrecontenido;
	}

	public void setNew_nombrecontenido(String new_nombrecontenido) {
		this.new_nombrecontenido = new_nombrecontenido;
	}

	public String getContenidoseleccionado() {
		return contenidoseleccionado;
	}

	public void setContenidoseleccionado(String contenidoseleccionado) {
		this.contenidoseleccionado = contenidoseleccionado;
	}

	public String getURLcontenidovivo() {
		return URLcontenidovivo;
	}

	public void setURLcontenidovivo(String uRLcontenidovivo) {
		URLcontenidovivo = uRLcontenidovivo;
	}

	public Double getPrecioPayPerView() {
		return precioPayPerView;
	}

	public void setPrecioPayPerView(Double precioPayPerView) {
		this.precioPayPerView = precioPayPerView;
	}

	public List<DatosContenido> getContenidosvivo() {
		return contenidosvivo;
	}

	public void setContenidosvivo(List<DatosContenido> contenidosvivo) {
		this.contenidosvivo = contenidosvivo;
	}

	public List<String> getContvivostr() {
		return contvivostr;
	}

	public void setContvivostr(List<String> contvivostr) {
		this.contvivostr = contvivostr;
	}

	public List<String> getCategoriasSeleccionadas() {
		return categoriasSeleccionadas;
	}

	public void setCategoriasSeleccionadas(List<String> categoriasSeleccionadas) {
		this.categoriasSeleccionadas = categoriasSeleccionadas;
	}

	public String getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(String empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	public List<DatosContenido> getContenidosempresa() {
		return contenidosempresa;
	}

	public void setContenidosempresa(List<DatosContenido> contenidosempresa) {
		this.contenidosempresa = contenidosempresa;
	}
	
	
}
