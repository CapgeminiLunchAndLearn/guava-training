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
	 * Check an argument.
	 * If it's null then throw a NullPointerException
	 * 
	 * Samples :
	 *   null -> NullPointerException
	 *   "" -> void
	 * 
	 * Tips :
	 * @see com.google.common.base.Preconditions
	 */
	public void checkNotNull(String testString) {
		Preconditions.checkNotNull(testString);
	}
	
	/**
	 * Check an argument.
	 * If the parameters is null or empty throw a IllegalArgumentException
	 * 
	 * Samples :
	 *  null -> IllegalArgumentException
	 *  "" -> IllegalArgumentException
	 *  "test" -> void
	 * 
	 * Tips :
	 * @see com.google.common.base.Preconditions
	 * @see com.google.common.base.Strings
	 */
	public void checkArgument(String testString) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(testString));
	}
	
	/**
	 * Search the common prefix between two Strings
	 * 
	 * Samples :
	 *  "abcdefgh" & "ijklmnop" -> ""
	 *  "TL_Snute" & "TL_Happy" -> "TL_"
	 *  "C'est pas faux" & "C'est pas sorcier" -> "C'est pas "
	 * 
	 * Tips :
	 * @see com.google.common.base.Strings
	 */
	public String prefix(String a, String b) {
		return Strings.commonPrefix(a, b);
	}
	
	/**
	 * Sort a list with natural order (lexical order).
	 * null elements should be placed first.
	 * 
	 * Warning : the parameter list is immutable and must not change.
	 * 
	 * Samples :
	 *   In  -> ["orange", null, "pomme", "pamplemousse", "goyave", null, "ananas", "goyabe", "anunas"]
	 *   Out -> [null, null, "ananas", "anunas", "goyabe", "goyave", "orange", "pamplemousse", "pomme"]
	 * 
	 * Tips :
	 * @see com.google.common.collect.Ordering#natural()
	 * @see com.google.common.collect.Ordering#nullsFirst()
	 * @see com.google.common.collect.Ordering#sortedCopy(Iterable)
	 */
	public List<String> orderingNatural(List<String> origin) {
		return Ordering.natural().nullsFirst().sortedCopy(origin);
	}
	
	/**
	 * Sort a List with a composition of comparative type.
	 * Sort rule : Sort by size of the strings, for equals size use natural order (lexical orer)
	 * 
	 * Samples :
	 *   In  -> ["orange", null, "pomme", "pamplemousse", "goyave", null, "ananas", "goyabe", "anunas"]
	 *   Out -> [null, null, "pomme", "ananas", "anunas", "goyabe", "goyave", "orange", "pamplemousse"]
	 *   
	 * Tips :
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
	 * Return a List in parameters with all the string contained in the second parameter removed.
	 * Warning : the parameter list is immutable and must not change.
	 * 
	 * Samples :
	 *   In  -> ["pomme", "ananas", "goyave", "orange", "pamplemousse"] & ["ananas", "goyave"]
	 *   Out -> ["pomme", "orange", "pamplemousse"]
	 * 
	 * Tips :
	 * @see com.google.common.collect.Lists#newArrayList(Iterable)
	 * @see com.google.common.collect.Iterables#removeAll(Iterable, java.util.Collection)
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
	 * Convert a List of String to return a List of Integer representing the size of every member of the original List.
	 * 
	 * Samples :
	 *   In  -> ["pomme", "ananas", "goyave", "orange", "pamplemousse"]
	 *   Out -> [5, 6, 6, 6, 12]
	 * 
	 * Tips :
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
	 * Compress a collection of elements.
	 * To compress a collection remove all the null elements and remove all duplicated elements.
	 * 
	 * Samples :
	 *   In  -> ["a", null, "a", "a", "b", null, "c", "c", null, "d", "e", "f", null, "f", null, null, "f", "f"]
	 *   Out -> ["a", "b", "c", "d", "e", "f"]
	 * 
	 * Tips :
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
		return Iterables.filter(iterable, Predicates.and(Predicates.not(Predicates.isNull()), uniquePredicate));
	}

	/**
	 * Transform a List of elements
	 * Return a  Map with as key each unique element of the list and in value the frequency of the element in the original List.
	 * 
	 * Samples :
	 *   In  -> ["a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e"]
	 *   Out -> [{"a",6}, {"b",1}, {"c",2}, {"d",1}, {"e",4}]
	 * 
	 * Tips :
	 * @see com.google.common.collect.Maps#toMap(Iterable, Function)
	 * @see com.google.common.collect.Iterables#frequency(Iterable, Object)
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
