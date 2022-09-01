package GUI.panelBienvenida;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import GUI.busqueda.PanelParaBusqueda;

/**
 * JPanel, para la búsqueda de eventos
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BienvenidaBuscar extends JPanel{
	
	private PanelParaBusqueda busqueda;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public BienvenidaBuscar() {
		SpringLayout l=new SpringLayout();
		this.setLayout(l);
		this.setPreferredSize(new Dimension(666,600));
		
		
		busqueda = new PanelParaBusqueda();
		
		JLabel imagenTeatro = new JLabel();
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/teatro.jpg").getImage().getScaledInstance(534, 374, Image.SCALE_DEFAULT)); 
		imagenTeatro.setIcon(imageIcon);
				
		
		l.putConstraint(SpringLayout.NORTH, imagenTeatro, 146, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.WEST, imagenTeatro, 49, SpringLayout.WEST, this);
		
		
		this.add(imagenTeatro);
		this.add(busqueda);		
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.busqueda.setControlador(c);
	}
	
	public String getEvento() {
		return this.busqueda.getEvento();
	}
	
	public int getFiltro() {
		return this.busqueda.getFiltro();
	}
}
