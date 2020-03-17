package dao;

import DB.DbOperation;

import banking.Voucher;

import java.sql.Timestamp;
import java.util.*;

/**
 * 该类用于封装打印账单所需的方法
 * @author
 * @version
 */
public class PrintBillDao {

    /**
     * 该方法用于保存账单信息到数据库中
     * @param atmId 用户操作的ATM机Id
     * @param money 用户操作的金额数
     * @param cardNo 用户的卡号
     * @param optionNum 用户操作类型的编号
     * @return
     */
    public Voucher saveBill(int atmId, int money,int cardNo, int optionNum) {
    	Voucher voucher=null;
        String sql="INSERT INTO voucher_info(atmId,money,time,cardNo,optionNum) VALUES(?,?,?,?,?)";
        Timestamp nowTime=createTime();
        //添加一条记录到数据库
        ArrayList<Object> array=new ArrayList<Object>();
        array.add(atmId);
        array.add(money);
        array.add(nowTime);
        array.add(cardNo);
        array.add(optionNum);
        //保存数据
        try {
			new DbOperation().execute(1, sql, array, false);
			voucher=seleBill(cardNo,nowTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return voucher;
    }

    /**
     * 该方法用于获取系统当前时间
     * @return
     */
    public Timestamp createTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    /**
     * 根据用户卡号和操作时间查询凭条
     * @return
     */
    public Voucher seleBill(int cardNo,Timestamp timestamp) throws Exception {
        String sql="SELECT * FROM voucher_info WHERE cardNo=? and time=?";
        ArrayList<Object> array=new ArrayList<Object>();
        array.add(cardNo);
        array.add(timestamp);
        //获取记录[]中的json数据
        ArrayList arrayList=new DbOperation().execute(1, sql, array, false);
        Voucher voucher=packageVocher(arrayList);
        return voucher;
    }

    /**
     * 封装数据库查询后获取到的Voucher对象
     * @param arrayList 数据库查询得到的list数据
     * @return
     */
    public Voucher packageVocher(ArrayList arrayList){
        Voucher voucher=null;
        if (arrayList.size()!=0){
            //获取记录{}中的list数据
            HashMap arrayList2=(HashMap) arrayList.get(0);
            //读取数据库记录中的字段
            int watercourse_num=(int) arrayList2.get("watercourseNum");
            int atm_id=(int) arrayList2.get("atmId");
            int money=(int) arrayList2.get("money");
            Timestamp time=(Timestamp) arrayList2.get("time");
            int card_no=(int) arrayList2.get("cardNo");
            int optionNum=(int) arrayList2.get("optionNum");
            voucher=new Voucher(watercourse_num,atm_id,money,time,card_no,optionNum);
        }
        return voucher;
    }



}
