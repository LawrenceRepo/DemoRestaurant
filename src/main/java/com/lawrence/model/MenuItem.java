package com.lawrence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MenuItem
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long				id;
		@ManyToOne
		@JoinColumn(name = "section_id", referencedColumnName = "id")
		private MenuSection	section;
		
		private String			title;
		private String			description;
		@OneToOne	(cascade=CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.(cascade = CascadeType.ALL)
		private Media				thumbnail;
		private String			price;
		
	}
