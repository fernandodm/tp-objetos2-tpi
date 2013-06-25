package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import excepciones.ExcepcionTodaviaNoSeHospedoEnEsteHotelOSuReservaNoHaFinalizado;

public class Hotel {
	private String nombre;
	private String pais;
	private String direccion;
	private int telefono;
	private int categoria;
	private String checkIn;
	private String checkOut;
	private String ciudad;
	private List<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private List<FormaDePago> tarjetasAceptadas = new ArrayList<FormaDePago>();
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	private SistemaDeBusqueda sistemaEnElQueEstaCargado;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public List<FormaDePago> getTarjetasAceptadas() {
		return tarjetasAceptadas;
	}
	public void setTarjetasAceptadas(List<FormaDePago> tarjetasAceptadas) {
		this.tarjetasAceptadas = tarjetasAceptadas;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}


	public SistemaDeBusqueda getSistemaEnElQueEstaCargado() {
		return sistemaEnElQueEstaCargado;
	}
	public void setSistemaEnElQueEstaCargado(
			SistemaDeBusqueda sistemaEnElQueEstaCargado) {
		this.sistemaEnElQueEstaCargado = sistemaEnElQueEstaCargado;
	}
	
	public Hotel(){
		
	}

	public Hotel(String nombre, String pais, String direccion, int tel, int categoria, String checkIn,
			String checkOut, String ciudad, SistemaDeBusqueda sistema){
		this.setNombre(nombre);
		this.setPais(pais);
		this.setDireccion(direccion);
		this.setTelefono(tel);
		this.setCategoria(categoria);
		this.setCheckIn(checkIn);
		this.setCheckOut(checkOut);
		this.setCiudad(ciudad);
		this.setSistemaEnElQueEstaCargado(sistema);
		
	}
	
	public void eliminarReserva(Reserva unaReserva){
	
	 	getReservas().remove(unaReserva);

	}
	
	public List<Reserva> reservasActuales(){
		
		List<Reserva> actuales = new ArrayList<Reserva>();
		
		Calendar diaActual = Calendar.getInstance();
		diaActual.set(diaActual.get(diaActual.YEAR),diaActual.get(diaActual.MONTH),
				diaActual.get(diaActual.DATE),0,0,0);
					
		for(Reserva each: getReservas()){
			if((Comparador.sonIguales(diaActual, each.getFechaDeIngreso())) || diaActual.after(each.getFechaDeIngreso())
					&& diaActual.before(each.getFechaDeSalida())){
				
				actuales.add(each);
			}
			
		}
		return actuales;		
	}
	
	public List<Reserva> reservasEnLosSiguientesNDias(int n){
		
		List<Reserva> lasReservas = new ArrayList<Reserva>();
		List<Calendar> siguientesDias = GeneradorDeCalendar.generar(n);
		
		for(Reserva each: getReservas()){
			if(Comparador.listaContieneFecha(siguientesDias, each.getFechaDeIngreso())){
				lasReservas.add(each);
			}
		}
		return lasReservas;
	}
	
	public List<Reserva> reservasFuturas(){
		
		List<Reserva> reservasFuturas = new ArrayList<Reserva>();
		
		Calendar diaActual = Calendar.getInstance();
		diaActual.set(diaActual.get(diaActual.YEAR),diaActual.get(diaActual.MONTH),
				diaActual.get(diaActual.DATE),0,0,0);
		
		for(Reserva each: getReservas()){
			Calendar ingreso = each.getFechaDeIngreso();
			if(ingreso.after(diaActual)){
				reservasFuturas.add(each);
			}
		}
		return reservasFuturas;
	}

	public void agregarCalificacion(Calificacion cal){
		
		getCalificaciones().add(cal);
		
	}
	
	public int calificacionPromedio(){
		Integer promedio = 0;
		for(Calificacion each : getCalificaciones()){
			promedio = promedio + each.getPuntaje();
		}
		return(promedio / getCalificaciones().size());
	}
	
	public boolean lePuedeInteresarAlUsuario(Usuario user){
		
		boolean lePuedeInteresar = false;
		for(Habitacion each : getHabitaciones()){
			if(each.lePuedeInteresarAlUsuario(user)){
				lePuedeInteresar = true;
				break;
			}
		}
		
		return lePuedeInteresar;
	}
	

	public void actualizarInformacion(){
		getSistemaEnElQueEstaCargado().actualizarOfertaDelHotel(this);
	}

	public boolean tieneHabitacionesCon(Calendar desde, Calendar hasta,
			int huespedes) {
		
		for(Habitacion each: getHabitaciones()){
			if(each.estaDisponible(desde, hasta) && (each.getCapacidadMaxima() == huespedes)){
				return true;
			}
		}
		
		return false;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
