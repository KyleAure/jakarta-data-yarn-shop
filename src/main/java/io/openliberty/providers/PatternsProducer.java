package io.openliberty.providers;

import java.util.Collections;
import java.util.Set;

import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.enterprise.inject.spi.Producer;
import jakarta.enterprise.inject.spi.ProducerFactory;

public class PatternsProducer<R, P> implements Producer<PatternsImpl> {
	
    /**
     * Factory class for this repository producer.
     */
    static class Factory<P> implements ProducerFactory<P> {
        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public <R> Producer<R> createProducer(Bean<R> bean) {
            return new PatternsProducer();
        }
    }

	@Override
	public PatternsImpl produce(CreationalContext<PatternsImpl> ctx) {
		System.out.println("Producing pattern repository implementation");
		return new PatternsImpl();
	}

	@Override
	public void dispose(PatternsImpl instance) {
		System.out.println("Disposing pattern repository");
		
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return Collections.emptySet();
	}

}
