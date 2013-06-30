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
	
	public boolean lePuedeInteresarAlUsuario(Usuario user) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		boolean ok = false;
		if(user.getPreferencias().size() == 1){
			ok = user.getPreferencias().get(0).lePuedeInteresarHabitacion(this);
		}
		for(int i = 0 ; i < user.getPreferencias().size() - 1 ; i++){
			ok = user.getPreferencias().get(i).lePuedeInteresarHabitacion(this) && user.getPreferencias().get(i+1).lePuedeInteresarHabitacion(this);
		}
		return ok;
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
	
	public float precioTotal(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		float precio = 0;
		int cantidad = cantidadDeDias(fechaInicio, fechaFin);
		List<Calendar> fechas = GeneradorDeCalendar.generar(cantidad);
		
		for(Calendar each: fechas){
			
			precio = precio + precioDeLaFecha(each);
			
		}
		return precio;
	}
	
	/**
	 * Devuelve el precio que sale por una noche en la fecha "fecha"
	 * se asume q siempre va a tener un precio asignado
	 * @param fecha
	 * @return
	 */

	public boolean tieneDescuentos(){
		
		return getDescuentos().size() > 0;
	}
	
	/**
	 * Dada dos fecha devuelve la cantidad de dias que hay desde
	 * "desde" hasta "hasta"
	 * @param desde
	 * @param hasta
	 * @return
	 */
	public int cantidadDeDias(Calendar desde, Calendar hasta) {
		
		Calendar inicio = desde;
		int cant = 0;
		while(!Comparador.sonIguales(inicio, hasta)){
			cant = cant + 1;
			inicio.set(inicio.get(inicio.YEAR),inicio.get(inicio.MONTH),inicio.get(inicio.DATE)+1);
			System.out.println(inicio.getTime());
			System.out.println(hasta.getTime());
		}
		cant = cant + 1;
		return cant;
	}
	
	/**
	 * Devuelve todos los descuentos de la habitacion como string
	 */
	public String obtenerDescuento() {
		
		
		String descuentos = "";
		if(tieneDescuentos()){
			for(Descuento each: getDescuentos()){
				
				descuentos = descuentos + each.descuento();
			}
		}else{
			return "No hay descuentos";
		}
		return descuentos;
	}
	
	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c.set(2013,06,3);
		//System.out.println(c.getTime());
		//System.out.println(cantidadDeDias(c1,c));
		
	}
}
