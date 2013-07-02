package ofertaHotelera;


import excepciones.ExcepcionLaSubastaYaHaFinalizado;

public class Finalizada extends EstadoSubasta {

	
	/**
	 * Agrega una apuesta de un usuario, pero como la subasta ya termino solo se lanza la excepción.
	 * @param user, ofert
	 * @return
	 * @throws ExcepcionLaSubastaYaHaFinalizado.
	 */
	
	public void agregarApuesta(Usuario user, float oferta) throws ExcepcionLaSubastaYaHaFinalizado {
		
		throw new ExcepcionLaSubastaYaHaFinalizado();
		
	}
	
}
