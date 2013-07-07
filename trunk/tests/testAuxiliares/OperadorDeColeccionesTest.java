package testAuxiliares;

import hotel.Reserva;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import auxiliares.OperadorDeColecciones;
import junit.framework.Assert;
import static org.mockito.Mockito.*;

public class OperadorDeColeccionesTest extends TestCase {

	private List<Reserva> reservas1;
	private List<Reserva> reservas2;
	private Reserva reservaA;
	private Reserva reservaB;
	private Reserva reservaC;
	
	public void setUp(){
		reservaA= mock(Reserva.class);
		reservaB= mock(Reserva.class);
		reservaC= mock(Reserva.class);
		reservas1= new ArrayList<Reserva>();
		reservas2= new ArrayList<Reserva>();
		reservas1.add(reservaA);
		reservas1.add(reservaB);
		reservas2.add(reservaC);
	}
	
	public void testConcatenarReservas(){
		OperadorDeColecciones.concatenarReservas(reservas1, reservas2);
		int resultadoEsperado= 3;
		Assert.assertEquals(resultadoEsperado, reservas1.size());
	}
}