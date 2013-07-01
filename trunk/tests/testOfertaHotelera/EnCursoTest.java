package testOfertaHotelera;

import excepciones.ExcepcionOfertaInferior;
import ofertaHotelera.EnCurso;
import ofertaHotelera.Subasta;
import ofertaHotelera.Usuario;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;


public class EnCursoTest extends TestCase {
	
	private Subasta subasta;
	private Usuario user1;
	private Usuario user2;
	private EnCurso subastaEnCurso;
	
	public void setUp(){
		
		user1 = mock(Usuario.class);
		user2 = mock(Usuario.class);
		
		subasta = mock(Subasta.class);
		when(subasta.getEstado()).thenReturn(subastaEnCurso);
		when(subasta.getValor()).thenReturn((float) 200);
		when(subasta.getGanadorActual()).thenReturn(user2);
		
		subastaEnCurso = new EnCurso();
		subastaEnCurso.setSubasta(subasta);
	}
	
	public void testAgregarOfertaSuperior() throws ExcepcionOfertaInferior{
		
		subastaEnCurso.agregarApuesta(user1, 300);
		verify(subasta).getValor();
		verify(subasta).setValor(300);
		verify(subasta).setGanadorActual(user1);
	}
	
	public void testAgregarOfertaInferior() throws ExcepcionOfertaInferior{
		
		try{
			subastaEnCurso.agregarApuesta(user1, 100);
			fail();
		} catch(ExcepcionOfertaInferior e){
			
		}
	}
	
	public void testAgregarOfertaIgual() throws ExcepcionOfertaInferior{
		
		try{
			subastaEnCurso.agregarApuesta(user1, 200);
			fail();
		} catch(ExcepcionOfertaInferior e){
			
		}
	}

}
