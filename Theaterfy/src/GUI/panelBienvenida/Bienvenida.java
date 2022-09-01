package GUI.panelBienvenida;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * JPanel que se muestra al principio
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Bienvenida extends JPanel{
	private BienvenidaCuentas bienvenidaCuentas;
	private BienvenidaBuscar bienvenidaBuscar;
	public Bienvenida() {
		SpringLayout l=new SpringLayout();
		this.setLayout(l);
		this.setPreferredSize(new Dimension(1000,600));
		bienvenidaCuentas=new BienvenidaCuentas(333,600);
		bienvenidaBuscar=new BienvenidaBuscar();
		l.putConstraint(SpringLayout.NORTH,bienvenidaCuentas,0,SpringLayout.NORTH,this);
		l.putConstraint(SpringLayout.NORTH,bienvenidaBuscar,0,SpringLayout.NORTH,this);
		l.putConstraint(SpringLayout.WEST,bienvenidaCuentas,0,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,bienvenidaBuscar,0,SpringLayout.EAST,bienvenidaCuentas);
		this.add(bienvenidaCuentas);
		this.add(bienvenidaBuscar);
		
	}

	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.bienvenidaBuscar.setControlador(c);
		this.bienvenidaCuentas.setControlador(c);
	}
	
	public String getEvento() {
		return this.bienvenidaBuscar.getEvento();
	}
	
	public int getFiltro() {
		return this.bienvenidaBuscar.getFiltro();
	}
	
	public String getNombreUsuario() {
		return this.bienvenidaCuentas.getNombreUsuario();
	}
	
	public String getContraUsuario() {
		return this.bienvenidaCuentas.getContraUsuario();
	}
	
	public String getContraGestor() {
		return this.bienvenidaCuentas.getContraGestor();
	}
}
