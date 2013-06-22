package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Comparador {

	public static boolean sonIguales(Calendar c1, Calendar c2){
		
		String f1 = "" + c1.get(c1.YEAR) + c1.get(c1.MONTH) + c1.get(c1.DATE);
		String f2 = "" + c2.get(c2.YEAR) + c2.get(c2.MONTH) + c2.get(c2.DATE);
		
		return f1.equals(f2);
	}
	
	public static boolean listaContieneFecha(List<Calendar> siguientesDias,
			Calendar fechaDeIngreso) {
		
		for(Calendar each: siguientesDias){
			if(Comparador.sonIguales(each, fechaDeIngreso)){
				return true;
			}
			
		}
		
		return false;
	}
	
	public static boolean between(Calendar fecha1, Calendar fecha2, Calendar fecha3){
		
		return (fecha1.after(fecha2) && fecha1.before(fecha3)) 
		|| sonIguales(fecha1,fecha2) || sonIguales(fecha1,fecha3);
		
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
	}
}
