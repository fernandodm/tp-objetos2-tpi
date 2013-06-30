package ofertaHotelera;

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



	public boolean lePuedeInteresarHabitacion(Habitacion h) {
		
		boolean b = false;
		
		if(h.getHotel().getPais() == getPaisDelHotel() && getCiudadDelHotel() == null){
			b = true;
		} 
		if(h.getHotel().getPais() == null && getCiudadDelHotel() == h.getHotel().getCiudad()){
			b = true;
		} 
		if(h.getHotel().getPais() == getPaisDelHotel() && h.getHotel().getCiudad() == getCiudadDelHotel() ){
			b = true;
		}
		
		return b;
	}

	
	
	
}

