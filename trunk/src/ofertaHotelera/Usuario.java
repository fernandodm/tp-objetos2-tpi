package ofertaHotelera;

import java.util.ArrayList;
import java.util.List;
//esteban se la lastra
public class Usuario {
	//
	private SistemaDeBusqueda sistema;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private String nombreUsuario;
	private String contrasenha;
	private String nombre;
	private boolean online;
	
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
	
	public List<Reserva> todasLasReservas(){
		return getReservas();
	}
	
	public List<Reserva> reservaPorCiudad(String unaCiudad){
		
		List<Reserva> misReservas = new ArrayList<Reserva>();
		
		for(Reserva each : getReservas()){
			if(each.ciudadDelHotel().equals(unaCiudad)){
				misReservas.add(each);
			}
		}
		return misReservas;
	}
	public List<String> ciudadesConReservas() {
		
		List<String> ciudades = new ArrayList<String>();
		
		for(Reserva each: getReservas()){
			ciudades.add(each.ciudadDelHotel());
		}
		
		return ciudades;
	}
	
}
