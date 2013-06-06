package ofertaHotelera;

import java.util.Calendar;

public class Reserva {
	private Usuario usuario;
	private Hotel hotel;
	private Habitacion habitacion;
	private Calendar fechaDeIngreso;
	private Calendar fechaDeSalida;
	
	
	public Habitacion getHabitacion() {
		return habitacion;
	}


	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}


	public Calendar getFechaDeIngreso() {
		return fechaDeIngreso;
	}


	public void setFechaDeIngreso(Calendar fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}


	public Calendar getFechaDeSalida() {
		return fechaDeSalida;
	}


	public void setFechaDeSalida(Calendar fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
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
	
	public boolean estaReservadaDespuesDe(Calendar instance) {
		
		return getFechaDeIngreso().after(instance);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
