package ActionListener;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Business.MagazziniereBusiness;
import Business.ProdottoBusiness;
import dao.MagazziniereDAO;
import view.MyTableModel;

public class MyTableListener implements TableModelListener{
	
	boolean isUpdatingTable=false;
	
	JFrame finestra = new JFrame();
	
	public MyTableListener(JFrame finestra){
		this.finestra = finestra;
	}

	

	@Override
	public void tableChanged(TableModelEvent e) {
		if(isUpdatingTable) {
			isUpdatingTable=false;
			return;
		}
		MagazziniereBusiness magazz_business = MagazziniereBusiness.getInstance();
		ProdottoBusiness prodotto = ProdottoBusiness.getInstance();
		
		Vector<String[]> vettore1 = magazz_business.PocaDisponibilita1();
		Vector<String[]> vettore2 = magazz_business.PocaDisponibilita2();
		String[] quantita_combobox = {"1","2","3","4","5"};
		
		int row = e.getFirstRow();
		MyTableModel model = (MyTableModel)e.getSource();
		Object data = model.getValueAt(row, 6);
		//Controllo sul valore dei checkbox
		if (data.equals(true)){
			//viene effettuato un controllo sul magazziniere per stabilire quale prodotto stampare nel pannello di input
			if (magazz_business.ControlloMagazziniere() == true){
				try{
					String scelta = (String) JOptionPane.showInputDialog(null, "Prodotto selezionato: "+vettore1.get(row)[0]+
														".\n Inserire la quantità"
														+ " da rifornire", "Rifornimento",JOptionPane.QUESTION_MESSAGE,
														null, quantita_combobox, quantita_combobox[0]);
					
					
			
				//viene preso il nome passato al pannello di Input insieme al valore scelto nel combobox
				//E vengono utilizzati nelle query di ProdottoDAO per stabilire il codice del prodotto
				//e di MagazziniereDAO per aumentare la disponibilità del prodotto selezionato
					int quantita = Integer.parseInt(scelta);
					int codice_prodotto = prodotto.CodiceProdotto(vettore1.get(e.getFirstRow())[0]);
					magazz_business.AggiungiProdotti(quantita,codice_prodotto);
					model.setValueAt(false, row, 6);
				//Prende la disponibilità attuale dal DB. Non è possibile usare vettore1 perchè è il valore vecchio!
					int vecchia_disp = Integer.parseInt(vettore1.get(row)[3]); 
					int nuova_disp = vecchia_disp + quantita;
					try{
						if (nuova_disp <6){
							model.setValueAt(nuova_disp, row, 3);
						}
					
						else {
							isUpdatingTable=true;
							model.removeRow(row);
						}
					}
					catch(ArrayIndexOutOfBoundsException eccezione){
						//eccezione ignorata come se non ci fosse un domani
					}
				
				}
				
				catch (NumberFormatException eccezione){
					model.setValueAt(false, row, 6);
					//Se si preme il tasto ANNULLA dell'OptionPane non viene lanciata nessuna eccezione che blocca
					//il programma e il rifornimento può continuare. La spunta dal Checkbox viene rimossa (valore false)
				}
			
				}
			
			
			//----------------ALTRO MAGAZZINIERE------------------------------
			else{		
				try{
					String scelta = (String)JOptionPane.showInputDialog(null, "Prodotto selezionato: "+vettore2.get(e.getFirstRow())[0]+
							".\n Inserire la quantità"
							+ " da rifornire", "Rifornimento",JOptionPane.QUESTION_MESSAGE,
							null, quantita_combobox, quantita_combobox[0]);
					int quantita = Integer.parseInt(scelta);
					int codice_prodotto = prodotto.CodiceProdotto(vettore2.get(e.getFirstRow())[0]);
					magazz_business.AggiungiProdotti(quantita,codice_prodotto);
					model.setValueAt(false, row, 6);
					//Come sopra (Magazziniere 1)
						int vecchia_disp = Integer.parseInt(vettore2.get(row)[3]); 
						int nuova_disp = vecchia_disp + quantita;
						try{
							if (nuova_disp <6){
								model.setValueAt(nuova_disp, row, 3);
							}
						
							else {
								isUpdatingTable=true;
								model.removeRow(row);
							}
						}
						catch(ArrayIndexOutOfBoundsException eccezione){
							//eccezione ignorata come se non ci fosse un domani
						}
						
						
				}
				
				catch (NumberFormatException eccezione){
					model.setValueAt(false, row, 6);
					//Se si preme il tasto ANNULLA dell'OptionPane non viene lanciata nessuna eccezione che blocca
					//il programma e il rifornimento può continuare. La spunta dal Checkbox viene rimossa (valore false)
				}
		
			}
			
			
		}
	
		
	}
	

}  


