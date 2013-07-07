package testHotel;

import hotel.PeriodoConPrecio;

import java.util.Calendar;
import junit.framework.TestCase;
import junit.framework.Assert;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;


public class PeriodoConPrecioTest extends TestCase {
	private Calendar desde;
	private Calendar hasta;
	
	public void setUp(){
		Calendar desde= mock(Calendar.class);
		Calendar hasta= mock(Calendar.class);    
	}
	
	public void testConstructorPeriodoConPrecio(){
		PeriodoConPrecio per= new PeriodoConPrecio(desde, hasta, 50);
		Assert.assertEquals(desde, per.getDesde());
		Assert.assertEquals(hasta, per.getHasta());
	}
	
	public void testAlgunDiaCoincide(){
		
	}
	
}
