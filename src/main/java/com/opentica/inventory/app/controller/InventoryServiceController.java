package com.opentica.inventory.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opentica.inventory.app.bean.ProductInfo;
import com.opentica.inventory.app.bean.ProductRepository;

/**
 * Controller for CRUD operations on the product inventory
 *
 */
@RestController
@RequestMapping("/api")
public class InventoryServiceController {
	@Autowired
	ProductRepository productRepository;

	/**
	 * API to get list of all available products
	 * 
	 * @return
	 */
	@GetMapping("/products")
	public List<ProductInfo> getAllProducts() {
	    return productRepository.findAll();
	}

	/**
	 * Add a product with the provided product details
	 * 
	 * @param productInfo
	 * @return
	 */
	@PostMapping("/addProduct")
	public ProductInfo addProduct(@Valid @RequestBody ProductInfo productInfo) {
	    return productRepository.save(productInfo);
	}
	
	/**
	 * Delete the product from the inventory
	 * 
	 * @param productInfo
	 */
	@DeleteMapping("/deleteProduct")
	public void deleteProuct(@Valid @RequestBody ProductInfo productInfo) {
		productRepository.delete(productInfo);
	}
	
	/**
	 * Delete the product from the inventory
	 * 
	 * @param productInfo
	 */
	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		productRepository.deleteAll();
	}
}
