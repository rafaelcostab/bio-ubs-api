package com.bionexo.ubs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Scores {
	private int scoreSize;
	
	private int scoreAdaptationForSeniors;
	
	private int scoresMedicalEquipament;
	
	private int scoresMedicine;
}
