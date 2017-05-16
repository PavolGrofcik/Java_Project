package containers;
/**
 * 
 * @author Pavol Grof��k
 *
 */
public class N�dr� extends Kontajner implements Atributy {
	/**
	 * �pecifick� atrib�ty pre N�dr�
	 */
	private static final long serialVersionUID = 1L;
	private  int zaruka =7;																				//Roky
	private final int hmotnost = 1500;
	
	private int Objem;																					//Objem v m3 kubick�ch, max 7 m3
	private String Velkost;
	
	
	/**
	 * Kon�truktor
	 * @param num Po�et Kontajnerov
	 * @param size Ve�kos� objemu Kontajnera
	 */
	public N�dr�(int num, String size) {
		
		this.Velkost=size;
		
		//nastavenie ceny a produk�neho �asu na zaklade v�beru velkosti z�kaznika										
		if(Velkost.equals("Small")){
			nastavCenu(num);
			nastavProdT(num);
			nastavObjem(size);
		}
		else if(Velkost.equals("Medium")){
			nastavCenu(zmenMnozstvo(num,-2));
			nastavProdT(zmenMnozstvo(num,-2));
			nastavObjem(size);
		}else if(Velkost.equals("Big")){
			nastavCenu(zmenMnozstvo(num,-3));
			nastavProdT(zmenMnozstvo(num,-3));
			nastavObjem(size);
		}
	}
	
	/**
	 * 
	 * @param number	Aktu�lne mno�stvo kontajnera
	 * @param delta		Zmen� dan� mno�stvo kontajnera o po�et delta
	 * @return			Nov� zmenen� hodnota
	 */
	public int zmenMnozstvo(int number, int delta){															//Met�da zmen� mnozstvo o dan� po�et
																											//Number = zvolene mnozstvo, delta = o kolko zmenit, kon�tanta
		return number-delta;
	}

	/**
	 * @return Aktu�lna z�ruka
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 * Nastav� cenu pod�a mno�stva kontajnerov, uplatn� z�avu pri v��om mno�stve
	 */
	@Override
	public void nastavCenu(int mnozstvo) {
		if(mnozstvo>5)
			this.cena=2000;
		else {
			this.cena=2500;
		}
	}
	
	/**
	 * Nastav� produk�n� �as, z�visiac od po�tu kontajnerov dan�ho typu
	 * @param mnozstvo Po�et Kontajnerov
	 */
	public void nastavProdT(int mnozstvo){										//Podla mno�stva sa  automaticky ur�uje aj �as ur�en� na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>5) {
			this.prodtime=90;
		}
		else {
			this.prodtime=100;
		}
	}

	/**
	 * 
	 * @param objem Nastav� objem pod�a zvolenej hodnoty
	 */
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
	
	/**
	 * 
	 * @return Aktu�lny objem
	 */
	public int zistiObjem(){
		return this.Objem;
	}

	/**
	 *@return Atu�lna hmotnos�
	 */
	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}

}
