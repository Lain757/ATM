//******************************************************************************
//
// ATM系统 -  Account.java 

// 参考了 http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html
// 
//******************************************************************************

package banking;

import javax.servlet.http.HttpServletRequest;

import atm.ATM;
import atm.Transfer;
import dao.AccountDao;
import dao.PrintBillDao;

/**
 * 账号类
 * @author
 *@version
 */
public class Account {
	int id;
	
	int card_no;//卡号
	
	int pwd;    //密码
	
	String name;//名字
	
	Double balance;//余额
	
	Integer surplus_input_num;//剩余可输入密码次数
	
	 
	
	/*
	 * 取款
	 * @auth 叶城廷
	 * @version 2018.11.28
	 * @param amount
	 * @return 0:成功 1:不成功
	 */
	public int withdraw(double amount) {	
		//判断账户当前余额是否足够扣款
		if(this.balance >= amount) {
			//执行扣款
			boolean flag=AccountDao.deductMoney(amount,this.card_no);
			//判断扣款是否执行成功
			if(flag) {
				//执行成功，刷新当前余额
				this.balance = this.balance - amount;
				return 0;
			}else {
				//执行出现异常
				System.out.println("系统扣款异常");
			}
		
		}
		return 1;
	}
	
	/*
	 * 存款
	 * @auth 孙少喜
	 * @version 29/11/2018
	 * @param amount
	 * @return 0:成功 1:不成功
	 */
	public int deposit(double amount) {	
		ATM machine = ATM.getInstance();
		//判断账户一次存钱不能超过10000
		if(amount <= 10000) {
			//执行存款
			boolean flag=AccountDao.addMoney(amount,this.card_no);
			//判断存款是否执行成功
			if(flag) {
				//执行成功，刷新当前余额
				this.balance = this.balance + amount;
				return 0;
			}else {
				//执行出现异常
				System.out.println("系统存款异常");
			}
		
		}else{
			machine.getDisplay().setText("一次存款不得超过10000，请重新输入存款金额：");
			machine.getDigitButton().stateChange(1, 0, "DepositInfoServlet");
		}
		return 1;
	}
	
	//无参构造函数
	public Account() {}

	
    //有参构造函数
	public Account(int id, int card_no, int pwd, String name, Double balance, Integer surplus_input_num) {
		super();
		this.id = id;
		this.card_no = card_no;
		this.pwd = pwd;
		this.name = name;
		this.balance = balance;
		this.surplus_input_num = surplus_input_num;
	}
	
	/*
	 * 是否存在用户
	 * @auth 刘清旭
	 * @version 21/12/2018
	 * @param amount
	 * @return 0:存在 1:不存在
	 */
	public int checkCard_no(int card_no) {	
		
		if(card_no>0) {
			
			boolean flag=AccountDao.checkCard_no(card_no);
			if(flag) {
				Transfer t = (Transfer) ATM.getInstance().getSession().getTransaction();
				Account a = new Account();
				a.setCard_no(card_no);
				t.setOtherAccount(a);			
				ATM.getInstance().getDisplay().setText("转账账号："+card_no+",请输入转账金额：");
				ATM.getInstance().getDigitButton().stateChange(1, 0, "TransBalanceServlet");
				return 0;
				
			}else {
				ATM.getInstance().getDisplay().setText("账号无效，请重新输入：");
				
			}
		
		}
		return 1;
	}
   
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCard_no() {
		return card_no;
	}

	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}

	public int getPwd() {
		return pwd;
	}

	public void setPwd(int pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public Integer getSurplus_input_num() {
		return surplus_input_num;
	}



	public void setSurplus_input_num(Integer surplus_input_num) {
		this.surplus_input_num = surplus_input_num;
	}



	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public Double getBalance() {
		
		return this.balance;
	}

	public void transfer(int options,HttpServletRequest req) {
		System.out.println("金额："+options);
				
		Account a1= ATM.getInstance().getSession().getAccount();
		Transfer t = (Transfer) ATM.getInstance().getSession().getTransaction();
		Account a2 = t.getOtherAccount();
		
		//本人余额
		double d1 = AccountDao.checkMenny(a1.getCard_no());
		if(d1>options){
			boolean flag=AccountDao.deductMoney(options,a1.getCard_no());
			boolean flag1=AccountDao.addMoney(options,a2.getCard_no());
		
			//向数据库添加流水账单--调用廖梦青添加流水功能（完成后去掉注释）		
			Voucher voucher=new PrintBillDao().saveBill(a1.getId(),options,a1.getCard_no(),3);
			//相关打印信息存入session
			req.getSession().setAttribute("voucher", voucher);
			
			ATM.getInstance().getDisplay().setText("转账成功，是否打印凭条 <br>"+"打印:1 不打印:0");
			ATM.getInstance().getDigitButton().stateChange(0, 0, "WithdrawPrintServlet");
		}else{
			ATM.getInstance().getDisplay().setText("余额不足，请重新输入：");
			ATM.getInstance().getDigitButton().stateChange(0, 0, "TransactionServlet");
		}


	}	
	
	/*// 账号
	String card_no = "";
	// 密码
	String pwd = "";
	// 余额
	double balance = 1000.00;
	
	public Account(String card_no, String pwd) {
		this.card_no = card_no;
		this.pwd = pwd;
	}
	*//**
	 * 通过用户名，密码获取一个银行账户对象
	 * 实际中应该查询数据库。在当前的实现中，我们只有一个账户。
	 * @param card_no
	 * @param pwd
	 * @return
	 *//*
	public static Account getAccount(String card_no, String pwd) {
		if(card_no.equals("12345") && pwd.equals("123") ) {
			Account act= new Account(card_no,pwd); 
			return act;
		}
		return null;
	}
	
	
	*//**
	 * 获取账户余额
	 * @return
	 *//*
	public double getBalance() {
		return this.balance;
	}
	
	*//**
	 * 存款
	 * @param amount
	 *//*
	public void deposit(double amount) {
		this.balance += amount;
	}*/
}
