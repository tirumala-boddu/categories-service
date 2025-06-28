package com.barclays.categories.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemException extends RuntimeException {

	private String respCode;
	private String respMsg;
	HttpStatus status;

}
