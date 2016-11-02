package model;

import java.io.Serializable;

public class Held implements Serializable{

	private String name;
	private int costGold;
	private int costSilber;
	private int costKupfer;

	public Held(){
		costGold = 0;
		costSilber = 0;
		costKupfer = 0;
	}
	
	public Held(String n){
		this.name = n;
		costGold = 0;
		costSilber = 0;
		costKupfer = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String n){
		this.name = n;
	}

	public String getNameAndCost() {
		return this.name + ": 	" + costGold + " GM, " + costSilber + " SM, " + costKupfer + " KM";
	}

	public void addGood(Good g, int i) {
		String priceString = g.getPriceString();
        if(priceString.contains("GM")){
            priceString = priceString.replace("GM", "").trim();
            this.costGold += Integer.parseInt(priceString);
        }
        if(priceString.contains("SM")){
            priceString = priceString.replace("SM", "").trim();
            this.costSilber += Integer.parseInt(priceString);
        }
        if(priceString.contains("KM")){
            priceString = priceString.replace("KM", "").trim();
            this.costKupfer += Integer.parseInt(priceString);
        }
	}

	public int getHeller() {
		return this.costKupfer;
	}

	public int getSilber() {
		return this.costSilber;
	}

	public int getDukaten() {
		return this.costGold;
	}
}
