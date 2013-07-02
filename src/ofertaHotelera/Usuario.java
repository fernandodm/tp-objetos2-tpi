package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import excepciones.ExcepcionHabitacionNoDisponible;
import excepciones.ExcepcionLaSubastaAunNoHaIniciado;
import excepciones.ExcepcionLaSubastaYaHaFinalizado;
import excepciones.ExcepcionNoEstaOnline;
import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;
import excepciones.ExcepcionNoSeEncontroReserva;
import excepciones.ExcepcionOfertaInferior;
import excepciones.ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia;
import excepciones.ExcepcionTodaviaNoSeHospedoEnEsteHotel;


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

	/**
	 * Retorna todas la reservas realizadas
	 * @return
	 * @throws ExcepcionNoEstaOnline
	 */
	public List<Reserva> todasLasReservas()throws ExcepcionNoEstaOnline{
		
		if(isOnline()){
	
			return getReservas();
		
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	/**
	 * Retorna todas las reservas que se realizo en una determinada ciudad
	 * @param unaCiudad
	 * @return
	 * @throws ExcepcionNoEstaOnline
	 */
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
	
	/**
	 * Retorna las ciudades donde relizo una reserva
	 * @return
	 * @throws ExcepcionNoEstaOnline
	 */
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
	/**
	 * Retorna todas las reservas futuras
	 * @return
	 * @throws ExcepcionNoEstaOnline
	 */
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
	
	/**
	 * Elimina la reserva que se pasa por parametro
	 * @param unaReserva
	 * @throws ExcepcionNoSeEncontroReserva
	 */
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
	/**
	 * Cancela una reserva
	 * @param unaReserva
	 * @throws ExcepcionNoEstaOnline
	 * @throws ExcepcionNoSeEncontroReserva
	 */
	public void cancelarReserva(Reserva unaReserva) throws ExcepcionNoEstaOnline, ExcepcionNoSeEncontroReserva{
		
		if(isOnline()){
			eliminarLaReserva(unaReserva);
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}	 
		
	/**
	 * Devuelve true si el usuario alguna vez se hospedo en tal hotel
	 * @param h
	 * */
	
	public boolean seHospedoEnElHotel(Hotel h){
		
		boolean seHospedo = false;
		
		for(Reserva each : getReservas()){
			if(each.getHotel().equals(h) && Calendar.getInstance().after(each.getPeriodo().getHasta())){
				seHospedo = true;
				break;
				}
			}
		
		return seHospedo;
	}
	
	/**
	 * Si esta onlina y alguna vez se hospedo en tal hotel, agrega una calificacion
	 * @param h, puntaje, comentario
	 * @throws ExcepcionNoEstaOnline
	 * @throws ExcepcionTodaviaNoSeHospedoEnEsteHotel.
	 */
	
	public void calificarHotel(Hotel h, int puntaje, String comentario) throws ExcepcionTodaviaNoSeHospedoEnEsteHotel, ExcepcionNoEstaOnline{
		
		
		if(isOnline()){
			if(seHospedoEnElHotel(h)){
				Calificacion cal = new Calificacion(this,puntaje,comentario);
				h.agregarCalificacion(cal);
			} else {
				throw new ExcepcionTodaviaNoSeHospedoEnEsteHotel();
			}
		} else {
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	/**
	 * Oferta en una subasta si está online, la subasta se comportará dependiendo de su estado
	 * @param sub, unMonto
	 * @return
	 * @throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline.
	 */
	
	public void ofertarEnSubasta(Subasta sub, float unMonto) throws ExcepcionLaSubastaAunNoHaIniciado, ExcepcionLaSubastaYaHaFinalizado, ExcepcionOfertaInferior, ExcepcionNoEstaOnline{
		if(isOnline()){
			sub.agragarOferta(this, unMonto);
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	/**
	 * Se suscribe al aviso de ofertas hoteleras de acuerdo a sus preferencias, si es que las tiene o esta online
	 * @param s
	 * @return
	 * @throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline.
	 */
	
	public void suscribirseAlAvisoDeOfertasHoteleras(SistemaDeBusqueda s) throws ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia,ExcepcionNoEstaOnline{
		
		if(isOnline()){

			if(!(getPreferencias() == null)){
				s.agregarSuscripto(this);
				} else {
					throw new ExcepcionSeDebeTenerAlMenosUnCriterioDePreferencia();
				}
		}else{
			throw new ExcepcionNoEstaOnline();
		}
	}
	
	

	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		try {
			if(((Hotel) arg1).lePuedeInteresarAlUsuario(this)){
				
				
			}
		} catch (ExcepcionNoHayPrecioEstablecidoParaTalFecha e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Agrega una reserva
	 * @param reserva
	 */
	public void agregarReserva(Reserva reserva) {
		
		reservas.add(reserva);
		
	}
	
	/**
	 * Reserva una habitacion para el usuario
	 * @param unaHabitacion
	 * @param formaDePago
	 * @param desde
	 * @param hasta
	 * @throws ExcepcionHabitacionNoDisponible
	 * @throws ExcepcionNoEstaOnline
	 */
	public void reservarHabitacion(Habitacion unaHabitacion, String formaDePago,
			Calendar desde, Calendar hasta) throws ExcepcionHabitacionNoDisponible, ExcepcionNoEstaOnline{
		
		if(isOnline()){
			getSistema().realizarReserva(this,unaHabitacion, formaDePago, desde, hasta);
		}else{
			throw new ExcepcionNoEstaOnline();
		}
		
	}
}
