package com.bolsadeideas.springboot.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.ProductDao;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> findByName(String term) {

		return productDao.findByName(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Product findProductById(Long id) {
		// TODO Auto-generated method stub
		return productDao.findById(id).orElse(null);
	}
}
