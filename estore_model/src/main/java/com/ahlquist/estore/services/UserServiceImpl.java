package com.ahlquist.estore.services;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ahlquist.estore.builder.UserBuilder;
import com.ahlquist.estore.model.User;
import com.ahlquist.estore.repositories.UserRepository;

@Service("userServive")
public class UserServiceImpl extends BaseServiceImpl<UserRepository, UserBuilder, User, Long> implements UserService {

	final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	@Autowired
	public UserServiceImpl(@Qualifier("userRepository") final UserRepository repository,
			@Qualifier("userBuilder") final UserBuilder builder) {
		super(repository, builder);
	}

	public void create(Map<String, String> map) {

		User u = this.getBuilder().build(map);
		this.getRepository().save(u);

	}

	@Override
	public String login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
