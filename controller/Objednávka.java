package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import containers.Kontajner;
import containers.Mraziarenský;
import containers.Nádrž;
import containers.Transportný;
import containers.Ubytovací;
import kamióny.Auto;

public class Objednávka implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private int CelkovyCas=0;
	//private int vzdialenost=0;
	
	private int PocetKontajnerov = 0;
	
	private ArrayList<Kontajner>  myList = new ArrayList<Kontajner>();										//arraylist na uskladnenie objednávky  vo¾ba zákaznika aké kontajnery si zvoli
	private ArrayList<Auto> transport = new ArrayList<Auto>();												//arraylist na uskladnenie aktuálnom poète aut
	
	transient private ArrayList<SledovatelObjednavky> sledovatelia = new ArrayList<>();						//transient arraylist na uchovovávanie sledovatelov kontajnerov
	
	//Observer -> metódy
	
	public void pridajSledovatela(SledovatelObjednavky sledovatelObjednavky){
		sledovatelia.add(sledovatelObjednavky);
	}
	
	public void upovedomSledovatelov(){
		for(SledovatelObjednavky s : sledovatelia){
			s.upovedom();
		}
	}

	public int getPocetKontajnerov(){
		return this.PocetKontajnerov;
	}
	
	public void addTransport(int PocetKontajnerov){
		if (PocetKontajnerov!=0) {
			for(int i=0; i<myList.size();i++){
			//dokonèi rtti
			}		
		}
		
		
		
	}
	
	
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah) throws NespravnyRozsah
	{
		if(stage.equals("MrazStage")){
			if (rozsah > 0) {

				switch (kontajner) {

				case "Chladiaci": {
					if (rozsah < 5 || rozsah > 15) {
						throw new NespravnyRozsah(); // Vlastná výnimka
					} else {
						for (int i = 0; i < mnozstvo; i++) {
							myList.add(new Mraziarenský("Chladiaci", mnozstvo, rozsah));
							// observer
							PocetKontajnerov++;
						}
					}
				}
					break;
				case "Hlbokomraziarenský": {
					if (rozsah > 15 && rozsah < 30) {
						for (int i = 0; i < mnozstvo; i++) {
							// observer
							myList.add(new Mraziarenský("Hlbokomraziarenský", mnozstvo, rozsah));
							PocetKontajnerov++;
						}
					} else {
						throw new NespravnyRozsah(); // Vlastná výnimka
					}

				}
					break;
				}
			}
			else if(rozsah==0){
				//ak nie je zaškrtnutý checkbox, tam som explicitne nastavil rozsah hodnote = 0, tu použijem iný konštruktor podåa ComboBox-a
				if (kontajner.equals("Chladiaci")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarenský("Chladiaci", mnozstvo));
						// miesto pre observera poèe
						PocetKontajnerov++;
					}
				} else if (kontajner.equals("Hlbokomraziarenský")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarenský("Hlbokomraziarenský", mnozstvo));
						//observer
						PocetKontajnerov++;
					}
				}
			}
		upovedomSledovatelov();
		
		}
		else if (stage.equals("TranStage")) {																	// kontrola pre Transportný kontajner
			
			//Vlastná Výnimka Nespravny rozsah
			if(rozsah<2 || rozsah >10){
				throw new NespravnyRozsah();
			}
			else{

				for (int i = 0; i < mnozstvo; i++) {
					myList.add(new Transportný(mnozstvo, kontajner));
					// observer
					PocetKontajnerov++;
				}

				upovedomSledovatelov();
			}
		}
		else if(stage.equals("NadržStage")){																	//kontrola pre Nádrž (kontajner)
			for(int i=0;i<mnozstvo;i++){
				myList.add(new Nádrž(mnozstvo, kontajner));
				//observer
				PocetKontajnerov++;
			}
			upovedomSledovatelov();
		}
		myList.trimToSize();
	}
	
	
	
	
	public void addUbytovaci(String stage, int mnozstvo, String kontajner, int rozsah, boolean balcon) throws NespravnyRozsah{
		if (stage.equals("UbytStage")) {
			
			if ((rozsah<1 || rozsah >5) && mnozstvo<2) {
				throw new NespravnyRozsah();
			}
			else if(balcon){
				for(int i=0;i<mnozstvo;i++){
					myList.add(new Ubytovací(mnozstvo, kontajner, rozsah, balcon));
					//Observer
					PocetKontajnerov++;
				}
			}
			else{
				for(int i=0;i<mnozstvo;i++){
					myList.add(new Ubytovací(mnozstvo, kontajner, rozsah, balcon));
					//Observer
					PocetKontajnerov++;
				}
			}
			upovedomSledovatelov();
		}
		myList.trimToSize();
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
			this.PocetKontajnerov=0;																				//Aktualizuje observera - aktuálny poèet kontajnerov
			upovedomSledovatelov();
		}
	}
	
	
	
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//uloženie objednávky do súboru FILE
		try{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(subor.getAbsolutePath()));			
		out.writeObject(myList);
		out.close();
		} catch (Exception e) {
			throw new IOException();
		}
	}
	
	public void nacitaj(File subor) throws ClassNotFoundException,IOException {
		
		try{
			
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(subor.getAbsolutePath()));
		//Objednávka nacitany = (Objednávka) in.readObject();
		ArrayList<Kontajner> list = (ArrayList<Kontajner>) in.readObject();
		in.close();
		myList=list;																								//Naèíta serializovaný arraylist zo suboru do objednávky 
																													//naèíta objednávku (kontajery) excluding observer
		PocetKontajnerov=myList.size();
		upovedomSledovatelov();
		
		myList.trimToSize();
		
		} catch (Exception e) {
			
			throw new ClassNotFoundException();
		}
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
