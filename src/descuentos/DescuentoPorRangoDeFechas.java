package descuentos;

import java.util.Calendar;
import java.util.*;

public class DescuentoPorRangoDeFechas extends Descuento {

	protected List<Calendar> rangoFechasParaDescuento;

	public List<Calendar> getRangoFechasParaDescuento() {
		return rangoFechasParaDescuento;
	}
	public void setRangoFechasParaDescuento(List<Calendar> rangoFechasParaDescuento) {
		this.rangoFechasParaDescuento = rangoFechasParaDescuento;
	}
	
	public DescuentoPorRangoDeFechas(List<Calendar> rango, int porcentaje){
		this.setRangoFechasParaDescuento(rango);
		this.setPorcentajeDescontado(porcentaje);
	}
	
}
