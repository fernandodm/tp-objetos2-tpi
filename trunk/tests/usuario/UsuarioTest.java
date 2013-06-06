package usuario;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.ws.Action;

import excepciones.ExcepcionNoEstaOnline;

import junit.framework.Assert;
import junit.framework.TestCase;
import ofertaHotelera.Hotel;
import ofertaHotelera.Usuario;
import ofertaHotelera.Reserva;

public class UsuarioTest extends TestCase {
	private Usuario usuario;
	private Usuario usuario2;
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
		
		usuario2 = new Usuario();
		usuario2.setOnline(false);
		
		usuario = new Usuario();	
		usuario.setOnline(true);
		usuario.setReservas(reservasUsuario);
		
	}
	
	public void testTodasLasReservasConUsuarioOffLine(){
		
		try{
			usuario2.todasLasReservas();
			fail("NO SE LANZO LA EXCEPCION DE todasLasReservas()");
		}catch(ExcepcionNoEstaOnline e){

		}
	}
	
	public void testTodasLasReservasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<Reserva> reservas = usuario.todasLasReservas();
		Assert.assertEquals("NO HAY LAS RESERVAS ESPERADAS",reservas.size(), 3 );
	}
	
	public void testReservaPorCiudadConUsuarioOffLine(){
	
		try{
			usuario2.reservaPorCiudad("Nueva York");
			fail("NO SE LANZO LA EXCEPCION DE reservaPorCiudad()");
		}catch(ExcepcionNoEstaOnline e){
			
		}
	}
	
	public void testReservaPorCiudadConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<Reserva> reservas = usuario.reservaPorCiudad("Sidney");
		Assert.assertEquals("LA CIUDAD NO COINCIDE CON LA ESPERADA", reservas.size(), 2);
		//verifico que sean los objetos deseados
		Assert.assertTrue(reservas.get(0) == reserva1);
		Assert.assertTrue(reservas.get(1) == reserva3);
	}

	public void testCiudadesConReservasConUsuarioOffLine(){
		
		try{
			usuario2.ciudadesConReservas();
			fail("NO SE LANZO LA EXCEPCION DE ciudadesConReservas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	public void testCiudadesConReservasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<String> ciudades = usuario.ciudadesConReservas();
		Assert.assertEquals("FALLA CIUDADES CON RESERVAS", ciudades.size(), 3);
		//verifico que sean las ciudades correctas
		Assert.assertTrue(ciudades.get(0).equals("Sidney"));
		Assert.assertTrue(ciudades.get(1).equals("Moscu"));
		Assert.assertTrue(ciudades.get(2).equals("Sidney"));
	}
	
	public void testReservasFuturasConUsuarioOffLine(){
		
		try{
			usuario2.reservasFuturas();
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
	}
	
	public void testReservasFuturasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		when(reserva1.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(true);
		when(reserva2.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(false);
		when(reserva3.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(true);
		
		List<Reserva> futuras = usuario.reservasFuturas();
		Assert.assertEquals("",futuras.size(), 2);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
