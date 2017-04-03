package containers;

import java.util.ArrayList;

public class Arraylist {

	private ArrayList<Kontajner>  myList = new ArrayList<Kontajner>();								//arraylist na uskladnenie objedn�vky // vo�ba z�kaznika 
	
	
	public void addmyList(int mnozstvo, String kontajner){											//funkcia addmyList ktor� vytvor� dan� po�et kontajnerov pod�a vo�by
		
		if(kontajner.equals("Mraziarensk�"))
		{
		for(int i = 0;i<mnozstvo;i++){
			
		myList.add(new Mraziarensk�());
		}
		}else if(kontajner.equals("Multifunk�n�")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new Multifunk�n�());
			}
		}else if(kontajner.equals("Transportn�")){
			for(int i = 0;i<mnozstvo;i++){
				myList.add(new Transportn�());
			}
		}else{
			for(int i = 0; i<mnozstvo;i++){
				myList.add(new Ubytovac�());
			}
		}
		myList.trimToSize();
	}
	
	public int zistipo�et(){
		return myList.size();
	}
	
	
	public void zma�(){																					//funkcia zma�e v�etky polo�ky v Arraliste
		if(!myList.isEmpty()){
		//iba ak u� v sebe obsahuje polo�ky
		myList.removeAll(myList);
		myList.trimToSize();
		}
	}
	
}
