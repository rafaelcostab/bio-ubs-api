package com.bionexo.ubs.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("repository.file")
public class RepositoryFileProperties {
	
	private String csvPath;
	
}