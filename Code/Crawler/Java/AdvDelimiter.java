package com.dataset.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.StringTokenizer;  

public class AdvDelimiter 
{
	public static void main(String[] args) 
	{
		try
		{
			FileInputStream file = new FileInputStream(new File("newadv.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Create a blank sheet
			XSSFWorkbook work = new XSSFWorkbook(); 
			XSSFSheet sh = work.createSheet("Data");
			Map<String, Object[]> data = new TreeMap<String, Object[]>();

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();	int j=0;	
			while (rowIterator.hasNext()) 
			{	
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				int i=0; 
                int id=0;
                int cat = 0;
				while (cellIterator.hasNext()) 
				{
					i++;
					Cell cell = cellIterator.next();
					if(i==2)
						cat=(int) cell.getNumericCellValue();
					  if(i==3)
                  	{
                  	try{
                  		
                  		   
                           StringTokenizer st = new StringTokenizer(cell.getStringCellValue(),"+");
                           while (st.hasMoreTokens()) {  
                        	   String temp=st.nextToken();
                        	   if(temp!=null&&temp!=" ")
                        	   data.put(String.valueOf(j++), new Object[] {j,temp,cat});
                           }  
                           
                  	}
                           catch(Exception e)
                           {}
                           
                   }
                      
					//Check the cell type and format accordingly
					
				}
				System.out.println("");
			}

			file.close();
			//Iterate over data and write to sheet
			Set<String> keyset = data.keySet();
			int rownum = 0;
			for (String key : keyset)
			{
			    Row roww = sh.createRow(rownum++);
			    Object [] objArr = data.get(key);
			    int cellnum = 0;
			    for (Object obj : objArr)
			    {
			       Cell cel = roww.createCell(cellnum++);
			       if(obj instanceof String)
			            cel.setCellValue((String)obj);
			        else if(obj instanceof Integer)
			            cel.setCellValue((Integer)obj);

					
			    }
			}
			try 
			{
				System.out.println(work);
				//Write the workbook in file system
			    FileOutputStream out = new FileOutputStream(new File("newad.xlsx"));
			    work.write(out);
			    System.out.println("FInished");
			    out.close();
			    
			       
			} 
			catch (Exception e) 
			{
			}
		
						
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
