package com.barclays.categories.model;

import com.barclays.categories.exception.CategoriesRequestInvalidException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardServiceClientReq {

	private String cardNum;
	private String clientId;
	private String channelId;
	private String reqId;
	private String msgTs;
	
}
