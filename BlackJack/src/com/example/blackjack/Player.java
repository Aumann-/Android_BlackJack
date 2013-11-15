package com.example.blackjack;

import android.content.Context;

public class Player {
	private Integer Total;
	private Integer cardTotal;
	
	
	public Player()
	{
		Total = 0;
		cardTotal = 0;
	}
	
	public Integer getTotal()
	{
		return Total;
	}
	
	public void setTotal(int v)
	{
		if (v == 0)
			Total = 0;
		else
			Total += v;
	}
	
	public Integer getCard()
	{
		return cardTotal;
	}
	
	public void setCard()
	{
		cardTotal++;
	}
	
	//method to check if player busted
	public Boolean checkBust()
	{
		if (this.Total > 21)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
