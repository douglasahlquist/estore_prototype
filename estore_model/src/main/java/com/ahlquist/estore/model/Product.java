/**
 * 
 */
package com.ahlquist.estore.model;

import java.io.Serializable;

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
@Table(name = "products")
public class Product extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1374360087863484310L;

	private String category;

	private String description;

	private JSONObject variants;

	@Column(name = "umage_url")
	private String imageUrl;

}
