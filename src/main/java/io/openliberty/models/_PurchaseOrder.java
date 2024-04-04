package io.openliberty.models;

import io.openliberty.entities.PurchaseOrder;
import jakarta.data.metamodel.Attribute;
import jakarta.data.metamodel.SortableAttribute;
import jakarta.data.metamodel.StaticMetamodel;
import jakarta.data.metamodel.TextAttribute;
import jakarta.data.metamodel.impl.AttributeRecord;
import jakarta.data.metamodel.impl.SortableAttributeRecord;
import jakarta.data.metamodel.impl.TextAttributeRecord;

@StaticMetamodel(PurchaseOrder.class)
public class _PurchaseOrder {
	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String PURCHASE_DATE = "purchasedDate";
	public static final String ITEMS = "items";
	public static final String VERSION = "versionNum";
	
	public static final SortableAttribute<PurchaseOrder> id = new SortableAttributeRecord<>(ID);
	public static final SortableAttribute<PurchaseOrder> purchasedDate = new SortableAttributeRecord<>(PURCHASE_DATE);
	public static final SortableAttribute<PurchaseOrder> version = new SortableAttributeRecord<>(VERSION);
	
	public static final TextAttribute<PurchaseOrder> username = new TextAttributeRecord<>(USERNAME);
	
	public static final Attribute<PurchaseOrder> items	= new AttributeRecord<>(ITEMS);


}
