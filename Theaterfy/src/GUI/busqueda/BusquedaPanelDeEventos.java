package GUI.busqueda;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * JPanel con la tabla de los eventos buscados
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BusquedaPanelDeEventos extends JPanel{
	
	private JButton boton;	
	private JTable tabla;
	private DefaultTableModel modeloDatos;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public BusquedaPanelDeEventos() {
		
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 20);
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(310, 200));
		
		
		boton=new JButton("Acceder");
		
		String[] titulos = {"Nombre", "Director", "Autor", "Descripción"};

		// Crear una matriz con las filas de la tabla
		Object[][] filas = {
		 {
		 }};

		// Crear un DefaultTableModel con las filas y los títulos de la tabla
		modeloDatos = new DefaultTableModel(filas, titulos);
		// Crear la tabla, pasando el modelo como parámetro
		tabla = new JTable(modeloDatos);
		
		JScrollPane scroll = new JScrollPane(tabla);
	    tabla.setPreferredScrollableViewportSize(new Dimension(300, 100));
	    this.add(scroll);
	
		this.add(boton);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.boton.addActionListener(c);
	}
	
	public DefaultTableModel getModeloDatos() {
		return this.modeloDatos;
	}
	
	public int getFila() {
		return this.tabla.getSelectedRow();
	}
	
}
