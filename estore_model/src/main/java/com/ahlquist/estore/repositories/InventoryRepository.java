package com.ahlquist.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Inventory;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository("inventoryRepository")
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}
