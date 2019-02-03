package com.ahlquist.estore.services;

import com.ahlquist.estore.model.Selection;

public interface SelectionService extends BaseService<Selection, Long> {

	void deleteAllSlectionWithCartId(final Long cartId);

	boolean existsByAllButCount(final Long userId, final Long cartId, final Long productId,
			final String variantionUuid, final Long priceId);

}
