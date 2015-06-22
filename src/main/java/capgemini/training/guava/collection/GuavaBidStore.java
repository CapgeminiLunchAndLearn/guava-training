package capgemini.training.guava.collection;

import java.util.Collection;
import java.util.Set;

import capgemini.training.guava.basic.Bid;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

public class GuavaBidStore implements IBidStore {
	
	private Set<Bid> bids = Sets.newConcurrentHashSet();

	@Override
	public void bid(Bid bid) {
		bids.add(bid);
	}

	@Override
	public void bid(Collection<Bid> bids) {
		Iterables.addAll(this.bids, bids);
	}

	@Override
	public Iterable<Bid> getBidsByAuctionner(final String auctionner) {
		return Iterables.filter(bids, new Predicate<Bid>() {

			@Override
			public boolean apply(Bid arg0) {
				return arg0.getOwner().equals(auctionner);
			}
		});
	}

	@Override
	public Iterable<Bid> getBidsWithMinimumPrice(final double reservedPrice) {
		return Iterables.filter(bids, new Predicate<Bid>() {

			@Override
			public boolean apply(Bid arg0) {
				return arg0.getValue().compareTo(reservedPrice) >= 0;
			}
		});
	}
	
	@Override
	public Set<Bid> deleteAuctionner(final String auctionner) {
		Iterables.removeIf(bids, new Predicate<Bid>() {

			@Override
			public boolean apply(Bid input) {
				return input.getOwner().equals(auctionner);
			}
		});
		return getAllBids();
	}

	@Override
	public Set<Double> getAllBidsValues() {
		return Sets.newHashSet(Iterables.transform(bids, new Function<Bid, Double>() {

			@Override
			public Double apply(Bid arg0) {
				return arg0.getValue();
			}
		}));
	}
	
	@Override
	public Multiset<String> getAllAuctionnerAndCounter() {
		return HashMultiset.create(Iterables.transform(bids, new Function<Bid, String>() {

			@Override
			public String apply(Bid arg0) {
				return arg0.getOwner();
			}
		}));
	}
	
	@Override
	public Multimap<String, Bid> getAllBidsByAuctionners() {
		Multimap<String, Bid> result = HashMultimap.create();
		for (Bid bid : bids) {
			result.put(bid.getOwner(), bid);
		}
		return result;
	}
	
	@Override
	public Set<Bid> getAllBids() {
		return ImmutableSet.copyOf(bids);
	}

}
