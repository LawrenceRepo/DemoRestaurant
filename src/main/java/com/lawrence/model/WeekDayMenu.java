package com.lawrence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class WeekDayMenu extends Menu
	{
		private String	weekday;
		private int			week;
		private int			year;
		
	}
