package com.practise_ground.service;

import java.io.InputStream;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.practise_ground.dao.IGradeDAO;
import com.practise_ground.dao.IPractiseGroundWeekDAO;
import com.practise_ground.dao.IPractiseGroundYearDAO;
import com.practise_ground.dao.IQuestionnaireDAO;
import com.practise_ground.dao.IQuizDAO;
import com.practise_ground.dao.ISubjectDAO;
import com.practise_ground.entity.GradeEntity;
import com.practise_ground.entity.PractiseGroundWeekEntity;
import com.practise_ground.entity.PractiseGroundYearEntity;
import com.practise_ground.entity.QuestionnaireEntity;
import com.practise_ground.entity.QuizEntity;
import com.practise_ground.entity.SubjectEntity;
import com.practise_ground.enums.Status;
import com.practise_ground.exceptions.PractiseGroundException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */

enum QUESTIONNAIRE_HEADERS {
	_0("Academic Year"), _1("Academic Year Week"), _2("Grade"), _3("Subject"), _4("Question"), _5("Option A"),
	_6("Option B"), _7("Option C"), _8("Option D"), _9("Answer");

	public final String label;

	private QUESTIONNAIRE_HEADERS(String label) {
		this.label = label;
	}
}

@Service
@RequiredArgsConstructor
public class ExcelUploadAndDownloadServiceImpl implements IExcelUploadAndDownloadService {

	private final IQuestionnaireDAO questionnaireDAO;

	private final IQuizDAO quizDAO;

	private final ISubjectDAO subjectDAO;

	private final IGradeDAO gradeDAO;

	private final IPractiseGroundWeekDAO practiseGroundWeekDAO;

	private final IPractiseGroundYearDAO practiseGroundYearDAO;

	@Override
	@Transactional
	public void uploadQuizQuestionnaire(InputStream inputStream) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet sheet = workbook.getSheetAt(0);

		QuizEntity quizEntity = null;

		// Skipping First Row with index 0 as it contains Headers
		Row row = sheet.getRow(1);
		if (ObjectUtils.isEmpty(row.getCell(0))) {
			workbook.close();
			throw new PractiseGroundException("Invalid Data. Please verify !!", HttpStatus.BAD_REQUEST);
		}

		String year = row.getCell(0).getStringCellValue();
		String week = row.getCell(1).getStringCellValue();
		String grade = row.getCell(2).getStringCellValue();
		String subject = row.getCell(3).getStringCellValue();

		PractiseGroundYearEntity yearEntity = practiseGroundYearDAO.findByName(year);
		PractiseGroundWeekEntity weekEntity = practiseGroundWeekDAO.findByName(week);
		SubjectEntity subjectEntity = subjectDAO.findByName(subject);
		GradeEntity gradeEntity = gradeDAO.findByName(grade);

		if (ObjectUtils.isEmpty(yearEntity) || ObjectUtils.isEmpty(weekEntity) || ObjectUtils.isEmpty(subjectEntity)
				|| ObjectUtils.isEmpty(gradeEntity)) {
			workbook.close();
			throw new PractiseGroundException("Invalid Data. Please verify !!", HttpStatus.BAD_REQUEST);
		} else {
			quizEntity = quizDAO.findByGradeIdAndSubjectIdAndWeekIdAndYearId(gradeEntity.getId(), subjectEntity.getId(),
					weekEntity.getId(), yearEntity.getId());

			if (ObjectUtils.isEmpty(quizEntity)) {
				StringBuilder sb = new StringBuilder();
				sb.append(yearEntity.getName()).append("-").append(weekEntity.getName()).append("-")
						.append(gradeEntity.getName()).append("-").append(subjectEntity.getName());
				quizEntity = quizDAO.save(QuizEntity.builder().grade(gradeEntity).subject(subjectEntity)
						.week(weekEntity).year(yearEntity).name(sb.toString()).build());
			} else {
				workbook.close();
				throw new PractiseGroundException(
						"There is already a Quiz with the provided combination of Year, Week, Subject and Grade !! Please verify !!",
						HttpStatus.BAD_REQUEST);
			}

			questionnaireDAO.save(QuestionnaireEntity.builder().quiz(quizEntity)
					.question(row.getCell(4).getStringCellValue()).optionA(row.getCell(5).getStringCellValue())
					.optionB(row.getCell(6).getStringCellValue()).optionC(row.getCell(7).getStringCellValue())
					.optionD(row.getCell(8).getStringCellValue()).answer(row.getCell(9).getStringCellValue()).build());
		}

