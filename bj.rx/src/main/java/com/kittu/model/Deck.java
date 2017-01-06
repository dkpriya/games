package com.kittu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

 
@Component
public class Deck {
 	List<Card> deckCards = new ArrayList<Card>(52);
 	int currentIndex=0;
 	
 	public Deck(){
 		init();
 		shuffle();
 	}

 	public void reset(){
 		deckCards.clear();
 		currentIndex=0;
 		init();
 		shuffle();
 	}
 	public Card getNext(){
 		if(currentIndex < deckCards.size());
 		return deckCards.get(currentIndex++);
 	}
 	
	private void shuffle() {
		Collections.shuffle(deckCards);
	}

	private void init() {
		Sign[] signs = Sign.values();
		for(Sign sign : signs){
			for( CardValue cVal: CardValue.values()){
				deckCards.add(new Card(sign, cVal));
			}
		}
 	}
	
}
