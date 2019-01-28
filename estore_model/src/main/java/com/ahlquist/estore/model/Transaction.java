/**
 * 
 */
package com.ahlquist.estore.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.json.JSONObject;

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
@Table(name = "transactions")
public class Transaction extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 2619982979018078241L;

	@Column(name = "user_id")
	private Long userId;
	
	private char type; //either 'd' or 'c' credit/debit
	
	@Column(name = "payment_info")
	private JSONObject paymentInfo;
	
	@Column(name = "product_info")
	private JSONObject productInfo;

	@Column(name = "transaction_time")
	private Timestamp transactionTime;
	
	private double cost;

}
