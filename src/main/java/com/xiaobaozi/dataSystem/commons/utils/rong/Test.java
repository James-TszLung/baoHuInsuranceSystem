package com.xiaobaozi.dataSystem.commons.utils.rong;

import com.xiaobaozi.dataSystem.commons.utils.rong.messages.ImgTextMessage;
import com.xiaobaozi.dataSystem.commons.utils.rong.messages.IncludingLinkMessage;
import com.xiaobaozi.dataSystem.commons.utils.rong.messages.TxtMessage;
import com.xiaobaozi.dataSystem.commons.utils.rong.messages.VoiceMessage;
import com.xiaobaozi.dataSystem.commons.utils.rong.models.CodeSuccessResult;
import com.xiaobaozi.dataSystem.commons.utils.rong.models.TokenResult;

public class Test {
	public static void main(String[] args) throws Exception {
		String appKey = "pvxdm17jpianr";//替换成您的appkey
		String appSecret = "A4Lhv2B0zjLsJ5";//替换成匹配上面key的secret
		RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
		// 发送系统消息方法（一个用户向一个或多个用户发送系统消息，单条消息最大 128k，会话类型为 SYSTEM。每秒钟最多发送 100 条消息，每次最多同时向 100 人发送，如：一次发送 100 人时，示为 100 条消息。） 
	/*	String[] messagePublishSystemToUserId = {"2405b3be0e6847a59da65659bc0f531f","userid3","userId4"};
		TxtMessage messagePublishSystemTxtMessage = new TxtMessage("Okk", "helloExtra");
		//ImgTextMessage imgTextMessage =new ImgTextMessage("k看哦", "ook", "oo", "dfff", "dvdf");
		CodeSuccessResult messagePublishSystemResult = rongCloud.message.PublishSystem("4eb8e7d655044f4899cbedc37faa5da0", messagePublishSystemToUserId, messagePublishSystemTxtMessage, "thisisapush", "{\"pushData\":\"hello\"}", 1, 1);
		System.out.println("PublishSystem:  " + messagePublishSystemResult.toString());
		*/
		// 发送单聊消息方法（一个用户向另外一个用户发送消息，单条消息最大 128k。每分钟最多发送 6000 条信息，每次发送用户上限为 1000 人，如：一次发送 1000 人时，示为 1000 条消息。） 
		//String[] messagePublishPrivateToUserId = {"822a30f3f84e45b8a9677edfb43d6293","userid3","userId4"};
		String[] messagePublishPrivateToUserId = {"2405b3be0e6847a59da65659bc0f531f","userid3","userId4"};
		ImgTextMessage messagePublishPrivateVoiceMessage = new ImgTextMessage("您于2017年7月6日确认接单了一笔服务订单，订单号为10031466562，点击查看订单详情", "ook", "接单成功", "/stewardSystem/tgySteward/getTgyStewardPageDetial.htm", "");
		CodeSuccessResult messagePublishPrivateResult = rongCloud.message.publishPrivate("4eb8e7d655044f4899cbedc37faa5da0", messagePublishPrivateToUserId, messagePublishPrivateVoiceMessage, "thisisapush", "{\"pushData\":\"hello\"}", "4", 1, 1, 1, 1);
		System.out.println("publishPrivate:  " + messagePublishPrivateResult.toString());
		
		
	}

}
