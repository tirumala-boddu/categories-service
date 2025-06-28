package com.barclays.categories.service;

import com.barclays.categories.model.CategoriesRequest;
import com.barclays.categories.model.CategoriesResponse;

public interface ICategoriesService {

	CategoriesResponse getCategories(CategoriesRequest categoriesReq);
}
