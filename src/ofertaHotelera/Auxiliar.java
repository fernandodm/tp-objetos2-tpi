package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Auxiliar {

	/**
	 * Dada dos fecha devuelve la cantidad de dias que hay desde
	 * "desde" hasta "hasta"
	 * @param desde
	 * @param hasta
	 * @return
	 */
	public static int cantidadDeDias(Calendar desde, Calendar hasta) {
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(desde.get(desde.YEAR),desde.get(desde.MONTH),
				desde.get(desde.DATE),0,0,0);
		fecha.clear(Calendar.MILLISECOND);
		int cant = 0;
		while(!fecha.equals(hasta)){
			cant = cant + 1;
			fecha.set(fecha.get(fecha.YEAR),fecha.get(fecha.MONTH),fecha.get(fecha.DATE)+1,0,0,0);
		}
		cant = cant + 1;
		return cant;
	}	
	
	/**
	 * Dada una lista de Calendar y un Calendar retorna si
	 * ese calendar esta en la lista
	 * @param siguientesDias
	 * @param fechaDeIngreso
	 * @return
	 */
	public static boolean listaContieneFecha(List<Calendar> siguientesDias,
			Calendar fechaDeIngreso) {
		
		for(Calendar each: siguientesDias){
			if(each.equals(fechaDeIngreso)){
				return true;
			}
			
		}
		
		return false;
	}
	/**
	 * retorna true si la fecha1 esta entre la fecha2 y fecha3
	 * @param fecha1
	 * @param fecha2
	 * @param fecha3
	 * @return
	 */
	public static boolean between(Calendar fecha1, Calendar fecha2, Calendar fecha3){
		
		return (fecha1.after(fecha2) && fecha1.before(fecha3)) 
		|| fecha1.equals(fecha2) || fecha1.equals(fecha3);
		
	}
	
	public static boolean algunDiaIncluido(List<Calendar> fechas1, List<Calendar> fechas2){
		boolean val= false;
		GregorianCalendar diaInicio= (GregorianCalendar) fechas1.get(1);
		GregorianCalendar diaPostFin= (GregorianCalendar) fechas1.get(2);
		diaPostFin.add(Calendar.DATE, 1);
		while(!val && !(diaInicio.equals(diaPostFin))){
			if(between(diaInicio, fechas2.get(1), fechas2.get(2)))
			{
				val= true;
			}
			diaInicio.add(Calendar.DATE, 1);
		}
		return val;
	}
	
	public static boolean algunDiaIncluidoEnColeccion(List<Calendar> fechas, List<List<Calendar>> colFechas){
		boolean val= false;
		for(List<Calendar> each: colFechas){
			val= algunDiaIncluido(fechas, each);
			if(val)
			{
				break;
			}
		}
		return val;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
}
