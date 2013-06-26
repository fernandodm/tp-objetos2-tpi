package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import excepciones.ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado;

import ofertaHotelera.Calificacion;
import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Reserva;
import ofertaHotelera.SistemaDeBusqueda;
import ofertaHotelera.SoloImportaElLugar;
import ofertaHotelera.Usuario;
import junit.framework.TestCase;

public class HotelTest extends TestCase{
	private Hotel hotel;
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	private Calificacion calificacion;
	private Calificacion calificacion2;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private Habitacion habitacion1;
	private Habitacion habitacion2;
	private Habitacion habitacion3;
	private Calendar fecha1;
	private Calendar fecha2;
	private Calendar fecha3;
	private Calendar fecha4;
	private Calendar fecha5;
	private Calendar fecha6;
	private Usuario usuario;
	private SoloImportaElLugar preferenciaPorLugar;
	private SistemaDeBusqueda sistema;
	
	public void setUp(){
		
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		
		hotel = new Hotel();
		
		fecha1 = Calendar.getInstance();
		fecha1.set(fecha1.get(fecha1.YEAR),fecha1.get(fecha1.MONTH), fecha1.get(fecha1.DATE),0,0,0);
		fecha2 = Calendar.getInstance();
		fecha2.set(fecha2.get(fecha2.YEAR),fecha2.get(fecha2.MONTH), fecha2.get(fecha2.DATE) + 7,0,0,0);
		fecha3 = Calendar.getInstance();
		fecha3.set(fecha3.get(fecha3.YEAR),fecha3.get(fecha3.MONTH), fecha3.get(fecha3.DATE) - 3,0,0,0);
		fecha4 = Calendar.getInstance();
		fecha4.set(fecha4.get(fecha4.YEAR),fecha4.get(fecha4.MONTH), fecha4.get(fecha4.DATE) + 4,0,0,0);
		fecha5 = Calendar.getInstance();
		fecha5.set(fecha5.get(fecha5.YEAR),fecha5.get(fecha5.MONTH), fecha5.get(fecha5.DATE) - 7,0,0,0);
		fecha6 = Calendar.getInstance();
		fecha6.set(fecha6.get(fecha6.YEAR),fecha6.get(fecha6.MONTH), fecha6.get(fecha6.DATE),0,0,0);
		
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		
		habitacion1 = mock(Habitacion.class);
		habitacion2 = mock(Habitacion.class);
		habitacion3 = mock(Habitacion.class);
		
		when(habitacion1.estaDisponible(fecha1, fecha2)).thenReturn(false);
		when(habitacion2.estaDisponible(fecha1, fecha2)).thenReturn(true);
		when(habitacion3.estaDisponible(fecha1, fecha2)).thenReturn(false);
		
		when(habitacion1.getCapacidadMaxima()).thenReturn(2);
		when(habitacion2.getCapacidadMaxima()).thenReturn(3);
		when(habitacion3.getCapacidadMaxima()).thenReturn(3);
		
		when(reserva1.getFechaDeIngreso()).thenReturn(fecha1);
		when(reserva1.getFechaDeSalida()).thenReturn(fecha2);
		when(reserva2.getFechaDeIngreso()).thenReturn(fecha3);
		when(reserva2.getFechaDeSalida()).thenReturn(fecha4);
		when(reserva3.getFechaDeIngreso()).thenReturn(fecha5);
		when(reserva3.getFechaDeSalida()).thenReturn(fecha6);
					
		reservas.add(reserva1);
		reservas.add(reserva2);
		reservas.add(reserva3);
		
		habitaciones.add(habitacion1);
		habitaciones.add(habitacion2);
		habitaciones.add(habitacion3);
		
		sistema = mock(SistemaDeBusqueda.class);
		
		hotel.setReservas(reservas);
		hotel.setCalificaciones(calificaciones);
		hotel.setHabitaciones(habitaciones);
		hotel.setSistemaEnElQueEstaCargado(sistema);
		
		calificacion = mock(Calificacion.class);
		when(calificacion.getPuntaje()).thenReturn(8);
		when(calificacion.getComentario()).thenReturn("Muy bien");
		
		calificacion2 = mock(Calificacion.class);
		when(calificacion2.getPuntaje()).thenReturn(6);
		when(calificacion2.getComentario()).thenReturn("Podria mejorar");
		
		preferenciaPorLugar = mock(SoloImportaElLugar.class);
		when(preferenciaPorLugar.getPaisDelHotel()).thenReturn("China");
		when(preferenciaPorLugar.getCiudadDelHotel()).thenReturn("Beijing");
		
		usuario = mock(Usuario.class);
		when(usuario.getPreferencia()).thenReturn(preferenciaPorLugar);
		
	}
	
	public void testEliminarReserva(){
		
		hotel.eliminarReserva(reserva2);
		int cantidadReservas = hotel.getReservas().size();
		Reserva reserv1 = hotel.getReservas().get(0);
		Reserva reserv2 = hotel.getReservas().get(1);
		Assert.assertEquals(cantidadReservas, 2);
		Assert.assertEquals(reserv1, reserva1);
		Assert.assertEquals(reserv2, reserva3);
		
	}
	
