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

/*
 
 +-----------------+--------------+------+-----+-------------------+-------------------+
| Field           | Type         | Null | Key | Default           | Extra             |
+-----------------+--------------+------+-----+-------------------+-------------------+
| id              | bigint(20)   | NO   |     | 0                 |                   |
| category        | varchar(32)  | NO   |     | NULL              |                   |
| description     | varchar(200) | NO   |     | NULL              |                   |
| image_url       | varchar(512) | YES  |     | NULL              |                   |
| variation_uuid  | varchar(64)  | NO   |     | NULL              |                   |
| attributes      | json         | NO   |     | NULL              |                   |
| sale_start_time | timestamp    | NO   |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| sale_end_time   | timestamp    | NO   |     | NULL              |                   |
| amount          | decimal(6,2) | YES  |     | NULL              |                   |
| count           | int(11)      | YES  |     | NULL              |                   |
+-----------------+--------------+------+-----+-------------------+-------------------+
 
 
 
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_inventory_price")
public class ProductInventoryPrice extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 101068423818894374L;

	@Column(name = "variation_uuid")
	private String variationUuid;

	private String category;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;
	
	private JSONObject attributes;
	
	@Column(name = "sale_start_time")
	private Timestamp saleStartTime;
	
	@Column(name = "sale_end_time")
	private Timestamp saleEndTime;
	
	private double amount;

	private int count;

}
