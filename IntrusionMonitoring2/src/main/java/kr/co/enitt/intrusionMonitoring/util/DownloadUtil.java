package kr.co.enitt.intrusionMonitoring.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DownloadUtil {

	static final Logger logger = LoggerFactory.getLogger(DownloadUtil.class);
	
	public static void ecxelDown(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, List<?> list, String voYn)  throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet   sheet = workbook.createSheet(ObjectUtil.toString(map.get("sheetName"),"")); //시트명 설정
    	XSSFRow   row = null;
    	XSSFCell  cell = null;
        
        
        Map<String, Object>  slist = new HashMap<String, Object>();
    	String titleArray[] = ObjectUtil.toString(map.get("title"),"").split(",");
    	String cellArray[] = ObjectUtil.toString(map.get("cell"),"").split(",");
        
        
    	for(int i= 0; i < list.size(); i++) {
    		if(voYn != null && voYn.equals("N")) {
    			slist = (Map<String, Object>) list.get(i);
    		}else {
    			slist = BeanUtils.describe(list.get(i));
    		}
			
    		if(i == 0) {
				row = sheet.createRow(i); // 타이틀 행 생성
	    		for(int j = 0; j < titleArray.length;  j++) {
    				cell = row.createCell(j); //타이틀 열 생성
    				cell.setCellValue(titleArray[j]); // 타이틀 열 생성 후 입력
    				cell.setCellStyle(cellStyle(workbook, "head"));  //열 스타일 적용 
    				sheet.setColumnWidth(j, 5000);

	    		}	
    		}
    		
    		row = sheet.createRow(i+1); // 행 생성
    		for(int j = 0; j < cellArray.length;  j++) {
    			cell = row.createCell(j); //열 생성
    			cell.setCellValue(ObjectUtil.toString(slist.get(ObjectUtil.toString(cellArray[j],"")),"")); //열 생성 후 입력
				cell.setCellStyle(cellStyle(workbook, "data")); //열 스타일 적용
    		}
    	}
    	
    	String strClient = request.getHeader("User-Agent");
    	 
    	DateFormat dateF = new SimpleDateFormat("yyyyMMddhhmmss");
		Date nDate = new Date();
		String date = dateF.format(nDate);
		String fileName = ObjectUtil.toString(map.get("fileName"),"")+"_"+ date+ ".xlsx";
		fileName = new String ( fileName.getBytes("utf-8"), "8859_1");
    	
    	if (strClient.indexOf("MSIE 5.5") > -1) {
    	    response.setHeader("Content-Disposition", "filename=" + fileName + ";");
    	} else {
			response.setHeader("Content-disposition","attachment;filename="+ fileName );
			response.setHeader("Content-Type", "application/vnd.ms-excel; charset=MS949");
			response.setHeader("Content-Description", "JSP Generated Data"); 
			response.setHeader("Content-Transfer-Encoding", "binary;"); 
			 response.setHeader("Pragma", "no-cache;"); 
    	}
    	
    	 
	     OutputStream fileOut = null;
         fileOut = response.getOutputStream();
         workbook.write(fileOut);
	     fileOut.flush();
	     fileOut.close();
	}

	public static void ecxelDownList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, List<?> list)  throws Exception {
		XSSFWorkbook  workbook = new XSSFWorkbook();
	  	XSSFSheet   sheet = workbook.createSheet(ObjectUtil.toString(map.get("sheetName"),"")); //시트명 설정
	  	XSSFRow   row = null;
	  	XSSFCell  cell = null;

        List<String> headerList = (List<String>) map.get("headerList");
        List<Integer> headerCellList = (List<Integer>) map.get("headerCellList");
        List<String> titleList = (List<String>) map.get("titleList");
        List<String> cellList = (List<String>) map.get("cellList");
        
        Map<String, Object>  slist = new HashMap<String, Object>();
        int rowCnt = 0;
        int cellCntStart = 0;
        int cellCntEnd = 0;
        OutputStream fileOut = null;
        if(headerCellList != null) {
        	if(headerCellList.size() > 0) {
    			row = sheet.createRow(rowCnt); // 타이틀 행 생성
    			rowCnt++;
    			for(int i = 0; i < headerCellList.size();  i++) {
    				cellCntEnd = headerCellList.get(i);
    				cell = row.createCell(cellCntStart);
    				cell.setCellValue(headerList.get(i)); // 타이틀 열 생성 후 입력
    				cell.setCellStyle(cellStyle(workbook, "head"));  //열 스타일 적용 
    				sheet.setColumnWidth(i, 5000);
    				if(cellCntStart < cellCntEnd) {
    					sheet.addMergedRegion(new CellRangeAddress(
    							0, //first row index in zero-based
    							0, //last row index in zero-based
    							cellCntStart, //first column index in zero-based
    							cellCntEnd  //last column index in zero-based
    			        ));
    				}
    				cellCntStart  = cellCntEnd+1;
    			}
    		}
        }
        
        if(titleList != null) {
			if(titleList.size() > 0) {
				row = sheet.createRow(rowCnt); // 타이틀 행 생성
				rowCnt++;
	    		for(int i = 0; i < titleList.size();  i++) {
					cell = row.createCell(i); //타이틀 열 생성
					cell.setCellValue(titleList.get(i)); // 타이틀 열 생성 후 입력
					cell.setCellStyle(cellStyle(workbook, "head"));  //열 스타일 적용 
					sheet.setColumnWidth(i, 5000);
	    		}	
			}
        }
        
    	for(int i= 0; i < list.size(); i++) {
    		slist = BeanUtils.describe(list.get(i));
    		row = sheet.createRow(rowCnt); // 행 생성
    		
    		for(int j = 0; j < cellList.size();  j++) {
    			cell = row.createCell(j); //열 생성
    			cell.setCellValue(ObjectUtil.toString(slist.get(ObjectUtil.toString(cellList.get(j),"")),"")); //열 생성 후 입력
				cell.setCellStyle(cellStyle(workbook, "data")); //열 스타일 적용
    		}
    		
    		rowCnt++;
    	}
    	
    	String strClient = request.getHeader("User-Agent");
    	 
    	DateFormat dateF = new SimpleDateFormat("yyyyMMddhhmmss");
		Date nDate = new Date();
		String date = dateF.format(nDate);
		String fileName = ObjectUtil.toString(map.get("fileName"),"")+"_"+ date+ ".xlsx";
		fileName = new String ( fileName.getBytes("KSC5601"), "8859_1");
    	
    	if (strClient.indexOf("MSIE 5.5") > -1) {
    	    response.setHeader("Content-Disposition", "filename=" + fileName + ";");
    	} else {
    	    response.setContentType("application/vnd.ms-excel");
    	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
    	}
    	
        fileOut = response.getOutputStream();
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        workbook.close();
	}

	public static void ecxelDownStatus(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, Map<String, Object> data)  throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet   sheet = workbook.createSheet(ObjectUtil.toString(map.get("sheetName"),"")); //시트명 설정
    	XSSFRow   row = null;
    	XSSFCell  cell = null;
        
        
        Map<String, Object>  slist = new HashMap<String, Object>();
        List<String> categories = (List<String>) data.get("categories");
        List<String> eventNames = (List<String>) data.get("eventNames");
        List<HashMap<String, Object>> dateData = (List<HashMap<String, Object>>) data.get("data");
        ArrayList<Integer> rowData = new ArrayList<Integer>();
        
    	row = sheet.createRow(0); // 타이틀 행 생성
    	cell = row.createCell(0);
    	cell.setCellValue("일자");
    	cell.setCellStyle(cellStyle(workbook, "head")); 
    	sheet.setColumnWidth(0, 5000);
		for(int j = 0; j < eventNames.size();  j++) {
			cell = row.createCell(j+1); //타이틀 열 생성
			cell.setCellValue(eventNames.get(j)); // 타이틀 열 생성 후 입력
			cell.setCellStyle(cellStyle(workbook, "head"));  //열 스타일 적용 
			sheet.setColumnWidth(j+1, 5000);
		}

		for(int i= 0; i < categories.size(); i++) {
			row = sheet.createRow(i+1); // 행 생성
			cell = row.createCell(0);
	    	cell.setCellValue(categories.get(i));
	    	cell.setCellStyle(cellStyle(workbook, "data")); //열 스타일 적용
	    	
			for(int j = 0; j < dateData.size(); j++) {
				cell = row.createCell(j+1); //타이틀 열 생성
				rowData = new ArrayList<Integer>();
				rowData = (ArrayList<Integer>) dateData.get(j).get("ListData");
				cell.setCellValue(rowData.get(i)); // 타이틀 열 생성 후 입력
				cell.setCellStyle(cellStyle(workbook, "data"));  //열 스타일 적용 
				sheet.setColumnWidth(j+1, 5000);
			}
		}
		
    	String strClient = request.getHeader("User-Agent");
    	 
    	DateFormat dateF = new SimpleDateFormat("yyyyMMddhhmmss");
		Date nDate = new Date();
		String date = dateF.format(nDate);
		String fileName = ObjectUtil.toString(map.get("fileName"),"")+"_"+ date+ ".xlsx";
		fileName = new String ( fileName.getBytes("utf-8"), "8859_1");
    	
    	if (strClient.indexOf("MSIE 5.5") > -1) {
    	    response.setHeader("Content-Disposition", "filename=" + fileName + ";");
    	} else {
			response.setHeader("Content-disposition","attachment;filename="+ fileName );
			response.setHeader("Content-Type", "application/vnd.ms-excel; charset=MS949");
			response.setHeader("Content-Description", "JSP Generated Data"); 
			response.setHeader("Content-Transfer-Encoding", "binary;"); 
			 response.setHeader("Pragma", "no-cache;"); 
    	}
    	
    	 
	     OutputStream fileOut = null;
         fileOut = response.getOutputStream();
         workbook.write(fileOut);
	     fileOut.flush();
	     fileOut.close();
	}
	
	private static CellStyle cellStyle(XSSFWorkbook workbook, String string) {
		Font defaultFont = workbook.createFont();   
		defaultFont.setFontHeightInPoints((short) 11);
		defaultFont.setFontName("맑은 고딕");


		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER); //가운데 정렬
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //중앙 정렬
		
		if(string.equals("head")) {
			cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); //회색 25%
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); //색상 패턴처리
		}else if(string.equals("data")) {
			cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex()); //흰색
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); //색상 패턴처리
		}
		
		return cellStyle;
	}
}
