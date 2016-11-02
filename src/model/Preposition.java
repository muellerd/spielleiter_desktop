package model;

public class Preposition {

	private String male;
	private String female;
	private String neutral;
	
	public Preposition(String string) {
		String [] s = string.split(",");
		this.male = s[0];
		this.female = s[1];
		this.neutral = s[2];
	}
	
	public String getM(){
		return this.male;
	}
	
	public String getF(){
		return this.female;
	}
	
	public String getN(){
		return this.neutral;
	}

}
