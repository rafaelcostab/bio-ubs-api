package com.bionexo.ubs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BasicHealthUnit {
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private String phone;
	
	private GeoCode geoCode;
	
	private Scores score;
	
}
