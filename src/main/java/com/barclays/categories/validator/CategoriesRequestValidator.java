package com.barclays.categories.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.barclays.categories.exception.CategoriesRequestInvalidException;
import com.barclays.categories.model.CategoriesRequest;

@Component
public class CategoriesRequestValidator {

	//validate the request, if req is invalid send the custom exception
	public void validateCategoriesRequest(CategoriesRequest categoriesRequest) {
		
		if(categoriesRequest == null) {
			throw new CategoriesRequestInvalidException("cat001", "Missing or invalid request object" ,HttpStatus.BAD_REQUEST);
		}
		if(categoriesRequest.getClientId() == null || categoriesRequest.getClientId().isBlank()) {
			throw new CategoriesRequestInvalidException("cat003", "Missing or clientid is in valid", HttpStatus.BAD_REQUEST);
		}
		if(categoriesRequest.getChannelId() == null || categoriesRequest.getChannelId().isBlank()) {
			throw new CategoriesRequestInvalidException("cat004", "Missing or channelid is in valid", HttpStatus.BAD_REQUEST);
		}
		
	}

}
