package com.xiaobaozi.dataSystem.commons.utils;
/*package com.skyecho.shenzhenairlines.commons.utils;


import com.skyecho.shenzhenairlines.order.util.DateConverter;
import com.skyecho.shenzhenairlines.order.util.IntConverter;
import com.skyecho.shenzhenairlines.order.util.LongConverter;
import com.skyecho.shenzhenairlines.order.vo.AirBookDetailVO;
import com.skyecho.shenzhenairlines.order.vo.AirBookLineVO;
import com.skyecho.shenzhenairlines.order.vo.AirBookVO;
import com.skyecho.shenzhenairlines.order.vo.OrderRequest;
import com.skyecho.shenzhenairlines.order.vo.OrderRespone;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class OrderXMLUtil {
	public static final String ORDER_REQUEST_ROOT = "order_request";	//订单请求参数xml根节点
	public static final String ORDER_RESPONSE_ROOT = "order_response";	//订单响应xml根节点
	public static final String ORDER_AIRBOOK_ROOT = "orderinfo";	//订单xml订单信息根节点
	public static final String ORDER_AIRBOOKLINE_ROOT = "airline";	//订单xml订单航段信息根节点
	public static final String ORDER_AIRBOOKDETAIL_ROOT = "orderdetail";	//订单xml订单详情信息根节点

	private static XStream getXStream(){
		return new XStream(new DomDriver()); 
	}
	*//**
	 * 将xml转换成订单请求实体类
	 * @param xml
	 * @return
	 *//*
	public static OrderRequest getCreateOrderRequest(String xml){
		XStream xs = getXStream();
		xs.registerConverter(new DateConverter());
		xs.registerConverter(new LongConverter());
		xs.alias(ORDER_REQUEST_ROOT, OrderRequest.class);
		xs.alias(ORDER_AIRBOOK_ROOT, AirBookVO.class);
		xs.alias(ORDER_AIRBOOKLINE_ROOT, AirBookLineVO.class);
		xs.alias(ORDER_AIRBOOKDETAIL_ROOT, AirBookDetailVO.class);
		OrderRequest prp = (OrderRequest)xs.fromXML(xml);
		return prp;
	}
	
	*//**
	 * 将订单请求实体类转换成xml
	 * @param xml
	 * @return
	 *//*
	public static String getCreateOrderRequestXML(OrderRequest req){
		XStream xs = getXStream();
		xs.registerConverter(new DateConverter());
		xs.registerConverter(new LongConverter());
		xs.alias(ORDER_REQUEST_ROOT, OrderRequest.class);
		xs.alias(ORDER_AIRBOOK_ROOT, AirBookVO.class);
		xs.alias(ORDER_AIRBOOKLINE_ROOT, AirBookLineVO.class);
		xs.alias(ORDER_AIRBOOKDETAIL_ROOT, AirBookDetailVO.class);
		return xs.toXML(req);
	}
	
	*//**
	 * 将订单数据实体类PtPolicyRespone转换成xml
	 * @param ptPolicyRespone
	 * @return
	 *//*
	public static String getCreateOrderResponeXml(OrderRespone or){
		XStream xs = getXStream(); 
		xs.alias(ORDER_RESPONSE_ROOT, OrderRespone.class);
		return xs.toXML(or);
	}
	
	*//**
	 * 将生成订单响应信息xml转实体类
	 * @param xml
	 * @return
	 * @author hesx 2015-4-18 上午11:00:08
	 *//*
	public static OrderRespone getOrderRespone(String xml){
		XStream xs = getXStream();
		xs.registerConverter(new LongConverter());
		xs.registerConverter(new IntConverter());
		xs.alias(ORDER_RESPONSE_ROOT, OrderRespone.class);
		OrderRespone orp = (OrderRespone)xs.fromXML(xml);
		return orp;
	}
	
	*//**
	 * @param args
	 *//*
	public static void main(String[] args) {
		
	}

}
*/