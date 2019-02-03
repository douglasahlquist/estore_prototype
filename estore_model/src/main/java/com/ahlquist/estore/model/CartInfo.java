package com.ahlquist.estore.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class CartInfo {
	
	private Cart cart;
	
	private List<Selection> selections;

}
