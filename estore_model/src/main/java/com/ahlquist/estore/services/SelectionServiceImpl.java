package com.ahlquist.estore.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ahlquist.estore.builder.SelectionBuilder;
import com.ahlquist.estore.commons.EntityToJsonUtil;
import com.ahlquist.estore.commons.RequestError;
import com.ahlquist.estore.model.Inventory;
import com.ahlquist.estore.model.Selection;
import com.ahlquist.estore.repositories.SelectionRepository;


@Service("selectionServive")
public class SelectionServiceImpl extends BaseServiceImpl<SelectionRepository, SelectionBuilder, Selection, Long>
		implements SelectionService {

	final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);
	
	@Autowired
	@Qualifier("inventoryService")
	InventoryService inventoryService;

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
			logger.debug("deleting selection with cartId: " + cartId.longValue() + " selectionId: " + s.getId().longValue());
			this.getRepository().delete(s);
		}	
	}
	
	

	@Override
	public boolean existsByAllButCount(Long userId, Long cartId, Long productId, String variantionUuid,
			Long priceId) {
	    Optional<Selection> o = this.getRepository().findByIdAllButCount(userId, cartId, productId, variantionUuid, priceId);
	    return o.isPresent() ? true : false;
	}

	@Override
	public Optional<Selection> incDecSelection(Long userId, Long cartId, Long productId, String variantionUuid,
			Long priceId, int count) throws IllegalArgumentException {
		
		Selection s = null;
		Optional<Selection> sO = this.getRepository().findByIdAllButCount(userId, cartId, productId, variantionUuid, priceId);
		
		//check the inventory
		Optional<Inventory> iO = inventoryService.findByProductIdVariationUuid(productId, variantionUuid);
		if(!iO.isPresent()) {
			
			
			throw new IllegalArgumentException("");
		}

		if(sO.isPresent()) {
			logger.debug("Selection NOT FOUND userId:" + userId.toString() + " cartId:" + cartId.toString() + " productId:" + productId.toString() + " priceId:" + priceId.toString());
		
			//if count - what's in inventory <= 0, don't allow to mke a selection, throw exception
		    return incDecSelection(s, iO.get(), count);
		
		}else{
			s = new Selection();
			s.setCartId(cartId);
			s.setCount(count);   //this is the first time this selection has been added
			s.setPriceId(priceId);
			s.setProductId(productId);
			s.setUserId(userId);
			s.setVariantUuid(variantionUuid);
			
			s = this.getRepository().save(s);
		}
		return Optional.of(s);
	}

	@Override
	public Optional<Selection> incDecSelection(Selection selection, final Inventory inventory, final int count) throws IllegalArgumentException {
		
		int inventoryCount = inventory.getQuantity();
		if(inventoryCount-count<0) {
			RequestError re = new RequestError();
			
			
			EntityToJsonUtil<RequestError> util = new EntityToJsonUtil<>();
			throw new IllegalArgumentException(util.toJson(re).toString());
		}
		selection.setCount(count);
		
		return Optional.of(selection);
	}


}
