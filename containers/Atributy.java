package containers;
/**
 * 
 * Interface, obsahuje spolo�n� met�dy, ktor� su �pecifick� pre dan� typ kontajnera, nemohli by� zakomponovan� v abstraktnej triede
 *
 */
public interface Atributy {
	
	
	public int zistiCenu();
	public void nastavCenu(int mnozstvo);
	public int zistiZaruku();
	public int zistiHmotnost();

	
}
