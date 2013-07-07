package testAuxiliares;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import auxiliares.Auxiliar;


import junit.framework.Assert;
import junit.framework.TestCase;

public class AuxiliarTest extends TestCase {

	private Auxiliar auxiliar;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private Calendar fecha;
	private Calendar fechaAComparar;
	
	public void setUp(){
		
		auxiliar = new Auxiliar();
		
		fechaInicio = Calendar.getInstance();
		fechaInicio.set(fechaInicio.get(fechaInicio.YEAR),fechaInicio.get(fechaInicio.MONTH),fechaInicio.get(fechaInicio.DATE) + 1,0,0,0);
		fechaInicio.clear(Calendar.MILLISECOND);
		
		fechaFin = Calendar.getInstance();
		fechaFin.set(fechaFin.get(fechaFin.YEAR),fechaFin.get(fechaFin.MONTH),fechaFin.get(fechaFin.DATE) + 3,0,0,0);
		fechaFin.clear(Calendar.MILLISECOND);
		
		fecha = Calendar.getInstance();
		fecha.set(2013,03,03,0,0,0);
		fecha.clear(Calendar.MILLISECOND);
		
		fechaAComparar = Calendar.getInstance();
		fechaAComparar.set(2013,03,01,0,0,0);
		fechaAComparar.clear(Calendar.MILLISECOND);
		
	}
	public void testCantidadDeDias(){
		
		int cantidad = auxiliar.cantidadDeDias(fechaInicio, fechaFin);
		
		Assert.assertTrue(cantidad == 3);
	}
	
	public void testListaContieneFechaTrue(){
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(2013,03,03,0,0,0);
		fecha.clear(Calendar.MILLISECOND);
		
		Calendar fechaAComparar = Calendar.getInstance();
		fechaAComparar.set(2013,03,03,0,0,0);
		fechaAComparar.clear(Calendar.MILLISECOND);
		
		List<Calendar> fechas = new ArrayList<Calendar>();
		
		fechas.add(fecha);
		fechas.add(fechaFin);
		fechas.add(fechaInicio);
		
		boolean contiene = auxiliar.listaContieneFecha(fechas, fechaAComparar);
		
		Assert.assertTrue(contiene);
	}
	
	public void testListaContieneFechaFalse(){
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(2013,03,03,0,0,0);
		fecha.clear(Calendar.MILLISECOND);
		
		Calendar fechaAComparar = Calendar.getInstance();
		fechaAComparar.set(2013,03,01,0,0,0);
		fechaAComparar.clear(Calendar.MILLISECOND);
		
		List<Calendar> fechas = new ArrayList<Calendar>();
		
		fechas.add(fecha);
		fechas.add(fechaFin);
		fechas.add(fechaInicio);
		
		boolean contiene = auxiliar.listaContieneFecha(fechas, fechaAComparar);
		
		Assert.assertFalse(contiene);
	}
	
	public void testBetweenTrue(){
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(fecha.get(fecha.YEAR),fecha.get(fecha.MONTH),fecha.get(fecha.DATE) + 2,0,0,0);
		fecha.clear(Calendar.MILLISECOND);
		
		boolean laFecha = auxiliar.between(fecha, fechaInicio, fechaFin);
		
		Assert.assertTrue(laFecha);
		
	}
	
	public void testBetweenFalse(){
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(fecha.get(fecha.YEAR),fecha.get(fecha.MONTH),fecha.get(fecha.DATE) + 5,0,0,0);
		fecha.clear(Calendar.MILLISECOND);
		
		boolean laFecha = auxiliar.between(fecha, fechaInicio, fechaFin);
		
		Assert.assertFalse(laFecha);
		
	}
}
