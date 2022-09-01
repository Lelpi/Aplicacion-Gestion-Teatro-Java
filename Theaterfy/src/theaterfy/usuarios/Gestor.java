package theaterfy.usuarios;

/**
 * Esta clase almacena una contrase�a asociada al gestor
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Juli�n oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Gestor  implements java.io.Serializable{
	
	private static final long serialVersionUID = 8071549597603120449L;
	private String contrase�a;
	
	/**
	 * Constructor, asigna los par�metros a las variables correspondientes
	 * @param contrase�a contrase�a del gestor
	 */
	public Gestor(String contrase�a) {
		this.contrase�a=contrase�a;
	}
}
