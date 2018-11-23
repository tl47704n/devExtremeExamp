package com.weekly.home.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Repository
public class ExcelReader {
	ExcelReader(){}
	
	
	
	public JSONArray getexcel(String file_path) {
		
		try {
			 
			OPCPackage pkg = OPCPackage.open(new File(file_path));
		   // POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		    XSSFWorkbook wb = new XSSFWorkbook(pkg);
		    XSSFSheet sheet = wb.getSheetAt(0);
		    XSSFRow row;
		    XSSFCell cell;
		   
	
		    int rows; // No of rows
		    rows = sheet.getPhysicalNumberOfRows();
	
		    int cols = 0; // No of columns
		    int tmp = 0;
	
		    // This trick ensures that we get the data properly even if it doesn't start from first few rows
		    for(int i = 0; i < 10 || i < rows; i++) {
		        row = sheet.getRow(i);
		        if(row != null) {
		            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
		            if(tmp > cols) cols = tmp;
		        }
		    }
		    XSSFRow row_attr;
		    XSSFCell cell_name;
	    	row_attr = sheet.getRow(0);
	    	JSONArray res = new JSONArray();
	    	
		    for(int r = 1; r < rows; r++) {
		    	
		        row = sheet.getRow(r);
		        if(row != null) {
		        	JSONObject each_row = new JSONObject();
		            for(int c = 0; c < cols; c++) {
		                cell = row.getCell(c);
		                cell_name = row_attr.getCell(c);
		                if(cell != null) {
		                   if(cell_name.toString().equals("WO Completed Date")) {
		                	 each_row.put("Year", getymd(cell.toString(),"Year"));
		                	  each_row.put("Month", getymd(cell.toString(),"Month"));
		                	  each_row.put("Day", getymd(cell.toString(),"Day"));
		                   }else {
		                   each_row.put(cell_name.toString(), cell.toString());
		                   }
		                }
		            }
		            res.add(each_row);
		        }
		    }
		    return res;
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
	return null;
	
	}
	
	private String getymd(String param,String date) {
		if(param.length()<2) return "Developing";
		 String[] ymd= param.toString().split("-");
		 switch (date) {
         case "Year":  return ymd[0];
         case "Month":  return ymd[1];
         case "Day":  return ymd[2];
         default: return "Developing";
     }
	}
	/*public static void main(String[] args) {
		ExcelReader e = new ExcelReader();
		String res = e.getexcel("C://Users/tl477/Desktop/Manday Detail.xlsx").toJSONString();
		//System.out.println(res);
	}*/
}
