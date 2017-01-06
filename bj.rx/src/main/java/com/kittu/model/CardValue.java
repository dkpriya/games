package com.kittu.model;

public enum CardValue {
    _ACE   (1,11), 
    _2     (2,2) ,
    _3     (3,3),
    _4     (4,4),
    _5     (5,5),
    _6     (6,6),
    _7     (7,7),
    _8     (8,8),
    _9     (9,9),
    _10    (10,10),
    _JACK  (10,10),
    _QUEEN (10,10),
    _KING  (10,10);
    private int value;
    private int optionalValue=0;
    
    CardValue(int val, int optionalVal){
    	value = val;
    	optionalValue = optionalVal;
    }
    public int getValue(){
    	return this.value;
    }
    public int getOptionalValue(){
    	return this.optionalValue;
    }
 }
