package GUI.busqueda;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.*;

/**
 * Controlador del panel Busqueda
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlBusqueda implements ActionListener{

	private Busqueda vista;
	private IntentoInterfaz intento;
	
	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param busqueda vista del panel
	 * @param intento interfaz
	 */
	public ControlBusqueda(Busqueda busqueda, IntentoInterfaz intento) {
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
		DefaultTableModel modeloDatos= vista.getModeloDatos();
		
		if(botonPulsado.equals("Buscar")) {
			int i;
			String evento = vista.getEvento();
			int f = vista.getFiltro();
			ArrayList<Evento> eventos = Theaterfy.getTheaterfy().buscarEventos(evento, f-1);			
			modeloDatos.setRowCount(eventos.size());
			for(i=0;i<eventos.size();i++) {
				modeloDatos.setValueAt(eventos.get(i).getNombre(), i, 0);
				modeloDatos.setValueAt(eventos.get(i).getDirector(), i, 1);
				modeloDatos.setValueAt(eventos.get(i).getAutor(), i, 2);
				modeloDatos.setValueAt(eventos.get(i).getDescripcion(), i, 3);
			}
		}else if(botonPulsado.equals("Acceder")) {
			if(Theaterfy.getTheaterfy().getGestor()!=null) {
				String nombreEvento;
				try {
					nombreEvento =(String) modeloDatos.getValueAt(vista.getFila(), 0);
				}
				catch(ArrayIndexOutOfBoundsException exception){
					return;
				}
				for(Suceso s:Theaterfy.getTheaterfy().getSucesos()) {
					if(s.getNombre().equals(nombreEvento)) {
						intento.actualizarBusquedaGestor((Evento)s);
					}
				}
				
				intento.mostrarPanel(IntentoInterfaz.BUSQUEDAGESTOR);
			}else if (Theaterfy.getTheaterfy().getUsuarioActual()!=null){
				String nombreEvento;
				try {
					nombreEvento =(String) modeloDatos.getValueAt(vista.getFila(), 0);
				}
				catch(ArrayIndexOutOfBoundsException exception){
					return;
				}
				for(Suceso s:Theaterfy.getTheaterfy().getSucesos()) {
					if(s.getNombre().equals(nombreEvento)) {
						intento.actualizarBusquedaUnEvento((Evento)s);
					}
				}
				intento.mostrarPanel(IntentoInterfaz.BUSQUEDAUNEVENTO);
			}else {
				String nombreEvento;
				try {
					nombreEvento =(String) modeloDatos.getValueAt(vista.getFila(), 0);
				}
				catch(ArrayIndexOutOfBoundsException exception){
					return;
				}
				for(Suceso s:Theaterfy.getTheaterfy().getSucesos()) {
					if(s.getNombre().equals(nombreEvento)) {
						intento.actualizarBusquedaSinSesion((Evento)s);
					}
				}
				intento.mostrarPanel(IntentoInterfaz.BUSQUEDASINSESION);
			}
			
		}else if(botonPulsado.equals("Inicio")) {
			if(Theaterfy.getTheaterfy().getGestor()!=null) {
				intento.mostrarPanel(IntentoInterfaz.GESTOR);
			}else if(Theaterfy.getTheaterfy().getUsuarioActual()!=null) {
				intento.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);
			}else {
				intento.mostrarPanel(IntentoInterfaz.BIENVENIDA);
			}
		}
	
	}
	
}
