package ofertaHotelera;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Hotel {
	private String nombre;
	private String pais;
	private String direccion;
	private int telefono;
	private int categoria;
	private String checkIn;
	private String checkOut;
	private String ciudad;
	private List<FormaDePago> tarjetasAceptadas = new ArrayList<FormaDePago>();
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
	
	public List<Reserva> reservasActuales(){
		
		List<Reserva> actuales = new ArrayList<Reserva>();
		
		Calendar diaActual = Calendar.getInstance();
		diaActual.set(diaActual.get(diaActual.YEAR),diaActual.get(diaActual.MONTH),
				diaActual.get(diaActual.DATE),0,0,0);
					
		for(Reserva each: getReservas()){
			///quitar el equals y crear una clase que compare x igualdad
			if((diaActual.equals(each.getFechaDeIngreso()) || diaActual.after(each.getFechaDeIngreso()))
					&& diaActual.before(each.getFechaDeSalida())){
				
				actuales.add(each);
			}
			
		}
		return actuales;		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
