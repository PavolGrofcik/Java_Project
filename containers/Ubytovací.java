package containers;


public class Ubytovací extends Kontajner {
	
	private final int hmotnost = 5000;			//kg
	private  int zaruka =10;					//roky
	
	private String typ;
	private int pocetOkien;
	private boolean balkon;


	public Ubytovací(int num) {
		nastavCenu(num);
		nastavProdT(num);
	}
	
	//preaženie
	public Ubytovací(int mnozstvo, String type, int windows) {
		this.typ=type;
		this.pocetOkien=windows;
		if(typ.equals("Rodinný")){
			nastavCenu(-2+mnozstvo);										//Rodinný je drahší typ
			nastavProdT(mnozstvo);										//Avšak produkèný èas je rovnaký
		}
		else if(typ.equalsIgnoreCase("Kancelársky")){
			nastavCenu(25/windows+mnozstvo);
			nastavProdT(mnozstvo);
		}
	}
	
	public Ubytovací(int mnozstvo, String type, int windows, boolean balcon) {
		if(type.equals("Rodinný")){
			if (balcon) {
				this.balkon=true;
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(-5+mnozstvo);
				nastavProdT(-5+mnozstvo);
			}else{
				this.balkon=false;											//ak nie je zvolený balkon
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(-2+mnozstvo);
				nastavProdT(-2+mnozstvo);
			}
		}
		else if(type.equals("Kancelársky")){
			this.balkon=false;
			this.typ=type;
			this.pocetOkien=windows;
			nastavCenu(mnozstvo);
			nastavProdT(mnozstvo);
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
		if(mnozstvo>5){
			cena=2500;			//+ zisti velkost
		}
		else{
			cena=3000;
		}
	}
	
	public void nastavProdT(int mnozstvo){										//podla množstva sa urèuje aj èas na produkciu jednotlivých kontajnerov, èím viac tým rýchlejšie
		if (mnozstvo>10) {
			this.prodtime=100;
		}
		else {
			this.prodtime=125;
		}
	}

	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}

}
