package theaterfy.sucesos;

import java.util.ArrayList;
import theaterfy.zona.Butaca;

/**
 * Esta clase sirve para determinar el aforo de los ciclos y eventos
 * 
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * 
 */
public class RestriccionAforo  implements java.io.Serializable{

	private static final long serialVersionUID = -7342545864610559004L;
	
	private float porcentaje;
	private ArrayList <Butaca> deshabilitadas=new ArrayList<>();
	
	/**
	 * Constructor, asigna un porcentaje de aforo permitido para un suceso
	 * 
	 * @param porcentaje del aforo
	 * @param deshabilitadas array de butacas dañadas
	 */
	public RestriccionAforo(float porcentaje, ArrayList<Butaca> deshabilitadas) {
		this.porcentaje = porcentaje;
		this.deshabilitadas = deshabilitadas;
	}

	/**
	 * Metodo para obtener el porcentaje de aforo permitido 
	 * 
	 * @return the porcentaje
	 */
	public float getPorcentaje() {
		return this.porcentaje;
	}

	/**
	 * @param b butaca que añadir a deshabilitadas
	 */
	public void anyadirDeshabilitada(Butaca b){
		deshabilitadas.add(b);
	}
	
}
