package GUI.panelEstadisticas;

import java.awt.event.*;
import java.util.LinkedHashMap;

import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Suceso;
import theaterfy.sucesos.Evento;

/**
 * Controlador del panel Estadisticas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Juli�n oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlEstadisticas implements ActionListener{
	private Estadisticas vista;
	private IntentoInterfaz interfaz;
	
	/**
	 * Constructor, asigna las variables a los par�metros correspondientes
	 * @param v vista del panel
	 * @param i interfaz
	 */
	public ControlEstadisticas(Estadisticas v, IntentoInterfaz i) {
		vista=v;
		interfaz=i;
	}
	
	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado= e.getActionCommand();
		String cabecera[]=new String[2];
		DefaultTableModel modeloDatos= vista.getModeloDatos();
		LinkedHashMap<String, Float> datos=null;
		String sufijo="", aux="";
		int i=0;
		
		this.vista.setTablaVisible(false);
		
		if(botonPulsado.equals("ocupaci�n por representaci�n")) {
			vista.setEventosVisible(true);
		} else if(botonPulsado.equals("ocupaci�n por zona")) {
			cabecera[0]="Nombre de la zona";
			cabecera[1]="Porcentaje de ocupaci�n";
			datos=Theaterfy.getTheaterfy().generarEstadisticasOcupacionZona();
			vista.setEventosVisible(false);
			sufijo=" %";
			
		} else if(botonPulsado.equals("remuneraci�n en cada evento")) {
			cabecera[0]="Nombre del evento";
			cabecera[1]="Dinero recaudado";
			datos=Theaterfy.getTheaterfy().generarEstadisticasRemuneracionEvento();
			vista.setEventosVisible(false);
			sufijo=" �";
			
		} else if(botonPulsado.equals("remuneraci�n por zona")) {
			
			cabecera[0]="Nombre de la zona";
			cabecera[1]="Dinero recaudado";
			datos=Theaterfy.getTheaterfy().generarEstadisticasRemuneracionZona();
			vista.setEventosVisible(false);
			sufijo=" �";
			
		} else if (botonPulsado.equals("comboBoxChanged")) {
			if(vista.getEventoSelected().equals("--eventos--")) {
				return;
			}
			
			cabecera[0]="Hora de la representaci�n";
			cabecera[1]="Porcentaje de ocupaci�n";
			for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
				if(s.getClass()!=Ciclo.class && s.getNombre().equals(vista.getEventoSelected())) {
					datos=Theaterfy.getTheaterfy().generarEstadisticasOcupacionRepresentacion((Evento)s);
				}
			}
			sufijo=" %";

		} else if(botonPulsado.equals("Volver al inicio")) {
			interfaz.mostrarPanel(IntentoInterfaz.GESTOR);
			return;
		}
		
		if(datos!=null) {
			modeloDatos.setColumnIdentifiers(cabecera);
			modeloDatos.setRowCount(datos.size());
			for(String clave : datos.keySet()) {
				if(botonPulsado.equals("comboBoxChanged") || botonPulsado.equals("ocupaci�n por zona")) {
					aux=(datos.get(clave)*100)+sufijo;
				} else {
					aux=datos.get(clave)+sufijo;
				}
				modeloDatos.setValueAt(clave, i,  0);
				modeloDatos.setValueAt(aux, i,  1);
				i++;
			}
			
			this.vista.setTablaVisible(true);
		}
	}
	
	
}
