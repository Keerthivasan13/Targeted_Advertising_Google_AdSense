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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class KeywordExtract 
{
	private static final int MYTHREADS = 500;
	public static Map<String, Object[]> data = new TreeMap<String, Object[]>();
	public static int j=0;
	public static void main(String[] args) 
	{
		try
		{
			ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
			FileInputStream file = new FileInputStream(new File("howtodoinjava_demo_2_2.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Create a blank sheet
			XSSFWorkbook work = new XSSFWorkbook(); 
			XSSFSheet sh = work.createSheet("Data");
			
			
			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) 
			{	
				Runnable worker = new MyRun(rowIterator.next());
				executor.execute(worker);
			}
				executor.shutdown();
				// Wait until all threads are finish
				while (!executor.isTerminated()) {
		 
				}
				System.out.println("\nFinished all threads");
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
				    FileOutputStream out = new FileOutputStream(new File("test2.xlsx"));
				    work.write(out);
				    out.close();
				       
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
		}
		catch (Exception e) 
		{
		}
	}
}
				
class MyRun implements Runnable {
	private final Row row;

	MyRun(Row row) {
		this.row = row;
	}

	@Override
	public void run() {
		//For each row, iterate through all the columns
		Iterator<Cell> cellIterator = row.cellIterator();
		int i=0; 
        int id=0;
        String pc = null,sc=null;
		while (cellIterator.hasNext()) 
		{i++;
			Cell cell = cellIterator.next();
			if(i==1)
           	 id=(int) cell.getNumericCellValue();
			if(i==2)
				pc=cell.getStringCellValue();
			if(i==3)
				sc=cell.getStringCellValue();
			  if(i==5)
          	{
          	try{         		
          		   Document doc = Jsoup.connect(cell.getStringCellValue()).get();      
                   String keywords = doc.select("meta[name=keywords]").first().attr("content");  
                   StringTokenizer st = new StringTokenizer(keywords,",");
                   while (st.hasMoreTokens()) {  
                	   String temp=st.nextToken();
                	   if(temp!=null&&temp!=" ")
                	   {               		   
                		   KeywordExtract.data.put(String.valueOf(KeywordExtract.j++), new Object[] {id,pc,sc,temp});
                	   }
                   }
                   System.out.println(id);
          	}
           catch(Exception e)
           {}
                   
           }
              
			//Check the cell type and format accordingly
			
		}
		
	}
	
}
