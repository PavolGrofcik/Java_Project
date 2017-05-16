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
import containers.Mraziarensk�;
import containers.N�dr�;
import containers.Transportn�;
import containers.Ubytovac�;
import kami�ny.Auto;
import kami�ny.Avia;
import kami�ny.Kami�n;
import kami�ny.Prives;

/**
 * 
 * @author Pavol Grof��k
 * Kontroler Hlavn� trieda z ktorej sa riadi cel� proces objednania a� po transport
 *
 */

public class Objedn�vka implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * �pecifick� atrib�ty sl��ia na upresnenie objedn�vky
	 */
	private int PocetKontajnerov = 0;
	private int PocetDni=0;			//dni
	private int TotalCena=0;		//�
	private int Vzdialenost=0;		//km
	private String Trieda=null;		//typ tried 1/2
	private String Kraj=null;
	private String Mesto=null;

	private String[] kupon = {"0000", "1111", "2222", "3333"};													//Zlavov� kup�n, ktor� obsahuje akceptuj�ce hodnoty r�znych hdn�t									
	
	
	private ArrayList<Kontajner> myList = ListCreator.Listcreate();
	private ArrayList <Auto> cars = ListCreator.Listcreate();
	
	
	transient private ArrayList<SledovatelObjednavky> sledovatelia = new ArrayList<>();							//Arraylist na uchovov�vanie sledovatelov kontajnerov, neuchov�va sa pri ulo�en� do s�boru
	
	
	/**
	 * Kon�truktor, defaultne sa prid� do List-u vozidiel 20 �ut
	 */
	public Objedn�vka(){
		
		try {
			addCars(10, "Kami�n");																				//Default nastav� 20 vozidiel
			addCars(10, "Avia");
		} catch (Nespr�vnyTypVozidla e) {
		}
	}
	
	//Observer -> met�dy
	/**
	 * Met�da sl��iaca na pridanie Sledovate�a
	 * @param sledovatelObjednavky Sledovate� sleduje dan� po�et
	 */
	public void pridajSledovatela(SledovatelObjednavky sledovatelObjednavky) {									//Prid� sledovate�a objedn�vky
		sledovatelia.add(sledovatelObjednavky);
	}

	/**
	 * Met�da aktualizuje s��asn�ch observerov
	 */
	public void upovedomSledovatelov() {																		//Upovedom� observera
		
		for (SledovatelObjednavky s : sledovatelia) {
			s.upovedom();
		}
	}

	public int getPocetKontajnerov() {																			//Zist� aktu�lny po�et kontajnerov v objedn�vke
		return this.PocetKontajnerov;
	}
	
	public void setPocetDni(int num){																			//Nastav� po�et dn�
		this.PocetDni+=num;
	}
	/**
	 * Meto�da uplatn� z�avov� kup�n pri zhode znakov
	 * @param kupon Re�azec obsahuj�ci val�dne znaky kup�nu
	 * @return	Nov� cena zn�en� o 5%
	 */
	public int zlavovyKupon(String kupon){																		//Met�da uplatn� zlavov� kup�n
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
	 * Met�da nakalibruje po�et �ut pri v���ch objedn�vkach ako 30 kontajnerov
	 * No vypo�i�an� vozidl� sa neprid�vaj� do list-u vozidiel firmy
	 */
	public void kalibrujPocetKonAut(){																			//Met�da nakalibruje po�et a�t a vypo�i�ia ich 
		if(PocetKontajnerov>30){
			int pom = PocetKontajnerov-30;
			try {
				addCars(pom, "Avia");
				addCena(pom*400);																				//Vypo���a nov� vozidl� a prid� aj cenu �, av�ak neprid�va do listu vozidiel
			} catch (Nespr�vnyTypVozidla e) {
				
			}
		}
	}
	
	/**
	 * Met�da naklad� kontajnery na vozidl� pod�a vzdialenosti trasy, zar�ta exportn� dobu prepravy a nalo�� prioritne, bu� Avie alebo Kami�ny
	 * @param vzdialenost Dl�ka trasy v km
	 * @return	Aktu�lny nalo�en� po�et vozidiel
	 */
	public int NalozKontajnery(int vzdialenost){
		
																													//Pomocn� premenn�
		Kontajner kontajner;
		Auto auto;
		Avia avia;
		Kami�n kami�n;
		
		int number=0;
		int pocetVozidiel=0;
		
		
																													//Najprv nakalibruje po�et kontajnerov a �ut
																													//automatick� vypo�i�a nov� vozidl�
		kalibrujPocetKonAut();
		
																													//Pre tuto vzdialenost sa v prvorade vyu�ij� avie, kv�li tomu �e odvez� menej a maj� aj men�iu r�chlos�
		if (vzdialenost < 200) {
																													//Aktualiz�cia pocet dni
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

						if (auto instanceof Kami�n) {
							
							kami�n = (Kami�n) auto;
																									//PocetDni+= kami�n.ExportTime(vzdialenost);
							if (!kami�n.zistiNalozeny()) {
								kami�n.nalozAuto(kontajner);
								number++;
								if(number==PocetKontajnerov){
									pocetVozidiel++;
								}
								
							} else if (kami�n.zistiNaklad()){
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

		} else  {																					//Ak je vzdialenost v��ia ako 200 km priorita s� kami�ny s pr�vesmi, ak kami�ny u� nebud� sta�it nakladaj� sa aj Avie
			
			setPocetDni(3);																			//Nastav� celkov� �as na v�robu aj s exportom
			
			if (number != PocetKontajnerov) {
																									//Uprednostnujeme kami�ny
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
						if (auto instanceof Kami�n) {
							kami�n = (Kami�n) auto;
							
							if (!kami�n.zistiNaklad()) {
								kami�n.nalozAuto(kontajner);
								number++;
																									//Nakladaj� sa kami�ny, vid prekon�vaj�cu met�du
								if (number == PocetKontajnerov) {
									pocetVozidiel++;
								}
							} else if (kami�n.zistiNaklad()) {
								pocetVozidiel++;
								break;
							}
						} else {
							break;
						}

					}
				}

			}
			
																									//Nakladaj� sa Avie, preto�e po�et kontajnerov prev��il kami�ny
			 if(number!=PocetKontajnerov){
				 																					// Kami�ny sa vy�erpali, treba nalo�i� Avie
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
							
																									//Kontroluje sa �i su avie nalo�ene ak nie tak sa nalozia
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
	 * Met�da nastav� typ z�sielky a prida cenu v z�vislosti od vzdialenosti doru�enia objedn�vky
	 * @param vzdialenost Zvolen� kraj na SR
	 * @param typZasielky Prv� alebo druh� trieda
	 * @param mesto	Mesto v danom kraji, bli��ia �pecifik�cia
	 */
	public void saveTransport(String vzdialenost, String typZasielky, String mesto){							//Met�da na upresnenie �pecifik�ci� o transporte
		
		this.Kraj=vzdialenost;																					//Trieda si zapam�t� kraj, ktor� si zvolil z�kazn�k
		this.Mesto=mesto;
		
		switch(typZasielky){
		
		case "1.":	setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					addCena(200); 																				//Prv� trieda je o 200 � drah�ia kv�li r�chlej�ej produkcii a exportu
					break;
		case "2.": setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					break;
		}
		
	}
	
	/**
	 * Meto�da prid�va nove druhy vozidiel do ArrayListu, vi� AdminMode
	 * @param pocet	Po�et pridan�ch vozidiel
	 * @param vozidlo Typ vozidla
	 * @throws Nespr�vnyTypVozidla Vlastn� v�nimka pri nezhode typu vozidla
	 */
	public void addCars(int pocet, String vozidlo) throws Nespr�vnyTypVozidla{									//Prid� v AdminMode nov� vozidla do listu, sl��i nato ak firma zak�pi nov� vozidl�
		
		switch (vozidlo) {
		
		case "Avia":
			for(int i=0;i<pocet;i++){
				cars.add(new Avia());
			}
			cars.trimToSize();
			break;
		case "Kami�n":
			for(int i=0;i<pocet;i++){
				cars.add(new Kami�n(new Prives()));
			}
			cars.trimToSize();
			break;
		default:																								//V pr�pade ak admin prid� nove vozidla, a nastane chyba  v type vozidla
			throw new Nespr�vnyTypVozidla();
		}
		
	}
	
	/**
	 * Met�da prid�va zvolen� typy kontajnerov do objedn�vky, Trieda ubytovac� m� vytvoren� vlastn� �peci�lnu funkciu
	 * @param stage	N�zov okna (stage)
	 * @param mnozstvo	Po�et zvolen�ch kontajnerov
	 * @param kontajner	Konkr�tny typ kontajnera
	 * @param rozsah Rozsah h�dn�t - small, medium big
	 * @throws NespravnyRozsah	Vlastn� v�nimka sl��iaca na vyhodenie pri nespr�vnom zadanom rozsahu
	 */
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah) throws NespravnyRozsah		//Pridavanie do listu jednotliv� kontajnery, s vyhadzovan�m vlastn�ch v�nimiek
	{
		if(stage.equals("MrazStage")){
			if (rozsah > 0) {

				switch (kontajner) {

				case "Chladiaci": {
					if (rozsah < 5 || rozsah > 15) {
						throw new NespravnyRozsah(); 															// Vlastn� v�nimka
					} else {
						for (int i = 0; i < mnozstvo; i++) {
							myList.add(new Mraziarensk�("Chladiaci", mnozstvo, rozsah));
							// observer
							PocetKontajnerov++;
						}
					}
				}
					break;
				case "Hlbokomraziarensk�": {
					if (rozsah > 15 && rozsah < 30) {
						for (int i = 0; i < mnozstvo; i++) {
							// observer
							myList.add(new Mraziarensk�("Hlbokomraziarensk�", mnozstvo, rozsah));
							PocetKontajnerov++;
						}
					} else {
						throw new NespravnyRozsah(); // Vlastn� v�nimka
					}

				}
					break;
				}
			}
			else if(rozsah==0){
				//ak nie je za�krtnut� checkbox, tam som explicitne nastavil rozsah hodnote = 0, tu pou�ijem in� kon�truktor pod�a ComboBox-a
				if (kontajner.equals("Chladiaci")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarensk�("Chladiaci", mnozstvo));
						// miesto pre observera po�e
						PocetKontajnerov++;
					}
				} else if (kontajner.equals("Hlbokomraziarensk�")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarensk�("Hlbokomraziarensk�", mnozstvo));
						//observer
						PocetKontajnerov++;
					}
				}
			}
		upovedomSledovatelov();
		
		}
		else if (stage.equals("TranStage")) {																	// Otestuje stage  pre Transportn� kontajner
			//Vlastn� V�nimka Nespravny rozsah
			if(rozsah<2 || rozsah >10){
				throw new NespravnyRozsah();
			}
			else{

				for (int i = 0; i < mnozstvo; i++) {
					myList.add(new Transportn�(mnozstvo, rozsah, kontajner));
					// observer
					PocetKontajnerov++;
				}

				upovedomSledovatelov();
			}
		}
		else if(stage.equals("Nadr�Stage")){																	//kontrola pre N�dr� (kontajner)
			
			for(int i=0;i<mnozstvo;i++){
				myList.add(new N�dr�(mnozstvo, kontajner));
				//observer
				PocetKontajnerov++;
			}
			upovedomSledovatelov();
		}
		myList.trimToSize();
	}
	
	
	
	/**
	 * 
	 * @param stage N�zov okna (stage)
	 * @param mnozstvo Po�et zvolen�ch kontajnerov
	 * @param kontajner Konkr�tny typ kontajnera
	 * @param rozsah Rozsah h�dn�t - small, medium big
	 * @param balcon Boolean ak u�ivate� zvolil mo�nos� Balcon
	 * @param terrace Boolean ak u��vate� zvoli s Balconom mo�nos� aj terasu
	 * @throws NespravnyRozsah	Vyhodenie vlastnej v�nimky pri nadmernom po�te okien pripadaj�cich na jeden kontajner viac ako 5, z�porn� �isla
	 */
	public void addUbytovaci(String stage, int mnozstvo, String kontajner, int rozsah, boolean balcon, boolean terrace) throws NespravnyRozsah {		//Met�da ur�ena len pre Ubytovac� kontajner, zlo�itej�ia kv�li agreg�cii
		
		if (stage.equals("UbytStage")) {
			switch (stage) {
			case "UbytStage":
				if ((rozsah < 1 || rozsah > 5) && mnozstvo < 2) {
					throw new NespravnyRozsah();
					
				} else if (balcon) {
					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Ubytovac�(mnozstvo, kontajner, rozsah, balcon, terrace));
						// Observer
						PocetKontajnerov++;
					}
				} else {
					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Ubytovac�(mnozstvo, kontajner, rozsah));
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
	 * RTTI bonus, sl��i na zobrazenie polo�iek v objedn�vke
	 * @param objedn�vka Zvolen� objedn�vka
	 * @param druh	Typ kontajnera sl��iaci na preh�ad�vanie v zozname objedn�vky
	 * @return Akut�lny po�et zvolen�ch typov kontajnera
	 */
	public int rtti(Objedn�vka objedn�vka, String druh){															//RTTI, sl��i na zobrazenie polo�iek  v ko��ku, vi� posledn� stage
		
		int number=0;
		Kontajner pomocny;
		
		for(int i =0; i<objedn�vka.zistiPo�et();i++){
			
			pomocny=objedn�vka.myList.get(i);
			
			switch (druh) {
			case "Ubytovac�":
				if (pomocny instanceof Ubytovac�){
					number++;
					}
				break;
			case "Transportn�":
				if(pomocny instanceof Transportn�){
					number++;
				}
				break;
			case "Mraziarensk�":
				if(pomocny instanceof Mraziarensk�){
					number++;
				}
				break;
			case "N�dr�":
				if (pomocny instanceof N�dr�) {
					number++;
				}
				break;
			}
		}
		return number;																							//vr�ti aktu�lny po�et kontajnerov dan�ho typu
	}
	
	/**
	 * Met�da zobraz� aktu�lne inform�cie o danom type kontajnera
	 * @param typ Typ kontajnera
	 * @return Inform�cie o zvolenom type kontajnera
	 * @throws ObjectNotFound Vyhodenie vlastnej v�nimky ak sa kontajner nenach�dza v objedn�vke
	 */
	public String kontajnerInfo(String typ) throws ObjectNotFound{												//Met�da zobraz� aktu�lne inform�cie o zvolenom kontajneri
		
		Kontajner pomocny=null;
		String temp=null;
		
		for(int i=0; i<myList.size();i++){																		//preh�ad�vanie cel�ho listu objedn�vky
			
			pomocny=myList.get(i);																				//priradenie kontajneru v liste s indexom i do pomocnej premenej, odkial dostaneme po�adovan� inform�cie
			
			//Prehladavanie pomocou n�zvu kontajnera, vyu�it�m funkcie substring ktor� vracia runtime nazov od zac indexu do end indexu, vi� oracle.docs....
			
			//N�dr�
			if(pomocny.toString().substring(11, 16).equals(typ)){
				temp= "Hmotnos�: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Z�ruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " �\n";
				return temp;
			}
			//Transportn�
			else if(pomocny.toString().substring(11, 22).equals(typ)){
				temp= "Hmotnos�: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Z�ruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " �\n";
				return temp;
			}
			//Ubytovac�
			else if(pomocny.toString().substring(11, 20).equals(typ)){
				temp= "Hmotnos�: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Z�ruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " �\n";
				return temp;
			}
			//Mraziarensk�
			else if(pomocny.toString().substring(11, 23).equals(typ)){
				temp= "Hmotnos�: " + Integer.toString(pomocny.zistiHmotnost()) + " kg\n"+ "Z�ruka: " + Integer.toString(pomocny.zistiZaruku()) +  " rokov" + "\n" + "Cena: " + Integer.toString(pomocny.zistiCenu()) + " �\n";
				return temp;
			}
		}
		throw new ObjectNotFound();																				//Ak sa zvolen� typ kontajnera nenach�dza v arraliste, vyhod� sa vlastn� v�nimka
	}
	
	/**
	 * Zobrazia sa inform�cie o mieste doru�enia, celkovej cene a produk�nom �ase kontajnerov
	 * @return Aktu�lne inform�cie o objedn�vke
	 */
	public String ObjednavkaInfo(){
		
		//Zobraz� podrobn� inform�cie o objedn�vke
		return "************\nMesto: " + Mesto + "\n" + "Vzdialenos�: " + Integer.toString(Vzdialenost) + " km" + "\n" + "Trieda: " + Trieda + "\n" + "Celkov� �as: " + Integer.toString(zistiPocetDni(this)) + " Dn�" + "\n" +
				"Celkov� cena: " + Integer.toString(zistiCenu())+ " �" + "\n";
	}
	
	
	
	/**
	 * Zist� po�et objednan�ch kontajnerov
	 * @return Aktu�lny po�et kontajnerov
	 */
	public int zistiPo�et(){																					//vr�ti po�et kontajnerov v arrayliste
		return myList.size();
	}
	
	/**
	 * Met�da zma�e cel� objedn�vku
	 */
	public void zma�(){																							//funkcia zma�e v�etky polo�ky v Arraliste
		if(!myList.isEmpty()){																					//iba ak u� v sebe obsahuje polo�ky
			
			this.PocetKontajnerov=0;																			//Aktualizuje observera - aktu�lny po�et kontajnerov
			this.Vzdialenost=0;
			
			myList.clear();
			myList.trimToSize();
			upovedomSledovatelov();
		}
	}
	
	
	/**
	 * Met�da ulo�� objedn�vku do s�boru
	 * @param subor Aktu�lny s�bor
	 * @throws ClassNotFoundException V�nimka
	 * @throws IOException V�nimka
	 */
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//ulo�enie objedn�vky do s�boru FILE
		try{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(subor.getAbsolutePath()));
		out.writeObject(myList);
		out.close();
		} catch (Exception e) {
			throw new IOException();
		}
	}
	/**
	 * Met�da na��ta zo s�boru objedn�vku
	 * @param subor Na��tan� s�bor
	 * @throws ClassNotFoundException Vyhodenie v�nimky ak sa �iaden s�bor nena��tal
	 * @throws IOException Vyhodenie v�nimky sp�sobenej vstupom/v�stupom
	 */
	public void nacitaj(File subor) throws ClassNotFoundException,IOException {
		
		try{
			
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(subor.getAbsolutePath()));
		//Objedn�vka nacitany = (Objedn�vka) in.readObject();
		@SuppressWarnings("unchecked")
		ArrayList<Kontajner> list = (ArrayList<Kontajner>) in.readObject();
		in.close();
	myList=list;																									//Na��ta serializovan� arraylist zo suboru do objedn�vky 
																													//na��ta objedn�vku (kontajery) excluding observer
		PocetKontajnerov=myList.size();
		upovedomSledovatelov();
		
		myList.trimToSize();
		
		} catch (Exception e) {
			
			throw new ClassNotFoundException();
		}
	}

	/**
	 * Met�da spo��ta celkov� ceny kontajnerov
	 * @return Celkov� cena kontajnerov
	 */
	private int zistiCenu(){	
		//funkcia zisti celkovu cenu kontajnerov
		int pom=0;
		Kontajner kontajner;
		
		for (int i = 0; i <myList.size(); i++) {
			kontajner= myList.get(i);
			pom=0;
			pom=kontajner.zistiCenu();
			TotalCena+=pom;																							//Ulo�� celkov� cenu do premennej TotalCena ktor� bude sl��i� na finaliz�ciu objedn�vky
		}
		return TotalCena;
	}
	
	/**
	 * Met�da zist� produk�ny �as na v�robu kontajnerov
	 * @param list Aktu�lna objedn�vka
	 * @return Po�et dn�
	 */
	public int zistiCas(Objedn�vka list){																			//Met�da zist� produk�n� �as kontajnerov
			
		int sum=0;
		Kontajner kontajner;
		
		for(int i=0;i<list.zistiPo�et();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	/**
	 * Met�da zr�ta celkov� �as na v�robu ale zar�ta tam aj zvolen� triedu
	 * Ak je zvolen� prv� trieda tak produk�n� �as trv� menej, no prid� sa na cene
	 * @param list Aktu�lan objedn�vka
	 * @return Nov� celkov� �as
	 */
	public int zistiPocetDni(Objedn�vka list){													
		
		int dni=0;
		int tyzdne=0;
		
		if(Trieda.equals("1.")){
			dni = (int) zistiCas(list)/24;																		//volanie met�dy zistiCas, ktor� vr�ti celkov� �as a nasl�dne sa vydeli kon�tantou 24 (hod)
			tyzdne=dni/7;
			this.PocetDni+=dni; 																				//Pripo��ta k celkov�m dnom v�roby
			dni-=(tyzdne*2);
			return dni;																							//Prv� trieda sa produkuje nonstop 7D/24H, teda aj cez v�kend narozdiel od druhej
		}
		else{
			dni = (int) zistiCas(list)/24;
			this.PocetDni+=dni;
			return dni;
			
		}
	}
	
	/**
	 * Met�da zist� po�et dn� na v�robu a export
	 * @return Aktu�lny po�et dn�
	 */
	public int getPocetDni(){
		return this.PocetDni;
	}
	
	/**
	 * Met�da zist� celkov� cenu
	 * @return Celkov� cena
	 */
	public int getTotalCena(){
		return TotalCena;
	}

	/**
	 * Met�da prida k cene nov� �iastku o ve�kosti delta
	 * 
	 * @param delta Zv��enie ceny o �iastku delta
	 */
	public void addCena(int delta){																				//Pripo��ta k cene parameter delta, sl��i na priadanie ceny ak u�ivatel zvolil prv� triedu
		TotalCena+=delta;
	}
	
	/**
	 * Met�da nastav� vzdialenos� a prid� na cene pod�a jednotliv�ch krajov
	 * @param kraj Zvolen� aktu�lny kraj na SR
	 */
	public void setVzdialenost(String kraj){
		
		//Podla kraju sa jednotlivo ur�uje vzdialenost
		//Z�le�� aj od toho cena, jednotlive kraje maj� r�zne ceny, koeficienty
		
		switch (kraj) {
		case "Bratislavsk�": this.Vzdialenost=100;
							addCena(100);
			break;
		case "Trnavsk�": this.Vzdialenost=150;
						 addCena(150);
			break;
		case "Nitriansky": this.Vzdialenost=150;
						   addCena(150);
			break;
		case "Tren�iansky": this.Vzdialenost=200;
							addCena(200);
			break;
		case "Banskobystrick�": this.Vzdialenost=250;
								addCena(250);
			break;
		case "�ilinsk�": this.Vzdialenost=250;
						 addCena(250);
			break;
		case "Pre�ovsk�": this.Vzdialenost=300;
						  addCena(300);
			break;
		case "Ko�ick�": this.Vzdialenost=300;
						addCena(300);
			break;
		}
	}
	
	/**
	 * Met�da zist� aktu�lny po�et vozidiel
	 * @return Po�et vozidiel
	 */
	public int getCars(){
		return cars.size();
	}
	/**
	 * Met�da zist� aktu�lnu vzdialenos�
	 * @return Vzdialenos� km
	 */
	public int getVzdialenost(){
		return this.Vzdialenost;
	}
	/**
	 * Met�da zist� miesto doru�enia
	 * @return Miesto doru�enia
	 */
	public String getMesto(){
		return Mesto;
	}
}
