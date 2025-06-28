package com.barclays.categories.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories {

	private String id;
	private String name;
	private String status;
	private String type;
	private String expDate;
	private String desc;
	
}
