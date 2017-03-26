package Model;

public class coolContainer extends Container implements C_attributes {

	private final int warranty=5;
	private boolean foody;
	
	public void setfoody(boolean name){
		this.foody=name;
	}
	
	public boolean checkfoody(){
		return this.foody;
		
	}
	public int checkwarr(){
		
		return this.warranty;
	}

	@Override
	public int checkhazard() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sethazard(int degree) {
		// TODO Auto-generated method stub
		
	}
	
}
