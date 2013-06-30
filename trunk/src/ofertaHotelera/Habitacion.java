package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import descuentos.Descuento;
import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;



public class Habitacion {
	private Hotel hotel;
	private int capacidadMaxima;
	private boolean camaTwin;
	private int numero;
	private List<Descuento> descuentos = new ArrayList<Descuento>();
	private List<Periodo> diasOcupados = new ArrayList<Periodo>();
	private List<PeriodoConPrecio> preciosPorFecha = new ArrayList<PeriodoConPrecio>();  
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public List<Periodo> getDiasOcupados() {
		return diasOcupados;
	}
	public void setDiasOcupados(List<Periodo> diasOcupados) {
		this.diasOcupados = diasOcupados;
	}
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

	public List<PeriodoConPrecio> getPreciosPorFecha() {
		return preciosPorFecha;
	}
	public void setPreciosPorFecha(List<PeriodoConPrecio> preciosPorFecha) {
		this.preciosPorFecha = preciosPorFecha;
	}
	public Habitacion(){
		
	}

	public Habitacion(Hotel hotel, int capMax, boolean twin, int nro){
		this.setHotel(hotel);
		this.setCapacidadMaxima(capMax);
		this.setCamaTwin(twin);
		this.setNumero(nro);
	}
	
	public void eliminarHorario(Periodo unPeriodo) {
		
		for(Periodo each: getDiasOcupados()){
			if(each.equals(unPeriodo)){
				getDiasOcupados().remove(each);
				break;
			}
		}
		
	}
	
	public boolean hayPrecioParaFecha(Calendar fecha){
		boolean hay = false;
		for(PeriodoConPrecio each : getPreciosPorFecha()){
			if(each.fechaEstaEnElPeriodo(fecha)){
				hay = true;
				break;
			}
		}
		return hay;
	}
	
	public float precioDeLaFecha(Calendar fecha) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		float precio = 0;
		for(PeriodoConPrecio each : getPreciosPorFecha()){
			if(each.fechaEstaEnElPeriodo(fecha)){
				precio = each.getPrecio();
				break;
			} else{
				throw new ExcepcionNoHayPrecioEstablecidoParaTalFecha();
			}
		}
		return precio;
		
	}
	
	public float precioPorNochePromedio(){
		
		float promedio = 0;
		for(PeriodoConPrecio each : getPreciosPorFecha()){
			promedio = each.getPrecio();
		}
		return promedio;
	}
	
	public boolean lePuedeInteresarAlUsuario(Usuario user){
		
		return (user.getPreferencia().lePuedeInteresarHabitacion(this));
	}

	public boolean estaDisponible(Calendar desde, Calendar hasta) {
		
		for(Periodo each: getDiasOcupados()){
		
			if( each.estaEntre(desde ,hasta)){
				
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
