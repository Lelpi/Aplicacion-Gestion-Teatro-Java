package GUI.busqueda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Suceso;

/**
 * Controlador del panel BusquedaEventoParticular
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlBusquedaEventoParticular implements ActionListener {

	private BusquedaEventoParticular vista;
	private IntentoInterfaz intento;

	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param busqueda vista del panel
	 * @param intento interfaz
	 */
	public ControlBusquedaEventoParticular(BusquedaEventoParticular busqueda, IntentoInterfaz intento) {
		this.vista = busqueda;
		this.intento=intento;
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
		}else if(botonPulsado.equals("Comprar/Reservar entradas")) {
			for(Suceso s: Theaterfy.getTheaterfy().getSucesos()) {
				if(s.getNombre().equals(vista.getEventoCompra())) {
					intento.actualizarComprarEntrada((Evento)s);
				}
			}
			intento.mostrarPanel(IntentoInterfaz.COMPRARENTRADA);
		}else if(botonPulsado.equals("Comprar abonos")) {
			intento.actualizarComprarAbono();
			intento.mostrarPanel(IntentoInterfaz.COMPRARABONO);
		}
	}

}
