package ofertaHotelera;

import java.util.Calendar;

public abstract class Preferencia {
	
	private String paisDelHotel;
	private String ciudadDelHotel;
	private Calendar fechaInicialDeInteres;
	private Calendar fechaFinalDeInteres;

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
	
	public Calendar getFechaInicialDeInteres() {
		return fechaInicialDeInteres;
	}
	public void setFechaInicialDeInteres(Calendar fechaInicialDeInteres) {
		this.fechaInicialDeInteres = fechaInicialDeInteres;
	}
	public Calendar getFechaFinalDeInteres() {
		return fechaFinalDeInteres;
	}
	public void setFechaFinalDeInteres(Calendar fechaFinalDeInteres) {
		this.fechaFinalDeInteres = fechaFinalDeInteres;
	}
	
	public boolean lasFechasEstanBien(Habitacion h){
		
		boolean ok = false;

		
		return ok;
	}
	
	public abstract boolean lePuedeInteresarHabitacion(Habitacion h);
		
}	


