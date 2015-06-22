package capgemini.training.guava;

import capgemini.training.guava.module.GuavaModule;
import capgemini.training.guava.module.StandardModule;

import com.google.inject.Guice;

public class ConfiguredTest {
	
	public static <T> T getInstance(Class<T> classToTest, boolean guavaModule) {
		if (guavaModule) {
			return Guice.createInjector(new GuavaModule()).getInstance(classToTest);
		} else {
			return Guice.createInjector(new StandardModule()).getInstance(classToTest);
		}
	}

}
