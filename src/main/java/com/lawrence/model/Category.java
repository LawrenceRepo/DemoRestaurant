package com.lawrence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Category
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long					id;
		
		@OneToOne(cascade=CascadeType.MERGE)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
		private Media					thumbnail;
		
		@OneToOne	(cascade=CascadeType.MERGE)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
		private Media					picture;
		
		@ManyToMany
		private Set <Product>	products;
	}
