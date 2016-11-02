package model;

import java.io.Serializable;
import java.util.Random;

public class Tavern implements Serializable{
	
	private String name;
	private Random randomer;
	private int price;
	private int quality;
	private int size;
	
	
	/*
	 * Set:
	 * Price niveau -> 1-5 Stars, 5 Stars most expensive
	 * Quality		-> 1-5 Stars, 5 Stars is the best
	 * Size			-> tiny, small, medium, big, huge
	 */
	
	public Tavern(){
		randomer = new Random();
		this.initializeDetails();
	}
	
	public Tavern(String n){
		this.name = n;
		this.randomer = new Random();
		this.initializeDetails();
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String n){
		this.name = n;
	}

	public String getDescription() {
		TavernSize [] ts = TavernSize.values();
		
		return "Price: " + this.price + "/5, Quality: " + this.quality + "/5, Size: " + ts[this.size-1];
	}
	
	public void initializeDetails(){
		this.price = this.randomer.nextInt(5) + 1;
		this.quality = this.randomer.nextInt(5) + 1;
		this.size = this.randomer.nextInt(5) + 1;
	}

	public String getPriceNiveau() {
		return this.price + "/5";
	}

	public String getQualityNiveau() {
		return this.quality + "/5";
	}

	public String getSize() {
		TavernSize [] ts = TavernSize.values();
		return ts[this.size-1].toString();
	}
}
