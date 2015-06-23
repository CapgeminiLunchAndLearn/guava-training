package capgemini.training.guava.train;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class TrainGuavaTest {
	
	TrainGuava training;
	
	@Before
	public void before() {
		training = new TrainGuava();
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testCheckNotNull() {
		training.checkNotNull(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckArgument() {
		training.checkArgument("");
	}
	
	@Test
	public void testPrefix() {
		assertEquals("", training.prefix("abcdefgh", "ijklmnop"));
		assertEquals("TL_", training.prefix("TL_Snute", "TL_Happy"));
		assertEquals("C'est pas ", training.prefix("C'est pas faux", "C'est pas sorcier"));
	}
	
	@Test
	public void testOrderingNatural() {
		List<String> expected = Lists.newArrayList(null, null, "ananas", "anunas", "goyabe", "goyave", "orange", "pamplemousse", "pomme");

		assertEquals(expected, training.orderingNatural(Lists.newArrayList("orange", null, "pomme", "pamplemousse", "goyave", null, "ananas", "goyabe", "anunas")));
	}
	
	@Test
	public void testOrdering() {
		List<String> expected = Lists.newArrayList(null, null, "pomme", "ananas", "anunas", "goyabe", "goyave", "orange", "pamplemousse");
		assertEquals(expected, training.ordering(Lists.newArrayList("orange", null, "pomme", "pamplemousse", "goyave", null, "ananas", "goyabe", "anunas")));
	}
	
	@Test
	public void testFilter() {
		List<String> expected = Lists.newArrayList("pomme", "orange", "pamplemousse");
		assertTrue(Iterables.elementsEqual(expected, training.filter(ImmutableList.of("pomme", "ananas", "goyave", "orange", "pamplemousse"), ImmutableList.of("ananas", "goyave"))));
	}
	
	@Test
	public void testTransform() {
		List<Integer> expected = Lists.newArrayList(5, 6, 6, 6, 12);
		assertTrue(Iterables.elementsEqual(expected, training.transform(Lists.newArrayList("pomme", "ananas", "goyave", "orange", "pamplemousse"))));
	}
	
	@Test
	public void testCompress() {
		List<String> values = Lists.newArrayList("a", null, "a", "a", "b", null, "c", "c", null, "d", "e", "f", null, "f", null, null, "f", "f");
		assertEquals(Lists.newArrayList("a", "b", "c", "d", "e", "f"), Lists.newArrayList(training.compress(values)));
	}
	
	@Test
	public void testCounter() {
		Map<String, Integer> expected = ImmutableMap.of("a", new Integer(6), "b", new Integer(1), "c", new Integer(2), "d", new Integer(1), "e", new Integer(4));
		
		assertEquals(expected, training.counter(Lists.newArrayList("a", "a", "a", "a", "b", "c", "c", "a", "a", "d", "e", "e", "e", "e")));
	}

}
