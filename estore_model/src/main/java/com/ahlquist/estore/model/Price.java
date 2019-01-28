/**
 * 
 */
package com.ahlquist.estore.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Douglas Ahlquist
 *
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class Price extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 2319283551414401713L;
	
	@Column(name="product_id")
	private Long productId;
	
	@Column(name="variation_uuid")
	private String variationUuid;

	private Double amount;

	private Timestamp startTime;

	private Timestamp endTime;

}
