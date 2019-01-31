package com.ahlquist.estore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlquist.estore.model.Selection;

@Repository("selectionRepository")
public interface SelectionRepository extends CrudRepository<Selection, Long> {

	@Query("SELECT s FROM selections s where t.cartid = :cartid")
	List<Selection> findAllByCartId(@Param("cartid")final Long cartId);

}
