package capgemini.training.guava.basic;

import java.util.Calendar;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Bid {
	
	private String owner;
	private Double value;
	private Calendar time;
	
	public Bid() {
		super();
	}

	public String getOwner() {
		return owner;
	}

	public Bid setOwner(String owner) {
		this.owner = owner;
		return this;
	}

	public Double getValue() {
		return value;
	}

	public Bid setValue(Double value) {
		this.value = value;
		return this;
	}

	public Calendar getTime() {
		return time;
	}

	public Bid setTime(Calendar time) {
		this.time = time;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(owner, value, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		return Objects.equal(owner, other.owner)
				&& Objects.equal(value, other.value)
				&& Objects.equal(time, other.time);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
				.add("owner", owner)
				.add("value", value)
				.add("time", time)
				.toString();
	}

}
