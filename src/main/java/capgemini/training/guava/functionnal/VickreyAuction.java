package capgemini.training.guava.functionnal;

import java.util.Map;
import java.util.Map.Entry;

import capgemini.training.guava.basic.Bid;

import com.google.common.base.Function;
import com.google.common.base.Predicate;


public class VickreyAuction {

	public static final Predicate<Iterable<Bid>> VALIDATION = new Predicate<Iterable<Bid>>() {

		@Override
		public boolean apply(Iterable<Bid> input) {
			// A Vickrey auction is valid when there is 2 auctionner with at least one bid each
			return false;
		}
	};
	
	public static final Function<Iterable<Bid>, Entry<String, Double>> WINNING_FUNCTION = new Function<Iterable<Bid>, Map.Entry<String,Double>>() {

		@Override
		public Entry<String, Double> apply(Iterable<Bid> input) {
			
			return null;
		}
	};

}
