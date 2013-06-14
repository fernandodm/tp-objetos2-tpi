package testOfertaHotelera;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import excepciones.ExcepcionElNombreDeUsuarioYaExiste;
import excepciones.ExcepcionPasswordIncorrecto;
import excepciones.ExcepcionUsuarioIncorrecto;

import ofertaHotelera.SistemaDeBusqueda;
import ofertaHotelera.Usuario;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SistemaDeBusquedaTest extends TestCase {
	private Usuario usuario1;
	private Usuario usuario2;
	private SistemaDeBusqueda sistema;
	
	public void setUp(){
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		sistema = new SistemaDeBusqueda();
		
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		
		when(usuario1.getNombreUsuario()).thenReturn("luis.muzarella");
		when(usuario2.getNombreUsuario()).thenReturn("steve22");
		when(usuario1.getContrasenha()).thenReturn("1234");
		when(usuario2.getContrasenha()).thenReturn("4321");
		
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		
		sistema.setUsuarios(usuarios);

	}

	public void testExisteNombreDeUsuarioTrue(){
		//con nombre existente
		
		Assert.assertTrue(sistema.existeNombreDeUsuario("steve22"));
		
	}
	
	public void testExisteNombreDeUsuarioFalse(){
		//con nombre inexistente
		
		Assert.assertFalse(sistema.existeNombreDeUsuario("ferplay_19"));
		
	}
	
	public void testRegistrarUsuario() throws ExcepcionElNombreDeUsuarioYaExiste{
		
		sistema.registrarUsuario("DonRamon", "1234", "ramon");
		int cantidadUsers = sistema.getUsuarios().size();
		Usuario user = sistema.getUsuarios().get(2);
		
		Assert.assertEquals("FALLA EL REGISTRAR USUARIO", cantidadUsers, 3);
		Assert.assertEquals(user.getNombreUsuario(), "DonRamon");
		
	}
	
	public void testRegistrarUsuarioConNombreExistente() throws ExcepcionElNombreDeUsuarioYaExiste{
		
		try{
			sistema.registrarUsuario("steve22", "1223", "esteban quito");
			fail("NO SE LANZO LA EXCEPCION DE REGISTRAR USUARIO");
		}catch(ExcepcionElNombreDeUsuarioYaExiste e){
			
		}
	}
	
	public void testBuscarUsuarioTrue() throws ExcepcionUsuarioIncorrecto{
		//Con usuario correcto
		
		Usuario user = sistema.buscarUsuario("steve22");
		Assert.assertEquals("FALLA BUSCAR USUARIO (USUARIO CORRECTO)",user.getNombreUsuario(), "steve22");
	}
	
	public void testBuscarUsuarioFalse() throws ExcepcionUsuarioIncorrecto{
		//Con usuario incorrecto
		try{
			sistema.buscarUsuario("steve222");
			fail("FALLA BUSCAR USUARIO (USUARIO INCORRECTO)");
		}catch(ExcepcionUsuarioIncorrecto e){
			
		}
	}
	
	public void testLogInContraseñaCorrecta() throws ExcepcionPasswordIncorrecto, ExcepcionUsuarioIncorrecto{		
		
		sistema.logIn("luis.muzarella", "1234");
		verify(usuario1).setOnline(true);
	}
	
	public void testLogInContraseñaIncorrecta() throws ExcepcionPasswordIncorrecto, ExcepcionUsuarioIncorrecto{
	
		try{
			sistema.logIn("luis.muzarella", "1245");
			fail("NO SE LANZO LA EXCEPCION DE CONTRASEÑA INCORRECTA");
		}catch(ExcepcionPasswordIncorrecto e){
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
