package ofertaHotelera;

public class TarjetaDeCredito extends FormaDePago {
	
	private String compaņiaEmisora;
	private int nroTarjeta;
	private int cuotas;
	
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
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	
	public TarjetaDeCredito(String compaņia, int nro, int cuotas, int monto){
		this.setMonto(monto);
		this.setCompaņiaEmisora(compaņia);
		this.setNroTarjeta(nro);
		this.setCuotas(cuotas);
	}
	
	void documentarPago(){
		
	}
	
}
