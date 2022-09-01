package theaterfy.sucesos;


/**
 * Esta clase abstracta almacena los ciclos y eventos actuales del teatro.
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * 
 */
public abstract class Suceso  implements java.io.Serializable{

	private static final long serialVersionUID = -1551877496903670893L;
	
	private String nombre;

	/**
	 * Constructor de los sucesos
	 * 
	 * @param nombre del suceso
	 */
	public Suceso(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para obtener el nombre de un suceso (unico)
	 * 
	 * @return el nombre del suceso
	 */
	public String getNombre() {
		return this.nombre;
	}
}
