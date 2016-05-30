package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreMagazziniere;

public class GuiMostraOrdini extends JFrame{
     
	
	AscoltatoreMagazziniere listener = new AscoltatoreMagazziniere(this);
	public GuiMostraOrdini(){
	super("Ordini Pendenti");
	Container c = getContentPane();
	c.setLayout(new BorderLayout());
	JButton evadi = new JButton("Evadi Ordine");
	JButton ritorno = new JButton("Home");
	ritorno.addActionListener(listener);
	ritorno.setActionCommand("ritorno");
	c.add(evadi,BorderLayout.EAST);
	c.add(ritorno,BorderLayout.WEST);
	Object rowData[][] = { 
    		{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3","Row2-Column1", "Row2-Column2"}
    		};
    Object columnNames[] = { "Nome", "Cognome", "Codice Dipendente","Stato","Produttore" ,"Progetto"};
    JTable mostraordine = new JTable(rowData, columnNames);
    JScrollPane scrollPane = new JScrollPane(mostraordine);
    mostraordine.setRowHeight(30);
    c.add(scrollPane,BorderLayout.NORTH);
    
  
    setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(500,500);
	
	}
}
