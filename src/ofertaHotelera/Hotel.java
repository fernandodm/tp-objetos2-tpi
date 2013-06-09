package ofertaHotelera;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	private String ciudad;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void eliminarReserva(Reserva unaReserva){
	
	 	getReservas().remove(unaReserva);

	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
