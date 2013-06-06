package hotelesYrelacionados;

import java.util.Calendar;
import java.util.*;

public class Habitacion {
	private int capacidadMaxima;
	private boolean camaTwin;
	private List<List<Calendar>> diasOcupadas;
	
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
	public List<List<Calendar>> getDiasOcupadas() {
		return diasOcupadas;
	}
	public void setDiasOcupadas(List<List<Calendar>> diasOcupadas) {
		this.diasOcupadas = diasOcupadas;
	}
	
	public Habitacion(int capacidad, boolean esTwin){
		this.setCamaTwin(esTwin);
		this.setCapacidadMaxima(capacidad);
		this.setDiasOcupadas(new ArrayList<List<Calendar>>());
	}
	
	public boolean disponibilidad(Calendar desde, Calendar hasta){
		return true;
	}

}
