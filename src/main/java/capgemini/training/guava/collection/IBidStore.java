package capgemini.training.guava.collection;

import java.util.Collection;
import java.util.Set;

import capgemini.training.guava.basic.Bid;

public interface IBidStore {

	public Iterable<Bid> getBidsByAuctionner(String auctionner);
	
	public Iterable<Bid> getBidsWithMinimumPrice(double reservedPrice);
	
	public Set<Bid> deleteAuctionner(String auctionner);
	
	public Set<Double> getAllBidsValues();
	
	public Object getAllAuctionnerAndCounter();
	
	public Object getAllBidsByAuctionners();
	
	public void bid(Bid bid);
	
	public void bid(Collection<Bid> bids);
	
	public Set<Bid> getAllBids();
	
 }
