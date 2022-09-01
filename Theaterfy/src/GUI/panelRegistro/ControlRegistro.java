package GUI.panelRegistro;

import java.awt.event.*;

import javax.swing.JOptionPane;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;

/**
 * Controlador del panel Registro
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlRegistro implements ActionListener{

	private Registro vista;
	private IntentoInterfaz interfaz;
	
	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param v vista del panel
	 * @param i interfaz
	 */
	public ControlRegistro(Registro v, IntentoInterfaz i) {
		vista=v;
		interfaz=i;
	}
	
	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado=e.getActionCommand();
		String nick=vista.getNick();
		String contr=vista.getContrasenia();
		if(botonPulsado.equals("Volver al inicio")) {
			interfaz.mostrarPanel(IntentoInterfaz.BIENVENIDA);
		}else if(botonPulsado.equals("Crear")){
		if(nick.equals("") || contr.equals("")) {
			JOptionPane.showMessageDialog(null, "Entrada no válida");
			return;
		}
		Boolean flag=Theaterfy.getTheaterfy().registrarse(nick,contr);
		if(!flag) {
			JOptionPane.showMessageDialog(null, "Ya existe alguien con ese nick, vuelva a intentarlo");
			return;
		}
		interfaz.mostrarPanel(IntentoInterfaz.BIENVENIDA);
		}
	}
}
