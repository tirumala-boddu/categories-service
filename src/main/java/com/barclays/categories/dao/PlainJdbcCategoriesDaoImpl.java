package com.barclays.categories.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.barclays.categories.model.CategoriesDao;
import com.barclays.categories.model.CategoriesDaoRequest;
import com.barclays.categories.model.CategoriesDaoResponse;

@Component
public class PlainJdbcCategoriesDaoImpl implements ICategoriesDao{

	@Override
	public CategoriesDaoResponse getCategories(CategoriesDaoRequest categoriesDaoReq) {
		
		//write dao code to communicate with DB and get the list of categories
		System.out.println("inside the getCategories()");
		CategoriesDaoResponse categoriesDaoResponse = new CategoriesDaoResponse();
		String jdbcUrl = "jdbc:mysql://localhost:3306/scart?useSSL=false"; 
		String username = "root";
		String password = "123456789";

		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println("inside the getCategories() -- try");
			// 1. Establish connection
			conn=  DriverManager.getConnection(jdbcUrl, username, password);
			
			// 2. Prepare call to stored procedure (statement)
            String call = "{CALL scart.GET_CATEGORIES_V1(?, ?, ?, ?, ?)}";
            cstmt = conn.prepareCall(call);
			
            
            // 3. Set input parameters
            cstmt.setString(1, categoriesDaoReq.getClientId());
            cstmt.setString(2, categoriesDaoReq.getChannelId());
            cstmt.setString(3, categoriesDaoReq.getCardNum());
            
            // 4. Register output parameters
            cstmt.registerOutParameter(4, Types.VARCHAR); // RESPCODE_OUT
            cstmt.registerOutParameter(5, Types.VARCHAR); // RESPMSG_OUT
            
            
            // 5. Execute
            boolean hasResultSet = cstmt.execute();
            rs = cstmt.getResultSet();
            
         
            //get the result set 
            List<CategoriesDao> categoriesList = new ArrayList<>();
            if (hasResultSet) {
                rs = cstmt.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    CategoriesDao categoriesDao = new CategoriesDao();
                    categoriesDao.setId(rs.getString("catid"));
                    categoriesDao.setName(rs.getString("name"));
                    categoriesDao.setType(rs.getString("cattype"));
        	        categoriesDao.setStatus(rs.getString("status"));
        	        categoriesDao.setExpDate(rs.getString("expdate"));
        	        categoriesDao.setDesc(rs.getString("desc"));
        	        categoriesDao.setCardNum( rs.getString("cardnum"));
        	        categoriesList.add(categoriesDao);
                }
            }
            
            // 7. Get output parameters
            String respCode = cstmt.getString(4);
            String respMsg = cstmt.getString(5);
            
            categoriesDaoResponse.setDbrespCode(respCode);
            categoriesDaoResponse.setDbrespMsg(respMsg);
            categoriesDaoResponse.setCategoriesDao(categoriesList);
            
            return categoriesDaoResponse;
		}catch(Exception e) {
			System.out.println("errorr :" +e);
		}
		return categoriesDaoResponse;
	}

}
