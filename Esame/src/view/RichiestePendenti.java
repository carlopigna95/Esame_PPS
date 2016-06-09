package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.JScrollPane;

import ActionListener.Ascoltatore;
import ActionListener.AscoltatoreMagazziniere;
import ActionListener.JTableListener;
import Business.MagazziniereBusiness;

	
public class RichiestePendenti extends JFrame {
	
	MagazziniereBusiness magazz_business = MagazziniereBusiness.getInstance();
	AscoltatoreMagazziniere listenerMagazz = new AscoltatoreMagazziniere(this);
	
	public RichiestePendenti(int codOrdine){
		super("Dettaglio Ordine");
		Container c = getContentPane();
		JButton evadi = new JButton("Evadi Ordine");
		JButton esci = new JButton("Torna agli ordini");
		JPanel nord = new JPanel();
		JPanel sud = new JPanel();
		c.setLayout(new BorderLayout());
		c.add(nord, BorderLayout.NORTH);
		c.add(sud, BorderLayout.SOUTH);
		sud.setLayout(new GridLayout(1,2,5,5));
		sud.add(evadi);
		evadi.addActionListener(listenerMagazz);
		evadi.setActionCommand("evadi");
		sud.add(esci);
		esci.addActionListener(listenerMagazz);
		esci.setActionCommand("chiudi");
		nord.add(TableRichiestePendenti(codOrdine));
		
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(650,550);
		setVisible(true);
		
	}
	
	//--------------TABLE DETTAGLIO ORDINI----------------
			public JScrollPane TableRichiestePendenti(int codOrdine){
				MyTableModel mtm = new MyTableModel();
				final JTable table = new JTable(){
					public Dimension getPreferredScrollableViewportSize(){
				  		return new Dimension(600,400);
				  	}
				};
				table.setRowSelectionAllowed(true);
				String columnNames[] = new String[] { "Nome Prodotto", "Categoria","Produttore","Numero ordinazioni", "Prezzo"};
				mtm.setColumnIdentifiers(columnNames);
				table.setModel(mtm);
				JTableListener lis = new JTableListener(table);
				for(int i=0;i<magazz_business.DettaglioRichiestePendenti(codOrdine).size();i++){
					mtm.addRow(magazz_business.DettaglioRichiestePendenti(codOrdine).get(i));
					//prende il numero degli ordinati e il prezzo e li moltiplica restituendo il prezzo giusto
					int numeroOrdinati = Integer.parseInt(magazz_business.DettaglioRichiestePendenti(codOrdine).get(i)[3]);
					float prezzo_singolo = Float.parseFloat((magazz_business.DettaglioRichiestePendenti(codOrdine).get(i)[4]));
					float prezzoTotaleProdotto = numeroOrdinati*prezzo_singolo;
					mtm.setValueAt(prezzoTotaleProdotto, i, 4);
					}
				

				JScrollPane scrollPane = new JScrollPane(table);
			  	
			  	return scrollPane;
			}
			
			
		

}
