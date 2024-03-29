package testOfertaHotelera;

import java.util.Calendar;
import java.util.List;

import junit.framework.*;
import junit.framework.TestCase;

import ofertaHotelera.GeneradorDeCalendar;

public class GeneradorDeCalendarTest extends TestCase {
	
	private GeneradorDeCalendar generador;
	
	public void setUp(){
		generador = new GeneradorDeCalendar();
	}
	
	public void testGenerar(){
		
		List<Calendar> fechas = generador.generar(2);
		
		Calendar fechaMaņana = Calendar.getInstance();
		fechaMaņana.set(fechaMaņana.get(fechaMaņana.YEAR), fechaMaņana.get(fechaMaņana.MONTH), 
				fechaMaņana.get(fechaMaņana.DATE) + 1,0,0,0);
		fechaMaņana.clear(Calendar.MILLISECOND);
		
		Calendar fechaPasadoMaņana = Calendar.getInstance();
		fechaPasadoMaņana.set(fechaPasadoMaņana.get(fechaPasadoMaņana.YEAR), fechaPasadoMaņana.get(fechaPasadoMaņana.MONTH), 
				fechaPasadoMaņana.get(fechaPasadoMaņana.DATE) + 2,0,0,0);
		fechaPasadoMaņana.clear(Calendar.MILLISECOND);
		
		Calendar fecha1 = fechas.get(0);
		Calendar fecha2 = fechas.get(1);

		Assert.assertEquals(fechas.size(),2);
		Assert.assertEquals(fecha1,fechaMaņana);
		Assert.assertEquals(fecha2,fechaPasadoMaņana);
		
	}
	
	public void testGenerarDiasEntre(){
		
		Calendar desde = Calendar.getInstance();
		desde.set(2013, 06, 01,0,0,0);
		desde.clear(Calendar.MILLISECOND);
		Calendar hasta = Calendar.getInstance();
		hasta.set(2013, 06, 03,0,0,0);
		hasta.clear(Calendar.MILLISECOND);
		
		List<Calendar> fechas = generador.generarDiasEntre(desde, hasta);
		
		Calendar fecha1 = fechas.get(0);
		Calendar fecha2 = fechas.get(1);
		Calendar fecha3 = fechas.get(2);
		
		Calendar fechaDelMedio = Calendar.getInstance(); // Para comparar con la fecha del medio de la lista
		fechaDelMedio.set(2013, 06, 02,0,0,0);
		fechaDelMedio.clear(Calendar.MILLISECOND);
		
		Assert.assertEquals(fechas.size(), 3);
		Assert.assertEquals(fecha1,desde);
		Assert.assertEquals(fecha2,fechaDelMedio);
		Assert.assertEquals(fecha3,hasta);
	}

}
