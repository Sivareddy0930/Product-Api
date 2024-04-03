package com.product.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.product.Model.Product;
import com.product.repository.ProductRepo;


@Service
public class ProductService implements ProductInterface {
	
	@Autowired
	ProductRepo productRepo;
	
	@Override
	public String createProduct(Integer pid, String pname, String pprice, String pdescription, MultipartFile file,Integer pqty) throws IOException, SerialException, SQLException {

		Product p = new Product();
		
		
		p.setPid(pid);
		p.setPname(pname);
		p.setPprice(pprice);
		p.setPdescription(pdescription);
		 if (!file.isEmpty()){
	            byte[] photoBytes = file.getBytes();
	            Blob photoBlob = new SerialBlob(photoBytes);
	            p.setPimage(photoBlob);
	        }

		p.setPqty(pqty);
		
		productRepo.save(p);
		
		return "product successfully added";
	}


	@Override
	public Product readSingleProduct(Integer Pid) {
		
		Product p = productRepo.getBypid(Pid);
		
		return p;
	}

	@Override
	public List<Product> readAllProduct() {
		
		return productRepo.findAll();
	}

	@Override
	public String updateProduct(Integer ProductId,Integer pid, String pname, String pprice, String pdescription, MultipartFile file,Integer pqty) throws SerialException, SQLException, IOException {
		
		Product p = productRepo.getBypid(ProductId);
		
		p.setPid(pid);
		p.setPname(pname);
		p.setPprice(pprice);
		p.setPdescription(pdescription);
		 if (!file.isEmpty()){
	            byte[] photoBytes = file.getBytes();
	            Blob photoBlob = new SerialBlob(photoBytes);
	            p.setPimage(photoBlob);
	        }
		p.setPqty(pqty);
		
		productRepo.save(p);
		
		return "product successfully updated";
	}

	@Override 
	public String deleteProduct(Integer Pid) {
		productRepo.deleteBypid(Pid);
		return "Product successfully deleted with pid "+Pid;
	}


	
	
	
}
