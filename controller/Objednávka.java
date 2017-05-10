package controller;

import java.beans.Transient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.PrimitiveIterator.OfDouble;

import javax.annotation.Resource;
import javax.swing.table.TableModel;

import org.omg.CORBA.INV_FLAG;

import containers.Kontajner;
import containers.Mraziarenskı;
import containers.Nádr;
import containers.Transportnı;
import containers.Ubytovací;
import kamióny.Auto;
import kamióny.Avia;
import kamióny.Kamión;
import kamióny.Prives;

public class Objednávka implements Serializable{
	/**
	 * Author Pavol Grofèík
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private int PocetKontajnerov = 0;
	private int PocetDni=0;
	private int TotalCena=0;		//€
	private int Vzdialenost=0;		//km
	private String Trieda=null;
	private String Kraj=null;
	private String Mesto=null;

	private String[] kupon = {"0000", "1111", "2222", "123456789"};											//Zlavovı kupón ktorı obsahuje akceptujúce hodnoty	kupónov									
	
	
	private ArrayList<Kontajner> myList = ListCreator.Listcreate();
	private ArrayList <Auto> cars = ListCreator.Listcreate();
	
	
	transient private ArrayList<SledovatelObjednavky> sledovatelia = new ArrayList<>();						//arraylist na uchovovávanie sledovatelov kontajnerov, neuchováva sa pri uloení do súboru
	
	//implicitné nastavenie poètu vozidiel pri vytvorení objednávky
	public Objednávka(){
		
		try {
			addCars(10, "Kamión");
			addCars(10, "Avia");
		} catch (NesprávnyTypVozidla e) {
		}
	}
	
	//Observer -> metódy
	
	public void pridajSledovatela(SledovatelObjednavky sledovatelObjednavky) {
		sledovatelia.add(sledovatelObjednavky);
	}

	public void upovedomSledovatelov() {
		
		for (SledovatelObjednavky s : sledovatelia) {
			s.upovedom();
		}
	}

	public int getPocetKontajnerov() {
		return this.PocetKontajnerov;
	}
	
	public void setPocetDni(int num){
		this.PocetDni+=num;
	}
	
	public void setCena(){
		TotalCena = (int) (TotalCena * 0.9);
	}

	//znii cenu o 5 %
	public boolean zlavovyKupon(String kupon){
		String pom=null;
		
		for(int i=0;i<this.kupon.length;i++){
			pom= this.kupon[i];
			if(pom.equals(kupon)){
				
				setCena();
				//this.TotalCena*=0.95;
				return true;
			}
		}
		return false;
	}
	
	public void kalibrujPocetKonAndAut(){
		if(PocetKontajnerov>30){
			int pom = PocetKontajnerov-30;
			try {
				addCars(pom, "Avia");
				addCena(pom*300);																					//Vypoíèa nové vozidlá a pridá aj cenu €
			} catch (NesprávnyTypVozidla e) {
				
			}
		}
	}
	
	//Dorobi transport
	
	public int NalozKontajnery(int vzdialenost){
		
		//pomocné premenné
		Kontajner kontajner;
		Auto auto;
		Avia avia;
		Kamión kamión;
		
		int number=0;
		int pocetVozidiel=0;
		
		
		//Najprv nakalibruje poèet kontajnerov a áut
		//automatickı prida
		kalibrujPocetKonAndAut();
		
		//pre tuto vzdialenost sa v prvorade vyuijú avie, kvôli tomu e odväzú menej a majú aj menšiu rıchlos
		if (vzdialenost < 200) {
			//aktualizácia total pocet dni
			setPocetDni(2);
			//PocetDni = PocetDni + 2;
			for (int j = 0; j < cars.size(); j++) {
				auto = cars.get(j);

				if (number == PocetKontajnerov) {
					break;
				}

				for (int i = 0; i < myList.size(); i++) {

					kontajner = myList.get(i);
					if (number == PocetKontajnerov) {
						break;
					}
					if (auto instanceof Avia) {
						avia=(Avia) auto;
						if (!auto.zistiNalozeny()) {
							auto.nalozAuto(kontajner);
							number++;
							pocetVozidiel++;
							break;

						} else {
							break;
						}
					} else {
						break;
					}
				}
			}

			if (number == PocetKontajnerov) {
				return pocetVozidiel;
			}

			else {

				for (int k = 0; k < cars.size(); k++) {
					
					auto = cars.get(k);
					
					if (number == PocetKontajnerov){
						break;
					}
				
					for (int i = 0; i < myList.size(); i++) {

						if (number == PocetKontajnerov){
							break;
						}
						
						kontajner = myList.get(i);

						if (auto instanceof Kamión) {
							
							kamión = (Kamión) auto;
							//PocetDni+= kamión.ExportTime(vzdialenost);
							if (!kamión.zistiNalozeny()) {
								kamión.nalozAuto(kontajner);
								number++;
								if(number==PocetKontajnerov){
									pocetVozidiel++;
								}
								
							} else if (kamión.zistiNaklad()){
								pocetVozidiel++;
								break;
							}
						}
						else {
							break;
						}
					}
				}
				return pocetVozidiel;
			}

		} else /* if(vzdialenost>=200) */ {
			//this.PocetDni +=3;
			setPocetDni(3);
			
			if(number!=PocetKontajnerov){
			// uprednostnujeme kamióny
			for (int i = 0; i < cars.size(); i++) {
				auto = cars.get(i);

				if (number == PocetKontajnerov) {
					return pocetVozidiel;
				}
				
				for (int j = 0; j < myList.size(); j++) {
					kontajner = myList.get(j);

					if (number == PocetKontajnerov) {
						break;
					}
					if (auto instanceof Kamión) {
						kamión = (Kamión) auto;
						//PocetDni+= kamión.ExportTime(vzdialenost);
						//pocetVozidiel++;
						
						if (!kamión.zistiNaklad()) {
							kamión.nalozAuto(kontajner);
							number++;
							if(number==PocetKontajnerov){
								pocetVozidiel++;
							}
						}
						else if (kamión.zistiNaklad()){
							pocetVozidiel++;
							break;
						}
						//if (kamión.zistiNaklad()) {
						//	pocetVozidiel++;
							//break;
						}
					else {
						break;
					}
					
					}
				}

			}
			
			/*if(number == PocetKontajnerov){
				return pocetVozidiel;
			}*/
			 if(number!=PocetKontajnerov){
				// Kamióny sa vyèerpali, treba naloi Avie
				for (int j = 0; j < cars.size(); j++) {
					auto = cars.get(j);

					if (number == PocetKontajnerov) {
						break;
					}

					for (int i = 0; i < myList.size(); i++) {

						kontajner = myList.get(i);
						if (number == PocetKontajnerov) {
							break;
						}
						if (auto instanceof Avia) {
							avia = (Avia) auto;
							
							//PocetDni+= avia.ExportTime(vzdialenost);
							
							if (!avia.zistiNalozeny())
								avia.nalozAuto(kontajner);
								number++;
								pocetVozidiel++;
								break;
						}
						else {
							break;
						}
					}
				}
				return pocetVozidiel;
				
			}
			if(number== PocetKontajnerov) {
				return pocetVozidiel;
			}
			return pocetVozidiel;

		}

	}
	
	
	
	
	//Metóda na upresnenie špecifikácií
	public void saveTransport(String vzdialenost, String typZasielky, String mesto){
		
		this.Kraj=vzdialenost;															//Trieda si zapamätá kraj, ktorı si zvolil zákazník
		this.Mesto=mesto;
		
		switch(typZasielky){
		
		case "1.":	setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					addCena(200); 														//Prvá trieda je o 200 € drhašia kvôli rıchlejšej produkcii
					break;
		case "2.": setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					break;
		}
		
	}
	
	public void addCars(int pocet, String vozidlo) throws NesprávnyTypVozidla{
		
		switch (vozidlo) {
		
		case "Avia":
			for(int i=0;i<pocet;i++){
				cars.add(new Avia());
			}
			cars.trimToSize();
			break;
		case "Kamión":
			for(int i=0;i<pocet;i++){
				cars.add(new Kamión(new Prives()));
			}
			cars.trimToSize();
			break;
		default:								//V prípade ak admin pridá nove vozidla, a nastane typo v type vozidla
			throw new NesprávnyTypVozidla();
		}
		
	}
	
	//Pridavanie do listu jednotlivé kontajnery
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah) throws NespravnyRozsah		//vyhadzovanie vlastnıch vınimiek
	{
		if(stage.equals("MrazStage")){
			if (rozsah > 0) {

				switch (kontajner) {

				case "Chladiaci": {
					if (rozsah < 5 || rozsah > 15) {
						throw new NespravnyRozsah(); // Vlastná vınimka
					} else {
						for (int i = 0; i < mnozstvo; i++) {
							myList.add(new Mraziarenskı("Chladiaci", mnozstvo, rozsah));
							// observer
							PocetKontajnerov++;
						}
					}
				}
					break;
				case "Hlbokomraziarenskı": {
					if (rozsah > 15 && rozsah < 30) {
						for (int i = 0; i < mnozstvo; i++) {
							// observer
							myList.add(new Mraziarenskı("Hlbokomraziarenskı", mnozstvo, rozsah));
							PocetKontajnerov++;
						}
					} else {
						throw new NespravnyRozsah(); // Vlastná vınimka
					}

				}
					break;
				}
			}
			else if(rozsah==0){
				//ak nie je zaškrtnutı checkbox, tam som explicitne nastavil rozsah hodnote = 0, tu pouijem inı konštruktor podåa ComboBox-a
				if (kontajner.equals("Chladiaci")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarenskı("Chladiaci", mnozstvo));
						// miesto pre observera poèe
						PocetKontajnerov++;
					}
				} else if (kontajner.equals("Hlbokomraziarenskı")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarenskı("Hlbokomraziarenskı", mnozstvo));
						//observer
						PocetKontajnerov++;
					}
				}
			}
		upovedomSledovatelov();
		
		}
		else if (stage.equals("TranStage")) {																	// kontrola pre Transportnı kontajner
			//Vlastná Vınimka Nespravny rozsah
			if(rozsah<2 || rozsah >10){
				throw new NespravnyRozsah();
			}
			else{

				for (int i = 0; i < mnozstvo; i++) {
					myList.add(new Transportnı(mnozstvo, rozsah, kontajner));
					// observer
					PocetKontajnerov++;
				}

				upovedomSledovatelov();
			}
		}
		else if(stage.equals("NadrStage")){																	//kontrola pre Nádr (kontajner)
			
			for(int i=0;i<mnozstvo;i++){
				myList.add(new Nádr(mnozstvo, kontajner));
				//observer
				PocetKontajnerov++;
			}
			upovedomSledovatelov();
		}
		myList.trimToSize();
	}
	
	
	
	//metóda pridá ubytovací kontajner
	public void addUbytovaci(String stage, int mnozstvo, String kontajner, int rozsah, boolean balcon, boolean terrace) throws NespravnyRozsah {
		
		if (stage.equals("UbytStage")) {
			switch (stage) {
			case "UbytStage":
				if ((rozsah < 1 || rozsah > 5) && mnozstvo < 2) {
					throw new NespravnyRozsah();
					
				} else if (balcon) {
					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Ubytovací(mnozstvo, kontajner, rozsah, balcon, terrace));
						// Observer
						PocetKontajnerov++;
					}
				} else {
					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Ubytovací(mnozstvo, kontajner, rozsah));
						// Observer
						PocetKontajnerov++;
					}
				}
				upovedomSledovatelov();
			}
			myList.trimToSize();
		}
	}
	
	public int rtti(Objednávka objednávka, String druh){															//RTTI
		
		int number=0;
		Kontajner pomocny;
		
		for(int i =0; i<objednávka.zistiPoèet();i++){
			
			pomocny=objednávka.myList.get(i);
			
			switch (druh) {
			case "Ubytovací":
				if (pomocny instanceof Ubytovací){
					number++;
					}
				break;
			case "Transportnı":
				if(pomocny instanceof Transportnı){
					number++;
				}
				break;
			case "Mraziarenskı":
				if(pomocny instanceof Mraziarenskı){
					number++;
				}
				break;
			case "Nádr":
				if (pomocny instanceof Nádr) {
					number++;
				}
				break;
			}
		}
		return number;																							//vráti aktuálny poèet kontajnerov daného typu
	}
	
	public String kontajnerInfo(String typ) throws ObjectNotFound{
		
		Kontajner pomocny=null;
		String temp=null;
		
		for(int i=0; i<myList.size();i++){																		//preh¾adávanie celého listu objednávky
			
			pomocny=myList.get(i);																				//priradenie kontajneru v liste s indexom i do pomocnej premenej, odkial dostaneme poadované informácie
			
			//Prehladavanie pommocou názvu kontajnera, vyuitím funkcie substring ktorá vracia runtime nazov od zac indexu do end indexu, viï oracle.docs....
			
			//Nádr
			if(pomocny.toString().substring(11, 16).equals(typ)){
				temp= "Hmotnos: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Záruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " €\n";
				return temp;
			}
			//Transportnı
			else if(pomocny.toString().substring(11, 22).equals(typ)){
				temp= "Hmotnos: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Záruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " €\n";
				return temp;
			}
			//Ubytovací
			else if(pomocny.toString().substring(11, 20).equals(typ)){
				temp= "Hmotnos: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Záruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " €\n";
				return temp;
			}
			//Mraziarenskı
			else if(pomocny.toString().substring(11, 23).equals(typ)){
				temp= "Hmotnos: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Záruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " €\n";
				return temp;
			}
		}
		throw new ObjectNotFound();																				//Ak sa zvolenı typ kontajnera nenachádza v arraliste, vyhodí sa vlastná vınimka
	}
	
	
	public String ObjednavkaInfo(){
		
		//Zobrazí podrobné informácie o 
		return "Mesto: " + Mesto + "\n" + "Vzdialenos " + Integer.toString(Vzdialenost) +  " km" + "\n" + "Celková cena: " + Integer.toString(zistiCenu(this))+ " €" + "\n" + "Produkènı èas: "
		+ Integer.toString(zistiPocetDni(this)) + " Dní";
	}
	
	
	
	
	public int zistiPoèet(){																					//vráti poèet prvkov v arraliste
		return myList.size();
	}
	
	
	public void zma(){																							//funkcia zmae všetky poloky v Arraliste
		if(!myList.isEmpty()){																					//iba ak u v sebe obsahuje poloky
			
			this.PocetKontajnerov=0;																			//Aktualizuje observera - aktuálny poèet kontajnerov
			this.Vzdialenost=0;
			
			myList.clear();
			myList.trimToSize();
			upovedomSledovatelov();
		}
	}
	
	
	
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//uloenie objednávky do súboru FILE
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
		@SuppressWarnings("unchecked")
		ArrayList<Kontajner> list = (ArrayList<Kontajner>) in.readObject();
		in.close();
		myList=list;																								//Naèíta serializovanı arraylist zo suboru do objednávky 
																													//naèíta objednávku (kontajery) excluding observer
		PocetKontajnerov=myList.size();
		upovedomSledovatelov();
		
		myList.trimToSize();
		
		} catch (Exception e) {
			
			throw new ClassNotFoundException();
		}
	}

	public int zistiCenu(Objednávka list){	
		//funkcia zisti celkovu cenu kontajnerov
		int sum=0;
		Kontajner kontajner;
		
		for (int i = 0; i <list.myList.size(); i++) {
			kontajner= list.myList.get(i);
			sum+=kontajner.zistiCenu();
			TotalCena+=sum;																							//Uloí celkovú cenu do premennej TotalCena ktorá bude slúi na finalizáciu objednávky
		}
		return sum;
	}
	
	public int zistiCas(Objednávka list){
		//funkcia na zistenie celkového produkèného èasu
		int sum=0;
		Kontajner kontajner;
		
		for(int i=0;i<list.zistiPoèet();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	public int zistiPocetDni(Objednávka list){
		
		int dni=0;
		int tyzdne=0;
		
		if(Trieda.equals("1.")){
			addCena(200);
			dni = (int) zistiCas(list)/24;																		//volanie metódy zisti cas, ktorá vráti celkovı èas a naslédne sa vydeli konštantou 24 aby sa zistil
			tyzdne=dni/7;
			this.PocetDni+=dni; 																				//Pripoèíta k celkovım dnom vıroby
			dni-=(tyzdne*2);
			return dni;																				//Prvá trieda sa produkuje nonstop 7D/24H, teda aj cez víkend
		}
		else{
			dni = (int) zistiCas(list)/24;
			this.PocetDni+=dni;
			return dni;
			
		}
	}
	
	//metóda zistí aktuálny poèet dni
	
	public int getPocetDni(){
		return this.PocetDni;
	}
	
	public int getTotalCena(){
		return this.TotalCena;
	}

	public void addCena(int delta){
		TotalCena+=delta;
	}
	
	public void setVzdialenost(String kraj){
		
		//podla kraju sa jednotlivo urèuje vzdialenost
		//Záleí aj od toho cena, jednotlive kraje majú rôzne ceny
		
		switch (kraj) {
		case "Bratislavskı": this.Vzdialenost=100;
							addCena(100);
			break;
		case "Trnavskı": this.Vzdialenost=150;
						 addCena(150);
			break;
		case "Nitriansky": this.Vzdialenost=150;
						   addCena(150);
			break;
		case "Trenèiansky": this.Vzdialenost=200;
							addCena(200);
			break;
		case "Banskobystrickı": this.Vzdialenost=250;
								addCena(250);
			break;
		case "ilinskı": this.Vzdialenost=250;
						 addCena(250);
			break;
		case "Prešovskı": this.Vzdialenost=300;
						  addCena(300);
			break;
		case "Košickı": this.Vzdialenost=300;
						addCena(300);
			break;
		}
	}
	
	public int getCars(){
		return cars.size();
	}
	public int getVzdialenost(){
		return this.Vzdialenost;
	}
	
}
