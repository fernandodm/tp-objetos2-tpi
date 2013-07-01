package testOfertaHotelera;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import descuentos.Descuento;
import descuentos.DescuentoPorCantidadDeNoches;
import descuentos.DescuentoPorFecha;
import descuentos.DescuentoPorRangoDeFechas;
import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;

import ofertaHotelera.Auxiliar;
import ofertaHotelera.GeneradorDeCalendar;
import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Periodo;
import ofertaHotelera.PeriodoConPrecio;
import ofertaHotelera.PorPrecioPorEstadia;
import ofertaHotelera.PorPrecioPorNoche;
import ofertaHotelera.Preferencia;
import ofertaHotelera.PreferenciaPorLugar;
import ofertaHotelera.Reserva;
import ofertaHotelera.Usuario;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HabitacionTest extends TestCase {
	private Habitacion habitacion;
	private List<Periodo> diasReservados = new ArrayList<Periodo>();
	private Calendar fechaInicio1;
	private Calendar fechaFin2;
	private Calendar fechaInicio3;
	private Calendar fechaFin4;
	private Calendar fechaInicio5;
	private Calendar fechaFin6;
	private Periodo periodo1;
	private Periodo periodo2;
	private Periodo periodo3;
	private Usuario user;
	private PorPrecioPorNoche preferencia1;
	private PorPrecioPorEstadia preferencia2;
	private PreferenciaPorLugar preferencia3;
	private PreferenciaPorLugar preferencia4;
	private Hotel hotel;
	private List<Preferencia> preferencias = new ArrayList<Preferencia>();
	private List<Preferencia> unaPreferencia = new ArrayList<Preferencia>();
	private PeriodoConPrecio periPrecio1;
	private PeriodoConPrecio periPrecio2;
	private PeriodoConPrecio periPrecio3;
	
	
	public void setUp(){
		
		habitacion = new Habitacion();
			
		periodo1 = mock(Periodo.class);
		periodo2 = mock(Periodo.class);
		periodo3 = mock(Periodo.class);
		
		periPrecio1 = mock(PeriodoConPrecio.class);
		periPrecio2 = mock(PeriodoConPrecio.class);
		periPrecio3 = mock(PeriodoConPrecio.class);
		
		fechaInicio1 = Calendar.getInstance();
		fechaInicio1.set(2013,01,1,0,0,0);
		fechaInicio1.clear(Calendar.MILLISECOND);
		fechaFin2 = Calendar.getInstance();
		fechaFin2.set(2013,01,7,0,0,0);
		fechaFin2.clear(Calendar.MILLISECOND);
		when(periodo1.getDesde()).thenReturn(fechaInicio1);
		when(periodo1.getHasta()).thenReturn(fechaFin2);
		when(periPrecio1.getDesde()).thenReturn(fechaInicio1);
		when(periPrecio1.getHasta()).thenReturn(fechaFin2);
		when(periPrecio1.getPrecio()).thenReturn((float) 100);
		
		fechaInicio3 = Calendar.getInstance();
		fechaInicio3.set(2013,01,10,0,0,0);		
		fechaInicio3.clear(Calendar.MILLISECOND);
		fechaFin4 = Calendar.getInstance();
		fechaFin4.set(2013,01,17,0,0,0);
		fechaFin4.clear(Calendar.MILLISECOND);
		when(periodo2.getDesde()).thenReturn(fechaInicio3);
		when(periodo2.getHasta()).thenReturn(fechaFin4);
		when(periPrecio2.getDesde()).thenReturn(fechaInicio3);
		when(periPrecio2.getHasta()).thenReturn(fechaFin4);
		when(periPrecio2.getPrecio()).thenReturn((float) 200);
		
		fechaInicio5 = Calendar.getInstance();
		fechaInicio5.set(2013,01,20,0,0,0);
		fechaInicio5.clear(Calendar.MILLISECOND);
		fechaFin6 = Calendar.getInstance();
		fechaFin6.set(2013,01,27,0,0,0);
		fechaFin6.clear(Calendar.MILLISECOND);
		when(periodo3.getDesde()).thenReturn(fechaInicio5);
		when(periodo3.getHasta()).thenReturn(fechaFin6);
		when(periPrecio3.getDesde()).thenReturn(fechaInicio5);
		when(periPrecio3.getHasta()).thenReturn(fechaFin6);
		when(periPrecio3.getPrecio()).thenReturn((float) 300);
		
		diasReservados.add(periodo1);
		diasReservados.add(periodo2);
		diasReservados.add(periodo3);
		
		
		
		habitacion.setDiasOcupados(diasReservados);
		//habitacion.setPrecioPorNoche(120);

		
		user = mock(Usuario.class);
		preferencia1 = mock(PorPrecioPorNoche.class);
		preferencia2 = mock(PorPrecioPorEstadia.class);
		preferencia3 = mock(PreferenciaPorLugar.class);
		preferencia4 = mock(PreferenciaPorLugar.class);
		
		
		
		hotel = mock(Hotel.class);
	}
	
	public void testLePuedeInteresarAlUsuarioPorLaUbicacionUnaSolaPreferencia() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{

		
		when(preferencia3.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		unaPreferencia.add(preferencia3);
		when(user.getPreferencias()).thenReturn(unaPreferencia);
		
		Assert.assertTrue(habitacion.lePuedeInteresarAlUsuario(user));
	}
	
	public void testLePuedeInteresarAlUsuarioPorElPrecioPorFecha() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		when(preferencia1.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		preferencias.add(preferencia1);
		when(user.getPreferencias()).thenReturn(preferencias);
		Assert.assertTrue(habitacion.lePuedeInteresarAlUsuario(user));
	}
	
	public void testLePuedeInteresarAlUsuarioPorElPrecioPorEstadia() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		when(preferencia2.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		preferencias.add(preferencia2);
		when(user.getPreferencias()).thenReturn(preferencias);
		Assert.assertTrue(habitacion.lePuedeInteresarAlUsuario(user));
	}
	
	public void testLePuedeInteresarAlUsuarioConVariosCriteriosDePreferenciaTrue() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{

		when(preferencia1.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		when(preferencia2.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		when(preferencia3.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		
		preferencias.add(preferencia1);
		preferencias.add(preferencia2);
		preferencias.add(preferencia3);
		when(user.getPreferencias()).thenReturn(preferencias);
		Assert.assertTrue(habitacion.lePuedeInteresarAlUsuario(user));
	}
	
	public void testLePuedeInteresarAlUsuarioConAlgunCriterioQueNoCoincide() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{

		when(preferencia1.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		when(preferencia2.lePuedeInteresarHabitacion(habitacion)).thenReturn(false);
		when(preferencia3.lePuedeInteresarHabitacion(habitacion)).thenReturn(true);
		
		preferencias.add(preferencia1);
		preferencias.add(preferencia2);
		preferencias.add(preferencia3);
		when(user.getPreferencias()).thenReturn(preferencias);
		Assert.assertFalse(habitacion.lePuedeInteresarAlUsuario(user));
	}
	
	public void testHayPrecioParaLaFechaTrue(){
		
		List<PeriodoConPrecio> periPrecios = new ArrayList<PeriodoConPrecio>();
		periPrecios.add(periPrecio1);
		periPrecios.add(periPrecio2);
		periPrecios.add(periPrecio3);
		when(periPrecio1.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		when(periPrecio2.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(true);
		when(periPrecio3.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		habitacion.setPreciosPorFecha(periPrecios);
		Assert.assertTrue(habitacion.hayPrecioParaFecha(fechaInicio1));
	}
	
	public void testHayPrecioParaLaFechaFalse(){
		
		List<PeriodoConPrecio> periPrecios = new ArrayList<PeriodoConPrecio>();
		periPrecios.add(periPrecio1);
		periPrecios.add(periPrecio2);
		periPrecios.add(periPrecio3);
		when(periPrecio1.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		when(periPrecio2.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		when(periPrecio3.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		habitacion.setPreciosPorFecha(periPrecios);
		Assert.assertFalse(habitacion.hayPrecioParaFecha(fechaInicio1));
	}
	
	public void testPrecioParaLaFechaConPrecioEstablecido() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		List<PeriodoConPrecio> periPrecios = new ArrayList<PeriodoConPrecio>();
		periPrecios.add(periPrecio1);
		periPrecios.add(periPrecio2);
		periPrecios.add(periPrecio3);
		when(periPrecio1.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		when(periPrecio2.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(true);
		when(periPrecio3.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		habitacion.setPreciosPorFecha(periPrecios);
		
		Assert.assertEquals(habitacion.precioDeLaFecha(fechaInicio1), (float) 200);
		
	}
	
	public void testPrecioParaLaFechaConPrecioNOEstablecido(){
		
		List<PeriodoConPrecio> periPrecios = new ArrayList<PeriodoConPrecio>();
		periPrecios.add(periPrecio1);
		periPrecios.add(periPrecio2);
		periPrecios.add(periPrecio3);
		when(periPrecio1.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		when(periPrecio2.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		when(periPrecio3.fechaEstaEnElPeriodo(fechaInicio1)).thenReturn(false);
		habitacion.setPreciosPorFecha(periPrecios);
		
		try{
			habitacion.precioDeLaFecha(fechaInicio1);
			fail();
		} catch (ExcepcionNoHayPrecioEstablecidoParaTalFecha e){
			
		}
	}
	
	public void testPrecioPorNochePromedio(){
		
		List<PeriodoConPrecio> periPrecios = new ArrayList<PeriodoConPrecio>();
		periPrecios.add(periPrecio1);
		periPrecios.add(periPrecio2);
		periPrecios.add(periPrecio3);
		
		habitacion.setPreciosPorFecha(periPrecios);
		
		Assert.assertEquals(habitacion.precioPorNochePromedio(), (float) 200);
		
	}
	
	public void testEliminarHorario(){
		
		habitacion.eliminarHorario(periodo2);
		Assert.assertEquals(habitacion.getDiasOcupados().size(), 2);
		
	}
	
	/**
	 * Se testea el caso en que no este disponible
	 */
	public void testEstaDisponibleFalse(){
		
		Calendar fechaInicio7 = Calendar.getInstance();
		fechaInicio7.set(2013,01,17,0,0,0);
		fechaInicio7.clear(Calendar.MILLISECOND);
		Calendar fechaFin8 = Calendar.getInstance();
		fechaFin8.set(2013,01,9,0,0,0);
		fechaFin8.clear(Calendar.MILLISECOND);
		
		when(periodo1.estaEntre(fechaInicio7, fechaFin8)).thenReturn(false);	
		when(periodo2.estaEntre(fechaInicio7, fechaFin8)).thenReturn(false);
		when(periodo3.estaEntre(fechaInicio7, fechaFin8)).thenReturn(true);
				
		boolean estaDisponible = habitacion.estaDisponible(fechaInicio7, fechaFin8);
		
		Assert.assertFalse("FALLA testEstaDisponibleFalse()",estaDisponible);
	}
	
	/**
	 * Se testea el caso en que este disponible
	 */
	public void testEstaDisponibleTrue(){
				
		Calendar fechaInicio7 = Calendar.getInstance();
		fechaInicio7.set(2013,01,28,0,0,0);
		fechaInicio7.clear(Calendar.MILLISECOND);
		
		Calendar fechaFin8 = Calendar.getInstance();
		fechaFin8.set(2013,02,5,0,0,0);
		fechaFin8.clear(Calendar.MILLISECOND);
		
		boolean estaDisponible = habitacion.estaDisponible(fechaInicio7, fechaFin8);
		
		Assert.assertTrue("FALLA testEstaDisponibleFalse()",estaDisponible);
	}
	
	public void testPrecioTotal() throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
				
		Calendar fechaInicio7 = Calendar.getInstance();
		fechaInicio7.set(2013,01,03,0,0,0);
		fechaInicio7.clear(Calendar.MILLISECOND);
		
		Calendar fechaFin8 = Calendar.getInstance();
		fechaFin8.set(2013,01,04,0,0,0);
		fechaFin8.clear(Calendar.MILLISECOND);
						
		PeriodoConPrecio periodoConPrecio1 = mock(PeriodoConPrecio.class);
		PeriodoConPrecio periodoConPrecio2 = mock(PeriodoConPrecio.class);
			
		List<PeriodoConPrecio> preciosPorFecha = new ArrayList<PeriodoConPrecio>();
		
		preciosPorFecha.add(periodoConPrecio1);
		preciosPorFecha.add(periodoConPrecio2);
	
		when(periodoConPrecio1.getPrecio()).thenReturn(120f);
		when(periodoConPrecio2.getPrecio()).thenReturn(115.5f);
		
		when(periodoConPrecio1.fechaEstaEnElPeriodo(fechaInicio7)).thenReturn(true);
		
		when(periodoConPrecio2.fechaEstaEnElPeriodo(fechaFin8)).thenReturn(true);
				
		habitacion.setPreciosPorFecha(preciosPorFecha);
		
		float precio = habitacion.precioTotal(fechaInicio7, fechaFin8);
		System.out.println(precio);
		Assert.assertTrue(precio == 235.5f);
		
	}
	
	/**
	 * Se testea el caso en que la habitacion si tenga descuento
	 */
	public void testTieneDescuentoConDescuento(){
		
		List<Descuento> descuentos = new ArrayList<Descuento>();
		DescuentoPorCantidadDeNoches descuento = mock(DescuentoPorCantidadDeNoches.class); 
		
		descuentos.add(descuento);
		
		habitacion.setDescuentos(descuentos);
		
		boolean tieneDescuento = habitacion.tieneDescuentos();
		
		Assert.assertTrue(tieneDescuento);
		
	}
	
	/**
	 * Se testea el caso de que la habitacion no tenga descuento
	 */
	public void testTieneDescuentoSinDescuento(){
		
		boolean tieneDescuento = habitacion.tieneDescuentos();
		
		Assert.assertFalse(tieneDescuento);
		
	}
	
	public void testObtenerDescuento(){
	
		List<Descuento> descuentos = new ArrayList<Descuento>();
		DescuentoPorCantidadDeNoches descuento1 = mock(DescuentoPorCantidadDeNoches.class); 
		DescuentoPorFecha descuento2 = mock(DescuentoPorFecha.class);
		
		descuentos.add(descuento1);
		descuentos.add(descuento2);
		
		habitacion.setDescuentos(descuentos);
		when(descuento1.descuento()).thenReturn("Hola ");
		when(descuento2.descuento()).thenReturn("a todos.");
		
		String desc = habitacion.obtenerDescuento();
		
		Assert.assertEquals(desc, "Hola a todos.");
	}
	
	public void testAgregarReserva(){
		
		Periodo periodo = mock(Periodo.class);
		
		habitacion.agregarDiaReservado(periodo);
		
		int cantidad = habitacion.getDiasOcupados().size();
		Periodo peri = habitacion.getDiasOcupados().get(3);
		
		Assert.assertTrue(cantidad == 4);
		Assert.assertTrue(periodo == peri);
	}
	
	/**
	 * Se testea el caso de que tenga cama twin
	 */
	public void testTieneCamaTwinSi(){
		
		habitacion.setCamaTwin(true);
		
		String tiene = habitacion.tieneCamaTwin();
		
		Assert.assertEquals(tiene, "Si");
		
	}
	
	/**
	 * Se testea el caso de que no tnga cama twin
	 */
	public void testTieneCamaTwinNo(){
		
		habitacion.setCamaTwin(false);
		
		String tiene = habitacion.tieneCamaTwin();
		
		Assert.assertEquals(tiene, "No");
		
	}
}
