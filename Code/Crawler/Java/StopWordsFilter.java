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

public class StopWordsFilter 
{
	public static void main(String[] args) 
	{
		try
		{
			FileInputStream file = new FileInputStream(new File("alpha.xlsx"));

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
			String stopWords[]={"for","no","to","that","top","of","or","uk","a","and","folder","file","in","small","free","the","v","*","d.","a.","p.",".com","j.","at","my","on","for"};
			while (rowIterator.hasNext()) 
			{	
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
                String pc = null,sc=null,key=null;
                int i=0;int z;
				while (cellIterator.hasNext()) 
				{
					i++;
					Cell cell = cellIterator.next();
					if(i==2)
                   	 pc= cell.getStringCellValue();
					if(i==3)
	                   	 sc= cell.getStringCellValue();
				    if(i==4)
                  	{
				    	key=cell.getStringCellValue();
                  		   for(z=0;z<stopWords.length;z++)
                  		   {
                  			   if(key.equals(stopWords[z]))
                  			   {
                  				 break;
                  			   }
                  		   }
                  		   if(!(z!=stopWords.length))
                  			   data.put(String.valueOf(j++), new Object[] {pc,sc,key});
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
			    FileOutputStream out = new FileOutputStream(new File("alphafiltered.xlsx"));
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
