package com.kittu.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
 	
	private List<Card> cards = new ArrayList<Card>();
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	private int totalMinValue = 0;
	private boolean inGame = true;
	private boolean blackJack = false;
	private boolean stand = false;
	private boolean bust = false;
	
	private long cash=1000; // TODO: default change that to user entered amounts
	private long bet = 0;
	private long won = 0;
	
	public long getCash() {
		return cash;
	}

	public void setCash(long cash) {
		this.cash = cash;
	}

	public long getBet() {
		return bet;
	}

	public void setBet(long bet) {
		this.bet = bet;
	}

	public long getWon() {
		return won;
	}

	public void setWon(long won) {
		this.won = won;
	}

	public boolean isBust() {
		return bust;
	}

	public void setBust(boolean bust) {
		this.bust = bust;
	}

	public int getTotalMinValue() {
		return totalMinValue;
	}

	public void setTotalMinValue(int totalMinValue) {
		this.totalMinValue = totalMinValue;
	}

	private short aceCount=0; 
 	
	public short getAceCount() {
		return aceCount;
	}

	public void setAceCount(short aceCount) {
		this.aceCount = aceCount;
	}

	public Player(String string) {
		this.name = string;
	}

	public Player(){
		name="Player";
	}
	public void addCard(Card card){
		cards.add(card);
		if(card.getValue() == CardValue._ACE) aceCount++;
		totalMinValue+=card.getValue().getValue();

		if(totalMinValue > Game.BLACKJACK_COUNT){
			setInGame(false);
	 		setBust(true);
 		}
 		blackJack = hitBlackJack();
  		
 	}
	
	public boolean hitBlackJack() {
		if(totalMinValue == Game.BLACKJACK_COUNT) return true;
		int temp = totalMinValue;
		for(int i=1;i<=aceCount;i++){
			temp+=10;
			if(temp == Game.BLACKJACK_COUNT) return true;
		}
		return false;
	}

	public int[] getCardsValue(){
   		return new int[]{totalMinValue, aceCount==0?totalMinValue:totalMinValue+(10*aceCount)};
 	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getOptimumCardsValue() {
		int[] values = getCardsValue();
		if(getAceCount()==0) 
			return values[0];
		else{
			//if more than 21 and have aces, report the highest count possible treating ace as 1 
			if(values[1] > Game.BLACKJACK_COUNT){
				int highestCount=values[1];
				for(int i=1;i<=getAceCount();i++){
					highestCount-=10;
					if(highestCount <Game.BLACKJACK_COUNT) return highestCount;
				}
				return highestCount;
			}else{ // max value less than 21 - so go ahead and claim it
				return values[1];
			}
		}
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder( getName()).append( ": [");
		for(Card card : getCards()){
			sb.append( card.getSign() ).append( card.getValue()).append(" , ");
		}
		//sb.deleteCharAt(sb.length());
		sb.append("]");
		return sb.toString();
	}

	public boolean isBlackJack() {
		return blackJack;
	}

	public void setBlackJack(boolean hasBlackJack) {
		this.blackJack = hasBlackJack;
	}

	public boolean isStand() {
		return stand;
	}

	public void setStand(boolean stand) {
		this.stand = stand;
	}
}
