package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Reserva {
	private Usuario usuario;
	private Hotel hotel;
	private Habitacion habitacion;
	private Periodo periodo;
	
	public Reserva(){
	}
	
	public Reserva(Usuario usuario, Hotel hotel, Habitacion habitacion,Periodo periodo) {
		
		this.usuario = usuario;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.periodo = periodo;
	}
	
	public void enviarMail(){
		String mail= this.generarMail();
		this.getHotel().getMail().add(mail);
		this.getUsuario().getCasillaMail().add(mail);
	}
	
	public String generarMail(){
		String mail;
		mail= "Datos de la reserva. Usuario: "+this.getUsuario().getNombre()+
		".  Hotel: "+this.getHotel().getNombre()+".  Habitacion: "+
		this.getHabitacion().getNumero()+". Desde: "+this.getPeriodo().getDesde().getTime()+
		". Hasta: "+this.getPeriodo().getHasta().getTime();
		return mail;
	}
	
	
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public Habitacion getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String ciudadDelHotel() {
		
		return hotel.getCiudad();
	}
	
	/**
	 * devuelve true si la reserva esta reservada despues de una fecha
	 * @param instance
	 * @return
	 */
	public boolean estaReservadaDespuesDe(Calendar instance) {
		
		return getPeriodo().getDesde().after(instance);
	}
	
	/**
	 * elimina la reserva del hotel
	 */
	public void eliminarReservaDelHotel(){
		
		getHotel().eliminarReserva(this);
	
	}

	/**
	 * Elimina el horario de la habitacion
	 */
	public void eliminarHorarioDeLaHabitacion() {
		
		getHabitacion().eliminarHorario(getPeriodo());
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
	}
}
