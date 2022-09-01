package GUI.gestorPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.zona.Butaca;
import theaterfy.zona.Deshabilitacion;
import theaterfy.zona.Zona;
import theaterfy.zona.ZonaNumerada;
/**
 * Controlador del panel DeshabilitarButacas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlDeshabilitar implements ActionListener {
	
	private DeshabilitarButacas vista;
	private IntentoInterfaz interfaz;
	
	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param vista vista del panel
	 * @param interfaz interfaz
	 */
	public ControlDeshabilitar(DeshabilitarButacas vista, IntentoInterfaz interfaz) {
		this.vista=vista;
		this.interfaz=interfaz;
	}
	
	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado=e.getActionCommand();
		String fechaIni;
		String fechaFin;
		GregorianCalendar ini = new GregorianCalendar();
		GregorianCalendar fin = new GregorianCalendar();
		Zona zonaAux=null;
		if(botonPulsado.equals("Cancelar")) {
			interfaz.mostrarPanel(IntentoInterfaz.GESTOR);
		}
		try {
			vista.getZona();
		} catch(java.lang.NullPointerException exception) {
			return;
		}
		fechaIni=vista.getFechaIni();
		fechaFin=vista.getFechaFin();
		for(Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
			if(z.getNombre().equals(vista.getZona())) {
				zonaAux=z;
			}
		}
		if(zonaAux==null) {
			vista.getNumerada().setVisible(false);
			return;
		}
		try {

			String[] fecha=fechaIni.split("/");
			String[] aux=fecha[2].split(" ");
			String[] hora=aux[1].split(":");
			ini.set(Integer.parseInt(aux[0]),Integer.parseInt(fecha[1])-1, Integer.parseInt(fecha[0]), Integer.parseInt(hora[0]), Integer.parseInt(hora[1]));
			fecha=fechaFin.split("/");
			aux=fecha[2].split(" ");
			hora=aux[1].split(":");
			fin.set(Integer.parseInt(aux[0]),Integer.parseInt(fecha[1])-1, Integer.parseInt(fecha[0]), Integer.parseInt(hora[0]), Integer.parseInt(hora[1]));

			vista.getNumerada().actualizaTabla((ZonaNumerada)zonaAux, ini, fin);
			vista.setTablaVisible(true);
		}catch(java.lang.ArrayIndexOutOfBoundsException | NumberFormatException excepcion) {
			JOptionPane.showMessageDialog(vista, "Formato de las fechas: dd/mm/aaaa hh:mm", "Error", JOptionPane.ERROR_MESSAGE);
			vista.getNumerada().setVisible(false);
			return;
		}
		
		if(ini.compareTo(fin)>=0) {
			JOptionPane.showMessageDialog(vista, "La fecha de inicio no puede ser posterior a la de fin", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if(botonPulsado.equals("Deshabilitar")) {
			if(vista.getMotivo().equals("")) {
				JOptionPane.showMessageDialog(vista, "Introduzca un motivo", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			int[] indicesButacas=vista.getNumerada().getSeleccionadas();
			DefaultTableModel modeloTabla=vista.getNumerada().modeloTabla();
			Butaca[] butacas=new Butaca[indicesButacas.length];
			int i=0;
			for(Integer indice: indicesButacas) {
				butacas[i]=((ZonaNumerada)zonaAux).getButaca((Integer)modeloTabla.getValueAt(indice, 1), (Integer)modeloTabla.getValueAt(indice, 0));
				butacas[i].addDeshabilitada(new Deshabilitacion(vista.getMotivo(), ini, fin));
				i++;
			}

			interfaz.mostrarPanel(IntentoInterfaz.GESTOR);
		}
	}
}
