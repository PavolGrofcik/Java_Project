package kamióny;

public class Auto {

	private int nosnost;
	private int cas;
	
	public void setAtributy(int m, int time){
		this.nosnost=m;
		this.cas=time;
	}
	
	public int getcas(){
		return cas;
	}
	
	public int getnosnost(){
		return nosnost;
	}
	
}
