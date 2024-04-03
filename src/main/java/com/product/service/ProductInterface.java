package com.product.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.product.Model.Product;
import com.product.payload.productResponseDTO;

public interface ProductInterface {
	
	public abstract String createProduct(Integer pid,String pname,String pprice,String pdescription,MultipartFile file,Integer pqty) throws IOException, SerialException, SQLException;
	public abstract Product readSingleProduct(Integer Pid);
	public abstract List<Product> readAllProduct();
	public abstract String updateProduct(Integer ProductId,Integer pid, String pname, String pprice, String pdescription, MultipartFile file,Integer pqty) throws SerialException, SQLException, IOException;
	public abstract String deleteProduct(Integer Pid);

}
