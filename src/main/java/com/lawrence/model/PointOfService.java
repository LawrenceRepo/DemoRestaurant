package com.lawrence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class PointOfService
	{
		
		private String					name;
		
		private Address					address;
		
		private String					description;
		
		@OneToOne(cascade = CascadeType.ALL)
		private OpeningSchedule	openingSchedule;
		
		@OneToMany(cascade = CascadeType.ALL)
		private List <Media>		media	= new ArrayList <>();
		
	}
