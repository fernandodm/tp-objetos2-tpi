package testHotel;

import hotel.Periodo;

import java.util.Calendar;


import junit.framework.Assert;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class PeriodoTest extends TestCase {
	protected Calendar desde;
	protected Calendar hasta;
	private Periodo periodo;
	
	public void setUp(){
		
		desde= Calendar.getInstance();
		desde.set(2013,01,01,0,0,0);
		desde.clear(Calendar.MILLISECOND);
		
		hasta= Calendar.getInstance();
		hasta.set(2013,01,07,0,0,0);
		hasta.clear(Calendar.MILLISECOND);
		
		periodo = new Periodo(desde,hasta);
	}
	
	public void testConstructorPeriodo(){
		Periodo per= new Periodo(desde, hasta);
		Assert.assertEquals(desde, per.getDesde());
		Assert.assertEquals(hasta, per.getHasta());
	}
	
	public void testFechaEstaEnElPeriodoTest(){
	
		Assert.assertTrue(periodo.fechaEstaEnElPeriodo(desde));
		
	}
	
	public void testEstaEntre(){
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(2013,01, 04,0,0,0);
		fecha1.clear(Calendar.MILLISECOND);
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(2013,01, 06,0,0,0);
		fecha2.clear(Calendar.MILLISECOND);
		
		
		boolean estaEntre = periodo.estaEntre(fecha1, fecha2);
		
		Assert.assertTrue(estaEntre);
	}
	/**
	 * Caso en el que el parametro uno se superpone
	 */
	public void testSeSuperPonePrimerParametro(){
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(2013,01, 01,0,0,0);
		fecha1.clear(Calendar.MILLISECOND);
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(2013,01, 06,0,0,0);
		fecha2.clear(Calendar.MILLISECOND);
		
		boolean seSuperPone = periodo.seSuperPone(fecha1, fecha2);
		
		Assert.assertTrue(seSuperPone);
		
	}
	
	/**
	 * segundo parametro se superpone
	 */
	public void testSeSuperPoneSegundoParametro(){
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(2013,01,04,0,0,0);
		fecha1.clear(Calendar.MILLISECOND);
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(2013,01,07,0,0,0);
		fecha2.clear(Calendar.MILLISECOND);
		
		boolean seSuperPone = periodo.seSuperPone(fecha1, fecha2);
		
		Assert.assertTrue(seSuperPone);
		
	}
	
	/**
	 * Caso en que no se superpone
	 */
	public void testSeSuperPoneFalse(){
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(2013,01, 04,0,0,0);
		fecha1.clear(Calendar.MILLISECOND);
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(2013,01, 06,0,0,0);
		fecha2.clear(Calendar.MILLISECOND);
		
		boolean seSuperPone = periodo.seSuperPone(fecha1, fecha2);
		
		Assert.assertFalse(seSuperPone);
		
	}
}
