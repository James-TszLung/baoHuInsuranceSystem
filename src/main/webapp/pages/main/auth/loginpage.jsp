<%@page import="org.springframework.security.web.WebAttributes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//禁止缓存
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录-管理系统</title>
<link rel="icon" href="${ctxPath}/images/images/favicon.png">
<link type="text/css" rel="stylesheet" href="${ctxPath}/styles/unique/logincss.css">
<script type="text/javascript" src="${ctxPath}/scripts/unique/login_script.js"></script>
<style type="text/css">
body {
	background: #FFF url(${ctxPath}/images/images/baohubackimage.png);
	background-size：100
	%
	100%
	/*background: url(${ctxPath}/images/images/baohubackimage.png) no-repeat; */
	background-size
	:
	100%
	100%;
}
</style>
</head>
<body style="background:#FFF;">
	<div class="">
		<div class="login-wrap" style="height: 100vh;">
			<div class="login-box" style="top: 320px;right: 48px;">
				<%--  action="<%=request.getContextPath()%>/j_spring_security_check" --%>
				<form id="reqForm" name="reqForm" method="POST" autocomplete="off">
					<div class="login-tit" style="color:#9F1711; display: none">
						登录 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="faulsts" style="font-size:14px;color:#9F1711;">${SPRING_SECURITY_LAST_EXCEPTION.message}</font>
					</div>
					<input type="text" placeholder="请输入账号" class="first" name="j_username" id="j_username" type="text" value="" /> <span
						class="msg-box n-right" style="position:static;" for="j_username"> <input type="password" name="j_password" value=""
						placeholder="请输入密码" id="j_password" class="sec" /> <span class="msg-box n-right" style="position:static;" for="j_password">
							<div class="input-yzm">
								<input type="text" placeholder="请输入验证码" name="code_input" id="code_input" type="text" />
								<div class="yzm-wrap">
									<div class="yzm">
										<a id="refreshCode" title="点击切换验证码"> <img id="valid_code" class="valid_code" width="105" height="46" border="0"
											align="absmiddle" src="../code/getCode.htm" /> </a>
									</div>
								</div>
								<div class="change-yzm" style="position: absolute;right: 0;top: 45px;color: #ff0000;">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
							</div> <!-- <input type="submit" value="登录" onclick="loginSubmit()"> --> <input type="button" value="登录"
							style="    background: rgb(0,111,214);border: none;font-size: 18px;color: #FFF;border-radius: 4px;"
							onclick="loginSubmit()">
				</form>
			</div>
		</div>
		<div class="container"></div>
	</div>
	<script>
		$(function() {
			// 扫码关注
			/* 			var c_state1 = 0;
			 $('#concern').click(function() {
			 if (c_state1 == 0) {
			 $('#concern_us').css('display', 'block');
			 $(this).addClass(' current');
			 c_state1 = 1;
			 } else if (c_state1 == 1) {
			 $('#concern_us').css('display', 'none');
			 $(this).removeClass(' current');
			 c_state1 = 0;
			 }
			 })
			 var c_state2 = 0;
			 $('#contact').click(function() {
			 if (c_state2 == 0) {
			 $('#contact_num').css('display', 'block');
			 $(this).addClass(' current');
			 c_state2 = 1;
			 } else if (c_state2 == 1) {
			 $('#contact_num').css('display', 'none');
			 $(this).removeClass(' current');
			 c_state2 = 0;
			 }
			 }) */

			$('#concern').mouseenter(function() {
				$('#concern_us').css('display', 'block');
				$(this).addClass(' current');
			})
			$('#concern').mouseleave(function() {
				$('#concern_us').css('display', 'none');
				$(this).removeClass(' current');
			});

			$('#contact').mouseenter(function() {
				$('#contact_num').css('display', 'block');
				$(this).addClass(' current');
			});
			$('#contact').mouseleave(function() {
				$('#contact_num').css('display', 'none');
				$(this).removeClass(' current');
			});

			// 轮播图
			//bannerslide(800, 5000);  //只有一张先注释掉，暂时不使用
			// 服务栏变色
			$('#ser_list li').mouseenter(function() {
				var h = $(this).children().children().attr('href');
				var s = $(this).children().children().children('img').attr('src');
				$(this).children().children('a').attr('href', s);
				$(this).children().children().children('img').attr('src', h);
				$(this).addClass(' active'); //{'background':'#00a0e9','color':'#FFF'}
			});
			$('#ser_list li').mouseleave(function() {
				var h = $(this).children().children().attr('href');
				var s = $(this).children().children().children('img').attr('src');
				$(this).children().children('a').attr('href', s);
				$(this).children().children().children('img').attr('src', h);
				$(this).removeClass(' active');
			});
		})

		function bannerslide(lunboshijian, jiangeshijian) {
			var nowimg = 0;
			$(".banner .banner-list li:first").clone().appendTo(".banner .banner-list");
			var timer = setInterval(slideFunc, jiangeshijian);
			function slideFunc() {
				if (!$(".banner .banner-list").is(":animated")) {
					if (nowimg < $(".banner .banner-list li").length - 2) {
						nowimg++;
						$(".banner .banner-list").animate({
							"left" : -100 * nowimg + "%"
						}, lunboshijian);
					} else {
						nowimg = 0;
						$(".banner .banner-list").animate({
							"left" : -100 * ($(".banner .banner-list li").length - 1) + "%"
						}, lunboshijian, function() {
							$(".banner .banner-list").css("left", 0);
						})
					}
					$(".banner .imgnav li").eq(nowimg).addClass(" active").siblings().removeClass(" active");
				}
			}
			$(".banner .imgnav li").click(function() {
				$(".banner .banner-list").stop(true);
				nowimg = $(this).index();
				$(".banner .banner-list").animate({
					"left" : -100 * nowimg + "%"
				}, lunboshijian);
				$(".banner .imgnav li").eq(nowimg).addClass(" active").siblings().removeClass(" active");
			});
		}
	</script>
</body>
</html>