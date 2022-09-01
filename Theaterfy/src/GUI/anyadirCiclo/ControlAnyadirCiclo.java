package GUI.anyadirCiclo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Suceso;

/**
 * Controlador del panel AnyadirCiclo
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlAnyadirCiclo implements ActionListener{

	private AnyadirCiclo vista;
	private IntentoInterfaz intento;
	
	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param vista vista del panel
	 * @param intento interfaz
	 */
	public ControlAnyadirCiclo(AnyadirCiclo vista, IntentoInterfaz intento) {
		this.vista=vista;
		this.intento=intento;
	}
	
	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado=e.getActionCommand();
		if(botonPulsado.equals("Agregar")) {
			String nombreCiclo = vista.getNombreCiclo();
			if(nombreCiclo.equals("")) {
				JOptionPane.showMessageDialog(vista, "Introduzca el nombre del ciclo.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			ArrayList<Suceso> sucesos = vista.getSubciclos();
			if(sucesos.size()==0) {
				JOptionPane.showMessageDialog(vista, "Elija al menos un sub suceso", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for(Suceso s: Theaterfy.getTheaterfy().getSucesos()) {
				if(s.getNombre().equals(nombreCiclo)) {
					JOptionPane.showMessageDialog(vista, "El nombre no debe repetirse", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			Theaterfy.getTheaterfy().anyadirSuceso(new Ciclo(nombreCiclo, sucesos));
			
			Theaterfy.getTheaterfy().getSucesosExternos().removeAll(sucesos);
			intento.mostrarPanel(IntentoInterfaz.GESTOR);
		}
		else if(botonPulsado.equals("Inicio")) {
			intento.mostrarPanel(IntentoInterfaz.GESTOR);
		}
	}

}
