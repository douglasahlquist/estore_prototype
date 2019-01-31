package com.ahlquist.estore.services;

import java.util.Map;

import com.ahlquist.estore.model.User;

public interface UserService extends BaseService<User, Long> {

	String login(Map<String, String> map);

	void create(Map<String, String> map);

}
