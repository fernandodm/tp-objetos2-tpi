package testHotel;

import hotel.Habitacion;
import hotel.Hotel;
import hotel.Hotelero;
import hotel.PeriodoConPrecio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import descuentos.Descuento;
import excepciones.Excepcion1OMasDiasYaTenianPrecioSetteado;
import sistemaYUsuario.Sistema;
import subasta.Subasta;
import junit.framework.Assert;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class HoteleroTest extends TestCase {

	private Hotel hotel;
	private Hotelero hotelero;
	private Habitacion habitacion1;
	private Habitacion habitacion2;
	private List<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private List<Hotel> hoteles= new ArrayList<Hotel>();
	private List<PeriodoConPrecio> periodos= new ArrayList<PeriodoConPrecio>();
	private PeriodoConPrecio per;
	private Sistema sistema;
	
	public void setUp(){
		sistema = mock(Sistema.class);
		hotel = mock(Hotel.class);
		hoteles.add(hotel);
		hotelero = new Hotelero("Steve", hoteles, "loquillo@gmail.com");
		habitacion1 = mock(Habitacion.class);
		habitacion2 = mock(Habitacion.class);
		per= mock(PeriodoConPrecio.class); 
		when(habitacion1.getPreciosPorFecha()).thenReturn(periodos);
		when(habitacion1.getHotel()).thenReturn(hotel);
		when(hotel.getSistemaEnElQueEstaCargado()).thenReturn(sistema);		
	}
	
	/**
	 * Testeo el constructor de la clase Hotelero
	 */
	public void testConstructorHotelero(){
		Assert.assertEquals("Steve", hotelero.getNombre());
		Assert.assertEquals(hotel, hotelero.getMisHoteles().get(0));
		Assert.assertEquals("loquillo@gmail.com", hotelero.getMail());		
	}
	
	/**
	 * Testeo el método obtenerReservasActuales()
	 */
	public void testObtenerReservasActuales(){
		hotelero.obtenerReservasActuales();
		verify(hotel).reservasActuales();
	}
	
	/**
	 * Testeo el método obtenerReservasFuturas()
	 */
	public void testObtenerReservasFuturas(){
		hotelero.obtenerReservasFuturas();
		verify(hotel).reservasFuturas();
	}

	/**
	 * Testeo el método obtenerReservasEnLosProximosNDias(unN)
	 */
	public void testObtenerReservasEnLosProximosNDias(){
		hotelero.obtenerReservasEnNDias(10);
		verify(hotel).reservasEnLosSiguientesNDias(10);
	}

	/**
	 * Testeo el método agregarDescuento()
	 */
	public void testAgregarDescuento(){
		habitaciones.add(habitacion1);
		habitaciones.add(habitacion2);
		Descuento descuento1 = mock(Descuento.class);
		List<Descuento> descuentoshab1= new ArrayList<Descuento>();
		List<Descuento> descuentoshab2= new ArrayList<Descuento>();
		when(habitacion1.getDescuentos()).thenReturn(descuentoshab1);
		when(habitacion2.getDescuentos()).thenReturn(descuentoshab2);
		
		hotelero.agregarDescuento(descuento1, habitaciones);
		
		int descHab1= habitacion1.getDescuentos().size();
		int descHab2= habitacion2.getDescuentos().size();
		int resultadoEsperado = 2;
		
		Assert.assertEquals(resultadoEsperado , descHab1+descHab2);
	}
	
	/**
	 * Testeo el método ponerPrecioRangoDias en un escenario en donde no se 
	 * tendría que lanzar la excepción
	 */
	public void testPonerPrecioHabRangoDiasSinDiasRepetidos() 
			throws Excepcion1OMasDiasYaTenianPrecioSetteado{
		
		hotelero.ponerPrecioHabRangoDias(per, habitacion1);
		PeriodoConPrecio resultadoEsperado= periodos.get(0);
		
		Assert.assertEquals(resultadoEsperado , per);
	}
	
	/**
	 * Testeo el método ponerPrecioRangoDias en un escenario en donde se 
	 * tendría que lanzar la excepción
	 */
	public void testPonerPrecioHabRangoDiasConDiasRepetidos() 
			throws Excepcion1OMasDiasYaTenianPrecioSetteado{
		    
		when(per.algunDiaCoincide(periodos)).thenReturn(true);
		try{
			hotelero.ponerPrecioHabRangoDias(per, habitacion1);	
			fail("NO SE LANZO LA EXCEPCION DE 1OMASDIASYATENIANPRECIOSETTEADO");
		}catch(Excepcion1OMasDiasYaTenianPrecioSetteado e){
				
		}
	}
	
	/**
	 * Testeo el método sacarPrecioHabRangoDias
	 * @throws Excepcion1OMasDiasYaTenianPrecioSetteado 
	 */
	public void testSacarPrecioHabRangoDias() throws Excepcion1OMasDiasYaTenianPrecioSetteado{
		periodos.add(per);
		hotelero.sacarPrecioHabRangoDias(per, habitacion1);
		
		verify(sistema).actualizarOfertaDelHotel(habitacion1.getHotel());
		Assert.assertFalse(periodos.contains(per));
	}
	
	/**
	 * Testeo el método subastarHabitacion()
	 */
	public void testSubastarHabitacion(){
		
		Sistema sistema= mock(Sistema.class);
		
		Calendar ini = Calendar.getInstance();
		Calendar fin = Calendar.getInstance();
		hotelero.subastarHabitacion(habitacion1,(float) 50,ini,fin,sistema);
		
		verify(sistema).agregarSubasta(habitacion1,(float) 50,ini,fin);
	}
	
	}
