package containers;

import java.util.ArrayList;

public class Arraylist {

	private ArrayList<Kontajner>  myList = new ArrayList<Kontajner>();								//arraylist na uskladnenie objednávky // vo¾ba zákaznika 
	
	
	public void addmyList(int mnozstvo, String kontajner){											//funkcia addmyList ktorá vytvorí daný poèet kontajnerov pod¾a vo¾by
		
		if(kontajner.equals("Mraziarenský"))
		{
		for(int i = 0;i<mnozstvo;i++){
			
		myList.add(new Mraziarenský());
		}
		}else if(kontajner.equals("Multifunkèný")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new Multifunkèný());
			}
		}else if(kontajner.equals("Transportný")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new Transportný());
			}
		}else{
			for(int i = 0; i<mnozstvo;i++){
				myList.add(new Ubytovací());
			}
		}
		myList.trimToSize();
	}
	
	public int zistipoèet(){
		return myList.size();
	}
	
	
	public void zmaž(){																					//funkcia zmaže všetky položky v Arraliste
		if(!myList.isEmpty()){
		//iba ak už v sebe obsahuje položky
		myList.removeAll(myList);
		myList.trimToSize();
		}
	}
	
}
