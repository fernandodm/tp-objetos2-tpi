package ofertaHotelera;

public class PorPrecioPorEstadia extends Preferencia {

	private int precioMinimo;
	private int precioMaximo;
	
	public int getPrecioMinimo() {
		return precioMinimo;
	}
	public void setPrecioMinimo(int precioMinimo) {
		this.precioMinimo = precioMinimo;
	}
	public int getPrecioMaximo() {
		return precioMaximo;
	}
	public void setPrecioMaximo(int precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
	
	@Override
	public boolean lePuedeInteresarHabitacion(Habitacion h) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
