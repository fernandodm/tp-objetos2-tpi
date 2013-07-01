package testOfertaHotelera;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;

import ofertaHotelera.Habitacion;
import ofertaHotelera.PorPrecioPorEstadia;
import junit.framework.Assert;
import junit.framework.TestCase;


public class PorPrecioPorEstadiaTest extends TestCase {
	
	private PorPrecioPorEstadia preferencia;
	private Habitacion hab1;
	private Habitacion hab2;
	private Calendar fecha1;
	private Calendar fecha2;

	
	public void setUp(){
		
		fecha1 = Calendar.getInstance();
		fecha2 = Calendar.getInstance();
		fecha2.set(2013,06,10,0,0,0);
		
		preferencia = new PorPrecioPorEstadia(0,1000);
		preferencia.setFechaInicialDeInteres(fecha1);
		preferencia.setFechaFinalDeInteres(fecha2);

		
		hab1 = mock(Habitacion.class);
		try {
			when(hab1.precioTotal(fecha1, fecha2)).thenReturn((float) 1000);
		} catch (ExcepcionNoHayPrecioEstablecidoParaTalFecha e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hab2 = mock(Habitacion.class);
		try {
			when(hab2.precioTotal(fecha1, fecha2)).thenReturn((float) 1500);
		} catch (ExcepcionNoHayPrecioEstablecidoParaTalFecha e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testLePuedeInteresarHabitacionTrue() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		Assert.assertTrue(preferencia.lePuedeInteresarHabitacion(hab1));
		
	}
	
	public void testLePuedeInteresarHabitacionFalse() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		Assert.assertFalse(preferencia.lePuedeInteresarHabitacion(hab2));
		
	}
	

}
