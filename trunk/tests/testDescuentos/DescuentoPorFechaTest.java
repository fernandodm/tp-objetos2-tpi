package testDescuentos;

import java.util.Calendar;

import org.junit.Assert;

import descuentos.DescuentoPorFecha;
import junit.framework.TestCase;

public class DescuentoPorFechaTest extends TestCase {

	public void testDescuento(){
		
		Calendar fechaLimite = Calendar.getInstance();
		
		DescuentoPorFecha descuento = new DescuentoPorFecha(fechaLimite, 10);
		
		String desc = descuento.descuento();
		
		Assert.assertEquals(desc, "Si venis antes del 10/6/2013 te hacemos un 10% de descuento. ");
	}
}
