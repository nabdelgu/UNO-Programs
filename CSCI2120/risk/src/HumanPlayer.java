
import java.io.Serializable;

public class HumanPlayer extends Player implements Serializable {

		
		
	public Action getNextAction (){
		
		return null;					//gets players next action
	}//end of method getNextAction
	
	public HumanPlayer ( String name ){
		super();
		this.name = name;				//2 agrument constructor
	}//end of 2 argument constructor

	@Override
	public Country getAttackingCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country getAttackedCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAttackDiceCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefendDiceCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Country getClaimedCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country getOldCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country getNewCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMovedArmyCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}//end of class HumanPlayer