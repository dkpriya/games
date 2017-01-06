package com.kittu.model;

public class Dealer extends Player {
	 
	public Dealer(){
		super("Dealer");
 	}

	public static boolean isDealer(Player p) {
		if(p instanceof Dealer) return true;
		return false;
	}
	
	

}
