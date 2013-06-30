package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import excepciones.ExcepcionNoEstaOnline;
import excepciones.ExcepcionNoSeEncontroReserva;
import excepciones.ExcepcionOfertaInferior;
import excepciones.ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia;
import excepciones.ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado;

public class Usuario implements Observer{

	private SistemaDeBusqueda sistema;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private String nombreUsuario;
	private String contrasenha;
	private String nombre;
	private String mail;
	private boolean online;
	private List<Preferencia> preferencias = new ArrayList<Preferencia>();
	
	public Usuario(){};
	
	public Usuario(SistemaDeBusqueda sistema, String nombreUsuario, String contrasenha, String nombre,
			boolean online, String mail) {
	
		this.sistema = sistema;
		this.nombreUsuario = nombreUsuario;
		this.contrasenha = contrasenha;
		this.nombre = nombre;
		this.online = online;
		this.mail= mail;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	
	public List<Preferencia> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(List<Preferencia> preferencias) {
		this.preferencias = preferencias;
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
		
	public void calificarHotel(Hotel h, int puntaje, String comentario) throws ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado, ExcepcionNoEstaOnline{
		
		boolean seHospedo = false;
		
		if(isOnline()){
			
		for(Reserva each : getReservas()){
			
			//para la fecha de hoy setearle la hora en 0
			if(each.getHotel().equals(h) && Calendar.getInstance().after(each.getPeriodo().getHasta())){
				seHospedo = true;
				break;
				}
			}

		} else {
			throw new ExcepcionNoEstaOnline();
		}
		if(seHospedo){
			Calificacion cal = new Calificacion(this,puntaje,comentario);
			
			h.agregarCalificacion(cal);
		}else{
			throw new ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado();
		}
		
		
	}
	
	public void ofertarEnSubasta(Subasta sub, float unMonto) throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		if(isOnline()){
			sub.agragarOferta(this, unMonto);
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	public void suscribirseAlAvisoDeOfertasHoteleras(SistemaDeBusqueda s) throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		if(isOnline()){
			if(!(getPreferencia() == null)){
				s.agregarSuscripto(this);
			} else {
				throw new ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia();
			}
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	public boolean puedeEstarInteresadoEnHotel(Hotel h){
		
		boolean lePuedeInteresar = false;
		for(Habitacion each : h.getHabitaciones()){
			if(getPreferencia().lePuedeInteresarHabitacion(each)){
				lePuedeInteresar = true;
				break;
			}
		}
		
		return lePuedeInteresar;
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		for(Habitacion each : ((Hotel) arg1).getHabitaciones()){
			
			if(this.getPreferencia().lePuedeInteresarHabitacion(each)){
				//aca iria el método en el que el usuario recibe el mail con la informacion.
				
			}
			
		}
		
	}
}
