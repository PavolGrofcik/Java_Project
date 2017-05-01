package containers;

public class Transportný extends Kontajner implements Atributy {
	
	private final int hmotnost = 1000;			//kg
	private  int zaruka =5;						//roky

	private int nosnost;						//kg
	private String name;						//typ Big alebo Medium
	
	public Transportný(int num){
		nastavCenu(num);
		nastavProdT(num);
	}
	
	
	//overloading
	public Transportný(int num, String nazov){
		this.nosnost=num;
		this.name=nazov;
		if(name.equals("Medium")){
			
			nastavCenu(num);							//Error prerobit u Transportneho::::::::::::::::::::::::::::::::::::
			nastavProdT(num);							//Rovnako prerobit:::::::::::::::::::::::::::::::::::
		}
		else if(name.equals("Big")){
			
			nastavCenu(3*num);						//záleží aj akú velkos má daný kontajner Big || Medium
			nastavProdT(3*num);
		}
	}
	
	public int zistiZaruku() {
		return this.zaruka;
	}

	public void nastavProdT(int mnozstvo){										//podla množstva sa urèuje aj èas na produkciu jednotlivých kontajnerov, èím viac tým rýchlejšie
		if (mnozstvo>10) {
			this.prodtime=50;
		}
		else {
			this.prodtime=70;
		}
	}

	@Override
	public void nastavCenu(int mnozstvo) {										//poskytuje zlavu 33% ak objednávka presahuje viac ako 10 položiek
		if (mnozstvo>10) {
			this.cena=1000;												
		}
		else
		this.cena=1500;		
	}
	

	public int zistiHmotnost(){
		return this.hmotnost;
	}
	
	public void nastavNosnost(int vaha){
		if (vaha<5000) {
			this.nosnost=vaha;
		}
	}
	
	public int zistiNosnost(){
		return this.nosnost;
	}


}
