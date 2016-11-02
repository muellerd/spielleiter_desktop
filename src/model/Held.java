package model;

import java.io.Serializable;

public class Held implements Serializable{

	private String name;
	private int costDukaten;
	private int costSilber;
	private int costHeller;
	private int costKreuzer;
	
	public Held(){
		costDukaten = 0;
		costSilber = 0;
		costHeller = 0;
		costKreuzer = 0;
	}
	
	public Held(String n){
		this.name = n;
		costDukaten = 0;
		costSilber = 0;
		costHeller = 0;
		costKreuzer = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String n){
		this.name = n;
	}

	public String getNameAndCost() {
		return this.name + ": 	" + costDukaten + "D, " + costSilber + "S, " + costHeller + "H, " + costKreuzer + "K";
	}

	public void addGood(Good g, int i) {
		/*this.costKreuzer += g.getPriceKreuzer();
		if(this.costKreuzer >= 10){
			this.costKreuzer -= 10;
			this.costHeller += 1;
		}
		
		this.costHeller += g.getPriceHeller();
		if(this.costHeller >= 10){
			this.costHeller -= 10;
			this.costSilber += 1;
		}
		
		this.costSilber += g.getPriceSilber();
		if(this.costSilber >= 10){
			this.costSilber -= 10;
			this.costDukaten += 1;
		}
		
		this.costDukaten += g.getPriceDukaten();
		*/
	}

	public int getHeller() {
		return this.costHeller;
	}

	public int getSilber() {
		return this.costSilber;
	}

	public int getDukaten() {
		return this.costDukaten;
	}

	public int getKreuzer() {
		return this.costKreuzer;
	}
}
