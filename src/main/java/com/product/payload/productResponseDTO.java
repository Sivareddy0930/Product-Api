package com.product.payload;

import java.sql.Blob;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class productResponseDTO {
	
	private Integer pid;
	private String pname;
	private String pprice;
	private String pdescription;
	@Lob
	private String pimage;
	private Integer pqty;

}
