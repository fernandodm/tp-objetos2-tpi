package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Habitacion {
	private Hotel hotel;
	private int capacidadMaxima;
	private boolean camaTwin;
	private List<List<Calendar>> diasOcupados = new ArrayList<List<Calendar>>();
	private int precioPorNoche;
	
	public Habitacion(){
	}
	
	public Habitacion(int capacidadMaxima, boolean camaTwin) {
	
		this.capacidadMaxima = capacidadMaxima;
		this.camaTwin = camaTwin;
	}
	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}
	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}
	public boolean isCamaTwin() {
		return camaTwin;
	}
	public void setCamaTwin(boolean camaTwin) {
		this.camaTwin = camaTwin;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<List<Calendar>> getDiasOcupados() {
		return diasOcupados;
	}
	public void setDiasOcupados(List<List<Calendar>> diasOcupados) {
		this.diasOcupados = diasOcupados;
	}
	
	
	
	
	public int getPrecioPorNoche() {
		return precioPorNoche;
	}

	public void setPrecioPorNoche(int precioPorNoche) {
		this.precioPorNoche = precioPorNoche;
	}

	public void eliminarHorario(Calendar fechaDeIngreso, Calendar fechaDeSalida) {
		
		List<Calendar> diasReservados = new ArrayList<Calendar>();
		diasReservados.add(fechaDeIngreso);
		diasReservados.add(fechaDeSalida);
		
		for(int i = 0; i < getDiasOcupados().size(); i++){
			if(getDiasOcupados().get(i).equals(diasReservados)){
				getDiasOcupados().remove(i);
				break;
			}
		}
		
	}
	
	public boolean lePuedeInteresarAlUsuario(Usuario user){
		
		boolean lePuedeInteresar = false;
		
		if(user.getPreferencia().lePuedeInteresarHabitacion(this)){
			lePuedeInteresar = true;
		}
		
		return lePuedeInteresar;
	}

	public boolean estaDisponible(Calendar desde, Calendar hasta) {
		
		for(List<Calendar> each: getDiasOcupados()){
			Calendar fechaInicio = each.get(0);
			Calendar fechaFin = each.get(1);
			if( Comparador.between(desde, fechaInicio, fechaFin) || Comparador.between(hasta, fechaInicio, fechaFin)){
				
				return false;
			}
		}
		
		return true;
	}
	
}
