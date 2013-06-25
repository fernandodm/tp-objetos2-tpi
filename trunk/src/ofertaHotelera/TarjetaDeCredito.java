package ofertaHotelera;

public class TarjetaDeCredito extends FormaDePago {
	
	private String compa�iaEmisora;
	private int nroTarjeta;
	private int cuotas;
	
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
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	
	public TarjetaDeCredito(String compa�ia, int nro, int cuotas, int monto){
		this.setMonto(monto);
		this.setCompa�iaEmisora(compa�ia);
		this.setNroTarjeta(nro);
		this.setCuotas(cuotas);
	}
	
	void documentarPago(){
		
	}
	
}
