package hotel;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import sistemaYUsuario.Sistema;
import sistemaYUsuario.Usuario;

import auxiliares.Auxiliar;
import auxiliares.GeneradorDeCalendar;

import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;


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
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	private Sistema sistemaEnElQueEstaCargado;
	private List<String> mail = new ArrayList<String>();
	
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


	public Sistema getSistemaEnElQueEstaCargado() {
		return sistemaEnElQueEstaCargado;
	}
	public void setSistemaEnElQueEstaCargado(
			Sistema sistemaEnElQueEstaCargado) {
		this.sistemaEnElQueEstaCargado = sistemaEnElQueEstaCargado;
	}
	
	public List<String> getMail() {
		return mail;
	}
	public void setMail(List<String> mail) {
		this.mail = mail;
	}
	public Hotel(){
		
	}

	public Hotel(String nombre, String pais, String direccion, int tel, int categoria, String checkIn,
			String checkOut, String ciudad, Sistema sistema){
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
	
	/**
	 * Elimina una reserva
	 * @param unaReserva
	 */
	public void eliminarReserva(Reserva unaReserva){
	
	 	getReservas().remove(unaReserva);

	}
	
	/**
	 * Retorna todas las reservas actuales
	 * @return
	 */
	public List<Reserva> reservasActuales(){
		
		List<Reserva> actuales = new ArrayList<Reserva>();
		
		Calendar diaActual = Calendar.getInstance();
		diaActual.set(diaActual.get(diaActual.YEAR),diaActual.get(diaActual.MONTH),
				diaActual.get(diaActual.DATE),0,0,0);
		diaActual.clear(Calendar.MILLISECOND);
					
		for(Reserva each: getReservas()){
			Calendar desde = each.getPeriodo().getDesde();
			Calendar hasta = each.getPeriodo().getHasta();
			if((diaActual.after(desde) && diaActual.before(hasta)) 
				|| diaActual.equals(desde) 
				|| diaActual.equals(hasta)){
				
				actuales.add(each);
			}
		}
		return actuales;		
	}
	
	/**
	 * Retorna las siguientes n reservas
	 * @param n
	 * @return
	 */
	public List<Reserva> reservasEnLosSiguientesNDias(int n){
		
		List<Reserva> lasReservas = new ArrayList<Reserva>();
		List<Calendar> siguientesDias = GeneradorDeCalendar.generar(n);
		
		for(Reserva each: getReservas()){
			if(Auxiliar.listaContieneFecha(siguientesDias, each.getPeriodo().getDesde())){
				lasReservas.add(each);
			}
		}
		return lasReservas;
	}
	
	/**
	 * Retorna todas las reservas futuras
	 * @return
	 */
	public List<Reserva> reservasFuturas(){
		
		List<Reserva> reservasFuturas = new ArrayList<Reserva>();
		
		Calendar diaActual = Calendar.getInstance();
		diaActual.set(diaActual.get(diaActual.YEAR),diaActual.get(diaActual.MONTH),
				diaActual.get(diaActual.DATE),0,0,0);
		
		for(Reserva each: getReservas()){
			Calendar ingreso = each.getPeriodo().getDesde();
			if(ingreso.after(diaActual)){
				reservasFuturas.add(each);
			}
		}
		return reservasFuturas;
	}

	/**
	 * Agrega una calificiaci�n a la lista de calificaciones, operaci�n total
	 * @param 
	 * @return 
	 * @throws 
	 */
	
	public void agregarCalificacion(Usuario user, int puntaje, String comentario){
		
		Calificacion cal = new Calificacion(user,puntaje,comentario);
		
		getCalificaciones().add(cal);
		
	}
	
	/**
	 * Retorna el promedio de todos los puntajes dados en las calificaciones
	 * @param 
	 * @return 
	 * @throws 
	 */
	
	public int calificacionPromedio(){
		Integer promedio = 0;
		for(Calificacion each : getCalificaciones()){
			promedio = promedio + each.getPuntaje();
		}
		return(promedio / getCalificaciones().size());
	}
	
	/**
	 * Retorna true si al usuario le puede interesa alguna de las habitaciones que tiene el hotel.
	 * @param user
	 * @return lePuedeInteresar
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha.
	 */
	
	public boolean lePuedeInteresarAlUsuario(Usuario user) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		boolean lePuedeInteresar = false;
		for(Habitacion each : getHabitaciones()){
			if(each.lePuedeInteresarAlUsuario(user)){
				lePuedeInteresar = true;
				break;
			}
		}
		
		return lePuedeInteresar;
	}
	
	/**
	 * Actualiza la informaci�n de un hotel, sacando el hotel del sistema en el que esta ingresado y agreg�ndolo nuevamente, para que se le notifique a alg�n interesado alg�n cambio en el hotel.
	 * Se tiene como precondici�n que el hotel ya estaba ingresado en el sistema.
	 * @param 
	 * @return 
	 * @throws .
	 */

	public void actualizarInformacion(){
		getSistemaEnElQueEstaCargado().actualizarOfertaDelHotel(this);
	}

	/**
	 * Retorna true si el hotel contiene una habitacion que sastisfaga
	 * con los parametros 
	 * @param desde
	 * @param hasta
	 * @param huespedes
	 * @return
	 */
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
	 * Agrega una reserva
	 * @param reserva
	 */
	public void agregarReserva(Reserva reserva) {
		
		reservas.add(reserva);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
