package com.lawrence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Menu
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long								id;
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "menu",fetch = FetchType.EAGER)
		private List <MenuSection>	sections;
		@ManyToOne
		private Restaurant					restaurant;
	}
