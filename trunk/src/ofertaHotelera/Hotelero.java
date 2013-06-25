package ofertaHotelera;

import java.util.*;
import ofertaHotelera.OperadorDeColecciones;

public class Hotelero {
	
	private String nombre;
	private List<Hotel> misHoteles;

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
	
	public Hotelero(String unNombre, List<Hotel> hoteles){
		this.setNombre(unNombre);
		this.setMisHoteles(hoteles);
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

	public void ponerPrecioHabRangoDias(List<Calendar> dias, int precio, Habitacion hab){
		hab.getPreciosPorFecha().put(dias, (Integer) precio);
	}
	
	
	
}
