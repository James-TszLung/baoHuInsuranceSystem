package com.xiaobaozi.commons.utils;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 
 * @author xubiao ---2014-10-20
 *
 */
public class ExcelUtil {
	/**
	 * 将List<Object[]>导出数据为excel
	 * @param title sheet页标题
	 * @param headers 表头
	 * @param objectList 显示的数据集合
	 * @param out 返回的流
	 * @param columnIndexs 显示列（从0开始计算）
	 */
	public void exportExcel(String title, String[] headers,Collection<Object[]> objectList, OutputStream out, int[] columnIndexs) {
	      // 声明一个工作薄
		  Workbook workbook = new SXSSFWorkbook(1000);//SXSSFWorkbook解决内存溢出问题  内存中存放1000条数据
	      // 生成一个表格
	      Sheet sheet = workbook.createSheet(title);
	      //冻结首行
	      sheet.createFreezePane( 0, 1, 0, 1 ); 
	      // 设置表格默认列宽度为15个字节
	      sheet.setDefaultColumnWidth((short) 15);
	      /************************************************表头样式************************************************/
	      // 生成一个样式
	      CellStyle style = workbook.createCellStyle();
	      // 设置样式
	      style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	      style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);//背景颜色设置
	      style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	      style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	      style.setBorderRight(XSSFCellStyle.BORDER_THIN);
	      style.setBorderTop(XSSFCellStyle.BORDER_THIN);
	      style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	      style.setWrapText( true );
	      // 生成一个字体
	      Font font = workbook.createFont();
	      font.setFontHeightInPoints((short) 12);
	      font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
	      // 把字体应用到当前的样式
	      style.setFont(font);
	      /************************************************数据样式************************************************/
	      // 生成并设置另一个样式
	      CellStyle style2 = workbook.createCellStyle();
	      style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	      style2.setFillForegroundColor(HSSFColor.WHITE.index);//背景颜色设置
	      style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
	      style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
	      style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
	      style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
	      style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	      style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
	      // 生成另一个字体
	      Font font2 = workbook.createFont();
	      font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
	      // 把字体应用到当前的样式
	      style2.setFont(font2);
	      /************************************************表头************************************************/
	         if(headers!=null&&headers.length>0){
		      //产生表格标题行，自动添加第一列为序号列
	    	  Row  row = sheet.createRow(0);
		      Cell cell =row.createCell(0);
		      cell.setCellValue(new XSSFRichTextString("序列"));
		      cell.setCellStyle(style);
		      for (short i = 0; i < headers.length; i++) {
		         Cell cell1 = row.createCell(i+1);
		         cell1.setCellStyle(style);
		         cell1.setCellValue(headers[i].toString());
		      }
	      }
	      /************************************************数据显示************************************************/
	     //写数据
	      if(objectList!=null){
	      //遍历集合数据，产生数据行
	    	  Iterator<Object[]> it = objectList.iterator();	      
		      int index = 0;
		      while (it.hasNext()) {
		    	 Object[] objects = it.next();
		         index++;
		         Row row = sheet.createRow(index);
		         //添加一列,写序号
		         Cell cell=row.createCell(0);
		         cell.setCellStyle(style2);
		         cell.setCellValue(index);
		         //无论是全量导出还是选择性导出,都默认在第一列加序号
		         if(columnIndexs!=null&&columnIndexs.length>0){//选择导出
			         for (int i = 0; i < columnIndexs.length; i++) {
			        	 Cell cell1 = row.createCell(i+1);
			        	 cell1.setCellStyle(style2);
			        	 cell1.setCellValue(objects[columnIndexs[i]]!=null?objects[columnIndexs[i]].toString():"");
					 }
		         }else{//全量导出
		        	 for (int i = 0; i < objects.length; i++) {
		        		 Cell cell1 = row.createCell(i+1);
			        	 cell1.setCellStyle(style2);
			        	 cell1.setCellValue(objects[i]!=null?objects[i].toString():"");
					}
		         }
		      }
		  }
		try {
			// 将工作簿写入流中
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 取Excel日期单元格数据
	 * @param row 行
	 * @param index 列序号
	 * @return
	 */
	public static Date getDateCellValue(Row row, int index) {
		Cell cell = row.getCell(index);
		if (cell == null) {
			return null;
		}
		return cell.getDateCellValue();
	}
	
	/**
	 * 取Excel文本单元格数据
	 * @param row 行
	 * @param index 列序号
	 * @return
	 */
	public static String getStringCellValue(Row row, int index) {
		Cell cell = row.getCell(index);
		if (cell == null) {
			return null;
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);  // 防止单元格格式设置不对
		return cell.getStringCellValue();
	}
	
	
	/**
	 * 取Excel文本单元格整数数据
	 * @param row 行
	 * @param index 列序号
	 * @return
	 */
	public static Integer getIntegerCellValue(Row row, int index) {
		Cell cell = row.getCell(index);
		if (cell == null) {
			return 0;
		}
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);	// 防止单元格格式设置不对
		return (int)cell.getNumericCellValue();
	}
	
