package dao;

import DB.DbUtil;

/**
 * 操作与账户类相关的Dao层
 * @author
 * @version
 *
 */
public class AccountDao {

	/**
	 * 取款执行扣款
	 * @author
	 * @version
	 * @param amount 扣款金额
	 * @param card_no 
	 * @return 
	 */
	public static boolean deductMoney(double amount, int card_no) {
		
		String sql="update account_info set balance =balance -"+amount+" where cardNo ="+ card_no;
		
		return DbUtil.executeUpdate(sql);
	}

	
	/**
	 * 执行存款
	 * @author 孙少喜
	 * @version 29/11/2018
	 * @param amount 存款金额
	 * @param card_no 
	 * @return 
	 */
	public static boolean addMoney(double amount, int card_no) {
		
		String sql="update account_info set balance =balance +"+amount+" where cardNo ="+ card_no;
		
		return DbUtil.executeUpdate(sql);
	}
	/**
	 * 根据账号查询当前余额  废弃
	 * @author 叶城廷
	 * @version 2018.11.28
	 * @param cardNo账号
	 * @return
	
	public static Double selectBalance(int cardNo) {
		  Connection con = null;
		  PreparedStatement stmt = null;
		  ResultSet rs = null;
		  
		  Double Balance=0.00;
	    
	      try
		    {  
		      DbUtil db = new DbUtil();
		      
		      con = db.getCon();
		      
		      stmt = con.prepareStatement("select balance from account_info where cardNo="+cardNo);
		      
		      rs = stmt.executeQuery();
		      
		      while(rs.next()) {
		    	 Balance=rs.getDouble(1);
		      }
		    }catch (Exception e) {
				e.printStackTrace();
			}finally {
				 DbUtil.closeConn(con);
			     DbUtil.closeStmt(stmt);
			     DbUtil.closeResultSet(rs);
			
		    }
			
		      
		  return Balance;
		 
	} */


	public static boolean checkCard_no(int card_no) {
		// TODO Auto-generated method stub
       String sql="select * from account_info where cardNo="+card_no;
		
		return DbUtil.executeBool(sql);
	}
	
	public static double checkMenny(int card_no) {
		// TODO Auto-generated method stub
       String sql="select balance from account_info where cardNo="+card_no;
		
		return DbUtil.executeSelete(sql);
	}


	public boolean selectManager(String userName, String passWord) {
		  String sql="select * from manager_info where userName='"+userName+"' and passWord='" +passWord+"'";
		  return DbUtil.executeBool(sql);
		
	}
}
