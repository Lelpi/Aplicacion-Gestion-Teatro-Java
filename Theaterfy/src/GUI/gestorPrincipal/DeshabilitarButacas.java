package GUI.gestorPrincipal;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import theaterfy.Theaterfy;
import theaterfy.zona.Zona;
import theaterfy.zona.ZonaNumerada;

/**
 * panel para deshabilitar butacas, este solo se utiliza para zonas numeradas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class DeshabilitarButacas extends JPanel{
	private JComboBox<String> comboZonas;
	private JTextField ini;
	private JTextField fin;
	private JTextArea motivo;
	private JButton deshabilitar;
	private JButton cancelar;
	private DeshabilitarNumerada numerada;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public DeshabilitarButacas() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		JLabel encabezado=new JLabel("Deshabilitar butacas");
		encabezado.setFont(new Font("Verdana", Font.BOLD, 24));
		JLabel zona=new JLabel("Selecciona la zona");
		String zonas[] = {"Tribuna", "Anfiteatro", "Principal", "Platea", "Patio de butacas"};
		comboZonas = new JComboBox<> (zonas);
		JLabel fechaIni=new JLabel("Introduce la fecha de inicio");
		ini=new JTextField(8);
		JLabel fechaFin=new JLabel("Introduce la fecha fin");
		fin=new JTextField(8);
		JLabel motivoDeshab=new JLabel("Introduce el motivo de la deshabilitacion:");
		motivo=new JTextArea(10,30);
		deshabilitar = new JButton("Deshabilitar");
		cancelar = new JButton("Cancelar");
		numerada = new DeshabilitarNumerada();
		
		layout.putConstraint(SpringLayout.WEST, encabezado, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, encabezado, 15, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, zona, 0, SpringLayout.WEST, encabezado);
		layout.putConstraint(SpringLayout.NORTH, zona, 20, SpringLayout.SOUTH, fechaFin);
		layout.putConstraint(SpringLayout.WEST, comboZonas, 10, SpringLayout.EAST, zona);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, comboZonas, 0, SpringLayout.VERTICAL_CENTER, zona);
		layout.putConstraint(SpringLayout.WEST, fechaIni, 0, SpringLayout.WEST, zona);
		layout.putConstraint(SpringLayout.NORTH, fechaIni, 20, SpringLayout.SOUTH, encabezado);
		layout.putConstraint(SpringLayout.WEST, ini, 15, SpringLayout.EAST, fechaIni);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, ini, 0, SpringLayout.VERTICAL_CENTER, fechaIni);
		layout.putConstraint(SpringLayout.WEST, fechaFin, 0, SpringLayout.WEST, fechaIni);
		layout.putConstraint(SpringLayout.NORTH, fechaFin, 20, SpringLayout.SOUTH, fechaIni);
		layout.putConstraint(SpringLayout.EAST, fin, 0, SpringLayout.EAST, ini);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, fin, 0, SpringLayout.VERTICAL_CENTER, fechaFin);
		layout.putConstraint(SpringLayout.WEST, motivoDeshab, 0, SpringLayout.WEST, fechaFin);
		layout.putConstraint(SpringLayout.NORTH, motivoDeshab, 20, SpringLayout.SOUTH, zona);
		layout.putConstraint(SpringLayout.WEST, motivo, 0, SpringLayout.WEST, motivoDeshab);
		layout.putConstraint(SpringLayout.NORTH, motivo, 10, SpringLayout.SOUTH, motivoDeshab);
		layout.putConstraint(SpringLayout.EAST, deshabilitar, 0, SpringLayout.EAST, motivo);
		layout.putConstraint(SpringLayout.NORTH, deshabilitar, 30, SpringLayout.SOUTH, motivo);
		layout.putConstraint(SpringLayout.EAST, cancelar, 0, SpringLayout.EAST, deshabilitar);
		layout.putConstraint(SpringLayout.NORTH, cancelar, 10, SpringLayout.SOUTH, deshabilitar);
		layout.putConstraint(SpringLayout.EAST, numerada, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, numerada, 0, SpringLayout.NORTH, this);
		
		this.add(encabezado);
		this.add(zona);
		this.add(comboZonas);
		this.add(fechaIni);
		this.add(ini);
		this.add(fechaFin);
		this.add(fin);
		this.add(motivoDeshab);
		this.add(motivo);
		this.add(deshabilitar);
		this.add(cancelar);
		this.add(numerada);
		this.numerada.setVisible(false);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.cancelar.addActionListener(c);
		this.deshabilitar.addActionListener(c);
		this.comboZonas.addActionListener(c);
	}
	
	public String getZona() {
		return this.comboZonas.getSelectedItem().toString();
	}
	
	/**
	 * actualiza la información de los componentes de DeshabilitarButacas
	 */
	public void actualizar() {
		ArrayList<ZonaNumerada> zonas=new ArrayList<>();
		this.setTablaVisible(false);
		
		for(Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
			if(z.getClass()==ZonaNumerada.class) {
				zonas.add((ZonaNumerada)z);
			}
		}
		
		this.comboZonas.removeAllItems();
		this.comboZonas.addItem("--zonas--");
		for(Zona z : zonas) {
			this.comboZonas.addItem(z.getNombre());
		}
	}
	
	public String getFechaIni() {
		return ini.getText();
	}
	
	public String getFechaFin() {
		return fin.getText();
	}
	
	public String getMotivo() {
		return motivo.getText();
	}
	
	public void setTablaVisible(boolean flag) {
		this.numerada.setVisible(flag);
	}
	
	public DeshabilitarNumerada getNumerada() {
		return this.numerada;
	}
}
