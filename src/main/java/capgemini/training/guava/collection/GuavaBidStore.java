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
		// Return a view of bids with only values >= to reserved price in parameter
		return null;
	}
	
	@Override
	public Set<Bid> deleteAuctionner(final String auctionner) {
		// Remove all bids with owner equals to auctionner parameter
		return bids;
	}

	@Override
	public Set<Double> getAllBidsValues() {
		// return a set containing all the values from the bids
		return null;
	}
	
	@Override
	public Multiset<String> getAllAuctionnerAndCounter() {
		Iterable<String> allAuctionners = Iterables.transform(bids, new Function<Bid, String>() {

			@Override
			public String apply(Bid arg0) {
				return arg0.getOwner();
			}
		});
		// return a multiset with auctionner and counting @see com.google.common.collect.HashMultiset#create
		return null;
	}
	
	@Override
	public Set<Bid> getAllBids() {
		// return an immutable copy 
		return null;
	}
	
	@Override
	public Multimap<String, Bid> getAllBidsByAuctionners() {
		Multimap<String, Bid> result = HashMultimap.create();
		for (Bid bid : bids) {
			result.put(bid.getOwner(), bid);
		}
		return result;
	}

}
