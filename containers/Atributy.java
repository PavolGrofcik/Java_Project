package containers;
/**
 * 
 * Interface, obsahuje spoloèné metódy, ktoré su špecifické pre danı typ kontajnera, nemohli by zakomponované v abstraktnej triede
 *
 */
public interface Atributy {
	
	
	public int zistiCenu();
	public void nastavCenu(int mnozstvo);
	public int zistiZaruku();
	public int zistiHmotnost();

	
}
