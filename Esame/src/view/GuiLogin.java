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
			f.setLayout(new BorderLayout());
            f.setBackground(BlueFacebook);
            JLabel user = new JLabel("                     Username");
            user.setForeground(Color.white);
            f.add(nord, BorderLayout.NORTH);
    		nord.setBorder(BorderFactory.createEmptyBorder(20,10,10,10));
            nord.setLayout(new GridLayout(2,2,5,5));
			nord.add(user);
			nord.setBackground(BlueFacebook);
			nord.add(username);
            JLabel pass = new JLabel("                     Password");
            pass.setForeground(Color.white);
            nord.add(pass);
			nord.add(password);			
		   
		   
			JButton accediOspite = new JButton("Accedi come Ospite");
			accediOspite.setBackground(BlueFacebook);
			accediOspite.setForeground(Color.white);
			accediOspite.setBorder(new LineBorder(BlueFacebook));
			f.add(sud, BorderLayout.SOUTH);
			sud.setLayout(new GridLayout(1,2));
			sud.add(accediOspite);
			accediOspite.addActionListener(listener);
			accediOspite.setActionCommand("accediOspite");

        	JButton accedi = new JButton("Accedi");
        	accedi.setForeground(Color.white);
			accedi.setBackground(BlueFacebook);
			accedi.setBorder(new LineBorder(BlueFacebook));
			username.setBorder(new LineBorder(BlueFacebook));
			password.setBorder(new LineBorder(BlueFacebook));
			accedi.addActionListener(new AscoltatoreLogin(this));
			sud.add(accedi); 
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
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
  
  