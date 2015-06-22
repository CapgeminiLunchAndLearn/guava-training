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
		Preconditions.checkNotNull(testString);
	}
	
	/**
	 * @see com.google.common.base.Preconditions
	 * @see com.google.common.base.Strings
	 */
	public void checkArgument(String testString) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(testString));
	}
	
	/**
	 * @see com.google.common.base.Strings
	 */
	public String prefix(String a, String b) {
		return Strings.commonPrefix(a, b);
	}
	
	/**
	 * @see com.google.common.collect.Ordering
	 */
	public List<String> orderingNatural(List<String> origin) {
		return Ordering.natural().nullsFirst().sortedCopy(origin);
	}
	
	/**
	 * @see com.google.common.collect.Ordering#compound(java.util.Comparator)
	 */
	public List<String> ordering(List<String> origin) {
		Ordering<String> sizeOrdering = new Ordering<String>() {

			@Override
			public int compare(String left, String right) {
				return Integer.valueOf(left.length()).compareTo(right.length());
			}
		}.compound(Ordering.natural()).nullsFirst();
		return sizeOrdering.sortedCopy(origin);
	}
	
	/**
	 * @see com.google.common.collect.Iterables#filter(Iterable, Predicate)
	 */
	public Iterable<String> filter(List<String> origin, final List<String> forbiddenWords) {
		return Iterables.filter(origin, new Predicate<String>() {

			@Override
			public boolean apply(String input) {
				return !forbiddenWords.contains(input);
			}
		});
	}
	
	/**
	 * @see com.google.common.collect.Iterables#transform(Iterable, Function)
	 */
	public Iterable<Integer> transform(List<String> origin) {
		
		return Iterables.transform(origin, new Function<String, Integer>() {

			@Override
			public Integer apply(String input) {
				return (input != null) ? input.length() : 0;
			}
			
		});
	}

	/**
	 * @see com.google.common.collect.Iterables#filter(Iterable, Predicate)
	 */
	public <T> Iterable<T> compress(Iterable<T> iterable) {
		Predicate<T> uniquePredicate = new Predicate<T>() {
			List<T> detectedElements = Lists.newArrayList();

			@Override
			public boolean apply(T input) {
				if (detectedElements.contains(input)) {
					return false;
				}
				detectedElements.add(input);
				return true;
			}
		};
		return Iterables.filter(iterable, Predicates.and(
				Predicates.not(Predicates.isNull()), uniquePredicate));
	}

	/**
	 * @see com.google.common.collect.Maps#toMap(Iterable, Function)
	 */
	public <T> Map<T, Integer> counter(final List<T> source) {
		return Maps.toMap(source, new Function<T, Integer>() {

			@Override
			public Integer apply(T element) {
				return Iterables.frequency(source, element);
			}
		});
	}

}
