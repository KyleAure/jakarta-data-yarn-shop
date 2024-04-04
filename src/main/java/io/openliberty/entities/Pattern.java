package io.openliberty.entities;

import java.net.URL;

// Gathered via mock REST API

@io.openliberty.anno.Entity
public record Pattern (
		long id, 
		String name, 
		String author, 
		double cost, 
		URL link) { }
