package preferencias;

import hotel.Habitacion;

public class PreferenciaPorLugar extends Preferencia {

	private String paisDelHotel;
	private String ciudadDelHotel;
	
	public String getPaisDelHotel() {
		return paisDelHotel;
	}



	public void setPaisDelHotel(String paisDelHotel) {
		this.paisDelHotel = paisDelHotel;
	}



	public String getCiudadDelHotel() {
		return ciudadDelHotel;
	}



	public void setCiudadDelHotel(String ciudadDelHotel) {
		this.ciudadDelHotel = ciudadDelHotel;
	}


	/**
	 * Retorna true si los datos de la preferencia coinciden con los requeridos de la habitación.
	 * @param h
	 * @return boolean
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha.
	 */

	public boolean lePuedeInteresarHabitacion(Habitacion h) {
		
		boolean b = false;
		
		if(h.getHotel().getPais() == getPaisDelHotel() && getCiudadDelHotel() == null){
			b = true;
		} 
		if(getCiudadDelHotel() == h.getHotel().getCiudad() && getPaisDelHotel() == null){
			b = true;
		} 
		if(h.getHotel().getPais() == getPaisDelHotel() && h.getHotel().getCiudad() == getCiudadDelHotel() ){
			b = true;
		}
		
		return b;
	}

	
	
	
}

