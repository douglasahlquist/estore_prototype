package com.ahlquist.estore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Inventory;

/**
 * @author Douglas Ahlquist
 *
 */
@Repository("inventoryRepository")
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
	
	@Query("SELECT i FROM Inventory i where i.productId = :productId AND u.variantionUuid = :variantionUuid")
	Optional<Inventory> getByProductIdVariationUuid(final Long productId, final String variantionUuid);

}
