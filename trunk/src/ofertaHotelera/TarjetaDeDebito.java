package ofertaHotelera;

public class TarjetaDeDebito extends FormaDePago {
	
	private String compañiaEmisora;
	private int nroTarjeta;

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
	
	public TarjetaDeDebito(String compañia, int nro, int monto){
		this.setMonto(monto);
		this.setCompañiaEmisora(compañia);
		this.setNroTarjeta(nro);	
	}

	void documentarPago(){
		
	}
}
