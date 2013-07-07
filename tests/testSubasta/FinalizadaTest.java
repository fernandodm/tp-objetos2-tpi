package testSubasta;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import sistemaYUsuario.Usuario;
import subasta.Finalizada;
import subasta.Subasta;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class FinalizadaTest extends TestCase {
	
	private Subasta subasta;
	private Usuario user1;
	private Usuario user2;
	private Finalizada subastaFinalizada;
	
	public void setUp(){
		
		user1 = mock(Usuario.class);
		user2 = mock(Usuario.class);
		
		subasta = mock(Subasta.class);
		when(subasta.getEstado()).thenReturn(subastaFinalizada);
		when(subasta.getValor()).thenReturn((float) 200);
		when(subasta.getGanadorActual()).thenReturn(user2);
		
		subastaFinalizada = new Finalizada();
		subastaFinalizada.setSubasta(subasta);
	}
	
	public void testAgregarOferta(){
		
		try{
			subastaFinalizada.agregarApuesta(user1, 500);
			fail();
		} catch (ExcepcionLaSubastaYaHaFinalizado e){
			
		}
		
	}
	

}

	

