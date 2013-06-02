package ofertaHotelera;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SistemaDeBusqueda {
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

	public List<Hotel> buscarHoteles(String unaCiudad, Calendar desde, Calendar hasta, int huespedes){
		return getHoteles();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
