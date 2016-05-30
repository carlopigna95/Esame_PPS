package view;
import javax.swing.*;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreCapoProgetto;
import model.Sessione;

import java.awt.*;

public class GuiCapoProgetto extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public GuiCapoProgetto(){
		super("Capo Progetto");
		Container c = getContentPane();
		

		
		Ascoltatore listener = new Ascoltatore(this);
		AscoltatoreCapoProgetto listener2 = new AscoltatoreCapoProgetto(this);

		
		JLabel labelProgetto = new JLabel("Seleziona il progetto:");
		JLabel labelOrganizza = new JLabel("Organizza spese per:");
		JPanel nord = new JPanel();
		JPanel centro = new JPanel();
		JPanel sud = new JPanel();
		JButton esci = new JButton("Esci");
		JButton stampa = new JButton("Stampa");
		JRadioButton per_progetto = new JRadioButton("Stampa spese per progetto");
		JRadioButton per_dipendente = new JRadioButton("Stampa spese per dipendente");
		ButtonGroup gruppo = new ButtonGroup();

		

		c.setLayout(new BorderLayout());
		sud.setLayout(new GridLayout(1,3,5,5));
		
		String[] listaProgetti = {"Progetto_1","Progetto_2"};
		JComboBox progetto = new JComboBox(listaProgetti);
		c.setLayout(new BorderLayout());
		c.add(nord,BorderLayout.NORTH);
		c.add(centro,BorderLayout.CENTER);
		c.add(sud,BorderLayout.SOUTH);
		nord.add(labelProgetto);
		nord.add(progetto);
		centro.add(per_progetto);
		centro.add(per_dipendente);
		gruppo.add(per_dipendente);
		gruppo.add(per_progetto);
		nord.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		centro.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		sud.add(esci);
		esci.addActionListener(listener);
		esci.setActionCommand("esci");
		sud.add(stampa);
		stampa.addActionListener(listener2);
		stampa.setActionCommand("stampa");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);   
		setSize(300,200);
		setVisible(true);
		
		
	}

}
