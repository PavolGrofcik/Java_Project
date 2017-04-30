package containers;


public class Ubytovac� extends Kontajner {
	
	private final int hmotnost = 5000;			//kg
	private  int zaruka =10;					//roky
	
	private String typ;
	private int pocetOkien;
	private boolean balkon;


	public Ubytovac�(int num) {
		nastavCenu(num);
		nastavProdT(num);
	}
	
	//pre�a�enie
	public Ubytovac�(int mnozstvo, String type, int windows) {
		this.typ=type;
		this.pocetOkien=windows;
		if(typ.equals("Rodinn�")){
			nastavCenu(-2+mnozstvo);										//Rodinn� je drah�� typ
			nastavProdT(mnozstvo);										//Av�ak produk�n� �as je rovnak�
		}
		else if(typ.equalsIgnoreCase("Kancel�rsky")){
			nastavCenu(25/windows+mnozstvo);
			nastavProdT(mnozstvo);
		}
	}
	
	public Ubytovac�(int mnozstvo, String type, int windows, boolean balcon) {
		if(type.equals("Rodinn�")){
			if (balcon) {
				this.balkon=true;
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(-5+mnozstvo);
				nastavProdT(-5+mnozstvo);
			}else{
				this.balkon=false;											//ak nie je zvolen� balkon
				this.typ=type;
				this.pocetOkien=windows;
				nastavCenu(-2+mnozstvo);
				nastavProdT(-2+mnozstvo);
			}
		}
		else if(type.equals("Kancel�rsky")){
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
