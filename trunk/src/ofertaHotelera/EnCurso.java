package ofertaHotelera;


import excepciones.ExcepcionOfertaInferior;
import excepciones.ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia;

public class EnCurso extends EstadoSubasta {

	/**
	 * Agrega una apuesta de un usuario, si es superior se lo declara como ganador hasta el momento y se actualiza el valor.
	 * @param user, ofert
	 * @return
	 * @throws ExcepcionOfertaInferior.
	 */
	
	@Override
	public void agregarApuesta(Usuario user, float oferta) throws ExcepcionOfertaInferior {
		
		if(oferta > getSubasta().getValor()){
			getSubasta().setValor(oferta);
			getSubasta().setGanadorActual(user);
		}else{
			throw new ExcepcionOfertaInferior();
		}
		
		
		
	}

}
