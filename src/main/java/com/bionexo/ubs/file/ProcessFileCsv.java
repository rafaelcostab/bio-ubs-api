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
import com.bionexo.ubs.model.GeoCode;
import com.bionexo.ubs.model.RawDataUBS;
import com.bionexo.ubs.model.Scores;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class ProcessFileCsv {

	@Autowired
	private RepositoryFileProperties properties;
	
	private List<BasicHealthUnit> listBasicHelthUnit;
	
	public List<BasicHealthUnit> process() {
		System.out.println("\n::Starting file processing::");
		Long init = System.currentTimeMillis();
		
		try {
			
			if (listBasicHelthUnit == null || listBasicHelthUnit.isEmpty()) {
				System.out.println("Loading file..");
				listBasicHelthUnit = convertRowDataToBasicHealtUnit(openCSV());
			}
			
			System.out.println("Total registers: " + listBasicHelthUnit.size());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Process time: " + (System.currentTimeMillis() - init)+ "ms");
		return listBasicHelthUnit;
	}
	
	/**
	 * Open file and convert to list object
	 * @return List of RawDataUBS
	 * @throws IOException
	 */
	private List<RawDataUBS> openCSV() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(properties.getCsvPath()).toAbsolutePath());
		
		CsvToBean<RawDataUBS> csvToBean = new CsvToBeanBuilder<RawDataUBS>(reader).withType(RawDataUBS.class).build();
		
		return csvToBean.parse();
	}
	
	/**
	 * convert the list of data obtained from the archive to a list of objects of type Basic Health Unit
	 * @param rawDataUBS
	 * @return
	 * @throws IOException
	 */
	private List<BasicHealthUnit> convertRowDataToBasicHealtUnit(List<RawDataUBS> rawDataUBS) throws IOException{
		List<BasicHealthUnit> basicHealthUnit = new ArrayList<>();
		
		for (RawDataUBS data : rawDataUBS) {
			try {
				basicHealthUnit.add(dataToUnit(data));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return basicHealthUnit;
	}
	
	/**
	 * convert the raw data to object BasicHealth Unit
	 * @param data
	 * @return
	 */
	private BasicHealthUnit dataToUnit(RawDataUBS data) {
		BasicHealthUnit basicHealthUnit = new BasicHealthUnit();
		GeoCode geo = new GeoCode();
		Scores score = new Scores();
		
		geo.setLatitude(new Double(data.getVlr_latitude()));
		geo.setLongitude(new Double(data.getVlr_longitude()));
		
		score.setScoreSize(convertScore(data.getDsc_estrut_fisic_ambiencia()));
		score.setScoreAdaptationForSeniors(convertScore(data.getDsc_adap_defic_fisic_idosos()));
		score.setScoresMedicalEquipament(convertScore(data.getDsc_equipamentos()));
		score.setScoresMedicine(convertScore(data.getDsc_medicamentos()));
		
		basicHealthUnit.setId(new Long(data.getCod_munic()));
		basicHealthUnit.setName(data.getNom_estab());
		basicHealthUnit.setAddress(data.getDsc_endereco());
		basicHealthUnit.setCity(data.getDsc_cidade());
		basicHealthUnit.setPhone(data.getDsc_telefone());
		
		basicHealthUnit.setGeoCode(geo);
		basicHealthUnit.setScore(score);

		return basicHealthUnit;
	}
	
	/**
	 * Identify sentences in text and convert to Score
	 * @param rawData
	 * @return
	 */
	private int convertScore(String rawData) {
		if (rawData.toLowerCase().contains("muito acima")) {
			return 3;
		} else if (rawData.toLowerCase().contains("acima")) {
			return 2;
		} else if (rawData.toLowerCase().contains("abaixo")) {
			return 1;
		} else {
			return 0;
		}
	}

}
