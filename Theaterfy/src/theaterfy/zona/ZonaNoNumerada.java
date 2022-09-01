package theaterfy.zona;

/**
 * Clase ZonaNoNumerada, esta clase gestiona todos los datos que se necesitan de una zona no numerada
 * 
 * @author luis.lepore@estudiante.uam.es
 * @author oriol.julian@estudiante.uam.es
 * @author paula.golpe@estudiante.uam.es
 *
 */
public class ZonaNoNumerada extends Zona{
	private static final long serialVersionUID = 6903843695557978736L;
	private int aforo;

	
	/**
	 * Constructor zonaNoNumerada, llama al constructor de zona y asocia
	 * el párametro de aforoPermitido a la variable de la clase
	 * 
	 * @param nombre es nombre de la zona numerada
	 * @param precioAbonoAnual es el precio del abono anual para una zona no numerada
	 * @param aforo es el aforo maximo permitido en la zona
	 */
	public ZonaNoNumerada(String nombre, float precioAbonoAnual, int aforo) {
		super(nombre, precioAbonoAnual);
		this.aforo = aforo;
	}


	/**
	 * 
	 * @return the aforo
	 */
	public int getAforo() {
		return this.aforo;
	}


	/**
	 * @param aforo the aforo to set
	 */
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	
	
}
