package com.ahlquist.estore.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ahlquist.estore.builder.SelectionBuilder;
import com.ahlquist.estore.model.Selection;
import com.ahlquist.estore.repositories.SelectionRepository;

@Service("selectionServive")
public class SelectionServiceImpl extends BaseServiceImpl<SelectionRepository, SelectionBuilder, Selection, Long>
		implements SelectionService {

	final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	@Autowired
	public SelectionServiceImpl(@Qualifier("selectionRepository") final SelectionRepository repository,
			@Qualifier("selectionBuilder") final SelectionBuilder builder) {
		super(repository, builder);
	}

	/**
	 * empties the users cart of all items
	 */
	@Override
	public void deleteAllSlectionWithCartId(Long cartId) {
		logger.debug("Deleting all selections for cartid: " + cartId.longValue());
		List<Selection> list = this.getRepository().findAllByCartId(cartId);
		for(Selection s: list) {
			logger.debug("deleting selection with cartId: " + cartId.longValue() + " selectionId: " + s.getId().longValue())
			this.getRepository().delete(s);
		}	
	}

}
