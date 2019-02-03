package com.ahlquist.estore.services;

import java.util.Optional;

import com.ahlquist.estore.model.Inventory;

public interface InventoryService {

	Optional<Inventory> findByProductIdVariationUuid(Long productId, String variantionUuid);

}
