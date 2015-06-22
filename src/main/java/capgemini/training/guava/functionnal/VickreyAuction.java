package capgemini.training.guava.functionnal;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;

import capgemini.training.guava.basic.Bid;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;


public class VickreyAuction {

	public static final Predicate<Iterable<Bid>> VALIDATION = new Predicate<Iterable<Bid>>() {

		@Override
		public boolean apply(Iterable<Bid> input) {
			return Iterables.size(Sets.newHashSet(Iterables.transform(input, new Function<Bid, String>() {

				@Override
				public String apply(Bid input) {
					return input.getOwner();
				}
				
			}))) > 1;
		}
	};
	
	public static final Function<Iterable<Bid>, Entry<String, Double>> WINNING_FUNCTION = new Function<Iterable<Bid>, Map.Entry<String,Double>>() {
		
		@Override
		public Entry<String, Double> apply(Iterable<Bid> input) {
			final Entry<String, Double> standardWinner = StandardAuction.WINNING_FUNCTION.apply(input);
			
			final Entry<String, Double> winningPrice = StandardAuction.WINNING_FUNCTION.apply(Iterables.filter(input, new Predicate<Bid>() {

				@Override
				public boolean apply(Bid input) {
					return !input.getOwner().equals(standardWinner.getKey());
				}
			}));
			
			return new AbstractMap.SimpleEntry<String, Double>(standardWinner.getKey(), winningPrice.getValue());
		}
	};

}
