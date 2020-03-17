//******************************************************************************
//
//ATM系统 -  Deposit.java 
//参考了 http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html
//
//******************************************************************************

package atm;

import javax.servlet.http.HttpServletRequest;

import banking.Account;
import banking.Voucher;
import dao.PrintBillDao;
import dao.PrintMessage;

public class Deposit extends Transaction {
	
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
	 */
	public Deposit(Session session, Account acct) {
		super(session,acct);
		// 当选择存款交易时,需要改变显示屏的显示,需要改变数字键盘的状态
		ATM machine = ATM.getInstance();
		machine.getDisplay().setText("请输入存款金额：");
		machine.getDigitButton().stateChange(1, 0, "DepositInfoServlet");
	}
	
	/**
	 * @author
	 * @version
	 *将金额存入用户账户
	 */
	public void execute(HttpServletRequest req) {
		ATM machine = ATM.getInstance();
		//判断用户是否按取消或0返回上一级菜单
		if(this.getAmount()==0){
			//进入交易选择状态
			machine.getSession().setState(3);
			machine.getDisplay().setText("请选择您需要的业务："+"<br>"+"1:取款 2:存款 3:转账 4:查询 0:退出 ");
			machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
		}
		//判断存款金额是否为整数
		else if(this.getAmount() % 100 == 0) {
			//该存款金额为100的整数，往下执行
			
			//执行存款
			int ret = this.getAccount().deposit(this.getAmount());
			
			// 判断存款是否成功
			if(ret == 0) {//存款成功,往下执行
						
				Account acc=this.getAccount();	
				
				//向数据库添加流水账单--调用廖梦青添加流水功能
				int money=(int) this.getAmount();
				PrintMessage printMessage=new PrintMessage("WO银行凭条信息","凭条编号:","ATM机编号:","操作金额:","操作时间:","银行卡号:","操作编号:");
				req.getSession().setAttribute("printMessage", printMessage);
				Voucher voucher=new PrintBillDao().saveBill(acc.getId(),money,acc.getCard_no(),1);
				
				//相关打印信息存入session
				req.getSession().setAttribute("voucher", voucher);
				
				// 显示屏更新 数字键盘状态更新 
				this.setState(TRANS_SUCCESS);
				machine.getDisplay().setText("存款成功。您的金额为"+this.getAccount().getBalance()+"<br>"+"打印:1 不打印:0");
				machine.getDigitButton().stateChange(0, 0, "DepositPrintServlet");
			}
		}else {
			//存款的金额不为整数
			machine.getDisplay().setText("您的存款金额应为100的倍数，请重新输入存款金额：");
			machine.getDigitButton().stateChange(1, 0, "DepositInfoServlet");
			
		}
	
	}
	
	/**
	 * 处理打印 --存款  
	 * @param flag 0:打印 1:不打印
	 */
	public void print(int flag) {
		// 显示屏更新 数字键盘状态更新 
		this.setState(TRANS_EXIT);
		this.getSession().setState(Session.CHOOSING);
		ATM machine = ATM.getInstance();
		machine.getDisplay().setText("请选择您需要的业务"+"<br>"+" 1:取款 2:存款 3:转账 4:查询  0:退出 ");
		machine.getDigitButton().stateChange(0, 0, "TransactionServlet");
	}
	
}
