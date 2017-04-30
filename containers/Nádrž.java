package containers;

public class Nádrž extends Kontajner implements Atributy {
	private  int zaruka =7;					//roky
	private final int hmotnost = 1000;
	
	private int Objem;						//objem v m3 kubických, max 7 m3
	private String Velkost;
	
	

	public Nádrž(int num) {
	nastavCenu(num);
	nastavProdT(num);
	}
	
	//konštruktor pre NadržStage
	
	public Nádrž(int num, String size) {
		
		this.Velkost=size;
		
		//nastavenie ceny a produkèneho èasu na zaklade výberu velkosti zákaznika										
		if(Velkost.equals("Small")){
			nastavCenu(num);
			nastavProdT(num);
			nastavObjem(size);
		}
		else if(Velkost.equals("Medium")){
			nastavCenu(-2+num);
			nastavProdT(-2+num);
			nastavObjem(size);
		}else if(Velkost.equals("Big")){
			nastavCenu(-3+num);
			nastavProdT(-3+num);
			nastavObjem(size);
		}
	}
	


	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	@Override
	public int zistiCas() {
		return this.prodtime;
	}

	@Override
	public void nastavCenu(int mnozstvo) {
		if(mnozstvo>10)
			this.cena=2000;
		else {
			this.cena=2500;
		}
	}
	
	public void nastavProdT(int mnozstvo){										//podla množstva sa  automaticky urèuje aj èas urèený na produkciu jednotlivých kontajnerov, èím viac tým rýchlejšie
		if (mnozstvo>10) {
			this.prodtime=90;
		}
		else {
			this.prodtime=100;
		}
	}

	
	public void nastavObjem(String objem){
		
		switch (objem) {
		case "Small":{
			this.Objem=3000;
				}		
			break;
		case "Medium":{
			this.Objem=7000;
			}
			break;
		case "Big":{
			this.Objem=10000;
			}
			break;
		}
	}
	
	public int zistiObjem(){
		return this.Objem;
	}

	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}

}
