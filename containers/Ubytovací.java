package containers;


public class Ubytovac� extends Kontajner {
	
	private final int hmotnost = 5000;			//kg
	private  int zaruka =10;					//roky
	
	private String typ;
	private int pocetOkien;
	private boolean balkon;
	
	private Balcon Balcon;

	// private inner Class Balcon, mo�n� len pre Rodinny typ kontajnera, lep�� sp�sob namiesto Agreg�cie(global)
	private class Balcon{
		
		private boolean Terasa;
		
		public Balcon(boolean terrace){
			this.Terasa=terrace;
		}
		
	}
	
	//pre�a�enie
	public Ubytovac�(int mnozstvo, String type, int windows) {
		this.typ=type;
		this.pocetOkien=windows;
		this.balkon=false;
		
		if(typ.equalsIgnoreCase("Rodinn�")){
			nastavCenu(-2+mnozstvo);										//Rodinn� je drah�� typ
			nastavProdT(mnozstvo);											//Av�ak produk�n� �as je rovnak�
			zvysCenu(windows);
		}
		else if(typ.equalsIgnoreCase("Kancel�rsky")){
			nastavCenu(25/windows+mnozstvo);
			nastavProdT(mnozstvo);
			zvysCenu(windows);
		}
	}
	
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
				this.balkon=false;											//ak nie je zvolen� balkon
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(zmenMnozstvoParam(-2, mnozstvo));
				nastavProdT(zmenMnozstvoParam(-2, mnozstvo));
				zvysCenu(windows);
			}
		}
	}
	

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
	
	public int zmenMnozstvoParam(int number, int curr){
		return number+curr;
	}
	
	public void zvysCenu(int windows){
		cena+=(windows*50);				//1 okno = 50�;
	}
	
	public Balcon newInstanceBalcon(boolean withTerrace){							//ak u��vatel zvol� mo�nos� Balcon
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
	
	public void nastavProdT(int mnozstvo){										//podla mno�stva sa ur�uje aj �as na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
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
