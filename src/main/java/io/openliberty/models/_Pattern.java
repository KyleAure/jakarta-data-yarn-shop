package io.openliberty.models;

import io.openliberty.entities.Pattern;
import jakarta.data.metamodel.Attribute;
import jakarta.data.metamodel.SortableAttribute;
import jakarta.data.metamodel.StaticMetamodel;
import jakarta.data.metamodel.TextAttribute;
import jakarta.data.metamodel.impl.AttributeRecord;
import jakarta.data.metamodel.impl.SortableAttributeRecord;
import jakarta.data.metamodel.impl.TextAttributeRecord;

@StaticMetamodel(Pattern.class)
public class _Pattern {
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String AUTHOR = "author";
	public static final String COST = "cost";
	public static final String LINK = "link";
	
	public static final SortableAttribute<Pattern> id = new SortableAttributeRecord<>(ID);
	public static final SortableAttribute<Pattern> cost = new SortableAttributeRecord<>(COST);
	
	public static final TextAttribute<Pattern> name = new TextAttributeRecord<>(NAME);
	public static final TextAttribute<Pattern> author = new TextAttributeRecord<>(AUTHOR);
	
	public static final Attribute<Pattern> link	= new AttributeRecord<>(LINK);
}
