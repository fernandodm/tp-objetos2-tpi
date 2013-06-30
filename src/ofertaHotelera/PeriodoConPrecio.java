package ofertaHotelera;

import java.util.Calendar;

public class PeriodoConPrecio extends Periodo {

	private float precio;
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public PeriodoConPrecio(Calendar desde, Calendar hasta, int precio) {
		super(desde, hasta);
		this.precio = precio;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
