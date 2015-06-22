package capgemini.training.guava.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import capgemini.training.guava.basic.Bid;

public class BidStore implements IBidStore {
	
	private Set<Bid> bids = new HashSet<Bid>();

	@Override
	public void bid(Bid bid) {
		bids.add(bid);
	}

	@Override
	public void bid(Collection<Bid> bids) {
		this.bids.addAll(bids);
	}

	@Override
	public Set<Bid> getBidsByAuctionner(String auctionner) {
		Set<Bid> result = new HashSet<Bid>();
		for (Bid bid : bids) {
			if (bid.getOwner().equals(auctionner)) {
				result.add(bid);
			}
		}
		return result;
	}

	@Override
	public Set<Bid> getBidsWithMinimumPrice(double reservedPrice) {
		Set<Bid> result = new HashSet<Bid>();
		for (Bid bid : bids) {
			if (bid.getValue().compareTo(reservedPrice) >= 0) {
				result.add(bid);
			}
		}
		return result;
	}
	
	@Override
	public Set<Bid> deleteAuctionner(String auctionner) {
		Iterator<Bid> iterator = bids.iterator();
		while (iterator.hasNext()) {
			Bid current = iterator.next();
			if (current.getOwner().equals(auctionner)) {
				iterator.remove();
			}
		}
		return getAllBids();
	}

	@Override
	public Set<Double> getAllBidsValues() {
		Set<Double> result = new HashSet<Double>();
		for (Bid bid : bids) {
			result.add(bid.getValue());
		}
		return result;
	}
	
	@Override
	public Map<String, Integer> getAllAuctionnerAndCounter() {
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (Bid bid : bids) {
			if (!result.containsKey(bid.getOwner())) {
				result.put(bid.getOwner(), Integer.valueOf(0));
			}
			result.put(bid.getOwner(), result.get(bid.getOwner()) +1);
		}
		return result;
	}
	
	@Override
	public Map<String, Set<Bid>> getAllBidsByAuctionners() {
		Map<String, Set<Bid>> result = new HashMap<String, Set<Bid>>();
		for (Bid bid : bids) {
			if (!result.containsKey(bid.getOwner())) {
				result.put(bid.getOwner(), new HashSet<Bid>());
			}
			result.get(bid.getOwner()).add(bid);
		}
		return result;
	}

	@Override
	public Set<Bid> getAllBids() {
		return Collections.unmodifiableSet(bids);
	}

}
