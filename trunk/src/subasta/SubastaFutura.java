package subasta;

import sistemaYUsuario.Usuario;
import excepciones.ExcepcionLaSubastaAunNoHaIniciado;

public class SubastaFutura extends EstadoSubasta {

	
	/**
	 * Agrega una apuesta de un usuario, pero como la subasta todav�a no empez� se lanza la excepci�n.
	 * @param user, oferta
	 * @return
	 * @throws ExcepcionLaSubastaAunNoHaIniciado.
	 */
	

	public void agregarApuesta(Usuario user, float oferta) throws ExcepcionLaSubastaAunNoHaIniciado {
	
		throw new ExcepcionLaSubastaAunNoHaIniciado();
		
	}

}
