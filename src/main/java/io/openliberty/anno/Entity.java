package io.openliberty.anno;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.data.spi.EntityDefining;

@EntityDefining
@Retention(RUNTIME)
@Target(TYPE)
public @interface Entity { }
