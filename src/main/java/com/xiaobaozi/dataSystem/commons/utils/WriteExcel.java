//package com.xiaobaozi.dataSystem.commons.utils;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DateFormat;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//
///**
// * 读取Excel中的内容
// * @author xubiao--2014-06-09
// *
// */
//public class WriteExcel {
//	 /**
//     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
//     * @return 读出的Excel中数据的内容
//     * @throws FileNotFoundException
//     * @throws IOException
//     */
//
//    public static String[][] getData(InputStream ins, int ignoreRows)
//           throws FileNotFoundException, IOException {
//       List<String[]> result = new ArrayList<String[]>();
//       int rowSize = 0;
//       /*// 打开XSSFWorkbook
//       if(file==null){
//    	   return null;
//       }*/
//       BufferedInputStream in=new BufferedInputStream(ins);
//       XSSFWorkbook  wb = new XSSFWorkbook(in);
//       XSSFCell cell = null;
//       //得到有多少sheet页
//       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
//           XSSFSheet st = wb.getSheetAt(sheetIndex);
//           //ignoreRows为忽视的行数第一行为标题，不取
//           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
//              XSSFRow row = st.getRow(rowIndex);
//              //第一列值不能为空
//              if (row == null) {
//                  continue;
//              }
//              int tempRowSize = row.getLastCellNum();
//              if (tempRowSize > rowSize) {//判断第一列是否为空
//                  rowSize = tempRowSize;
//              }
//              String[] values = new String[rowSize];//每行的列
//              Arrays.fill(values, "");//为每列的值填充数据
//              boolean hasValue = false;
//              for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
//                  String value = "";
//                  cell = row.getCell(columnIndex);//得到每行的列数据
//                  if (cell != null) {
//                     // 注意：一定要设成这个，否则可能会出现乱码
//                    //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//                     switch (cell.getCellType()) {//根据不同的类型得到不同类型的值
//                     case XSSFCell.CELL_TYPE_STRING:
//                         value = cell.getStringCellValue();
//                         break;
//                     case XSSFCell.CELL_TYPE_NUMERIC:
//                    	 if (HSSFDateUtil.isCellDateFormatted(cell)) {//在判断是否是日期类型
//                    		 Date d = cell.getDateCellValue();
//                    		 DateFormat formater1 = new SimpleDateFormat("yyyy-MM-dd");
//                    		 value=formater1.format(d);
//                    	 }else{
//                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
//                    	 }
//                         break;
//                     case XSSFCell.CELL_TYPE_FORMULA:
//                         // 导入时如果为公式生成的数据则无值
//                         if (!cell.getStringCellValue().equals("")) {
//                            value = cell.getStringCellValue();
//                         } else {
//                            value = cell.getNumericCellValue() + "";
//                         }
//                         break;
//                     case XSSFCell.CELL_TYPE_BLANK:
//                    	 break;
//                     case XSSFCell.CELL_TYPE_ERROR:
//                         value = "";
//                         break;
//                     case XSSFCell.CELL_TYPE_BOOLEAN:
//                         value = (cell.getBooleanCellValue() == true ? "Y": "N");
//                         break;
//                     default:
//                         value = "";
//                     }
//                  }
//                  //---------------------工贸540为空也进行写入
//                  /*if (columnIndex == 0 && value.trim().equals("")) {
//                     break;
//                  }*/
//                  values[columnIndex] = rightTrim(value);
//                  hasValue = true;
//              }
//              if (hasValue) {
//            	  result.add(values);
//              }
//           }
//       }
//       in.close();
//       String[][] returnArray = new String[result.size()][rowSize];
//       if(returnArray==null||returnArray.length==0){
//    	  return null; 
//       }
//       for (int i = 0; i < returnArray.length; i++) {
//           returnArray[i] = (String[]) result.get(i);
//       }
//       return returnArray;
//    }
//    /**
//
//     * 去掉字符串右边的空格
//
//     * @param str 要处理的字符串
//
//     * @return 处理后的字符串
//
//     */
//
//     public static String rightTrim(String str) {
//       if (str == null) {
//           return "";
//       }
//       int length = str.length();
//       for (int i = length - 1; i >= 0; i--) {
//           if (str.charAt(i) != 0x20) {
//              break;
//           }
//           length--;
//       }
//       return str.substring(0, length);
//    }
//    /* public static void main(String[] args) throws Exception {
//        File file = new File("D:\\保单模板.xlsx");
//         String[][] result = getData(file, 1);
//         int rowLength = result.length;
//         for(int i=0;i<rowLength;i++) {
//             for(int j=0;j<result[i].length;j++) {
//                System.out.print(result[i][j]+"\t\t");
//             }
//             System.out.println();
//
//         }
//      }*/
//}
