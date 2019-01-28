package com.ahlquist.estore.services;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.ahlquist.estore.builder.IEntityBuilder;

/**
 * Base Services
 * 
 * @author Douglas Ahlquist
 *
 * @param <R> - The Repository Class
 * @param <B> - The JSONObject to Entity converter class
 * @param <T> - The Entity Type
 * @param <K> - The Key into the database for this Entity Service
 */

public abstract class BaseServiceImpl<R extends CrudRepository<T, K>, B extends IEntityBuilder<T>, T, K>
		implements BaseService<T, K> {

	final static Logger logger = Logger.getLogger(BaseServiceImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	HibernateTransactionManager transactionManager;

	protected final String ID_PRESENT_ERROR = "Error Cannot create instance of %s with id=%d present";

	public BaseServiceImpl(final R repository, final B builder) {
		this.repository = repository;
		this.builder = builder;
	}

	// The Repository used in accessing the database
	private R repository;

	public R getRepository() {
		return repository;
	}

	public B getBuilder() {
		return builder;
	}

	// The Type Builder. Used to convert JSON to Entities
	private B builder;

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public Iterable<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<T> findById(K id) {
		return repository.findById(id);
	}

	@Override
	public T createFromJson(JSONObject json) throws IllegalArgumentException {
		T t = (T) ((IEntityBuilder<T>) builder).build(json, Integer.MAX_VALUE);
		return create(t);
	}

	@Override
	public T create(T t) throws IllegalArgumentException {
		return repository.save(t);
	}

	@Override
	public boolean existsById(K id) {
		return repository.existsById(id);
	}

	@Override
	public void deleteFromJson(JSONObject json) throws IllegalArgumentException {
		@SuppressWarnings("unchecked")
		T t = (T) ((IEntityBuilder<?>) builder).build(json, Integer.MAX_VALUE);
		delete(t);
	}

	@Override
	public void delete(T t) throws IllegalArgumentException {
		Session s = sessionFactory.openSession();
		Transaction tr = s.beginTransaction();
		s.delete(t);
		tr.commit();
		s.close();
	}

	@Override
	public T saveFromJson(JSONObject json) {
		@SuppressWarnings("unchecked")
		T t = (T) ((IEntityBuilder<?>) builder).build(json, Integer.MAX_VALUE);
		t = save(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T t) {
		Session s = sessionFactory.openSession();
		Transaction tr = s.beginTransaction();
		t = (T) s.save(t);
		tr.commit();
		s.close();
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T t) {
		Session s = sessionFactory.openSession();
		Transaction tr = s.beginTransaction();
		t = (T) s.merge(t);
		tr.commit();
		s.close();
		return t;
	}

	@Override
	public void saveAll(Iterable<T> it) {
		it.forEach(t -> save(t));
	}

	@Override
	public JSONArray findAllAsJson() {
		Iterable<T> ja = findAll();
		JSONArray array = new JSONArray();
		ja.forEach(c -> {
			array.put(c.toString());
		});
		return array;
	}

	@Override
	public JSONObject findByIdAsJson(K id) {
		Optional<T> o = repository.findById(id);
		JSONObject json = new JSONObject();
		if (o.isPresent()) {
			String str = o.get().toString();
			logger.debug(str);
			json = new JSONObject(o.get().toString());
		}
		return json;
	}
}
