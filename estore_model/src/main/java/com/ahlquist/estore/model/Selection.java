package com.ahlquist.estore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "selections")
public class Selection extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 2380575758481965345L;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "cart_id")
	private Long cartId;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "variant_uuid")
	private String variantUuid;

	@Column(name = "price_id")
	private Long priceId;
	
	private int count;

}
