package com.bionexo.ubs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GeoCode {
	
	private double latitude;
	
	private double longitude;
	
}
