package com.barclays.categories.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.barclays.categories.dao.ICategoriesDao;
import com.barclays.categories.model.CardServiceClientReq;
import com.barclays.categories.model.CardServiceClientRes;
import com.barclays.categories.model.Categories;
import com.barclays.categories.model.CategoriesDao;
import com.barclays.categories.model.CategoriesDaoRequest;
import com.barclays.categories.model.CategoriesDaoResponse;
import com.barclays.categories.model.CategoriesRequest;
import com.barclays.categories.model.CategoriesResponse;
import com.barclays.categories.model.StatusBlock;
import com.barclays.categories.serviceclient.ICardVerifyServiceClient;

@Service
public class CategoriesServiceImpl implements ICategoriesService {

	
	@Autowired
	ICardVerifyServiceClient cardVerifyServiceClient;
	
	@Autowired
	@Qualifier("jdbcTemplateCategoriesDaoImpl")
	ICategoriesDao categoriesDao;
	
	@Override
	public CategoriesResponse getCategories(CategoriesRequest categoriesReq) {
		
		CategoriesResponse categoriesResponse= new CategoriesResponse();
		// 1.get the request from controller

		// 2. prepare the request for intg layer -1 cardVerifyService

		CardServiceClientReq request = new CardServiceClientReq();

		// 3. call cardVerifyService

		CardServiceClientRes cardVerifyResp = cardVerifyServiceClient.cardVerify(request);

		// 4. apply the business logic on cardVerifyResp

		// 5. Prepare the request for intg layer-2. categories dao

		CategoriesDaoRequest categoriesDaoReq = new CategoriesDaoRequest();
		categoriesDaoReq.setCardNum(categoriesReq.getCardNum());
		categoriesDaoReq.setChannelId(categoriesReq.getChannelId());
		categoriesDaoReq.setClientId(categoriesReq.getClientId());

		// 6. call dao and get the response

		CategoriesDaoResponse categoriesdaoResp = categoriesDao.getCategories(categoriesDaoReq);
		
		//7. prepare the CategoriesResponse response - with the help of service client and dao
		List<CategoriesDao> categoriesDaoList = categoriesdaoResp.getCategoriesDao();
		List<Categories> categoriesList =new ArrayList<Categories>();
		for(CategoriesDao dao : categoriesDaoList) {
			Categories categories = new Categories();
			categories.setId(dao.getId());
			categories.setName(dao.getName());
			categories.setStatus(dao.getStatus());
			categories.setType(dao.getType());
			categories.setDesc(dao.getDesc());
			categories.setExpDate(dao.getExpDate());
			categoriesList.add(categories);
		}
		
		StatusBlock sb = new StatusBlock();
		sb.setRespCode(categoriesdaoResp.getDbrespCode());
		sb.setRespMsg(categoriesdaoResp.getDbrespMsg());
		categoriesResponse.setStatus(sb);
		categoriesResponse.setCategories(categoriesList);
		
		return categoriesResponse;
	}

}
