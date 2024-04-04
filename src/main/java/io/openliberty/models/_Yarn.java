package io.openliberty.models;

import io.openliberty.entities.Yarn;
import jakarta.data.metamodel.Attribute;
import jakarta.data.metamodel.SortableAttribute;
import jakarta.data.metamodel.StaticMetamodel;
import jakarta.data.metamodel.TextAttribute;
import jakarta.data.metamodel.impl.AttributeRecord;
import jakarta.data.metamodel.impl.SortableAttributeRecord;
import jakarta.data.metamodel.impl.TextAttributeRecord;

@StaticMetamodel(Yarn.class)
public class _Yarn {
	public static final String ID = "id";
	public static final String STOCK = "stock";
	public static final String COST = "cost";
	public static final String BRAND = "brand";
	public static final String COLORWAY = "colorway";
	public static final String PRIMARY = "primary";
	public static final String PRIMARY_PERCENT = "primaryPercent";
	
	public static final Attribute<Yarn> id = new AttributeRecord<>(ID);
	
	public static final SortableAttribute<Yarn> stock = new SortableAttributeRecord<>(STOCK);
	public static final SortableAttribute<Yarn> cost = new SortableAttributeRecord<>(COST);
	
	public static final TextAttribute<Yarn> brand = new TextAttributeRecord<>(BRAND);
	public static final TextAttribute<Yarn> colorway = new TextAttributeRecord<>(COLORWAY);
}
