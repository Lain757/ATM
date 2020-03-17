package atm;

import banking.Account;

public class Transfer extends Transaction {
	public static final int TRANS_UNSTART = 1; // 未开始
	public static final int TRANS_GETDATA = 2; // 获取交易请求
	public static final int TRANS_SUCCESS = 3; // 交易成功
	public static final int TRANS_FAILURE = 4; // 交易失败
	public static final int TRANS_EXIT = 4; // 退出
	
	Account otherAccount = null;
	
	
	public Account getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(Account otherAccount) {
		this.otherAccount = otherAccount;
	}

	/**
	 * @author
	 * @version
	 * @param session
	 * @param acct
	 */
	
	public Transfer(Session session, Account acct) {
		super(session, acct);
		// 当选择转账交易时,需要改变显示屏的显示,需要改变数字键盘的状态
		ATM machine = ATM.getInstance();
		machine.getDisplay().setText("请输入转账账户（卡号）：");
		machine.getDigitButton().stateChange(1, 0, "TransferAccountServlet");
	}
	
	/**
	 * @author
	 * @version
	 *判断是否存在该账户，用户余额是否大于等于转账金额
	 */
	
	public void JudgePerform() {
		ATM machine = ATM.getInstance();
		//判断用户是否按0返回上一级菜单
		if(this.getAmount()==0){
			machine.getDisplay().setText("请选择您需要的业务："+"<br>"+"1:取款 2:存款 3:转账 4:查询 0:退出 ");
			machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
		}
		
		
		
	}
}
