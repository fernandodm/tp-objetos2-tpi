package testOfertaHotelera;

import descuentos.DescuentoPorCantidadDeNoches;
import junit.framework.Assert;
import junit.framework.TestCase;

public class DescuentoPorCantidadDeNochesTest extends TestCase {

	public void testDescuento(){
		
		DescuentoPorCantidadDeNoches descuento = new DescuentoPorCantidadDeNoches(7,20);
		
		String desc = descuento.descuento();
	
		Assert.assertEquals(desc, "Si pasas 7 dias te hacemos un 20% de descuento. " );
	}
	
}
