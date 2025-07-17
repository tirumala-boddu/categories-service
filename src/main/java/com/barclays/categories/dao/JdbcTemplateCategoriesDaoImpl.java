package com.barclays.categories.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.barclays.categories.model.CategoriesDao;
import com.barclays.categories.model.CategoriesDaoRequest;
import com.barclays.categories.model.CategoriesDaoResponse;
/*
 * 
 * 	In my project, we had a complex stored procedure named GET_CATEGORIES_V1 which returned multiple OUT parameters (RESPCODE_OUT, RESPMSG_OUT) and a ResultSet (list of categories).

	I implemented the integration using Spring’s StoredProcedure abstraction and extended the class to encapsulate the logic cleanly in a DAO. This allowed us to:

	-> Register input and output parameters clearly using SqlParameter and SqlOutParameter

	-> Handle the ResultSet mapping via the RowMapper interface

	-> Reuse and isolate the procedure logic in a single DAO class, promoting separation of concerns

	I chose this over JPA or basic JdbcTemplate because:

	-> JPA doesn’t support procedures returning both a ResultSet and OUT parameters in one call

	-> JdbcTemplate alone would require more manual mapping and boilerplate

	-> Extending StoredProcedure gave us better testability, cleaner code, and parameter safety

	This implementation made our backend more maintainable and scalable for future stored procedures.
 * */


@Component("jdbcTemplateCategoriesDaoImpl")
public class JdbcTemplateCategoriesDaoImpl extends  StoredProcedure  implements ICategoriesDao , RowMapper<CategoriesDao>    {

	@Autowired
	//This line is calling the constructor of the superclass, which is : org.springframework.jdbc.object.StoredProcedure
	public JdbcTemplateCategoriesDaoImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "GET_CATEGORIES_V1");
		registerSPParams();
	}

	private void registerSPParams() {
		
		// register input params
		declareParameter(new SqlParameter("CLIENT_ID_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CHANNELID_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CARDNUM_IN", Types.VARCHAR));

		// register output params
		declareParameter(new SqlOutParameter("RESPCODE_OUT", Types.VARCHAR));
		declareParameter(new SqlOutParameter("RESPMSG_OUT", Types.VARCHAR));

		// register ResultSet
		declareParameter(new SqlReturnResultSet("categoriesResult", this));
		compile();
	}
	
	@Override
	public CategoriesDaoResponse getCategories(CategoriesDaoRequest categoriesDaoReq) {
		System.out.println("JdbcTemplateCategoriesDaoImpl::..?");
		CategoriesDaoResponse categoriesDaoResponse = new CategoriesDaoResponse();
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("CLIENT_ID_IN", categoriesDaoReq.getClientId());
		requestMap.put("CHANNELID_IN", categoriesDaoReq.getChannelId());
		requestMap.put("CARDNUM_IN", categoriesDaoReq.getCardNum());

		// execute the Stored Procedure
		Map<String, Object> respMap = super.execute(requestMap);

		String dbRespCode = respMap.get("RESPCODE_OUT").toString();
		String dbRespMsg = respMap.get("RESPMSG_OUT").toString();
		List<CategoriesDao> categoriesDaoList = new ArrayList<CategoriesDao>();
		if ("0".equals(dbRespCode)) {
			// prepare the dao response
			// mapRow() will be called and prepare the list of categories with the help of
			// ResultSet
			categoriesDaoList = (List<CategoriesDao>) respMap.get("categoriesResult");
			categoriesDaoResponse.setDbrespCode(dbRespCode);
			categoriesDaoResponse.setDbrespMsg(dbRespMsg);
			categoriesDaoResponse.setCategoriesDao(categoriesDaoList);

		}
		return categoriesDaoResponse;
	}

	@Override
	public CategoriesDao mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoriesDao categoriesDao = new CategoriesDao();
		categoriesDao.setId(rs.getString("catid"));
		categoriesDao.setName(rs.getString("name"));
		categoriesDao.setType(rs.getString("cattype"));
		categoriesDao.setStatus(rs.getString("status"));
		categoriesDao.setExpDate(rs.getString("expdate"));
		categoriesDao.setDesc(rs.getString("desc"));
		return categoriesDao;
	}

}
