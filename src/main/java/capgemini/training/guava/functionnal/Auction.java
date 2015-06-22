package capgemini.training.guava.functionnal;

import java.util.Map.Entry;

import capgemini.training.guava.basic.Bid;
import capgemini.training.guava.collection.GuavaBidStore;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

public class Auction {
	
	private String product;
	private Double reservedPrice;
	private GuavaBidStore bidStore;
	
	public Auction(String product, Double reservedPrice) {
		super();
		this.product = product;
		this.reservedPrice = reservedPrice;
		this.bidStore = new GuavaBidStore();
	}
	
	public Entry<String, Double> winner(Predicate<Iterable<Bid>> valid,  Function<Iterable<Bid>, Entry<String, Double>> winningFunction) {
		Preconditions.checkState(valid.apply(bidStore.getBidsWithMinimumPrice(this.reservedPrice)), "Invalid auction for valid predicate : "+this.toString());
		return winningFunction.apply(bidStore.getBidsWithMinimumPrice(this.reservedPrice));
	}
	
	public Optional<Entry<String, Double>> winner(Function<Iterable<Bid>, Entry<String, Double>> winningFunction) {
		return Optional.fromNullable(winningFunction.apply(bidStore.getBidsWithMinimumPrice(this.reservedPrice)));
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public Double getReservedPrice() {
		return reservedPrice;
	}

	public void setReservedPrice(Double reservedPrice) {
		this.reservedPrice = reservedPrice;
	}

	public GuavaBidStore getBidStore() {
		return bidStore;
	}

	public void setBidStore(GuavaBidStore bidStore) {
		this.bidStore = bidStore;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(product, reservedPrice, bidStore);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auction other = (Auction) obj;
		return Objects.equal(product, other.product)
				&& Objects.equal(reservedPrice, other.reservedPrice)
				&& Objects.equal(bidStore, other.bidStore);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.addValue(product)
				.addValue(reservedPrice)
				.addValue(bidStore)
				.toString();
	}
	
	

}
