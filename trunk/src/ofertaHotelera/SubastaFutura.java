package ofertaHotelera;

import excepciones.ExcepcionLaSubastaAunNoHaIniciado;

public class SubastaFutura extends EstadoSubasta {


	public void agregarApuesta(Usuario user, float oferta) throws ExcepcionLaSubastaAunNoHaIniciado {
	
		throw new ExcepcionLaSubastaAunNoHaIniciado();
		
	}

}
