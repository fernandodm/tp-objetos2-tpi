package ofertaHotelera;

import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;

public abstract class EstadoSubasta {

	private Subasta subasta;
	
	
	public abstract void agregarApuesta(Usuario user, float oferta) throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado;
}
