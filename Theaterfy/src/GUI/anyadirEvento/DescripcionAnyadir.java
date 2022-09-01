package GUI.anyadirEvento;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 * panel para seleccionar el tipo de evento y añadir una descripción
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class DescripcionAnyadir extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel descripcion;
	private JTextArea descr;
	private JRadioButton danzaButton;
	private JRadioButton musicaButton;
	private JRadioButton teatroButton;
	private ButtonGroup grupo;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public DescripcionAnyadir() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 250));
		
		descripcion = new JLabel("Añadir descripción");
		descr = new JTextArea(6, 40);
		danzaButton = new JRadioButton("Danza");
		musicaButton = new JRadioButton("Musica");
		teatroButton = new JRadioButton("Teatro");
		grupo = new ButtonGroup();
		
		layout.putConstraint(SpringLayout.WEST, descripcion, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, descripcion, 0, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, descr, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, descr, 3, SpringLayout.SOUTH, descripcion);
		
		layout.putConstraint(SpringLayout.WEST, danzaButton, 0, SpringLayout.WEST, descr);
		layout.putConstraint(SpringLayout.NORTH, danzaButton, 7, SpringLayout.SOUTH, descr);
		layout.putConstraint(SpringLayout.WEST, musicaButton, 105, SpringLayout.EAST, danzaButton);
		layout.putConstraint(SpringLayout.NORTH, musicaButton, 0, SpringLayout.NORTH, danzaButton);
		layout.putConstraint(SpringLayout.EAST, teatroButton, 0, SpringLayout.EAST, descr);
		layout.putConstraint(SpringLayout.NORTH, teatroButton, 0, SpringLayout.NORTH, danzaButton);
		
		grupo.add(danzaButton);
		grupo.add(musicaButton);
		grupo.add(teatroButton);
		
		this.add(descripcion);
		this.add(descr);
		this.add(musicaButton);
		this.add(danzaButton);
		this.add(teatroButton);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.danzaButton.addActionListener(c);
		this.musicaButton.addActionListener(c);
		this.teatroButton.addActionListener(c);
	}
	
	public String getDescripcion() {
		return this.descr.getText();
	}
	
	public boolean musicaIsSelected() {
		return this.musicaButton.isSelected();
	}
	
	public boolean teatroIsSelected() {
		return this.teatroButton.isSelected();
	}
	
	public boolean danzaIsSelected() {
		return this.danzaButton.isSelected();
	}
}
