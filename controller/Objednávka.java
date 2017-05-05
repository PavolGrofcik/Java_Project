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

public class Objedn�vka implements Serializable{
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
	
	transient private ArrayList<SledovatelObjednavky> sledovatelia = new ArrayList<>();						//transient arraylist na uchovov�vanie sledovatelov kontajnerov
	
	
	//implicitn� nastavenie po�tu vozidiel pri vytvoren� objedn�vky
	public Objedn�vka(){
		try {
			addCars(10, "Kami�n");
			addCars(10, "Avia");
		} catch (Nespr�vnyTypVozidla e) {
		}
	}
	
	
	//Observer -> met�dy
	
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

	
	//Dorobi� transport

	
	//Met�da na upresnenie �pecifik�ci�
	public void saveTransport(String vzdialenost, String typZasielky){
		this.Kraj=vzdialenost;															//Trieda si zapam�t� kraj, ktor� si zvolil z�kazn�k
		
		switch(typZasielky){
		case "1.":	setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					break;
		case "2.": setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					break;
		}
		
	}
	
	public void addCars(int pocet, String vozidlo) throws Nespr�vnyTypVozidla{
		
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
		default:								//V pr�pade ak admin prid� nove vozidla, a nastane typo v type vozidla
			throw new Nespr�vnyTypVozidla();
		}
		
	}
	
	//Pridavanie do listu jednotliv� kontajnery
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah) throws NespravnyRozsah		//vyhadzovanie vlastn�ch v�nimiek
	{
		if(stage.equals("MrazStage")){
			if (rozsah > 0) {

				switch (kontajner) {

				case "Chladiaci": {
					if (rozsah < 5 || rozsah > 15) {
						throw new NespravnyRozsah(); // Vlastn� v�nimka
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
		else if (stage.equals("TranStage")) {																	// kontrola pre Transportn� kontajner
			
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
	
	
	
	//met�da prid� ubytovac� kontajner
	public void addUbytovaci(String stage, int mnozstvo, String kontajner, int rozsah, boolean balcon, boolean terrace) throws NespravnyRozsah {
		
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
	
	public int rti(Objedn�vka objedn�vka, Kontajner kontajner){															//RTTI
		
		int number=0;
		
		for(int i =0; i<objedn�vka.zistiPo�et();i++){
			Kontajner kontajner2;
			kontajner2=objedn�vka.myList.get(i);				
			if (kontajner2.equals(kontajner)) {
			number++;	
			}
		}
		return number;																							//zapis do suboru number po�et a dan� Clas get object?	
	}
	
	
	
	public int zistiPo�et(){																					//vr�ti po�et prvkov v arraliste
		return myList.size();
	}
	
	
	public void zma�(){																							//funkcia zma�e v�etky polo�ky v Arraliste
		if(!myList.isEmpty()){																					//iba ak u� v sebe obsahuje polo�ky
			
			this.PocetKontajnerov=0;																			//Aktualizuje observera - aktu�lny po�et kontajnerov
			
			myList.clear();
			myList.trimToSize();
			upovedomSledovatelov();
		}
	}
	
	
	
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//ulo�enie objedn�vky do s�boru FILE
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
		//Objedn�vka nacitany = (Objedn�vka) in.readObject();
		@SuppressWarnings("unchecked")
		ArrayList<Kontajner> list = (ArrayList<Kontajner>) in.readObject();
		in.close();
		myList=list;																								//Na��ta serializovan� arraylist zo suboru do objedn�vky 
																													//na��ta objedn�vku (kontajery) excluding observer
		PocetKontajnerov=myList.size();
		upovedomSledovatelov();
		
		myList.trimToSize();
		
		} catch (Exception e) {
			
			throw new ClassNotFoundException();
		}
	}

	public int zistiCenu(Objedn�vka list){	
		//funkcia zisti celkovu cenu kontajnerov
		int sum=0;
		Kontajner kontajner;
		
		for (int i = 0; i <list.myList.size(); i++) {
			kontajner= list.myList.get(i);
			sum+=kontajner.zistiCenu();
			TotalCena+=sum;																							//Ulo�� celkov� cenu do premennej TotalCena ktor� bude sl��i� na finaliz�ciu objedn�vky
		}
		return sum;
	}
	
	public int zistiCas(Objedn�vka list){
		//funkcia na zistenie celkov�ho produk�n�ho �asu
		int sum=0;
		Kontajner kontajner;
		
		for(int i=0;i<list.zistiPo�et();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	public int zistiPocetDni(Objedn�vka list){
		int dni = (int) zistiCas(list)/24;																		//volanie met�dy zisti cas, ktor� vr�ti celkov� �as a nasl�dne sa vydeli kon�tantou 24 aby sa zistil
		this.PocetDni+=dni; 																					//Pripo��ta k celkov�m dnom v�roby
		return dni;	
	}
	
	//met�da zist� aktu�lny po�et dni
	
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
		
		//podla kraju sa jednotlivo ur�uje vzdialenost
		//Z�le�� aj od toho cena, jednotlive kraje maj� r�zne ceny
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
	
	public int getCars(){
		return cars.size();
	}
	
}
