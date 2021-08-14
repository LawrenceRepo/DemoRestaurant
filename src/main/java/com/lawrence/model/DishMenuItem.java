package com.lawrence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class DishMenuItem extends MenuItem
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long	id;
		@OneToOne	(cascade= CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
		@NotNull
		@JoinColumn(name = "dish_id")
		private Dish	dish;
	}
