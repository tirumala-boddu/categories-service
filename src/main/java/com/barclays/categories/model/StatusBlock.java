package com.barclays.categories.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusBlock {

	private String respCode;
	private String respMsg;

}
