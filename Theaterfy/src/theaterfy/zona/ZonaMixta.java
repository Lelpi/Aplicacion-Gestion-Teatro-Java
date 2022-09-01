package theaterfy.zona;

import java.util.ArrayList;

import theaterfy.sucesos.RestriccionAforo;

/**
 * Clase ZonaMixta, gestiona todos los datos necesarios de una zona
 * que puede comprender a su vez distintos tipos de zonas
 * 
 * @author luis.lepore@estudiante.uam.es
 * @author oriol.julian@estudiante.uam.es
 * @author paula.golpe@estudiante.uam.es
 *
 */
public class ZonaMixta extends Zona{
	private static final long serialVersionUID = -4637172483945127916L;
	private ArrayList <Zona> subzonas=new ArrayList<>();
	
	/**
	 * Constructor principal, llama al constructor de zona y
	 * si recibe una zona la agrega a su array de zonas
	 * 
	 * @param nombre es el nombre de la zona
	 * @param zonas es un array con las subzonas que componen la zona mixta
	 */
	public ZonaMixta(String nombre, Zona zonas[]) {
		super(nombre, 0);
		if(zonas!=null) {
			for(Zona z : zonas) {
				addSubzona(z);
			}
		}
	}
	
	/**
	 * Constructor, este no recibe un parámetro de zona, llama al constructor principal
	 * pasándole null para la variable zona
	 * 
	 * @param nombre es el nombre de la zona
	 */
	public ZonaMixta(String nombre) {
		this(nombre, null);
	}
	
	/**
	 * Método que añade nuevas subzonas a la zona
	 * 
	 * @param z es la nueva subzona a añadir
	 */
	public void addSubzona(Zona z) {
		subzonas.add(z);
	}
	
	public int getAforo() {
		int contador=0;
		for(Zona z:this.subzonas) {
			contador+=z.getAforo();
		}
		return contador;
	}
	
	/**
	 * añade deshabilitacion a ciertas butacas, de acuerdo con el porcentaje de ra
	 * @param des deshabilitacion para añadir a ciertas butacas
	 * @param ra restriccion de aforo para la zona
	 */
	public void anyadirRestriccionZonaMixta(Deshabilitacion des, RestriccionAforo ra) {
		for(Zona zona : this.subzonas) {
			if(zona.getClass()==ZonaNumerada.class) {
				((ZonaNumerada)zona).anyadirRestriccionZonaNumerada(des, ra);
			} else if(zona.getClass()==ZonaMixta.class) {
				((ZonaMixta)zona).anyadirRestriccionZonaMixta(des, ra);
			}
		}
	}

	/**
	 * @return the subzonas
	 */
	public ArrayList<Zona> getSubzonas() {
		return subzonas;
	}
}
