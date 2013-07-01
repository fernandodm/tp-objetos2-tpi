package testOfertaHotelera;

import java.util.ArrayList;
import java.util.List;
import descuentos.Descuento;
import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Hotelero;
import ofertaHotelera.PeriodoConPrecio;
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
	
	public void setUp(){
		hotel = mock(Hotel.class);
		hoteles.add(hotel);
		hotelero = new Hotelero("Steve", hoteles, "loquillo@gmail.com");
		habitacion1 = mock(Habitacion.class);
		habitacion2 = mock(Habitacion.class);
	}
	
	public void testConstructorHotelero(){
		Assert.assertEquals("Steve", hotelero.getNombre());
		Assert.assertEquals(hotel, hotelero.getMisHoteles().get(0));
		Assert.assertEquals("loquillo@gmail.com", hotelero.getMail());		
	}
	
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
	
	public void testPonerPrecioHabRangoDiasSinDiasRepetidos(){
		PeriodoConPrecio per= mock(PeriodoConPrecio.class); 
		when(habitacion1.getPreciosPorFecha()).thenReturn(periodos);
		hotelero.ponerPrecioHabRangoDias(per, habitacion1);
		PeriodoConPrecio resultadoEsperado= periodos.get(0);
		
		Assert.assertEquals(resultadoEsperado , per);
	}
	
	public void testPonerPrecioHabRangoDiasConDiasRepetidos(){
		//TODO aca deberia chequear que se lanzo la excepcion.
	}	
	
}