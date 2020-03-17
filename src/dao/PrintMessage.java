package dao;

/**
 * 该类用于封装凭条固定表单信息
 * @author
 * @version
 */
public class PrintMessage {
	
	private String title;
	private String printNum;
	private String ATMNum;
	private String operatMoney;
	private String operaTime;
	private String operaCard;
	private String operaNum;
	
	
	public PrintMessage(String title, String printNum, String ATMNum, String operatMoney, String operaTime,
			String operaCard, String operaNum) {
		super();
		this.title = title;
		this.printNum = printNum;
		this.ATMNum = ATMNum;
		this.operatMoney = operatMoney;
		this.operaTime = operaTime;
		this.operaCard = operaCard;
		this.operaNum = operaNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrintNum() {
		return printNum;
	}
	public void setPrintNum(String printNum) {
		this.printNum = printNum;
	}
	public String getATMNum() {
		return ATMNum;
	}
	public void setATMNum(String aTMNum) {
		ATMNum = aTMNum;
	}
	public String getOperatMoney() {
		return operatMoney;
	}
	public void setOperatMoney(String operatMoney) {
		this.operatMoney = operatMoney;
	}
	public String getOperaTime() {
		return operaTime;
	}
	public void setOperaTime(String operaTime) {
		this.operaTime = operaTime;
	}
	public String getOperaCard() {
		return operaCard;
	}
	public void setOperaCard(String operaCard) {
		this.operaCard = operaCard;
	}
	public String getOperaNum() {
		return operaNum;
	}
	public void setOperaNum(String operaNum) {
		this.operaNum = operaNum;
	}

	
}
