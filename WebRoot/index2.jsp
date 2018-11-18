<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<title>欢迎登录后台管理系统</title>
<link href="${APP_PATH }/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${APP_PATH }/js/jquery.js"></script>
<script src="${APP_PATH }/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox loginbox1">
    <%
    	Integer flag=-1;
    	String str=(String)session.getAttribute("flag");
    	if(str!=null){
    		flag=Integer.parseInt(str);
    	}
    	if(flag.equals(0)){
    %>
	    <div style="text-align: center;">
	    	<p style="font-size: 15px;color:darkred;font-weight: bold;">用户名或密码错误</p>
	    </div>
    <%}  %>
    
     <%
    	Object pwd=session.getAttribute("flag");
    	if(flag.equals(1)){
    %>
	    <div style="text-align: center;">
	    	<p style="font-size: 15px;color:darkred;font-weight: bold;">密码修改成功</p>
	    </div>
    	
    <%} 
    	session.removeAttribute("flag");	
    %>
    
     <%
    	Object reg=session.getAttribute("flag");
    	if(flag.equals(2)){
    %>
	    <div style="text-align: center;">
	    	<p style="font-size: 15px;color:darkred;font-weight: bold;">注册成功</p>
	    </div>
    	
    <%} 
    	session.removeAttribute("flag");	
    %>
    
    <form action="UserServlet" method="post">
	    <ul>
	    	
	    <li><input name="uname" type="text" placeholder="用户名" class="loginuser"  /></li>
	    <input name="oper" type="hidden" value="login" />
	    <li><input name="pwd" type="text" placeholder="密码" class="loginpwd" /></li>
	    <li class="yzm">
	    <span><input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite> 
	    </li>
	    <li><input name="" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location='main.html'"  /><label><a href="user/reg.jsp">注册</a></label><label><a href="#">忘记密码？</a></label></li>
	    </ul>
    </form>
    
    </div>
    
    </div>
    
    
    <div class="loginbm">版权所有  2014  <a href="http://www.uimaker.com">uimaker.com</a>  仅供学习交流，勿用于任何商业用途</div>
	
    
</body>

</html>
