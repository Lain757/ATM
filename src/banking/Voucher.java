package banking;

import java.sql.Timestamp;

/**
 * 收据类
 * @author 
 * @version
 *
 */
public class Voucher {

	int watercourse_num; //流水号
	int atm_id;   //ATM机编号
	int money;    //金额
	Timestamp time;    //操作时间
	int card_no;  //操作卡号
	int optionNum;   //业务类型

	public Voucher(int watercourse_num, int atm_id, int money, Timestamp time, int card_no, int optionNum) {
		this.watercourse_num = watercourse_num;
		this.atm_id = atm_id;
		this.money = money;
		this.time = time;
		this.card_no = card_no;
		this.optionNum = optionNum;
	}

	//对应getter和setter方法
	public int getWatercourse_num() {
		return watercourse_num;
	}

	public void setWatercourse_num(int watercourse_num) {
		this.watercourse_num = watercourse_num;
	}

	public int getAtm_id() {
		return atm_id;
	}

	public void setAtm_id(int atm_id) {
		this.atm_id = atm_id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getCard_no() {
		return card_no;
	}

	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}

	public int getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}
	
	
}
