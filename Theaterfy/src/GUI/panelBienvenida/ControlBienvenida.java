package GUI.panelBienvenida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;

/**
 * Controlador del panel Bienvenida
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Juli�n oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlBienvenida implements ActionListener {

	private Bienvenida vista;
	private IntentoInterfaz intento;

	/**
	 * Constructor, asigna las variables a los par�metros correspondientes
	 * @param bienvenida vista del panel
	 * @param intento interfaz
	 */
	public ControlBienvenida(Bienvenida bienvenida, IntentoInterfaz intento) {
		this.vista = bienvenida;
		this.intento = intento;
	}

	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String botonPulsado = e.getActionCommand();

		if (botonPulsado.equals("Buscar")) {
			int i;
			String evento = vista.getEvento();
			int f = vista.getFiltro();
			ArrayList<Evento> eventos = Theaterfy.getTheaterfy().buscarEventos(evento, f - 1);

			DefaultTableModel modeloDatos = intento.getPanelBusqueda().getModeloDatos();

			modeloDatos.setRowCount(eventos.size());
			for (i = 0; i < eventos.size(); i++) {
				modeloDatos.setValueAt(eventos.get(i).getNombre(), i, 0);
				modeloDatos.setValueAt(eventos.get(i).getDirector(), i, 1);
				modeloDatos.setValueAt(eventos.get(i).getAutor(), i, 2);
				modeloDatos.setValueAt(eventos.get(i).getDescripcion(), i, 3);
			}
			intento.mostrarPanel(IntentoInterfaz.BUSQUEDA);
		} else if(botonPulsado.equals("Login")) {
			String nick=vista.getNombreUsuario();
			String contr=vista.getContraUsuario();
			if(nick.equals("") || contr.equals("")) {
				JOptionPane.showMessageDialog(null, "Entrada no v�lida");
				return;
			}
			if(!Theaterfy.getTheaterfy().login(nick, contr)) {
				JOptionPane.showMessageDialog(null, "Usuario o contrase�a incorrecta");
				return;
			}
			
			intento.actualizarBienvenidaLogin();
			intento.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);
		} else if(botonPulsado.equals("Registrarse")) {
			intento.mostrarPanel(IntentoInterfaz.REGISTRO);
		} else if(botonPulsado.equals("Login gestor")) {
			if(!Theaterfy.getTheaterfy().login(vista.getContraGestor())) {
				JOptionPane.showMessageDialog(null, "Contrase�a incorrecta");
				return;
			}
			intento.actualizarGestorPrincipal();
			intento.mostrarPanel(IntentoInterfaz.GESTOR);
		}

	}
}
