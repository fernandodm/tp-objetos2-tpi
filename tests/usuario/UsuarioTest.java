package usuario;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import ofertaHotelera.Hotel;
import ofertaHotelera.Usuario;
import ofertaHotelera.Reserva;

public class UsuarioTest extends TestCase {
	private Usuario usuario;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private List<Reserva> reservasUsuario = new ArrayList<Reserva>();
	
	public void setUp(){
		
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		when(reserva1.ciudadDelHotel()).thenReturn("Sidney");
		when(reserva2.ciudadDelHotel()).thenReturn("Moscu");
		when(reserva3.ciudadDelHotel()).thenReturn("Sidney");
		reservasUsuario.add(reserva1);
		reservasUsuario.add(reserva2);
		reservasUsuario.add(reserva3);
		
		usuario = new Usuario();		
		usuario.setReservas(reservasUsuario);
		
	}
	
	public void testTodasLasReservas(){
		
		List<Reserva> reservas = usuario.todasLasReservas();
		Assert.assertEquals("NO HAY LAS RESERVAS ESPERADAS",reservas.size(), 3 );
	}
	
	public void testReservaPorCiudad(){
		
		List<Reserva> reservas = usuario.reservaPorCiudad("Sidney");
		Assert.assertEquals("LA CIUDAD NO COINCIDE CON LA ESPERADA", reservas.size(), 2);
		//verifico que sean los objetos deseados
		Assert.assertTrue(reservas.get(0) == reserva1);
		Assert.assertTrue(reservas.get(1) == reserva3);
	}

	public void testCiudadesConReservas(){
		
		List<String> ciudades = usuario.ciudadesConReservas();
		Assert.assertEquals("FALLA CIUDADES CON RESERVAS", ciudades.size(), 3);
		//verifico que sean las ciudades correctas
		Assert.assertTrue(ciudades.get(0) == "Sidney");
		Assert.assertTrue(ciudades.get(1) == "Moscu");
		Assert.assertTrue(ciudades.get(2) == "Sidney");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Usuario fergay;
	}

}
