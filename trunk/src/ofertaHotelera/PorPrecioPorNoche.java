package ofertaHotelera;

public class PorPrecioPorNoche extends Preferencia{
	
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
		
		boolean b = false;
		if(lasFechasEstanBien(h) && h.getPrecioPorNoche() > getPrecioMinimo() && h.getPrecioPorNoche() < getPrecioMaximo()){
			b = true;
		}
		
		
		return b;
	}
	
	

}
