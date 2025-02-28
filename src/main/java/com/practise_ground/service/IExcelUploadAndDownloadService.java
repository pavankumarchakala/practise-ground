package com.practise_ground.service;

import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

/**
 * @author Pavankumar - created date : Feb 26, 2025
 *
 */
public interface IExcelUploadAndDownloadService {

	void uploadQuizQuestionnaire(InputStream inputStream) throws Exception;

	ByteArrayOutputStream generateQuizQuestionnaireTemplate() throws Exception;

}
