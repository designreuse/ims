package com.ims.excel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ims.util.AppUtil;

public class ExcelOperation {

	public void readExcel(ExcelInputOutput excelInputOutput, byte[] bytes)
			throws IOException {

		InputStream file = new ByteArrayInputStream(bytes);
		int subNoColRef = 0;
		int subNoRowRef = 0;

		// Get the workbook instance for XLS file
		Workbook workbook = new XSSFWorkbook(file);

		// Get first sheet from the workbook
		Sheet sheet = workbook.getSheetAt(0);
		boolean isTableHeadingRowReached = false;
		/*
		 * valuesPeriodI = new HashMap<String, ArrayList<Double>>();
		 * valuesPeriodII = new HashMap<String, ArrayList<Double>>(); keys = new
		 * ArrayList<String>();
		 */
		for (Row row : sheet) {
			if (row.getRowNum() == (subNoRowRef + 1)) {
				continue;
			}

			for (Cell cell : row) {
				// Find out the location coords of 'Subject No.' as reference
				if ((cell.getCellType() == Cell.CELL_TYPE_STRING)
						&& cell.getStringCellValue().equalsIgnoreCase(
								"Subject No.")) {
					subNoColRef = cell.getColumnIndex();
					subNoRowRef = cell.getRowIndex();
					// Row with subject no. as column name is considered as the
					// heading row
					isTableHeadingRowReached = true;
					// break;
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING
						&& cell.getRowIndex() == subNoRowRef) {
					// if we are in the heading row then save the cells as keys
					// in the map along with the empty arraylist
					excelInputOutput.getValuesPeriodI().put(
							cell.getStringCellValue(), new ArrayList<Double>());
					excelInputOutput.getValuesPeriodII().put(
							cell.getStringCellValue(), new ArrayList<Double>());

					excelInputOutput.getKeys().add(cell.getStringCellValue());

				}
				// After table heading if the column index is same as that of
				// subject no or that +1
				if ((cell.getRowIndex() != subNoRowRef)
						&& isTableHeadingRowReached
						&& ((cell.getColumnIndex() > subNoColRef))) {
					// Incase of string
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

						if ((cell.getColumnIndex()) % 2 == 0) {
							excelInputOutput
									.getValuesPeriodI()
									.get(excelInputOutput.getKeys().get(
											((cell.getColumnIndex()) / 2) - 1))
									.add(0.0d);
						} else {

							excelInputOutput
									.getValuesPeriodII()
									.get(excelInputOutput.getKeys().get(
											((cell.getColumnIndex()) / 2) - 1))
									.add(0.0d);
						}

					} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

						//
						if ((cell.getColumnIndex()) % 2 == 0) {
							excelInputOutput
									.getValuesPeriodI()
									.get(excelInputOutput.getKeys().get(
											((cell.getColumnIndex()) / 2) - 1))
									.add(cell.getNumericCellValue());
						} else {
							excelInputOutput
									.getValuesPeriodII()
									.get(excelInputOutput.getKeys().get(
											((cell.getColumnIndex()) / 2) - 1))
									.add(cell.getNumericCellValue());
						}

					}

				} else if ((cell.getColumnIndex() == subNoColRef)
						&& (cell.getRowIndex() > (subNoRowRef + 1))
						&& (cell.getCellType() == Cell.CELL_TYPE_STRING)) {

					excelInputOutput.getTimePoints().add(
							extractString(cell.getStringCellValue()));
				}

			}
		}

	}

	public void readRandomization(ExcelInputOutput excelInputOutput,
			byte[] bytes) throws IOException {

		InputStream file = new ByteArrayInputStream(bytes);
		int subNoColRef = 0;
		int subNoRowRef = 0;
		boolean isHeadingReached = false;

		// Get the workbook instance for XLS file
		Workbook workbook = new XSSFWorkbook(file);

		// Get first sheet from the workbook
		Sheet sheet = workbook.getSheetAt(0);

		for (Row row : sheet) {
			String[] details = new String[3];

			int i = 0;
			for (Cell cell : row) {
				if (!isHeadingReached
						&& (cell.getCellType() == Cell.CELL_TYPE_STRING)
						&& cell.getStringCellValue()
								.equalsIgnoreCase("SUBJECT")) {
					isHeadingReached = true;
					subNoRowRef = cell.getRowIndex();

					break;
				}

				if (isHeadingReached
						&& ((cell.getCellType() == Cell.CELL_TYPE_STRING) || (cell
								.getCellType() == Cell.CELL_TYPE_NUMERIC))) {

					if ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
							&& cell.getColumnIndex() == 0) {

						details[i] = Double
								.toString(cell.getNumericCellValue());
					}
					if ((cell.getCellType() == Cell.CELL_TYPE_STRING)
							&& cell.getColumnIndex() == 1) {

						details[i] = cell.getStringCellValue();
					}
					if ((cell.getCellType() == Cell.CELL_TYPE_STRING)
							&& cell.getColumnIndex() == 2) {
						details[i] = cell.getStringCellValue();
					}

				}
				i++;

			}
			if (isHeadingReached) {

				excelInputOutput.getRandomizationSchList().add(details);
			}

		}
		if (excelInputOutput.getRandomizationSchList().size() > 0) {
			excelInputOutput.getRandomizationSchList().remove(0);
		}

	}

	public void writeExcel(ExcelInputOutput excelInputOutput, OutputStream out)
			throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Cell cell = null;
		Sheet spreadsheet = workbook.createSheet("concentration");
		Row row = spreadsheet.createRow((short) 2);
		row.createCell(0).setCellValue("Subject No");
		row.createCell(1).setCellValue("Sequence");
		row.createCell(2).setCellValue("Period");
		row.createCell(3).setCellValue("Formulation");
		row.createCell(4).setCellValue("Scheduled Time");
		row.createCell(5).setCellValue("ACTUAL TIME");
		row.createCell(6).setCellValue("CONCENTRATION");
		int k = 2;

		for (int i = 0; i < excelInputOutput.getKeys().size(); i++) {
			String subjHeading = excelInputOutput.getKeys().get(i);
			if (subjHeading != null && subjHeading.contains("_DROP")) {
				continue;
			}

			List valuesI = excelInputOutput.getValuesPeriodI().get(subjHeading);
			List valuesII = excelInputOutput.getValuesPeriodII().get(
					subjHeading);

			for (int j = 0; j < valuesI.size(); j++) {
				Row row1 = spreadsheet.createRow((short) (k + 1));
				k++;

				row1.createCell(0).setCellValue(
						trimSubjectString((String) excelInputOutput.getKeys()
								.get(i)));
				if (excelInputOutput.getRandomizationSchList().size() > (i)) {
					if (excelInputOutput.getRandomizationSchList().get(i) != null) {

						String details[] = excelInputOutput
								.getRandomizationSchList().get(i);
						row1.createCell(1)
								.setCellValue(details[1] + details[2]);
						row1.createCell(3).setCellValue(details[1]);
					}
				}

				row1.createCell(2).setCellValue(1);
				row1.createCell(4).setCellValue(
						excelInputOutput.getTimePoints().get(j));
				row1.createCell(5).setCellValue(
						excelInputOutput.getTimePoints().get(j));
				Double d = (Double) valuesI.get(j);
				row1.createCell(6).setCellValue(d.doubleValue());
			}

			for (int j = 0; j < valuesII.size(); j++) {
				Row row1 = spreadsheet.createRow((short) (k + 1));
				k++;

				row1.createCell(0).setCellValue(
						trimSubjectString((String) excelInputOutput.getKeys()
								.get(i)));
				row1.createCell(2).setCellValue(2);
				row1.createCell(4).setCellValue(
						excelInputOutput.getTimePoints().get(j));
				row1.createCell(5).setCellValue(
						excelInputOutput.getTimePoints().get(j));
				Double d = (Double) valuesII.get(j);
				row1.createCell(6).setCellValue(d.doubleValue());

				if (excelInputOutput.getRandomizationSchList().size() > (i)) {
					if (excelInputOutput.getRandomizationSchList().get(i) != null) {

						String details[] = excelInputOutput
								.getRandomizationSchList().get(i);
						row1.createCell(1)
								.setCellValue(details[1] + details[2]);
						row1.createCell(3).setCellValue(details[2]);
					}
				}
			}
		}

		workbook.write(out);
		out.close();

	}

	public void getExcelOutput() {

	}

	private static int trimSubjectString(String input) {
		if (input != null && input.trim().length() > 0) {
			input = input.substring((input.length() - 2), input.length());
			Integer integer = new Integer(input);
			return integer.intValue();
		}
		return 0;
	}

	private static String extractString(String input) {
		if (input != null && input.trim().length() > 4) {
			return input.substring(0, 5);
		}
		return "";
	}

}
