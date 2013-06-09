package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import ofertaHotelera.Hotel;
import ofertaHotelera.Reserva;
import junit.framework.TestCase;

public class HotelTest extends TestCase{
	private Hotel hotel;
	private Reserva reserva1;
	private Reserva reserva2;
	
	public void setUp(){
		
		hotel = new Hotel();
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		
		hotel.setReservas(reservas);
		
	}
	
	public void testEliminarReserva(){
		
		hotel.eliminarReserva(reserva2);
		int cantidadReservas = hotel.getReservas().size();
		Reserva reserv = hotel.getReservas().get(0);
		Assert.assertEquals(cantidadReservas, 1);
		Assert.assertEquals(reserv, reserva1);
		
	}
	
	
}
