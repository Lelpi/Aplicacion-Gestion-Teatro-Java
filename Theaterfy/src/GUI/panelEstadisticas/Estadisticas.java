package GUI.panelEstadisticas;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Suceso;

/**
 * panel que muestra las estadisticas del teatro
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Estadisticas extends JPanel{
	private JComboBox<String> eventos;
	private EstadisticasEleccion estadisticasEleccion;
	private DefaultTableModel modeloDatos;
	private JScrollPane scroll;
	private JButton inicio;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public Estadisticas() {
		SpringLayout l=new SpringLayout();
		this.setLayout(l);
		
		this.setPreferredSize(new Dimension(1000,600));
		
		JLabel titulo=new JLabel("Estadisticas");
		titulo.setFont(new Font("Verdana",Font.BOLD,16));
		
		ArrayList<String> nombreEventos=new ArrayList<String>();
		nombreEventos.add("--eventos--");
		for(Suceso e:Theaterfy.getTheaterfy().getSucesos()) {
			if(e.getClass()!=Ciclo.class) {
				nombreEventos.add(e.getNombre());
			}
		}
		String aux[]=new String[nombreEventos.size()];
		aux=nombreEventos.toArray(aux);
		
		eventos=new JComboBox<String>(aux);
		
		estadisticasEleccion=new EstadisticasEleccion();
		
		inicio=new JButton("Volver al inicio");
		
		String[][] filas = {{}};
		String[] cabecera= new String[2];
		modeloDatos = new DefaultTableModel(filas, cabecera);
		// Crear la tabla, pasando el modelo como parámetro
		JTable tabla = new JTable(modeloDatos);
		
		scroll = new JScrollPane(tabla);
	    tabla.setPreferredScrollableViewportSize(new Dimension(500, 200));
	    this.add(scroll);
		
		l.putConstraint(SpringLayout.NORTH, titulo, 29, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.NORTH, estadisticasEleccion, 100, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.NORTH, eventos, 100, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.NORTH, scroll, 300, SpringLayout.NORTH,this);
		l.putConstraint(SpringLayout.SOUTH, inicio, -50, SpringLayout.SOUTH, this);
		
		l.putConstraint(SpringLayout.WEST,titulo,430,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,estadisticasEleccion,300,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,eventos,300,SpringLayout.WEST,estadisticasEleccion);
		l.putConstraint(SpringLayout.WEST,scroll,300,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST, inicio, 50, SpringLayout.WEST, this);
		
		this.add(titulo);
		this.add(estadisticasEleccion);
		this.add(eventos);
		this.add(inicio);
		eventos.setVisible(false);
		scroll.setVisible(false);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.estadisticasEleccion.setControlador(c);
		this.inicio.addActionListener(c);
		this.eventos.addActionListener(c);
	}
	
	public void setEventosVisible(boolean flag) {
		eventos.setVisible(flag);
	}
	
	public void setTablaVisible(boolean flag) {
		this.scroll.setVisible(flag);
	}
	
	public String getEventoSelected() {
		return (String)eventos.getSelectedItem();
	}
	
	public DefaultTableModel getModeloDatos() {
		return this.modeloDatos;
	}
	
	/**
	 * actualiza la información de los componentes de Estadisticas
	 */
	public void actualizar() {
		eventos.removeAllItems();
		for(Suceso e:Theaterfy.getTheaterfy().getSucesos()) {
			if(e.getClass()!=Ciclo.class) {
				eventos.addItem(e.getNombre());
				
			}
		}
		eventos.setVisible(false);
	}
}
