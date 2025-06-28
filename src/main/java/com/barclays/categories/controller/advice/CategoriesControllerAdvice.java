package com.barclays.categories.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.barclays.categories.exception.BusinessException;
import com.barclays.categories.exception.CategoriesRequestInvalidException;
import com.barclays.categories.exception.SystemException;
import com.barclays.categories.model.CategoriesResponse;
import com.barclays.categories.model.StatusBlock;

@ControllerAdvice
public class CategoriesControllerAdvice {

	@ExceptionHandler(CategoriesRequestInvalidException.class)
	public ResponseEntity<CategoriesResponse> handleCategoriesRequestException(CategoriesRequestInvalidException exception) {
		String respCode = exception.getRespCode();
		String respMsg = exception.getRespMsg();
		CategoriesResponse categoriesResponse = new CategoriesResponse();
		StatusBlock status = new StatusBlock();
		status.setRespCode(respCode);
		status.setRespMsg(respMsg);
		categoriesResponse.setStatus(status);
		return ResponseEntity.status(exception.getStatus()).body(categoriesResponse);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<CategoriesResponse> handleBusinessException(BusinessException exception) {
		String respCode = exception.getRespCode();
		String respMsg = exception.getRespMsg();
		CategoriesResponse categoriesResponse = new CategoriesResponse();
		StatusBlock status = new StatusBlock();
		status.setRespCode(respCode);
		status.setRespMsg(respMsg);
		categoriesResponse.setStatus(status);
		return ResponseEntity.status(exception.getStatus()).body(categoriesResponse);
	}
	
	@ExceptionHandler(SystemException.class)
	public ResponseEntity<CategoriesResponse> handleSystemException(SystemException exception) {
		String respCode = exception.getRespCode();
		String respMsg = exception.getRespMsg();
		CategoriesResponse categoriesResponse = new CategoriesResponse();
		StatusBlock status = new StatusBlock();
		status.setRespCode(respCode);
		status.setRespMsg(respMsg);
		categoriesResponse.setStatus(status);
		return ResponseEntity.status(exception.getStatus()).body(categoriesResponse);
	}

//	@ExceptionHandler(NoResourceFoundException.class)
//	public ResponseEntity<CategoriesResponse> handleMissingPathVariable(NoResourceFoundException ex) {
//	    CategoriesResponse response = new CategoriesResponse();
//	    StatusBlock status = new StatusBlock();
//	    status.setRespCode("400");
//	    status.setRespMsg("Missing required path variable: " + ex.getVariableName());
//	    response.setStatus(status);
//	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//	}
	
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<CategoriesResponse> handleGenericException(Exception exception) {
//		CategoriesResponse categoriesResponse = new CategoriesResponse();
//		StatusBlock status = new StatusBlock();
//		status.setRespCode("888");
//		status.setRespMsg(exception.getMessage());
//		categoriesResponse.setStatus(status);
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(categoriesResponse);
//	}
}
