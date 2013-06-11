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
			calendarios.add(fecha);
		}
		
		return calendarios;
	}

	public static void main(String[] args) {
		
		List<Calendar> c = GeneradorDeCalendar.generar(4);
		for(int i = 0; i <4 ; i++){
			System.out.println(c.get(i).getTime());
		}
	}
	
}
