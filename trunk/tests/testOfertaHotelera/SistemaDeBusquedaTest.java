package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import excepciones.ExcepcionElNombreDeUsuarioYaExiste;
import excepciones.ExcepcionPasswordIncorrecto;
import excepciones.ExcepcionUsuarioIncorrecto;

import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.SistemaDeBusqueda;
import ofertaHotelera.Usuario;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SistemaDeBusquedaTest extends TestCase {
	private Usuario usuario1;
	private Usuario usuario2;
	private Hotel hotel1;
	private Hotel hotel2;
	private Habitacion habittacion1;
	private Habitacion habittacion2;
	private Habitacion habittacion3;
	private SistemaDeBusqueda sistema;
	
	public void setUp(){
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Hotel> hoteles = new ArrayList<Hotel>();
		
		sistema = new SistemaDeBusqueda();
		
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		
		hotel1 = mock(Hotel.class);
		hotel2 = mock(Hotel.class);
		
		hoteles.add(hotel1);
		hoteles.add(hotel2);
		
		when(usuario1.getNombreUsuario()).thenReturn("luis.muzarella");
		when(usuario2.getNombreUsuario()).thenReturn("steve22");
		when(usuario1.getContrasenha()).thenReturn("1234");
		when(usuario2.getContrasenha()).thenReturn("4321");
		
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		
		sistema.setUsuarios(usuarios);
		sistema.setHoteles(hoteles);

	}

	public void testExisteNombreDeUsuarioTrue(){
		//con nombre existente
		
		Assert.assertTrue(sistema.existeNombreDeUsuario("steve22"));
		
	}
	
	public void testExisteNombreDeUsuarioFalse(){
		//con nombre inexistente
		
		Assert.assertFalse(sistema.existeNombreDeUsuario("ferplay_19"));
		
	}
	
	public void testRegistrarUsuario() throws ExcepcionElNombreDeUsuarioYaExiste{
		
		sistema.registrarUsuario("DonRamon", "1234", "ramon");
		int cantidadUsers = sistema.getUsuarios().size();
		Usuario user = sistema.getUsuarios().get(2);
		
		Assert.assertEquals("FALLA EL REGISTRAR USUARIO", cantidadUsers, 3);
		Assert.assertEquals(user.getNombreUsuario(), "DonRamon");
		
	}
	
	public void testRegistrarUsuarioConNombreExistente() throws ExcepcionElNombreDeUsuarioYaExiste{
		
		try{
			sistema.registrarUsuario("steve22", "1223", "esteban quito");
			fail("NO SE LANZO LA EXCEPCION DE REGISTRAR USUARIO");
		}catch(ExcepcionElNombreDeUsuarioYaExiste e){
			
		}
	}
	
	public void testBuscarUsuarioTrue() throws ExcepcionUsuarioIncorrecto{
		//Con usuario correcto
		
		Usuario user = sistema.buscarUsuario("steve22");
		Assert.assertEquals("FALLA BUSCAR USUARIO (USUARIO CORRECTO)",user.getNombreUsuario(), "steve22");
	}
	
	public void testBuscarUsuarioFalse() throws ExcepcionUsuarioIncorrecto{
		//Con usuario incorrecto
		try{
			sistema.buscarUsuario("steve222");
			fail("FALLA BUSCAR USUARIO (USUARIO INCORRECTO)");
		}catch(ExcepcionUsuarioIncorrecto e){
			
		}
	}
	
	public void testLogInContraseñaCorrecta() throws ExcepcionPasswordIncorrecto, ExcepcionUsuarioIncorrecto{		
		
		sistema.logIn("luis.muzarella", "1234");
		verify(usuario1).setOnline(true);
	}
	
	public void testLogInContraseñaIncorrecta() throws ExcepcionPasswordIncorrecto, ExcepcionUsuarioIncorrecto{
	
		try{
			sistema.logIn("luis.muzarella", "1245");
			fail("NO SE LANZO LA EXCEPCION DE CONTRASEÑA INCORRECTA");
		}catch(ExcepcionPasswordIncorrecto e){
			
		}
	}
	
	public void testBuscarHoteles(){
		
		Calendar desde = Calendar.getInstance();
		desde.set(2013, 03, 01, 0,0,0);
		
		Calendar hasta = Calendar.getInstance();
		hasta.set(2013, 03, 07, 0,0,0);
		
		
		when(hotel1.tieneHabitacionesCon(desde, hasta, 2)).thenReturn(true);	
		when(hotel2.tieneHabitacionesCon(desde, hasta, 2)).thenReturn(false);
		when(hotel1.getCiudad()).thenReturn("quilmes");	
		when(hotel2.getCiudad()).thenReturn("quilmes");
		
		List<Hotel> hoteles = sistema.buscarHoteles("quilmes", desde, hasta, 2);
		Hotel hotel = hoteles.get(0);		
		
		Assert.assertEquals(hoteles.size(), 1);
		Assert.assertTrue(hotel == hotel1);
	}
	
	public void testHotelesDe(){
		
		Hotel hotel3 = mock(Hotel.class);
		
		sistema.getHoteles().add(hotel3);
		
		when(hotel1.getCiudad()).thenReturn("quilmes");	
		when(hotel2.getCiudad()).thenReturn("la plata");
		when(hotel3.getCiudad()).thenReturn("quilmes");	
		
		List<Hotel> hoteles = sistema.hotelesDe("quilmes");
		Hotel h1 = hoteles.get(0);
		Hotel h2 = hoteles.get(1);
		
		Assert.assertEquals(hoteles.size(), 2);
		Assert.assertTrue(h1 == hotel1);
		Assert.assertTrue(h2 == hotel3);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
