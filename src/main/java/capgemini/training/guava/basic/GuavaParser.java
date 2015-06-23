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
		Preconditions.checkArgument(!Strings.isNullOrEmpty(bid));
		List<String> values = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(bid);
		Preconditions.checkArgument(Iterables.size(values) == 3);
		
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
		Preconditions.checkArgument(!Strings.isNullOrEmpty(bids));
		
		return Lists.newArrayList(
				Iterables.transform(Splitter.on(';').omitEmptyStrings().trimResults().split(bids), new Function<String, Bid>() {

			@Override
			public Bid apply(String input) {
				return unmarshalBid(input);
			}
		}));
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
