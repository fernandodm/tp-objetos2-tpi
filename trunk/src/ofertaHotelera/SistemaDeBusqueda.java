package ofertaHotelera;

//
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

//TODO import testOfertaHotelera.SistemaDeBusquedaTest;

import excepciones.ExcepcionElNombreDeUsuarioYaExiste;
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

	public boolean existeNombreDeUsuario(String nombre){
		
		for(Usuario each: getUsuarios()){
			if(each.getNombreUsuario().equals(nombre)){
				return true;
			}
		}
		return false;
	}

	public void registrarUsuario(String nombreUsuario, String pass, String nombre, String mail) throws ExcepcionElNombreDeUsuarioYaExiste{
		
		if(existeNombreDeUsuario(nombreUsuario)){
			throw new ExcepcionElNombreDeUsuarioYaExiste();
		}else{
			Usuario user = new Usuario(this,nombreUsuario, pass, nombre, false, mail);
			usuarios.add(user);
			//podria enviar un mail al usuario
		}
	}
	
	public void logIn(String nombreDeUsuario, String pass) throws ExcepcionPasswordIncorrecto, ExcepcionUsuarioIncorrecto{
		
		Usuario user = buscarUsuario(nombreDeUsuario);
		if(user.getContrasenha().equals(pass)){
			user.setOnline(true);
		}else{
			throw new ExcepcionPasswordIncorrecto();
		}
		
	}
	
	public void logOut(Usuario unUsuario){
		
		unUsuario.setOnline(false);
		
	}
	
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
	
	public List<Habitacion> buscarHabitacion(String nombreHotel, Calendar desde, Calendar hasta, int huespedes)throws ExcepcionNoHayPrecioEstablecidoParaTalFecha{
		
		List<Habitacion> habitaciones = new ArrayList<Habitacion>();
		Hotel hotel = buscarHotel(nombreHotel);
		System.out.println("Habitaciones de " + nombreHotel);
		for(Habitacion each: hotel.getHabitaciones()){
			if(each.estaDisponible(desde, hasta) && each.getCapacidadMaxima() == huespedes){
				habitaciones.add(each);
				System.out.println("-Numero: " + each.getNumero());
				System.out.println("  -Precio: " + each.precioTotal(desde, hasta));
				System.out.println("  -Descuento: " + each.obtenerDescuento());					
			}
			each.imprimirServicios();
		}
		return habitaciones;
	}

	
	public Hotel buscarHotel(String nombreHotel){
		
		Hotel hotel = new Hotel();
		for(Hotel each: getHoteles()){
			if(each.getNombre().equals(nombreHotel)){
				hotel = each;
			}
		}
		return hotel;
	}

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

	//public void realizarReserva
	
	/**
	 * @param args
	 * @throws ExcepcionHotelNoEncontrado 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
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
		List<Servicio> list = new ArrayList<Servicio>();
		list.add(s1);
		list.add(s2);
		h1.setServicios(list);
		h1.setNumero(1);
	//	h1.setPrecioPorNoche(133);
		h1.setCapacidadMaxima(2);
		List<Servicio> list1 = new ArrayList<Servicio>();
		list1.add(s3);
		h2.setServicios(list1);
		h2.setNumero(2);
	//	h2.setPrecioPorNoche(100);
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
	//	sist.buscarHoteles("Quilmes", Calendar.getInstance(), Calendar.getInstance(), 2);
	}
}
