package com.kittu.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Game {
	@Autowired
	private Deck deck ;
	
	@Autowired
	private Dealer dealer;
	
 	List<Player> players;
 	
 	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public static final int BLACKJACK_COUNT=21;
 	public static final int DEALER_MUST_HIT=17;
 	
 	private int playerCount=1;
 	
 	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	public Game(){
		if(deck == null) deck = new Deck();
		if(dealer == null) dealer = new Dealer();
		
 		players = new ArrayList<Player>();
 		for(int i=1;i<=5;i++)	players.add( new Player("Player "+ i));
  	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void validate() throws Exception{
		if(playerCount > 5 ) throw new Exception("Maximum players allowed are 5.");
		if(playerCount <1 ) throw new Exception("Minimum players  needed are 1.");
		
		if(playerCount <5){
			for(int i=playerCount;i<=playerCount;i++){
				players.get(i-1).setInGame(false);
			}
		}
	}
	
	public void distribute() {
		List<Player> playersIter = new ArrayList<Player>();
		// Add the Dealer's card first
		playersIter.add(dealer);
		playersIter.addAll(this.players);
		
		for(int i=0;i<2;i++)  {
			for(Player p : playersIter){
				if(p.isInGame()){
 					Card c = deck.getNext();
					p.addCard(c);
/*  					if(i>0 && Dealer.isDealer(p)){
  						System.out.println( p.getName()+"  XXXXX_XX");
 					}else{
						System.out.println( p.getName()+"  "+ c.getSign()+c.getValue());
 					}
*/				}
			}
		}
		}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	public void hit(Player p) {
		Card c = deck.getNext();
		p.addCard(c);
		System.out.println( c.toString() );
	 	p.toString();
	}
	
}
