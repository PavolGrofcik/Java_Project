package containers;

/**
 * @author Pavol Grof��k
 */
public class Transportn� extends Kontajner implements Atributy {
	
	/**
	 * �pecifick� atrib�ty pre dan� typ kontajnera
	 */
	private static final long serialVersionUID = 1L;
	private final int hmotnost = 1000;																				//kg
	private  int zaruka =5;																							//roky

	private int nosnost;																							//kg
	private String name;																							//Big alebo Medium
	
	/**
	 * Kon�truktor
	 * @param num Po�et Kontajnerov
	 * @param range	Nosnos� kontajnera
	 * @param size	Ve�kos� kontajnera
	 */
	public Transportn�(int num, int range, String size){															//Kon�truktor
		
		this.nosnost=range;
		this.name=size;
		
		nastavCenu(num);
		nastavProdT(num);
		zvysCenu(range);
		zvysCenu(size);
		
	}
	
	/**
	 * 
	 * @param size Pod�a zvolenej ve�kosti sa automaticky prid� cena
	 */
	public void zvysCenu(String size){																				//Zvysi cenu pre typ Transportneho kontajnera Big/Medium
		switch (size) {
		case "Medium":
			this.cena+=200;
			break;

		case "Big":
			this.cena+=500;
			break;
		}
	}
	
	/**
	 * 
	 * @param range Nastav� cenu pod�a zvolen�ho rozsahu u��vate�om
	 */
	public void zvysCenu(int range){																				//Zvysi cenu pre nosnost Transportneho kontajnera 2/10 ton
		if(range>6){
			this.cena+=200;
		}
		else if(range >=2 && range <7){
			this.cena+=100;
		}
		
	}
	
	/**
	 * Akut�lna z�ruka
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 * Nastav� produk�n� �as pod�a zvolen�ho mno�stva
	 *
	 * @param mnozstvo Po�et kontajnerov
	 */
	public void nastavProdT(int mnozstvo){																			//podla mno�stva sa ur�uje aj �as na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>10) {
			this.prodtime=50;
		}
		else {
			this.prodtime=70;
		}
	}
	/**
	 * Nastav� cenu pod�a mno�stva kontajnerov, uplatn� z�avu pri v��om mno�stve
	 * @param mnozstvo Aktu�lny po�et kontajnerov
	 */
	@Override
	public void nastavCenu(int mnozstvo) {																			//poskytuje zlavu 33% ak objedn�vka presahuje viac ako 10 polo�iek
		if (mnozstvo>10) {
			this.cena=1000;												
		}
		else
		this.cena=1500;		
	}
	
	
	/**
	 * @return Aktu�lna hmotnos�
	 */
	@Override
	public int zistiHmotnost(){
		return this.hmotnost;
	}
	
	/**
	 * 
	 * @return Aktu�lna nosnos�
	 */
	public int zistiNosnost(){
		return this.nosnost;
	}
}
