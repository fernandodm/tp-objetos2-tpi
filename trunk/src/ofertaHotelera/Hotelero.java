package ofertaHotelera;

import java.util.*;
import descuentos.Descuento;
import excepciones.Excepcion1OMasDiasYaTenianPrecioSetteado;
import ofertaHotelera.OperadorDeColecciones;

public class Hotelero {
	
	private String nombre;
	private List<Hotel> misHoteles;
	private String mail;
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Hotel> getMisHoteles() {
		return misHoteles;
	}
	public void setMisHoteles(List<Hotel> misHoteles) {
		this.misHoteles = misHoteles;
	}
	
	/**
	 * Constructor de la clase hotelero.
	 * @param unNombre
	 * @param hoteles
	 * @param mail
	 */
	public Hotelero(String unNombre, List<Hotel> hoteles, String mail){
		this.setNombre(unNombre);
		this.setMisHoteles(hoteles);
		this.setMail(mail);
	}
	
	/**
	 * Retorna las reservas actuales para todos los hoteles del hotelero.
	 */
	public List<Reserva> obtenerReservasActuales(){
		List<Reserva> res= new ArrayList<Reserva>();
		for(Hotel each : misHoteles){
			OperadorDeColecciones.concatenarReservas(res, each.reservasActuales());
		}
		return res;
	}
	
	/**
	 * Retorna las reservas futuras para todos los hoteles del hotelero.
	 */
	public List<Reserva> obtenerReservasFuturas(){
		List<Reserva> res= new ArrayList<Reserva>();
		for(Hotel each : misHoteles){
			OperadorDeColecciones.concatenarReservas(res, each.reservasFuturas());
		}
		return res;
	}
	
	/**
	 * Retorna las reservas en los proximos n días para todos los hoteles del hotelero.
	 * @param dias
	 */
	public List<Reserva> obtenerReservasEnNDias(int dias){
		List<Reserva> res= new ArrayList<Reserva>();
		for(Hotel each : misHoteles){
			OperadorDeColecciones.concatenarReservas(res, each.reservasEnLosSiguientesNDias(dias));
		}
		return res;
	}
	
	/**
	 * Agrega a una habitación un período con precio, solo si los días a los que se quiere
	 * settear un precio, no estaban previamente setteados. Si algún día ya había sido
	 * setteado previamente, se lanza la excepción.
	 * Precondición: la habitación debe pertenecer a uno de los hoteles del hotelero.
	 * @param periodo
	 * @param hab
	 */
	public void ponerPrecioHabRangoDias(PeriodoConPrecio periodo, Habitacion hab) 
			throws Excepcion1OMasDiasYaTenianPrecioSetteado{
		if(periodo.algunDiaCoincide(hab.getPreciosPorFecha())){
			throw new  Excepcion1OMasDiasYaTenianPrecioSetteado();
		}
		else{
			hab.getPreciosPorFecha().add(periodo);
		}
	}
	
	/**
	 * Saca de una habitación un período con precio (el hotelero lo utilizaría si 
	 * por ejemplo quiere cambiar sus precios).
	 * Precondición: la habitación debe pertenecer a uno de los hoteles del hotelero.
	 * Precondición: el período con precio a quitar debe estar incluido en la colección
	 * de períodos con precio de la habitación pasada como parámetro (el hotelero 
	 * debería sacar un precio solo si sabe que existe).
	 * @param periodo
	 * @param hab
	 */
	public void sacarPrecioHabRangoDias(PeriodoConPrecio periodo, Habitacion hab){
		hab.getPreciosPorFecha().remove(periodo);
		hab.getHotel().getSistemaEnElQueEstaCargado().actualizarOfertaDelHotel(hab.getHotel());
	}
	
	/**
	 * Agrega un descuento a la lista de habitaciones pasadas como parámetro.
	 * Precondición: las habitaciones deben pertenecer a hoteles del hotelero.
	 * @param descuento
	 * @param habs
	 */
	public void agregarDescuento(Descuento descuento, List<Habitacion> habs){
		for(Habitacion each : habs){
			each.getDescuentos().add(descuento);
		}
	}
	
	/**
	 * Agrega una habitación a subastar al sistema.
	 * Precondicion: la habitación debe pertenecer a un hotel del hotelero.
	 * @param h
	 * @param fechaIni
	 * @param fechaFin
	 * @param valorInicial
	 * @param sistem
	 */
	public void subastarHabitacion(Habitacion hab, float valorInicial, Calendar desde, Calendar hasta, SistemaDeBusqueda sistema){
		sistema.agregarSubasta(hab,valorInicial,desde,hasta);
	}
	
}
