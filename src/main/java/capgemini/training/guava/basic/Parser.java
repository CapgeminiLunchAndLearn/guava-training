package capgemini.training.guava.basic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class Parser implements IParser {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public static Calendar getCalendarFor(String aDate) {
		Calendar result =  Calendar.getInstance();
		try {
			result.setTime(DATE_FORMAT.parse(aDate));
		} catch (ParseException e) {
			System.out.println("Convertion error in Calendar for "+aDate+" expected pattern 'dd/MM/YYYY HH:mm'");
		}
		return result;
	}

	@Override
	public Bid unmarshalBid(String bid) {
		if (bid == null || bid.isEmpty()) {
			throw new IllegalArgumentException();
		}
		String[] values = bid.split(",");
		if (values.length != 3) {
			throw new IllegalArgumentException();
		}
		
		Bid result = new Bid();
		result.setOwner(values[0].trim());
		result.setValue(Double.valueOf(values[1].trim()));
		result.setTime(getCalendarFor(values[2].trim()));
		return result;
	}

	@Override
	public String marshallBid(Bid bid) {
		if (bid == null) {
			throw new IllegalArgumentException();
		}
		StringBuilder result = new StringBuilder();
		result.append(bid.getOwner());
		result.append(',');
		result.append(bid.getValue());
		result.append(',');
		result.append(DATE_FORMAT.format(bid.getTime().getTime()));
		return result.toString();
	}

	@Override
	public List<Bid> unmarshalBids(String bids) {
		if (bids == null || bids.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		List<Bid> result = new ArrayList<Bid>();
		String[] values = bids.split(";");
		for (String value : values) {
			if (!value.isEmpty() && !Pattern.compile("^[ ]$").matcher(value).matches()) {
				result.add(unmarshalBid(value));
			}
		}
		return result;
	}

	@Override
	public String marshallBids(List<Bid> bids) {
		if (bids == null) {
			throw new IllegalArgumentException();
		}
		StringBuilder result = new StringBuilder();
		for (Bid bid : bids) {
			if (bid == null)  {
				result.append(" ");
			} else {
				result.append(marshallBid(bid));
			}
			result.append(";");
		}
		if (bids.size() > 0) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

}
