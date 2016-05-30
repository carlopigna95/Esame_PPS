package view;

import java.awt.Color;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreLogin;
import dao.UtenteRegistratoDAO;

public class GuiLogin extends JFrame {
	  
	public JTextField username = new JTextField();
	public JPasswordField password = new JPasswordField();


	private static final long serialVersionUID = 1L;
	Ascoltatore listener = new Ascoltatore(this);
	 private static GuiLogin instance = null;
	
	public GuiLogin() {
	       super("Login");
	  
			Container f = getContentPane();
			f.setLayout(new GridLayout(3,2));
            f.setBackground(Color.LIGHT_GRAY);
            
           
            JLabel user = new JLabel("                     Username");
			f.add(user);
			
			f.add(username);
			
            JLabel pass = new JLabel("                     Password");
            f.add(pass);

			f.add(password);
			
		   
		    		
			JButton accediOspite = new JButton("Accedi come Ospite");
			accediOspite.setBackground(Color.LIGHT_GRAY);
			accediOspite.setBorder(new LineBorder(Color.LIGHT_GRAY));
			f.add(accediOspite);
			accediOspite.addActionListener(listener);
			accediOspite.setActionCommand("accediOspite");

        	JButton accedi = new JButton("Accedi");
			accedi.setBackground(Color.LIGHT_GRAY);
			accedi.setBorder(new LineBorder(Color.LIGHT_GRAY));
			accedi.addActionListener(new AscoltatoreLogin(this));
			f.add(accedi); 
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(700,150);
			setVisible(true);
	 		
	 }
	
	 public static synchronized GuiLogin getInstance() {
	        if (instance == null) {
	            instance = new GuiLogin();
	        }
	        return instance;
	 }
}
  
  