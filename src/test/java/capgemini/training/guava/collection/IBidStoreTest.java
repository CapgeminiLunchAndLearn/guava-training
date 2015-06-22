package capgemini.training.guava.collection;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import capgemini.training.guava.ConfiguredTest;
import capgemini.training.guava.basic.Bid;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Sets;

public class IBidStoreTest extends ConfiguredTest {
	
	boolean testGuava = false;
	
	IBidStore bidStore;
	
	@Test
	public void testGetBidsByAuctionner() throws ParseException {
		Set<Bid> expected = Sets.newHashSet(bidsForFrancois());
		
		Iterable<Bid> actual = bidStore.getBidsByAuctionner("Francois");
		
		assertEquals(expected, Sets.newHashSet(actual));
	}
	
	@Test
	public void testGetBidsWithMinimumPrice() throws ParseException {
		Set<Bid> expected = Sets.newHashSet(
			new Bid().setOwner("Francois").setTime(getCalendarFor("20/06/2015 16:00")).setValue(90d),
			new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 15:20")).setValue(85d),
			new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 16:10")).setValue(90d),
			new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 16:15")).setValue(95d),
			new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 16:45")).setValue(105d),
			new Bid().setOwner("Johnny").setTime(getCalendarFor("20/06/2015 16:30")).setValue(100d),
			new Bid().setOwner("Johnny").setTime(getCalendarFor("20/06/2015 17:00")).setValue(110d));
		
		Iterable<Bid> actual = bidStore.getBidsWithMinimumPrice(85d);
		
		assertEquals(expected,  Sets.newHashSet(actual));
	}
	
	@Test
	public void testGetAllBidsValues() {
		Set<Double> expected = Sets.newHashSet(40d, 50d, 60d, 70d, 90d, 35d, 47d, 55d, 60d, 85d, 95d, 105d, 75d, 100d, 110d);
		
		assertEquals(expected, bidStore.getAllBidsValues());
	}
	
	@Test
	public void testGetAllAuctionnerAndCounter() {
		Object expected;
		if (testGuava) {
			expected = ImmutableMultiset.builder().addCopies("Francois", 5).addCopies("Charlotte", 8).addCopies("Johnny", 3).build();
		} else {
			expected = ImmutableMap.builder().put("Francois", 5).put("Charlotte", 8).put("Johnny", 3).build();
		}
		
		assertEquals(expected, bidStore.getAllAuctionnerAndCounter());
	}
	
	@Test
	public void testGetAllBidsByAuctionners() throws ParseException {
		Object expected;
		if (testGuava) {
			expected = ImmutableSetMultimap.builder()
					.putAll("Francois", bidsForFrancois())
					.putAll("Charlotte", bidsForCharlotte())
					.putAll("Johnny", bidsForJohnny())
					.build();
		} else {
			expected = ImmutableMap.builder()
					.put("Francois", bidsForFrancois())
					.put("Charlotte", bidsForCharlotte())
					.put("Johnny", bidsForJohnny())
					.build();
		}
		
		assertEquals(expected, bidStore.getAllBidsByAuctionners());
	}
	
	@Before
	public void before() throws ParseException {
		bidStore = getInstance(IBidStore.class, testGuava);
		
		bidStore.bid(bidsForFrancois());
		bidStore.bid(bidsForCharlotte());
		bidStore.bid(bidsForJohnny());
	}
	
	public static Set<Bid> bidsForFrancois() throws ParseException {
		return Sets.newHashSet(
				new Bid().setOwner("Francois").setTime(getCalendarFor("20/06/2015 14:00")).setValue(40d),
				new Bid().setOwner("Francois").setTime(getCalendarFor("20/06/2015 14:30")).setValue(50d),
				new Bid().setOwner("Francois").setTime(getCalendarFor("20/06/2015 14:45")).setValue(60d),
				new Bid().setOwner("Francois").setTime(getCalendarFor("20/06/2015 15:00")).setValue(70d),
				new Bid().setOwner("Francois").setTime(getCalendarFor("20/06/2015 16:00")).setValue(90d));
	}
	
	public static Set<Bid> bidsForCharlotte() throws ParseException {
		return Sets.newHashSet(
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 13:00")).setValue(35d),
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 14:15")).setValue(47d),
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 14:30")).setValue(55d),
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 14:45")).setValue(60d),
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 15:20")).setValue(85d),
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 16:10")).setValue(90d),
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 16:15")).setValue(95d),
				new Bid().setOwner("Charlotte").setTime(getCalendarFor("20/06/2015 16:45")).setValue(105d));
	}
	
	public static Set<Bid> bidsForJohnny() throws ParseException {
		return Sets.newHashSet(
				new Bid().setOwner("Johnny").setTime(getCalendarFor("20/06/2015 15:10")).setValue(75d),
				new Bid().setOwner("Johnny").setTime(getCalendarFor("20/06/2015 16:30")).setValue(100d),
				new Bid().setOwner("Johnny").setTime(getCalendarFor("20/06/2015 17:00")).setValue(110d));
	}
	
	public static Calendar getCalendarFor(String aDate) throws ParseException {
		Calendar result =  Calendar.getInstance();
		result.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(aDate));
		return result;
	}

}
