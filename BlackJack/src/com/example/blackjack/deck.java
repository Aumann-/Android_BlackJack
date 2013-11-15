package com.example.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class deck{
	
	private ArrayList<card> myCards = new ArrayList<card>();
	public ArrayList<Drawable> cards = new ArrayList<Drawable>();
	//Context context;
	private Resources res;
	
	public deck(Context context)
	{
		//cardsRemain = 52;
		res = context.getResources();
		
		
	}
	
	
	public void addCards()
	{
		
		//add to card image list
		
		//spade images
		
		Drawable s_a = res.getDrawable(R.drawable.s_a);
		cards.add(s_a);
		
		Drawable s_2 = res.getDrawable(R.drawable.s_2);
		cards.add(s_2);
		
		Drawable s_3 = res.getDrawable(R.drawable.s_3);
		cards.add(s_3);
		
		Drawable s_4 = res.getDrawable(R.drawable.s_4);
		cards.add(s_4);
		
		Drawable s_5 = res.getDrawable(R.drawable.s_5);
		cards.add(s_5);
		
		Drawable s_6 = res.getDrawable(R.drawable.s_6);
		cards.add(s_6);
		
		Drawable s_7 = res.getDrawable(R.drawable.s_7);
		cards.add(s_7);
		
		Drawable s_8 = res.getDrawable(R.drawable.s_8);
		cards.add(s_8);
		
		Drawable s_9 = res.getDrawable(R.drawable.s_9);
		cards.add(s_9);
		
		Drawable s_10 = res.getDrawable(R.drawable.s_10);
		cards.add(s_10);
		
		Drawable s_j = res.getDrawable(R.drawable.s_j);
		cards.add(s_j);
		
		Drawable s_q = res.getDrawable(R.drawable.s_q);
		cards.add(s_q);
		
		Drawable s_k = res.getDrawable(R.drawable.s_k);
		cards.add(s_k);
		
		//heart images
		
		Drawable h_a = res.getDrawable(R.drawable.h_a);
		cards.add(h_a);
		
		Drawable h_2 = res.getDrawable(R.drawable.h_2);
		cards.add(h_2);
		
		Drawable h_3 = res.getDrawable(R.drawable.h_3);
		cards.add(h_3);
		
		Drawable h_4 = res.getDrawable(R.drawable.h_4);
		cards.add(h_4);
		
		Drawable h_5 = res.getDrawable(R.drawable.h_5);
		cards.add(h_5);
		
		Drawable h_6 = res.getDrawable(R.drawable.h_6);
		cards.add(h_6);
		
		Drawable h_7 = res.getDrawable(R.drawable.h_7);
		cards.add(h_7);
		
		Drawable h_8 = res.getDrawable(R.drawable.h_8);
		cards.add(h_8);
		
		Drawable h_9 = res.getDrawable(R.drawable.h_9);
		cards.add(h_9);
		
		Drawable h_10 = res.getDrawable(R.drawable.h_10);
		cards.add(h_10);
		
		Drawable h_j = res.getDrawable(R.drawable.h_j);
		cards.add(h_j);
		
		Drawable h_q = res.getDrawable(R.drawable.h_q);
		cards.add(h_q);
		
		Drawable h_k = res.getDrawable(R.drawable.h_k);
		cards.add(h_k);
		
		//club images
		Drawable c_a = res.getDrawable(R.drawable.c_a);
		cards.add(c_a);
		
		Drawable c_2 = res.getDrawable(R.drawable.c_2);
		cards.add(c_2);
		
		Drawable c_3 = res.getDrawable(R.drawable.c_3);
		cards.add(c_3);
		
		Drawable c_4 = res.getDrawable(R.drawable.c_4);
		cards.add(c_4);
		
		Drawable c_5 = res.getDrawable(R.drawable.c_5);
		cards.add(c_5);
		
		Drawable c_6 = res.getDrawable(R.drawable.c_6);
		cards.add(c_6);
		
		Drawable c_7 = res.getDrawable(R.drawable.c_7);
		cards.add(c_7);
		
		Drawable c_8 = res.getDrawable(R.drawable.c_8);
		cards.add(c_8);
		
		Drawable c_9 = res.getDrawable(R.drawable.c_9);
		cards.add(c_9);
		
		Drawable c_10 = res.getDrawable(R.drawable.c_10);
		cards.add(c_10);
		
		Drawable c_j = res.getDrawable(R.drawable.c_j);
		cards.add(c_j);
		
		Drawable c_q = res.getDrawable(R.drawable.c_q);
		cards.add(c_q);
		
		Drawable c_k = res.getDrawable(R.drawable.c_k);
		cards.add(c_k);
		
		//diamond images
		Drawable d_a = res.getDrawable(R.drawable.d_a);
		cards.add(d_a);
		
		Drawable d_2 = res.getDrawable(R.drawable.d_2);
		cards.add(d_2);
		
		Drawable d_3 = res.getDrawable(R.drawable.d_3);
		cards.add(d_3);
		
		Drawable d_4 = res.getDrawable(R.drawable.d_4);
		cards.add(d_4);
		
		Drawable d_5 = res.getDrawable(R.drawable.d_5);
		cards.add(d_5);
		
		Drawable d_6 = res.getDrawable(R.drawable.d_6);
		cards.add(d_6);
		
		Drawable d_7 = res.getDrawable(R.drawable.d_7);
		cards.add(d_7);
		
		Drawable d_8 = res.getDrawable(R.drawable.d_8);
		cards.add(d_8);
		
		Drawable d_9 = res.getDrawable(R.drawable.d_9);
		cards.add(d_9);
		
		Drawable d_10 = res.getDrawable(R.drawable.d_10);
		cards.add(d_10);
		
		Drawable d_j = res.getDrawable(R.drawable.d_j);
		cards.add(d_j);
		
		Drawable d_q = res.getDrawable(R.drawable.d_q);
		cards.add(d_q);
		
		Drawable d_k = res.getDrawable(R.drawable.d_k);
		cards.add(d_k);
		
		
		//add to card value list
		
		//add spades
		card S_a = new card('S',1);
		myCards.add(S_a);
		
		card S_2 = new card('S',2);
		myCards.add(S_2);
		
		card S_3 = new card('S',3);
		myCards.add(S_3);
		
		card S_4 = new card('S',4);
		myCards.add(S_4);
		
		card S_5 = new card('S',5);
		myCards.add(S_5);
		
		card S_6 = new card('S',6);
		myCards.add(S_6);
		
		card S_7 = new card('S',7);
		myCards.add(S_7);
		
		card S_8 = new card('S',8);
		myCards.add(S_8);
		
		card S_9 = new card('S',9);
		myCards.add(S_9);
		
		card S_10 = new card('S',10);
		myCards.add(S_10);
		
		card S_j = new card('S',11);
		myCards.add(S_j);
		
		card S_q = new card('S',12);
		myCards.add(S_q);
		
		card S_k = new card('S',13);
		myCards.add(S_k);
		
		//add hearts
		card H_a = new card('H',1);
		myCards.add(H_a);
		
		card H_2 = new card('H',2);
		myCards.add(H_2);
		
		card H_3 = new card('H',3);
		myCards.add(H_3);
		
		card H_4 = new card('H',4);
		myCards.add(H_4);
		
		card H_5 = new card('H',5);
		myCards.add(H_5);
		
		card H_6 = new card('H',6);
		myCards.add(H_6);
		
		card H_7 = new card('H',7);
		myCards.add(H_7);
		
		card H_8 = new card('H',8);
		myCards.add(H_8);
		
		card H_9 = new card('H',9);
		myCards.add(H_9);
		
		card H_10 = new card('H',10);
		myCards.add(H_10);
		
		card H_j = new card('H',11);
		myCards.add(H_j);
		
		card H_q = new card('H',12);
		myCards.add(H_q);
		
		card H_k = new card('H',13);
		myCards.add(H_k);
		
		//add clubs
		card C_a = new card('C',1);
		myCards.add(C_a);
		
		card C_2 = new card('C',2);
		myCards.add(C_2);
		
		card C_3 = new card('C',3);
		myCards.add(C_3);
		
		card C_4 = new card('C',4);
		myCards.add(C_4);
		
		card C_5 = new card('C',5);
		myCards.add(C_5);
		
		card C_6 = new card('C',6);
		myCards.add(C_6);
		
		card C_7 = new card('C',7);
		myCards.add(C_7);
		
		card C_8 = new card('C',8);
		myCards.add(C_8);
		
		card C_9 = new card('C',9);
		myCards.add(C_9);
		
		card C_10 = new card('C',10);
		myCards.add(C_10);
		
		card C_j = new card('C',11);
		myCards.add(C_j);
		
		card C_q = new card('c',12);
		myCards.add(C_q);
		
		card C_k = new card('C',13);
		myCards.add(C_k);
		
		
		//add diamonds
		card D_a = new card('D',1);
		myCards.add(D_a);
		
		card D_2 = new card('D',2);
		myCards.add(D_2);
		
		card D_3 = new card('D',3);
		myCards.add(D_3);
		
		card D_4 = new card('D',4);
		myCards.add(D_4);
		
		card D_5 = new card('D',5);
		myCards.add(D_5);
		
		card D_6 = new card('D',6);
		myCards.add(D_6);
		
		card D_7 = new card('D',7);
		myCards.add(D_7);
		
		card D_8 = new card('D',8);
		myCards.add(D_8);
		
		card D_9 = new card('D',9);
		myCards.add(D_9);
		
		card D_10 = new card('D',10);
		myCards.add(D_10);
		
		card D_j = new card('D',11);
		myCards.add(D_j);
		
		card D_q = new card('D',12);
		myCards.add(D_q);
		
		card D_k = new card('D',13);
		myCards.add(D_k);
		
	}
	
	
	
	
	public Drawable drawImage()
	{		
		Drawable temp = cards.get(0);
		cards.remove(0);
		//cardsRemain--;
		return temp;
		
	}
	
	
	public int draw()
	{
		int x = myCards.get(0).getValue();
		myCards.remove(0);
		return x;
	}
	
	
	public void shuffle()
	{
		cards.clear();
		myCards.clear();
		addCards();
		int first;
		int second;
		
		Random gen = new Random();
		
		for (int k = 0; k < 10000; k++)
		{
		first = gen.nextInt(52);
		second = gen.nextInt(52);
		Collections.swap(cards, first, second);
		Collections.swap(myCards, first, second);
		}
	}
}//end class
