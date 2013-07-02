package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Action;

import excepciones.ExcepcionHabitacionNoDisponible;
import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import excepciones.ExcepcionNoEstaOnline;
import excepciones.ExcepcionNoSeEncontroReserva;
import excepciones.ExcepcionOfertaInferior;
import excepciones.ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia;
import excepciones.ExcepcionTodaviaNoSeHospedoEnEsteHotel;


import junit.framework.Assert;
import junit.framework.TestCase;
import ofertaHotelera.Calificacion;
import ofertaHotelera.EnCurso;
import ofertaHotelera.EstadoSubasta;
import ofertaHotelera.Finalizada;
import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Periodo;
import ofertaHotelera.PorPrecioPorEstadia;
import ofertaHotelera.PorPrecioPorNoche;
import ofertaHotelera.Preferencia;
import ofertaHotelera.SistemaDeBusqueda;
import ofertaHotelera.PreferenciaPorLugar;
import ofertaHotelera.Subasta;
import ofertaHotelera.SubastaFutura;
import ofertaHotelera.Usuario;
import ofertaHotelera.Reserva;

public class UsuarioTest extends TestCase {
	private Usuario usuario;
	private Usuario usuario2;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	private List<Reserva> reservasUsuario = new ArrayList<Reserva>();
	private Hotel hotel1;
	private Calendar fecha1;
	private Subasta subasta;
	private EstadoSubasta estadoSubasta;
	private SubastaFutura subastaFutura;
	private EnCurso subastaEnCurso;
	private Finalizada subastaFinalizada;
	private PreferenciaPorLugar preferenciaPorLugar;
	private PorPrecioPorEstadia preferenciaPorPrecioEstadia;
	private PorPrecioPorNoche preferenciaPorPrecioPorNoche;
	private SistemaDeBusqueda sistema;
	private Periodo periodo1;
	private Habitacion habitacion;
	
	
	public void setUp(){
		
		habitacion = mock(Habitacion.class);
	
		fecha1 = Calendar.getInstance();
		fecha1.set(fecha1.get(fecha1.YEAR),fecha1.get(fecha1.MONTH), fecha1.get(fecha1.DATE) - 3,0,0,0);
		
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		hotel1 = mock(Hotel.class);
		periodo1 = mock(Periodo.class);
		
		when(periodo1.getHasta()).thenReturn(fecha1);
		
		when(reserva1.getHotel()).thenReturn(hotel1);
		when(reserva2.getHotel()).thenReturn(hotel1);
		when(reserva3.getHotel()).thenReturn(hotel1);
		when(reserva1.getPeriodo()).thenReturn(periodo1);
		when(reserva1.ciudadDelHotel()).thenReturn("Sidney");
		when(reserva2.ciudadDelHotel()).thenReturn("Moscu");
		when(reserva3.ciudadDelHotel()).thenReturn("Sidney");
		
		reservasUsuario.add(reserva1);
		reservasUsuario.add(reserva2);
		reservasUsuario.add(reserva3);
		
		usuario2 = new Usuario();
		usuario2.setOnline(false);
		usuario2.setReservas(reservasUsuario);
		
		usuario = new Usuario();	
		usuario.setOnline(true);
		usuario.setReservas(reservasUsuario);
		
		subasta = mock(Subasta.class);
		subastaFinalizada = mock(Finalizada.class);
		subastaFutura = mock(SubastaFutura.class);
		subastaEnCurso = mock(EnCurso.class);
		
		sistema = mock(SistemaDeBusqueda.class);
		
		preferenciaPorLugar = mock(PreferenciaPorLugar.class);
		preferenciaPorPrecioPorNoche = mock(PorPrecioPorNoche.class);
		preferenciaPorPrecioEstadia = mock(PorPrecioPorEstadia.class);
	}
	
	/**
	 * Testeo el caso en que el usuario quiere reservar y no esta online
	 * @throws ExcepcionNoEstaOnline 
	 * @throws ExcepcionHabitacionNoDisponible 
	 */
	public void testReservarHabitacionConUsuarioOffLine() throws ExcepcionHabitacionNoDisponible, ExcepcionNoEstaOnline{
		
		try{
			usuario2.reservarHabitacion(habitacion, "", fecha1, fecha1);
			fail();
		}catch(ExcepcionNoEstaOnline e){
			
		}
	}
	
	/**
	 * Testeo el caso que el usuario quiere reserva y esta online
	 * @throws ExcepcionNoEstaOnline 
	 * @throws ExcepcionHabitacionNoDisponible 
	 */
	public void testReservarHabitacionConUsuarioOnline() throws ExcepcionHabitacionNoDisponible, ExcepcionNoEstaOnline{
		
		usuario.setSistema(sistema);
		usuario.reservarHabitacion(habitacion, "", fecha1, fecha1);
		
		verify(sistema).realizarReserva(usuario,habitacion, "", fecha1, fecha1);
	}
	
