package theaterfy.compras;

import java.util.GregorianCalendar;

import theaterfy.zona.Zona;

/**
 * Esta clase almacena las variables propias de un abono, además de una fecha de inicio
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class AbonoAnual extends Abono {
	private static final long serialVersionUID = -1510629134093156973L;
	
	private GregorianCalendar inicio;

	/**
	 * Constructor, llama al constructor de Abono con las variables correspondientes, 
	 * además de asignar el parámetro inicio a su variable
	 * @param precio precio del abono
	 * @param zona zona del abono
	 * @param inicio fecha en la que se ha comprado el abono
	 */
	public AbonoAnual(double precio, Zona zona, GregorianCalendar inicio) {
		super(precio, zona);
		this.inicio = inicio;
	}

	/**
	 * @return the inicio
	 */
	public GregorianCalendar getInicio() {
		return inicio;
	}
	
}
