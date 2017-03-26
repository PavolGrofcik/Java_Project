package Model;

public class chemContainer extends coolContainer implements C_attributes {

	private int hazard;
	private final int warranty = 10;
	
	public int checkwarr(){
		
		return this.warranty;
	}
	
	public void sethazard(int degree){
		this.hazard = degree;
	}
	
	public int checkhazard(){
		return this.hazard;
	}
}
