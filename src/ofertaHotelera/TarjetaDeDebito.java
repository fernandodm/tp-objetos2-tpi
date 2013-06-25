package ofertaHotelera;

public class TarjetaDeDebito extends FormaDePago {
	
	private String compaņiaEmisora;
	private int nroTarjeta;

	public String getCompaņiaEmisora() {
		return compaņiaEmisora;
	}
	public void setCompaņiaEmisora(String compaņiaEmisora) {
		this.compaņiaEmisora = compaņiaEmisora;
	}
	public int getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(int nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	
	public TarjetaDeDebito(String compaņia, int nro, int monto){
		this.setMonto(monto);
		this.setCompaņiaEmisora(compaņia);
		this.setNroTarjeta(nro);	
	}

	void documentarPago(){
		
	}
}
