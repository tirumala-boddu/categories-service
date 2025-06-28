package com.barclays.categories.model;

import java.util.List;

import com.barclays.categories.exception.CategoriesRequestInvalidException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDaoResponse {
	
	private String dbrespCode;
	private String dbrespMsg;
	private List<CategoriesDao> categoriesDao;
	
	
}
