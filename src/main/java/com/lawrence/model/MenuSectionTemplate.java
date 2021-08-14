package com.lawrence.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class MenuSectionTemplate
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int								id;
		
		private int								menuRank;
		
		private String						name;
		
		private LocalDateTime			startTime;
		
		private LocalDateTime			endTime;
		
		@OneToMany
		@NotNull
		private Set<Restaurant> restaurants;

		@OneToOne(cascade= CascadeType.ALL)
		private Media							thumbnail;
		@OneToOne(cascade= CascadeType.ALL)
		private Media							picture;
	}
