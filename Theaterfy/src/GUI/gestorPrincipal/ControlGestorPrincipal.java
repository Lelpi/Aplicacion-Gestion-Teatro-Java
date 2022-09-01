package GUI.gestorPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Evento;

/**
 * Controlador del panel GestorPrincipal
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlGestorPrincipal implements ActionListener{
	private GestorPrincipal vista;
	private IntentoInterfaz interfaz;
	
	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param v vista del panel
	 * @param i interfaz
	 */
	public ControlGestorPrincipal(GestorPrincipal v, IntentoInterfaz i) {
		this.vista = v;
		this.interfaz = i;
	}
	
	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado=e.getActionCommand();
		if(botonPulsado.equals("Deshabilitar butacas")) {
			interfaz.actualizarDeshabilitarButacas();
			interfaz.mostrarPanel(IntentoInterfaz.DESHABILITARBUTACAS);
			
		}
		else if(botonPulsado.equals("Buscar")) {
			String evento = vista.getPanelBusqueda().getEvento();
			int f = vista.getPanelBusqueda().getFiltro();
			ArrayList<Evento> eventos = Theaterfy.getTheaterfy().buscarEventos(evento, f - 1);

			DefaultTableModel modeloDatos = interfaz.getPanelBusqueda().getModeloDatos();
			modeloDatos.setRowCount(eventos.size());
			for (int i = 0; i < eventos.size(); i++) {
				modeloDatos.setValueAt(eventos.get(i).getNombre(), i, 0);
				modeloDatos.setValueAt(eventos.get(i).getDirector(), i, 1);
				modeloDatos.setValueAt(eventos.get(i).getAutor(), i, 2);
				modeloDatos.setValueAt(eventos.get(i).getDescripcion(), i, 3);
			}
			interfaz.mostrarPanel(IntentoInterfaz.BUSQUEDA);
		}
		else if(botonPulsado.equals("Consultar estadisticas")) {
			interfaz.actualizarEstadisticas();
			interfaz.mostrarPanel(IntentoInterfaz.ESTADISTICAS);
		}
		else if(botonPulsado.equals("Añadir evento")) {
			interfaz.actualizarAnyadirEvento();
			interfaz.mostrarPanel(IntentoInterfaz.ANYADIREVENTO);
		}
		else if(botonPulsado.equals("Configurar zonas del teatro")) {
			if(Theaterfy.getTheaterfy().algunEventoActivo()) {
				JOptionPane.showMessageDialog(vista, "Solo se pueden modificar zonas si no hay eventos activos.", "Información", JOptionPane.WARNING_MESSAGE);
				return;
			}
			interfaz.mostrarPanel(IntentoInterfaz.CONFIGURAZONAS);
		}
		else if(botonPulsado.equals("Guardar cambios")) {
			
			if(!vista.getMaxCompradas().equals("")) {
				try {
					Theaterfy.getTheaterfy().setMaxCompraEntradas(Integer.parseInt(vista.getMaxCompradas()));
				}catch(java.lang.NumberFormatException excepcion) {
					JOptionPane.showMessageDialog(vista, "Solo se permiten dígitos en el número de entradas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			if(!vista.getMaxReservadas().equals("")) {
				try {
					Theaterfy.getTheaterfy().setMaxReservaEntradas(Integer.parseInt(vista.getMaxReservadas()));
				}catch(java.lang.NumberFormatException excepcion) {
					JOptionPane.showMessageDialog(vista, "Solo se permiten dígitos en el número de entradas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			if(!vista.getHorasConfirmar().equals("")) {
				try {
					Theaterfy.getTheaterfy().setHorasConfirmarReserva(Integer.parseInt(vista.getHorasConfirmar()));
				}catch(java.lang.NumberFormatException excepcion) {
					JOptionPane.showMessageDialog(vista, "Solo se permiten dígitos en el número de horas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			if(!vista.getHorasReservar().equals("")) {
				try {
					Theaterfy.getTheaterfy().setHorasSePermiteReserva(Integer.parseInt(vista.getHorasReservar()));
				}catch(java.lang.NumberFormatException excepcion) {
					JOptionPane.showMessageDialog(vista, "Solo se permiten dígitos en el número de horas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			
		}
		else if(botonPulsado.equals("Guardar abonos")) {
			Map<Ciclo, Float> mapa = null;
			try {
				mapa = vista.getPreciosAbonos();
			}catch(NumberFormatException excepcion) {
				JOptionPane.showMessageDialog(vista, "El formato debe ser un numero entre 0 y 100.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for(Ciclo c: mapa.keySet()) {
				Theaterfy.getTheaterfy().anyadirPrecioAbonoCiclo(c, mapa.get(c));
				System.out.println(mapa.get(c));
			}
			
		}
		else if(botonPulsado.equals("Añadir ciclos")) {
			interfaz.actualizarAnyadirCiclos();
			interfaz.mostrarPanel(IntentoInterfaz.ANYADIRCICLO);
		}
		else if(botonPulsado.equals("Cerrar sesión")) {
			Theaterfy.getTheaterfy().cerrarSesion();
			interfaz.mostrarPanel(IntentoInterfaz.BIENVENIDA);
		}
	}

}
