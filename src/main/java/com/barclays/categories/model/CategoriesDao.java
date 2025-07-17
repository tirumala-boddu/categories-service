package com.barclays.categories.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesDao {

	private String id;
	private String name;
	private String type;
	private String status;
	private String expDate;
	private String desc;
	private String cardNum;
}
