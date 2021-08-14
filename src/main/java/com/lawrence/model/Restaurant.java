package com.lawrence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Restaurant extends PointOfService
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long								id;
		
		private boolean							servesGuest;
		
		@ManyToOne
		@JoinColumn(name = "plant_id", referencedColumnName = "id")
		private Plant								plant;
		
		@ManyToMany
		private Set <Dish>					dishes;
		
		@OneToMany
		private List <Menu>					menus;
		
		@ManyToMany
		private Set<DishCategoryToRestaurant> dishCategories;
		
	}
