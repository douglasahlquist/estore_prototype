package com.ahlquist.estore.services;

import com.ahlquist.estore.model.Selection;

public interface SelectionService extends BaseService<Selection, Long> {
	
	void deleteAllSlectionWithCartId(final Long cartId);

}
