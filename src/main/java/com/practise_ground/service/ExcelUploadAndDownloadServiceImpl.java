package com.practise_ground.service;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.practise_ground.dao.IGradeDAO;
import com.practise_ground.dao.IQuestionnaireDAO;
import com.practise_ground.dao.IQuizDAO;
import com.practise_ground.dao.ISubjectDAO;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@Service
@RequiredArgsConstructor
public class ExcelUploadAndDownloadServiceImpl implements IExcelUploadAndDownloadService {

	private final IQuestionnaireDAO questionnaireDAO;

	private final IQuizDAO quizDAO;

	private final ISubjectDAO subjectDAO;

	private final IGradeDAO gradeDAO;

	@Override
	public void uploadQuizQuestionnaire(InputStream inputStream) throws Exception {
//		Workbook workbook = WorkbookFactory.create(inputStream);
//		Sheet sheet = workbook.getSheetAt(0);

//		sheet.forEach(row -> {
//			Employee employee = new Employee();
//
//			if (row.getRowNum() != 0) {
//				employee.setEmpName(row.getCell(0).getStringCellValue());
//				employee.setEmpSalary(row.getCell(1).getNumericCellValue());
//				employeeList.add(employee);
//			}
//		});
//
//		employeeRepository.saveAll(employeeList);
	}

	@Override
	public ByteArrayOutputStream generateQuizQuestionnaireTemplate() throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Quiz Questionnaire");

		// Create a cell style with the "Locked" property set to true
//		CellStyle lockedStyle = workbook.createCellStyle();
//		lockedStyle.setLocked(true);

		// Get the cell you want to make uneditable and apply the style
//		Row row = sheet.getRow(rowIndex);
//		Cell cell = row.getCell(columnIndex);
//		cell.setCellStyle(lockedStyle); 

		// merge cells
//		sheet.addMergedRegion(new CellRangeAddress(startRowIndx, endRowIndx, startColIndx,endColIndx))

		// Set header row

//		Font font = workbook.createFont();
//		font.setBold(true);
//
//		CellStyle fontStyle = workbook.createCellStyle();
//		fontStyle.setFont(font);
//
//		Row row = sheet.createRow(0);
//		row.setRowStyle(fontStyle);
//
//		row.createCell(0).setCellValue("Academic Year");
//		row.createCell(1).setCellValue("Academic Year Week");
//		row.createCell(2).setCellValue("Grade");
//		row.createCell(3).setCellValue("Subject");
//		row.createCell(4).setCellValue("Question");
//		row.createCell(5).setCellValue("Option A");
//		row.createCell(6).setCellValue("Option B");
//		row.createCell(7).setCellValue("Option C");
//		row.createCell(8).setCellValue("Option D");
//		row.createCell(9).setCellValue("Answer");
//
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//		workbook.write(outputStream);

		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
		CellRangeAddressList addressList = new CellRangeAddressList(0, 2, 1, 3);
		DataValidationConstraint constraint = validationHelper
				.createExplicitListConstraint(new String[] { "YES", "NO", "MAYBE" });
		DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
		dataValidation.setSuppressDropDownArrow(true);
		sheet.addValidationData(dataValidation);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);

		return outputStream;

//		return null;
	}

	private void addAcademicYearDropDown() {
	}

}
