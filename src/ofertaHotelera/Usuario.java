package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import excepciones.ExcepcionNoEstaOnline;
import excepciones.ExcepcionNoSeEncontroReserva;
import excepciones.ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado;

public class Usuario {

	private SistemaDeBusqueda sistema;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private String nombreUsuario;
	private String contrasenha;
	private String nombre;
	private boolean online;
	
	public Usuario(){};
	
	public Usuario(SistemaDeBusqueda sistema, String nombreUsuario, String contrasenha, String nombre,
			boolean online) {
	
		this.sistema = sistema;
		this.nombreUsuario = nombreUsuario;
		this.contrasenha = contrasenha;
		this.nombre = nombre;
		this.online = online;
	}
	
	public SistemaDeBusqueda getSistema() {
		return sistema;
	}
	public void setSistema(SistemaDeBusqueda sistema) {
		this.sistema = sistema;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasenha() {
		return contrasenha;
	}
	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	
	public List<Reserva> todasLasReservas()throws ExcepcionNoEstaOnline{
		
		if(isOnline()){
	
			return getReservas();
		
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	public List<Reserva> reservaPorCiudad(String unaCiudad) throws ExcepcionNoEstaOnline{
		
		if(isOnline()){
			List<Reserva> misReservas = new ArrayList<Reserva>();
			for(Reserva each : getReservas()){
				if(each.ciudadDelHotel().equals(unaCiudad)){
					misReservas.add(each);
				}
			}
			return misReservas;
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	public List<String> ciudadesConReservas()throws ExcepcionNoEstaOnline{
		
		if(isOnline()){
			List<String> ciudades = new ArrayList<String>();
			
			for(Reserva each: getReservas()){
				ciudades.add(each.ciudadDelHotel());
			}
		
			return ciudades;
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	public List<Reserva> reservasFuturas() throws ExcepcionNoEstaOnline{
		
		if(isOnline()){
			List<Reserva> futuras = new ArrayList<Reserva>();
		
			for(Reserva each: getReservas()){
				if(each.estaReservadaDespuesDe(Calendar.getInstance())){
					futuras.add(each);
				}
			}
			return futuras;
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	public void eliminarLaReserva(Reserva unaReserva) throws ExcepcionNoSeEncontroReserva{
		
		if(getReservas().contains(unaReserva)){
			for(Reserva each : getReservas()){
				if(each.equals(unaReserva)){
					each.eliminarReservaDelHotel();
					each.eliminarHorarioDeLaHabitacion();
					getReservas().remove(unaReserva);
					break;
				}	
			}
		}else{	
			throw new ExcepcionNoSeEncontroReserva();
			}
	}
	
	public void cancelarReserva(Reserva unaReserva) throws ExcepcionNoEstaOnline, ExcepcionNoSeEncontroReserva{
		
		if(isOnline()){
			eliminarLaReserva(unaReserva);
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}	 
	
	
	
	public void calificarHotel(Hotel h, String comentario, int puntaje) throws ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado, ExcepcionNoEstaOnline{
		
		boolean seHospedo = false;
		
		if(isOnline()){
			
		for(Reserva each : getReservas()){
			
			//para la fecha de hoy setearle la hora en 0
			if(each.getHotel().equals(h) && Calendar.getInstance().after(each.getFechaDeSalida())){
				seHospedo = true;
				break;
				}
			}

		}
		h.agregarCalificacion(comentario, puntaje, seHospedo);
	}
	
	public static void main(String[] args) {
	
	}
}