	/**
	 * 取Excel文本单元格小数数据
	 * @param row 行
	 * @param index 列序号
	 * @return
	 */
	public static double getDoubleCellValue(Row row, int index) {
		Cell cell = row.getCell(index);
		if (cell == null) {
			return 0;
		}
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);	// 防止单元格格式设置不对
		return cell.getNumericCellValue();
	}
	
	public static double formatDiscount(double discount, boolean f){
		double n  = 0;
		if(f){
			n = discount * 10; 
		}else{
			n = discount / 10;
		}
		BigDecimal bg = new BigDecimal(n);
        n = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return n;
	}
	/**
	 * 自定义表头和数据
	 * @param obj
	 * @param finance
	 * @return
	 */
	public static SXSSFWorkbook writeHead(List<Object> obj,List<Object[]> finance) {
		SXSSFWorkbook writeWB = new SXSSFWorkbook(500);
		Sheet writeSheet = writeWB.createSheet("报表核对下载");
		// 设置表格默认列宽度为15个字节
		writeSheet.setDefaultColumnWidth((short) 15);
		// 表头设置
		CellStyle style = writeWB.createCellStyle();
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.YELLOW.index);// 背景颜色设置
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 生成表头字体
		Font font = writeWB.createFont();
		font.setFontHeightInPoints((short) 15);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到表头样式
		style.setFont(font);
		// ----------------------

		//字体样式
		CellStyle style2 = writeWB.createCellStyle();
		style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style2.setFillForegroundColor(HSSFColor.WHITE.index);// 背景颜色设置
		style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style2.setWrapText(true);// 自动换行
		// 生成另一个字体
		Font font2 = writeWB.createFont();
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		//-------------------------------------
		// ------开始写表头-----------------
		Row writeRowFrist = writeSheet.createRow(0);// 第一行
		Row writeRowtwo = writeSheet.createRow(1);// 第二行
		int countCell=0;//总列
		for (int i = 0; i < obj.size(); i++) {
			// 如果类型为String则占两行，一列，为MAP类型为一行多列
			Object ob = obj.get(i);
			if (ob instanceof String) {// 两行一列
				writeSheet.addMergedRegion(new CellRangeAddress(0, 1, countCell, countCell));// 合并单元格
				Cell cellFrist = writeRowFrist.createCell(countCell);
				writeRowtwo.createCell(countCell);
				cellFrist.setCellValue(ob.toString());
				writeRowFrist.getCell(countCell).setCellStyle(style);
				writeRowFrist.getCell(countCell).setCellStyle(style);
				writeRowtwo.getCell(countCell).setCellStyle(style);
				writeRowtwo.getCell(countCell).setCellStyle(style);
				countCell++;
			} else if (ob instanceof Map) {// MAP类型为多列一行
				Map map = (Map) ob;
				Iterator it = map.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					String[] title = map.get(key) == null ? null : map.get(key)
							.toString().split("\\,");
					int m = title == null ? 0 : title.length;
					writeSheet.addMergedRegion(new CellRangeAddress(0, 0, countCell, countCell
							+ m-1));// 合并单元格
					Cell cellFrist = writeRowFrist.createCell(countCell);
					writeRowFrist.getCell(countCell).setCellStyle(style);
					writeRowFrist.getCell(countCell).setCellStyle(style);
					cellFrist.setCellValue(key);
					for(int f=1;f<m;f++){
						writeRowFrist.createCell(countCell+f);
						writeRowFrist.getCell(countCell+f).setCellStyle(style);
						writeRowFrist.getCell(countCell+f).setCellStyle(style);
					}
					// 写Map下的值
					for (int j = 0; j < title.length; j++) {
						Cell celltwo = writeRowtwo.createCell(countCell+j);
						celltwo.setCellValue(title[j]);
						writeRowtwo.getCell(countCell+j).setCellStyle(style);
						writeRowtwo.getCell(countCell+j).setCellStyle(style);
					}
					countCell+=title.length;
				}
			}
		}
		// ---------------表头写结束------------------------
		//开始写数据
		int i=2;//初始化行数数据
		if(finance!=null&&finance.size()>0){
			for(int j=0;j<finance.size();j++){
			    Row row=writeSheet.createRow(i+j);
			    //写字段数据开始
			    for(int m=0;m<finance.get(j).length;m++){
			    	Object objData=finance.get(j)[m];
			    	Cell cell=row.createCell(m);
			    	cell.setCellValue(objData==null?"":objData.toString());
			    	//写样式
			    	cell.setCellStyle(style2);
			    }
			    //------------------
			}
		}
		return writeWB;
	}
}
