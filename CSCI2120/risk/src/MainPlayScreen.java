import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFrame;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainPlayScreen extends JFrame {
	
	private JLabel riskMap;
	private final JPanel[] panels = { new JPanel(), new JPanel(), new JPanel(), new JPanel() };
	final private Game game;
	// array of JButtons
	private JButton userAction[] = new JButton[]{
			new JButton( "Turn in Cards"), new JButton("Place Reinforcements"), new JButton("Attack"), new JButton("Fortify"), new JButton("Save game"),new JButton("Quit game"), new JButton("Pass Turn") // actionButton array
		
	};
	// array of JLabels
	private JLabel labels[] = new JLabel[]{
		new JLabel("Attack From"), new JLabel("Attack Too"), new JLabel("Select the number of dice to attack with"), new JLabel("Select what occupied country to reinforce"), new JLabel("Select the number of troops to reinforce with"), new JLabel(" Select country to moves troops from"), new JLabel( " Select country to move troops too"), new JLabel( "Select the number of troops to fortify with"), new JLabel("Select the cards to trade in.")		// array of JLabels
	
	};
	 
	// JComboBoxes to select countries
	
	protected final JComboBox[] countrySelect = { new JComboBox(), new JComboBox(), new JComboBox(), new JComboBox(), new JComboBox() };
	protected final JComboBox attackFromComboBox = new JComboBox();
	protected final JComboBox attackToComboBox = new JComboBox();
	protected final JComboBox fortifyFrom = new JComboBox();
	protected final JComboBox fortifyTo = new JComboBox();
	
	
	//JComboBoxes to select number of armies
	protected final JComboBox[] numberOfArmies = { new JComboBox(), new JComboBox(), new JComboBox(), new JComboBox(), new JComboBox(), new JComboBox() };
	
	private JLabel defenderDice = new JLabel("Select defender dice count");
	
	// button to execute command
	private JButton execute[] = new JButton[]{ new JButton( "Execute" ),new JButton( "Execute" ),new JButton( "Execute" ), new JButton( "Execute" ), new JButton( "Execute" ), new JButton("Execute") };
	// string for the reinforcement size
	
	// reinforcement size strings
	private final String[] reinforcementSize = { "1", "2", "3", "4", "5", "6", "7" };
	private final String[] diceCountAttacker = { "1", "2", "3" };
	private final String[] diceCountDefender = { "1", "2", "3" };
	private final String[] diceCountDefender2 = { "1", "2", "3"};
	private final String[] diceCountDefender1 = { "1", "2", "3"};
	private JLabel label;
	private JCheckBox[] cardBoxes;
	JButton button = new JButton("Trade In");
	
	
	// refreshes components
	public void refresh(){
		label.setText("Player: " + this.game.getCurrentPlayer().getName() + " has " + this.game.getCurrentPlayer().unplacedArmies + " unplaced armies. " + "The turn number is: " + this.game.getTurnNumber() );
		reinforceAction();				
		countrySelect[2].repaint();
		numberOfArmies[2].repaint();
		numberOfArmies[5].repaint();
		fortifyTo.repaint();
		numberOfArmies[4].repaint();
		label.repaint();
		this.repaint();
		
	}// end of refresh method
	
	//refreshes attack components
	public void refreshAttack(){
		attackFromComboBox.repaint();
		numberOfArmies[0].repaint();
		numberOfArmies[4].repaint();
		attackToComboBox.repaint();
		this.repaint();
	}// end of refreshAttack
	
	public MainPlayScreen(Game game){
		super("Risk"); // call to super class
		this.game = game;
		
		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout( layout );  // sets layout
		
		
		label = new JLabel("Turn number goes here..");
		add(label);
		this.refresh();
	
		
		
		// instantiate and set the size of the actionPanel
		this.panels[0] = new JPanel();		
		this.panels[0].setSize(500,250);
				
		// instantiate and set the size of the reinforcePanel
		this.panels[1] = new JPanel();	
		this.panels[1].setSize( 500,250 );
				
		// instantiate and set the size of the Panel
		this.panels[2] = new JPanel();		
		this.panels[2].setSize( 500, 250 );
		
		//instantiate and set the size of the riskMap
		this.riskMap = new JLabel();			
		this.riskMap.setSize(500,500);
		
		// item lister for attack from
		// gets number of armies depending on country armies
		attackFromComboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event){
				
				if(event.getStateChange() == ItemEvent.SELECTED){
					
					// gets selected item
					final String s = attackFromComboBox.getSelectedItem().toString();
					// gets the country object
					Country country = MainPlayScreen.this.game.getBoard().getCountryByName(s);
					
					// gets the country armies
					 final int armies = MainPlayScreen.this.game.countryArmies.get(country);
					// sets number of dice in JComboBox
					 numberOfArmies[4].removeAllItems();
					 for (int i = 1; i <= Math.min(3, armies); i++){
						 numberOfArmies[4].addItem(i);
					 }// end of for loop
					
					
					
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
					
					String selectedText = attackFromComboBox.getSelectedItem().toString();
					Country adj = MainPlayScreen.this.game.getBoard().getCountryByName(selectedText);
					
					// gets adjacent countries to attack
					ArrayList<Country> adjacencies = adj.getAdjacencies();
					attackToComboBox.removeAllItems();
					for (int b = 0; b < adjacencies.size(); b++) {
						Country adjacent = adjacencies.get(b);
						System.out.print(adjacent.getName());
						attackToComboBox.addItem(adjacent.getName());
					} //  of for loop
					
					// repaints components
					attackToComboBox.repaint();
					numberOfArmies[0].repaint();	
					// gets the country being attacked
					String d = attackToComboBox.getSelectedItem().toString();
					Country get = MainPlayScreen.this.game.getBoard().getCountryByName(d);
					// get the number of armies on that country
					final int armies1 = MainPlayScreen.this.game.countryArmies.get(get);
					
					// display armies on attacking and defending countries
					JOptionPane.showMessageDialog( null, "Country attacking: " + s + " " + armies  + "armies" + "\n" + "Country defend " + d + " "  + armies1 + "armies");
					
				}// end of itemStateChaned method
				
			}// // end of if statement
			
	
		});	// end of anonymous class
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// gets adjacent countries
		this.fortifyFrom.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent evenet){
				
				
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// number of  armies varies based on the country selected to fortify
				
				// gets country fortify from
				String selectedText = fortifyFrom.getSelectedItem().toString();
				Country adj = MainPlayScreen.this.game.getBoard().getCountryByName(selectedText);
				
				//get the country armies
				int i = MainPlayScreen.this.game.countryArmies.get(adj);
				
				// array list  of adjacent countries
				ArrayList<Country> adjacencies = adj.getAdjacencies();
				fortifyTo.removeAllItems();
				
				// adds ajacent countries to fortifyTo JComboBox
				for (int b = 0; b < adjacencies.size(); b++) {
					Country adjacent = adjacencies.get(b);
					System.out.print(adjacent.getName());
					fortifyTo.addItem(adjacent.getName());
				}	// end of for loop
			
				// gets country to fortify
				String A = fortifyTo.getSelectedItem().toString();
				
				Country country1 = MainPlayScreen.this.game.getBoard().getCountryByName(A);
				int d = MainPlayScreen.this.game.countryArmies.get(country1);
				
				// adds number of countries based on country armies
				numberOfArmies[2].removeAllItems();
				 for (int a = 0; a < i;  a++){
					 numberOfArmies[2].addItem(a);
					 System.out.print(a);
				 }
				// displays whats being fortified
				JOptionPane.showMessageDialog( null, "Country fortifing from: " + selectedText + " " + i + "armies" + "\n" + "Country being fortified" + A + " " +  d + " armies");
				 
				refresh();	// refreshes components
				
				
				}// end itemStateChanged method
				
			}// end of if statement
		
		
		); // end of item listener
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		//ATTACK TO UPDATED
		this.attackToComboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent event){
			
				if( event.getStateChange() == ItemEvent.SELECTED){
		
					updatAttackArmies(); 

					
				}// end of if statement
				
				
			}// end of itemStateChanged method 
			
		}); // end of item listener 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		this.countrySelect[2].addItemListener( new ItemListener(){
			public void itemStateChanged(ItemEvent event){
				
				if( event.getStateChange() == ItemEvent.SELECTED){
					
					reinforceAction();	// executes reinforce action
				
				} // end of if statement
				// gets country spring, object, and country armies
				String s = countrySelect[2].getSelectedItem().toString();
				Country country = MainPlayScreen.this.game.getBoard().getCountryByName(s);
				final int z = MainPlayScreen.this.game.countryArmies.get(country);
				
				// displays whats being reinforced
				JOptionPane.showMessageDialog( null, "Country to reinforce: " + s + " armies " + z);
				
				
			}	// of itemStateChaned method
			
		}	// end of anonymous class
		
	);// end of ItemListener
				
		try{		
			File file = new File("RiskBoard2.jpeg");
			BufferedImage ico = ImageIO.read(file);											// reads the map onto the gui
			System.out.println(file.getAbsolutePath());
			riskMap.setIcon(new ImageIcon(ico.getScaledInstance(this.riskMap.getWidth(), this.riskMap.getHeight(), Image.SCALE_SMOOTH)));
		}// end of try block
		 catch (Exception e){
			System.out.println(e.getMessage());
			//Couldn't load map.								// exception handling catch block
			System.out.println("Could not load map. Exiting.");
			System.exit(1);
		} // end of catch block
		
		this.add(riskMap);
		this.add(panels[0]);
		for ( int i = 0; i < userAction.length; i++){
			panels[0].add( userAction[ i ] );
		}// end of for loop
		
		
		// action listener to execute attack command
		execute[2].addActionListener(
				new ActionListener()				{
					public void actionPerformed( ActionEvent e)
					{
						
						Game game = MainPlayScreen.this.game;
						
						System.out.println("Clicked");
						// adds current players countries to the JComboBox
						for(Country country : game.getCurrentPlayer().getOwnedCountries() ){		// get current players countries
							attackFromComboBox.addItem( country.toString() );	
						
						} // end of for loop
								
						// gets country attacker string, add object
						String A  = attackFromComboBox.getSelectedItem().toString();			//action listener for attack command
						Country countryA = MainPlayScreen.this.game.getBoard().getCountryByName(A);
						
						//number of dice to roll attack
						String B = numberOfArmies[0].getSelectedItem().toString();
						int attackDice = Integer.parseInt(B);
						
												
						// defender country
						String D = attackToComboBox.getSelectedItem().toString();
						Country countryD = MainPlayScreen.this.game.getBoard().getCountryByName(D);
					
						// number of dice to roll defend
						String C = numberOfArmies[4].getSelectedItem().toString();
						int defendDice = Integer.parseInt(C);
						
						// pass parameters to the resolveBattle method
						String s = MainPlayScreen.this.game.resolveBattle(countryA, attackDice, countryD, defendDice);		
					
						
						// displays what being attacked
			 			JOptionPane.showMessageDialog( null, "You attacked:" + D + "  "  +  "From:" + A + " with"  +  B + "number of armies" );
			 			
			 			// displays result of battle
			 			JOptionPane.showMessageDialog( null, s );
			 			
		 // lose main play screen open riskButtonFrame
			 			 
			 			// if player occupies all the countries they win
			 			 if( MainPlayScreen.this.game.getBoard().getCountries().contains(game.getCurrentPlayer().playerCountries)){
			 				JOptionPane.showMessageDialog(null, "The game is over." + "Player:" + game.getCurrentPlayer().getName() + "Wins");			 						
			 				RiskButtonFrame riskButtonFrame = new RiskButtonFrame();
			 				riskButtonFrame.setVisible( true );	
			 				MainPlayScreen mainPlayScreen = null;
			 				mainPlayScreen.setVisible( false );
			 				
			 						
			 			 }// end of if statement
			 			
			 			 			 			
					}// end of actionPerformed method
				}// end of anonymous class
		
				); // end of execute action listener
		
			
		//executes the reinforce command	
		execute[1].addActionListener(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent e)
					{	
						
						
						Game game = MainPlayScreen.this.game;
						
						
						// country to reinforce
						String A  = countrySelect[2].getSelectedItem().toString();		
						Country country =  MainPlayScreen.this.game.getBoard().getCountryByName(A);
						
						// number of armies to reinforce with
						int n = numberOfArmies[1].getSelectedIndex();
						n = n + 1;
						System.out.println("Removing " + n);
							
						// increments country armies
						game.countryArmies.put(country, game.countryArmies.get(country) + n);
						game.getCurrentPlayer().decrementArmies(n);
					
						// there are no unplaced armies 
						// remove all element from JComboBox
						if( MainPlayScreen.this.game.getCurrentPlayer().getUnplacedArmies() == 0){
							numberOfArmies[0].removeAllItems();
						} // end of if statement		
						refresh();					// refreshes components
						numberOfArmies[0].repaint();
						
						// displays what is being reinforced	
			 			JOptionPane.showMessageDialog( null, "  You reinforced: "   + A + "  " +  "Number of armies to reinforce with: "   + n );					
					} // end of method actionPerformed
				} // end of anonymous class
		
				); // end of action listener
		// action listener fortify command
		execute[0].addActionListener(
				new ActionListener()
				{
					public void actionPerformed( ActionEvent e)
					{
						
						Game game = MainPlayScreen.this.game;
						
						// gets selected countries
						Country countryFrom = game.getBoard().getCountryByName(fortifyFrom.getSelectedItem().toString());
						Country countryTo = game.getBoard().getCountryByName(fortifyTo.getSelectedItem().toString());
						int numberMoved = Integer.parseInt(numberOfArmies[2].getSelectedItem().toString());
						
						// gets country armies
						int fromArmies = game.countryArmies.get(countryFrom);
						int toArmies = game.countryArmies.get(countryTo);
						
						// adds to and decrements armies
						game.countryArmies.put(countryFrom, fromArmies-numberMoved);
						game.countryArmies.put(countryTo, toArmies+numberMoved);
						
						//country to fortify from
						String A  = fortifyFrom.getSelectedItem().toString();			//executes fortify command
						Country country = game.getCountryFromText(A);
						System.out.println(A + "Country to fortify from");
						System.out.println(country.toString());
					
						// number of armies to fortify with
						String B = numberOfArmies[2].getSelectedItem().toString();
						int fortifyNumberOfArmies =  Integer.parseInt(B);
						System.out.println(B + "number of armies to fortify");
						
						//country to fortify
						String C = fortifyTo.getSelectedItem().toString();
						System.out.println(C + "country to fortify");
						Country countryC = game.getCountryFromText(C);
					
						// fortify countries
						MainPlayScreen.this.game.countryArmies.put(country, game.countryArmies.get(country) - fortifyNumberOfArmies);
						MainPlayScreen.this.game.countryArmies.put(countryC, game.countryArmies.get(countryC) + fortifyNumberOfArmies);
						
			 			JOptionPane.showMessageDialog( null, " You moved fortified from: "   + A + "  " + "\n" +  "You moved fortified too: "   + C + "  " + "\n" + " Number of armies moved: "  + B );					
						
					} // end of method actionPerformed
				} // end of anonymous class
		
				); // end of executeB actionListener
		
		
		// action listener for trade in cards 
		execute[5].addActionListener(
				new ActionListener()
		{
			@Override
			
			public void actionPerformed(ActionEvent e)		
			{
		
				
				ArrayList<Card> tradedCards = new ArrayList<Card>();
			
					for (int i = 0; i < MainPlayScreen.this.game.getCurrentPlayer().cards.size(); i++){
					if (cardBoxes.isSelected()){		// determines the number of cards traded in 
						tradedCards.add(MainPlayScreen.this.game.getCurrentPlayer().cards.get(i));
						// too many cards
						if( tradedCards.size() > 3){
							JOptionPane.showMessageDialog(null, "You cannot trade in that many cards");
						}
						// too few cards
						if( tradedCards.size() < 3){
							JOptionPane.showMessageDialog(null, "You traded in too few cards");
						}
						if( tradedCards.size() == 3){
							Design design = null;
							
							Country country = null;
							Game game = null;
							Card card = null;
							Deck deck = null;
							
							Card card5= new Card();
							//regualr cards
							Card card1 = new Card(design.ARTILLERY, country );
							Card card2 = new Card(design.CALVALRY, country);
							Card card3 = new Card(design.INFANTRY, country);	
							
							// player hand contain 5 or 6 card player must trade in cards
							// not exactly sure how to implement
					
								if(tradedCards.contains(card)){
									//Deck deck;
									int armies1 = MainPlayScreen.this.game.getCurrentPlayer().getDeck().tradeInCards(tradedCards);	
									JOptionPane.showMessageDialog(null, "You traded in " + tradedCards.size() + " cards and aquired" + armies1 + "armies");							
									game.getCurrentPlayer().incrementArmies(armies1);
									
								}
							
								// trade in 3 card. 3 different designs
								else if(tradedCards.contains(card1) && tradedCards.contains(card2) && tradedCards.contains(card3)){
									
									int armies2 = deck.tradeInCards(tradedCards);
									JOptionPane.showMessageDialog(null, "You traded in " + tradedCards.size() + " cards and aquired" + armies2 + "armies");	
									game.getCurrentPlayer().incrementArmies(armies2);
								} // end of else if
								//traded in 3 cards. 1 wild and 2 regular cards.
								else if(tradedCards.contains(card) && tradedCards.contains(card1) || tradedCards.contains(card2) || tradedCards.contains(card3) && tradedCards.contains(card1) || tradedCards.contains(card2) || tradedCards.contains(card3)){
									Deck deck2;
									int armies = deck.tradeInCards(tradedCards);	
									JOptionPane.showMessageDialog(null, "You traded in " + tradedCards.size() + " cards and aquired" + armies + "armies");	
									game.getCurrentPlayer().incrementArmies(armies);
								} // end of else if
								// unhandled case
								else{
									JOptionPane.showMessageDialog(null, "Uncaught exception");	
								} // end of else statement
							
						
						}// end of actonPerformed method
					} // end of if statement
				} // end of for loop
				JOptionPane.showMessageDialog(null, "Traded in " + tradedCards.size() + " cards.");	// displays a JOptionPane showing the number of cards traded in
			
			
			} // end of method actionPerformed
		//	tradeInPanel.setVisible(false);
				
		//return tradeInPanel;
			} // end of method actionPerformed
		
				); // end of action listener 
		
		
			
		userAction[3].addActionListener(
				new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)		// action listener for fortify
			{
				showPanel(createFortifyPanel(MainPlayScreen.this.game.getCurrentPlayer()));
				//MainPlayScreen.this.refresh();
			} // end of actionPerformed method
		}); // end of actionListener
		
		
		userAction[2].addActionListener(
				new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)			// action listener for attack
			{
				showPanel(createAttackPanel(MainPlayScreen.this.game.getCurrentPlayer()));
				MainPlayScreen.this.refresh();
			} // end of method actionPerformed
		}); // end of actionListener
			
		userAction[0].addActionListener(
				new ActionListener()
		{
				
			@Override
			public void actionPerformed(ActionEvent e)			// action listener for trading in cards
			{
				showPanel(createTradeInPanel(MainPlayScreen.this.game.getCurrentPlayer()));		
			} // end of method actionPerformed
		}); // end of actionListener
		
		userAction[1].addActionListener(
				new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{													// action listener for reinforcing 
				showPanel(createReinforcePanel(MainPlayScreen.this.game.getCurrentPlayer()));		
			
			} // end of method actionPerformed
		}); // end actionListener
		
		userAction[4].addActionListener(
				new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
					JFileChooser chooser = new JFileChooser();		// action listener to save game
					chooser.showSaveDialog(null);
				
			} // end of method actionPerformed

				
		} // end of actionListener
	 
	); // end of action listener
		
		userAction[5].addActionListener(
				new ActionListener()
		{
			@Override
			
			public void actionPerformed(ActionEvent e)		// action listener to exit the game
			{
				dispose();			
			} // end of method actionPerformed
		}); // end of action listener 
		
		userAction[6].addActionListener(
				new ActionListener()
		{
			@Override
			
			public void actionPerformed(ActionEvent e)		// action listener to pass turn
			{
				// end turn
				MainPlayScreen.this.game.incrementTurn();	
				 JOptionPane.showMessageDialog(null, "Player: " + MainPlayScreen.this.game.getCurrentPlayer().getName() + "turn ended." );
			 
				 refresh();
 				 
			} // end of method actionPerformed
		}); // end of action listener 
	
			numberOfArmies[0] = new JComboBox( diceCountAttacker );
			numberOfArmies[1] = new JComboBox( reinforcementSize );		// adds the numberOfArmies to the JComboBoxs to select the number of armies
			numberOfArmies[2] = new JComboBox( reinforcementSize );
			numberOfArmies[3] = new JComboBox( reinforcementSize );
			numberOfArmies[4] = new JComboBox( diceCountDefender );
			numberOfArmies[5] = new JComboBox( diceCountDefender2 );
			
			countrySelect[0].setMaximumRowCount( 7 );
			countrySelect[1].setMaximumRowCount( 7 );
			countrySelect[2].setMaximumRowCount( 7 );
			numberOfArmies[0].setMaximumRowCount( 7 );				//sets the number of rows on the JComboBox
			numberOfArmies[1].setMaximumRowCount( 7 );
			numberOfArmies[2].setMaximumRowCount( 7 );	
			numberOfArmies[3].setMaximumRowCount( 7 );
			
			this.refresh();
			
	}
	
	private void showPanel(JPanel newPanel){
		if (this.panels[3] != null){
			this.panels[3].setVisible(false);		//sets the currentPanel selected to true
		} // end of if statement
		this.panels[3] = newPanel;
		this.panels[3].setVisible(true);
		
	} // end of method showPanel
	
	private JPanel createFortifyPanel(Player p){
		this.add( panels[2] );
		fortifyFrom.removeAllItems();
		for (Country country : this.game.getCurrentPlayer().getOwnedCountries()){
			
			fortifyFrom.addItem(country.getName());
		}
		fortifyTo.removeAllItems();
		for (Country country1 : this.game.getCurrentPlayer().getOwnedCountries()){
			fortifyTo.addItem(country1.getName());
		}
		this.panels[2].add( labels[ 5 ]);
		this.panels[2].add( fortifyFrom );
		this.panels[2].add( labels[ 6 ]);
		this.panels[2].add( numberOfArmies[2] );			// panel for fortify command 
		this.panels[2].add( labels[ 7 ]);
		this.panels[2].add( fortifyTo );
		this.panels[2].add( execute[0] );
		panels[2].setVisible( false );
		return panels[2];
	} // end of method createFortifyPanel
	
	private JPanel createReinforcePanel(Player p){
		this.add( panels[1] );
		this.panels[1].add( labels[ 3 ]);
		
		
		for (Country c : this.game.getCurrentPlayer().getOwnedCountries()){
			countrySelect[2].addItem(c.getName());
		}																		//reinforce panel
		this.panels[1].add( countrySelect[2] );		
		this.panels[1].add(labels[ 4 ]);
		this.panels[1].add( numberOfArmies[1] );
		this.panels[1].add( execute[1] );
		panels[1].setVisible(false);
		return panels[1];
	} // end of method createReinforcePanel 
	
	private JPanel createAttackPanel(Player p){
		JPanel attackPanel = new JPanel();
		this.add(attackPanel);
		attackPanel.setSize(500,250);
		for(Country country : game.getCurrentPlayer().getOwnedCountries() ){		// get current players countries
			attackFromComboBox.addItem( country.toString() );	
		
		}
		
		attackPanel.add( labels[ 0 ] );			
		attackPanel.add( attackFromComboBox );
		attackPanel.add(labels[ 1 ]);					// panel for attack Command
		attackPanel.add( attackToComboBox );	
		attackPanel.add( labels[ 2 ] );
		attackPanel.add( numberOfArmies[4] );		//attack dice
		attackPanel.add(defenderDice);
		attackPanel.add( numberOfArmies[5]);		// defend dice
		attackPanel.add( execute[2] );	//Populate with attacker's Countries.
		attackFromComboBox.removeAllItems();
		for (Country c : this.game.getCurrentPlayer().getOwnedCountries()){
			attackFromComboBox.addItem(c.getName());
		}
		attackPanel.setVisible(false);
		return attackPanel;
	}// end of method createAttackPanel
	
	private JPanel createTradeInPanel(Player p){
		
		JPanel tradeInPanel = new JPanel();				// panel for trading in cards
		this.add(tradeInPanel);						
		tradeInPanel.add( labels[ 8 ] );
			
		JCheckBox cardBoxes;
		final JCheckBox[] boxes = new JCheckBox[MainPlayScreen.this.game.getCurrentPlayer().cards.size()];
		
		final ArrayList<Card> playerCard = MainPlayScreen.this.game.getCurrentPlayer().cards;
		this.cardBoxes = new JCheckBox[playerCard.size()];
		
		for (int i = 0; i < playerCard.size(); i++){
			boxes[i] = new JCheckBox(MainPlayScreen.this.game.getCurrentPlayer().cards.get(i).getCountry() + "--" + MainPlayScreen.this.game.getCurrentPlayer().cards.get(i).getDesign().toString());		//Sets up the check boxes to select cards
			tradeInPanel.add(boxes[i]);
		} // end of for loops		
		tradeInPanel.add(button);
		
		tradeInPanel.setVisible(false);		
		return tradeInPanel;
	
	}	// end of createTradeInPanel
	
	

			
			
		
			
	
	
		
		
		 // end of actionListener to trade in cards
		
	 // end of constructor
	//updates the attackers dice
	
	// updates dice depending on country
	public void updatAttackArmies(){
		
		final String s = attackToComboBox.getSelectedItem().toString();
		Country country = MainPlayScreen.this.game.getBoard().getCountryByName(s);
			
		final int armies = MainPlayScreen.this.game.countryArmies.get(country);
		System.out.println(s + " Defender Armies: " + armies);
		
		if( armies == 1){
			numberOfArmies[5].removeAllItems();
			numberOfArmies[5].addItem(1);
		}// end of if statement
		
		else if( armies == 2){
			numberOfArmies[5].removeAllItems();
			numberOfArmies[5].addItem(1);
			numberOfArmies[5].addItem(2);
			
		} // end of else if statement
		
		else{
			numberOfArmies[5].removeAllItems();
			numberOfArmies[5].addItem(1);
			numberOfArmies[5].addItem(2);
			numberOfArmies[5].addItem(3);
			
		}// end of else statement
		numberOfArmies[5].repaint();
	}// end updatedAttackArmies
	
	//updates the dice for the reinforce command 
	public void reinforceAction(){
		int u = MainPlayScreen.this.game.getCurrentPlayer().getUnplacedArmies();
		
		if( u == 0){
			numberOfArmies[1].removeAllItems();
		}	// end of if statement
		
		else{					
			numberOfArmies[1].removeAllItems();
			for( int i = 1; i <= u; i++){
				System.out.print(i);
				
				numberOfArmies[1].addItem(i);
				
			}// end of else
		} // end of else
		
		numberOfArmies[1].repaint();
	} // end of if
	
		
	
	public static void main( String[] args ) throws Exception{
		
		// mainPlayScreen = new MainPlayScreen();
		Game game = null;
		
		try {
			MainPlayScreen mainPlayScreen = new MainPlayScreen(game);
			mainPlayScreen.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	// sets the default close operation
			mainPlayScreen.setSize( 900, 400);		//sets the size of the of the mainPlayScreen
			mainPlayScreen.setVisible( true );			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	// creates an object of MainPlayScreen
			// sets the visibility of the gui to true
	} // end of main method
	
} // end of class MainPlayScreen
