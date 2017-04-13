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
	private ArrayList<Kontajner>  myList = new ArrayList<Kontajner>();										//arraylist na uskladnenie objednávky  vo¾ba zákaznika aké kontajnery si zvoli
	
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
	
	
	
	public int zistiPoèet(){																					//vráti poèet prvkov v arraliste
		return myList.size();
	}
	
	
	public void zmaž(){																							//funkcia zmaže všetky položky v Arraliste
		if(!myList.isEmpty()){
																												//iba ak už v sebe obsahuje položky
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
	
	public int zistiCas(Arraylist list){																		//funkcia na zistenie celkového produkèného èasu
		int sum=0;
		Kontajner kontajner;
		for(int i=0;i<list.zistiPoèet();i++){
			kontajner=list.myList.get(i);
			sum+= kontajner.zistiCas();
		}
		return sum;
	}
	
	public int zistiPocetDni(Arraylist list){
	
		int dni = (int) zistiCas(list)/24;																		//volanie metódy zisti cas, ktorá vráti celkový èas a naslédne sa vydeli konštantou 24 aby sa zistil
		 																										//poèet dní
		return dni;	
	}

	
}
