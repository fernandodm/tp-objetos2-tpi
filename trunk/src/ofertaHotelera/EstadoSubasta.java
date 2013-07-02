package ofertaHotelera;

import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import excepciones.ExcepcionOfertaInferior;

public abstract class EstadoSubasta {

	private Subasta subasta;
	
	
	
	public Subasta getSubasta() {
		return subasta;
	}



	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}

	
	
	public abstract void agregarApuesta(Usuario user, float oferta) throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior;
}
