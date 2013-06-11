package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;

import ofertaHotelera.Hotel;
import ofertaHotelera.Reserva;
import junit.framework.TestCase;

public class HotelTest extends TestCase{
	private Hotel hotel;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	
	public void setUp(){
		
		hotel = new Hotel();
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		reservas.add(reserva1);
		reservas.add(reserva2);
		reservas.add(reserva3);
		
		hotel.setReservas(reservas);
		
	}
	
	public void testEliminarReserva(){
		
		hotel.eliminarReserva(reserva2);
		int cantidadReservas = hotel.getReservas().size();
		Reserva reserv1 = hotel.getReservas().get(0);
		Reserva reserv2 = hotel.getReservas().get(1);
		Assert.assertEquals(cantidadReservas, 2);
		Assert.assertEquals(reserv1, reserva1);
		Assert.assertEquals(reserv2, reserva3);
		
	}
	
	public void testReservasActuales(){
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(fecha1.get(fecha1.YEAR),fecha1.get(fecha1.MONTH), fecha1.get(fecha1.DATE),0,0,0);
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(fecha2.get(fecha2.YEAR),fecha2.get(fecha1.MONTH), fecha2.get(fecha2.DATE) + 7,0,0,0);
		Calendar fecha3 = Calendar.getInstance();
		fecha3.set(fecha3.get(fecha3.YEAR),fecha3.get(fecha1.MONTH), fecha3.get(fecha3.DATE) - 3,0,0,0);
		Calendar fecha4 = Calendar.getInstance();
		fecha4.set(fecha4.get(fecha4.YEAR),fecha4.get(fecha1.MONTH), fecha4.get(fecha4.DATE) + 4,0,0,0);
		Calendar fecha5 = Calendar.getInstance();
		fecha5.set(fecha5.get(fecha5.YEAR),fecha5.get(fecha1.MONTH), fecha5.get(fecha5.DATE) - 7,0,0,0);
		Calendar fecha6 = Calendar.getInstance();
		fecha6.set(fecha6.get(fecha6.YEAR),fecha6.get(fecha1.MONTH), fecha6.get(fecha6.DATE),0,0,0);
		
		when(reserva1.getFechaDeIngreso()).thenReturn(fecha1);
		when(reserva1.getFechaDeSalida()).thenReturn(fecha2);
		when(reserva2.getFechaDeIngreso()).thenReturn(fecha3);
		when(reserva2.getFechaDeSalida()).thenReturn(fecha4);
		when(reserva3.getFechaDeIngreso()).thenReturn(fecha5);
		when(reserva3.getFechaDeSalida()).thenReturn(fecha6);
		
		List<Reserva> actuales = hotel.reservasActuales();
		Assert.assertEquals(actuales.size(), 2);
		Assert.assertTrue(actuales.get(0) == reserva1);
		Assert.assertTrue(actuales.get(1) == reserva2);
	}
	
}
