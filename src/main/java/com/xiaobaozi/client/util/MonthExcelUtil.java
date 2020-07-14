//package com.xiaobaozi.client.util;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Collection;
//import java.util.Iterator;
//
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFRichTextString;
///**
// * 
// * @author yangsong
// *
// */
//public class MonthExcelUtil {
//	/**
//	 * 将List<Object[]>导出数据为excel
//	 * @param title sheet页标题
//	 * @param headers 表头
//	 * @param objectList 显示的数据集合
//	 * @param out 返回的流
//	 * @param columnIndexs 显示列（从0开始计算）
//	 */
//	public void exportExcel(String title, String[] headers,Collection<Object[]> objectList, OutputStream out, int[] columnIndexs) {
//	      // 声明一个工作薄
//		  Workbook workbook = new SXSSFWorkbook(1000);//SXSSFWorkbook解决内存溢出问题  内存中存放1000条数据
//	      // 生成一个表格
//	      Sheet sheet = workbook.createSheet(title);
//	      //冻结首行
//	      sheet.createFreezePane( 0, 1, 0, 1 ); 
//	      // 设置表格默认列宽度为15个字节
//	      sheet.setDefaultColumnWidth((short) 15);
//	      /************************************************表头样式************************************************/
//	      // 生成一个样式
//	      CellStyle style = workbook.createCellStyle();
//	      // 设置样式
//	      style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//	      style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);//背景颜色设置
//	      style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//	      style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//	      style.setBorderRight(XSSFCellStyle.BORDER_THIN);
//	      style.setBorderTop(XSSFCellStyle.BORDER_THIN);
//	      style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//	      style.setWrapText( true );
//	      // 生成一个字体
//	      Font font = workbook.createFont();
//	      font.setFontHeightInPoints((short) 12);
//	      font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
//	      // 把字体应用到当前的样式
//	      style.setFont(font);
//	      /************************************************数据样式************************************************/
//	      // 生成并设置另一个样式
//	      CellStyle style2 = workbook.createCellStyle();
//	      style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
//	      style2.setFillForegroundColor(HSSFColor.WHITE.index);//背景颜色设置
//	      style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//	      style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//	      style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
//	      style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
//	      style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//	      style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//	      // 生成另一个字体
//	      Font font2 = workbook.createFont();
//	      font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
//	      // 把字体应用到当前的样式
//	      style2.setFont(font2);
//	      /************************************************表头************************************************/
//	         if(headers!=null&&headers.length>0){
//		      //产生表格标题行，自动添加第一列为序号列
//	    	  Row  row = sheet.createRow(0);
//		      Cell cell =row.createCell(0);
//		      cell.setCellValue(new XSSFRichTextString("序列"));
//		      cell.setCellStyle(style);
//		      for (short i = 0; i < headers.length; i++) {
//		         Cell cell1 = row.createCell(i+1);
//		         cell1.setCellStyle(style);
//		         cell1.setCellValue(headers[i].toString());
//		      }
//	      }
//	      /************************************************数据显示************************************************/
//	     
//	      float outpriceTotal = 0;//票面价
//	      float tpfTotal = 0;//退票费
//	      float taxfeeTotal = 0 ;//基建
//	      float yqTotal = 0 ;//燃油
//	      float agtTotal =0;//应收
//	      float recTotal =0;//实收
//	      float qkmoneys = 0;//欠款
//	      int data[]={19,20,21,22,27,30,31};
//	    //写数据
//	      if(objectList!=null){
//	      //遍历集合数据，产生数据行
//	    	  Iterator<Object[]> it = objectList.iterator();	      
//		      int index = 0;
//		      while (it.hasNext()) {
//		    	 Object[] objects = it.next();
//		         index++;
//		         Row row = sheet.createRow(index);
//		         //添加一列,写序号
//		         Cell cell=row.createCell(0);
//		         cell.setCellStyle(style2);
//		         cell.setCellValue(index);
//		         //无论是全量导出还是选择性导出,都默认在第一列加序号
//		         if(columnIndexs!=null&&columnIndexs.length>0){//选择导出
//			         for (int i = 0; i < columnIndexs.length; i++) {
//			        	 Cell cell1 = row.createCell(i+1);
//			        	 cell1.setCellStyle(style2);
//			        	 cell1.setCellValue(objects[columnIndexs[i]]!=null?objects[columnIndexs[i]].toString():"");
//					 }
//		         }else{//全量导出
//		        	 for (int i = 0; i < objects.length; i++) {
//		        		 Cell cell1 = row.createCell(i+1);
//			        	 cell1.setCellStyle(style2);
//			        	 cell1.setCellValue(objects[i]!=null?objects[i].toString():"");
//			        	 if(i==19){
//			        		 outpriceTotal = outpriceTotal+(objects[i]==null?0:Float.parseFloat(objects[i].toString()));
//			        	 }else if(i==22){
//			        		 tpfTotal = tpfTotal+((objects[i]==null||objects[i].toString().equals(""))?0:Float.parseFloat(objects[i].toString()));
//			        	 }else if(i==21){
//			        		 taxfeeTotal = taxfeeTotal+((objects[i]==null||objects[i].toString().equals(""))?0:Float.parseFloat(objects[i].toString()));
//			        	 }else if(i==22){
//			        		 yqTotal = yqTotal+((objects[i]==null||objects[i].toString().equals(""))?0:Float.parseFloat(objects[i].toString()));
//			        	 }else if(i==27){
//			        		 agtTotal = agtTotal+((objects[i]==null||objects[i].toString().equals(""))?0:Float.parseFloat(objects[i].toString()));
//			        	 }else if(i==30){
//			        		 recTotal = recTotal+((objects[i]==null||objects[i].toString().equals(""))?0:Float.parseFloat(objects[i].toString()));
//			        	 }else if(i==31){
//			        		 qkmoneys = qkmoneys+((objects[i]==null||objects[i].toString().equals(""))?0:Float.parseFloat(objects[i].toString()));
//			        	 }
//					}
//		         }
//		         
//		      }
//		      	//求合计
//				Row row = sheet.createRow(index+1);
//				Cell cell=row.createCell(0);
//				cell.setCellValue("合计");
//				for (int i = 0; i < data.length; i++) {
//					Cell cellSum = row.createCell(data[i]+1);
//					 if(i==0){
//						 cellSum.setCellValue(outpriceTotal);
//		        	 }else if(i==1){
//		        		 cellSum.setCellValue(tpfTotal);
//		        	 }else if(i==2){
//		        		 cellSum.setCellValue(taxfeeTotal);
//		        	 }else if(i==3){
//		        		 cellSum.setCellValue(yqTotal);
//		        	 }else if(i==4){
//		        		 cellSum.setCellValue(agtTotal);
//		        	 }else if(i==5){
//		        		 cellSum.setCellValue(recTotal);
//		        	 }else if(i==6){
//		        		 cellSum.setCellValue(qkmoneys);
//		        	 }
//				}
//		  }
//		try {
//			// 将工作簿写入流中
//			workbook.write(out);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (out != null) {
//					out.flush();
//					out.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
