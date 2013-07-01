package testOfertaHotelera;

import java.util.Calendar;

import ofertaHotelera.Periodo;

import junit.framework.Assert;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class PeriodoTest extends TestCase {
	protected Calendar desde;
	protected Calendar hasta;
	
	public void setUp(){
		Calendar desde= mock(Calendar.class);
		Calendar hasta= mock(Calendar.class);    
	}
	
	public void testConstructorPeriodo(){
		Periodo per= new Periodo(desde, hasta);
		Assert.assertEquals(desde, per.getDesde());
		Assert.assertEquals(hasta, per.getHasta());
	}
	
	
}
