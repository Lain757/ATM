package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DB.DbUtil;
import banking.Account;

public  class LoginDao {

	
	/**
	 * 根据卡号查询该账号所有信息
	 * @author
	 * @version
	 */
		public static Account selectCardInfo(int cardNo) {
			
			 Connection con = null;
			  PreparedStatement stmt = null;
			  ResultSet rs = null;
			  
		      Account acc=new Account();
		    
		      try
			    {  
			      DbUtil db = new DbUtil();
			      
			      con = db.getCon();
			      
			      stmt = con.prepareStatement("select * from account_info where cardNo="+cardNo);
			      
			      rs = stmt.executeQuery();
			      
			      while(rs.next()) {
			    	  acc.setId(rs.getInt(1));
			    	  acc.setCard_no(rs.getInt(2));
			    	  acc.setPwd(rs.getInt(3));
			    	  acc.setName(rs.getString(4));
			    	  acc.setBalance(rs.getDouble(5));
			    	  acc.setSurplus_input_num(rs.getInt(6));
			      }
			    }catch (Exception e) {
					e.printStackTrace();
				}finally {
					 DbUtil.closeConn(con);
				     DbUtil.closeStmt(stmt);
				     DbUtil.closeResultSet(rs);
			    }
			  return acc;
		}

    
		/**
		 * 查询银行卡正确性、存在性
		 * @param cardNo
		 * @return
		 */
	public static boolean checkCardNo(int cardNo) {
		
		  String sql="select * from account_info where cardNo="+cardNo;
		  return DbUtil.executeBool(sql);
	}

	
	/**
	 * 输入的密码错误时更新数据库中可输入密码的剩余次数
	 * @param cardNo
	 * @return
	 */
	public static boolean reduceInpuNum(int cardNo) {
		String sql="update account_info set surplusInputNum =surplusInputNum-1 where cardNo ="+ cardNo;
		
		return DbUtil.executeUpdate(sql);
	}


	public static boolean updateSurplusInput(int cardNo) {
        String sql="update account_info set surplusInputNum =5 where cardNo ="+cardNo;
		
		return DbUtil.executeUpdate(sql);
	}

}
