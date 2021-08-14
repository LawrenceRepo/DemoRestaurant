package com.lawrence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class DishCategoryMenuItem extends MenuItem
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long					id;
		@NotNull@OneToOne(cascade= CascadeType.ALL)//cascade=CascadeType.ALL propagates all operations — including Hibernate-specific ones — from a parent to a child entity.

		private DishCategory	dishCategory;
	}
