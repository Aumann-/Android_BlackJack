/*
Roy Smith
Blackjack game
Current status:
Program works as a single player game with the only winning move is to get 21

Needs:									Completed:
Expand player class for dealer		 	Unneeded
Add dealer for player to go against.	11/15
Add hit button.							11/15
Add stand button.						11/15
Add deal button to reset game.			11/15
Move checkBust method to player class	11/15
Add conversion for Aces if 11 value 
would cause bust (change Ace value to 1)11/15
If player get to five card without busting,
switch to dealer move					11/18
If dealer gets to 5 cards without busting,
switch to checkWinner.					11/18

Bugs:
Toast displays twice on occasion
	11/18: 	Due to calling checkWinner() at end of dealerMove,
			Need to add extra flag to not perform second check


Face cards occasionally use Ace flag to revert to 1
	11/18: 	Potential fix by resetting flag at beginning of dealer move.
			 Bug due to player getting Ace as last card, setting flag to true,
			 dealer than plays with flag being true and reverting at first bust.

Ace flag bug after autodeal
	Due to autodeal setting ace flag, totals are incorrect.
	11/18:	Fixed by separating player and dealer flag into two.
*/
package com.example.blackjack;


import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//globals
	
	//buttons
	Button hit;
	Button stand;
	Button deal;
	//images
	ImageView p1;
	ImageView p2;
	ImageView p3;
	ImageView p4;
	ImageView p5;
	ImageView d1;
	ImageView d2;
	ImageView d3;
	ImageView d4;
	ImageView d5;
	//text views
	TextView ptotal;
	TextView dtotal;
	//booleans
	//players cards
	Boolean p3Used = false;
	Boolean p4Used = false;
	Boolean p5Used = false;
	//dealer cards
	Boolean d3Used = false;
	Boolean d4Used = false;
	Boolean d5Used = false;
	//flag for dealer to stop
	Boolean stop = false;
	//flags for ace values of 11
	Boolean PAce = false; //player
	Boolean DAce = false; //dealer
	//flag for second checkWinner()
	Boolean CW = false;
	//player objects
	Player Brovier = new Player(); //instance of player class for user
	Player Brovid = new Player(); //instance of player class for dealer
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//widget declarations
		hit = (Button) findViewById(R.id.hit);
		stand = (Button) findViewById(R.id.stand);
		deal = (Button) findViewById(R.id.Deal);
		p1 = (ImageView) findViewById(R.id.Player1);
		p2 = (ImageView) findViewById(R.id.Player2);
		p3 = (ImageView) findViewById(R.id.Player3);
		p4 = (ImageView) findViewById(R.id.Player4);
		p5 = (ImageView) findViewById(R.id.Player5);
		d1 = (ImageView) findViewById(R.id.Dealer1);
		d2 = (ImageView) findViewById(R.id.Dealer2);
		d3 = (ImageView) findViewById(R.id.Dealer3);
		d4 = (ImageView) findViewById(R.id.Dealer4);
		d5 = (ImageView) findViewById(R.id.Dealer5);
		ptotal = (TextView) findViewById(R.id.PlayerTotal);
		dtotal = (TextView) findViewById(R.id.Dtotal);
		
		//make blank drawable for generic display
		Resources res = getResources();
		final Drawable blank = res.getDrawable(R.drawable.blank);
		//create new deck
		final deck test_deck = new deck(this);
		//add images to deck
		test_deck.addCards();
		
		//display total value
		ptotal.setText(Brovier.getTotal().toString());
		//shuffle deck
		test_deck.shuffle();
		//set all cards except first to invisible
		p3.setVisibility(View.INVISIBLE);
		p4.setVisibility(View.INVISIBLE);
		p5.setVisibility(View.INVISIBLE);
		d3.setVisibility(View.INVISIBLE);
		d4.setVisibility(View.INVISIBLE);
		d5.setVisibility(View.INVISIBLE);
		
		//disable deal button until game is over
		deal.setEnabled(false);
		
		
		firstDeal(test_deck);
		
		
		
		//listener for hit button
		hit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub		
					playerMove(test_deck);	//call playermove method			
			}//close onClick
		});	//close setonClickListener
		
		
		//listener for stand button
		stand.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dealerMove(test_deck); //call dealer move method
			}//close onClick
		});//close stand listener
		
		
		//listener for deal button
		deal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearHand(test_deck, blank); //clear hand and reset game
			}
		});
		
	}//close onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//method to convert char value of card to integer value
	public int evalCard(int value, Player p, Boolean a)
	{
		//king, queen, jack, or ten
		if (value == 10 || value == 11 || value == 12 || value == 13)
			return 10;
		//Ace if 11 value would cause bust
		else if (value == 1 && p.getTotal() > 10)
			return 1;
		//ace if 11 value would not bust
		else if (value == 1 && p.getTotal() <=10)
		{
			if (a)
				PAce = true;
			else if (!a)
				DAce = true;
			return 11;
		}
		else
			return value;
		
	}//close evalCard
	
	
	//method to reset displayed hand and player total value
	public void clearHand(deck test_deck, Drawable blank)
	{
		//reshuffle deck
		test_deck.shuffle();
		
		//set all but first cards to blank and invisible
		p1.setImageDrawable(blank);
		d1.setImageDrawable(blank);
		p3.setVisibility(View.INVISIBLE);
		p4.setVisibility(View.INVISIBLE);
		p5.setVisibility(View.INVISIBLE);
		d3.setVisibility(View.INVISIBLE);
		d4.setVisibility(View.INVISIBLE);
		d5.setVisibility(View.INVISIBLE);
		//reset card flags
		p3Used = false;
		p4Used = false;
		p5Used = false;
		d3Used = false;
		d4Used = false;
		d5Used = false;
		//dealer stop flag
		stop = false;
		//Ace flag
		PAce = false;
		DAce = false;
		//checkWinner flag
		CW = false;
		//reset player totals and card totals
		Brovier.setTotal(0);
		Brovid.setTotal(0);
		Brovier.resetCard();
		Brovid.resetCard();
		//reset total displays
		ptotal.setText(Brovier.getTotal().toString());
		dtotal.setText(Brovid.getTotal().toString());
		//enable hit & stand buttons, disable deal button
		hit.setEnabled(true);
		stand.setEnabled(true);
		deal.setEnabled(false);
		
		
		firstDeal(test_deck);
		
	}//close clearHand
	
	//method for player moves
	public void playerMove(deck test_deck)
	{
		//PAce = false;
		//if third card not used
		if (!p3Used)
		{
			p3.setVisibility(View.VISIBLE);
			p3.setImageDrawable(test_deck.drawImage());
			Brovier.setTotal(evalCard(test_deck.draw(),Brovier, true));
			Brovier.setCard();
			ptotal.setText(Brovier.getTotal().toString());
			p3Used = true;
			if (Brovier.checkBust() && PAce)
			{
				Brovier.setTotal(-10);
				ptotal.setText(Brovier.getTotal().toString());
				PAce = false;
			}
			if (Brovier.checkBust())
			{
				checkWinner();		
			}
		}
		
		//if fourth card not used
		else if (!p4Used)
		{
			p4.setVisibility(View.VISIBLE);
			p4.setImageDrawable(test_deck.drawImage());
			Brovier.setTotal(evalCard(test_deck.draw(),Brovier, true));
			Brovier.setCard();
			ptotal.setText(Brovier.getTotal().toString());
			p4Used = true;
			if (Brovier.checkBust() && PAce)
			{
				Brovier.setTotal(-10);
				ptotal.setText(Brovier.getTotal().toString());
				PAce = false;
			}
			if (Brovier.checkBust())
			{
				checkWinner();		
			}
		}
		//if fifth card not sued
		else if (!p5Used)
		{
			p5.setVisibility(View.VISIBLE);
			p5.setImageDrawable(test_deck.drawImage());
			Brovier.setTotal(evalCard(test_deck.draw(),Brovier, true));
			Brovier.setCard();
			ptotal.setText(Brovier.getTotal().toString());
			p5Used = true;
			if (Brovier.checkBust() && PAce)
			{
				Brovier.setTotal(-10);
				ptotal.setText(Brovier.getTotal().toString());
				PAce = false;
			}
			if (Brovier.checkBust())
			{
				checkWinner();		
			}
		}
		//debug
		else
		{
			dealerMove(test_deck);
			//Toast.makeText(getApplicationContext(), "Player Broken", Toast.LENGTH_SHORT).show();
		}
	}//close playerMove

	
	public void dealerMove(deck test_deck)
	{
		//DAce = false;
		//if first card not used
		while (!stop) //until stop flag is set
		{
				CW = true;
				//if third card not used
				if (!d3Used)
				{
					d3.setVisibility(View.VISIBLE);
					d3.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid, false));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d3Used = true;
					if (Brovid.checkBust() && DAce)
					{
						Brovid.setTotal(-10);
						dtotal.setText(Brovid.getTotal().toString());
						DAce = false;
					}
					if (Brovid.checkBust())
					{
						checkWinner();	
						stop = true;
					}
					else if (Brovid.getTotal() >= 17)
					{
						checkWinner();		
						stop = true;
					}
					
				}
				//if fourth card not used
				else if (!d4Used)
				{
					d4.setVisibility(View.VISIBLE);
					d4.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid, false));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d4Used = true;
					if (Brovid.checkBust() && DAce)
					{
						Brovid.setTotal(-10);
						dtotal.setText(Brovid.getTotal().toString());
						DAce = false;
					}
					if (Brovid.checkBust())
					{
						checkWinner();	
						stop = true;
					}
					else if (Brovid.getTotal() >= 17)
					{
						checkWinner();		
						stop = true;
					}
					
				}
				//if fifth card not sued
				else if (!d5Used)
				{
					d5.setVisibility(View.VISIBLE);
					d5.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid, false));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d5Used = true;
					if (Brovid.checkBust() && DAce)
					{
						Brovid.setTotal(-10);
						dtotal.setText(Brovid.getTotal().toString());
						DAce = false;
					}
					if (Brovid.checkBust())
					{
						checkWinner();	
						stop = true;
					}
					else if (Brovid.getTotal() >= 17)
					{
						checkWinner();		
						stop = true;
					}
				
				}
				//debug
				else
				{
					checkWinner();
					//Toast.makeText(getApplicationContext(), "Dealer Broken", Toast.LENGTH_SHORT).show();
				}
		}//close while
		if (!CW)
			checkWinner();
	}//close dealerMove
	
	//method to determine winner
	public void checkWinner()
	{
		hit.setEnabled(false); //disable hit button
		stand.setEnabled(false); //disable stand button
		deal.setEnabled(true); //enable deal button
		//check for player bust
		if (Brovier.getTotal() > 21)
		{
			Toast.makeText(getApplicationContext(), 
					"Player: BUST", Toast.LENGTH_SHORT).show();
		}
		//check for dealer bust
		else if (Brovid.getTotal() > 21)
		{
			Toast.makeText(getApplicationContext(), 
					"Dealer: BUST", Toast.LENGTH_SHORT).show();
		}
		//check for player blackjack
		else if (Brovier.getTotal() == 21 && Brovier.getCard() == 2)
		{
				Toast.makeText(getApplicationContext(), 
						"Player: BLACKJACK", Toast.LENGTH_SHORT).show();
		}
		//check for dealer blackjack
		else if (Brovid.getTotal() == 21 && Brovid.getCard() == 2)
		{
			Toast.makeText(getApplicationContext(), 
					"Dealer: BLACKJACK", Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			//check for palyer wins
			if (Brovier.getTotal() > Brovid.getTotal())
			{
				//player wins
				Toast.makeText(getApplicationContext(), 
						"Player: WIN", Toast.LENGTH_SHORT).show();
			}
			//check for dealer wins
			else if (Brovid.getTotal() > Brovier.getTotal())
			{
				Toast.makeText(getApplicationContext(), 
						"Dealer: WIN", Toast.LENGTH_SHORT).show();
			}
			//check for tie
			else if (Brovier.getTotal() == Brovid.getTotal())
			{
				//push
				Toast.makeText(getApplicationContext(), 
						"PUSH", Toast.LENGTH_SHORT).show();
			}
			//debug
			else
			{
				//error check
				Toast.makeText(getApplicationContext(), 
						"ERROR!!!", Toast.LENGTH_SHORT).show();
			}
		}
	}//close checkWinner
	
	
	public void firstDeal(deck test_deck)
	{
		//Deal first two cards for player
		p1.setImageDrawable(test_deck.drawImage()); //draw card image
		Brovier.setTotal(evalCard(test_deck.draw(), Brovier, true)); //draw card value
		Brovier.setCard(); //increment card count

	
		p2.setImageDrawable(test_deck.drawImage());
		Brovier.setTotal(evalCard(test_deck.draw(),Brovier, true));
		Brovier.setCard();
		ptotal.setText(Brovier.getTotal().toString());
		if (Brovier.getTotal() == 21) //check for bust or blackjack
		{
			checkWinner();	
		}

	
		//deal first two cards to dealer
		d1.setImageDrawable(test_deck.drawImage());
		Brovid.setTotal(evalCard(test_deck.draw(),Brovid, false));
		Brovid.setCard();

		
		d2.setImageDrawable(test_deck.drawImage());
		Brovid.setTotal(evalCard(test_deck.draw(),Brovid, false));
		Brovid.setCard();
		dtotal.setText(Brovid.getTotal().toString());
		if (Brovid.getTotal() == 21)
		{
			checkWinner();		
			stop = true;
		}
		else if (Brovid.getTotal() >= 17)
		{		
			stop = true;
		}
		
	}
	
}//close Main class

