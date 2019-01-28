package com.ahlquist.estore.builder;

import java.sql.Timestamp;

import org.json.JSONObject;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.Price;
import com.ahlquist.estore.commons.Utils;


public class PriceBuilder implements IEntityBuilder<Price> {
	
	public static final String AMOUNT = "amount";
	public static final String ENDTIME = "endtime";
	public static final String STARtTIME = "starttime";
	public static final String ID = "id";

	@Override
	public Price build(JSONObject json, int level) {
		
		String endtime = json.getString(ENDTIME);
		String starttime = json.getString(STARtTIME);
		
		Timestamp et = Utils.stringToTimestamp(Utils.DEFAULT_DATE_FORMAT, endtime);
		Timestamp st = Utils.stringToTimestamp(Utils.DEFAULT_DATE_FORMAT, starttime);
		
		Price p = new Price();
		p.setAmount(json.getDouble(AMOUNT));
		p.setEndTime(et);
		p.setId(json.getLong(ID));
		p.setStartTime(st);
		
		return p;
	}

	@Override
	public String toString(Price t) {
		return new EntityToJsonUtil<Price>().toString(t);
	}

	@Override
	public JSONObject toJson(Price t) {
		return new JSONObject(toString(t));
	}

}
