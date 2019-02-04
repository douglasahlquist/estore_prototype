package com.ahlquist.estore.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ahlquist.estore.builder.ProductBuilder;
import com.ahlquist.estore.model.Product;
import com.ahlquist.estore.repositories.ProductRepository;

@Service("productServive")
public class ProductServiceImpl extends BaseServiceImpl<ProductRepository, ProductBuilder, Product, Long>
		implements ProductService {

	final static Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Autowired
	public ProductServiceImpl(@Qualifier("productRepository") final ProductRepository repository,
			@Qualifier("productBuilder") final ProductBuilder builder) {
		super(repository, builder);
	}

	@SuppressWarnings("unchecked")
	public List<String> findAllProductCategories() {

		Session session = this.sessionFactory.openSession();
		try {

			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(String.class);
			criteria.setProjection(Projections.property("category")); // Projections.property is used to retrieve
																		// specific columns
			return criteria.list();

		} catch (HibernateException e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return new ArrayList<String>();
	}

	@Override
	public JSONArray findAllProductCategoriesAsJson() {
		JSONArray a = new JSONArray();
		a.put(findAllProductCategories());
		return a;
	}

}
