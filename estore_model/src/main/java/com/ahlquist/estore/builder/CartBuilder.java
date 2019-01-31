package com.ahlquist.estore.builder;

import java.sql.Timestamp;
import java.util.Map;

import org.json.JSONObject;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.Cart;

import com.ahlquist.estore.commons.Utils;

public class CartBuilder implements IEntityBuilder<Cart> {

	public static final String BEGINTIME = "begin_time";
	public static final String ID = "id";
	public static final String PURCHASETIME = "purchase_time";
	public static final String USERID = "user_id";

	public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S"; // 2014-04-02 20:04:05

	@Override
	public Cart build(JSONObject json, int level) {

		String purchaseTime = json.getString(PURCHASETIME);
		String beginTime = json.getString(BEGINTIME);

		Timestamp pt = Utils.stringToTimestamp(DEFAULT_TIMESTAMP_FORMAT, purchaseTime);
		Timestamp bt = Utils.stringToTimestamp(DEFAULT_TIMESTAMP_FORMAT, beginTime);

		level--;
		Cart c = new Cart();
		c.setBeginTime(bt);
		c.setId(json.getLong(ID));
		c.setPurchaseTime(pt);
		c.setUserId(json.getLong(USERID));
		return c;

	}

	@Override
	public String toString(Cart t) {
		return new EntityToJsonUtil<Cart>().toString(t);
	}

	@Override
	public JSONObject toJson(Cart t) {
		return new JSONObject(toString(t));
	}

	@Override
	public Cart build(Map<String, ?> map) {
		String beginTime = (String)map.get(BEGINTIME);
		Timestamp bt = Utils.stringToTimestamp(Utils.DEFAULT_DATE_FORMAT, beginTime);
		
		String purchaseTime = (String)map.get(PURCHASETIME);
		Timestamp pt = Utils.stringToTimestamp(Utils.DEFAULT_DATE_FORMAT, purchaseTime);
		
		Cart c = new Cart();
		c.setBeginTime(bt);
		c.setId((Long)map.get(ID));
		c.setPurchaseTime(pt);
		c.setUserId((Long)map.get(USERID));
		return c;
	}

}
