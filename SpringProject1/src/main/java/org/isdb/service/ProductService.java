package org.isdb.service;

import java.util.List;
import java.util.Optional;

import org.isdb.model.Product;
import org.isdb.repository.ProductRepository;
import org.springframework.stereotype.Service;




@Service

public class ProductService {
	public final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public Product saveProduct(Product product) {
		int save = repository.save(product);
		return getProById(save);
	}

	public Product getProById(int id) {
		Optional<Product> byId = repository.findById(id);
		return byId.get();
	}

	public List<Product> getAllPro() {
		List<Product> all = repository.findAll();
		return all;

	}

	public void deleteById(int id) {
		repository.deleteById(id);

	}

	public Product updatePro(int id, Product product) { 
		product.setId(id);
		repository.update(product);
		return getProById(id);
	}

	      
	    

	public List<Product> getProByName(String name) {
		 return repository.findByName(name); 
	}
	}