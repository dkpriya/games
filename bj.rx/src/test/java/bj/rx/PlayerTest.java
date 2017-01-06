package bj.rx;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.kittu.model.Card;
import com.kittu.model.CardValue;
import com.kittu.model.Player;
import com.kittu.model.Sign;

public class PlayerTest {

	Player p ;
	
	@Before
	public void setUp(){
  	}
	
	public void reset(){
		p = new Player();
		p.setName("Test Player");

	}
	@Test
	public void getOptimumValueTest(){
		aceTest();
		blackJackWithAceMax();
		blackJackWithAceMin();
		blackJackWithNoAce();
 	}
	
	
	public void aceTest(){
		reset();
		p.addCard(new Card(Sign.CLUB, CardValue._ACE));
		p.addCard(new Card(Sign.CLUB, CardValue._ACE));
 		Assert.assertEquals(p.getOptimumCardsValue(),12);
	}
	
	public void blackJackWithAceMax(){
		reset();
		p.addCard(new Card(Sign.CLUB, CardValue._ACE));
		p.addCard(new Card(Sign.CLUB, CardValue._JACK));
 		Assert.assertEquals(p.getOptimumCardsValue(),21);
 	}
	
	public void blackJackWithAceMin(){
		reset();
		p.addCard(new Card(Sign.CLUB, CardValue._ACE));
		p.addCard(new Card(Sign.CLUB, CardValue._JACK));
		p.addCard(new Card(Sign.CLUB, CardValue._QUEEN));
 		Assert.assertEquals(p.getOptimumCardsValue(),21);
 	}
	
	public void blackJackWithNoAce(){
		reset();
		p.addCard(new Card(Sign.CLUB, CardValue._9));
		p.addCard(new Card(Sign.CLUB, CardValue._JACK));
		p.addCard(new Card(Sign.CLUB, CardValue._2));
 		Assert.assertEquals(p.getOptimumCardsValue(),21);
 	}
}
