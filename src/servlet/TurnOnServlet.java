package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atm.ATM;
import dao.AccountDao;

/**
* 开启ATM
* @author  何希
* @version 10/06/2018
*/
public class TurnOnServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
        ATM machine = ATM.getInstance();
		
		String userJson = req.getParameter("num");
		
		//用于接收分隔后的账号密码
		String[] str = null;
		
		//用于判断管理员正确性
		boolean flag=false;
		
		try {
			str=userJson.split("0000");
		}catch (Exception e) {
			System.out.println("格式错误");
			flag=false;
		}
		
		 //验证管理员
		 flag=new AccountDao().selectManager(str[0],str[1]);
		if(flag) {
			machine.getDisplay().setText("请插入您的银行卡!");
			machine.getDigitButton().stateChange(2,1, "CardInsertedServlet");
		
		}else {
			ATM.getInstance().turnoff();
		}
		
		
		String json = ATM.getInstance().getResponse();
		resp.setContentType("text/json");  
		resp.setCharacterEncoding("UTF-8"); 
		resp.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
