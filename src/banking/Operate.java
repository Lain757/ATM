package banking;

/**
 * 该类为operate的实体类，用于封装用户的操作类型
 * @author
 * @version
 */
public class Operate {

    private int operateId;
    private String operateName;

    public Operate() {
    }

    public Operate(int operateId, String operateName) {
        this.operateId = operateId;
        this.operateName = operateName;
    }

    public int getOperateId() {
        return operateId;
    }

    public void setOperateId(int operateId) {
        this.operateId = operateId;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }
}
