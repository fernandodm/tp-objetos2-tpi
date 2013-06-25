package ofertaHotelera;

public class TarjetaDeCredito extends FormaDePago {
	
	private String compañiaEmisora;
	private int nroTarjeta;
	private int cuotas;
	
	public String getCompañiaEmisora() {
		return compañiaEmisora;
	}
	public void setCompañiaEmisora(String compañiaEmisora) {
		this.compañiaEmisora = compañiaEmisora;
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
	
	public TarjetaDeCredito(String compañia, int nro, int cuotas, int monto){
		this.setMonto(monto);
		this.setCompañiaEmisora(compañia);
		this.setNroTarjeta(nro);
		this.setCuotas(cuotas);
	}
	
	void documentarPago(){
		
	}
	
}
