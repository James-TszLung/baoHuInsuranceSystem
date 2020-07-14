<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<script type="text/javascript">
	function view() {
		visitUrl(ctxPath + "/factoryOrder/list.htm", 1, 1, 1);
	}
	$(function() {
		var areaList;
		$.post(ctxPath + "/areaInfo/queryAreaListByMap.htm", function(data,
				status) {
			$("#areaId").empty();
			areaList = data;
		}, "json");
	});
</script>
<div class="content">
	<div class="orders_tile">
		<p class="orders_tile_s01">
			<span>订单详情</span> &gt;&gt; <span>查看</span>
		</p>
	</div>
	<form id="viewform" method="post">
		<div class="policy_added">
			<ul>
				<li style="line-height: 10px"><label>订单号：</label>
					<div class="pdiv">${factoryOrder.orderId}&nbsp;</div>
				</li>
				<li style="line-height: 10px"><label>收货地区：&nbsp;</label>
					<div class="pdiv">${areaInfo.areaName}</div>
				</li>
				<li style="line-height: 10px"><label>工厂名称：&nbsp;</label>
					<div class="pdiv">${factoryInfo.factoryName}</div>
				</li>
				<li style="line-height: 10px"><c:forEach
						items="${factoryOrder.preOrderList}" var="preOrder">
						<div class="pdiv" style="margin:30px">
							<span style="line-height:1.5 ;font-size:15px;margin:30px">产品名称：${preOrder.productName}&nbsp;</span>
							<c:if test="${preOrder.attribute==1}">
								<span style="line-height:1.5 ;font-size:15px;margin:30px">属性：大公仔&nbsp;</span>
							</c:if>
							<c:if test="${preOrder.attribute==2}">
								<span style="line-height:1.5 ;font-size:15px;margin:30px">属性：小公仔&nbsp;</span>
							</c:if>
							<span style="line-height:1.5 ;font-size:15px;margin:30px">数量：${preOrder.num}&nbsp;</span>
							<span style="line-height:1.5 ;font-size:15px;margin:30px">单价：${preOrder.price}&nbsp;</span>
						</div>
					</c:forEach>
				</li>
				<li style="line-height: 10px"><label>是否交付30%定金:</label>
					<div class="pdiv">
						<c:if test="${factoryOrder.isDeposit==1}">
					是&nbsp;&nbsp;&nbsp;&nbsp;${factoryOrder.deposit}
				</c:if>
						<c:if test="${factoryOrder.isDeposit==0}">
					否
				</c:if>
						&nbsp;
					</div>
				</li>
				<li style="line-height: 10px"><label>是否已确认收货:</label>
					<div class="pdiv">
						<c:if test="${factoryOrder.isConfirm==1}">
					是
				</c:if>
						<c:if test="${factoryOrder.isConfirm==0}">
					否
				</c:if>
						&nbsp;
					</div>
				</li>
				<li style="line-height: 10px"><label>总货款:</label>
					<div class="pdiv">${factoryOrder.goodsCost} &nbsp;</div>
				</li>
				<li style="line-height: 10px"><label>欠款:</label>
					<div class="pdiv">${factoryOrder.owesGoodsCost} &nbsp;</div>
				</li>
				<li style="line-height: 10px"><label>已付货款:</label>
					<div class="pdiv">${factoryOrder.paidGoodsCost} &nbsp;</div>
				</li>
				<li style="line-height: 10px"><label>下单时间：</label>
					<div class="pdiv">
						<fmt:formatDate value="${factoryOrder.orderTime}"
							pattern="yyyy-MM-dd HH:mm:ss" />
						&nbsp;
					</div>
				</li>
				<li style="line-height: 10px"><label>备注：</label>
					<div class="pdiv">
						<span style="line-height:1.5 ;font-size:15px;margin:30px">${factoryOrder.remark}&nbsp;</span>
					</div>
				</li>
				<li style="border-bottom:0">
					<div style="text-align: center; margin-top:30px;">
						<input id="backBtn" type="button" value="返回" onclick="view()"
							class="city_airport_button_s01" style="background-color:#cccccc;color:#ffffff;" />
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

