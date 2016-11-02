package model;

public class Noun {
	
	private String noun;
	
	/*
	 * Genus
	 * 0 - männlich
	 * 1 - weiblich
	 * 2 - neutral
	 *  
	 */
	private int genus;			

	public Noun(String string) {
		String [] s = string.split(",");
		
		this.noun = s[0];
		
		if(s[1].equals("m")){
			this.genus = 0;
		}
		
		if(s[1].equals("w")){
			this.genus = 1;
		}
		
		if(s[1].equals("n")){
			this.genus = 2;
		}
	}
	
	public String toString(){
		return this.noun;
	}

	public int getGenus() {
		return this.genus;
	}

}
