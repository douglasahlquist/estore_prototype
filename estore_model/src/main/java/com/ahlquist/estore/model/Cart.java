/**
 * 
 */
package com.ahlquist.estore.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "carts")
public class Cart extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -5014429883644829570L;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "begin_time")
	private Timestamp beginTime;

	@Column(name = "purchase_time")
	private Timestamp purchaseTime;

}
