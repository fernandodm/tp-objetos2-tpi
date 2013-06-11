package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import excepciones.ExcepcionNoEstaOnline;
import excepciones.ExcepcionNoSeEncontroReserva;

public class Usuario {

	private SistemaDeBusqueda sistema;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private String nombreUsuario;
	private String contrasenha;
	private String nombre;
	private boolean online;
	
	public Usuario(){};
	
	public Usuario(SistemaDeBusqueda sistema, List<Reserva> reservas,
			String nombreUsuario, String contrasenha, String nombre,
			boolean online) {
	
		this.sistema = sistema;
		this.reservas = reservas;
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
	
	
	public static void main(String[] args) {
		Calendar fechau = Calendar.getInstance();
		fechau.set(fechau.get(fechau.YEAR),fechau.get(fechau.MONTH), fechau.get(fechau.DATE),0,0,0);
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(fecha1.get(fecha1.YEAR),fecha1.get(fecha1.MONTH), fecha1.get(fecha1.DATE),0,0,0);
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(fecha2.get(fecha2.YEAR),fecha2.get(fecha1.MONTH), fecha2.get(fecha2.DATE) + 7,0,0,0);
		Calendar fecha3 = Calendar.getInstance();
		fecha3.set(fecha3.get(fecha3.YEAR),fecha3.get(fecha1.MONTH), fecha3.get(fecha3.DATE) - 3,0,0,0);
		Calendar fecha4 = Calendar.getInstance();
		fecha4.set(fecha4.get(fecha4.YEAR),fecha4.get(fecha1.MONTH), fecha4.get(fecha4.DATE) + 4,0,0,0);
		Calendar fecha5 = Calendar.getInstance();
		fecha5.set(fecha5.get(fecha5.YEAR),fecha5.get(fecha1.MONTH), fecha5.get(fecha5.DATE) - 7,0,0,0);
		Calendar fecha6 = Calendar.getInstance();
		fecha6.set(fecha6.get(fecha6.YEAR),fecha6.get(fecha1.MONTH), fecha6.get(fecha6.DATE),0,0,0);
	
		System.out.println(fecha1 == fecha3);
		System.out.println(fechau.getTime());
	}
}
