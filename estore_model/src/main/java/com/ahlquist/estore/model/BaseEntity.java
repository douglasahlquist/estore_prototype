package com.ahlquist.estore.model;

import javax.persistence.Id;

public class BaseEntity<K> implements IBaseEntity<K> {

	@Id
	private K id;

	@Override
	public K getId() {
		return id;

	}

	@Override
	public void setId(K id) {
		this.id = id;
	}
}
