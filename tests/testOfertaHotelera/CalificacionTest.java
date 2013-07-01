package testOfertaHotelera;

import ofertaHotelera.Calificacion;
import ofertaHotelera.Usuario;
import junit.framework.TestCase;
import junit.framework.Assert;
import static org.mockito.Mockito.*;

public class CalificacionTest extends TestCase {
	
	private Usuario user;
	private int puntaje;
	private String comentario;
	
	public void setUp(){
		user= mock(Usuario.class);
		puntaje= mock(int.class);
		comentario= mock(String.class);
	}
	
	public void testConstructorCalificacion(){
		Calificacion cal= new Calificacion(user, puntaje, comentario);
		
		Assert.assertEquals(user, cal.getUser());
		Assert.assertEquals(puntaje, cal.getPuntaje());
		Assert.assertEquals(comentario, cal.getComentario());
	}

}
