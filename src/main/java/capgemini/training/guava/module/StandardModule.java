package capgemini.training.guava.module;

import capgemini.training.guava.basic.IParser;
import capgemini.training.guava.basic.Parser;
import capgemini.training.guava.collection.BidStore;
import capgemini.training.guava.collection.IBidStore;

import com.google.inject.AbstractModule;

public class StandardModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IParser.class).to(Parser.class);
		bind(IBidStore.class).to(BidStore.class);
	}

}
