package servicios;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import datatypes.DatosIdNombre;
import datatypes.DatosIngresoMensual;
import datatypes.DatosPuntuacionContenido;

@ManagedBean(name="reportesView")
@ViewScoped
public class ReportesBean {
	@ManagedProperty(value = "#{mainBean.URL_Back}")
	private String URL_Back;
	
	@ManagedProperty(value = "#{mainBean.nombreEmpresa}")
	private String nombreEmpresa;
	
	private LineChartModel zoomModel;
	
	private BarChartModel barModel;
	
	@PostConstruct
	public void init() {
		createZoomModel();
    	createBarModel();
	}
	
	public LineChartModel getZoomModel() {
        return zoomModel;
    }
	
    public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public void setZoomModel(LineChartModel zoomModel) {
		this.zoomModel = zoomModel;
	}

	private void createZoomModel() {
        zoomModel = initLinearModel();
        zoomModel.setTitle("Balance anual");
        zoomModel.setZoom(true);
        zoomModel.setLegendPosition("e");
        zoomModel.setShowPointLabels(true);
        zoomModel.setSeriesColors("58BA27");
        
        zoomModel.getAxes().put(AxisType.X, new CategoryAxis("Mes/Año"));
        Axis yAxis = zoomModel.getAxis(AxisType.Y);
        yAxis.setLabel("Ingresos");
        yAxis.setMin(0);
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries series1 = new ChartSeries();
        series1.setLabel("Ingresos");
        
        List<DatosIngresoMensual> ingresos = obtenerIngresosUltimoAnio();
        for (int i=0; i<12; i++){
	        for(DatosIngresoMensual dim : ingresos){
	        	if (dim.getPosicion() == i){
	        		series1.set(dim.getMes()+"/"+dim.getAnio(), ((int)dim.getIngreso()));
	        		System.out.println("Clave: " + dim.getMes()+"/"+dim.getAnio() + " Valor: "+((int)dim.getIngreso()));
	        		break;
	        	}
	        }
        }
        
        /*series1.set("2019", 2);
        series1.set("2015", 1);
        series1.set("2016", 3);
        series1.set("2017", 6);
        series1.set("2018", 8);
         */
        
        model.addSeries(series1);
         
        return model;
    }
    
    public List<DatosIngresoMensual> obtenerIngresosUltimoAnio(){
    	Client client = ClientBuilder.newClient();
    	List<DatosIngresoMensual> ingresos = client
    	.target(URL_Back+"/empresa/"+nombreEmpresa+"/obtenerIngresosSuscripcionesMensuales")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosIngresoMensual>>() {});
    	return ingresos;
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Top five");
        
        List<DatosPuntuacionContenido> topfive = obtenerTopFive();
        for(DatosPuntuacionContenido dpc : topfive){
    		boys.set(dpc.getTitulo(), ((int)dpc.getPuntuacion()));
    		System.out.println("Clave: " + dpc.getTitulo() + " Valor: "+((int)dpc.getPuntuacion()));
        }
        
        /*
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 		*/
        
        model.addSeries(boys);
         
        return model;
    }
    
    public List<DatosPuntuacionContenido> obtenerTopFive(){
    	Client client = ClientBuilder.newClient();
    	List<DatosPuntuacionContenido> topten = client
    	.target(URL_Back+"/empresa/"+nombreEmpresa+"/obtenerTopFiveContenidos")
    	.request(MediaType.APPLICATION_JSON).get(new GenericType<List<DatosPuntuacionContenido>>() {});
    	return topten;
    }
    
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Top five contenidos");
        barModel.setLegendPosition("ne");
        barModel.setSeriesColors("FFCC33");
        
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Contenidos");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Puntuación");
        yAxis.setMin(0);
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
