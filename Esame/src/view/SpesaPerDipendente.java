package view;

import java.awt.*;

import javax.swing.*;

import ActionListener.AscoltatoreCapoProgetto;;

public class SpesaPerDipendente extends JFrame {
	
	AscoltatoreCapoProgetto listener = new AscoltatoreCapoProgetto(this);
	
	public SpesaPerDipendente(){
		Container c = getContentPane();
		JPanel nord = new JPanel();
		JPanel sud = new JPanel();
		JButton opzioni = new JButton("Torna alle opzioni");
		JButton stampa = new JButton("Stampa");
		
		c.setLayout(new BorderLayout());
		c.add(nord, BorderLayout.NORTH);
		c.add(sud, BorderLayout.SOUTH);
		sud.setLayout(new GridLayout(1,2,5,5));	
		sud.add(stampa);
		stampa.addActionListener(listener);
		stampa.setActionCommand("stampa");
		sud.add(opzioni);
		opzioni.addActionListener(listener);
		opzioni.setActionCommand("ritorna_opzioni");
		
		
		
		
		
		setVisible(true);
		setSize(500, 300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		
		
		
		
		
	}
}


