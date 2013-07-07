package auxiliares;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class GeneradorDeCalendar {
	
	/**
	 * Retorna teniendo en cuenta la fecha actual los n Calendar seguientes
	 * @param n
	 * @return
	 */
	public static List<Calendar> generar(int n) {
		
		List<Calendar> calendarios = new ArrayList<Calendar>();
		
		for(int i = 1; i < n + 1 ; i++){
			Calendar fecha = Calendar.getInstance();
			fecha.set(fecha.get(fecha.YEAR),fecha.get(fecha.MONTH),
					fecha.get(fecha.DATE) + i,0,0,0);
			fecha.clear(Calendar.MILLISECOND);
			calendarios.add(fecha);
	
		}
		
		return calendarios;
	}

	/**
	 * Retorna todas las fechas que hay entre los parametros incluyendolos
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	public static List<Calendar> generarDiasEntre(Calendar fechaInicio, Calendar fechaFin) {
		
		List<Calendar> calendarios = new ArrayList<Calendar>();
		
		int cantidad = Auxiliar.cantidadDeDias(fechaInicio, fechaFin);
	
		for(int i = 0; i < cantidad; i++){
	
			Calendar fecha = Calendar.getInstance();
			fecha.set(fechaInicio.get(fechaInicio.YEAR),fechaInicio.get(fechaInicio.MONTH),
					fechaInicio.get(fechaInicio.DATE) + i,0,0,0);
			
			fecha.clear(Calendar.MILLISECOND);
		
			calendarios.add(fecha);
		}
		
		return calendarios;
	}
	
	public static void main(String[] args) {
		
	}
}
