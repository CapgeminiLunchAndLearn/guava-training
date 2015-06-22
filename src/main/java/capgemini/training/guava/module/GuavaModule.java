package capgemini.training.guava.module;

import capgemini.training.guava.basic.GuavaParser;
import capgemini.training.guava.basic.IParser;
import capgemini.training.guava.collection.GuavaBidStore;
import capgemini.training.guava.collection.IBidStore;

import com.google.inject.AbstractModule;

public class GuavaModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(IParser.class).to(GuavaParser.class);
		bind(IBidStore.class).to(GuavaBidStore.class);
	}

}
