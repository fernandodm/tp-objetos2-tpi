package ofertaHotelera;


import excepciones.ExcepcionLaSubastaYaHaFinalizado;

public class Finalizada extends EstadoSubasta {

	
	
	public void agregarApuesta(Usuario user, float oferta) throws ExcepcionLaSubastaYaHaFinalizado {
		
		throw new ExcepcionLaSubastaYaHaFinalizado();
		
	}
	
}
