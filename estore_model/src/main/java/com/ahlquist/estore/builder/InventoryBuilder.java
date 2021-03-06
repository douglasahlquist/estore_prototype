package com.ahlquist.estore.builder;

import java.util.Map;

import org.json.JSONObject;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.Inventory;

public class InventoryBuilder implements IEntityBuilder<Inventory> {

	public static final String ID = "id";
	public static final String PRODUCTID = "product_id";
	public static final String QUANTITY = "quantity";

	@Override
	public Inventory build(JSONObject json, int level) {

		Inventory i = new Inventory();
		i.setId(json.getLong(ID));
		i.setProductId(json.getLong(PRODUCTID));
		i.setQuantity(json.getInt(QUANTITY));
		return i;

	}

	@Override
	public String toString(Inventory t) {
		return new EntityToJsonUtil<Inventory>().toString(t);
	}

	@Override
	public JSONObject toJson(Inventory t) {
		return new JSONObject(toString(t));
	}

	@Override
	public Inventory build(Map<String, ?> map) {
		Inventory i = new Inventory();
		i.setId((Long)map.get(ID));
		i.setProductId((Long)map.get(PRODUCTID));
		Integer q = Integer.parseInt((String) map.get(QUANTITY));
		i.setQuantity(q.intValue());
		return i;
	}

}
