package capgemini.training.guava.basic;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import capgemini.training.guava.ConfiguredTest;


public class IParserTest extends ConfiguredTest {

	private IParser parser;

	/**
	 * Before.
	 */
	@Before
	public void before() {
	}
	
	@Test
	public void testUnmarshalBid() throws ParseException {
		Bid expected = new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00"));

		assertEquals(expected, parser.unmarshalBid("Francois,50.0,20/06/2015 20:00"));
		assertEquals(expected, parser.unmarshalBid("Francois,50d,20/06/2015 20:00"));
		assertEquals(expected, parser.unmarshalBid("Francois ,50d ,20/06/2015 20:00"));
		assertEquals(expected, parser.unmarshalBid("Francois, 50, 20/06/2015 20:00"));
		assertEquals(expected, parser.unmarshalBid(" Francois , 50 , 20/06/2015 20:00 "));
		assertEquals(expected, parser.unmarshalBid(" Francois , 50  ,20/06/2015 20:00   "));
	}
	
	@Test
	public void testMarshalBid() throws ParseException {
		String expected = "Francois,50.0,20/06/2015 20:00";
		
		Bid parameter = new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00"));
		assertEquals(expected, parser.marshallBid(parameter));
	}
	
	@Test
	public void testUnmarshalBids() throws ParseException {
		List<Bid> expected = Lists.newArrayList(
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")),
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")),
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")),
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")));
		
		String parameter = "Francois,50.0,20/06/2015 20:00;Francois, 50, 20/06/2015 20:00  ; Francois , 50 , 20/06/2015 20:00 ; Francois , 50  ,20/06/2015 20:00   ";
		
		assertEquals(expected, parser.unmarshalBids(parameter));
	}
	
	@Test
	public void testMarshalBids() throws ParseException {
		String expected = "Francois,50.0,20/06/2015 20:00;Francois,50.0,20/06/2015 20:00;Francois,50.0,20/06/2015 20:00;Francois,50.0,20/06/2015 20:00";
		
		List<Bid> parameter = Lists.newArrayList(
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")),
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")),
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")),
				new Bid().setOwner("Francois").setValue(50d).setTime(getCalendarFor("20/06/2015 20:00")));
		
		assertEquals(expected, parser.marshallBids(parameter));
	}
	
	public static Calendar getCalendarFor(String aDate) throws ParseException {
		Calendar result =  Calendar.getInstance();
		result.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(aDate));
		return result;
	}


}
