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

public class Objedn�vka implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Kontajner>  myList = new ArrayList<Kontajner>();										//arraylist na uskladnenie objedn�vky  vo�ba z�kaznika ak� kontajnery si zvoli
	
	/*
	transient private List<SledovatelObjednavky> sledovatelia = new ArrayList<>();

	public void pridajSledovatela(SledovatelObjednavky sledovatelStavu) {
		sledovatelia.add(sledovatelStavu);
	}
	
	public void upovedomSledovatelov() {
		for (SledovatelStretu s : sledovatelia)
			s.upovedom();
	}*/
	//rozsah pre nadr� || mraziarensky kontajner
	
	public void addMyList(String stage , int mnozstvo, String kontajner, int rozsah){
		if(stage.equals("MrazStage")){
			if(rozsah>0){
				//ak je za�krtnut� checkbox tak rozsah nastav�me implicitne prostredn�ctvom Textfield2-u
				
				if (kontajner.equals("Chladiaci") && mnozstvo<=15) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarensk�("Chladiaci", mnozstvo, rozsah));
						// miesto pre observera po�et
					}
				} else if (kontajner.equals("Hlbokomraziarensk�") && (rozsah>15 && rozsah<30)) {
						
					for (int i = 0; i < mnozstvo; i++) {
						//observer
						myList.add(new Mraziarensk�("Hlbokomraziarensk�", mnozstvo, rozsah));
					}
				}
			}
			else if(rozsah==0){
				//ak nie je za�krtnut� checkbox, tam som explicitne nastavil rozsah hodnote = 0, tu pou�ijem in� kon�truktor pod�a ComboBox-a
				if (kontajner.equals("Chladiaci")) {

					for (int i = 0; i < mnozstvo; i++) {
						myList.add(new Mraziarensk�("Chladiaci", mnozstvo));
						// miesto pre observera po�et
					}
				} else if (kontajner.equals("Hlbokomraziarensk�")) {

					for (int i = 0; i < mnozstvo; i++) {
						//observer
						myList.add(new Mraziarensk�("Hlbokomraziarensk�", mnozstvo));
					}
				}
				
				
			}
			//kon�� MrazStage
		}
		
		
	}
	
	
	
	
	
	
	
	public void addmyList(int mnozstvo, String kontajner){													//funkcia addmyList ktor� vytvor� dan� po�et kontajnerov pod�a vo�by zakaznika
		
		if(kontajner.equals("Mraziarensk�"))																//vytvorenie kontajnera pod�a n�zvu(checkbox)
		{
		for(int i = 0;i<mnozstvo;i++){
			
		myList.add(new Mraziarensk�(mnozstvo));
		}
		}else if(kontajner.equals("N�dr�")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new N�dr�(mnozstvo));
			}
		}else if(kontajner.equals("Transportn�")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new Transportn�(mnozstvo));
			}
		}else{
			for(int i = 0; i<mnozstvo;i++){
				myList.add(new Ubytovac�(mnozstvo));
			}
		}

		myList.trimToSize();																					//funkcia trimToSize ktor� uprav� arraylist podla po�tu, zmen�� zv���
	}
	
	
	
	public int rti(Objedn�vka objedn�vka, Kontajner kontajner){															//RTTI
		int number=0;
		
		//Iterator<List> iterator = new Iterator<List>() {
		//};
		
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
		if(!myList.isEmpty()){																						//iba ak u� v sebe obsahuje polo�ky
			myList.clear();
		myList.trimToSize();
		}
	}
	
	
	
	public void uloz(File subor) throws ClassNotFoundException, IOException {									//ulo�enie objedn�vky do s�boru FILE
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(subor.getAbsolutePath()));			
		out.writeObject(myList);
		out.close();
	}
	
	public void nacitaj(File subor) throws ClassNotFoundException,IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(subor.getAbsolutePath()));
		Objedn�vka nacitany = (Objedn�vka) in.readObject();
		in.close();
	}

	public int zistiCenu(Objedn�vka list){																		//funkcia zisti celkovu cenu kontajnerov
		int sum=0;
		Kontajner kontajner;
		for (int i = 0; i <list.myList.size(); i++) {
			kontajner= list.myList.get(i);
			sum+=kontajner.zistiCenu();
		}
		return sum;
	}
	
	public int zistiCas(Objedn�vka list){																		//funkcia na zistenie celkov�ho produk�n�ho �asu
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
		 																										//po�et dn�
		return dni;	
	}

	
}
