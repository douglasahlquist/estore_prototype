/**
 * 
 */
package com.ahlquist.estore.model;

import java.io.Serializable;

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
@Table(name = "inventory")
public class Inventory extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -3483503442560009628L;

	private Long productId;

	private Long variationid;

	private int quantity = 0;

}
