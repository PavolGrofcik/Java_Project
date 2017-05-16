package containers;

/**
 * @author Pavol GrofËÌk
 */
public class Transportn˝ extends Kontajner implements Atributy {
	
	/**
	 * äpecifickÈ atrib˙ty pre dan˝ typ kontajnera
	 */
	private static final long serialVersionUID = 1L;
	private final int hmotnost = 1000;																				//kg
	private  int zaruka =5;																							//roky

	private int nosnost;																							//kg
	private String name;																							//Big alebo Medium
	
	/**
	 * Konötruktor
	 * @param num PoËet Kontajnerov
	 * @param range	Nosnosù kontajnera
	 * @param size	Veækosù kontajnera
	 */
	public Transportn˝(int num, int range, String size){															//Konötruktor
		
		this.nosnost=range;
		this.name=size;
		
		nastavCenu(num);
		nastavProdT(num);
		zvysCenu(range);
		zvysCenu(size);
		
	}
	
	/**
	 * 
	 * @param size Podæa zvolenej veækosti sa automaticky prid· cena
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
	 * @param range NastavÌ cenu podæa zvolenÈho rozsahu uûÌvateæom
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
	 * Akut·lna z·ruka
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 * NastavÌ produkËn˝ Ëas podæa zvolenÈho mnoûstva
	 *
	 * @param mnozstvo PoËet kontajnerov
	 */
	public void nastavProdT(int mnozstvo){																			//podla mnoûstva sa urËuje aj Ëas na produkciu jednotliv˝ch kontajnerov, ËÌm viac t˝m r˝chlejöie
		if (mnozstvo>10) {
			this.prodtime=50;
		}
		else {
			this.prodtime=70;
		}
	}
	/**
	 * NastavÌ cenu podæa mnoûstva kontajnerov, uplatnÌ zæavu pri v‰Ëöom mnoûstve
	 * @param mnozstvo Aktu·lny poËet kontajnerov
	 */
	@Override
	public void nastavCenu(int mnozstvo) {																			//poskytuje zlavu 33% ak objedn·vka presahuje viac ako 10 poloûiek
		if (mnozstvo>10) {
			this.cena=1000;												
		}
		else
		this.cena=1500;		
	}
	
	
	/**
	 * @return Aktu·lna hmotnosù
	 */
	@Override
	public int zistiHmotnost(){
		return this.hmotnost;
	}
	
	/**
	 * 
	 * @return Aktu·lna nosnosù
	 */
	public int zistiNosnost(){
		return this.nosnost;
	}
}
