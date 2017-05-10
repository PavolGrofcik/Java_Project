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
	 * Author Pavol Grof��k
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private int PocetKontajnerov = 0;
	private int PocetDni=0;
	private int TotalCena=0;		//�
	private int Vzdialenost=0;		//km
	private String Trieda=null;
	private String Kraj=null;
	private String Mesto=null;

	private String[] kupon = {"0000", "1111", "2222", "123456789"};											//Zlavov� kup�n ktor� obsahuje akceptuj�ce hodnoty	kup�nov									
	
	
	private ArrayList<Kontajner> myList = ListCreator.Listcreate();
	private ArrayList <Auto> cars = ListCreator.Listcreate();
	
	
	transient private ArrayList<SledovatelObjednavky> sledovatelia = new ArrayList<>();						//arraylist na uchovov�vanie sledovatelov kontajnerov, neuchov�va sa pri ulo�en� do s�boru
	
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
	
	public void setPocetDni(int num){
		this.PocetDni+=num;
	}
	
	public void setCena(){
		TotalCena = (int) (TotalCena * 0.9);
	}

	//zni�i cenu o 5 %
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
				addCena(pom*300);																					//Vypo���a nov� vozidl� a prid� aj cenu �
			} catch (Nespr�vnyTypVozidla e) {
				
			}
		}
	}
	
	//Dorobi� transport
	
	public int NalozKontajnery(int vzdialenost){
		
		//pomocn� premenn�
		Kontajner kontajner;
		Auto auto;
		Avia avia;
		Kami�n kami�n;
		
		int number=0;
		int pocetVozidiel=0;
		
		
		//Najprv nakalibruje po�et kontajnerov a �ut
		//automatick� prida
		kalibrujPocetKonAndAut();
		
		//pre tuto vzdialenost sa v prvorade vyu�ij� avie, kv�li tomu �e odv�z� menej a maj� aj men�iu r�chlos�
		if (vzdialenost < 200) {
			//aktualiz�cia total pocet dni
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

		} else /* if(vzdialenost>=200) */ {
			//this.PocetDni +=3;
			setPocetDni(3);
			
			if(number!=PocetKontajnerov){
			// uprednostnujeme kami�ny
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
						//PocetDni+= kami�n.ExportTime(vzdialenost);
						//pocetVozidiel++;
						
						if (!kami�n.zistiNaklad()) {
							kami�n.nalozAuto(kontajner);
							number++;
							if(number==PocetKontajnerov){
								pocetVozidiel++;
							}
						}
						else if (kami�n.zistiNaklad()){
							pocetVozidiel++;
							break;
						}
						//if (kami�n.zistiNaklad()) {
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
	
	
	
	
	//Met�da na upresnenie �pecifik�ci�
	public void saveTransport(String vzdialenost, String typZasielky, String mesto){
		
		this.Kraj=vzdialenost;															//Trieda si zapam�t� kraj, ktor� si zvolil z�kazn�k
		this.Mesto=mesto;
		
		switch(typZasielky){
		
		case "1.":	setVzdialenost(vzdialenost);
					this.Trieda=typZasielky;
					addCena(200); 														//Prv� trieda je o 200 � drha�ia kv�li r�chlej�ej produkcii
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
	
	public int rtti(Objedn�vka objedn�vka, String druh){															//RTTI
		
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
	
	public String kontajnerInfo(String typ) throws ObjectNotFound{
		
		Kontajner pomocny=null;
		String temp=null;
		
		for(int i=0; i<myList.size();i++){																		//preh�ad�vanie cel�ho listu objedn�vky
			
			pomocny=myList.get(i);																				//priradenie kontajneru v liste s indexom i do pomocnej premenej, odkial dostaneme po�adovan� inform�cie
			
			//Prehladavanie pommocou n�zvu kontajnera, vyu�it�m funkcie substring ktor� vracia runtime nazov od zac indexu do end indexu, vi� oracle.docs....
			
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
	
	
	public String ObjednavkaInfo(){
		
		//Zobraz� podrobn� inform�cie o 
		return "Mesto: " + Mesto + "\n" + "Vzdialenos� " + Integer.toString(Vzdialenost) +  " km" + "\n" + "Celkov� cena: " + Integer.toString(zistiCenu(this))+ " �" + "\n" + "Produk�n� �as: "
		+ Integer.toString(zistiPocetDni(this)) + " Dn�";
	}
	
	
	
	
	public int zistiPo�et(){																					//vr�ti po�et prvkov v arraliste
		return myList.size();
	}
	
	
	public void zma�(){																							//funkcia zma�e v�etky polo�ky v Arraliste
		if(!myList.isEmpty()){																					//iba ak u� v sebe obsahuje polo�ky
			
			this.PocetKontajnerov=0;																			//Aktualizuje observera - aktu�lny po�et kontajnerov
			this.Vzdialenost=0;
			
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
		
		int dni=0;
		int tyzdne=0;
		
		if(Trieda.equals("1.")){
			addCena(200);
			dni = (int) zistiCas(list)/24;																		//volanie met�dy zisti cas, ktor� vr�ti celkov� �as a nasl�dne sa vydeli kon�tantou 24 aby sa zistil
			tyzdne=dni/7;
			this.PocetDni+=dni; 																				//Pripo��ta k celkov�m dnom v�roby
			dni-=(tyzdne*2);
			return dni;																				//Prv� trieda sa produkuje nonstop 7D/24H, teda aj cez v�kend
		}
		else{
			dni = (int) zistiCas(list)/24;
			this.PocetDni+=dni;
			return dni;
			
		}
	}
	
	//met�da zist� aktu�lny po�et dni
	
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
	public int getVzdialenost(){
		return this.Vzdialenost;
	}
	
}
