package com.ahlquist.estore.builder;

import org.json.JSONObject;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.Selection;

public class SelectionBuilder implements IEntityBuilder<Selection> {

	public static final String ID = "id";
	public static final String CARTID = "cartid";
	public static final String COUNT = "count";
	public static final String PRICEID = "priceid";
	public static final String PRODUCTID = "productid";
	public static final String VARIANTUUID = "variantuuid";
	public static final String USERID = "userid";

	@Override
	public Selection build(JSONObject json, int level) {
		Selection s = new Selection();
		s.setId(json.getLong(ID));
		s.setCartId(json.getLong(CARTID));
		s.setCount(json.getInt(COUNT));
		s.setPriceId(json.getLong(PRICEID));
		s.setProductId(json.getLong(PRODUCTID));
		s.setUserId(json.getLong(USERID));
		s.setVariantUuid(json.getString(VARIANTUUID));
		return s;
	}

	@Override
	public String toString(Selection t) {
		return new EntityToJsonUtil<Selection>().toString(t);
	}

	@Override
	public JSONObject toJson(Selection t) {
		return new JSONObject(toString(t));
	}

}