		final QuizEntity savedQuizEntity = quizEntity;

		IntStream.range(2, 26).forEach(i -> {

			Row r = sheet.getRow(i);

			questionnaireDAO.save(QuestionnaireEntity.builder().quiz(savedQuizEntity)
					.question(r.getCell(4).getStringCellValue()).optionA(r.getCell(5).getStringCellValue())
					.optionB(r.getCell(6).getStringCellValue()).optionC(r.getCell(7).getStringCellValue())
					.optionD(r.getCell(8).getStringCellValue()).answer(r.getCell(9).getStringCellValue()).build());
		});

		workbook.close();

	}

	@Override
	public ByteArrayOutputStream generateQuizQuestionnaireTemplate() throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Quiz Questionnaire");

		XSSFFont font = workbook.createFont();
		font.setBold(true);

		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setLocked(true);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
		cellStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index);

		Row row = sheet.createRow(0);

		for (int i = 0; i < 10; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(QUESTIONNAIRE_HEADERS.valueOf("_" + i).label);
			cell.setCellStyle(cellStyle);
		}

		String[] grades = gradeDAO.findByStatus(Status.ACTIVE).parallelStream().map(item -> item.getName())
				.toArray(String[]::new);
		String[] subjects = subjectDAO.findByStatus(Status.ACTIVE).parallelStream().map(item -> item.getName())
				.toArray(String[]::new);
		String[] weeks = practiseGroundWeekDAO.findByStatus(Status.ACTIVE).parallelStream().map(item -> item.getName())
				.toArray(String[]::new);
		String[] years = practiseGroundYearDAO.findByStatus(Status.ACTIVE).parallelStream().map(item -> item.getName())
				.toArray(String[]::new);

		DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);

		// For Subjects Drop Down
		CellRangeAddressList subjectAddressList = new CellRangeAddressList(1, 25, 3, 3);
		DataValidationConstraint subjectsConstraint = validationHelper.createExplicitListConstraint(subjects);
		DataValidation subjectsValidation = validationHelper.createValidation(subjectsConstraint, subjectAddressList);
		subjectsValidation.setSuppressDropDownArrow(true);

		// For Weeks Drop Down
		CellRangeAddressList weeksAddressList = new CellRangeAddressList(1, 25, 1, 1);
		DataValidationConstraint weeksConstraint = validationHelper.createExplicitListConstraint(weeks);
		DataValidation weeksValidation = validationHelper.createValidation(weeksConstraint, weeksAddressList);
		weeksValidation.setSuppressDropDownArrow(true);

		// For Years Drop Down
		CellRangeAddressList yearsAddressList = new CellRangeAddressList(1, 25, 0, 0);
		DataValidationConstraint yearsConstraint = validationHelper.createExplicitListConstraint(years);
		DataValidation yearsValidation = validationHelper.createValidation(yearsConstraint, yearsAddressList);
		yearsValidation.setSuppressDropDownArrow(true);

		// For Grades Drop Down
		CellRangeAddressList gradesAddressList = new CellRangeAddressList(1, 25, 2, 2);
		DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(grades);
		DataValidation gradesValidation = validationHelper.createValidation(constraint, gradesAddressList);
		gradesValidation.setSuppressDropDownArrow(true);

		sheet.addValidationData(subjectsValidation);
		sheet.addValidationData(weeksValidation);
		sheet.addValidationData(yearsValidation);
		sheet.addValidationData(gradesValidation);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return outputStream;
	}

}
