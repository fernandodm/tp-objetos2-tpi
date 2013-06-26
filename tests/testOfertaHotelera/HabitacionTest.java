package testOfertaHotelera;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ofertaHotelera.Habitacion;
import ofertaHotelera.Hotel;
import ofertaHotelera.Periodo;
import ofertaHotelera.PorPrecioPorEstadia;
import ofertaHotelera.PorPrecioPorNoche;
import ofertaHotelera.SoloImportaElLugar;
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
	private SoloImportaElLugar preferencia3;
	private Hotel hotel;

	
	
	public void setUp(){
		
		habitacion = new Habitacion();
			
		periodo1 = mock(Periodo.class);
		periodo2 = mock(Periodo.class);
		periodo3 = mock(Periodo.class);
		
		fechaInicio1 = Calendar.getInstance();
		fechaInicio1.set(2013,01,1,0,0,0);
		fechaFin2 = Calendar.getInstance();
		fechaFin2.set(2013,01,7,0,0,0);
		when(periodo1.getDesde()).thenReturn(fechaInicio1);
		when(periodo1.getHasta()).thenReturn(fechaFin2);
		
		fechaInicio3 = Calendar.getInstance();
		fechaInicio3.set(2013,01,10,0,0,0);		
		fechaFin4 = Calendar.getInstance();
		fechaFin4.set(2013,01,17,0,0,0);
		when(periodo2.getDesde()).thenReturn(fechaInicio3);
		when(periodo2.getHasta()).thenReturn(fechaFin4);
		
		fechaInicio5 = Calendar.getInstance();
		fechaInicio5.set(2013,01,20,0,0,0);
		fechaFin6 = Calendar.getInstance();
		fechaFin6.set(2013,01,27,0,0,0);
		when(periodo3.getDesde()).thenReturn(fechaInicio5);
		when(periodo3.getHasta()).thenReturn(fechaFin6);
		
		diasReservados.add(periodo1);
		diasReservados.add(periodo2);
		diasReservados.add(periodo3);
		
		habitacion.setDiasOcupados(diasReservados);
		//habitacion.setPrecioPorNoche(120);

		
		user = mock(Usuario.class);
		preferencia1 = mock(PorPrecioPorNoche.class);
		preferencia2 = mock(PorPrecioPorEstadia.class);
		preferencia3 = mock(SoloImportaElLugar.class);
		
		hotel = mock(Hotel.class);
		
		
	}
	
	public void testLePuedeInteresarAlUsuarioPorElPrecioPorNoche(){
		
		habitacion.lePuedeInteresarAlUsuario(user);
		
		//verify(user).getPreferencia();
		//verify(preferencia).le
	}
	
	public void testLePuedeInteresarAlUsuarioPorLaUbicacion(){

		when(hotel.getPais()).thenReturn("Espa�a");
		when(hotel.getCiudad()).thenReturn("Madrid");
		habitacion.setHotel(hotel);
		when(preferencia3.getPaisDelHotel()).thenReturn("Espa�a");
		when(preferencia3.getCiudadDelHotel()).thenReturn("Madrid");
		when(preferencia3.lasFechasEstanBien(habitacion)).thenReturn(true);
		when(user.getPreferencia()).thenReturn(preferencia3);
		boolean b = habitacion.lePuedeInteresarAlUsuario(user);
		Assert.assertTrue(b);
		
	}
	
	public void testLePuedeInteresarAlUsuarioPorElPrecioPorEstadia(){
		
		
		fechaInicio1 = Calendar.getInstance();
		fechaInicio1.set(2013,01,1,0,0,0);
		fechaFin2 = Calendar.getInstance();
		fechaFin2.set(2013,10,1,0,0,0);
		when(preferencia2.getPrecioMinimo()).thenReturn(1000);
		when(preferencia2.getPrecioMaximo()).thenReturn(1500);
		when(preferencia2.getFechaInicialDeInteres()).thenReturn(fechaInicio1);
		when(preferencia2.getFechaFinalDeInteres()).thenReturn(fechaFin2);
		boolean b= habitacion.lePuedeInteresarAlUsuario(user);
		Assert.assertTrue(b);
		
	}
	
	public void testEliminarHorario(){
		
		habitacion.eliminarHorario(periodo2);
		Assert.assertEquals(habitacion.getDiasOcupados().size(), 2);
		
	}
	
	public void testEstaDisponibleFalse(){
		
		//con fecha no disponible
		
		Calendar fechaInicio7 = Calendar.getInstance();
		fechaInicio7.set(2013,01,17,0,0,0);
		Calendar fechaFin8 = Calendar.getInstance();
		fechaFin8.set(2013,01,9,0,0,0);
		
		when(periodo1.estaEntre(fechaInicio7, fechaFin8)).thenReturn(false);	
		when(periodo2.estaEntre(fechaInicio7, fechaFin8)).thenReturn(false);
		when(periodo3.estaEntre(fechaInicio7, fechaFin8)).thenReturn(true);
				
		boolean estaDisponible = habitacion.estaDisponible(fechaInicio7, fechaFin8);
		
		Assert.assertFalse("FALLA testEstaDisponibleFalse()",estaDisponible);
	}
	
	public void testEstaDisponibleTrue(){
		
		//con fecha disponible
		
		Calendar fechaInicio7 = Calendar.getInstance();
		fechaInicio7.set(2013,01,28,0,0,0);
		Calendar fechaFin8 = Calendar.getInstance();
		fechaFin8.set(2013,02,5,0,0,0);
		
		boolean estaDisponible = habitacion.estaDisponible(fechaInicio7, fechaFin8);
		
		Assert.assertTrue("FALLA testEstaDisponibleFalse()",estaDisponible);
	}
}
