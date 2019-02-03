package com.ahlquist.estore.ecommerce.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ahlquist.estore.builder.ProductInventoryPriceBuilder;
import com.ahlquist.estore.model.Cart;
import com.ahlquist.estore.model.Inventory;
import com.ahlquist.estore.model.ProductInventoryPrice;
import com.ahlquist.estore.repositories.ProductInventoryPriceRepository;
import com.ahlquist.estore.services.CartService;
import com.ahlquist.estore.services.InventoryService;
import com.ahlquist.estore.services.ProductInventoryPriceService;
import com.ahlquist.estore.services.ProductInventoryPriceServieImpl;
import com.ahlquist.estore.services.SelectionService;
import com.ahlquist.estore.commons.*;

public class ECommerceProductServiceImpl extends ProductInventoryPriceServieImpl implements ECommerceProductService {

	public static final String USERID = "user_id";
	public static final String CARTID = "cart_id";
	public static final String PRODUCTID = "product_id";
	public static final String PRICEID = "price_id";
	public static final String VARIATIONUUID = "variation_uuid";
	public static final String QUANTITY = "quantity";

	@Autowired
	@Qualifier("productInventoryPriceService")
	ProductInventoryPriceService productInventoryPriceService;

	@Autowired
	@Qualifier("selectionService")
	SelectionService selectionService;

	@Autowired
	@Qualifier("cartService")
	CartService cartService;
	
	@Autowired
	@Qualifier("inventoryService")
	InventoryService inventoryService;

	@Autowired
	public ECommerceProductServiceImpl(
			@Qualifier("productInventoryPriceRepository") ProductInventoryPriceRepository repository,
			@Qualifier("productInventoryPriceBuilder") ProductInventoryPriceBuilder builder) {
		super(repository, builder);

	}

	/**
	 * Request { "user_id" : "", "cart_id" : "", "product_id" : "", "variation_uuid"
	 * : "" "price_id" : "quantity" : 2 }
	 */
	@Override
	public ResponseEntity<String> addProductVariationToCart(Map<String, String> map) {

		try {
			// TODO(dahlquist) validate map, if any expected values populate RequestError
			// and return in ResponseEntity
			final Long userId = Long.parseLong(map.get(USERID));
			final Long productId = Long.parseLong(map.get(PRODUCTID));
			final String variantionUuid = (map.get(VARIATIONUUID));
			final int quantity = Integer.parseInt(map.get(QUANTITY));
			final Long priceId = Long.parseLong(map.get(PRICEID));

			ResponseMessage rm = new ResponseMessage();
			rm.setClazz(this.getClass().getName());

			// check to see if product/variation is in stock
			Optional<ProductInventoryPrice> o = productInventoryPriceService.findByProductIdVariationUuid(productId,
					variantionUuid);
			if (!o.isPresent()) {
				rm.setDevMessage("could not find pip from productId:" + productId + "variantUuid: " + variantionUuid);
				return new ResponseEntity<String>(rm.toString(), HttpStatus.BAD_REQUEST);
			}
			ProductInventoryPrice pip = o.get();
			if (pip.getCount() < 1) {
				rm.setDevMessage("Inventory count for pip from productId:" + productId + "variantUuid: "
						+ variantionUuid + " =" + pip.getCount());
				return new ResponseEntity<String>(rm.toString(), HttpStatus.BAD_REQUEST);
			}

			// get users cart or create
			Cart c = cartService.findByUserIdOrCreate(userId);
			Long cartId = c.getId();

			// TODO (dahlquist) : wrap in transaction
			// also do we want to decrement the stock for this item now or wait for a
			// purchase
			// or make a reserve count addition for the item that will be reset after some
			// short time
			// decrement stock item

			// create selection item linked to cart, update/save cart total

			selectionService.existsByAllButCount(userId, cartId, productId, variantionUuid, priceId);
			Optional<Inventory> iO = inventoryService.findByProductIdVariationUuid(productId, variantionUuid);
			if(!iO.isPresent()){
				rm.setDevMessage("Inventory Object NOT FOUND matching productId:" + productId.toString() + " variantionUuid:" + variantionUuid);
				rm.setDevMessage("Inventory Object NOT FOUND");
				return new ResponseEntity<String>(rm.toString(), HttpStatus.BAD_REQUEST);
			}
			Inventory i = iO.get();
			int inventoryQuantity = i.getQuantity();
			

			// return entire cart json.

		} catch (NumberFormatException e) {
			RequestError rm = new RequestError();
			
			return new ResponseEntity<String>(rm.toString(), HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
