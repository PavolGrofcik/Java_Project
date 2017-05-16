package containers;
/**
 * 
 * @author Pavol Grof��k
 *
 */

public class Ubytovac� extends Kontajner {
	
	/**
	 * �pecifick� atrib�ty pre dan� typ kontajnera
	 */
	private static final long serialVersionUID = 1L;
	private final int hmotnost = 5000;																			//kg
	private  int zaruka =10;																					//roky
	
	private String typ;
	private int pocetOkien;
	private boolean balkon;
	
	/**
	 * Agreg�cia hodnotou - Balcon
	 */
	private Balcon Balcon;

	// private Inner Class Balcon, mo�n� len pre Rodinny typ kontajnera, lep�� sp�sob  Agreg�cie (Local), vytv�ra sa len pri Rodinnom type ak si zvol� u��vate�
	
	/**
	 * Vnoren� trieda Balcon, sl��i ako in�tancovanie agreg�tu Balcon pri zvolen� Rodinn�h typu Ubytovacieho kontajnera
	 *
	 */
	private class Balcon{
		
		private boolean Terasa;
		
		public Balcon(boolean terrace){
			this.Terasa=terrace;
		}
		
	}
	
	/**
	 * Kon�truktor
	 * @param mnozstvo Po�et Kontajnerov
	 * @param type Typ - Rodinn�/Kancel�rsky
	 * @param windows Po�et okien
	 */
	public Ubytovac�(int mnozstvo, String type, int windows) {
		this.typ=type;
		this.pocetOkien=windows;
		this.balkon=false;
		
		if(typ.equalsIgnoreCase("Rodinn�")){
			nastavCenu(-2+mnozstvo);																		//Rodinn� typ je drah�� ako kancel�rsky
			nastavProdT(mnozstvo);																			//Av�ak produk�n� �as je rovnak� ako pri kancel�rskom
			zvysCenu(windows);
		}
		else if(typ.equalsIgnoreCase("Kancel�rsky")){
			nastavCenu(25/windows+mnozstvo);
			nastavProdT(mnozstvo);
			zvysCenu(windows);
		}
	}
	
	
	/**
	 * Kon�truktor
	 * @param mnozstvo Po�et kontajnerov
	 * @param type	Typ kontajnera
	 * @param windows Po�et okien
	 * @param balcon Balk�n (T/F)
	 * @param terrasa Terrasa(Najprv mus� by� zvolen� Balk�n)
	 */
	public Ubytovac�(int mnozstvo, String type, int windows, boolean balcon,boolean terrasa) {
		
		if(type.equals("Rodinn�")){
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
				this.balkon=false;																			//Mo�nos� ak u��vate� nezvoli mo�nos� s Balk�nom
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(zmenMnozstvoParam(-2, mnozstvo));
				nastavProdT(zmenMnozstvoParam(-2, mnozstvo));
				zvysCenu(windows);
			}
		}
	}
	

	/**
	 * Met�da prid� cenu v z�vislosti od zvolen�ch polo�iek Balkon/Terasa
	 * @param balcon Boolean hodnota ak zakazn�k zvolil Balcon
	 * @param terrasa Boolean hodnota ak zakazn�k zvolil Terasu
	 */
	public void zvysCenu(boolean balcon,boolean terrasa){
		
		if(balcon){
			if(terrasa){
				cena+=300;				//terasa = 300�;
			}
			else{
				cena+=150;				//balkon = 150�;
			}
		}
	}
	
	/**
	 * Met�da prid� cen� v z�vislosti od po�tu okien
	 * @param windows Po�et okien
	 */
	public void zvysCenu(int windows){
		cena+=(windows*50);																					//Cena: 1 okno = 50�
	}
	
	/**
	 * 
	 * @param number Aktu�lne mno�stvo kontajnerov
	 * @param curr	Delta - zmena mno�stva
	 * @return	Nov� zmenen� mno�stvo
	 */
	public int zmenMnozstvoParam(int number, int curr){
		return number+curr;
	}
	
	/**
	 * Met�da vytvor� agreg�t Balk�n
	 * @param withTerrace Booelan hodnota ak z�kaznik zvoli mo�nos� s Terasou
	 * @return Nov� Objekt typu Balcon
	 */
	public Balcon newInstanceBalcon(boolean withTerrace){													//Ak u��vatel zvol� mo�nos� Balcon
		return new Balcon(withTerrace);
	}
	
	/**
	 * @return Aktu�lna z�ruka
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 *  Nastav� cenu pod�a mno�stva kontajnerov, uplatn� z�avu pri v��om mno�stve
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
	 * Nastav� produk�n� �as, z�visiac od po�tu kontajnerov dan�ho typu
	 * @param mnozstvo Aktu�lny po�et kontajnerov
	 */
	public void nastavProdT(int mnozstvo){																	//Pod�a mno�stva objedn�vky sa ur�uje aj �as na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>10) {
			this.prodtime=100;
		}
		else {
			this.prodtime=125;
		}
	}

	/**
	 * @return Aktu�lna hmotnos�
	 */
	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}
	
	/**
	 * Met�da zist� pr�tomnos� agreg�tu
	 * @return Boolean hodnota obsiahnutie Balcon-u ako Agreg�t
	 */
	public boolean zistiBalkon(){																			//Met�da zist�, �i si u��vate� zvoli balk�n
		return this.balkon;
	}
	
	/**
	 * Met�da zist� pr�tomnos� agreg�tu
	 * @return Boolean hodnota obsiahnutie Terasy ako Agreg�t
	 */
	public boolean zistiTerasu(){																			//Met�da, zist� ak si u��vate� zvolil s balk�nom terasu
		
		if(this.balkon == true){
			return Balcon.Terasa;
		}
		else{
			return false;
		}
	}

}
