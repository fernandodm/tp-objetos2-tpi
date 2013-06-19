package ofertaHotelera;

//
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

import excepciones.ExcepcionElNombreDeUsuarioYaExiste;

import excepciones.ExcepcionPasswordIncorrecto;
import excepciones.ExcepcionUsuarioIncorrecto;

public class SistemaDeBusqueda extends Observable{
	
	// RECORDAR HACER EL LOGOUT FER :P
	
	private List<Hotel> hoteles = new ArrayList<Hotel>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
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
	
	public boolean existeNombreDeUsuario(String nombre){
		
		for(Usuario each: getUsuarios()){
			if(each.getNombreUsuario().equals(nombre)){
				return true;
			}
		}
		return false;
	}

	public void registrarUsuario(String nombreUsuario, String pass, String nombre) throws ExcepcionElNombreDeUsuarioYaExiste{
		
		if(existeNombreDeUsuario(nombreUsuario)){
			throw new ExcepcionElNombreDeUsuarioYaExiste();
		}else{
			Usuario user = new Usuario(this,nombreUsuario, pass, nombre, false);
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
	

	public List<Hotel> buscarHoteles(String ciudad, Calendar desde, Calendar hasta, int huespedes){
		
		List<Hotel> hoteles = hotelesDe(ciudad);
		List<Hotel> retornarHoteles = new ArrayList<Hotel>();
		for(Hotel each: hoteles){
			if(each.tieneHabitacionesCon(desde, hasta, huespedes)){
				retornarHoteles.add(each);
				System.out.println(each.getNombre());
			}
		}
		return retornarHoteles;
	}
	
	private List<Hotel> hotelesDe(String ciudad) {
		
		List<Hotel> hoteles = new ArrayList<Hotel>();
		
		for(Hotel each: getHoteles()){
			if(each.getCiudad().equals(ciudad)){
				hoteles.add(each);
			}
		}
		
		return hoteles;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
