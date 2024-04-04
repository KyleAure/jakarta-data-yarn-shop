package io.openliberty.yarn.shop;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

public class AppContainerConfig implements SharedContainerConfiguration {
	@Container
	public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("alpine3.19"))
			.withNetworkAliases("testpostgres")
			.withDatabaseName("testdb");

	@Container
	public static MongoDBContainer mongodb = new MongoDBContainer(DockerImageName.parse("mongo:6.0.6"))
			.withNetworkAliases("testmongodb");

	@Container
	public static ApplicationContainer app = new ApplicationContainer()
			.withAppContextRoot("/myservice")
			//PostgreSQL Setup
			.withEnv("POSTGRES_HOSTNAME", "testpostgres")
			.withEnv("POSTGRES_PORT", "5432")
			.withEnv("POSTGRES_USER", postgres.getUsername())
			.withEnv("POSTGRES_PASSWORD", postgres.getPassword())
			.withEnv("POSTGRES_DATABASE", postgres.getDatabaseName())
			//MongoDB Setup
			.withEnv("MONGO_HOST", "testmongodb:27017")
			.withEnv("MONGO_DBNAME", "test")
			.dependsOn(postgres)
			.dependsOn(mongodb);
}
