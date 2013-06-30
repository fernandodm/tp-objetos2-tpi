package ofertaHotelera;

import java.util.Calendar;

import excepciones.ExcepcionNoHayPrecioEstablecidoParaTalFecha;

public class PorPrecioPorEstadia extends Preferencia {

	private Calendar fechaInicialDeInteres;
	private Calendar fechaFinalDeInteres;
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
	
	public Calendar getFechaInicialDeInteres() {
		return fechaInicialDeInteres;
	}
	public void setFechaInicialDeInteres(Calendar fechaInicialDeInteres) {
		this.fechaInicialDeInteres = fechaInicialDeInteres;
	}
	public Calendar getFechaFinalDeInteres() {
		return fechaFinalDeInteres;
	}
	public void setFechaFinalDeInteres(Calendar fechaFinalDeInteres) {
		this.fechaFinalDeInteres = fechaFinalDeInteres;
	}
	@Override
	public boolean lePuedeInteresarHabitacion(Habitacion h) throws ExcepcionNoHayPrecioEstablecidoParaTalFecha {

		return (h.precioTotal(getFechaInicialDeInteres(), getFechaFinalDeInteres()) >= getPrecioMinimo() && h.precioTotal(getFechaInicialDeInteres(), getFechaFinalDeInteres()) <= getPrecioMaximo());
	}
}
