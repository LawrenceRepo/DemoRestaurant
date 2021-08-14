package com.lawrence.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.lawrence.Exception.CommonException;
import com.lawrence.model.Media;
import com.lawrence.model.Product;
import com.lawrence.repository.CategoryRepo;
import com.lawrence.repository.ProductRepo;
import com.lawrence.service.ProductService;
import com.lawrence.web.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService
	{
		@Autowired
        ProductRepo productRepo;
		@Autowired
        CategoryRepo categoryRepo;
		@Value("${file.upload-dir}")// reading from default application properties file
		private String	uploadPath;
		
		@Override
		@Transactional
		public Product addProduct(ProductDto productDto) throws CommonException, Exception
			{
				Product product = new Product();
				Media thumbnail = null;
				
				BeanUtils.copyProperties(productDto, product);
				MultipartFile pictureFile = productDto.getPicture();
				MultipartFile thumbnailFile = productDto.getThumbnail();
				Files.copy(pictureFile.getInputStream(), Paths.get(uploadPath + pictureFile.getOriginalFilename()));
				
				Media picture = new Media();
				picture.setPhoto(Paths.get(uploadPath + pictureFile.getOriginalFilename()).toString());
				product.setPicture(picture);
				
				thumbnail = Media.class.getConstructor().newInstance();
				
				Files.copy(thumbnailFile.getInputStream(), Paths.get(uploadPath + thumbnailFile.getOriginalFilename()));
				
				thumbnail.setPhoto(Paths.get(uploadPath + thumbnailFile.getOriginalFilename()).toString());
				product.setThumbnail(thumbnail);
				product.setSupercategories(categoryRepo.findByCategoryIds(productDto.getSupercategories()));
				return productRepo.save(product);
			}

			@Override
		public List<Product> getAllProducts()
			{
				return productRepo.findAll();
			}
	}
