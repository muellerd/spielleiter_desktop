package model;

public class Good {
	
	private String name;
	private String category;
    private String weight;
    private String price;
	
	public Good(String n, String c, String w, String p){
		name = n;
        category = n;
        weight = w;
        price = p;
	}


    public String getName() {
		return this.name;
	}

	public String getCategory(){
		return this.category;
	}

    @Override
    public String toString() {
        return this.name;
    }
}
