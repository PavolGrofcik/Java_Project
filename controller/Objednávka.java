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
import containers.Mraziarensk˝;
import containers.N·drû;
import containers.Transportn˝;
import containers.UbytovacÌ;
import kamiÛny.Auto;
import kamiÛny.Avia;
import kamiÛny.KamiÛn;
import kamiÛny.Prives;

public class Objedn·vka implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int PocetKontajnerov = 0;
	private int PocetDni=0;
	private int TotalCena=0;
	private int Vzdialenost=0;		//km
	private String Trieda=null;
	private String Kraj=null;

	
	
	private ArrayList<Kontajner> myList = ListCreator.Listcreate();
	private ArrayList <Auto> cars = ListCreator.Listcreate();
	
	transient private ArrayList<SledovatelObjednavky> sledovatelia = new ArrayList<>();						//transient arraylist na uchovov·vanie sledovatelov kontajnerov
	
	
	//implicitnÈ nastavenie poËtu vozidiel pri vytvorenÌ objedn·vky
	public Objedn·vka(){
		try {
			addCars(10, "KamiÛn");
			addCars(10, "Avia");
		} catch (Nespr·vnyTypVozidla e) {
		}
	}
	
	
	//Observer -> metÛdy
	
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

	
	//Dorobiù transport

	
	//MetÛda na upresnenie öpecifik·ciÌ
	public void saveTransport(String vzdialenost, String typZasielky){
		this.Kraj=vzdialenost;															//Trieda si zapam‰t· kraj, ktor˝ si zvolil z·kaznÌk
		
		switch(typZasielky){
		case "1.":	setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					break;
		case "2.": setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					break;
		}
		
	}
	
	public void addCars(int pocet, String vozidlo) throws Nespr·vnyTypVozidla{
		
		switch (vozidlo) {
		
		case "Avia":
			for(int i=0;i<pocet;i++){
				cars.add(new Avia());
			}
			cars.trimToSize();
			break;
		case "KamiÛn":
			for(int i=0;i<pocet;i++){
				cars.add(new KamiÛn(new Prives()));
			}
			cars.trimToSize();
			break;
		default:								//V prÌpade ak admin prid· nove vozidla, a nastane typo v type vozidla
			throw new Nespr·vnyTypVozidla();
		}
		
	}
	
	//Pridavanie do listu jednotlivÈ kontajnery
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah) throws NespravnyRozsah		//vyhadzovanie vlastn˝ch v˝nimiek
	{
		if(stage.equals("MrazStage")){
			if (rozsah > 0) {

				switch (kontajner) {

				case "Chladiaci": {
					if (rozsah < 5 || rozsah > 15) {
						throw new NespravnyRozsah(); // Vlastn· v˝nimka
					} else {
						for (int i = 0; i < mnozstvo; i++) {
							myList.add(new Mraziarensk˝("Chladiaci", mnozstvo, rozsah));
							// observer
							PocetKontajnerov++;
						}
					}
				}
					break;
				case "Hlbokomraziarensk˝": {
					if (rozsah > 15 && rozsah < 30) {
						for (int i = 0; i < mnozstvo; i++) {
							// observer
							myList.add(new Mraziarensk˝("Hlbokomraziarensk˝", mnozstvo, rozsah));
							PocetKontajnerov++;
						}
					} else {
						throw new NespravnyRozsah(); // Vlastn· v˝nimka
					}

				}
					break;
				}
			}
			else if(rozsah==0){
				//ak nie je zaökrtnut˝ checkbox, tam som explicitne nastavil rozsah hodnote = 0, tu pouûijem in˝ konötruktor podÂa ComboBox-a
				if (kontajner.equals("Chladiaci")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarensk˝("Chladiaci", mnozstvo));
						// miesto pre observera poËe
						PocetKontajnerov++;
					}
				} else if (kontajner.equals("Hlbokomraziarensk˝")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarensk˝("Hlbokomraziarensk˝", mnozstvo));
						//observer
						PocetKontajnerov++;
					}
				}
			}
		upovedomSledovatelov();
		
		}
		else if (stage.equals("TranStage")) {																	// kontrola pre Transportn˝ kontajner
			
			//Vlastn· V˝nimka Nespravny rozsah
			if(rozsah<2 || rozsah >10){
				throw new NespravnyRozsah();
			}
			else{

				for (int i = 0; i < mnozstvo; i++) {
					myList.add(new Transportn˝(mnozstvo, rozsah, kontajner));
					// observer
					PocetKontajnerov++;
				}

				upovedomSledovatelov();
			}
		}
		else if(stage.equals("NadrûStage")){																	//kontrola pre N·drû (kontajner)
			
			for(int i=0;i<mnozstvo;i++){
				myList.add(new N·drû(mnozstvo, kontajner));
				//observer
				PocetKontajnerov++;
			}
			upovedomSledovatelov();
		}
		myList.trimToSize();
	}
	
	
	
	//metÛda prid· ubytovacÌ kontajner
	public void addUbytovaci(String stage, int mnozstvo, String kontajner, int rozsah, boolean balcon, boolean terrace) throws NespravnyRozsah {
		
		if (stage.equals("UbytStage")) {
			switch (stage) {
			case "UbytStage":
				if ((rozsah < 1 || rozsah > 5) && mnozstvo < 2) {
					throw new NespravnyRozsah();
				} else if (balcon) {
					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new UbytovacÌ(mnozstvo, kontajner, rozsah, balcon, terrace));
						// Observer
						PocetKontajnerov++;
					}
				} else {
					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new UbytovacÌ(mnozstvo, kontajner, rozsah));
						// Observer
						PocetKontajnerov++;
					}
				}
				upovedomSledovatelov();
			}
			myList.trimToSize();
		}
	}
	
	public int rti(Objedn·vka objedn·vka, Kontajner kontajner){															//RTTI
		
		int number=0;
		
		for(int i =0; i<objedn·vka.zistiPoËet();i++){
			Kontajner kontajner2;
			kontajner2=objedn·vka.myList.get(i);				
			if (kontajner2.equals(kontajner)) {
			number++;	
			}
		}
		return number;																							//zapis do suboru number poËet a dan˝ Clas get object?	
	}
	
	
	
	public int zistiPoËet(){																					//vr·ti poËet prvkov v arraliste
		return myList.size();
	}
	
	
	public void zmaû(){																							//funkcia zmaûe vöetky poloûky v Arraliste
		if(!myList.isEmpty()){																					//iba ak uû v sebe obsahuje poloûky
			
			this.PocetKontajnerov=0;																			//Aktualizuje observera - aktu·lny poËet kontajnerov
			
			myList.clear();
			myList.trimToSize();
			upovedomSledovatelov();
		}
	}
	
	
	
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//uloûenie objedn·vky do s˙boru FILE
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
		//Objedn·vka nacitany = (Objedn·vka) in.readObject();
		@SuppressWarnings("unchecked")
		ArrayList<Kontajner> list = (ArrayList<Kontajner>) in.readObject();
		in.close();
		myList=list;																								//NaËÌta serializovan˝ arraylist zo suboru do objedn·vky 
																													//naËÌta objedn·vku (kontajery) excluding observer
		PocetKontajnerov=myList.size();
		upovedomSledovatelov();
		
		myList.trimToSize();
		
		} catch (Exception e) {
			
			throw new ClassNotFoundException();
		}
	}

	public int zistiCenu(Objedn·vka list){	
		//funkcia zisti celkovu cenu kontajnerov
		int sum=0;
		Kontajner kontajner;
		
		for (int i = 0; i <list.myList.size(); i++) {
			kontajner= list.myList.get(i);
			sum+=kontajner.zistiCenu();
			TotalCena+=sum;																							//UloûÌ celkov˙ cenu do premennej TotalCena ktor· bude sl˙ûiù na finaliz·ciu objedn·vky
		}
		return sum;
	}
	
	public int zistiCas(Objedn·vka list){
		//funkcia na zistenie celkovÈho produkËnÈho Ëasu
		int sum=0;
		Kontajner kontajner;
		
		for(int i=0;i<list.zistiPoËet();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	public int zistiPocetDni(Objedn·vka list){
		int dni = (int) zistiCas(list)/24;																		//volanie metÛdy zisti cas, ktor· vr·ti celkov˝ Ëas a naslÈdne sa vydeli konötantou 24 aby sa zistil
		this.PocetDni+=dni; 																					//PripoËÌta k celkov˝m dnom v˝roby
		return dni;	
	}
	
	//metÛda zistÌ aktu·lny poËet dni
	
	public int getPocetDni(){
		return this.PocetDni;
	}
	
	public int getTotalCena(){
		return this.TotalCena;
	}

	public void addCena(int delta){
		this.TotalCena+=delta;
	}
	
	public void setVzdialenost(String kraj){
		
		//podla kraju sa jednotlivo urËuje vzdialenost
		//Z·leûÌ aj od toho cena, jednotlive kraje maj˙ rÙzne ceny
		switch (kraj) {
		case "Bratislavsk˝": this.Vzdialenost=100;
							addCena(100);
			break;
		case "Trnavsk˝": this.Vzdialenost=150;
						 addCena(150);
			break;
		case "Nitriansky": this.Vzdialenost=150;
						   addCena(150);
			break;
		case "TrenËiansky": this.Vzdialenost=200;
							addCena(200);
			break;
		case "Banskobystrick˝": this.Vzdialenost=250;
								addCena(250);
			break;
		case "éilinsk˝": this.Vzdialenost=250;
						 addCena(250);
			break;
		case "Preöovsk˝": this.Vzdialenost=300;
						  addCena(300);
			break;
		case "Koöick˝": this.Vzdialenost=300;
						addCena(300);
			break;
		}
	}
	
	public int getCars(){
		return cars.size();
	}
	
}
