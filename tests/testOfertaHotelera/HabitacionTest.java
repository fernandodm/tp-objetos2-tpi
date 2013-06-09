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
	
	
	////////////FALLAAA///////////
	
	
	public void setUp(){
		
		List<Calendar> diasReserva1 = new ArrayList<Calendar>();
		List<Calendar> diasReserva2 = new ArrayList<Calendar>();
		List<Calendar> diasReserva3 = new ArrayList<Calendar>();
		
		fechaInicio1 = Calendar.getInstance();
		fechaInicio1.set(2013,01,1);
		fechaFin2 = Calendar.getInstance();
		fechaFin2.set(2013,01,7);
		diasReserva1.add(fechaInicio1);
		diasReserva1.add(fechaFin2);
		
		fechaInicio3 = Calendar.getInstance();
		fechaInicio3.set(2013,01,10);		
		fechaFin4 = Calendar.getInstance();
		fechaFin4.set(2013,01,17);
		diasReserva3.add(fechaInicio3);
		diasReserva3.add(fechaFin4);
		
		fechaInicio5 = Calendar.getInstance();
		fechaInicio5.set(2013,01,20);
		fechaFin6 = Calendar.getInstance();
		fechaFin6.set(2013,01,27);
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
}
