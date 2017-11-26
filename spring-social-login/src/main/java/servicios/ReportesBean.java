package servicios;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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
        zoomModel.setTitle("Visualizaciones");
        zoomModel.setZoom(true);
        zoomModel.setLegendPosition("e");
        Axis yAxis = zoomModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        yAxis.setLabel("Cantidad de visualizaciones");
        Axis xAxis = zoomModel.getAxis(AxisType.X);
        xAxis.setLabel("Meses/Año");
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Hombres");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Mujeres");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Hombres");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Mujeres");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
    
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Reportes anuales");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Año");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad usuarios");
        yAxis.setMin(0);
        yAxis.setMax(200);
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
