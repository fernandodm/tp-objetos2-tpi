package testOfertaHotelera;

import java.util.Calendar;

import ofertaHotelera.Periodo;

import org.junit.Assert;

import static org.mockito.Mockito.*;

import descuentos.DescuentoPorFecha;
import descuentos.DescuentoPorRangoDeFechas;
import junit.framework.TestCase;

public class DescuentoPorRangoDeFechasTest extends TestCase {
	
	public void testDescuento(){
		
		Periodo rango = mock(Periodo.class);
		
		Calendar desde = Calendar.getInstance();
		Calendar hasta = Calendar.getInstance();;
		hasta.set(2013, 06, 30);
		
		when(rango.getDesde()).thenReturn(desde);
		when(rango.getHasta()).thenReturn(hasta);
		
		DescuentoPorRangoDeFechas descuento = new DescuentoPorRangoDeFechas(rango, 30);
		
		String desc = descuento.descuento();
		
		Assert.assertEquals(desc, "Si venis entre el 2/6/2013 y el 30/6/2013 te hacemos un 30% de descuento. ");
	}
}
