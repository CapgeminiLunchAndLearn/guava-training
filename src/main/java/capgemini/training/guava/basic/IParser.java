package capgemini.training.guava.basic;

import java.util.List;

public interface IParser {
	
	public Bid unmarshalBid(String bid);
	
	public String marshallBid(Bid bid);
	
	public List<Bid> unmarshalBids(String bids);
	
	public String marshallBids(List<Bid> bids);

}
