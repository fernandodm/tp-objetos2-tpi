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
	private Calendar calendario;
	private Habitacion habitacion;
	private Hotel hotel;
	
	public void setUp(){
		reserva = new Reserva();
		habitacion = mock(Habitacion.class);
		hotel = mock(Hotel.class);
		calendario = Calendar.getInstance();
		reserva.setFechaDeIngreso(calendario);
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
		
		calendario.set(2013,07,11);
		reserva.setFechaDeIngreso(calendario);
		Assert.assertTrue(reserva.estaReservadaDespuesDe(Calendar.getInstance()));
		
	}
	
	public void testEstaReservadaDespuesDeConReservaPasada(){
		
		calendario.set(2013,04,19);
		reserva.setFechaDeIngreso(calendario);
		Assert.assertFalse(reserva.estaReservadaDespuesDe(Calendar.getInstance()));
		
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
