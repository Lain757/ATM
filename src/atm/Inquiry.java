package atm;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DB.DbUtil;
import banking.Account;

public class Inquiry extends Transaction {
	
	
	public static final int TRANS_UNSTART = 1; // 未开始
	public static final int TRANS_GETDATA = 2; // 获取交易请求
	public static final int TRANS_SUCCESS = 3; // 交易成功
	public static final int TRANS_FAILURE = 4; // 交易失败
	public static final int TRANS_EXIT = 4; // 退出
	
	
	/**
	 * @author
	 * @version
	 * @param session
	 * @param acct
	 * @return 
	 * @return 
	 */
	public Inquiry(Session session, Account acct) {
		super(session,acct);
		// 当选择存款交易时,需要改变显示屏的显示,需要改变数字键盘的状态
		ATM machine = ATM.getInstance();
		Account acc=getAccount();
		machine.getDisplay().setText("您的余额为："+acc.getBalance()+"<br>"+"请按任意键退出 ");
		machine.getDigitButton().stateChange(0, 0, "InquiryServlet");
	}

}
