package GUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import theaterfy.Theaterfy;

/**
 * WindowListener de la interfaz del proyecto
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Guardado implements WindowListener{

	/**
	 * Carga los datos de la aplicacion cuando se abre
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		Theaterfy.getTheaterfy().cargar();
		System.out.println("Cargando información");
	}

	/**
	 * Guarda los datos de la aplicacion cuando se cierra
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		Theaterfy.getTheaterfy().guardar();
		System.out.println("Guardando información");
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}
	
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}
