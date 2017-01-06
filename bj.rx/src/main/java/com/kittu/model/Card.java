package com.kittu.model;

public class Card {
	public Card(Sign sign2, CardValue cVal) {
		this.sign = sign2;
		this.value = cVal;
	}
	private Sign sign;
	private CardValue value;
	
	public Sign getSign() {
		return sign;
	}
	public void setSign(Sign sign) {
		this.sign = sign;
	}
	public CardValue getValue() {
		return value;
	}
	public void setValue(CardValue value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		
		return this.sign+""+this.value;
	}
}
