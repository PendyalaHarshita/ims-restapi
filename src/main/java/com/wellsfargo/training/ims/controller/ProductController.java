package com.wellsfargo.training.ims.controller;

import com.wellsfargo.training.ims.model.Product;
import com.wellsfargo.training.ims.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Spring RestController annotation is used to create RESTful web services using Spring MVC.
		* Spring RestController takes care of mapping request data to the defined request handler method.
		* Once response body is generated from the handler method, it converts it to JSON or XML response.
		*
		* @RequestMapping - maps HTTP request with a path to a controller
		* */
@RestController
@RequestMapping
public class ProductController {
// POSTMAN - REST API Testing Tool

/*Open PostMan, make a POST Request - http://localhost:8085/pms/api/products/
    Select body -> raw -> JSON */
	@Autowired
	private ProductService productService;
	@PostMapping("/products")
	public Product saveProduct(@Validated @RequestBody Product product)
	{
		/* @RequestBody annotation automatically deserializes the JSON into a Java type */
		Product p= productService.saveProduct(product);
		return	p;
	}
	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		return productService.findall();
	}
	

}
