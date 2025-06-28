package com.barclays.categories.serviceclient;

import com.barclays.categories.model.CardServiceClientReq;
import com.barclays.categories.model.CardServiceClientRes;


public interface ICardVerifyServiceClient {

	CardServiceClientRes cardVerify(CardServiceClientReq request);

}
