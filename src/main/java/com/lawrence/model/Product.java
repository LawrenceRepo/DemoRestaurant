package com.lawrence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Product
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long						id;
		
		private String					code;
		private String					name;
		private String					description;
		
		@OneToOne	(cascade=CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
		private Media						thumbnail;
		
		@OneToOne	(cascade=CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
		private Media						picture;
		
		@ManyToMany
		private Set <Category>	supercategories;
	}
