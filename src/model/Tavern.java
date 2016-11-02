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
	 * Festlegen:
	 * Preisniveau 	-> 1-5 Sterne, 5 Sterne am teuersten
	 * Qualität		-> 1-5 Sterne, 5 Sterne am besten
	 * Größe		-> winzig, klein, normal, groß, riesig
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
		
		return "Preis: " + this.price + "/5, Qualität: " + this.quality + "/5, Größe: " + ts[this.size-1];
	}
	
	public void initializeDetails(){
		this.price = this.randomer.nextInt(5) + 1;
		this.quality = this.randomer.nextInt(5) + 1;
		this.size = this.randomer.nextInt(5) + 1;
	}

	public String getPreisniveau() {
		return this.price + "/5";
	}

	public String getQualitaetsniveau() {
		return this.quality + "/5";
	}

	public String getGroesse() {
		TavernSize [] ts = TavernSize.values();
		return ts[this.size-1].toString();
	}
}
