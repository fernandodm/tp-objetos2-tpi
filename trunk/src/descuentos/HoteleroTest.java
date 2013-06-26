package descuentos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import descuentos.Descuento;
import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Hotelero;
import junit.framework.Assert;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class HoteleroTest extends TestCase {

	private Hotel hotel;
	private Hotelero hotelero;
	private Habitacion habitacion1;
	private Habitacion habitacion2;
	private Calendar inicio;
	private Calendar fin;
	private List<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private List<Calendar> dias = new ArrayList<Calendar>();

	public void setUp(){
		hotelero = new Hotelero("Steve", new ArrayList<Hotel>(), "loquillo@gmail.com");
		hotel = mock(Hotel.class);
		habitacion1 = new Habitacion(hotel, 5, true, 100, 1);
		habitacion2 = new Habitacion(hotel, 4, false, 200, 2);
		inicio = Calendar.getInstance();
		fin= (GregorianCalendar) inicio;
		fin.add(Calendar.DATE, 1); 
		dias.add(inicio);
		dias.add((Calendar)fin);
	}
	
	public void testAgregarDescuento(){
		habitaciones.add(habitacion1);
		habitaciones.add(habitacion2);
		Descuento descuento1 = mock(Descuento.class);
		Descuento descuento2= mock(Descuento.class);
		hotelero.agregarDescuento(descuento1, habitaciones);
		hotelero.agregarDescuento(descuento2, habitaciones);
		int descHab1= habitacion1.getDescuentos().size();
		int descHab2= habitacion2.getDescuentos().size();
		int resultadoEsperado = 4;
		Assert.assertEquals(resultadoEsperado , descHab1+descHab2);
	}
	
	public void testPonerPrecioHabRangoDiasSinDiasRepetidos(){
		Integer resultadoEsperado = 100;
		hotelero.ponerPrecioHabRangoDias(dias, resultadoEsperado, habitacion1);
		Integer precio =  habitacion1.getPreciosPorFecha().get(dias);
		Assert.assertEquals(resultadoEsperado , precio);
	}
	
	public void testPonerPrecioHabRangoDiasConDiasRepetidos(){
		//TODO aca deberia chequear que se lanzo la excepcion.
	}	
	
}