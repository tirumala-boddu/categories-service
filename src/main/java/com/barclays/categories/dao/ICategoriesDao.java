package com.barclays.categories.dao;

import com.barclays.categories.model.CategoriesDaoRequest;
import com.barclays.categories.model.CategoriesDaoResponse;

public interface ICategoriesDao {

	CategoriesDaoResponse getCategories(CategoriesDaoRequest categoriesDaoReq);

}
