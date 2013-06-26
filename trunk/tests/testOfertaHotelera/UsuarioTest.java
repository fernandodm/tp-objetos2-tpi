package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Action;

import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import excepciones.ExcepcionNoEstaOnline;
import excepciones.ExcepcionNoSeEncontroReserva;
import excepciones.ExcepcionOfertaInferior;
import excepciones.ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia;
import excepciones.ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado;

import junit.framework.Assert;
import junit.framework.TestCase;
import ofertaHotelera.Calificacion;
import ofertaHotelera.EnCurso;
import ofertaHotelera.EstadoSubasta;
import ofertaHotelera.Finalizada;
import ofertaHotelera.Hotel;
import ofertaHotelera.PorPrecioPorEstadia;
import ofertaHotelera.PorPrecioPorNoche;
import ofertaHotelera.SistemaDeBusqueda;
import ofertaHotelera.SoloImportaElLugar;
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
	private SoloImportaElLugar preferenciaPorLugar;
	private PorPrecioPorEstadia preferenciaPorPrecioEstadia;
	private PorPrecioPorNoche preferenciaPorPrecioPorNoche;
	private SistemaDeBusqueda sistema;
	
	public void setUp(){
		
		fecha1 = Calendar.getInstance();
		fecha1.set(fecha1.get(fecha1.YEAR),fecha1.get(fecha1.MONTH), fecha1.get(fecha1.DATE) - 3,0,0,0);
		
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);
		hotel1 = mock(Hotel.class);
		
		when(reserva1.getHotel()).thenReturn(hotel1);
		when(reserva1.getFechaDeSalida()).thenReturn(fecha1);
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
		
		preferenciaPorLugar = mock(SoloImportaElLugar.class);
		preferenciaPorPrecioPorNoche = mock(PorPrecioPorNoche.class);
		preferenciaPorPrecioEstadia = mock(PorPrecioPorEstadia.class);
	}
	
	public void testTodasLasReservasConUsuarioOffLine(){
		
		try{
			usuario2.todasLasReservas();
			fail("NO SE LANZO LA EXCEPCION DE todasLasReservas()");
		}catch(ExcepcionNoEstaOnline e){

		}
	}
	
	public void testTodasLasReservasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<Reserva> reservas = usuario.todasLasReservas();
		Assert.assertEquals("NO HAY LAS RESERVAS ESPERADAS",reservas.size(), 3 );
	}
	
	public void testReservaPorCiudadConUsuarioOffLine(){
	
		try{
			usuario2.reservaPorCiudad("Nueva York");
			fail("NO SE LANZO LA EXCEPCION DE reservaPorCiudad()");
		}catch(ExcepcionNoEstaOnline e){
			
		}
	}
	
	public void testReservaPorCiudadConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<Reserva> reservas = usuario.reservaPorCiudad("Sidney");
		Assert.assertEquals("LA CIUDAD NO COINCIDE CON LA ESPERADA", reservas.size(), 2);
		//verifico que sean los objetos deseados
		Assert.assertTrue(reservas.get(0) == reserva1);
		Assert.assertTrue(reservas.get(1) == reserva3);
	}

	public void testCiudadesConReservasConUsuarioOffLine(){
		
		try{
			usuario2.ciudadesConReservas();
			fail("NO SE LANZO LA EXCEPCION DE ciudadesConReservas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	public void testCiudadesConReservasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		List<String> ciudades = usuario.ciudadesConReservas();
		Assert.assertEquals("FALLA CIUDADES CON RESERVAS", ciudades.size(), 3);
		//verifico que sean las ciudades correctas
		Assert.assertTrue(ciudades.get(0).equals("Sidney"));
		Assert.assertTrue(ciudades.get(1).equals("Moscu"));
		Assert.assertTrue(ciudades.get(2).equals("Sidney"));
	}
	
	public void testReservasFuturasConUsuarioOffLine(){
		
		try{
			usuario2.reservasFuturas();
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
	}
	
	public void testReservasFuturasConUsuarioOnline() throws ExcepcionNoEstaOnline{
		
		when(reserva1.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(true);
		when(reserva2.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(false);
		when(reserva3.estaReservadaDespuesDe(any(Calendar.class))).thenReturn(true);
		
		List<Reserva> futuras = usuario.reservasFuturas();
		Assert.assertEquals(futuras.size(), 2);
	}
	
	public void testCancelarReservaConUsuarioOnline() throws ExcepcionNoEstaOnline, ExcepcionNoSeEncontroReserva{
		
		usuario.cancelarReserva(reserva1);
		Reserva r1 = usuario.getReservas().get(0);
		Reserva r2 = usuario.getReservas().get(1);
		int cantReservas = usuario.getReservas().size();
		Assert.assertEquals(cantReservas, 2);
		Assert.assertTrue(r1 == reserva2);
		Assert.assertTrue(r2 == reserva3);
	}
	
	public void testCancelarReservaConUsuarioOffline() throws ExcepcionNoSeEncontroReserva{
		Reserva reserva4 = mock(Reserva.class);
		try{
			usuario2.cancelarReserva(reserva4);
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
	}
	
	public void testCancelarReservaConReservaNoEncontrada() throws ExcepcionNoEstaOnline{
		Reserva reserva4 = mock(Reserva.class);
		try{
			usuario.cancelarReserva(reserva4);
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoSeEncontroReserva e){
	
		}
	}
	
	public void testCalificarHotelEstandoLogueado() throws ExcepcionNoEstaOnline, ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado{
		

		
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();

		

		usuario.calificarHotel(hotel1, 9, "Bien ahi");

		when(hotel1.getCalificaciones()).thenReturn(calificaciones);



		
		verify(hotel1).equals(hotel1);



	}

	public void testCalificarHotelSinEstarLogueado() throws ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado  {
		
		try{

			usuario2.calificarHotel(hotel1, 8,"Muy bien");
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");


		}catch(ExcepcionNoEstaOnline e){
			
		}

	}
	
	public void testOfertarEnSubastaFuturaEstandoLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaFutura);
		try{
			usuario.ofertarEnSubasta(subasta, 100);
		} catch(ExcepcionLaSubastaAunNoHaIniciado e){
			
		}
	}
		
	public void testOfertarEnSubastaFuturaSinEstarLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior {
		
		

		when(subasta.getEstado()).thenReturn(subastaFutura);
		try{
			usuario2.ofertarEnSubasta(subasta,120);
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	

	public void testOfertarEnSubastaFinalizadaEstandoLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaFinalizada);
		
		try{
			usuario.ofertarEnSubasta(subasta,100);
			verify(subasta).agragarOferta(usuario, 100);
			fail("NO SE LANZO LA EXCEPCION DE ofertarEnSubasta()");
		} catch(ExcepcionLaSubastaYaHaFinalizado e){
			
		}
		
	
	}
	
	public void testOfertarEnSubastaFinalizadaSinEstarLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior {
		
		

		when(subasta.getEstado()).thenReturn(subastaFinalizada);
		try{
			usuario2.ofertarEnSubasta(subasta,120);
			fail("NO SE LANZO LA EXCEPCION DE reservasFuturas()");
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
	
	public void testSuscribirseAlAvisoDeOfertasHotelerasSinEstarLogueado() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		usuario.setPreferencia(preferenciaPorLugar);
		try{
			usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
		
		} catch (ExcepcionNoEstaOnline e) {
			
		}
		
	}
	
	public void testSuscribirseAlAvisoDeOfertasHotelerasEstandoLogueadoConPreferencia() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		usuario.setPreferencia(preferenciaPorLugar);
		usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
		verify(sistema).agregarSuscripto(usuario);
		
	}
	
	public void testSuscribirseAlAvisoDeOfertasHotelerasEstandoLogueadoSinPreferencia() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		usuario.setPreferencia(null);
		try{
			usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
			fail("NO SE LANZÓ LA EXsCEPCIÓN DE suscribirseAlAvisoDeOfertasHoteleras()");
		} catch (ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia e) {
			
		}
	}
	
	

	public void ofertarEnSubastaFuturaEstandoLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaFutura);
		try{
			usuario.ofertarEnSubasta(subasta, 100);
		} catch(ExcepcionLaSubastaAunNoHaIniciado e){
			
		}
	}
		
	public void ofertarEnSubastaFuturaSinEstarLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		

		when(subasta.getEstado()).thenReturn(subastaFutura);
		try{
			usuario.ofertarEnSubasta(subasta,120);
			fail("NO SE LANZÓ LA EXCEPCIÓN DE ofertarEnSubasta()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	public void ofertarEnSubastaFinalizadaEstandoLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaFinalizada);
		try{
			usuario.ofertarEnSubasta(subasta, 100);
		} catch(ExcepcionLaSubastaYaHaFinalizado e){
			
		}
	}
	
	public void ofertarEnSubastaFinalizadaSinEstarLogueado() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior{
		
		

		when(subasta.getEstado()).thenReturn(subastaFinalizada);
		try{
			usuario.ofertarEnSubasta(subasta,120);
			fail("NO SE LANZÓ LA EXCEPCIÓN DE ofertarEnSubasta()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	
	public void ofertarEnSubastaEnCursoEstandoLogueadoConOfertaInferior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		when(subasta.getValor()).thenReturn((float) 200);
		try{
			usuario.ofertarEnSubasta(subasta, 100);
		} catch(ExcepcionOfertaInferior e){
			
		}
	}
	
	public void ofertarEnSubastaEnCursoEstandoLogueadoConOfertaSuperior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		
		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		when(subasta.getValor()).thenReturn((float) 200);
		usuario.ofertarEnSubasta(subasta, 250);
		verify(subasta).agragarOferta(usuario, 250);
	}
	
	public void ofertarEnSubastaEnCursoSinEstarLogueadoConOfertaInferior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior{
		
		

		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		try{
			usuario.ofertarEnSubasta(subasta,120);
			fail("NO SE LANZÓ LA EXCEPCIÓN DE ofertarEnSubasta()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	public void ofertarEnSubastaEnCursoSinEstarLogueadoConOfertaSuperior() throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior{
		
		

		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		try{
			usuario.ofertarEnSubasta(subasta,300);
			fail("NO SE LANZÓ LA EXCEPCIÓN DE ofertarEnSubasta()");
		}catch(ExcepcionNoEstaOnline e){
	
		}
		
	}
	
	public void suscribirseAlAvisoDeOfertasHotelerasSinEstarLogueado() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		try{
			usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
			fail("NO SE LANZÓ LA EXCEPCIÓN DE suscribirseAlAvisoDeOfertasHoteleras()");
		} catch (ExcepcionNoEstaOnline e) {
			
		}
		
	}
	
	public void suscribirseAlAvisoDeOfertasHotelerasEstandoLogueadoConPreferencia() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		when(usuario.getPreferencia()).thenReturn(preferenciaPorLugar);
		usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
		verify(sistema).agregarSuscripto(usuario);
		
	}
	
	public void suscribirseAlAvisoDeOfertasHotelerasEstandoLogueadoSinPreferencia() throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		when(usuario.getPreferencia()).thenReturn(null);
		try{
			usuario.suscribirseAlAvisoDeOfertasHoteleras(sistema);
			fail("NO SE LANZÓ LA EXsCEPCIÓN DE suscribirseAlAvisoDeOfertasHoteleras()");
		} catch (ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia e) {
			
		}
	}
	
	
		/**
>>>>>>> .r61
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
