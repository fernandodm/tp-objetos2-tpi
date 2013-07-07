package descuentos;

import hotel.Periodo;

import java.util.*;


public class DescuentoPorRangoDeFechas extends Descuento {

	private Periodo rango;
	
	public Periodo getRango() {
		return rango;
	}

	public void setRango(Periodo rango) {
		this.rango = rango;
	}

	public DescuentoPorRangoDeFechas(Periodo rango, int porcentaje){
		this.rango = rango;
		this.porcentajeDescontado = porcentaje;
	}

	public String descuento() {
		
		Calendar fechaInicio = getRango().getDesde();
		int año1 = fechaInicio.get(fechaInicio.YEAR);
		int mes1 = fechaInicio.get(fechaInicio.MONTH);
		int dia1= fechaInicio.get(fechaInicio.DATE);
		String laFechaInicio = dia1 + "/" + mes1 + "/" + año1;
		
		Calendar fechaFin = getRango().getHasta();
		int año2 = fechaFin.get(fechaFin.YEAR);
		int mes2 = fechaFin.get(fechaFin.MONTH);
		int dia2= fechaFin.get(fechaFin.DATE);
		String laFechaFin = dia2 + "/" + mes2 + "/" + año2;
		
		return "Si venis entre el " + laFechaInicio + " y el " + laFechaFin + 
		" te hacemos un " + getPorcentajeDescontado() + "%" + " de descuento. ";
	}
	
	
}
