package ofertaHotelera;

import java.util.Calendar;

import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;

public abstract class Preferencia {
	


	
	
	public abstract boolean lePuedeInteresarHabitacion(Habitacion h) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha;
		
}	


