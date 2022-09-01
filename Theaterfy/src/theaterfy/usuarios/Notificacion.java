package theaterfy.usuarios;

/**
 * Esta clase almacena un mensaje
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Notificacion  implements java.io.Serializable{

	private static final long serialVersionUID = 462527996359546650L;
	private String mensaje;
	
	/**
	 * Constructor, asocia los parámetros a las variables correspondientes
	 * @param mensaje mensaje de la notificación
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
	 * Método toString de Notificacion
	 */
	@Override
	public String toString() {
		return mensaje;
	}
	
	
}
