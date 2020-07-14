package com.xiaobaozi.commons.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("code")
public class CodeController extends BaseController{

	//需要靠读取配置文件来最终确定，以下为默认值
	private int width = 69;// 定义图片的width
	private int height = 28;// 定义图片的height
	private int codeCount = 4;// 定义图片上显示验证码的个数
	private int xx = 11;
	private int fontHeight = 24;
	private int codeY = 20;
	String[] codeSequence = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	//固化静态变量
	private final static String CODE_WIDTH = "auth.login.valid.code.width";
	private final static String CODE_HEIGHT = "auth.login.valid.code.height";
	private final static String CODE_COUNT = "auth.login.valid.code.count";
	private final static String CODE_X = "auth.login.valid.code.x";
	private final static String CODE_Y = "auth.login.valid.code.y";
	private final static String FONT_HEIGHT = "auth.login.valid.code.font.height";
	private final static String CODE_SEQUENCE = "auth.login.valid.code.sequence";
	private final static String CODE_INPUTKEY = "auth.login.valid.code.inputkey";
	private final static String CODE_SESKEY = "auth.login.valid.code.sessionkey";
	private final static String CODE_ERRORMSG = "auth.login.valid.code.errormsg";

	/**
	 * 获取新的验证码
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping("getCode.htm")
	public void getCode(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//先初始化数据
		initParams();
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// Graphics2D gd = buffImg.createGraphics();
		// Graphics2D gd = (Graphics2D) buffImg.getGraphics();
		Graphics gd = buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		// 设置字体。
		gd.setFont(font);

		// 画边框。
		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		gd.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = codeSequence[random.nextInt(codeSequence.length)];
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(1);
			green = random.nextInt(128);
			blue = random.nextInt(128);

			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * xx, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		// 将四位数字的验证码保存到Session中。
		HttpSession session = req.getSession();
//		System.out.print(randomCode);
		session.setAttribute(props.getProperty(CODE_SESKEY), randomCode.toString());

		// 禁止图像缓存(写死就行)。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
	
	/**
	 * 实时验证输入的验证码
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void validCode(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();
		String sessionCode = (String)session.getAttribute(props.getProperty(CODE_SESKEY));
		String reqCode = req.getParameter(props.getProperty(CODE_INPUTKEY));
		Map<String, String> rsMap = new HashMap<String, String>();
		if(reqCode.equalsIgnoreCase(sessionCode))
			rsMap.put("ok", "");
		else
			rsMap.put("error", props.getProperty(CODE_ERRORMSG));
		JSONObject jarr = JSONObject.fromObject(rsMap);
		resp.setContentType("text/json;charset=UTF-8");  
		PrintWriter out = null;  
	    try {  
	        out = resp.getWriter();  
	        out.println(jarr.toString());  
	    }  
	    catch (IOException ex1) {  
	        ex1.printStackTrace();  
	    }finally{  
	    	out.close();  
	    }  
	}
	
	/**
	 * 重新读取配置文件中的配置
	 * 注：如果配置文件中无相应属性值，那么对应的变量将采用默认值进行生产验证码
	 */
	private void initParams(){
		if(StringUtils.isNotBlank(props.getProperty(CODE_WIDTH)))
			this.width = Integer.parseInt(props.getProperty(CODE_WIDTH));
		if(StringUtils.isNotBlank(props.getProperty(CODE_HEIGHT)))
			this.height = Integer.parseInt(props.getProperty(CODE_HEIGHT));
		if(StringUtils.isNotBlank(props.getProperty(CODE_COUNT)))
			this.codeCount = Integer.parseInt(props.getProperty(CODE_COUNT));
		if(StringUtils.isNotBlank(props.getProperty(CODE_X)))
			this.xx = Integer.parseInt(props.getProperty(CODE_X));
		if(StringUtils.isNotBlank(props.getProperty(CODE_Y)))
			this.codeY = Integer.parseInt(props.getProperty(CODE_Y));
		if(StringUtils.isNotBlank(props.getProperty(FONT_HEIGHT)))
			this.fontHeight = Integer.parseInt(props.getProperty(FONT_HEIGHT));
		//遍历验证码数组
		if(StringUtils.isNotBlank(props.getProperty(CODE_SEQUENCE)))
			this.codeSequence = props.getProperty(CODE_SEQUENCE).split(",");
	}
}
