package GUI.panelBienvenida;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * JPanel para acceder a Registro
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BienvenidaRegistrarse extends JPanel{
	
	private JButton registro;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 * @param width anchura del panel
	 * @param height altura del panel
	 */
	public BienvenidaRegistrarse(int width, int height) {
		SpringLayout l=new SpringLayout(); 
		this.setLayout(l);
		this.setPreferredSize(new Dimension(width,height));
		
		JLabel noSesion=new JLabel("¿No tienes sesión?");
		JLabel registrate=new JLabel("Regístrate");
		registro=new JButton("Registrarse");
		
		l.putConstraint(SpringLayout.WEST, noSesion, 0, SpringLayout.WEST, this);
		l.putConstraint(SpringLayout.WEST, registrate, 0, SpringLayout.WEST, this);
		l.putConstraint(SpringLayout.WEST, registro, 0, SpringLayout.WEST, this);
		
		l.putConstraint(SpringLayout.NORTH, noSesion, (int)(height/5.5), SpringLayout.NORTH, this);
		l.putConstraint(SpringLayout.NORTH, registrate, (int)(height/11), SpringLayout.NORTH, noSesion);
		l.putConstraint(SpringLayout.NORTH, registro, (int)(height/4.7), SpringLayout.NORTH, registrate);
		
		this.add(noSesion);
		this.add(registrate);
		this.add(registro);
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.registro.addActionListener(c);
	}
}
