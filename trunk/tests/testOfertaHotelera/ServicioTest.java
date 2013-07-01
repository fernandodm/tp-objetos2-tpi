package testOfertaHotelera;

import ofertaHotelera.Servicio;
import junit.framework.TestCase;
import junit.framework.Assert;

public class ServicioTest extends TestCase {

	private String nombre;
	private int precio;
	private String descripcion;

	public void setUp(){
		nombre= "wi-fi";
		precio= 100;
		descripcion= "conexion wi-fi";
	}
	
	public void testConstructorServicio(){
		Servicio ser= new Servicio(nombre, precio, descripcion);
		
		Assert.assertEquals(nombre, ser.getNombre());
		Assert.assertEquals(precio, ser.getPrecio());
		Assert.assertEquals(descripcion, ser.getDescripcion());
	}
}
