package com.lawrence.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class ProductDto
	{
		@NotEmpty
		private String				code;
		@NotEmpty
		private String				name;
		@NotEmpty
		private String				description;
		private MultipartFile	thumbnail;
		private MultipartFile	picture;
		private List <Long>		supercategories;
		private double employeePrice;
		private double guestPrice;

	}
