package descuentos;

public abstract class Descuento {
	
	protected int porcentajeDescontado; 
	
	public int getPorcentajeDescontado() {
		return porcentajeDescontado;
	}
	public void setPorcentajeDescontado(int porcentajeDescontado) {
		this.porcentajeDescontado = porcentajeDescontado;
	}
	public abstract String descuento();
	
}
