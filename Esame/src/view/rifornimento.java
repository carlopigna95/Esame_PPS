package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreMagazziniere;

public class rifornimento extends JFrame{
	AscoltatoreMagazziniere listener = new AscoltatoreMagazziniere(this);
	public rifornimento(){
		super("Rifornimento");
		Container c = getContentPane();
		JButton rifornisci = new JButton("Riforisci");
		JButton ritorno = new JButton("Home");
		ritorno.addActionListener(listener);
		ritorno.setActionCommand("ritorno");
		c.setLayout(new BorderLayout());
		Object rowData[][] = { 
	      		{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3","Row2-Column1", "Row2-Column2", "Row2-Column3","Row2-Column3"},
 	    		{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3","Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3"},	  	    		
 	    		{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3","Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3"},
	  	    	{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3","Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3"},
	  	   		{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3","Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3"},
	  	   		{ "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3","Row2-Column1", "Row2-Column2", "Row2-Column3", "Row2-Column3"},
	  	};
	  	
	  	Object columnNames[] = { "Nome", "Prezzo", "Disponibilità","Max Ordinazioni","Produttore" ,"Rifornitore","Seleziona"};
	  	JTable table = new JTable(rowData,columnNames);
	  	JScrollPane scroll = new JScrollPane(table);
	  	table.setRowHeight(30);
	  	c.add(scroll,BorderLayout.NORTH);
	  	c.add(ritorno,BorderLayout.WEST);
	  	c.add(rifornisci,BorderLayout.EAST);
		
	  	
	  	setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(520,500);
		setVisible(true);
	}

}
