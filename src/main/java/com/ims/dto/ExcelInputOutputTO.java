package com.ims.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

public class ExcelInputOutputTO extends AbstractTO {

	private static final long serialVersionUID = 1L;


	private String inputFileNameI;
	
	private String inputFileNameII;

	private String randomizationSchFileName;

	private String outputFileNameI;

	private String outputFileNameII;
	
	private Workbook randomizationWorkbook;

	private  Map<String, ArrayList<Double>> valuesPeriodI = null;

	private  Map<String, ArrayList<Double>> valuesPeriodII = null;

	private  List<String> keys = null;

	private  List<String> timePoints;

	private  List<String[]> randomizationSchList;

	public String getInputFileNameI() {
		return inputFileNameI;
	}

	public void setInputFileNameI(String inputFileNameI) {
		this.inputFileNameI = inputFileNameI;
	}

	public String getInputFileNameII() {
		return inputFileNameII;
	}

	public void setInputFileNameII(String inputFileNameII) {
		this.inputFileNameII = inputFileNameII;
	}

	public String getRandomizationSchFileName() {
		return randomizationSchFileName;
	}

	public void setRandomizationSchFileName(String randomizationSchFileName) {
		this.randomizationSchFileName = randomizationSchFileName;
	}

	public String getOutputFileNameI() {
		return outputFileNameI;
	}

	public void setOutputFileNameI(String outputFileNameI) {
		this.outputFileNameI = outputFileNameI;
	}

	public String getOutputFileNameII() {
		return outputFileNameII;
	}

	public void setOutputFileNameII(String outputFileNameII) {
		this.outputFileNameII = outputFileNameII;
	}

	public Map<String, ArrayList<Double>> getValuesPeriodI() {
		if(valuesPeriodI == null){
			valuesPeriodI = new HashMap<String, ArrayList<Double>>();
		}
		return valuesPeriodI;
	}

	public void setValuesPeriodI(Map<String, ArrayList<Double>> valuesPeriodI) {
		
		this.valuesPeriodI = valuesPeriodI;
	}

	public Map<String, ArrayList<Double>> getValuesPeriodII() {
		if(valuesPeriodII == null){
			valuesPeriodII = new HashMap<String, ArrayList<Double>>();
		}
		return valuesPeriodII;
	}

	public void setValuesPeriodII(Map<String, ArrayList<Double>> valuesPeriodII) {
		this.valuesPeriodII = valuesPeriodII;
	}

	public List<String> getKeys() {
		if(keys == null){
			keys = new ArrayList<String>();
		}
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public List<String> getTimePoints() {
		if(timePoints == null){
			timePoints = new ArrayList<String>();
		}
		return timePoints;
	}

	public void setTimePoints(List<String> timePoints) {
		this.timePoints = timePoints;
	}

	public List<String[]> getRandomizationSchList() {
		if(randomizationSchList == null){
			randomizationSchList = new ArrayList<String[]>();
		}
		return randomizationSchList;
	}

	public void setRandomizationSchList(List<String[]> randomizationSchList) {
		this.randomizationSchList = randomizationSchList;
	}

	public Workbook getRandomizationWorkbook() {
		return randomizationWorkbook;
	}

	public void setRandomizationWorkbook(Workbook randomizationWorkbook) {
		this.randomizationWorkbook = randomizationWorkbook;
	}


}
