package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atm.ATM;
import atm.Session;


public class InquiryServlet extends HttpServlet {

	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Session currentSession = ATM.getInstance().getSession();
		ATM machine = ATM.getInstance();
		
		machine.getSession().setState(3);
		machine.getDisplay().setText("请选择您需要的业务："+"<br>"+"1:取款 2:存款 3:转账 4:查询  0:退出");
		machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
		
		String json = ATM.getInstance().getResponse();
		resp.setContentType("text/json");  
		resp.setCharacterEncoding("UTF-8"); 
		resp.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}