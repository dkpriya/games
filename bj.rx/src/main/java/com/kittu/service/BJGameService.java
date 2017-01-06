package com.kittu.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;

import com.kittu.model.Card;
import com.kittu.model.Dealer;
import com.kittu.model.Game;
import com.kittu.model.Player;

public class BJGameService {

	@Autowired
	Game game;

	public BJGameService(){
		if(this.game == null) game  = new Game();
	}
	
	/*
	 * Basic logic of the game with the Console as UI
	 */
	public void playGameOnConsole(){
		game.distribute();
 		showCards(false);
        getAndHitPlayersChoice();
 		
 		//Play the Dealer's game
  		hitDealersCards();
 		
 		System.out.println("\n\n\n#################################RESULTS###############################");
 		showCards(true);
 		evaluateWinners();
  		System.out.println("########################RESTART TO TRY YOUR LUCK########################");
	}

	private void hitDealersCards() {
		Player d = game.getDealer();
 		//Show the hidden card of the dealer
 		System.out.println("###################################");
 		System.out.println( d.toString());
 		System.out.println("###################################");
 		 
 		if(d.isBust()) System.out.println( " Dealer is BUST.");
 		// HARD LIMIT OF 17. Soft limit of 17 rule is not applied currently.
 		//TODO: make the soft limit configurable to evaluate soft 17 rule too.
 		while( !d.isBust() && d.getTotalMinValue() < Game.DEALER_MUST_HIT){
 		  game.hit(d);
  		}
	}

	private void getAndHitPlayersChoice() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
 		for(Player p : game.getPlayers()){
 			// Continue to ask for user's choice if the user is 
 			// 1. IS NOT BUSTED
 			// 2. HAS NOT HIT BLACKJACK
 			// 3. Did not chose to STAND YET
 			while(p.isInGame() && !p.isBlackJack() && !p.isStand()){
 				// Ask for the player input to hit or stand
 				// if Hit call the distribute and check if the user is bust
 				//if stand,  break the loop and move on to the next player
 				char userChoice = 'S';
 				System.out.println( p.getName() + " Current Count :"+p.getOptimumCardsValue());
  				System.out.println( p.getName() + " please press Hit(H) OR Stand(S or Any key):>");
 
 				try {
 					String input=br.readLine();
 	 				userChoice = input.toLowerCase().charAt(0);
  				} catch (IOException e) {
					System.out.println("User's choice could not be recognised. Please try again");
					continue;
				}
 				
 				executeUserChoice(p, userChoice);
 			}
 			 
 		} // End of for loop
	}

	private void evaluateWinners() {
		Dealer d = game.getDealer();
		int dealerCount = d.getOptimumCardsValue();
 		System.out.println( " DEALER COUNT : "+ dealerCount);
 		boolean winners = false;
 		
		for(Player p : game.getPlayers()){
			int playerCount = p.getOptimumCardsValue();
			System.out.println( p.toString() + "IS BUST :"+p.isBust() );
			
			 if( p.isStand() && !p.isBust() &&  playerCount >  dealerCount ){
				 System.out.println( "WINNER : "+p.toString() );
				 winners=true;
			 }
		}
		
		if(!winners) System.out.println("NO WINNERS. ALL BETS LOST");
		
	}

	private void executeUserChoice(Player p, char userChoice) {
		switch(userChoice){
			case 'h':
				game.hit(p);
 			break;
			default:
				p.setStand(true);
			break;
		}
	}
	
	private void showCards(boolean showDealerCards) {
		StringBuffer sb = new StringBuffer();
		for(Player p : game.getPlayers()){
			sb.append( p.getName() ).append(" : [");
			p.getCards().forEach(c -> sb.append( c.getSign()+" "+c.getValue()+","));
			sb.append("] \n");
		}
		if(showDealerCards){
			sb.append("\n").append(game.getDealer().getName() ).append(" : [");
			game.getDealer().getCards().forEach(c -> sb.append( c.getSign()+" "+c.getValue()+","));
			sb.append("] \n \n");
		}else{
			Card c = game.getDealer().getCards().get(0);
			sb.append(game.getDealer().getName()).append(" :[ ").append(c.getSign()+" "+c.getValue()).append(", XXXXXXX-XX ] \n\n");
		}
		System.out.println(sb.toString());
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
	
	
}
