package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import descuentos.Descuento;



public class Habitacion {
	private Hotel hotel;
	private int capacidadMaxima;
	private boolean camaTwin;
	private int precioPorNoche; //Esto deberia funcionar como un precio por default, 
	//si lo dejamos. No lo saque xq se cagaban otras clases.
	private int numero;
	private List<Descuento> descuentos = new ArrayList<Descuento>();
	private List<List<Calendar>> diasOcupados = new ArrayList<List<Calendar>>();
	private Map<List<Calendar>,Integer> preciosPorFecha = new HashMap<List<Calendar>,Integer>();  
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public List<Descuento> getDescuentos() {
		return descuentos;
	}
	public void setDescuentos(List<Descuento> descuentos) {
		this.descuentos = descuentos;
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	public Map<List<Calendar>, Integer> getPreciosPorFecha() {
		return preciosPorFecha;
	}
	public void setPreciosPorFecha(Map<List<Calendar>, Integer> preciosPorFecha) {
		this.preciosPorFecha = preciosPorFecha;
	}
	public int getPrecioPorNoche() {
		return precioPorNoche;
	}
	public void setPrecioPorNoche(int precioPorNoche) {
		this.precioPorNoche = precioPorNoche;
	}
	
	public Habitacion(){
		
	}

	public Habitacion(Hotel hotel, int capMax, boolean twin, int precio, int nro){
		this.setHotel(hotel);
		this.setCapacidadMaxima(capMax);
		this.setCamaTwin(twin);
		this.setPrecioPorNoche(precio);
		this.setNumero(nro);
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
		

		return (user.getPreferencia().lePuedeInteresarHabitacion(this));
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
	
	public void imprimirServicios(){
		
		System.out.println(" -Servicios:");
		for(Servicio each: getServicios()){
			System.out.println("   -" + each.getNombre() + " $" + each.getPrecio());
		}
	}
	
}
