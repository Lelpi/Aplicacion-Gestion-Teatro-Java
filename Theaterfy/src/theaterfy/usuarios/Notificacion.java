package theaterfy.usuarios;

/**
 * Esta clase almacena un mensaje
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Juli�n oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Notificacion  implements java.io.Serializable{

	private static final long serialVersionUID = 462527996359546650L;
	private String mensaje;
	
	/**
	 * Constructor, asocia los par�metros a las variables correspondientes
	 * @param mensaje mensaje de la notificaci�n
	 */
	public Notificacion(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * M�todo toString de Notificacion
	 */
	@Override
	public String toString() {
		return mensaje;
	}
	
	
}
