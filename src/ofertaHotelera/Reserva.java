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
	private Calendar fechaDeIngreso;
	private Calendar fechaDeSalida;
	
	public Reserva(){
	}
	
	public Reserva(Usuario usuario, Hotel hotel, Habitacion habitacion,
			Calendar fechaDeIngreso, Calendar fechaDeSalida) {
		
		this.usuario = usuario;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fechaDeIngreso = fechaDeIngreso;
		this.fechaDeSalida = fechaDeSalida;
	}
	
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
	
	public void eliminarReservaDelHotel(){
		
		getHotel().eliminarReserva(this);
	
	}

	public void eliminarHorarioDeLaHabitacion() {
		
		getHabitacion().eliminarHorario(getFechaDeIngreso(),getFechaDeSalida());
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Calendar> a = new ArrayList<Calendar>();
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c.set(2000,10,3,0,0,0);
		c1.set(2000,10,3,0,0,0);
		System.out.println(c.after(c1));
		System.out.println(c.before(c1));
		
		System.out.println(c.getTime());
		
	}
}
