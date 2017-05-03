package containers;

public class Transportný extends Kontajner implements Atributy {
	
	private final int hmotnost = 1000;			//kg
	private  int zaruka =5;						//roky

	private int nosnost;						//kg
	private String name;						//typ Big alebo Medium
	
	//Constructor
	public Transportný(int num, int range, String size){
		
		this.nosnost=range;
		this.name=size;
		
		nastavCenu(num);
		nastavProdT(num);
		zvysCenu(range);
		zvysCenu(size);
		
	}
	
	//zvysi cenu pre typ Transportneho kontajnera Big/Medium
	public void zvysCenu(String size){
		switch (size) {
		case "Medium":
			this.cena+=200;
			break;

		case "Big":
			this.cena+=500;
			break;
		}
	}
	
	//zvysi cenu pre nosnost Transportneho kontajnera 2/10 ton
	public void zvysCenu(int range){
		if(range>6){
			this.cena+=200;
		}
		else if(range >=2 && range <7){
			this.cena+=100;
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
