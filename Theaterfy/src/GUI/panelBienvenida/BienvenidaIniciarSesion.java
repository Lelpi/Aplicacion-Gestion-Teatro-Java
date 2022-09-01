package GUI.panelBienvenida;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * JPanel para iniciar sesión
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class BienvenidaIniciarSesion extends JPanel{
	
	private JButton login;
	private JTextField nick;
	private JPasswordField contrasenia;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 * @param width anchura del panel
	 * @param height altura del panel
	 */
	public BienvenidaIniciarSesion(int width, int height) {
		this.setLayout(new GridLayout(0,1,1,(int)(height/9.14)));

		this.setSize(new Dimension(width, height));
		JLabel inicioSesion=new JLabel("Iniciar sesión");
		nick=new JTextField(10);
		
		contrasenia=new JPasswordField(10);
		login=new JButton("Login");
		
		this.add(inicioSesion);
		this.add(nick);
		this.add(contrasenia);
		this.add(login);
	}
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.login.addActionListener(c);
	}
	
	
	public String getNombreUsuario() {
		return this.nick.getText();
	}
	
	public String getContraUsuario() {
		String string="";
		for(char c : this.contrasenia.getPassword()) {
			string+=c;
		}
		return string;
	}
}
