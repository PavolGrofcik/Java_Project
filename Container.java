package Model;

public abstract class Container implements C_attributes{
	private int mass;
	private int prod_time;
	private boolean extensible;
	private double price;
	private String Name;
	
	public void setPrice(double price){
		this.price=price;
	}
	
	public double checkPrice(){
		return this.price;
	}
	
	public void setattributes(int m, int t, boolean ext){
		this.mass=m;
		this.prod_time=t;
		this.extensible=ext;
	}
	
	public int getm(){
		return mass;
	}
	
	public int gett(){
		return prod_time;
	}
	public boolean getex(){
		return extensible;
	}
	
	public void setName(String new_name){
		this.Name = new_name;
	}
	
	public String checkName(){
		return this.Name;
	}
	
}
