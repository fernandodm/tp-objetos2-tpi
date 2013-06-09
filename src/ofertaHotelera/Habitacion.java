package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Habitacion {
	private int capacidadMaxima;
	private boolean camaTwin;
	private List<List<Calendar>> diasOcupados = new ArrayList<List<Calendar>>();
	
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
	public List<List<Calendar>> getDiasOcupados() {
		return diasOcupados;
	}
	public void setDiasOcupados(List<List<Calendar>> diasOcupados) {
		this.diasOcupados = diasOcupados;
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
}
