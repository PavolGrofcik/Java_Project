package containers;
/**
 * 
 * @author Pavol Grofèík
 *
 */

public class Ubytovací extends Kontajner {
	
	/**
	 * Špecifické atribúty pre danı typ kontajnera
	 */
	private static final long serialVersionUID = 1L;
	private final int hmotnost = 5000;																			//kg
	private  int zaruka =10;																					//roky
	
	private String typ;
	private int pocetOkien;
	private boolean balkon;
	
	/**
	 * Agregácia hodnotou - Balcon
	 */
	private Balcon Balcon;

	// private Inner Class Balcon, moná len pre Rodinny typ kontajnera, lepší spôsob  Agregácie (Local), vytvára sa len pri Rodinnom type ak si zvolí uívate¾
	
	/**
	 * Vnorená trieda Balcon, slúi ako inštancovanie agregátu Balcon pri zvolení Rodinnéh typu Ubytovacieho kontajnera
	 *
	 */
	private class Balcon{
		
		private boolean Terasa;
		
		public Balcon(boolean terrace){
			this.Terasa=terrace;
		}
		
	}
	
	/**
	 * Konštruktor
	 * @param mnozstvo Poèet Kontajnerov
	 * @param type Typ - Rodinnı/Kancelársky
	 * @param windows Poèet okien
	 */
	public Ubytovací(int mnozstvo, String type, int windows) {
		this.typ=type;
		this.pocetOkien=windows;
		this.balkon=false;
		
		if(typ.equalsIgnoreCase("Rodinnı")){
			nastavCenu(-2+mnozstvo);																		//Rodinnı typ je drahší ako kancelársky
			nastavProdT(mnozstvo);																			//Avšak produkènı èas je rovnakı ako pri kancelárskom
			zvysCenu(windows);
		}
		else if(typ.equalsIgnoreCase("Kancelársky")){
			nastavCenu(25/windows+mnozstvo);
			nastavProdT(mnozstvo);
			zvysCenu(windows);
		}
	}
	
	
	/**
	 * Konštruktor
	 * @param mnozstvo Poèet kontajnerov
	 * @param type	Typ kontajnera
	 * @param windows Poèet okien
	 * @param balcon Balkón (T/F)
	 * @param terrasa Terrasa(Najprv musí by zvolenı Balkón)
	 */
	public Ubytovací(int mnozstvo, String type, int windows, boolean balcon,boolean terrasa) {
		
		if(type.equals("Rodinnı")){
			if (balcon) {
				this.balkon=true;
				this.typ=type;
				this.pocetOkien=windows;
				//nastavenie Balkonu s atributmi
				Balcon=newInstanceBalcon(terrasa);
				
				nastavCenu(zmenMnozstvoParam(-5, mnozstvo));
				nastavProdT(zmenMnozstvoParam(-5, mnozstvo));
				zvysCenu(balcon, terrasa);
				zvysCenu(windows);
				
			}else{
				this.balkon=false;																			//Monos ak uívate¾ nezvoli monos s Balkónom
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(zmenMnozstvoParam(-2, mnozstvo));
				nastavProdT(zmenMnozstvoParam(-2, mnozstvo));
				zvysCenu(windows);
			}
		}
	}
	

	/**
	 * Metóda pridá cenu v závislosti od zvolenıch poloiek Balkon/Terasa
	 * @param balcon Boolean hodnota ak zakazník zvolil Balcon
	 * @param terrasa Boolean hodnota ak zakazník zvolil Terasu
	 */
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
	
	/**
	 * Metóda pridá cenú v závislosti od poètu okien
	 * @param windows Poèet okien
	 */
	public void zvysCenu(int windows){
		cena+=(windows*50);																					//Cena: 1 okno = 50€
	}
	
	/**
	 * 
	 * @param number Aktuálne mnostvo kontajnerov
	 * @param curr	Delta - zmena mnostva
	 * @return	Nová zmenené mnostvo
	 */
	public int zmenMnozstvoParam(int number, int curr){
		return number+curr;
	}
	
	/**
	 * Metóda vytvorı agregát Balkón
	 * @param withTerrace Booelan hodnota ak zákaznik zvoli monos s Terasou
	 * @return Novı Objekt typu Balcon
	 */
	public Balcon newInstanceBalcon(boolean withTerrace){													//Ak uívatel zvolí monos Balcon
		return new Balcon(withTerrace);
	}
	
	/**
	 * @return Aktuálna záruka
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 *  Nastaví cenu pod¾a mnostva kontajnerov, uplatní z¾avu pri väèšom mnostve
	 */
	@Override
	public void nastavCenu(int mnozstvo) {
		if(mnozstvo>5){
			cena=2500;			//+ zisti velkost
		}
		else{
			cena=3000;
		}
	}
	
	/**
	 * Nastaví produkènı èas, závisiac od poètu kontajnerov daného typu
	 * @param mnozstvo Aktuálny poèet kontajnerov
	 */
	public void nastavProdT(int mnozstvo){																	//Pod¾a mnostva objednávky sa urèuje aj èas na produkciu jednotlivıch kontajnerov, èím viac tım rıchlejšie
		if (mnozstvo>10) {
			this.prodtime=100;
		}
		else {
			this.prodtime=125;
		}
	}

	/**
	 * @return Aktuálna hmotnos
	 */
	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}
	
	/**
	 * Metóda zistí prítomnos agregátu
	 * @return Boolean hodnota obsiahnutie Balcon-u ako Agregát
	 */
	public boolean zistiBalkon(){																			//Metóda zistí, èi si uívate¾ zvoli balkón
		return this.balkon;
	}
	
	/**
	 * Metóda zistí prítomnos agregátu
	 * @return Boolean hodnota obsiahnutie Terasy ako Agregát
	 */
	public boolean zistiTerasu(){																			//Metóda, zistí ak si uívate¾ zvolil s balkónom terasu
		
		if(this.balkon == true){
			return Balcon.Terasa;
		}
		else{
			return false;
		}
	}

}
