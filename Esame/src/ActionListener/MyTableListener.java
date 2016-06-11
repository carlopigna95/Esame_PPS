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

//Listener di Magazziniere per il rifornimento dei prodotto con Poca Disponibilità
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
		
		Vector<String[]> vettore1 = magazz_business.PocaDisponibilita();
		String[] quantita_combobox = {"1","2","3","4","5"};
		
		int row = e.getFirstRow();
		MyTableModel model = (MyTableModel)e.getSource();
		Object data = model.getValueAt(row, 6);
		//Controllo sul valore dei checkbox
		if (data.equals(true)){
			//viene effettuato un controllo sul magazziniere per stabilire quale prodotto stampare nel pannello di input

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
					magazz_business.RifornimentoProdotto(quantita,codice_prodotto);
					model.setValueAt(false, row, 6);
				//Prende la disponibilità attuale dal DB. Non è possibile usare vettore1 perchè è il valore vecchio!
					int vecchia_disp = Integer.parseInt(vettore1.get(row)[3]); 
					int nuova_disp = vecchia_disp + quantita;
					
						if (nuova_disp <5){
							model.setValueAt(nuova_disp, row, 3);
						}
						
					
						else {
							isUpdatingTable=true;
							model.removeRow(row);
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
	
 


