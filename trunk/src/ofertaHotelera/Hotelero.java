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
	
	public Hotelero(String unNombre, List<Hotel> hoteles, String mail){
		this.setNombre(unNombre);
		this.setMisHoteles(hoteles);
		this.setMail(mail);
	}
	
	public List<Reserva> obtenerReservasActuales(){
		List<Reserva> res= new ArrayList<Reserva>();
		for(Hotel each : misHoteles){
			OperadorDeColecciones.concatenarReservas(res, each.reservasActuales());
		}
		return res;
	}
	
	public List<Reserva> obtenerReservasFuturas(){
		List<Reserva> res= new ArrayList<Reserva>();
		for(Hotel each : misHoteles){
			OperadorDeColecciones.concatenarReservas(res, each.reservasFuturas());
		}
		return res;
	}
	
	public List<Reserva> obtenerReservasEnNDias(int dias){
		List<Reserva> res= new ArrayList<Reserva>();
		for(Hotel each : misHoteles){
			OperadorDeColecciones.concatenarReservas(res, each.reservasEnLosSiguientesNDias(dias));
		}
		return res;
	}
	
	public void ponerPrecioHabRangoDias(PeriodoConPrecio periodo, Habitacion hab){
		if(periodo.algunDiaCoincide(hab.getPreciosPorFecha())){
			hab.getPreciosPorFecha().add(periodo);
		}
		else{
			//TODO deberiamos arrojar una excepcion que diga que uno de los dias 
			// ya tenia un precio fijado. 
			System.out.println("Uno o mas de los dias del periodo ya tiene un precio fijado");
		}
	}
	
	public void agregarDescuento(Descuento descuento, List<Habitacion> habs){
		for(Habitacion each : habs){
			each.getDescuentos().add(descuento);
		}
	}
	
	public void subastarHabitacion(Habitacion h, Calendar fechaIni, Calendar fechaFin, int valorInicial, SistemaDeBusqueda sistem){
		
		Subasta subasta = new Subasta(h,valorInicial,fechaIni,fechaFin);
		sistem.agregarSubasta(subasta);
		
	}
	
}
