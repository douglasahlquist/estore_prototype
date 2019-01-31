package com.ahlquist.estore.builder;

import java.util.Map;

import org.json.JSONObject;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.Product;

public class ProductBuilder implements IEntityBuilder<Product> {

	public static final String CATEGORY = "category";
	public static final String DESCRIPTION = "description";
	public static final String IMAGEURL = "imageUrl";
	public static final String VARIANTS = "variants";
	public static final String ID = "id";

	@Override
	public Product build(JSONObject json, int level) {
		Product p = new Product();
		p.setCategory(json.getString(CATEGORY));
		p.setDescription(json.getString(DESCRIPTION));
		p.setId(json.getLong(ID));
		p.setImageUrl(json.getString(IMAGEURL));
		p.setVariants(json.getJSONObject(VARIANTS));
		return p;
	}

	@Override
	public String toString(Product t) {
		return new EntityToJsonUtil<Product>().toString(t);
	}

	@Override
	public JSONObject toJson(Product t) {
		return new JSONObject(toString(t));
	}

	@Override
	public Product build(Map<String, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
