package ofertaHotelera;

public class TarjetaDeDebito extends FormaDePago {
	
	private String compa�iaEmisora;
	private int nroTarjeta;

	public String getCompa�iaEmisora() {
		return compa�iaEmisora;
	}
	public void setCompa�iaEmisora(String compa�iaEmisora) {
		this.compa�iaEmisora = compa�iaEmisora;
	}
	public int getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(int nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	
	public TarjetaDeDebito(String compa�ia, int nro, int monto){
		this.setMonto(monto);
		this.setCompa�iaEmisora(compa�ia);
		this.setNroTarjeta(nro);	
	}

	void documentarPago(){
		
	}
}
