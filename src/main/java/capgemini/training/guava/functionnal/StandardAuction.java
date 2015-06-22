package capgemini.training.guava.functionnal;

import java.util.AbstractMap;
import java.util.Map.Entry;

import capgemini.training.guava.basic.Bid;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public final class StandardAuction {
	
	/** 
	 * Comparaison de deux bid selon la règle standard.
	 * Tri par valeur de l'enchère.
	 * Si deux enchères sont de même valeur alors celle qui a été la première dans le temps prend la priorité.
	 */
	public static final Ordering<Bid> ORDERING = new Ordering<Bid>() {
			@Override
			public int compare(Bid left, Bid right) {
				return left.getValue().compareTo(right.getValue());
			}
		}.compound(new Ordering<Bid>() {
			@Override
			public int compare(Bid left, Bid right) {
				return right.getTime().compareTo(left.getTime());
			}
		});
	
	/**
	 * Fonction de validation pour une enchère standard.
	 * Il doit exister au moins une enchère (Le filtre sur le prix minimum est considéré comme fait).
	 */
	public static final Predicate<Iterable<Bid>> VALIDATION = new Predicate<Iterable<Bid>>() {

		@Override
		public boolean apply(Iterable<Bid> input) {
			return Lists.newArrayList(input).size() > 0;
		}
	};
	
	/**
	 * Fonction de recherche du couple enchereur gagnant / valeur de l'enchère gagnant.
	 * 
	 * Sur une enchère standard il s'agit de la personne et de la valeur de l'enchère la plus élevée.
	 */
	public static final Function<Iterable<Bid>, Entry<String, Double>> WINNING_FUNCTION = new Function<Iterable<Bid>, Entry<String, Double>>() {

		@Override
		public Entry<String, Double> apply(Iterable<Bid> input) {
			if (Iterables.size(input) == 0) {
				return null;
			}
			Bid winnerBid = ORDERING.max(input);
			return new AbstractMap.SimpleEntry<String, Double>(winnerBid.getOwner(), winnerBid.getValue());
		}
	};

}
