package com.ahlquist.estore.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ahlquist.estore.builder.SelectionBuilder;
import com.ahlquist.estore.model.Selection;
import com.ahlquist.estore.repositories.SelectionRepository;

public class SelectionServiceImpl extends BaseServiceImpl<SelectionRepository, SelectionBuilder, Selection, Long>
		implements SelectionService {

	final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	@Autowired
	public SelectionServiceImpl(@Qualifier("selectionRepository") final SelectionRepository repository,
			@Qualifier("selectionBuilder") final SelectionBuilder builder) {
		super(repository, builder);
	}

}
