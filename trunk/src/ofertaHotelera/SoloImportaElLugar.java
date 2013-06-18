package ofertaHotelera;

public class SoloImportaElLugar extends Preferencia {

	@Override
	public boolean lePuedeInteresarHabitacion(Habitacion h) {
		
		boolean b = false;
		
		if(h.getHotel().getPais() == getPaisDelHotel() && getCiudadDelHotel() == null && lasFechasEstanBien(h)){
			b = true;
		} 
		if(h.getHotel().getPais() == getPaisDelHotel() && h.getHotel().getPais() == getCiudadDelHotel() && lasFechasEstanBien(h)){
			b = true;
		}
		
		return b;
	}

	
	
	
}

