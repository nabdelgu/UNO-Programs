public class Card
{
	
	private Design design;
	private boolean isWild;				//Card attributes
	private Country country;
	
	
	public Card (){
		this.design = null;
		this.country = null;			//constructor for wild card
		this.isWild = true;
	} // end of 3 argument constructor
	
	public Card (Design design, Country country){
		this.design = design;
		this.country = country;			//constructor for regular card
		this.isWild = false;
	} // end of method Card
	
	public boolean isWild (){
		return this.isWild;				//query method for isWild
	} // end method isWild
	
	public Design getDesign(){	
	
		return this.design;				//query method to return  unitTypes
	} // end method getDesign
	
	public Country getCountry(){
		
		return country;					//query method to return country
	} //end of getCountry method
	
} //end of class Card
