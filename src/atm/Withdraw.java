//******************************************************************************
//
// ATM系统 -  Withdraw.java 
// 参考了 http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html
// 
//******************************************************************************

package atm;
/*
import javax.servlet.http.HttpServletRequest;


import org.apache.http.HttpRequest;
import org.junit.runner.Request;
import org.junit.runner.Runner;*/

import javax.servlet.http.HttpServletRequest;

import banking.Account;
import banking.Voucher;
import dao.PrintBillDao;
import dao.PrintMessage;

public class Withdraw extends Transaction {
	
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
	public Withdraw(Session session, Account acct) {
		super(session,acct);
		// 当选择取款交易时,需要改变显示屏的显示,需要改变数字键盘的状态
		ATM machine = ATM.getInstance();
		machine.getDisplay().setText("请输入取款金额:");
		machine.getDigitButton().stateChange(1, 0, "WithdrawInfoServlet");
	}
	
	/**
	 * @author
	 * @version
	 * 从用户账户扣取金额
	 */
	public void execute( HttpServletRequest req) {
		ATM machine = ATM.getInstance();
		//判断用户是否按0返回上一级菜单
		if(this.getAmount()==0){		
		    machine.getSession().setState(3);
			machine.getDisplay().setText("请选择您需要的业务："+"<br>"+"1:取款 2:存款 3:转账 4:查询  0:退出");
			machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
		}
		//判断取款金额是否为整数
		else if(this.getAmount()%100==0) {
			//取款金额为整数
			
			//执行扣款
			int ret = this.getAccount().withdraw(this.getAmount());
			
			// 判断扣款是否成功
			if(ret == 0) {
				//扣款成功
				Account acc=this.getAccount();
				int money=(int) this.getAmount();
				//向数据库添加流水账单--调用廖梦青添加流水功能（完成后去掉注释）
				PrintMessage printMessage=new PrintMessage("WO银行凭条信息","凭条编号:","ATM机编号:","操作金额:","操作时间:","银行卡号:","操作编号:");
				req.getSession().setAttribute("printMessage", printMessage);
				Voucher voucher=new PrintBillDao().saveBill(acc.getId(),money,acc.getCard_no(),2);
				//相关打印信息存入session
				req.getSession().setAttribute("voucher", voucher);
                  
			
				// 显示屏更新 数字键盘状态更新 
				this.setState(TRANS_SUCCESS);
			
				//向ATM荧屏显示文字
				machine.getDisplay().setText("取款成功。您的余额是"+this.getAccount().getBalance()+"<br>"+"打印:1 不打印:0");
				//充值ATM状态和下次即将调整的servlet名字
				machine.getDigitButton().stateChange(0, 0, "WithdrawPrintServlet");
				
			}
			// 扣取不成功
			else {
				machine.getDisplay().setText("您的余额不足，请重新输入取款金额:");
				machine.getDigitButton().stateChange(1, 0, "WithdrawInfoServlet");
			}
		}else {
			//取款金额不为整数
			machine.getDisplay().setText("取款金额不为整数，请重新输入取款金额:");
			machine.getDigitButton().stateChange(1, 0, "WithdrawInfoServlet");
			
		}
	
	}
	
	/**
	 * 处理打印   
	 * @Tip
	 * @param flag 0:打印 1:不打印
	 */
	public void print(int flag) {
		// 显示屏更新 数字键盘状态更新 
		//退出交易状态
		this.setState(TRANS_EXIT);
		//进入选择交易状态
		this.getSession().setState(Session.CHOOSING);
		ATM machine = ATM.getInstance();
		machine.getDisplay().setText("请选择您需要的业务 "+"<br>"+"1:取款 2:存款 3:转账 4:查询  0:退出 ");
		machine.getDigitButton().stateChange(0, 0, "TransactionServlet");
	}
	
}
