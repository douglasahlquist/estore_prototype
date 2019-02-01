package com.ahlquist.estore.builder;

import java.sql.Timestamp;
import java.util.Map;

import org.json.JSONObject;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.Product;
import com.ahlquist.estore.model.ProductInventoryPrice;

public class ProductInventoryPriceBuilder implements IEntityBuilder<ProductInventoryPrice> {

	public static final String AMOUNT = "amount";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORY = "category";
	public static final String COUNT = "count";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String IMAGEURL = "imageurl";
	public static final String SALEENDTIME = "saleendtime";
	public static final String SALESTARTTIME = "salestarttime";
	public static final String VARIATIONUUID = "variationuuid"; 

	@Override
	public ProductInventoryPrice build(JSONObject json, int level) {
		ProductInventoryPrice p = new ProductInventoryPrice();
		p.setAmount(json.getDouble(AMOUNT));
		p.setAttributes(json.getJSONObject(ATTRIBUTES));
		p.setCategory(json.getString(CATEGORY));
		p.setCount(json.getInt(COUNT));
		p.setDescription(json.getString(DESCRIPTION));
		p.setId(json.getLong(ID));
		p.setImageUrl(json.getString(IMAGEURL));
		
		String et = json.getString(SALEENDTIME);
		Timestamp saleEndTime = com.ahlquist.estore.commons.Utils.stringToTimestamp(et);
		String st = json.getString(SALESTARTTIME);
		Timestamp saleStartTime = com.ahlquist.estore.commons.Utils.stringToTimestamp(st);
		
		p.setSaleEndTime(saleEndTime);
		p.setSaleStartTime(saleStartTime);
		p.setVariationUuid(json.getString(VARIATIONUUID));
		
		return p;
	}

	@Override
	public ProductInventoryPrice build(Map<String, ?> map) {
		ProductInventoryPrice p = new ProductInventoryPrice();
		p.setAmount(Double.parseDouble((String)map.get(AMOUNT)));
		p.setAttributes((JSONObject)map.get(ATTRIBUTES));
		p.setCategory((String)map.get(CATEGORY));
		p.setCount(Integer.parseInt((String)map.get(COUNT)));
		p.setDescription((String)map.get(DESCRIPTION));
		p.setId(Long.parseLong((String)map.get(ID)));
		p.setImageUrl((String)map.get(IMAGEURL));
		
		String et = (String)map.get(SALEENDTIME);
		Timestamp saleEndTime = com.ahlquist.estore.commons.Utils.stringToTimestamp(et);
		String st = (String)map.get(SALESTARTTIME);
		Timestamp saleStartTime = com.ahlquist.estore.commons.Utils.stringToTimestamp(st);
		
		p.setSaleEndTime(saleEndTime);
		p.setSaleStartTime(saleStartTime);
		p.setVariationUuid((String)map.get(VARIATIONUUID));
		
		return p;
	}

	@Override
	public String toString(ProductInventoryPrice t) {
		return new EntityToJsonUtil<ProductInventoryPrice>().toString(t);
	}

	@Override
	public JSONObject toJson(ProductInventoryPrice t) {
		return new JSONObject(toString(t));
	}

}
