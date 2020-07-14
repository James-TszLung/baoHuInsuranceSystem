package com.xiaobaozi.commons.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * XML��Map��List��JSON�໥ת��������
 * 
 * @author zhengbh
 */
public class XmlConverUtil {

	/**
	 * 将XML转换成MAP
	 * 
	 * @param xml
	 * @return
	 */
	public static Map xmltoMap(String xml) {
		try {
			Map map = new HashMap();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List node = nodeElement.elements();
			if(node.size()==0){
				map.put(nodeElement.getName(), nodeElement.getText());
			}else{
				for (Iterator it = node.iterator(); it.hasNext();) {
					Element elm = (Element) it.next();
					Object val = null;
					//获取要存放在map的value对象
					if(elm.elements().size()==0){
						val = elm.getText();
					}else{
						val = xmltoMap(elm.asXML());
					}
					//当遇到map中有相同key值对象时
					Object obj = map.get(elm.getName());
					if(obj!=null){
						Map m = new HashMap();
						List l = new ArrayList();
						if(obj instanceof Map){
							l.add(checkTypeOfObject(elm.getName(),val));
							l.add(obj);
						}else if(obj instanceof List){
							((List) obj).add(checkTypeOfObject(elm.getName(),val));
							l = (List)obj;
						}else if(obj instanceof String){
							l.add(checkTypeOfObject(elm.getName(),obj));
							l.add(checkTypeOfObject(elm.getName(),val));
						}
						val = l;
					}
					//将最终val存入map中
					map.put(elm.getName(), val);
					elm = null;
				}
			}
			node = null;
			nodeElement = null;
			document = null;
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断object的类型，如果是字符串，就封装一个MAP返回
	 * @param name
	 * @param obj
	 * @return
	 */
	private static Map checkTypeOfObject(String name,Object obj){
		Map m = new HashMap();
		if(obj instanceof Map){
			m = (Map)obj;
		}else if(obj instanceof String){
			m.put(name, obj);
		}
		return m;
	}

	/**
	 * xml to json <node><key label="key1">value1</key></node> ת��Ϊ
	 * {"key":{"@label":"key1","#text":"value1"}}
	 * 
	 * @param xml
	 * @return
	 */
	public static String xmltoJson(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		return xmlSerializer.read(xml).toString();
	}

	/**
	 * 
	 * @param document
	 * @return
	 */
	public static String doc2String(Document document) {
		String s = "";
		try {
			// ʹ�������������ת��
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// ʹ��UTF-8����
			OutputFormat format = new OutputFormat("   ", true, "UTF-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
}
