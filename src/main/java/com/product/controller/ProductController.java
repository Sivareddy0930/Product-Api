package com.product.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.Model.Product;
import com.product.payload.productResponseDTO;
import com.product.service.ProductService;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestParam Integer pid,@RequestParam String pname,@RequestParam String pprice,@RequestParam String pdescription,@RequestParam("pimage") MultipartFile file,@RequestParam Integer pqty) throws SerialException, IOException, SQLException{
		
		return productService.createProduct(pid, pname, pprice, pdescription, file, pqty);
		
	}
	
	@GetMapping("/getProduct/{pid}")
	public productResponseDTO getById(@PathVariable Integer pid) throws SQLException {
		Product p=productService.readSingleProduct(pid);
		
		Blob img = p.getPimage();
		String photoBase64 = null;
		if(img !=null) {
			byte[] imgBytes = img.getBytes(1, (int)img.length());
            if (imgBytes != null && imgBytes.length > 0){
                 photoBase64 = Base64.encodeBase64String(imgBytes);
                
            }
		}
		
		productResponseDTO response =new productResponseDTO(p.getPid(),p.getPname(),p.getPprice(), p.getPdescription(), photoBase64, p.getPqty());
		return response ;
		
	}
	
	@GetMapping("/getProducts")
	public List<productResponseDTO> getAllProducts() throws SQLException{
		List<Product> productList =  productService.readAllProduct();
		
		List<productResponseDTO> response = new ArrayList<>();
		
		for(Product p: productList) {
			Blob img = p.getPimage();
			String photoBase64 = null;
			if(img !=null) {
				byte[] imgBytes = img.getBytes(1, (int)img.length());
	            if (imgBytes != null && imgBytes.length > 0){
	                 photoBase64 = Base64.encodeBase64String(imgBytes);
	                
	            }
			}
			
			
			productResponseDTO productResponse = new productResponseDTO(p.getPid(),p.getPname(),p.getPprice(), p.getPdescription(), photoBase64, p.getPqty());
			response.add(productResponse);
		}
		

		return response ;
		
	}
	
	@PutMapping("/updateProduct/{ProductId}")
	public String updateProduct(@PathVariable Integer ProductId, @RequestParam Integer pid,@RequestParam String pname, @RequestParam String pprice, @RequestParam String pdescription,@RequestParam("pimage") MultipartFile file,@RequestParam Integer pqty) throws SerialException, SQLException, IOException{
				

		return productService.updateProduct(ProductId, pid, pname, pprice, pdescription, file, pqty) ;
		
	}
	
	@DeleteMapping("/deleteProduct/{ProductId}")
	public String deleteproduct(@PathVariable Integer ProductId){
		
		return productService.deleteProduct(ProductId) ;
		
	}
	

}
