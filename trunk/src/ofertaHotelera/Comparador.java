package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Comparador {

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
	
	/*public static boolean sonIguales(Calendar c1, Calendar c2){
		
		String f1 = "" + c1.get(c1.YEAR) + c1.get(c1.MONTH) + c1.get(c1.DATE);
		String f2 = "" + c2.get(c2.YEAR) + c2.get(c2.MONTH) + c2.get(c2.DATE);
		
		return f1.equals(f2);
	}*/
	
	public static boolean listaContieneFecha(List<Calendar> siguientesDias,
			Calendar fechaDeIngreso) {
		
		for(Calendar each: siguientesDias){
			if(each.equals(fechaDeIngreso)){
				return true;
			}
			
		}
		
		return false;
	}
	
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
		Calendar fechau = Calendar.getInstance();
		fechau.set(fechau.get(fechau.YEAR),fechau.get(fechau.MONTH), fechau.get(fechau.DATE),0,0,0);
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(fecha1.get(fecha1.YEAR),fecha1.get(fecha1.MONTH), fecha1.get(fecha1.DATE),0,0,0);
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(fecha2.get(fecha2.YEAR),fecha2.get(fecha1.MONTH), fecha2.get(fecha2.DATE) + 7,0,0,0);
		Calendar fecha3 = Calendar.getInstance();
		fecha3.set(fecha3.get(fecha3.YEAR),fecha3.get(fecha1.MONTH), fecha3.get(fecha3.DATE) - 3,0,0,0);
		Calendar fecha4 = Calendar.getInstance();
		fecha4.set(fecha4.get(fecha4.YEAR),fecha4.get(fecha1.MONTH), fecha4.get(fecha4.DATE) + 4,0,0,0);
		Calendar fecha5 = Calendar.getInstance();
		fecha5.set(fecha5.get(fecha5.YEAR),fecha5.get(fecha1.MONTH), fecha5.get(fecha5.DATE) - 7,0,0,0);
		Calendar fecha6 = Calendar.getInstance();
		fecha6.set(fecha6.get(fecha6.YEAR),fecha6.get(fecha1.MONTH), fecha6.get(fecha6.DATE),0,0,0);
	
		List<Calendar> c = new ArrayList<Calendar>();
		c.add(fecha1);
		c.add(fecha3);
		c.add(fecha4);

		System.out.println(Comparador.listaContieneFecha(c, fecha6));
		
		System.out.println(cantidadDeDias(fecha1,fecha2));
	}
}
