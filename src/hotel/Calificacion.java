package hotel;

import sistemaYUsuario.Usuario;

public class Calificacion {
	
	private Usuario user;
	private int puntaje;
	private String comentario;
	
	public Calificacion(Usuario usuario, int punt, String comment){
		
		setUser(usuario);
		setPuntaje(punt);
		setComentario(comment);
	}
	
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	

}
