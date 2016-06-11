package view;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	Color BlueFacebook = new Color(59,89,152);		
	
	public GuiLogin() {
	       super("Login");
	  
			Container f = getContentPane();
			JPanel nord = new JPanel();
			JPanel sud = new JPanel();
			JButton accedi = new JButton("Accedi");
			JButton accediOspite = new JButton("Accedi come Ospite");
            JLabel user = new JLabel("                     Username");
            JLabel pass = new JLabel("                     Password");
			
            f.setLayout(new BorderLayout());
            f.add(nord, BorderLayout.NORTH);
    		nord.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));
            nord.setLayout(new GridLayout(2,2,5,5));
			nord.add(user);
			nord.add(username);
            nord.add(pass);
			nord.add(password);			
			f.add(sud, BorderLayout.SOUTH);
			sud.setLayout(new GridLayout(1,2));
			sud.add(accediOspite);
			accediOspite.addActionListener(listener);
			accediOspite.setActionCommand("accediOspite");

        	
        	accedi.setForeground(Color.white);
			accedi.setBackground(BlueFacebook);
			
			accedi.addActionListener(new AscoltatoreLogin(this));
			sud.add(accedi); 
			
			//Colori e grafica
			f.setBackground(BlueFacebook);
			user.setForeground(Color.white);
			nord.setBackground(BlueFacebook);
			sud.setBackground(BlueFacebook);
            pass.setForeground(Color.white);
            accediOspite.setBackground(BlueFacebook);
			accediOspite.setForeground(Color.white);
			accediOspite.setBorder(new LineBorder(BlueFacebook));
			accedi.setBorder(new LineBorder(BlueFacebook));
			username.setBorder(new LineBorder(BlueFacebook));
			password.setBorder(new LineBorder(BlueFacebook));
			sud.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));

			
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			setSize(500,150);
			setVisible(true);
	 		
	 }
	
	 public static synchronized GuiLogin getInstance() {
	        if (instance == null) {
	            instance = new GuiLogin();
	        }
	        return instance;
	 }
}
  
  