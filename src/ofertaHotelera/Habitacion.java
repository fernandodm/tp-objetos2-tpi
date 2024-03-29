package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
	
	/**
	 * Elimina el periodo que se paso como parametro
	 * @param unPeriodo
	 */
	public void eliminarHorario(Periodo unPeriodo) {
		
		for(Periodo each: getDiasOcupados()){
			if(each.equals(unPeriodo)){
				getDiasOcupados().remove(each);
				break;
			}
		}
		
	}
	
	/**
	 * Retorna true si la fecha pasada como par�metro est� incluida en alguno de los preciosPorFecha.
	 * @param fecha
	 * @return hay
	 * @throws 
	 */
	
	
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
	
	/**
	 * Retorna el precio de una fecha si es que hay precio para ella, de lo contrario se lanza la excepci�n.
	 * @param fecha
	 * @return precio
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha.
	 */
	
	
	public float precioDeLaFecha(Calendar fecha) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		float precio = 0;
		if(hayPrecioParaFecha(fecha)){
			for(PeriodoConPrecio each : getPreciosPorFecha()){
				if(each.fechaEstaEnElPeriodo(fecha)){
					precio = each.getPrecio();
				}
			}
		} else {
			throw new ExcepcionNoHayPrecioEstablecidoParaTalFecha();
		}
		return precio;
		
	}
	
	/**
	 * Retorna el precio por noche promedio de una habitaci�n, tomando en cuenta todos sus precios posibles, que est�n en los preciosPorFecha.
	 * @param 
	 * @return promedio
	 * @throws 
	 */
	
	public float precioPorNochePromedio(){
		
		float promedio = 0;
		for(PeriodoConPrecio each : getPreciosPorFecha()){
			promedio = promedio + each.getPrecio();
		}
		return promedio / getPreciosPorFecha().size();
	}
	
	/**
	 * Retorna true si al usuario le puede interesa la habitaci�n, dependiendo de sus preferencias 
	 * @param user
	 * @return ok
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha.
	 */
	
	public boolean lePuedeInteresarAlUsuario(Usuario user) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		boolean ok = false;
		if(user.getPreferencias().size() == 1){
			ok = user.getPreferencias().get(0).lePuedeInteresarHabitacion(this);
		} else {
			for(int i = 0 ; i < user.getPreferencias().size() - 1 ; i++){
				ok = user.getPreferencias().get(i).lePuedeInteresarHabitacion(this) && user.getPreferencias().get(i+1).lePuedeInteresarHabitacion(this);
			}
		}
		return ok;
	}

	/**
	 * Retorna true si la habitacion esta disponible entre las fechas
	 * de los parametros, false en caso contrario
	 * @param desde
	 * @param hasta
	 * @return
	 */
	public boolean estaDisponible(Calendar desde, Calendar hasta) {
		
		for(Periodo each: getDiasOcupados()){
		
			if( each.estaEntre(desde ,hasta)){
				
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Imprime los servicios en la consola
	 */
	public void imprimirServicios(){
		
		System.out.println(" -Servicios:");
		for(Servicio each: getServicios()){
			System.out.println("   -" + each.getNombre() + " $" + each.getPrecio());
		}
	}
	
	/**
	 * Retorna el precio total de una habitacion en un periodo
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha
	 */
	public float precioTotal(Calendar fechaInicio, Calendar fechaFin) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		float precio = 0;
		
		List<Calendar> fechas = GeneradorDeCalendar.generarDiasEntre(fechaInicio, fechaFin);
		
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
	
	public void agregarDiaReservado(Periodo periodo) {
		
		diasOcupados.add(periodo);
		
	}
	
	/**
	 * Retorna un string, "Si" si tiene camaTwin "No" en caso contrario
	 * @return
	 */
	public String tieneCamaTwin() {
		
		if(isCamaTwin()){
			
			return "Si";
		}
		
		return "No";
	}
	
	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		Calendar c1 = Calendar.getInstance();
		c.set(2013,06,3);
		System.out.println(c.getTime());
		//System.out.println(cantidadDeDias(c1,c));
		
		System.out.println(1f + 12f == 13f);
		
	}
}
