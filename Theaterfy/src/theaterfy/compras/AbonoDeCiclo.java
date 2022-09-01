package theaterfy.compras;

import theaterfy.sucesos.Ciclo;
import theaterfy.zona.Zona;

/**
 * Esta clase almacena las variables propias de un abono, además de 
 * un ciclo al que está asociado el abono
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class AbonoDeCiclo extends Abono {

	private static final long serialVersionUID = 2646331412365287231L;
	private Ciclo ciclo;

	/**
	 * Constructor, llama al constructor de Abono con las variables correspondientes, 
	 * además de asignar el parámetro ciclo a su variable
	 * @param precio precio del abono
	 * @param zona zona del abono
	 * @param descuento descuento aplicado al precio
	 * @param ciclo ciclo asociado
	 */
	public AbonoDeCiclo(double precio, Zona zona, double descuento, Ciclo ciclo) {
		super(precio*descuento, zona);
		this.ciclo=ciclo;
	}

	/**
	 * @return the ciclo
	 */
	public Ciclo getCiclo() {
		return ciclo;
	}
	
}
