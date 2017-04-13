package containers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Arraylist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Kontajner>  myList = new ArrayList<Kontajner>();										//arraylist na uskladnenie objedn�vky  vo�ba z�kaznika ak� kontajnery si zvoli
	
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
	
	
	
	public int zistiPo�et(){																					//vr�ti po�et prvkov v arraliste
		return myList.size();
	}
	
	
	public void zma�(){																							//funkcia zma�e v�etky polo�ky v Arraliste
		if(!myList.isEmpty()){
																												//iba ak u� v sebe obsahuje polo�ky
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
		Arraylist nacitany = (Arraylist) in.readObject();
		in.close();
	}

	public int zistiCenu(Arraylist list){																		//funkcia zisti celkovu cenu kontajnerov
		int sum=0;
		Kontajner kontajner;
		for (int i = 0; i <list.myList.size(); i++) {
			kontajner= list.myList.get(i);
			sum+=kontajner.zistiCenu();
		}
		return sum;
	}
	
	public int zistiCas(Arraylist list){																		//funkcia na zistenie celkov�ho produk�n�ho �asu
		int sum=0;
		Kontajner kontajner;
		for(int i=0;i<list.zistiPo�et();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	public int zistiPocetDni(Arraylist list){
	
		int dni = (int) zistiCas(list)/24;																		//volanie met�dy zisti cas, ktor� vr�ti celkov� �as a nasl�dne sa vydeli kon�tantou 24 aby sa zistil
		 																										//po�et dn�
		return dni;	
	}

	
}
