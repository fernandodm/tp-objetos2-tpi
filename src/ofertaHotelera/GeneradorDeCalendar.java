package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GeneradorDeCalendar {
	

	public static List<Calendar> generar(int n) {
		
		List<Calendar> calendarios = new ArrayList<Calendar>();
		
		for(int i = 1; i < n + 1; i++){
			Calendar fecha = Calendar.getInstance();
			fecha.set(fecha.get(fecha.YEAR),fecha.get(fecha.MONTH),
					fecha.get(fecha.DATE) + i,0,0,0);
			fecha.clear(Calendar.MILLISECOND);
			calendarios.add(fecha);
		}
		
		return calendarios;
	}

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
