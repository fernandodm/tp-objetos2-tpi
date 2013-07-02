package ofertaHotelera;

public class PorPrecioPorNoche extends Preferencia{
	
	private float precioMinimo;
	private float precioMaximo;
	
	public float getPrecioMinimo() {
		return precioMinimo;
	}
	public void setPrecioMinimo(float precioMinimo) {
		this.precioMinimo = precioMinimo;
	}
	public float getPrecioMaximo() {
		return precioMaximo;
	}
	public void setPrecioMaximo(float precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
	
	public PorPrecioPorNoche(float minimo, float maximo){
		setPrecioMinimo(minimo);
		setPrecioMaximo(maximo);
	}
	
	/**
	 * Retorna true si los datos de la preferencia coinciden con los requeridos de la habitación.
	 * @param h
	 * @return boolean
	 * @throws ExcepcionNoHayPrecioEstablecidoParaTalFecha.
	 */
	
	@Override
	public boolean lePuedeInteresarHabitacion(Habitacion h) {

		return (h.precioPorNochePromedio() >= getPrecioMinimo() && h.precioPorNochePromedio() <= getPrecioMaximo());
	}
	
	

}
