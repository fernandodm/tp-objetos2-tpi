package testPreferencias;

import hotel.Habitacion;
import hotel.Hotel;
import preferencias.PorPrecioPorNoche;
import sistemaYUsuario.Usuario;
import junit.framework.Assert;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class PorPrecioPorNocheTest extends TestCase {

	
	private PorPrecioPorNoche preferencia;
	private Habitacion hab1;
	private Habitacion hab2;

	
	public void setUp(){
		
		preferencia = new PorPrecioPorNoche(0,120);
		
		hab1 = mock(Habitacion.class);
		when(hab1.precioPorNochePromedio()).thenReturn((float) 100);
		hab2 = mock(Habitacion.class);
		when(hab2.precioPorNochePromedio()).thenReturn((float) 150);

		
	}
	
	public void testLePuedeInteresarHabitacionTrue(){
		
		Assert.assertTrue(preferencia.lePuedeInteresarHabitacion(hab1));
		
	}
	
	public void testLePuedeInteresarHabitacionFalse(){
		
		Assert.assertFalse(preferencia.lePuedeInteresarHabitacion(hab2));
		
	}
	
}
