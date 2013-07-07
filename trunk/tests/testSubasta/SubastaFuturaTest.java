package testSubasta;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import excepciones.ExcepcionOfertaInferior;

import sistemaYUsuario.Usuario;
import subasta.Subasta;
import subasta.SubastaFutura;
import junit.framework.TestCase;

public class SubastaFuturaTest extends TestCase {

	private Subasta subasta;
	private Usuario user1;
	private Usuario user2;
	private SubastaFutura subastaFutura;
	
	public void setUp(){
		
		user1 = mock(Usuario.class);
		user2 = mock(Usuario.class);
		
		subasta = mock(Subasta.class);
		when(subasta.getEstado()).thenReturn(subastaFutura);
		when(subasta.getValor()).thenReturn((float) 200);
		when(subasta.getGanadorActual()).thenReturn(user2);
		
		subastaFutura = new SubastaFutura();
		subastaFutura.setSubasta(subasta);
	}
	
	public void testAgregarOferta() throws ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior{
		
		try{
			subastaFutura.agregarApuesta(user1, 500);
			fail();
		} catch (ExcepcionLaSubastaAunNoHaIniciado e){
			
		}
		
	}
	
}
