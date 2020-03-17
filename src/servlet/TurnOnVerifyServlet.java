package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import atm.ATM;

public class TurnOnVerifyServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ATM machine = ATM.getInstance();
		
		
		//暂时开机
		machine.setState(0);
		machine.getSwitchButton().stateChange(ATM.IDLE);
		
		machine.getDisplay().setText("该机初始化中<br>请输入管理员账号与密码完成启动，账号与密码之间用“0000”隔开，如密码或格式错误直接关机");
		machine.getDigitButton().stateChange(1, 1, "TurnOnServlet");
		
		String json = ATM.getInstance().getResponse();
		resp.setContentType("text/json");  
		resp.setCharacterEncoding("UTF-8"); 
		resp.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
