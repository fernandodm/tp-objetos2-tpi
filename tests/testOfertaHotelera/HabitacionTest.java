package testOfertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ofertaHotelera.Habitacion;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HabitacionTest extends TestCase {
	private Habitacion habitacion;
	private List<List<Calendar>> diasReservados = new ArrayList<List<Calendar>>();
	private Calendar fechaInicio1;
	private Calendar fechaFin2;
	private Calendar fechaInicio3;
	private Calendar fechaFin4;
	private Calendar fechaInicio5;
	private Calendar fechaFin6;
	
	
	public void setUp(){
		
		habitacion = new Habitacion();
		
		List<Calendar> diasReserva1 = new ArrayList<Calendar>();
		List<Calendar> diasReserva2 = new ArrayList<Calendar>();
		List<Calendar> diasReserva3 = new ArrayList<Calendar>();
		
		fechaInicio1 = Calendar.getInstance();
		fechaInicio1.set(2013,01,1,0,0,0);
		fechaFin2 = Calendar.getInstance();
		fechaFin2.set(2013,01,7,0,0,0);
		diasReserva1.add(fechaInicio1);
		diasReserva1.add(fechaFin2);
		
		fechaInicio3 = Calendar.getInstance();
		fechaInicio3.set(2013,01,10,0,0,0);		
		fechaFin4 = Calendar.getInstance();
		fechaFin4.set(2013,01,17,0,0,0);
		diasReserva2.add(fechaInicio3);
		diasReserva2.add(fechaFin4);
		
		fechaInicio5 = Calendar.getInstance();
		fechaInicio5.set(2013,01,20,0,0,0);
		fechaFin6 = Calendar.getInstance();
		fechaFin6.set(2013,01,27,0,0,0);
		diasReserva3.add(fechaInicio5);
		diasReserva3.add(fechaFin6);
		
		diasReservados.add(diasReserva1);
		diasReservados.add(diasReserva2);
		diasReservados.add(diasReserva3);
		
		habitacion.setDiasOcupados(diasReservados);
	}
	
	public void testEliminarHorario(){
		
		habitacion.eliminarHorario(fechaInicio3, fechaFin4);
		Assert.assertEquals(habitacion.getDiasOcupados().size(), 2);
		
	}
	
	public void testEstaDisponibleFalse(){
		
		//con fecha no disponible
		
		Calendar fechaInicio7 = Calendar.getInstance();
		fechaInicio7.set(2013,01,17,0,0,0);
		Calendar fechaFin8 = Calendar.getInstance();
		fechaFin8.set(2013,01,9,0,0,0);
		
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