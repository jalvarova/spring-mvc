package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Product;

public interface ProductService {
	List<Product> findByName(String term);
}
