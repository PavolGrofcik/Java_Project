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
import containers.Mraziarenskı;
import containers.Nádr;
import containers.Transportnı;
import containers.Ubytovací;
import kamióny.Auto;
import kamióny.Avia;
import kamióny.Kamión;
import kamióny.Prives;

/**
 * 
 * @author Pavol Grofèík
 * Kontroler Hlavná trieda z ktorej sa riadi celı proces objednania a po transport
 *
 */

public class Objednávka implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Špecifické atribúty slúia na upresnenie objednávky
	 */
	private int PocetKontajnerov = 0;
	private int PocetDni=0;			//dni
	private int TotalCena=0;		//€
	private int Vzdialenost=0;		//km
	private String Trieda=null;		//typ tried 1/2
	private String Kraj=null;
	private String Mesto=null;

	private String[] kupon = {"0000", "1111", "2222", "3333"};													//Zlavovı kupón, ktorı obsahuje akceptujúce hodnoty rôznych hdnôt									
	
	
	private ArrayList<Kontajner> myList = ListCreator.Listcreate();
	private ArrayList <Auto> cars = ListCreator.Listcreate();
	
	
	transient private ArrayList<SledovatelObjednavky> sledovatelia = new ArrayList<>();							//Arraylist na uchovovávanie sledovatelov kontajnerov, neuchováva sa pri uloení do súboru
	
	
	/**
	 * Konštruktor, defaultne sa pridá do List-u vozidiel 20 áut
	 */
	public Objednávka(){
		
		try {
			addCars(10, "Kamión");																				//Default nastaví 20 vozidiel
			addCars(10, "Avia");
		} catch (NesprávnyTypVozidla e) {
		}
	}
	
	//Observer -> metódy
	/**
	 * Metóda slúiaca na pridanie Sledovate¾a
	 * @param sledovatelObjednavky Sledovate¾ sleduje danı poèet
	 */
	public void pridajSledovatela(SledovatelObjednavky sledovatelObjednavky) {									//Pridá sledovate¾a objednávky
		sledovatelia.add(sledovatelObjednavky);
	}

	/**
	 * Metóda aktualizuje súèasnıch observerov
	 */
	public void upovedomSledovatelov() {																		//Upovedomí observera
		
		for (SledovatelObjednavky s : sledovatelia) {
			s.upovedom();
		}
	}

	public int getPocetKontajnerov() {																			//Zistí aktuálny poèet kontajnerov v objednávke
		return this.PocetKontajnerov;
	}
	
	public void setPocetDni(int num){																			//Nastaví poèet dní
		this.PocetDni+=num;
	}
	/**
	 * Metoóda uplatní z¾avovı kupón pri zhode znakov
	 * @param kupon Reazec obsahujúci valídne znaky kupónu
	 * @return	Nová cena zníená o 5%
	 */
	public int zlavovyKupon(String kupon){																		//Metóda uplatní zlavovı kupón
		String pom=null;
		
		for(int i=0;i<this.kupon.length;i++){
			pom= this.kupon[i];
			if(pom.equals(kupon)){
				TotalCena= (int) (TotalCena*0.95);
				return TotalCena;
			}
		}
		return 0;
	}
	/**
	 * Metóda nakalibruje poèet áut pri väèších objednávkach ako 30 kontajnerov
	 * No vypoièané vozidlá sa nepridávajú do list-u vozidiel firmy
	 */
	public void kalibrujPocetKonAut(){																			//Metóda nakalibruje poèet aút a vypoièia ich 
		if(PocetKontajnerov>30){
			int pom = PocetKontajnerov-30;
			try {
				addCars(pom, "Avia");
				addCena(pom*400);																				//Vypoíèa nové vozidlá a pridá aj cenu €, avšak nepridáva do listu vozidiel
			} catch (NesprávnyTypVozidla e) {
				
			}
		}
	}
	
	/**
	 * Metóda nakladá kontajnery na vozidlá pod¾a vzdialenosti trasy, zaráta exportnú dobu prepravy a naloí prioritne, buï Avie alebo Kamióny
	 * @param vzdialenost DlŸka trasy v km
	 * @return	Aktuálny naloenı poèet vozidiel
	 */
	public int NalozKontajnery(int vzdialenost){
		
																													//Pomocné premenné
		Kontajner kontajner;
		Auto auto;
		Avia avia;
		Kamión kamión;
		
		int number=0;
		int pocetVozidiel=0;
		
		
																													//Najprv nakalibruje poèet kontajnerov a áut
																													//automatickı vypoièa nové vozidlá
		kalibrujPocetKonAut();
		
																													//Pre tuto vzdialenost sa v prvorade vyuijú avie, kvôli tomu e odvezú menej a majú aj menšiu rıchlos
		if (vzdialenost < 200) {
																													//Aktualizácia pocet dni
			setPocetDni(2);
			
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

		} else  {																					//Ak je vzdialenost väèšia ako 200 km priorita sú kamióny s prívesmi, ak kamióny u nebudú staèit nakladajú sa aj Avie
			
			setPocetDni(3);																			//Nastaví celkovı èas na vırobu aj s exportom
			
			if (number != PocetKontajnerov) {
																									//Uprednostnujeme kamióny
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
							
							if (!kamión.zistiNaklad()) {
								kamión.nalozAuto(kontajner);
								number++;
																									//Nakladajú sa kamióny, vid prekonávajúcu metódu
								if (number == PocetKontajnerov) {
									pocetVozidiel++;
								}
							} else if (kamión.zistiNaklad()) {
								pocetVozidiel++;
								break;
							}
						} else {
							break;
						}

					}
				}

			}
			
																									//Nakladajú sa Avie, pretoe poèet kontajnerov prevıšil kamióny
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
							
																									//Kontroluje sa èi su avie naloene ak nie tak sa nalozia
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
	
	
	
	/**
	 * Metóda nastaví typ zásielky a prida cenu v závislosti od vzdialenosti doruèenia objednávky
	 * @param vzdialenost Zvolenı kraj na SR
	 * @param typZasielky Prvá alebo druhá trieda
	 * @param mesto	Mesto v danom kraji, blišia špecifikácia
	 */
	public void saveTransport(String vzdialenost, String typZasielky, String mesto){							//Metóda na upresnenie špecifikácií o transporte
		
		this.Kraj=vzdialenost;																					//Trieda si zapamätá kraj, ktorı si zvolil zákazník
		this.Mesto=mesto;
		
		switch(typZasielky){
		
		case "1.":	setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					addCena(200); 																				//Prvá trieda je o 200 € drahšia kvôli rıchlejšej produkcii a exportu
					break;
		case "2.": setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					break;
		}
		
	}
	
	/**
	 * Metoóda pridáva nove druhy vozidiel do ArrayListu, viï AdminMode
	 * @param pocet	Poèet pridanıch vozidiel
	 * @param vozidlo Typ vozidla
	 * @throws NesprávnyTypVozidla Vlastná vınimka pri nezhode typu vozidla
	 */
	public void addCars(int pocet, String vozidlo) throws NesprávnyTypVozidla{									//Pridá v AdminMode nové vozidla do listu, slúi nato ak firma zakúpi nové vozidlá
		
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
		default:																								//V prípade ak admin pridá nove vozidla, a nastane chyba  v type vozidla
			throw new NesprávnyTypVozidla();
		}
		
	}
	
	/**
	 * Metóda pridáva zvolené typy kontajnerov do objednávky, Trieda ubytovací má vytvorenú vlastnú špeciálnu funkciu
	 * @param stage	Názov okna (stage)
	 * @param mnozstvo	Poèet zvolenıch kontajnerov
	 * @param kontajner	Konkrétny typ kontajnera
	 * @param rozsah Rozsah hôdnôt - small, medium big
	 * @throws NespravnyRozsah	Vlastná vınimka slúiaca na vyhodenie pri nesprávnom zadanom rozsahu
	 */
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah) throws NespravnyRozsah		//Pridavanie do listu jednotlivé kontajnery, s vyhadzovaním vlastnıch vınimiek
	{
		if(stage.equals("MrazStage")){
			if (rozsah > 0) {

				switch (kontajner) {

				case "Chladiaci": {
					if (rozsah < 5 || rozsah > 15) {
						throw new NespravnyRozsah(); 															// Vlastná vınimka
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
		else if (stage.equals("TranStage")) {																	// Otestuje stage  pre Transportnı kontajner
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
	
	
	
	/**
	 * 
	 * @param stage Názov okna (stage)
	 * @param mnozstvo Poèet zvolenıch kontajnerov
	 * @param kontajner Konkrétny typ kontajnera
	 * @param rozsah Rozsah hôdnôt - small, medium big
	 * @param balcon Boolean ak uivate¾ zvolil monos Balcon
	 * @param terrace Boolean ak uívate¾ zvoli s Balconom monos aj terasu
	 * @throws NespravnyRozsah	Vyhodenie vlastnej vınimky pri nadmernom poète okien pripadajúcich na jeden kontajner viac ako 5, záporné èisla
	 */
	public void addUbytovaci(String stage, int mnozstvo, String kontajner, int rozsah, boolean balcon, boolean terrace) throws NespravnyRozsah {		//Metóda urèena len pre Ubytovací kontajner, zloitejšia kvôli agregácii
		
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
	
	/**
	 * RTTI bonus, slúi na zobrazenie poloiek v objednávke
	 * @param objednávka Zvolená objednávka
	 * @param druh	Typ kontajnera slúiaci na preh¾adávanie v zozname objednávky
	 * @return Akutálny poèet zvolenıch typov kontajnera
	 */
	public int rtti(Objednávka objednávka, String druh){															//RTTI, slúi na zobrazenie poloiek  v košíku, viï poslednı stage
		
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
	
	/**
	 * Metóda zobrazí aktuálne informácie o danom type kontajnera
	 * @param typ Typ kontajnera
	 * @return Informácie o zvolenom type kontajnera
	 * @throws ObjectNotFound Vyhodenie vlastnej vınimky ak sa kontajner nenachádza v objednávke
	 */
	public String kontajnerInfo(String typ) throws ObjectNotFound{												//Metóda zobrazí aktuálne informácie o zvolenom kontajneri
		
		Kontajner pomocny=null;
		String temp=null;
		
		for(int i=0; i<myList.size();i++){																		//preh¾adávanie celého listu objednávky
			
			pomocny=myList.get(i);																				//priradenie kontajneru v liste s indexom i do pomocnej premenej, odkial dostaneme poadované informácie
			
			//Prehladavanie pomocou názvu kontajnera, vyuitím funkcie substring ktorá vracia runtime nazov od zac indexu do end indexu, viï oracle.docs....
			
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
	
	/**
	 * Zobrazia sa informácie o mieste doruèenia, celkovej cene a produkènom èase kontajnerov
	 * @return Aktuálne informácie o objednávke
	 */
	public String ObjednavkaInfo(){
		
		//Zobrazí podrobné informácie o objednávke
		return "************\nMesto: " + Mesto + "\n" + "Vzdialenos: " + Integer.toString(Vzdialenost) + " km" + "\n" + "Trieda: " + Trieda + "\n" + "Celkovı èas: " + Integer.toString(zistiPocetDni(this)) + " Dní" + "\n" +
				"Celková cena: " + Integer.toString(zistiCenu())+ " €" + "\n";
	}
	
	
	
	/**
	 * Zistí poèet objednanıch kontajnerov
	 * @return Aktuálny poèet kontajnerov
	 */
	public int zistiPoèet(){																					//vráti poèet kontajnerov v arrayliste
		return myList.size();
	}
	
	/**
	 * Metóda zmae celú objednávku
	 */
	public void zma(){																							//funkcia zmae všetky poloky v Arraliste
		if(!myList.isEmpty()){																					//iba ak u v sebe obsahuje poloky
			
			this.PocetKontajnerov=0;																			//Aktualizuje observera - aktuálny poèet kontajnerov
			this.Vzdialenost=0;
			
			myList.clear();
			myList.trimToSize();
			upovedomSledovatelov();
		}
	}
	
	
	/**
	 * Metóda uloí objednávku do súboru
	 * @param subor Aktuálny súbor
	 * @throws ClassNotFoundException Vınimka
	 * @throws IOException Vınimka
	 */
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//uloenie objednávky do súboru FILE
		try{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(subor.getAbsolutePath()));
		out.writeObject(myList);
		out.close();
		} catch (Exception e) {
			throw new IOException();
		}
	}
	/**
	 * Metóda naèíta zo súboru objednávku
	 * @param subor Naèítanı súbor
	 * @throws ClassNotFoundException Vyhodenie vınimky ak sa iaden súbor nenaèítal
	 * @throws IOException Vyhodenie vınimky spôsobenej vstupom/vıstupom
	 */
	public void nacitaj(File subor) throws ClassNotFoundException,IOException {
		
		try{
			
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(subor.getAbsolutePath()));
		//Objednávka nacitany = (Objednávka) in.readObject();
		@SuppressWarnings("unchecked")
		ArrayList<Kontajner> list = (ArrayList<Kontajner>) in.readObject();
		in.close();
	myList=list;																									//Naèíta serializovanı arraylist zo suboru do objednávky 
																													//naèíta objednávku (kontajery) excluding observer
		PocetKontajnerov=myList.size();
		upovedomSledovatelov();
		
		myList.trimToSize();
		
		} catch (Exception e) {
			
			throw new ClassNotFoundException();
		}
	}

	/**
	 * Metóda spoèíta celkové ceny kontajnerov
	 * @return Celková cena kontajnerov
	 */
	private int zistiCenu(){	
		//funkcia zisti celkovu cenu kontajnerov
		int pom=0;
		Kontajner kontajner;
		
		for (int i = 0; i <myList.size(); i++) {
			kontajner= myList.get(i);
			pom=0;
			pom=kontajner.zistiCenu();
			TotalCena+=pom;																							//Uloí celkovú cenu do premennej TotalCena ktorá bude slúi na finalizáciu objednávky
		}
		return TotalCena;
	}
	
	/**
	 * Metóda zistí produkèny èas na vırobu kontajnerov
	 * @param list Aktuálna objednávka
	 * @return Poèet dní
	 */
	public int zistiCas(Objednávka list){																			//Metóda zistí produkènı èas kontajnerov
			
		int sum=0;
		Kontajner kontajner;
		
		for(int i=0;i<list.zistiPoèet();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	/**
	 * Metóda zráta celkovı èas na vırobu ale zaráta tam aj zvolenú triedu
	 * Ak je zvolená prvá trieda tak produkènı èas trvá menej, no pridá sa na cene
	 * @param list Aktuálan objednávka
	 * @return Novı celkovı èas
	 */
	public int zistiPocetDni(Objednávka list){													
		
		int dni=0;
		int tyzdne=0;
		
		if(Trieda.equals("1.")){
			dni = (int) zistiCas(list)/24;																		//volanie metódy zistiCas, ktorá vráti celkovı èas a naslédne sa vydeli konštantou 24 (hod)
			tyzdne=dni/7;
			this.PocetDni+=dni; 																				//Pripoèíta k celkovım dnom vıroby
			dni-=(tyzdne*2);
			return dni;																							//Prvá trieda sa produkuje nonstop 7D/24H, teda aj cez víkend narozdiel od druhej
		}
		else{
			dni = (int) zistiCas(list)/24;
			this.PocetDni+=dni;
			return dni;
			
		}
	}
	
	/**
	 * Metóda zistí poèet dní na vırobu a export
	 * @return Aktuálny poèet dní
	 */
	public int getPocetDni(){
		return this.PocetDni;
	}
	
	/**
	 * Metóda zistí celkovú cenu
	 * @return Celková cena
	 */
	public int getTotalCena(){
		return TotalCena;
	}

	/**
	 * Metóda prida k cene novú èiastku o ve¾kosti delta
	 * 
	 * @param delta Zvıšenie ceny o èiastku delta
	 */
	public void addCena(int delta){																				//Pripoèíta k cene parameter delta, slúi na priadanie ceny ak uivatel zvolil prvú triedu
		TotalCena+=delta;
	}
	
	/**
	 * Metóda nastaví vzdialenos a pridá na cene pod¾a jednotlivıch krajov
	 * @param kraj Zvolenı aktuálny kraj na SR
	 */
	public void setVzdialenost(String kraj){
		
		//Podla kraju sa jednotlivo urèuje vzdialenost
		//Záleí aj od toho cena, jednotlive kraje majú rôzne ceny, koeficienty
		
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
	
	/**
	 * Metóda zistí aktuálny poèet vozidiel
	 * @return Poèet vozidiel
	 */
	public int getCars(){
		return cars.size();
	}
	/**
	 * Metóda zistí aktuálnu vzdialenos
	 * @return Vzdialenos km
	 */
	public int getVzdialenost(){
		return this.Vzdialenost;
	}
	/**
	 * Metóda zistí miesto doruèenia
	 * @return Miesto doruèenia
	 */
	public String getMesto(){
		return Mesto;
	}
}