	public void testReservasActuales(){
		
		////REVISAR
		
		List<Reserva> actuales = hotel.reservasActuales();
		Assert.assertEquals(actuales.size(), 2);
		Assert.assertTrue(actuales.get(0) == reserva1);
		Assert.assertTrue(actuales.get(1) == reserva2);
	}
	
	public void testReservasEnLosSiguientesNDias(){
		
		Reserva reserva4 = mock(Reserva.class);
		Reserva reserva5 = mock(Reserva.class);
		
		Calendar fecha7 = Calendar.getInstance();
		fecha7.set(fecha7.get(fecha7.YEAR),fecha7.get(fecha7.MONTH), fecha7.get(fecha7.DATE) + 1,0,0,0);
		Calendar fecha8 = Calendar.getInstance();
		fecha8.set(fecha8.get(fecha8.YEAR),fecha8.get(fecha8.MONTH), fecha8.get(fecha8.DATE) + 4,0,0,0);
		
		when(reserva4.getFechaDeIngreso()).thenReturn(fecha7);
		when(reserva5.getFechaDeIngreso()).thenReturn(fecha8);
		
		reservas.add(reserva4);
		reservas.add(reserva5);
		
		List<Reserva> reservasSiguientes = hotel.reservasEnLosSiguientesNDias(4);
		Reserva r1 = reservasSiguientes.get(0);
		Reserva r2 = reservasSiguientes.get(1);
		Assert.assertEquals(reservasSiguientes.size(), 2);
		Assert.assertTrue(r1 == reserva4);
		Assert.assertTrue(r2 == reserva5);
	}
	
	public void testReservasFuturas(){
		
		Reserva reserva4 = mock(Reserva.class);
		Reserva reserva5 = mock(Reserva.class);
		
		Calendar fecha7 = Calendar.getInstance();
		fecha7.set(fecha7.get(fecha7.YEAR) + 1,fecha7.get(fecha7.MONTH), fecha7.get(fecha7.DATE),0,0,0);
		Calendar fecha8 = Calendar.getInstance();
		fecha8.set(fecha8.get(fecha8.YEAR),fecha8.get(fecha8.MONTH), fecha8.get(fecha8.DATE) + 19,0,0,0);
		
		when(reserva4.getFechaDeIngreso()).thenReturn(fecha7);
		when(reserva5.getFechaDeIngreso()).thenReturn(fecha8);
		
		reservas.add(reserva4);
		reservas.add(reserva5);
		
		List<Reserva> futuras = hotel.reservasFuturas();
		Reserva r1 = futuras.get(0);
		Reserva r2 = futuras.get(1);
		Assert.assertEquals(futuras.size(), 2);
		Assert.assertTrue(r1 == reserva4);
		Assert.assertTrue(r2 == reserva5);
	}
		
	public void testTieneHabitacionConTrue(){
		// este test encuentra una habitacion, devuelve true 
		boolean tieneHabitacion = hotel.tieneHabitacionesCon(fecha1, fecha2, 3);
		Assert.assertTrue(tieneHabitacion);
	}
	
	public void testTieneHabitacionConFalse(){
		// este test no encuentra una habitacion, devuelve false
		boolean tieneHabitacion = hotel.tieneHabitacionesCon(fecha1, fecha2, 2);
		Assert.assertFalse(tieneHabitacion);
	}
	
	public void testAgregarCalificacion() throws ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado{
		
		
		
		hotel.agregarCalificacion(calificacion);
		


		Assert.assertEquals(hotel.getCalificaciones().size(), 1);

		Assert.assertTrue((hotel.getCalificaciones()).get(0)== calificacion);

		Assert.assertTrue((hotel.getCalificaciones()).get(0)== calificacion);
		}
	


	
	public void testCalificacionPromedio() throws ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado{


		hotel.agregarCalificacion(calificacion);
		hotel.agregarCalificacion(calificacion2);


		
		Integer prom = hotel.calificacionPromedio();
		Assert.assertTrue(prom.equals(7));
	}
	
	public void lePuedeInteresarAlUsuarioPorUbicacion(){
		
		hotel.setPais("China");
		hotel.setCiudad("Beijing");
		Assert.assertTrue(hotel.lePuedeInteresarAlUsuario(usuario));
		
	}

	
	public void noLePuedeInteresarAlUsuarioPorUbicacion(){
		

		hotel.agregarCalificacion(calificacion);
		hotel.agregarCalificacion(calificacion2);


		

		Integer prom = hotel.calificacionPromedio();
		Assert.assertTrue(prom.equals(7));

		hotel.setPais("China");
		hotel.setCiudad("Chinchon");
		Assert.assertFalse(hotel.lePuedeInteresarAlUsuario(usuario));

		
	}
	


		

	public void testActualizarInformacion(){
		hotel.actualizarInformacion();
		verify(sistema).actualizarOfertaDelHotel(hotel);

	}
	


	


	
}
