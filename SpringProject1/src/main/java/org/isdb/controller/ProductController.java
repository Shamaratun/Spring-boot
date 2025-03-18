package org.isdb.controller;

import java.util.List;


import org.isdb.model.Product;
import org.isdb.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/Product")
public class ProductController {


	private ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

//	RequestMapping()
	@PostMapping
	public Product saveProduct(@RequestBody Product Product) {
		Product saveProduct = service.saveProduct(Product);
		return saveProduct;

	}

	@GetMapping("/{id}")
	public Product getProById(@PathVariable ("id") int id) {
		Product proById = service.getProById(id);
		return proById;
	}

	@GetMapping
	public List<Product> findAllPro() {
		List<Product> allPro = service.getAllPro();
		return allPro;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable ("id") int id) {
		service.deleteById(id);
	}

	@PutMapping("/{id}")
	public Product updatePro(@PathVariable ("id") int id, @RequestBody Product Product) {
		Product updated = service.updatePro(id, Product);
		return updated;
	}
	   @GetMapping("/byName")
	    public List<Product> getProByName(@PathVariable String name) {
		   List<Product> byName= service.getProByName(name); 
		   return byName;
	    }
}
