package com.barclays.categories.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	ICategoriesDao categoriesDao;
	
	@Override
	public CategoriesResponse getCategories(CategoriesRequest categoriesReq) {
		
		// 1.get the request from controller

		// 2. prepare the request for intg layer -1 cardVerifyService

		CardServiceClientReq request = new CardServiceClientReq();

		// 3. call cardVerifyService

		CardServiceClientRes cardVerifyResp = cardVerifyServiceClient.cardVerify(request);

		// 4. apply the business logic on cardVerifyResp

		// 5. Prepare the request for intg layer-2. categories dao

		CategoriesDaoRequest categoriesDaoReq = new CategoriesDaoRequest();

		// 6. call dao and get the response

		CategoriesDaoResponse categoriesdaoResp = categoriesDao.getCategories(categoriesDaoReq);
		
		//7. prepare the CategoriesResponse response - with the help of service client and dao
		 
		List<CategoriesDao> categoriesDao2 = categoriesdaoResp.getCategoriesDao();
		List<Categories> categoriesList = new ArrayList<Categories>();
		CategoriesResponse categoriesResponse = new CategoriesResponse();
		
		for(CategoriesDao catDao : categoriesDao2 ) {
		Categories categories = new Categories();
		categories.setId(catDao.getId());
		categories.setName(catDao.getName());
		categories.setStatus(catDao.getStatus());
		categories.setType(catDao.getType());
		categories.setExpDate(catDao.getExpDate());
		categories.setDesc(catDao.getDesc());
		
		categoriesList.add(categories);
		}

		categoriesResponse.setCategories(categoriesList);
		StatusBlock sb = new StatusBlock();
		sb.setRespCode("000");
		sb.setRespMsg("success");
		categoriesResponse.setStatus(sb);
		
		return categoriesResponse;
	}

}
