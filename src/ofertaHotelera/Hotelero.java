package ofertaHotelera;

import java.util.*;
import ofertaHotelera.Auxiliar;
import descuentos.Descuento;
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
	 * Agrega a una habitación un periodo con precio, solo si los días a los que se quiere
	 * setear un precio, no estaban previamente seteados.
	 * Precondición: la habitación debe pertenecer a uno de los hoteles del hotelero
	 * @param periodo
	 * @param hab
	 */
	public void ponerPrecioHabRangoDias(PeriodoConPrecio periodo, Habitacion hab){
		if(periodo.algunDiaCoincide(hab.getPreciosPorFecha())){
			//TODO deberiamos arrojar una excepcion que diga que uno de los dias 
			// ya tenia un precio fijado. 
			System.out.println("Uno o mas de los dias del periodo ya tiene un precio fijado");
			
		}
		else{
			hab.getPreciosPorFecha().add(periodo);
		}
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
	public void subastarHabitacion(Habitacion h, Calendar fechaIni, Calendar fechaFin, int valorInicial, SistemaDeBusqueda sistem){
		
		Subasta subasta = new Subasta(h,valorInicial,fechaIni,fechaFin);
		sistem.agregarSubasta(subasta);
		
	}
	
}
