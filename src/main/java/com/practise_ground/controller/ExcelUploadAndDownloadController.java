package com.practise_ground.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.practise_ground.service.IExcelUploadAndDownloadService;

import lombok.AllArgsConstructor;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
@RestController
@RequestMapping("/excel")
@AllArgsConstructor
public class ExcelUploadAndDownloadController {

	private final IExcelUploadAndDownloadService excelUploadAndDownloadService;

	@PostMapping(value = "/uploadQuizQuestionnaire", consumes = "multipart/form-data")
	public ResponseEntity<Boolean> uploadQuizQuestionnaire(@RequestParam("file") MultipartFile file) throws Exception {

		excelUploadAndDownloadService.uploadQuizQuestionnaire(file.getInputStream());
		return ResponseEntity.ok(true);

	}

	@GetMapping(value = "/downloadQuizQuestionnaireTemplate")
	public ResponseEntity<byte[]> downloadQuizQuestionnaireTemplate() throws Exception {

		byte[] excelBytes = excelUploadAndDownloadService.generateQuizQuestionnaireTemplate().toByteArray();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		headers.setContentDispositionFormData("attachment", "Quiz Questionnaire Template.xlsx");

		return ResponseEntity.ok().headers(headers).body(excelBytes);

	}

}
