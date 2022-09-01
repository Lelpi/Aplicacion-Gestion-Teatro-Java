package GUI.panelConfirmarReserva;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * JPanel del pago de confirmar reserva
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ConfirmarReservaPago extends JPanel{
	
	private JTextField tarjeta;
	private JLabel nombreEvento;
	private JLabel introduceNumEntradas;
	private JTextField numEntradas;
	private JButton pagar;
	private int maxEntradas;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ConfirmarReservaPago() {
		SpringLayout l=new SpringLayout();
		this.setLayout(l);
		this.setPreferredSize(new Dimension(600,500));
		
		nombreEvento=new JLabel("nombre evento");
		nombreEvento.setFont(new Font("Dialog",Font.BOLD,14));
		introduceNumEntradas=new JLabel("Introduzca la cantidad a entradas a confirmar (máx 5)");
		numEntradas=new JTextField(2);
		JLabel introduceTarjeta=new JLabel("Introduzca su número de tarjeta");
		tarjeta=new JTextField(12);
		
		pagar=new JButton("Pagar");
		
		l.putConstraint(SpringLayout.NORTH, nombreEvento, 0, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.NORTH, introduceNumEntradas, 0, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.NORTH, numEntradas, 0, SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.NORTH, introduceTarjeta, 20, SpringLayout.SOUTH, nombreEvento);
		l.putConstraint(SpringLayout.NORTH, tarjeta, 20, SpringLayout.SOUTH, nombreEvento);
		l.putConstraint(SpringLayout.NORTH, pagar, 20, SpringLayout.SOUTH, tarjeta);
		
		l.putConstraint(SpringLayout.EAST, numEntradas, 0, SpringLayout.EAST, this);
		l.putConstraint(SpringLayout.EAST, introduceNumEntradas, -20, SpringLayout.WEST, numEntradas);
		l.putConstraint(SpringLayout.EAST, nombreEvento, -20, SpringLayout.WEST, introduceNumEntradas);
		l.putConstraint(SpringLayout.WEST, introduceTarjeta, 0, SpringLayout.WEST, introduceNumEntradas);
		l.putConstraint(SpringLayout.WEST, tarjeta, 20, SpringLayout.EAST, introduceTarjeta);
		l.putConstraint(SpringLayout.WEST, pagar, 0, SpringLayout.EAST, introduceTarjeta);
		
		this.add(nombreEvento);
		this.add(introduceNumEntradas);
		this.add(numEntradas);
		this.add(introduceTarjeta);
		this.add(tarjeta);
		this.add(pagar);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.pagar.addActionListener(c);
	}
	
	public void setMaxEntradas(int maxEntradas) {
		this.introduceNumEntradas.setText("Introduzca la cantidad a entradas a confirmar (máx "+maxEntradas+")");
		this.maxEntradas=maxEntradas;
	}
	
	public int getMaxEntradas() {
		return this.maxEntradas;
	}
	
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento.setText(nombreEvento);
	}
	
	public String getNumEntradas() {
		return this.numEntradas.getText();
	}
	
	public String getTarjeta() {
		return this.tarjeta.getText();
	}
	
	
}
