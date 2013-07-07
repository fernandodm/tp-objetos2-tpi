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
		
		Calendar fechaMa�ana = Calendar.getInstance();
		fechaMa�ana.set(fechaMa�ana.get(fechaMa�ana.YEAR), fechaMa�ana.get(fechaMa�ana.MONTH), 
				fechaMa�ana.get(fechaMa�ana.DATE) + 1,0,0,0);
		fechaMa�ana.clear(Calendar.MILLISECOND);
		
		Calendar fechaPasadoMa�ana = Calendar.getInstance();
		fechaPasadoMa�ana.set(fechaPasadoMa�ana.get(fechaPasadoMa�ana.YEAR), fechaPasadoMa�ana.get(fechaPasadoMa�ana.MONTH), 
				fechaPasadoMa�ana.get(fechaPasadoMa�ana.DATE) + 2,0,0,0);
		fechaPasadoMa�ana.clear(Calendar.MILLISECOND);
		
		Calendar fecha1 = fechas.get(0);
		Calendar fecha2 = fechas.get(1);

		Assert.assertEquals(fechas.size(),2);
		Assert.assertEquals(fecha1,fechaMa�ana);
		Assert.assertEquals(fecha2,fechaPasadoMa�ana);
		
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
