package theaterfy.usuarios;

/**
 * Esta clase almacena una contraseña asociada al gestor
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Gestor  implements java.io.Serializable{
	
	private static final long serialVersionUID = 8071549597603120449L;
	private String contraseña;
	
	/**
	 * Constructor, asigna los parámetros a las variables correspondientes
	 * @param contraseña contraseña del gestor
	 */
	public Gestor(String contraseña) {
		this.contraseña=contraseña;
	}
}
