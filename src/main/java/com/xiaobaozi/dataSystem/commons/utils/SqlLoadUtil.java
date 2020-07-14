package com.xiaobaozi.dataSystem.commons.utils;


/**
 * 进行命令导入数据库
 * @author xubiao
 *
 * 2015-1-30
 */
/*public class SqlLoadUtil {
	protected static Logger log = Logger.getLogger(SqlLoadUtil.class);
	
	public void importToDataBase(List<LoadDataVO> loadDataList) {
		if(loadDataList==null||loadDataList.size()==0){//请求对象为空,无法操作数据库
			return;
		}
		List<String> pathList = new ArrayList<String>();
		try {
			ExecRunner er = new ExecRunner();
			//创建临时控制文件
			for (int i=0;i<loadDataList.size();i++) {//根据模板创建文件
				LoadDataVO loadData=loadDataList.get(i);
				File f=new File(loadData.getTaskTag());//控制文件路劲
				String dataFile=loadData.getOutFilePath();//数据文件路劲
				String tableName=loadData.getTableName();//表名
				if(StringUtils.isNotBlank(tableName)){//复制模板
					pathList.add(getCLTFile(f,dataFile,tableName,loadData));
				}
			}
			//LINUX下需要从数据库切换到应用的用户
			// 开始导入数据
			ConfigUtil configUtil=ConfigUtil.getInstance();
			String cmd=configUtil.getSqlldr();
			if(pathList!=null&&pathList.size()>0){
				for (int i=0;i<pathList.size();i++) {
					String s=pathList.get(i);
					LoadDataVO loadData=loadDataList.get(i);
					PrintWriter stid_pw = new PrintWriter(new StringWriter());
					PrintWriter err_pw = new PrintWriter(new StringWriter());
					//日志文件名
					String Logname =loadData.getOutFilePath().replace(loadData.getSuffer(), ".log");
					ProcessVO process=new ProcessVO();//组装执行数据
					process.setCommand(cmd + " control=" + s + ", errors=0, log=" + Logname + ",direct=true, parallel=true");
					process.setWorkDir("");
					process.setError(err_pw);
					process.setOutStr(stid_pw);
					log.error("接收到的命令格式为:"+process.getCommand());
					er.exec(process);
					// 关闭流
					if (stid_pw != null)
						stid_pw.close();
					if (err_pw != null)
						err_pw.close();
				}
			}else{
				log.error("此次请求的数据为空：------------------》》》》》》");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("执行命令出错："+e.getMessage());
		} finally {
			this.clearTempCTLFile(pathList);
		}
	}
	
    *//**
     * 根据模板穿件文件,一面多人同时惭怍一个文件
     * @param f
     * @param dataFile
     * @param tableName
     * @param loadData
     * @return
     * @throws IOException
     *//*
    private String getCLTFile(File f,String dataFile,String tableName,LoadDataVO loadData) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));//得到文件流
			File temp = new File(f.getPath().replace(".ctl", loadData.getTaskid()+".ctl"));
			if (!temp.exists()) {
				temp.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
			String line = br.readLine();
			try{
			while (line != null) {//将模板通配符进行替换
				if (line.contains("${temp.file}")) {//控制文件所在
					line = line.replace("${temp.file}",dataFile);
				}
				if (line.contains("${temp.data}")) {//坏文件所在后缀换名
						line = line.replace("${temp.data}", dataFile.replace(loadData.getSuffer(), ".dat"));		
				}
				if (line.contains("${temp.table}")) {//表明
					line = line.replace("${temp.table}", tableName);		
				}
				if (line.contains("${temp.sep}")) {//分割符
					line = line.replace("${temp.sep}", loadData.getSep());//分隔符		
				}
				if (line.contains("${temp.taskid}")) {//分割符
					line = line.replace("${temp.taskid}", loadData.getTaskid());//分隔符		
				}
				bw.write(line + "\n");
				line = br.readLine();
			}
			bw.close();
			br.close();
			}catch(Exception e){
				e.getMessage();
				log.error("模板转换出错："+e.getMessage());
			}
			return temp.getPath();
	}
	
	*//**
	 * 删除临时的ctl文件,如果存在的话
	 *//*
	public void clearTempCTLFile(List<String> pathList) {
		if (pathList.isEmpty())
			return;
		for (String path : pathList) {
			File f = new File(path);
			if (f.exists()) {
				f.delete();
			}
		}
		pathList.clear();
	}
}*/
