package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreCapoProgetto;
import model.Sessione;
import model.UtenteRegistrato;

public class GuiCapoProgetto extends JFrame {
	
	Color BlueFacebook = new Color(59,89,152);
	Color MediumBlueFacebook = new Color(109, 132, 180);
	UtenteRegistrato utente = Sessione.getInstance().session.get("utente_corrente");
	private String nome = utente.getNome();
	private String cognome = utente.getCognome();
	Ascoltatore listener = new Ascoltatore(this);
	AscoltatoreCapoProgetto listener2 = new AscoltatoreCapoProgetto(this);


	private static final long serialVersionUID = 1L;
	
	public GuiCapoProgetto(){
		super("Capo Progetto");
		Container c = getContentPane();
		JLabel label = new JLabel("Visualizza spese per: ");
		JLabel accesso = new JLabel(nome+" "+cognome);
		JPanel utente = new JPanel();
		JPanel principale = new JPanel();
		JPanel nord = new JPanel();
		JPanel centro = new JPanel();
		JPanel sud = new JPanel();
		JButton esci = new JButton("Esci");
		JButton per_progetto = new JButton("Per progetto");
		JButton per_dipendente = new JButton("Per dipendente");
		JButton logout = new JButton("Log-Out");
		
		c.add(utente, BorderLayout.NORTH);
		c.add(principale);
		principale.setLayout(new BorderLayout());
		principale.add(nord,BorderLayout.NORTH);
		principale.add(centro,BorderLayout.CENTER);
		principale.add(sud,BorderLayout.SOUTH);
		utente.add(accesso);
		nord.add(label);
		centro.setLayout(new FlowLayout());
		centro.add(per_progetto);
		centro.add(per_dipendente);
		sud.setLayout(new GridLayout(1, 2,5,5));
		sud.add(esci);
		sud.add(logout);
		esci.addActionListener(listener);
		esci.setActionCommand("esci");
		logout.addActionListener(listener);
		logout.setActionCommand("logout");
		per_dipendente.addActionListener(listener2);
		per_dipendente.setActionCommand("per_dipendente");
		per_progetto.addActionListener(listener2);
		per_progetto.setActionCommand("per_progetto");
		
		//Colori e grafica
		
		centro.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		c.setBackground(BlueFacebook);
		nord.setOpaque(true);
		nord.setBackground(MediumBlueFacebook);
		centro.setOpaque(true);
		centro.setBackground(MediumBlueFacebook);
		sud.setOpaque(true);
		sud.setBackground(BlueFacebook);
		sud.setBorder(new LineBorder(Color.BLACK,1));
		utente.setBackground(BlueFacebook);
		utente.setBorder(new LineBorder(Color.BLACK,1));
		accesso.setForeground(Color.WHITE);
		label.setForeground(Color.WHITE);


		
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		setSize(450,200);
		setVisible(true);
		
		
		
		
	}

}
