package com.example.blackjack;

public class card {
	private char suit;
	private int value;
	
	
	public card(char s, int v)
	{
		suit = s;
		value = v;
	}
	
	
	public char getSuit() {
		return suit;
	}
	public void setSuit(char s) {
		suit = s;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int v) {
		value = v;
	}	
}
