package com.xiaobaozi.dataSystem.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 进程处理写日志流
 * @author xubiao
 *
 * 2015-1-30
 */
public class StreamOutPrint extends Thread{
	private InputStream in;// 输入流
	private PrintWriter pw;// 输出流

	public StreamOutPrint(InputStream in, PrintWriter pw) {
		this.in = in;
		this.pw = pw;
	}

	public void quit() {
		// 关闭流
		if (pw != null) {
			pw.close();
		}
	}
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (pw != null)
					pw.println(line);
			}
			if (pw != null)
				pw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally{
			quit();//退出流	
		}
	}
}
