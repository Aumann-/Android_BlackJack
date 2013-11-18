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
If 21 is scored, game does not end


Face cards occasionally use Ace flag to revert to 1
	11/18: 	Potential fix by resetting flag at beginning of dealer move.
			 Bug due to player getting Ace as last card, setting flag to true,
			 dealer than plays with flag being true and reverting at first bust.

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
	Boolean p1Used = false;
	Boolean p2Used = false;
	Boolean p3Used = false;
	Boolean p4Used = false;
	Boolean p5Used = false;
	//dealer cards
	Boolean d1Used = false;
	Boolean d2Used = false;
	Boolean d3Used = false;
	Boolean d4Used = false;
	Boolean d5Used = false;
	//flag for dealer to stop
	Boolean stop = false;
	//flags for ace values of 11
	Boolean Ace = false;
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
		p2.setVisibility(View.INVISIBLE);
		p3.setVisibility(View.INVISIBLE);
		p4.setVisibility(View.INVISIBLE);
		p5.setVisibility(View.INVISIBLE);
		d2.setVisibility(View.INVISIBLE);
		d3.setVisibility(View.INVISIBLE);
		d4.setVisibility(View.INVISIBLE);
		d5.setVisibility(View.INVISIBLE);
		
		//disable deal button until game is over
		deal.setEnabled(false);
		
		
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
	public int evalCard(int value, Player p)
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
			Ace = true;
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
		p2.setVisibility(View.INVISIBLE);
		p3.setVisibility(View.INVISIBLE);
		p4.setVisibility(View.INVISIBLE);
		p5.setVisibility(View.INVISIBLE);
		d2.setVisibility(View.INVISIBLE);
		d3.setVisibility(View.INVISIBLE);
		d4.setVisibility(View.INVISIBLE);
		d5.setVisibility(View.INVISIBLE);
		p1Used = false;
		p2Used = false;
		p3Used = false;
		p4Used = false;
		p5Used = false;
		d1Used = false;
		d2Used = false;
		d3Used = false;
		d4Used = false;
		d5Used = false;
		stop = false;
		Ace = false;
		Brovier.setTotal(0);
		Brovid.setTotal(0);
		ptotal.setText(Brovier.getTotal().toString());
		dtotal.setText(Brovid.getTotal().toString());
		hit.setEnabled(true);
		stand.setEnabled(true);
		deal.setEnabled(false);
	}//close clearHand
	
	//method for player moves
	public void playerMove(deck test_deck)
	{
		//if first card not used

		if (!p1Used)
		{
			p1.setImageDrawable(test_deck.drawImage()); //draw card image
			Brovier.setTotal(evalCard(test_deck.draw(), Brovier)); //draw card value
			Brovier.setCard(); //increment card count
			ptotal.setText(Brovier.getTotal().toString()); //output current total
			p1Used = true; //set used card boolean
			if (Brovier.checkBust()) //check for bust (not needed for first card)
			{
				checkWinner(Brovier, Brovid);	
			}
		}
		
		//if second card not used
		else if (!p2Used)
		{
			p2.setVisibility(View.VISIBLE);
			p2.setImageDrawable(test_deck.drawImage());
			Brovier.setTotal(evalCard(test_deck.draw(),Brovier));
			Brovier.setCard();
			ptotal.setText(Brovier.getTotal().toString());
			p2Used = true;
			if (Brovier.checkBust() || Brovier.getTotal() == 21) //check for bust or blackjack
			{
				checkWinner(Brovier, Brovid);	
			}
		}
		//if third card not used
		else if (!p3Used)
		{
			p3.setVisibility(View.VISIBLE);
			p3.setImageDrawable(test_deck.drawImage());
			Brovier.setTotal(evalCard(test_deck.draw(),Brovier));
			Brovier.setCard();
			ptotal.setText(Brovier.getTotal().toString());
			p3Used = true;
			if (Brovier.checkBust() && Ace)
			{
				Brovier.setTotal(-10);
				ptotal.setText(Brovier.getTotal().toString());
				Ace = false;
			}
			if (Brovier.checkBust())
			{
				checkWinner(Brovier, Brovid);		
			}
		}
		
		//if fourth card not used
		else if (!p4Used)
		{
			p4.setVisibility(View.VISIBLE);
			p4.setImageDrawable(test_deck.drawImage());
			Brovier.setTotal(evalCard(test_deck.draw(),Brovier));
			Brovier.setCard();
			ptotal.setText(Brovier.getTotal().toString());
			p4Used = true;
			if (Brovier.checkBust() && Ace)
			{
				Brovier.setTotal(-10);
				ptotal.setText(Brovier.getTotal().toString());
				Ace = false;
			}
			if (Brovier.checkBust())
			{
				checkWinner(Brovier, Brovid);		
			}
		}
		//if fifth card not sued
		else if (!p5Used)
		{
			p5.setVisibility(View.VISIBLE);
			p5.setImageDrawable(test_deck.drawImage());
			Brovier.setTotal(evalCard(test_deck.draw(),Brovier));
			Brovier.setCard();
			ptotal.setText(Brovier.getTotal().toString());
			p5Used = true;
			if (Brovier.checkBust() && Ace)
			{
				Brovier.setTotal(-10);
				ptotal.setText(Brovier.getTotal().toString());
				Ace = false;
			}
			if (Brovier.checkBust())
			{
				checkWinner(Brovier, Brovid);		
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
		Ace = false;
		//if first card not used
		while (!stop) //until stop flag is set
		{
				if (!d1Used)
				{
					d1.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d1Used = true;
					if (Brovid.checkBust())
					{
						checkWinner(Brovier, Brovid);		
						stop = true;
					}
					//stop = true;
					
				}

				//if second card not used
				else if (!d2Used)
				{
					d2.setVisibility(View.VISIBLE);
					d2.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d2Used = true;
					if (Brovid.checkBust() || Brovid.getCard() == 21)
					{
						checkWinner(Brovier, Brovid);		
						stop = true;
					}
					else if (Brovid.getTotal() >= 17)
					{
						checkWinner(Brovier, Brovid);		
						stop = true;
					}
					
				}
				//if third card not used
				else if (!d3Used)
				{
					d3.setVisibility(View.VISIBLE);
					d3.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d3Used = true;
					if (Brovid.checkBust() && Ace)
					{
						Brovid.setTotal(-10);
						dtotal.setText(Brovid.getTotal().toString());
						Ace = false;
					}
					if (Brovid.checkBust())
					{
						checkWinner(Brovier, Brovid);	
						stop = true;
					}
					else if (Brovid.getTotal() >= 17)
					{
						checkWinner(Brovier, Brovid);		
						stop = true;
					}
					
				}
				//if fourth card not used
				else if (!d4Used)
				{
					d4.setVisibility(View.VISIBLE);
					d4.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d4Used = true;
					if (Brovid.checkBust() && Ace)
					{
						Brovid.setTotal(-10);
						dtotal.setText(Brovid.getTotal().toString());
						Ace = false;
					}
					if (Brovid.checkBust())
					{
						checkWinner(Brovier, Brovid);	
						stop = true;
					}
					else if (Brovid.getTotal() >= 17)
					{
						checkWinner(Brovier, Brovid);		
						stop = true;
					}
					
				}
				//if fifth card not sued
				else if (!d5Used)
				{
					d5.setVisibility(View.VISIBLE);
					d5.setImageDrawable(test_deck.drawImage());
					Brovid.setTotal(evalCard(test_deck.draw(),Brovid));
					Brovid.setCard();
					dtotal.setText(Brovid.getTotal().toString());
					d5Used = true;
					if (Brovid.checkBust() && Ace)
					{
						Brovid.setTotal(-10);
						dtotal.setText(Brovid.getTotal().toString());
						Ace = false;
					}
					if (Brovid.checkBust())
					{
						checkWinner(Brovier, Brovid);	
						stop = true;
					}
					else if (Brovid.getTotal() >= 17)
					{
						checkWinner(Brovier, Brovid);		
						stop = true;
					}
				
				}
				//debug
				else
				{
					checkWinner(Brovier, Brovid);
					//Toast.makeText(getApplicationContext(), "Dealer Broken", Toast.LENGTH_SHORT).show();
				}
		}//close while
	}//close dealerMove
	
	//method to determine winner
	public void checkWinner(Player p, Player d)
	{
		hit.setEnabled(false); //disable hit button
		stand.setEnabled(false); //disable stand button
		deal.setEnabled(true); //enable deal button
		//check for player bust
		if (p.getTotal() > 21)
		{
			Toast.makeText(getApplicationContext(), 
					"Player: BUST", Toast.LENGTH_SHORT).show();
		}
		//check for dealer bust
		else if (d.getTotal() > 21)
		{
			Toast.makeText(getApplicationContext(), 
					"Dealer: BUST", Toast.LENGTH_SHORT).show();
		}
		//check for player blackjack
		else if (p.getTotal() == 21 && p.getCard() == 2)
		{
				Toast.makeText(getApplicationContext(), 
						"Player: BLACKJACK", Toast.LENGTH_SHORT).show();
		}
		//check for dealer blackjack
		else if (d.getTotal() == 21 && d.getCard() == 2)
		{
			Toast.makeText(getApplicationContext(), 
					"Dealer: BLACKJACK", Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			//check for palyer wins
			if (p.getTotal() > d.getTotal())
			{
				//player wins
				Toast.makeText(getApplicationContext(), 
						"Player: WIN", Toast.LENGTH_SHORT).show();
			}
			//check for dealer wins
			else if (d.getTotal() > p.getTotal())
			{
				Toast.makeText(getApplicationContext(), 
						"Dealer: WIN", Toast.LENGTH_SHORT).show();
			}
			//check for tie
			else if (p.getTotal() == d.getTotal())
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
	
}//close Main class

