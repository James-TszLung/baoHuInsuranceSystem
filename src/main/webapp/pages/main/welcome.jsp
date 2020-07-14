<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<meta charset="UTF-8">
<title>保乎笔记业务管理系统</title>
<style>
* {
	margin: 0;
	padding: 0;
}

ul,li {
	list-style: none;
}

.main-wrap {
	width: 930px;
	font-size: 14px;
}

.main-wrap .top-info {
	width: 100%;
	height: 45px;
	line-height: 45px;
}

.top-info ul {
	width: 100%;
	overflow: hidden;
}

.top-info ul li {
	width: 17%;
	text-align: center;
	float: left;
}

.top-info ul li:last-child {
	width: 32%;
}

.bot-wrap {
	width: 100%;
	display: -webkit-box;
	border: 1px solid #999;
}

.left-part {
	width: 600px;
	border-right: 1px solid #e5e5e5;
	box-sizing: border-box;
}

.chart1,.chart2 {
	border-bottom: 1px solid #e5e5e5;
	margin-top: 20px;
}

.left-bot {
	width: 100%;
	overflow: hidden;
}

.left-bot .section {
	width: 50%;
	padding: 10px 20px;
	box-sizing: border-box;
	text-align: left;
	float: left;
}

.left-bot .section:first-child {
	border-right: 1px solid #e5e5e5;
}

.left-bot .section .tit,.right-part .tit {
	font-size: 16px;
	padding: 10px 0;
}

.left-bot .section ul {
	width: 100%;
	line-height: 30px;
}

.left-bot .section ul li {
	width: 100%;
	height: 30px;
}

.left-bot .section ul li p {
	float: left;
	width: 50%;
}

.left-bot .section ul li p:first-child {
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
}

.left-bot .section ul li p:last-child {
	text-align: right;
}

.right-part {
	width: 330px;
}

.right-part .part {
	width: 100%;
	height: 280px;
	padding: 10px 20px;
	box-sizing: border-box;
	line-height: 30px;
	border-bottom: 1px solid #e5e5e5;
}

.right-part .part:last-child {
	border: none;
}

.right-part .part .tit .title-right {
	margin-left: 90px;
	color: grey;
	font-size: 14px;
}

.right-part .part .list ul {
	position: relative;
}

.right-part .part .list .item-right {
	position: absolute;
	right: 60px;
	font-size: 14px;
}
</style>
<div class="main-wrap">
</div>
