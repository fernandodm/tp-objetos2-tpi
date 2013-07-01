package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import descuentos.Descuento;
import descuentos.DescuentoPorFecha;

//TODO import testOfertaHotelera.SistemaDeBusquedaTest;

import excepciones.ExcepcionNoSeEncontroHabitacion;
import excepciones.ExcepcionElNombreDeUsuarioYaExiste;
import excepciones.ExcepcionHotelNoEncontrado;
import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;
import excepciones.ExcepcionPasswordIncorrecto;
import excepciones.ExcepcionUsuarioIncorrecto;

public class SistemaDeBusqueda extends Observable{
	
	
	private List<Hotel> hoteles = new ArrayList<Hotel>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Subasta> subastas = new ArrayList<Subasta>();
	
	public List<Hotel> getHoteles() {
		return hoteles;
	}

	public void setHoteles(List<Hotel> hoteles) {
		this.hoteles = hoteles;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	public List<Subasta> getSubastas() {
		return subastas;
	}

	public void setSubastas(List<Subasta> subastas) {
		this.subastas = subastas;
	}

	/**
	 * Retorna true si existe el usuario en el sistema false en caso contrario
	 * @param nombre
	 * @return
	 */
	public boolean existeNombreDeUsuario(String nombre){
		
		for(Usuario each: getUsuarios()){
			if(each.getNombreUsuario().equals(nombre)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Agrega un usuario nuevo al sistema
	 * @param nombreUsuario
	 * @param pass
	 * @param nombre
	 * @param mail
	 * @throws ExcepcionElNombreDeUsuarioYaExiste
	 */
	public void registrarUsuario(String nombreUsuario, String pass, String nombre, String mail) throws ExcepcionElNombreDeUsuarioYaExiste{
		
		if(existeNombreDeUsuario(nombreUsuario)){
			throw new ExcepcionElNombreDeUsuarioYaExiste();
		}else{
			Usuario user = new Usuario(this,nombreUsuario, pass, nombre, false, mail);
			usuarios.add(user);
			//podria enviar un mail al usuario
		}
	}
	
	/**
	 * Setea en true el estado online del usuario si ingreso mal
	 * la contraseña o pass salta excepcion
	 * @param nombreDeUsuario
	 * @param pass
	 * @throws ExcepcionPasswordIncorrecto
	 * @throws ExcepcionUsuarioIncorrecto
	 */
	public void logIn(String nombreDeUsuario, String pass) throws ExcepcionPasswordIncorrecto, ExcepcionUsuarioIncorrecto{
		
		Usuario user = buscarUsuario(nombreDeUsuario);
		if(user.getContrasenha().equals(pass)){
			user.setOnline(true);
		}else{
			throw new ExcepcionPasswordIncorrecto();
		}
		
	}
	/**
	 * Setea en false el estado online del usuario
	 * @param unUsuario
	 */
	public void logOut(Usuario unUsuario){
		
		unUsuario.setOnline(false);
		
	}
	
	/**
	 * Busca un usuario en el sistema
	 * @param nombreDeUsuario
	 * @return
	 * @throws ExcepcionUsuarioIncorrecto
	 */
	public Usuario buscarUsuario(String nombreDeUsuario) throws ExcepcionUsuarioIncorrecto {
	
		for(Usuario each: getUsuarios()){
			if(each.getNombreUsuario().equals(nombreDeUsuario)){
				return each;
			}
		}
		throw new ExcepcionUsuarioIncorrecto();
	}
	
	public void agregarSuscripto(Usuario user){
		
		this.addObserver(user);
		
	}


	public void quitarHotel(Hotel h){
		
		//PRECONDICION: EL HOTEL ESTA EN LA LISTA DE HOTELES.
		getHoteles().remove(h);
	}

	public void actualizarOfertaDelHotel(Hotel h){
		quitarHotel(h);
		agregarHotel(h);
	}
	
	public void agregarHotel(Hotel h){
		
		getHoteles().add(h);
		setChanged();
		notifyObservers();
		
	}
	
	/**
	 * Busca los hoteles de una determinada ciudad en un periodo de fecha
	 * con una cantidad de huespedes y los retorna
	 * @param ciudad
	 * @param desde
	 * @param hasta
	 * @param huespedes
	 * @return
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha
	 * @throws ExcepcionHotelNoEncontrado
	 */
	public List<Hotel> buscarHoteles(String ciudad, Calendar desde, Calendar hasta, int huespedes) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		List<Hotel> hoteles = hotelesDe(ciudad);
		List<Hotel> retornarHoteles = new ArrayList<Hotel>();
		for(Hotel each: hoteles){
			if(each.tieneHabitacionesCon(desde, hasta, huespedes)){
				retornarHoteles.add(each);
				buscarHabitacion(each.getNombre(), desde, hasta, huespedes);
			}
		}
		return retornarHoteles;
	}
	
	/**
	 * Busca las habitaciones de un determinado hotel en un rango de fechas
	 * con la cantidad de huspedes, imprime las caracteristicas de las habitaciones
	 * y las retorna
	 * @param nombreHotel
	 * @param desde
	 * @param hasta
	 * @param huespedes
	 * @return
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha
	 * @throws ExcepcionHotelNoEncontrado 
	 */
	public List<Habitacion> buscarHabitacion(String nombreHotel, Calendar desde, Calendar hasta, int huespedes)throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		Hotel hotel = buscarHotel(nombreHotel);
		System.out.println("Habitaciones de " + nombreHotel);
		for(Habitacion each: hotel.getHabitaciones()){
			if(each.estaDisponible(desde, hasta) && each.getCapacidadMaxima() == huespedes){
				habitaciones.add(each);
				System.out.println("-Numero: " + each.getNumero());
				System.out.println("  -Precio: " + each.precioTotal(desde, hasta));
				System.out.println("  -CamaTwin: " + each.tieneCamaTwin());
				System.out.println("  -Descuento: " + each.obtenerDescuento());					
			}
			each.imprimirServicios();
		}
		return habitaciones;
	}

	/**
	 * Busca un hotel por su nombre y lo devuelve
	 * @param nombreHotel
	 * @return
	 */
	public Hotel buscarHotel(String nombreHotel){
		
		for(Hotel each: getHoteles()){
			if(each.getNombre().equals(nombreHotel)){
				return each;
			}
		}
		return null;
	}
	
	/**
	 * Devuelve lod hoteles de la ciudad pasada por el parametro
	 * @param ciudad
	 * @return
	 */
	public List<Hotel> hotelesDe(String ciudad) {
		
		List<Hotel> hoteles = new ArrayList<Hotel>();
		
		for(Hotel each: getHoteles()){
			if(each.getCiudad().equals(ciudad)){
				hoteles.add(each);
			}
		}
		
		return hoteles;
	}

	public void agregarSubasta(Subasta sub){
		getSubastas().add(sub);
	}

	/**
	 * Concreta una reserva poniendo en contacto a las partes
	 * @param unUsuario
	 * @param unaFormaDePago
	 * @param unHotel
	 * @param unaHabitacion
	 * @param desde
	 * @param hasta
	 * @throws ExcepcionHotelNoEncontrado
	 * @throws ExcepcionNoSeEncontroHabitacion
	 */
	public void realizarReserva(Usuario unUsuario,FormaDePago unaFormaDePago, Hotel unHotel,
			Habitacion unaHabitacion, Calendar desde, Calendar hasta) throws ExcepcionHotelNoEncontrado, ExcepcionNoSeEncontroHabitacion{
		
		Hotel hotel = buscarHotel(unHotel.getNombre());
		List<Habitacion> habitaciones = hotel.getHabitaciones();
		
		if(habitaciones.contains(unaHabitacion)){
			if(unaHabitacion.estaDisponible(desde, hasta)){
				
				Periodo periodo = new Periodo(desde, hasta);
				Reserva reserva = new Reserva(unUsuario, unHotel, unaHabitacion, periodo);
				unUsuario.agregarReserva(reserva);
				unHotel.agregarReserva(reserva);
				unaHabitacion.agregarDiaReservado(periodo);
				//enviar mail al usuario
			}			
		}else{
			throw new ExcepcionNoSeEncontroHabitacion();
		}
		
	}
	
	/**
	 * @param args
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha 
	 * @throws ExcepcionHotelNoEncontrado 
	 */
	public static void main(String[] args) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha, ExcepcionHotelNoEncontrado{
		// TODO Auto-generated method stub
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(2013,03,01,0,0,0);
		fecha1.clear(Calendar.MILLISECOND);
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(2013,03,07,0,0,0);
		fecha2.clear(Calendar.MILLISECOND);
		
		SistemaDeBusqueda sist = new SistemaDeBusqueda();
		
		Hotel hot = new Hotel();
		Hotel hot1 = new Hotel();
		hot1.setNombre("Luna");
		hot.setNombre("Dallas");
		
		Habitacion h1 = new Habitacion();
		Habitacion h2 = new Habitacion();
		Servicio s1 = new Servicio("wifi", 78, "lala");
		Servicio s2 = new Servicio("TV cable", 100, "dsfclala");
		Servicio s3 = new Servicio("wifi", 90, "laldfda");
		
		Descuento desc = new DescuentoPorFecha(fecha1, 25);
		List<Descuento> d = new ArrayList<Descuento>();
		d.add(desc);
		
		List<PeriodoConPrecio> listP1 = new ArrayList<PeriodoConPrecio>();
		List<PeriodoConPrecio> listP2 = new ArrayList<PeriodoConPrecio>();
		PeriodoConPrecio per1 = new PeriodoConPrecio(fecha1,fecha2, 122);
		PeriodoConPrecio per2 = new PeriodoConPrecio(fecha1,fecha2, 142);
		listP1.add(per1);
		listP1.add(per2);
		listP2.add(per1);
		
		
		List<Servicio> list = new ArrayList<Servicio>();
		list.add(s1);
		list.add(s2);
		h1.setDescuentos(d);
		h1.setServicios(list);
		h1.setNumero(1);
		h1.setPreciosPorFecha(listP1);
		h1.setCapacidadMaxima(2);
		List<Servicio> list1 = new ArrayList<Servicio>();
		list1.add(s3);
		h2.setServicios(list1);
		h2.setNumero(2);
		h2.setPreciosPorFecha(listP2);
		h2.setCapacidadMaxima(2);
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		habitaciones.add(h1);
		habitaciones.add(h2);
		hot.setHabitaciones(habitaciones);
		hot1.setHabitaciones(habitaciones);
		hot.setCiudad("Quilmes");
		hot1.setCiudad("Quilmes");
		sist.hoteles.add(hot);
		sist.hoteles.add(hot1);
		sist.buscarHoteles("Quilmes", fecha1,fecha2, 2);
	}
}
