package com.wellsfargo.training.ims.controller;

import com.wellsfargo.training.ims.exception.ResourceNotFoundException;
import com.wellsfargo.training.ims.model.Product;
import com.wellsfargo.training.ims.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Spring RestController annotation is used to create RESTful web services using Spring MVC.
		* Spring RestController takes care of mapping request data to the defined request handler method.
		* Once response body is generated from the handler method, it converts it to JSON or XML response.
		*
		* @RequestMapping - maps HTTP request with a path to a controller
		* */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class ProductController {
// POSTMAN - REST API Testing Tool

/*Open PostMan, make a POST Request - http://localhost:8085/pms/api/products/
    Select body -> raw -> JSON */
	@Autowired
	private ProductService productService;
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product product)
	{
		try{
			Product p= productService.saveProduct(product);
			return	ResponseEntity.status(HttpStatus.CREATED).body(p);

		}
		catch (Exception e){
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		/* @RequestBody annotation automatically deserializes the JSON into a Java type */

	}
	@GetMapping("/products")
	public ResponseEntity<List> getAllProducts()
	{
		try{
			List<Product> products= productService.findall();
			return ResponseEntity.ok(products);
		}
		catch (Exception e){
			e.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	/* @PathVariable is a Spring annotation which indicates that a method parameter should be
    *  bound to a URI template variable.
      @PathVariable annotation is used to read an URL template variable.
    */
	//Open PostMan, make a GET Request - http://localhost:8085/ims/api/products/4
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long pid) throws ResourceNotFoundException{
		Product p = productService.getSingleProduct(pid).orElseThrow(() -> new ResourceNotFoundException("Product Not Found for this Id: "));
		return ResponseEntity.ok().body(p);
	}
	//Open PostMan, make a PUT Request - http://localhost:8085/ims/api/products/1003
	//Select body -> raw -> JSON
	//Update JSON product object with new Values.
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long pid, @Validated @RequestBody Product product) throws ResourceNotFoundException{
		Product p = productService.getSingleProduct(pid).orElseThrow(() -> new ResourceNotFoundException("Product Not Found for this Id: "));
		//Update product with new values
		p.setBrand(product.getBrand());
		p.setName(product.getName());
		p.setMadeIn(product.getMadeIn());
		p.setPrice(product.getPrice());
		final Product updatedProduct = productService.saveProduct(p);
		return ResponseEntity.ok().body(updatedProduct);
	}
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable(value = "id") Long pid) throws ResourceNotFoundException{
		productService.getSingleProduct(pid).orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
		productService.deleteProduct(pid);
		Map<String,Boolean> response=new HashMap<String,Boolean>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	

}
