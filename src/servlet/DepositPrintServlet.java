package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atm.ATM;
import dao.PrintMessage;

public class DepositPrintServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int flag = Integer.parseInt(req.getParameter("num"));
		ATM machine = ATM.getInstance();
		if(flag==1) {
			//打印凭条
			//保存PrintMessage对象
			PrintMessage printMessage=new PrintMessage("WO银行凭条信息","凭条编号:","ATM机编号:","操作金额:","操作时间:","银行卡号:","操作编号:");
			req.getSession().setAttribute("printMessage", printMessage);
			machine.getSession().setState(3);
			machine.getDisplay().setText("打印成功，请收好凭条！"+"<br>"+"请选择您需要的业务："+"<br>"+"1:取款 2:存款 3:转账 4:查询  0:退出");
			machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
			//req.getRequestDispatcher("printBill.jsp").forward(req, resp);	
			
		}else if(flag==0){
			//不打印凭条
			machine.getSession().setState(3);
			machine.getDisplay().setText("请选择您需要的业务："+"<br>"+"1:取款 2:存款 3:转账 4:查询  0:退出");
			machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
		
		}else {
			//用户按的数字非0非1
			//向ATM荧屏显示文字
			machine.getDisplay().setText("输入错误，请重新输入"+"<br>"+"不打印:0 打印:1");
			//充值ATM状态和下次即将调整的servlet名字
			machine.getDigitButton().stateChange(0, 0, "DepositPrintServlet");
		}
		//ATM.getInstance().getSession().getTransaction().print(num);
		String json = ATM.getInstance().getResponse();
		resp.setContentType("text/json");  
		resp.setCharacterEncoding("UTF-8"); 
		resp.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
