package io.openliberty.repositories;

import java.util.UUID;

import io.openliberty.entities.Yarn;
import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

import static jakarta.data.repository.Repository.ANY_PROVIDER;
import static jakarta.data.repository.Repository.DEFAULT_DATA_STORE;

@Repository(provider = ANY_PROVIDER, dataStore = DEFAULT_DATA_STORE)
public interface Example extends BasicRepository<Yarn, UUID> {
	
}
