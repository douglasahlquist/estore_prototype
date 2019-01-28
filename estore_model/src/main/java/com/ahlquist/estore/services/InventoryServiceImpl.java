package com.ahlquist.estore.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ahlquist.estore.builder.InventoryBuilder;
import com.ahlquist.estore.model.Inventory;
import com.ahlquist.estore.repositories.InventoryRepository;

public class InventoryServiceImpl extends BaseServiceImpl<InventoryRepository, InventoryBuilder, Inventory, Long>
		implements InventoryService {

	final static Logger logger = Logger.getLogger(InventoryServiceImpl.class);

	@Autowired
	public InventoryServiceImpl(@Qualifier("inventoryRepository") final InventoryRepository inventoryRepository,
			@Qualifier("inventoryBuilder") final InventoryBuilder inventoryBuilder) {
		super(inventoryRepository, inventoryBuilder);
	}

}
