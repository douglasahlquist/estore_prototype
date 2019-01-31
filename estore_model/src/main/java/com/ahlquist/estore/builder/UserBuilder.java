package com.ahlquist.estore.builder;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.model.User;

@Component("userBuilder")
public class UserBuilder implements IEntityBuilder<User> {

	public static final String EMAIL = "email";
	public static final String ID = "id";
	public static final String FIRSTNAME = "first_name";
	public static final String LASTNAME = "last_name";
	public static final String LOGINTOKEN = "login_token";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	public User build(JSONObject json, int level) {

		level--;
		User u = new User();
		u.setEmail(json.getString(EMAIL));
		u.setFirstname(json.getString(FIRSTNAME));
		u.setId(json.getLong(ID));
		u.setLastname(json.getString(LASTNAME));
		u.setLoginToken(json.getString(LOGINTOKEN));
		u.setUsername(json.getString(USERNAME));
		return u;
	}

	@Override
	public String toString(User t) {
		return new EntityToJsonUtil<User>().toString(t);
	}

	@Override
	public JSONObject toJson(User t) {
		return new JSONObject(toString(t));
	}

	@Override
	public User build(Map<String, ?> map) {
		User u = new User();
		u.setEmail((String)map.get(EMAIL));
		u.setFirstname((String)map.get(FIRSTNAME));
		u.setId((Long)map.get(ID));
		u.setLastname((String)map.get(LASTNAME));
		u.setLoginToken((String)map.get(LOGINTOKEN));
		u.setUsername((String)map.get(USERNAME));
		return u;
	}
}
