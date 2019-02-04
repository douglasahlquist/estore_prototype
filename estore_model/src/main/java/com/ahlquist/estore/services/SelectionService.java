package com.ahlquist.estore.services;

import java.util.Optional;

import com.ahlquist.estore.model.Inventory;
import com.ahlquist.estore.model.Selection;

public interface SelectionService extends BaseService<Selection, Long> {

	void deleteAllSlectionWithCartId(final Long cartId);

	/**
	 * Allows for a selection to be incremented of decremented
	 * 
	 * @param userId
	 * @param cartId
	 * @param productId
	 * @param variantionUuid
	 * @param priceId
	 * @param count
	 * @return
	 */
	Optional<Selection> incDecSelection(final Long userId, final Long cartId, final Long productId,
			final String variantionUuid, final Long priceId, final int count);

	Optional<Selection> incDecSelection(final Selection selection, final Inventory inventory, final int count)
			throws IllegalArgumentException;

	boolean existsByAllButCount(final Long userId, final Long cartId, final Long productId, final String variantionUuid,
			final Long priceId);

}
