<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<title>ATM</title>
<link href="css/index.css" rel="stylesheet" type="text/css"/>

</head>
  
<body bgcolor="#ffffff">

<div id="main">
	<div id="topic">欢 迎 来 到  WO 银 行 办 理 自 助 业 务 </div>
	
	<!-- 荧屏 -->
	<div id="display">
	<br/>
	</div>
	

	<div id="receipt">
	<!-- <center>
		<button type="button" id="btn">业&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务</button>
		<br/>
		
		<div id="kuan">
		<center>
		<button type="button" id="chaxun" class="command">查询</button> 
		<button type="button" id="qukuan" class="command" value="1" onclick="readNum(this);">取款</button>
		<button type="button" id="zhuanzhang" class="command">转账</button>
		<button type="button" id="cunkuan" class="command" value="2" onclick="readNum(this);">存款</button> 
		<button type="button" id="quxiao" class="command">取消</button> 
		<button type="button" id="queding" class="command">确定</button>
		<button type="button" id="fanhui" class="command">返回</button>
		<button type="button" id="quka" class="command">取卡</button>  
		</center>
		</div>
		</center>
	 -->		
		<center>
		<button type="button" id="b1" class="number" value="1" onclick="readNum(this);">1</button> 
		<button type="button" id="b2" class="number" value="2" onclick="readNum(this);">2</button> 
		<button type="button" id="b3" class="number" value="3" onclick="readNum(this);">3</button>
		<button type="button" id="b4" class="number" value="4" onclick="readNum(this);">4</button> 
		<button type="button" id="b5" class="number" value="5" onclick="readNum(this);">5</button> 
		<button type="button" id="b6" class="number" value="6" onclick="readNum(this);">6</button>
		<button type="button" id="b7" class="number" value="7" onclick="readNum(this);">7</button> 
		<button type="button" id="b8" class="number" value="8" onclick="readNum(this);">8</button> 
		<button type="button" id="b9" class="number" value="9" onclick="readNum(this);">9</button>
		<button type="button" id="b0" class="number0" value="0" onclick="readNum(this);">0</button>
		</center>
		
		<center>
		<button type="button" id="cancel" class="command1" onclick="cancel();">取消</button>
		<button type="button" id="comfirm" class="command1" onclick="submit();">确认</button>
		</center>
	
	</div>
	
<div id="receipt1">
	<center>
		<button type="button" id="btn">打 印 收 据</button>
		<br/>	
		<div style="width: 270px;height: 210px;background-color: white;margin-left:-46px">
			<%@include file="printBill.jsp" %>
		</div>
		</center>
</div>
	<!--div id="panel">
		<div id="left_panel">
		</div>
		<div id="right_panel">
			<button type="button" id="log">显示日志</button> 
		</div>
	</div
	-->
<div id="receipt2">
<div id="kuan2">
	<div id="panel">
	<!--button type="button" class="button" id="on" onclick="turnon();">开机</button> 
	<button type="button" class="button" id="off">关机</button-->
	<button type="button" class="button" id="switch"></button>&nbsp;&nbsp;
	<button type="button" class="button" id="card">插卡</button>
	<textarea rows="2" cols="2" id="cqqk">存/取钱口</textarea>
	</div>
	</div>
</div>

		
</div>

</body>
</html>
