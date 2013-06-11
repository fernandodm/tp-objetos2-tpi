package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.Calendar;

import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Reserva;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ReservaTest extends TestCase {
	private Reserva reserva;
	private Calendar fecha1;
	private Calendar fecha2;
	private Habitacion habitacion;
	private Hotel hotel;
	
	public void setUp(){
		reserva = new Reserva();
		habitacion = mock(Habitacion.class);
		hotel = mock(Hotel.class);
		fecha1 = Calendar.getInstance();
		fecha2 = Calendar.getInstance();
		fecha2.set(2013,06,10,0,0,0);
		reserva.setFechaDeIngreso(fecha1);
		reserva.setHabitacion(habitacion);
		reserva.setHotel(hotel);
	}
	
	public void testCiudadDelHotel(){
		
		Hotel hotel = mock(Hotel.class);
		reserva.setHotel(hotel);
		when(hotel.getCiudad()).thenReturn("Madrid");
		String ciudad = reserva.ciudadDelHotel();
		Assert.assertEquals("FALLA ciudadDelHotal() NO ES LA ESPERADA" , ciudad, "Madrid");
	
	}
	
	public void testEstaReservadaDespuesDeConReservaFutura(){
		
		fecha1.set(2013,07,11,0,0,0);
		Assert.assertTrue(reserva.estaReservadaDespuesDe(fecha2));
		
	}
	
	public void testEstaReservadaDespuesDeConReservaPasada(){
		
		fecha1.set(2013,04,19);
		Assert.assertFalse(reserva.estaReservadaDespuesDe(fecha2));
		
	}
	
	public void testEliminarHorarioDeLaHabitacion(){
		
		reserva.eliminarHorarioDeLaHabitacion();
		verify(habitacion).eliminarHorario(any(Calendar.class), any(Calendar.class));
		
	}
	
	public void testEliminarReservaDelHotel(){
		
		reserva.eliminarReservaDelHotel();
		verify(hotel).eliminarReserva(any(Reserva.class));
		
	}
	
	public static void main(String[] args) {
	
	}
}
