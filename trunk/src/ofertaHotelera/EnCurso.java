package ofertaHotelera;


import excepciones.ExcepcionOfertaInferior;

public class EnCurso extends EstadoSubasta {

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
