package com.ahlquist.estore.builder;

import java.sql.Timestamp;
import java.util.Map;

import org.json.JSONObject;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.Transaction;

import com.ahlquist.estore.commons.Utils;

public class TransactionBuilder implements IEntityBuilder<Transaction> {

	public static final String ID = "id";
	public static final String USERID = "userid";
	public static final String PAYMENTINFO = "paymentinfo";
	public static final String PRODUCTINFO = "productinfo";
	public static final String TRANSACTIONTIME = "transactiontime";
	public static final String TYPE = "type";

	@Override
	public Transaction build(JSONObject json, int level) {

		String transactionTime = json.getString(TRANSACTIONTIME);
		Timestamp tt = Utils.stringToTimestamp(Utils.DEFAULT_DATE_FORMAT, transactionTime);

		Transaction t = new Transaction();
		t.setId(json.getLong(ID));
		t.setUserId(json.getLong(USERID));
		t.setPaymentInfo(json.getJSONObject(PAYMENTINFO));
		t.setProductInfo(json.getJSONObject(PRODUCTINFO));
		t.setTransactionTime(tt);
		t.setType((char) json.getInt(TYPE));
		return t;
	}

	@Override
	public String toString(Transaction t) {
		return new EntityToJsonUtil<Transaction>().toString(t);
	}

	@Override
	public JSONObject toJson(Transaction t) {
		return new JSONObject(toString(t));
	}

	@Override
	public Transaction build(Map<String, ?> map) {
		String transactionTime = (String)map.get(TRANSACTIONTIME);
		Timestamp tt = Utils.stringToTimestamp(Utils.DEFAULT_DATE_FORMAT, transactionTime);

		Transaction t = new Transaction();
		t.setId((Long)map.get(ID));
		t.setUserId((Long)map.get(USERID));
		t.setPaymentInfo((JSONObject)map.get(PAYMENTINFO));
		t.setProductInfo((JSONObject)map.get(PRODUCTINFO));
		t.setTransactionTime(tt);
		String c = (String)map.get(TYPE);
		t.setType(c.charAt(0));
		return t;

	}

}
