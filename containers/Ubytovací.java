package containers;


public class Ubytovací extends Kontajner {
	
	private final int hmotnost = 5000;			//kg
	private  int zaruka =10;					//roky
	
	private String typ;
	private int pocetOkien;
	private boolean balkon;

	// private inner Class Balcon, možná len pre Rodinny typ kontajnera, lepší spôsob namiesto Agregácie(global)
	private class Balcon{
		
		private boolean Terasa;
		
		public Balcon(boolean terrace){
			this.Terasa=terrace;
		}
		
	}
	

	public Ubytovací(int num) {
		nastavCenu(num);
		nastavProdT(num);
	}
	
	//preaženie
	public Ubytovací(int mnozstvo, String type, int windows) {
		this.typ=type;
		this.pocetOkien=windows;
		this.balkon=false;
		if(typ.equalsIgnoreCase("Rodinný")){
			nastavCenu(-2+mnozstvo);										//Rodinný je drahší typ
			nastavProdT(mnozstvo);											//Avšak produkèný èas je rovnaký
			zvysCenu(windows);
		}
		else if(typ.equalsIgnoreCase("Kancelársky")){
			nastavCenu(25/windows+mnozstvo);
			nastavProdT(mnozstvo);
			zvysCenu(windows);
		}
	}
	
	public Ubytovací(int mnozstvo, String type, int windows, boolean balcon,boolean terrasa) {
		if(type.equals("Rodinný")){
			if (balcon) {
				this.balkon=true;
				this.typ=type;
				this.pocetOkien=windows;
				//nastavenie Balkonu s atributmi
				newInstanceBalcon(terrasa);
				
				nastavCenu(-5+mnozstvo);
				nastavProdT(-5+mnozstvo);
				zvysCenu(balcon, terrasa);
				zvysCenu(windows);
			}else{
				this.balkon=false;											//ak nie je zvolený balkon
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(zmenMnozstvoParam(-2, mnozstvo));
				nastavProdT(zmenMnozstvoParam(-2, mnozstvo));
				zvysCenu(windows);
			}
		}
		/*
		else if(type.equals("Kancelársky")){
			this.balkon=false;
			this.typ=type;
			this.pocetOkien=windows;
			nastavCenu(mnozstvo);
			nastavProdT(mnozstvo);
			zvysCenu(windows);
		}*/
	}
	

	public void zvysCenu(boolean balcon,boolean terrasa){
		
		if(balcon){
			if(terrasa){
				cena+=300;				//terasa = 300€;
			}
			else{
				cena+=150;				//balkon = 150€;
			}
		}
	}
	
	public int zmenMnozstvoParam(int number, int curr){
		return number+curr;
	}
	
	public void zvysCenu(int windows){
		cena+=(windows*50);				//1 okno = 50€;
	}
	
	public Balcon newInstanceBalcon(boolean withTerrace){							//ak užívatel zvolí možnos Balcon
		return new Balcon(withTerrace);
	}
	
	@Override
	public int zistiZaruku() {
		return this.zaruka;
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
