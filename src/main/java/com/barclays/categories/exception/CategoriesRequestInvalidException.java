package com.barclays.categories.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesRequestInvalidException extends RuntimeException {

	private String respCode;
	private String respMsg;
	private HttpStatus status;
	
}
