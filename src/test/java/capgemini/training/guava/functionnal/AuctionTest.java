package capgemini.training.guava.functionnal;

import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

import capgemini.training.guava.basic.Bid;
import static org.junit.Assert.*;

public class AuctionTest {
	
	Auction auction;
	
	@Before
	public void before() {
		auction = new Auction("Produit", 45d);
	}
	
	@Test
	public void testWinning_StandardAuction() {
		auction.getBidStore().bid(new Bid().setOwner("Francois").setValue(35d).setTime(Calendar.getInstance()));
		auction.getBidStore().bid(new Bid().setOwner("Francois").setValue(60d).setTime(Calendar.getInstance()));
		auction.getBidStore().bid(new Bid().setOwner("Francois").setValue(80d).setTime(Calendar.getInstance()));

		auction.getBidStore().bid(new Bid().setOwner("Charlotte").setValue(40d).setTime(Calendar.getInstance()));
		auction.getBidStore().bid(new Bid().setOwner("Charlotte").setValue(45d).setTime(Calendar.getInstance()));
		auction.getBidStore().bid(new Bid().setOwner("Charlotte").setValue(75d).setTime(Calendar.getInstance()));
		auction.getBidStore().bid(new Bid().setOwner("Charlotte").setValue(85d).setTime(Calendar.getInstance()));
		
		Entry<String, Double> expected = new AbstractMap.SimpleEntry<String, Double>("Charlotte", 85d);
		
		assertEquals(expected, auction.winner(StandardAuction.VALIDATION, StandardAuction.WINNING_FUNCTION));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testWinning_StandardAuctionNoBids() {
		auction.winner(StandardAuction.VALIDATION, StandardAuction.WINNING_FUNCTION);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testWinning_StandardAuctionInvalidBids() {
		auction.getBidStore().bid(new Bid().setOwner("Francois").setValue(35d).setTime(Calendar.getInstance()));
		auction.getBidStore().bid(new Bid().setOwner("Charlotte").setValue(35d).setTime(Calendar.getInstance()));
		auction.winner(StandardAuction.VALIDATION, StandardAuction.WINNING_FUNCTION);
	}
	
	@Test
	public void testWinning_StandardAuctionWithMaxEquality() {
		auction.getBidStore().bid(new Bid().setOwner("Francois").setValue(80d).setTime(Calendar.getInstance()));
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(time.getTimeInMillis() - 10000);
		auction.getBidStore().bid(new Bid().setOwner("Charlotte").setValue(80d).setTime(time));
		
		Entry<String, Double> expected = new AbstractMap.SimpleEntry<String, Double>("Charlotte", 80d);
		
		assertEquals(expected, auction.winner(StandardAuction.VALIDATION, StandardAuction.WINNING_FUNCTION));
	}
	
	@Test
	public void testWinning_OptionnalFalse() {
		assertFalse(auction.winner(StandardAuction.WINNING_FUNCTION).isPresent());
	}
	
	@Test
	public void testWinning_OptionnalTrue() {
		auction.getBidStore().bid(new Bid().setOwner("Francois").setValue(80d).setTime(Calendar.getInstance()));
		auction.getBidStore().bid(new Bid().setOwner("Charlotte").setValue(90d).setTime(Calendar.getInstance()));
		
		Entry<String, Double> expected = new AbstractMap.SimpleEntry<String, Double>("Charlotte", 90d);
		
		Optional<Entry<String, Double>> actual = auction.winner(StandardAuction.WINNING_FUNCTION);
		assertTrue(actual.isPresent());
		assertEquals(expected, actual.get());
	}

}
