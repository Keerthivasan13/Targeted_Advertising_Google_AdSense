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
public class webpageKeywordExtract 
{
	private static final int MYTHREADS = 500;
	public static Map<String, Object[]> data = new TreeMap<String, Object[]>();
	public static int j=0;
	public static void main(String[] args) 
	{
		try
		{
			ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
			FileInputStream file = new FileInputStream(new File("webpages2.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Create a blank sheet
			XSSFWorkbook work = new XSSFWorkbook(); 
			XSSFSheet sh = work.createSheet("Data");
			
			
			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) 
			{	
				Runnable worker = new Runn(rowIterator.next());
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
				    FileOutputStream out = new FileOutputStream(new File("webpage2-key.xlsx"));
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
				
class Runn implements Runnable {
	private final Row row;

	Runn(Row row) {
		this.row = row;
	}

	@Override
	public void run() {
		//For each row, iterate through all the columns
		Iterator<Cell> cellIterator = row.cellIterator();
		int i=0; 
        String cat = null;
		while (cellIterator.hasNext()) 
		{
			i++;
			Cell cell = cellIterator.next();	
			  if(i==1)
				  cat=cell.getStringCellValue();
			  if(i==2)
          	{
          	try{         		
          		   Document doc = Jsoup.connect(cell.getStringCellValue()).get();      
                   String keywords = doc.select("meta[name=keywords]").first().attr("content");  
                   StringTokenizer st = new StringTokenizer(keywords,",");
                   while (st.hasMoreTokens()) {  
                	   String temp=st.nextToken();
                	   if(temp!=null&&temp!=" ")
                	   {               		   
                		   webpageKeywordExtract.data.put(String.valueOf(webpageKeywordExtract.j++), new Object[] {cat,temp});
                	   }
                   }
                   System.out.println(webpageKeywordExtract.j);
          	}
           catch(Exception e)
           {}
                   
           }
              
			//Check the cell type and format accordingly
			
		}
		
	}
	
}
