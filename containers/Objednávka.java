package containers;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Objednávka implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Kontajner>  myList = new ArrayList<Kontajner>();										//arraylist na uskladnenie objednávky  vo¾ba zákaznika aké kontajnery si zvoli
	
	/*
	transient private List<SledovatelObjednavky> sledovatelia = new ArrayList<>();

	public void pridajSledovatela(SledovatelObjednavky sledovatelStavu) {
		sledovatelia.add(sledovatelStavu);
	}
	
	public void upovedomSledovatelov() {
		for (SledovatelStretu s : sledovatelia)
			s.upovedom();
	}*/
	//rozsah pre nadrž || mraziarensky kontajner
	
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah){
		if(stage.equals("MrazStage")){
			if(rozsah>0){
				//ak je zaškrtnutý checkbox tak rozsah nastavíme implicitne prostredníctvom Textfield2-u
				
				if (kontajner.equals("Chladiaci") && mnozstvo<=15) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarenský("Chladiaci", mnozstvo, rozsah));
						// miesto pre observera poèet
					}
				} else if (kontajner.equals("Hlbokomraziarenský") && (rozsah>15 && rozsah<30)) {
						
					for (int i = 0; i < mnozstvo; i++) {
						//observer
						myList.add(new Mraziarenský("Hlbokomraziarenský", mnozstvo, rozsah));
					}
				}
			}
			else if(rozsah==0){
				//ak nie je zaškrtnutý checkbox, tam som explicitne nastavil rozsah hodnote = 0, tu použijem iný konštruktor podåa ComboBox-a
				if (kontajner.equals("Chladiaci")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarenský("Chladiaci", mnozstvo));
						// miesto pre observera poèet
					}
				} else if (kontajner.equals("Hlbokomraziarenský")) {

					for (int i = 0; i < mnozstvo; i++) {
						//observer
						myList.add(new Mraziarenský("Hlbokomraziarenský", mnozstvo));
					}
				}
				
				
			}
			//konèí MrazStage
		}
		
		
	}
	
	
	
	
	
	
	
	public void addmyList(int mnozstvo, String kontajner){													//funkcia addmyList ktorá vytvorí daný poèet kontajnerov pod¾a vo¾by zakaznika
		
		if(kontajner.equals("Mraziarenský"))																//vytvorenie kontajnera pod¾a názvu(checkbox)
		{
		for(int i = 0;i<mnozstvo;i++){
			
		myList.add(new Mraziarenský(mnozstvo));
		}
		}else if(kontajner.equals("Nádrž")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new Nádrž(mnozstvo));
			}
		}else if(kontajner.equals("Transportný")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new Transportný(mnozstvo));
			}
		}else{
			for(int i = 0; i<mnozstvo;i++){
				myList.add(new Ubytovací(mnozstvo));
			}
		}

		myList.trimToSize();																					//funkcia trimToSize ktorá upraví arraylist podla poètu, zmenší zväèší
	}
	
	
	
	public int rti(Objednávka objednávka, Kontajner kontajner){															//RTTI
		int number=0;
		
		//Iterator<List> iterator = new Iterator<List>() {
		//};
		
		for(int i =0; i<objednávka.zistiPoèet();i++){
			Kontajner kontajner2;
			kontajner2=objednávka.myList.get(i);				
			if (kontajner2.equals(kontajner)) {
			number++;	
			}
		}
		return number;																							//zapis do suboru number poèet a daný Clas get object?	
	}
	
	
	
	public int zistiPoèet(){																					//vráti poèet prvkov v arraliste
		return myList.size();
	}
	
	
	public void zmaž(){																							//funkcia zmaže všetky položky v Arraliste
		if(!myList.isEmpty()){																						//iba ak už v sebe obsahuje položky
			myList.clear();
		myList.trimToSize();
		}
	}
	
	
	
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//uloženie objednávky do súboru FILE
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(subor.getAbsolutePath()));			
		out.writeObject(myList);
		out.close();
	}
	
	public void nacitaj(File subor) throws ClassNotFoundException,IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(subor.getAbsolutePath()));
		Objednávka nacitany = (Objednávka) in.readObject();
		in.close();
	}

	public int zistiCenu(Objednávka list){																		//funkcia zisti celkovu cenu kontajnerov
		int sum=0;
		Kontajner kontajner;
		for (int i = 0; i <list.myList.size(); i++) {
			kontajner= list.myList.get(i);
			sum+=kontajner.zistiCenu();
		}
		return sum;
	}
	
	public int zistiCas(Objednávka list){																		//funkcia na zistenie celkového produkèného èasu
		int sum=0;
		Kontajner kontajner;
		for(int i=0;i<list.zistiPoèet();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	public int zistiPocetDni(Objednávka list){
		int dni = (int) zistiCas(list)/24;																		//volanie metódy zisti cas, ktorá vráti celkový èas a naslédne sa vydeli konštantou 24 aby sa zistil
		 																										//poèet dní
		return dni;	
	}

	
}
