package ofertaHotelera;

public abstract class FormaDePago {
	
	private float monto;
	
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}

	abstract void documentarPago();

}
