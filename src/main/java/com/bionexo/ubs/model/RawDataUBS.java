package com.bionexo.ubs.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class represents the model of data imported of csv file
 * @author rafaelcosta
 *
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RawDataUBS {
	
	private String vlr_latitude;
	
	private String vlr_longitude;
	
	@EqualsAndHashCode.Include
	private String cod_munic;
	
	private String cod_cnes;
	
	private String nom_estab;
	
	private String dsc_endereco;
	
	private String dsc_bairro;
	
	private String dsc_cidade;
	
	private String dsc_telefone;
	
	private String dsc_estrut_fisic_ambiencia;
	
	private String dsc_adap_defic_fisic_idosos;
	
	private String dsc_equipamentos;
	
	private String dsc_medicamentos;
	
}