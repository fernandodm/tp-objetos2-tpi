package testPreferencias;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import hotel.Habitacion;
import hotel.Hotel;
import preferencias.PorPrecioPorNoche;
import preferencias.PreferenciaPorLugar;
import junit.framework.Assert;
import junit.framework.TestCase;

public class PreferenciaPorLugarTest extends TestCase {

	private PreferenciaPorLugar preferencia;
	private PreferenciaPorLugar preferencia2;
	private PreferenciaPorLugar preferencia3;
	private Habitacion hab1;
	private Habitacion hab2;
	private Hotel hotel1;
	private Hotel hotel2;
	
	public void setUp(){
		
		preferencia = new PreferenciaPorLugar();
		preferencia.setPaisDelHotel("Argentina");
		preferencia.setCiudadDelHotel("Bernal");
		
		preferencia2 = new PreferenciaPorLugar();
		preferencia2.setPaisDelHotel("Argentina");

		
		preferencia3 = new PreferenciaPorLugar();
		preferencia3.setCiudadDelHotel("Bernal");
		
		hotel1 = mock(Hotel.class);
		when(hotel1.getPais()).thenReturn("Argentina");
		when(hotel1.getCiudad()).thenReturn("Bernal");
		
		hab1 = mock(Habitacion.class);
		when(hab1.getHotel()).thenReturn(hotel1);
		hab2 = mock(Habitacion.class);
		when(hab2.getHotel()).thenReturn(hotel2);
	}
	
	public void testLePuedeInteresarHabitacionLugarDetalladoTrue(){
		
		Assert.assertTrue(preferencia.lePuedeInteresarHabitacion(hab1));
	}
	
	public void testLePuedeInteresarHabitacionLugarSoloEspecificandoPais(){
		when(hotel1.getCiudad()).thenReturn("Bernalandia");
		Assert.assertTrue(preferencia2.lePuedeInteresarHabitacion(hab1));
	}
	
	public void testLePuedeInteresarHabitacionLugarSoloEspecificandoCiudad(){
		when(hotel1.getPais()).thenReturn("Alemania");
		Assert.assertTrue(preferencia3.lePuedeInteresarHabitacion(hab1));
	}
	
	public void testLePuedeInteresarHabitacionLugarDetalladoFalse(){
		when(hotel1.getPais()).thenReturn("Alemania");
		Assert.assertFalse(preferencia.lePuedeInteresarHabitacion(hab1));
	}
	
	public void testLePuedeInteresarHabitacionLugarDetalladoFalse2(){
		when(hotel1.getCiudad()).thenReturn("Varela");
		Assert.assertFalse(preferencia.lePuedeInteresarHabitacion(hab1));
	}
	
	
	
	
	
	
}
