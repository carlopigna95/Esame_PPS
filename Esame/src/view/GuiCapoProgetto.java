package view;
import javax.swing.*;
import java.util.HashMap;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreCapoProgetto;
import Business.CapoProgettoBusiness;
import model.Sessione;

import java.awt.*;
import java.util.Vector;

public class GuiCapoProgetto extends JFrame {
	
	


	private static final long serialVersionUID = 1L;
	
	public GuiCapoProgetto(){
		super("Capo Progetto");
		Container c = getContentPane();
		
		JLabel label = new JLabel("Visualizza spese per: ");
		JPanel nord = new JPanel();
		JPanel centro = new JPanel();
		JPanel sud = new JPanel();
		JButton esci = new JButton("Esci");
		JButton per_progetto = new JButton("Per progetto");
		JButton per_dipendente = new JButton("Per dipendente");
		
		Ascoltatore listener = new Ascoltatore(this);
		AscoltatoreCapoProgetto listener2 = new AscoltatoreCapoProgetto(this);

		
		

		c.setLayout(new BorderLayout());
		c.add(nord,BorderLayout.NORTH);
		c.add(centro,BorderLayout.CENTER);
		c.add(sud,BorderLayout.SOUTH);
		nord.add(label);
		centro.setLayout(new FlowLayout());
		centro.add(per_progetto);
		centro.add(per_dipendente);
		
		nord.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		centro.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		sud.add(esci);
		esci.addActionListener(listener);
		esci.setActionCommand("esci");
		
		per_dipendente.addActionListener(listener2);
		per_dipendente.setActionCommand("per_dipendente");
		per_progetto.addActionListener(listener2);
		per_progetto.setActionCommand("per_progetto");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		setSize(300,200);
		setVisible(true);
		
		
		
		
	}

}