	/**
	 * Se testea todoasLasReservas() con usuario offline
	 */
	public void testTodasLasReservasConUsuarioOffLine(){
		
		try{
			usuario2.todasLasReservas();
			fail("NO SE LANZO LA EXCEPCION DE todasLasReservas()");
		}catch(ExcepcionNoEstaOnline e){

		}
	}
	
	/**
	 * Se testea todoasLasReservas() con usuario online
	 * @throws ExcepcionNoEstaOnline
	 */
	public void testTodasLasReservasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<Reserva> reservas = usuario.todasLasReservas();
		Assert.assertEquals("NO HAY LAS RESERVAS ESPERADAS",reservas.size(), 3 );
	}
	
	/**
	 * Reserva por ciudad con usuario offline
	 */
	public void testReservaPorCiudadConUsuarioOffLine(){
	
		try{
			usuario2.reservaPorCiudad("Nueva York");
			fail("NO SE LANZO LA EXCEPCION DE reservaPorCiudad()");
		}catch(ExcepcionNoEstaOnline e){
			
		}
	}
	
	/**
	 * Reserva por ciudad con usuario online
	 * @throws ExcepcionNoEstaOnline
	 */
	public void testReservaPorCiudadConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<Reserva> reservas = usuario.reservaPorCiudad("Sidney");
		Assert.assertEquals("LA CIUDAD NO COINCIDE CON LA ESPERADA", reservas.size(), 2);
		//verifico que sean los objetos deseados
		Assert.assertTrue(reservas.get(0) == reserva1);
		Assert.assertTrue(reservas.get(1) == reserva3);
	}

	/**
	 * Se testea CiudadesConReservas con un usuario offline
	 */
	public void testCiudadesConReservasConUsuarioOffLine(){
		
		try{
			usuario2.ciudadesConReservas();
			fail("NO SE LANZO LA EXCEPCION DE ciudadesConReservas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
	}
	
	/**
	 * Se testea CiudadesConReservas con un usuario online
	 * @throws ExcepcionNoEstaOnline
	 */
	public void testCiudadesConReservasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<String> ciudades = usuario.ciudadesConReservas();
		Assert.assertEquals("FALLA CIUDADES CON RESERVAS", ciudades.size(), 3);
		//verifico que sean las ciudades correctas
		Assert.assertTrue(ciudades.get(0).equals("Sidney"));
		Assert.assertTrue(ciudades.get(1).equals("Moscu"));
		Assert.assertTrue(ciudades.get(2).equals("Sidney"));
	}
	
	/**
	 * Se testea el caso en que el usuario pide las reservas futuras estando offline
	 */
	public void testReservasFuturasConUsuarioOffLine(){
		
		try{
			usuario2.reservasFuturas();
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
	}
	
	/**
	 * Se testea el caso en que el usuario pide las reservas futuras estando online
	 * @throws ExcepcionNoEstaOnline
	 */
	public void testReservasFuturasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		when(reserva1.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(true);
		when(reserva2.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(false);
		when(reserva3.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(true);
		
		List<Reserva> futuras = usuario.reservasFuturas();
		Assert.assertEquals(futuras.size(), 2);
	}
	
	/**
	 * Se testea el caso que queiere cancelar una reserva estando online
	 * @throws ExcepcionNoEstaOnline
	 * @throws ExcepcionNoSeEncontroReserva
	 */
	public void testCancelarReservaConUsuarioOnline() throws ExcepcionNoEstaOnline, ExcepcionNoSeEncontroReserva{
		
		usuario.cancelarReserva(reserva1);
		Reserva r1 = usuario.getReservas().get(0);
		Reserva r2 = usuario.getReservas().get(1);
		int cantReservas = usuario.getReservas().size();
		Assert.assertEquals(cantReservas, 2);
		Assert.assertTrue(r1 == reserva2);
		Assert.assertTrue(r2 == reserva3);
	}
	
	/**
	 * Se testea el caso que queiere cancelar una reserva estando offLine
	 * @throws ExcepcionNoSeEncontroReserva
	 */
	public void testCancelarReservaConUsuarioOffline() throws ExcepcionNoSeEncontroReserva{
		Reserva reserva4 = mock(Reserva.class);
		try{
			usuario2.cancelarReserva(reserva4);
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
	}
	
	/**
	 *Se testea el caso que se quiere cancelar una reserva y no se encontro la reserva 
	 * @throws ExcepcionNoEstaOnline
	 */
	public void testCancelarReservaConReservaNoEncontrada() throws ExcepcionNoEstaOnline{
		Reserva reserva4 = mock(Reserva.class);
		try{
			usuario.cancelarReserva(reserva4);
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoSeEncontroReserva e){
	
		}
	}
	
	public void testCalificarHotelEnElQueSiSeHospedoEstandoLogueado() throws ExcepcionNoEstaOnline, ExcepcionTodaviaNoSeHospedoEnEsteHotel{
		
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();

		when(hotel1.getCalificaciones()).thenReturn(calificaciones);
		
		usuario.calificarHotel(hotel1, 9, "Bien ahi");

		verify(hotel1).agregarCalificacion(usuario,9,"Bien ahi");

	}
	
	public void testCalificarHotelEnElQueSiSeHospedoNOEstandoLogueado() throws ExcepcionTodaviaNoSeHospedoEnEsteHotel{
		
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();

		when(hotel1.getCalificaciones()).thenReturn(calificaciones);
		
		try{
			usuario2.calificarHotel(hotel1, 9, "Bien ahi");
		} catch (ExcepcionNoEstaOnline e){
			
		}
	}
	
	public void testCalificarHotelEnElQueTodaviaNoSeHospedo() throws ExcepcionNoEstaOnline{
		
		Hotel hotelNoVisitado = mock(Hotel.class);
		try{
			usuario.calificarHotel(hotelNoVisitado, 7, "Copado!");
			fail();
		} catch (ExcepcionTodaviaNoSeHospedoEnEsteHotel e){
			
		}
		
	}
	
	public void testOfertarEnSubastaFuturaSinEstarLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior {
		
		when(subasta.getEstado()).thenReturn(subastaFutura);
		try{
			usuario2.ofertarEnSubasta(subasta,120);
			fail();
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	

	public void testOfertarEnSubastaFinalizadaEstandoLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline, ExcepcionLaSubastaYaHaFinalizado{
		
		usuario.ofertarEnSubasta(subasta,100);
		verify(subasta).agragarOferta(usuario, 100);
	
	}
	
	public void testOfertarEnSubastaFinalizadaSinEstarLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior {
		
		

		when(subasta.getEstado()).thenReturn(subastaFinalizada);
		try{
			usuario2.ofertarEnSubasta(subasta,120);
			fail();
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	
	public void testOfertarEnSubastaEnCursoEstandoLogueadoConOfertaInferior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		when(subasta.getValor()).thenReturn((float) 200);
		try{
			usuario.ofertarEnSubasta(subasta, 100);
		} catch(ExcepcionOfertaInferior e){
			
		}
	}
	
	public void testOfertarEnSubastaEnCursoEstandoLogueadoConOfertaSuperior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		when(subasta.getValor()).thenReturn((float) 200);
		usuario.ofertarEnSubasta(subasta, 250);
		verify(subasta).agragarOferta(usuario, 250);
	}
	
	public void testOfertarEnSubastaEnCursoSinEstarLogueadoConOfertaInferior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior{
		
		

		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		try{
			usuario.ofertarEnSubasta(subasta,120);
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	public void testOfertarEnSubastaEnCursoSinEstarLogueadoConOfertaSuperior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior{
		
		

		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		try{
			usuario.ofertarEnSubasta(subasta,300);
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	public void testSuscribirseAlAvisoDeOfertasHotelerasOnlineConPreferencia() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia, ExcepcionNoEstaOnline{
		
		List<Preferencia> prefs = new ArrayList<Preferencia>();
		prefs.add(preferenciaPorLugar);
		usuario.setPreferencias(prefs);
		usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
		
		verify(sistema).agregarSuscripto(usuario);
		
		
	}
	
	public void testSuscribirseAlAvisoDeOfertasHotelerasOnlineSinPreferencia() throws ExcepcionNoEstaOnline{
		
		List<Preferencia> prefs = new ArrayList<Preferencia>();
		usuario.setPreferencias(prefs);
		try{
			usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
		} catch (ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia e){
			
		}
	}
	
	public void testSuscribirseAlAvisoDeOfertasHotelerasOffline() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia{
		
		try{
			usuario2.suscribirseAlAvisoDeOfertasHoteleras(sistema);
		} catch (ExcepcionNoEstaOnline e){
			
		}
	}
	
	public void testAgregarReserva(){
		
		Reserva reserva = mock(Reserva.class);
		
		usuario.agregarReserva(reserva);
		
		int cantidad = usuario.getReservas().size();
		
		Assert.assertTrue(cantidad == 4);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
