package com.kittu.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.kittu.model.Dealer;
import com.kittu.model.Deck;
import com.kittu.model.Game;
import com.kittu.model.Player;

@Controller
public class BJGameController {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BJGameController.class);

	private final static int PLAYERS = 2;
	private final static int DECKS =1;
	
	@Autowired
	Game game;
	 
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String start(ModelMap model){
		
		
 		model.addAttribute(" Start","Welcome");
		model.addAttribute("Players", "2 - dealer and two player");

		game.setPlayerCount(PLAYERS);
 		game.distribute();

  		List<Player> blackJackPlayers = new ArrayList<Player>();
 
 		model.addAttribute("dealer cards", game.getDealer().toString());
   		model.addAttribute("dealer count", game.getDealer().getOptimumCardsValue());
  		if(game.getDealer().isBlackJack()) blackJackPlayers.add(game.getDealer());
  		 
   		for(int i=0;i<PLAYERS;i++){
   			Player p = game.getPlayer(i);
   			model.addAttribute(p.getName()+" cards", p.toString());
   	   		model.addAttribute( p.getName()+" count", p.getOptimumCardsValue());
   	   		if(p.isBlackJack()) blackJackPlayers.add(p);
    	}
   		
   		if(blackJackPlayers.size()==1){
   			if(blackJackPlayers.get(0) instanceof Dealer) 
   				model.addAttribute("PLAYERS LOST, BLACKJACK ", blackJackPlayers.get(0).getName());
   			else
   				model.addAttribute("BLACKJACK DEAL", blackJackPlayers.get(0).getName());
   		}
   		
   		if(blackJackPlayers.size()>1){
   			StringBuffer winners = new StringBuffer();
   			boolean dealerInBlackJack = false;
   			for(Player p : blackJackPlayers) {
   				winners.append(" ").append( p.getName()).append(",");
   				if(p instanceof Dealer) dealerInBlackJack = true;
   			}
   			winners.deleteCharAt(winners.length()-1);
   			if(!dealerInBlackJack) 
   				model.addAttribute("BLACKJACK Winner", blackJackPlayers.get(0).getName());
   			else{
   				model.addAttribute("PUSH : BLACKJACK BY "+ winners.toString());
   			}
   		} 
    	
   		
   		if(game.getDealer().getOptimumCardsValue() > Game.BLACKJACK_COUNT){
   			
   		}
		return "index";
	}
	
}
