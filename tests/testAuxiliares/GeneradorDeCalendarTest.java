package testAuxiliares;

import java.util.Calendar;
import java.util.List;

import auxiliares.GeneradorDeCalendar;

import junit.framework.*;


public class GeneradorDeCalendarTest extends TestCase {
	
	private GeneradorDeCalendar generador;
	
	public void setUp(){
		generador = new GeneradorDeCalendar();
	}
	
	public void testGenerar(){
		
		List<Calendar> fechas = generador.generar(2);
		
		Calendar fechaMañana = Calendar.getInstance();
		fechaMañana.set(fechaMañana.get(fechaMañana.YEAR), fechaMañana.get(fechaMañana.MONTH), 
				fechaMañana.get(fechaMañana.DATE) + 1,0,0,0);
		fechaMañana.clear(Calendar.MILLISECOND);
		
		Calendar fechaPasadoMañana = Calendar.getInstance();
		fechaPasadoMañana.set(fechaPasadoMañana.get(fechaPasadoMañana.YEAR), fechaPasadoMañana.get(fechaPasadoMañana.MONTH), 
				fechaPasadoMañana.get(fechaPasadoMañana.DATE) + 2,0,0,0);
		fechaPasadoMañana.clear(Calendar.MILLISECOND);
		
		Calendar fecha1 = fechas.get(0);
		Calendar fecha2 = fechas.get(1);

		Assert.assertEquals(fechas.size(),2);
		Assert.assertEquals(fecha1,fechaMañana);
		Assert.assertEquals(fecha2,fechaPasadoMañana);
		
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
