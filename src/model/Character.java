package model;

import java.io.Serializable;

public class Character implements Serializable{

	private String name;
	private int costGold;
	private int costSilver;
	private int costCopper;

	public Character(){
		costGold = 0;
		costSilver = 0;
		costCopper = 0;
	}
	
	public Character(String n){
		this.name = n;
		costGold = 0;
		costSilver = 0;
		costCopper = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String n){
		this.name = n;
	}

	public String getNameAndCost() {
		return this.name + ": 	" + costGold + " GM, " + costSilver + " SM, " + costCopper + " KM";
	}

	public void addGood(Good g, int i) {
		String priceString = g.getPriceString();
        if(priceString.contains("GM")){
            priceString = priceString.replace("GM", "").trim();
            this.costGold += Integer.parseInt(priceString);
        }
        if(priceString.contains("SM")){
            priceString = priceString.replace("SM", "").trim();
            this.costSilver += Integer.parseInt(priceString);
        }
        if(priceString.contains("KM")){
            priceString = priceString.replace("KM", "").trim();
            this.costCopper += Integer.parseInt(priceString);
        }
	}
}
