package io.openliberty.providers;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

import jakarta.data.exceptions.MappingException;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Repository;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.AfterBeanDiscovery;
import jakarta.enterprise.inject.spi.AfterTypeDiscovery;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanAttributes;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;
import jakarta.enterprise.inject.spi.WithAnnotations;

public class PatternExtension implements Extension {
	
	public static final String PROVIDER = "PATTERNS_PROVIDER";
	
    private final ArrayList<Bean<?>> repositoryBeans = new ArrayList<>();

    private final HashSet<AnnotatedType<?>> repositoryTypes = new HashSet<>();

    public <T> void annotatedRepository(@Observes @WithAnnotations(Repository.class) ProcessAnnotatedType<T> event) {
        AnnotatedType<T> type = event.getAnnotatedType();

        Repository repository = type.getAnnotation(Repository.class);
        String provider = repository.provider();
        if (PROVIDER.equals(provider)) {
            System.out.println("Pattern CDI Extension: adding repository " + repository.toString() + ' ' + type.getJavaClass().getName());
            repositoryTypes.add(type);
        } else {
        	System.out.println("Pattern CDI Extension: ignore repository " + repository.toString() + ' ' + type.getJavaClass().getName());
        }
    }

    public void afterTypeDiscovery(@Observes AfterTypeDiscovery event, BeanManager beanMgr) {
        for (AnnotatedType<?> repositoryType : repositoryTypes) {
            Class<?> repositoryInterface = repositoryType.getJavaClass();

            Class<?> entityClass = null;
            for (Type interfaceType : repositoryInterface.getGenericInterfaces()) {
                if (interfaceType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) interfaceType;
                    if (parameterizedType.getRawType().getTypeName().startsWith(DataRepository.class.getPackageName())) {
                        Type typeParams[] = parameterizedType.getActualTypeArguments();
                        if (typeParams.length == 2 && typeParams[0] instanceof Class) {
                            entityClass = (Class<?>) typeParams[0];
                            break;
                        }
                    }
                }
            }

            if (entityClass == null)
                throw new MappingException("Did not find the entity class for " + repositoryInterface);

            io.openliberty.anno.Entity entityAnno = entityClass.getAnnotation(io.openliberty.anno.Entity.class);

            if (entityAnno == null) {
                Repository repository = repositoryType.getAnnotation(Repository.class);
                if (!Repository.ANY_PROVIDER.equals(repository.provider())) {
                    String message = "The Pattern Jakarta Data provider cannot provide the " +
                            repositoryType.getJavaClass().getName() + " repository because the repository's " +
                            entityClass.getName() + " entity class is not annotated with " + io.openliberty.anno.Entity.class.getName();
                    throw new MappingException(message);
                }
            } else {
                BeanAttributes<?> attrs = beanMgr.createBeanAttributes(repositoryType);
                Bean<?> bean = beanMgr.createBean(attrs, repositoryInterface, new PatternsProducer.Factory<>());
                repositoryBeans.add(bean);
            }
        }
    }

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery event, BeanManager beanMgr) {
        for (Bean<?> bean : repositoryBeans) {
            event.addBean(bean);
        }
    }
}
