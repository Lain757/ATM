<%--
  Created by IntelliJ IDEA.
  User: 廖梦青
  Date: 2018-12-20
  该页面用于显示用户的凭条信息，模拟订单打印完成。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WO银行凭条</title>
</head>
<body>
<div class="box">
    <h4>${sessionScope.printMessage.title}</h4>
    <div class="textTop">
       ${sessionScope.printMessage.printNum}${sessionScope.voucher.watercourse_num}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.ATMNum}${sessionScope.voucher.atm_id}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.operatMoney}${sessionScope.voucher.money}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.operaTime}${sessionScope.voucher.time}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.operaCard}${sessionScope.voucher.card_no}
    </div>
   <div class="textBottom">
        ${sessionScope.printMessage.operaNum}${sessionScope.voucher.optionNum}
    </div>

</div>
</body>
</html>
