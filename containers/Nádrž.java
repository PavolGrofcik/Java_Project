package containers;
/**
 * 
 * @author Pavol GrofËÌk
 *
 */
public class N·drû extends Kontajner implements Atributy {
	/**
	 * äpecifickÈ atrib˙ty pre N·drû
	 */
	private static final long serialVersionUID = 1L;
	private  int zaruka =7;																				//Roky
	private final int hmotnost = 1500;
	
	private int Objem;																					//Objem v m3 kubick˝ch, max 7 m3
	private String Velkost;
	
	
	/**
	 * Konötruktor
	 * @param num PoËet Kontajnerov
	 * @param size Veækosù objemu Kontajnera
	 */
	public N·drû(int num, String size) {
		
		this.Velkost=size;
		
		//nastavenie ceny a produkËneho Ëasu na zaklade v˝beru velkosti z·kaznika										
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
	 * @param number	Aktu·lne mnoûstvo kontajnera
	 * @param delta		ZmenÌ danÈ mnoûstvo kontajnera o poËet delta
	 * @return			Nov· zmenen· hodnota
	 */
	public int zmenMnozstvo(int number, int delta){															//MetÛda zmenÌ mnozstvo o dan˝ poËet
																											//Number = zvolene mnozstvo, delta = o kolko zmenit, konötanta
		return number-delta;
	}

	/**
	 * @return Aktu·lna z·ruka
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 * NastavÌ cenu podæa mnoûstva kontajnerov, uplatnÌ zæavu pri v‰Ëöom mnoûstve
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
	 * NastavÌ produkËn˝ Ëas, z·visiac od poËtu kontajnerov danÈho typu
	 * @param mnozstvo PoËet Kontajnerov
	 */
	public void nastavProdT(int mnozstvo){										//Podla mnoûstva sa  automaticky urËuje aj Ëas urËen˝ na produkciu jednotliv˝ch kontajnerov, ËÌm viac t˝m r˝chlejöie
		if (mnozstvo>5) {
			this.prodtime=90;
		}
		else {
			this.prodtime=100;
		}
	}

	/**
	 * 
	 * @param objem NastavÌ objem podæa zvolenej hodnoty
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
	 * @return Aktu·lny objem
	 */
	public int zistiObjem(){
		return this.Objem;
	}

	/**
	 *@return Atu·lna hmotnosù
	 */
	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}

}
