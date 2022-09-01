package GUI.busqueda;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * JPanel para busqueda de eventos
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Busqueda extends JPanel {

	private BusquedaPanelDeEventos eventos;
	private PanelParaBusqueda busqueda;
	private JButton inicio;
	
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public Busqueda() {
		
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		JLabel tit=new JLabel("Búsqueda");
		layout.putConstraint(SpringLayout.WEST, tit, 50, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tit, 20, SpringLayout.NORTH, this);
		
		busqueda= new PanelParaBusqueda();
		
		layout.putConstraint(SpringLayout.NORTH, busqueda, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, busqueda, 150, SpringLayout.WEST, this);
		
		eventos = new BusquedaPanelDeEventos();
		
		layout.putConstraint(SpringLayout.NORTH, eventos, 250, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, eventos, 325, SpringLayout.WEST, this);
		
		inicio = new JButton("Inicio");
		
		layout.putConstraint(SpringLayout.NORTH, inicio, 30, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, inicio, 825, SpringLayout.WEST, this);
		
		this.add(tit);
		this.add(busqueda);
		this.add(eventos);
		this.add(inicio);
		
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.eventos.setControlador(c);
		this.busqueda.setControlador(c);
		this.inicio.addActionListener(c);
	}
	
	public String getEvento() {
		return this.busqueda.getEvento();
	}
	
	public int getFiltro() {
		return this.busqueda.getFiltro();
	}
	
	public DefaultTableModel getModeloDatos() {
		return this.eventos.getModeloDatos();
	}
	
	public int getFila() {
		return this.eventos.getFila();
	}
}
