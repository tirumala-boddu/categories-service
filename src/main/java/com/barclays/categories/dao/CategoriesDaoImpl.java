package com.barclays.categories.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.barclays.categories.model.CategoriesDao;
import com.barclays.categories.model.CategoriesDaoRequest;
import com.barclays.categories.model.CategoriesDaoResponse;

@Component
public class CategoriesDaoImpl implements ICategoriesDao{

	@Override
	public CategoriesDaoResponse getCategories(CategoriesDaoRequest categoriesDaoReq) {
		
		//write dao code to communicate with DB and get the list of categories
		CategoriesDaoResponse categoriesDaoResponse = new CategoriesDaoResponse();
		 List<CategoriesDao> categories = new ArrayList<>();

		 CategoriesDao cat1 = new CategoriesDao();
	        cat1.setId("101");
	        cat1.setName("Electronics");
	        cat1.setType("cat001");
	        cat1.setStatus("active");
	        cat1.setExpDate("31/12/2025");
	        cat1.setDesc("Devices and gadgets");

	        CategoriesDao cat2 = new CategoriesDao();
	        cat2.setId("102");
	        cat2.setName("Books");
	        cat2.setType("cat002");
	        cat2.setStatus("active");
	        cat2.setExpDate("01/01/2026");
	        cat2.setDesc("Educational and fiction");

	        CategoriesDao cat3 = new CategoriesDao();
	        cat3.setId("103");
	        cat3.setName("Clothing");
	        cat3.setType("cat003");
	        cat3.setStatus("inactive");
	        cat3.setExpDate("31/12/2024");
	        cat3.setDesc("Men and Women Apparel");

	        CategoriesDao cat4 = new CategoriesDao();
	        cat4.setId("104");
	        cat4.setName("Home Appliances");
	        cat4.setType("cat004");
	        cat4.setStatus("active");
	        cat4.setExpDate("30/06/2026");
	        cat4.setDesc("Kitchen and household devices");

	        CategoriesDao cat5 = new CategoriesDao();
	        cat5.setId("105");
	        cat5.setName("Sports");
	        cat5.setType("cat005");
	        cat5.setStatus("active");
	        cat5.setExpDate("31/12/2025");
	        cat5.setDesc("Outdoor and indoor sports gear");

	        categories.add(cat1);
	        categories.add(cat2);
	        categories.add(cat3);
	        categories.add(cat4);
	        categories.add(cat5);

	        categoriesDaoResponse.setCategoriesDao(categories);
		
		return categoriesDaoResponse;
	}

}
