package com.barclays.categories.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardServiceClientRes {

	private String respCode;
	private String respMsg;
	private String status;

}
