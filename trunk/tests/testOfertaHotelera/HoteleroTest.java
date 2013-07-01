package testOfertaHotelera;

import java.util.ArrayList;
import java.util.List;
import descuentos.Descuento;
import excepciones.Excepcion1OMasDiasYaTenianPrecioSetteado;
import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Hotelero;
import ofertaHotelera.PeriodoConPrecio;
import ofertaHotelera.SistemaDeBusqueda;
import ofertaHotelera.Subasta;
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
	
	public void setUp(){
		hotel = mock(Hotel.class);
		hoteles.add(hotel);
		hotelero = new Hotelero("Steve", hoteles, "loquillo@gmail.com");
		habitacion1 = mock(Habitacion.class);
		habitacion2 = mock(Habitacion.class);
		per= mock(PeriodoConPrecio.class); 
		when(habitacion1.getPreciosPorFecha()).thenReturn(periodos);
		
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
	 * Testeo el m�todo obtenerReservasActuales()
	 */
	public void testObtenerReservasActuales(){
		hotelero.obtenerReservasActuales();
		verify(hotel).reservasActuales();
	}
	
	/**
	 * Testeo el m�todo obtenerReservasFuturas()
	 */
	public void testObtenerReservasFuturas(){
		hotelero.obtenerReservasFuturas();
		verify(hotel).reservasFuturas();
	}

	/**
	 * Testeo el m�todo obtenerReservasEnLosProximosNDias(unN)
	 */
	public void testObtenerReservasEnLosProximosNDias(){
		hotelero.obtenerReservasEnNDias(10);
		verify(hotel).reservasEnLosSiguientesNDias(10);
	}

	/**
	 * Testeo el m�todo agregarDescuento()
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
	 * Testeo el m�todo ponerPrecioRangoDias en un escenario en donde no se 
	 * tendr�a que lanzar la excepci�n
	 */
	public void testPonerPrecioHabRangoDiasSinDiasRepetidos() 
			throws Excepcion1OMasDiasYaTenianPrecioSetteado{
		
		hotelero.ponerPrecioHabRangoDias(per, habitacion1);
		PeriodoConPrecio resultadoEsperado= periodos.get(0);
		
		Assert.assertEquals(resultadoEsperado , per);
	}
	
	/**
	 * Testeo el m�todo ponerPrecioRangoDias en un escenario en donde se 
	 * tendr�a que lanzar la excepci�n
	 */
	public void testPonerPrecioHabRangoDiasConDiasRepetidos() 
			throws Excepcion1OMasDiasYaTenianPrecioSetteado{
		    
		when(per.algunDiaCoincide(periodos)).thenReturn(true);
		try{
			hotelero.ponerPrecioHabRangoDias(per, habitacion1);	
			fail("NO SE LANZO LA EXCEPCION DE DIASYASETTEADOS");
		}catch(Excepcion1OMasDiasYaTenianPrecioSetteado e){
				
		}
	}
	
	/**
	 * Testeo el m�todo subastarHabitacion()
	 */
	public void testSubastarHabitacion(){
		Subasta sub= mock(Subasta.class);
		SistemaDeBusqueda sistema= mock(SistemaDeBusqueda.class);
		
		hotelero.subastarHabitacion(sub, sistema);
		
		verify(sistema).agregarSubasta(sub);
	}
	
	}