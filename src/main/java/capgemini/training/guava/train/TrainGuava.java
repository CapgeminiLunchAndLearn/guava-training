package capgemini.training.guava.train;

import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public class TrainGuava {
	
	/**
	 * @see com.google.common.base.Preconditions
	 */
	public void checkNotNull(String testString) {
		
	}
	
	/**
	 * @see com.google.common.base.Preconditions
	 * @see com.google.common.base.Strings
	 */
	public void checkArgument(String testString) {
		
	}
	
	/**
	 * @see com.google.common.base.Strings
	 */
	public String prefix(String a, String b) {
		return null;
	}
	
	/**
	 * @see com.google.common.collect.Ordering
	 */
	public List<String> orderingNatural(List<String> origin) {
		return null;
	}
	
	/**
	 * @see com.google.common.collect.Ordering#compound(java.util.Comparator)
	 */
	public List<String> ordering(List<String> origin) {
		return null;
	}
	
	/**
	 * @see com.google.common.collect.Iterables#filter(Iterable, Predicate)
	 */
	public Iterable<String> filter(List<String> origin, final List<String> forbiddenWords) {
		return null;
	}
	
	/**
	 * @see com.google.common.collect.Iterables#transform(Iterable, Function)
	 */
	public Iterable<Integer> transform(List<String> origin) {
		return null;
	}

	/**
	 * @see com.google.common.collect.Iterables#filter(Iterable, Predicate)
	 */
	public <T> Iterable<T> compress(Iterable<T> iterable) {
		return null;
	}

	/**
	 * @see com.google.common.collect.Maps#toMap(Iterable, Function)
	 */
	public <T> Map<T, Integer> counter(final List<T> source) {
		return null;
	}

}
