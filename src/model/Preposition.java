package model;

public class Preposition {

	private String maennlich;
	private String weiblich;
	private String neutral;
	
	public Preposition(String string) {
		String [] s = string.split(",");
		this.maennlich = s[0];
		this.weiblich = s[1];
		this.neutral = s[2];
	}
	
	public String getM(){
		return this.maennlich;
	}
	
	public String getW(){
		return this.weiblich;
	}
	
	public String getN(){
		return this.neutral;
	}

}
