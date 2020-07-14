package com.xiaobaozi.dataSystem.commons.utils;
/*package com.skyecho.shenzhenairlines.commons.utils;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

*//**
 * 
 * @author xubiao
 *
 * 2015-1-30
 *//*
public class ExecRunner {
	private int maxRunTimeSecs;
	private String out;
	private String err;
	private boolean maxRunTimeExceeded;
	public int getMaxRunTimeSecs() {
		return maxRunTimeSecs;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public void setMaxRunTimeSecs(int maxRunTimeSecs) {
		this.maxRunTimeSecs = maxRunTimeSecs;
	}
	public boolean isMaxRunTimeExceeded() {
		return maxRunTimeExceeded;
	}
	public void setMaxRunTimeExceeded(boolean maxRunTimeExceeded) {
		this.maxRunTimeExceeded = maxRunTimeExceeded;
	}
	public ExecRunner() {
		out = new String();
		err = new String();
		maxRunTimeSecs = 0;
		maxRunTimeExceeded = false;
	}
	
	public int exec(ProcessVO process) throws IOException, InterruptedException {
		int exitVal = 1;//正常结束为0
		Runtime rt = Runtime.getRuntime();
		String cmd[] =null;
		Date startTime = new Date();
		long startTimeMs = startTime.getTime();//开始时间
		long maxTimeMs = startTimeMs + (long) (maxRunTimeSecs * 1000);//运行最大时间
		String osName = System.getProperty("os.name");//获取系统名称，判断指令名称
		if (osName.toUpperCase().contains("WINDOWS")) {//windows下的命令执行
			cmd = new String[3];
			cmd[0] = "cmd.exe";
			cmd[1] = "/C";
			cmd[2] = process.getCommand();
		}else if(osName.toUpperCase().contains("LINUX")){
			cmd = new String[3];
			cmd[0] = "/bin/sh";
			cmd[1] = "-c";
			cmd[2] = process.getCommand();
		}
		Process proc;
		if (cmd != null && cmd.length > 0){
			//Linux切换指定目录
			if(StringUtils.isNotBlank(process.getWorkDir())){
			rt.exec(process.getWorkDir());
			}
	     	proc = rt.exec(cmd);
		}
		else{
			throw new IOException("命令执行错误,请联系系统管理员!");
		}
		//将执行的错误和输如流记录起来
		StreamOutPrint outputGobbler =new StreamOutPrint(proc.getInputStream(), process.getOutStr());
		StreamOutPrint errorGobbler = new StreamOutPrint(proc.getErrorStream(), process.getError());
		outputGobbler.start();
		errorGobbler.start();
		do {//一直后的到进程的出口值
			try {
				exitVal = proc.exitValue();
				break;
			} catch (IllegalThreadStateException e) {//还没运行,则继续等待下次执行
				if (maxRunTimeSecs > 0) {
					Date endTime = new Date();
					long endTimeMs = endTime.getTime();
					if (endTimeMs > maxTimeMs) {//结束时间大于指定的知道运行时间则进程销毁
						proc.destroy();
						maxRunTimeExceeded = true;
						process.getError().println("MAX_RUN_TIME_EXCEEDED");
						outputGobbler.quit();
						errorGobbler.quit();
						return exitVal;
					}
					Thread.sleep(100L);
				}
			}
		} while (true);
		while (outputGobbler.isAlive() || errorGobbler.isAlive());//输入输出存在则继续
		if(process.getOutStr()!=null){
			process.getOutStr().flush();
		}
		if(process.getError()!=null){
			process.getError().flush();
		}
		return exitVal;
	}
}
*/