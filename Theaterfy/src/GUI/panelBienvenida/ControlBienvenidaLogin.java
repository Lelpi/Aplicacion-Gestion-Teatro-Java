package GUI.panelBienvenida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;

/**
 * Controlador del panel BienvenidaLogin
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlBienvenidaLogin implements ActionListener {

	private BienvenidaLogin vista;
	private IntentoInterfaz intento;

	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param bienvenida vista del panel
	 * @param intento interfaz
	 */
	public ControlBienvenidaLogin(BienvenidaLogin bienvenida, IntentoInterfaz intento) {
		this.vista = bienvenida;
		this.intento = intento;
	}

	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String botonPulsado=e.getActionCommand();
		if(botonPulsado.equals("Buscar")) {
			String evento = vista.getEvento();
			int f = vista.getFiltro();
			ArrayList<Evento> eventos = Theaterfy.getTheaterfy().buscarEventos(evento, f-1);

			DefaultTableModel modeloDatos =intento.getPanelBusqueda().getModeloDatos();
			modeloDatos.setRowCount(eventos.size());
			for(int i=0;i<eventos.size();i++) {
				modeloDatos.setValueAt(eventos.get(i).getNombre(), i, 0);
				modeloDatos.setValueAt(eventos.get(i).getDirector(), i, 1);
				modeloDatos.setValueAt(eventos.get(i).getAutor(), i, 2);
				modeloDatos.setValueAt(eventos.get(i).getDescripcion(), i, 3);
			}
			intento.mostrarPanel(IntentoInterfaz.BUSQUEDA);
		} else if(botonPulsado.equals("Confirmar reservas")) {
			intento.actualizarConfirmarReserva();
			intento.mostrarPanel(IntentoInterfaz.CONFIRMARRESERVA);
		} else if(botonPulsado.equals("Comprar abonos")) {
			intento.actualizarComprarAbono();
			intento.mostrarPanel(IntentoInterfaz.COMPRARABONO);
		} else if(botonPulsado.equals("Cerrar sesión")) {
			Theaterfy.getTheaterfy().cerrarSesion();
			intento.mostrarPanel(IntentoInterfaz.BIENVENIDA);
		}
	}
}
