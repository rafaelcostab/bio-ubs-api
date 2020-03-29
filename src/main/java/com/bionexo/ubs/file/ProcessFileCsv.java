package com.bionexo.ubs.file;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bionexo.ubs.model.BasicHealthUnit;
import com.bionexo.ubs.model.RawDataUBS;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class ProcessFileCsv {

	@Autowired
	private RepositoryFileProperties properties;
	
	private List<BasicHealthUnit> ubss;
	
	public void process() {
		System.out.println(properties.getCsvPath());
		
		try {
			
			if (ubss == null || ubss.isEmpty()) {
				System.out.println("Loading file..");
				ubss = convertRowDataToBasicHealtUnit(loadFile());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<RawDataUBS> loadFile() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(properties.getCsvPath()));
		
		CsvToBean<RawDataUBS> csvToBean = new CsvToBeanBuilder<RawDataUBS>(reader).withType(RawDataUBS.class).build();
		
		return csvToBean.parse();
	}
	
	private List<BasicHealthUnit> convertRowDataToBasicHealtUnit(List<RawDataUBS> rawDataUBS) throws IOException{
		List<BasicHealthUnit> basicHealthUnit = new ArrayList<>();
		
		for (RawDataUBS data : rawDataUBS) {
			basicHealthUnit.add(dataToUnit(data));
		}
		
		return basicHealthUnit;
	}
	
	private BasicHealthUnit dataToUnit(RawDataUBS data) {
		BasicHealthUnit basicHealthUnit = new BasicHealthUnit();
		
		basicHealthUnit.setId(new Long(data.getCod_munic()));
		basicHealthUnit.setName(data.getNom_estab());
		basicHealthUnit.setAddress(data.getDsc_endereco());
		basicHealthUnit.setCity(data.getDsc_cidade());
		basicHealthUnit.setPhone(data.getDsc_telefone());
		basicHealthUnit.setGeocodeLat(new Double(data.getVlr_latitude()));
		basicHealthUnit.setGeocodeLong(new Double(data.getVlr_longitude()));
//		basicHealthUnit.setScoreSize(Integer.parseInt(data.));
//		basicHealthUnit.setScoreAdaptationForSeniors(scoreAdaptationForSeniors);;
//		basicHealthUnit.setScoresMedicalEquipament(scoresMedicalEquipament);
//		basicHealthUnit.setScoresMedicine(scoresMedicine);
		
		return basicHealthUnit;
	}

}
