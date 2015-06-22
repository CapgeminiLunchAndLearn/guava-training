package capgemini.training.guava.basic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class GuavaParser implements IParser {
	
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
		// Check validity of the string in parameter and split it @see com.google.common.base.Splitters
		List<String> values = null;
		
		return new Bid()
				.setOwner(values.get(0))
				.setValue(Double.valueOf(values.get(1)))
				.setTime(getCalendarFor(values.get(2)));
	}

	@Override
	public String marshallBid(Bid bid) {
		Preconditions.checkArgument(bid != null);
		return Joiner.on(',').useForNull(" ").join(bid.getOwner(), bid.getValue(), DATE_FORMAT.format(bid.getTime().getTime()));
	}

	@Override
	public List<Bid> unmarshalBids(String bids) {
		// Check validity of the string in parameter and for each segment between each ; unmarshal a auction
		return null;
	}

	@Override
	public String marshallBids(List<Bid> bids) {
		Preconditions.checkArgument(bids != null);
		return Joiner.on(';').useForNull(" ").join(Iterables.transform(bids, new Function<Bid, String>() {

			@Override
			public String apply(Bid input) {
				return marshallBid(input);
			}
		}));
	}

}
