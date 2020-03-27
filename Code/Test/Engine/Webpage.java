import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class insert
{
	public static void main(String[] args) 
	{
		try
		{
			FileInputStream file = new FileInputStream(new File("alphafiltered.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/projdb","root","");
			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			
			while (rowIterator.hasNext()) 
			{
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				int i=0;
				String pcat=null,scat=null,keyword=null;
				int cat=0;
				while (cellIterator.hasNext()) 
				{
					Cell cell = cellIterator.next();
					//Check the cell type and format accordingly
					if(i==0)
						pcat=cell.getStringCellValue();
					else if(i==1)
						scat=cell.getStringCellValue();
					else if(i==2)
					{
						keyword=cell.getStringCellValue();
						PreparedStatement st=con.prepareStatement("insert into webpage(Pcategory,Scategory,Keyword) values(?,?,?)");
						st.setString(1,pcat);
						st.setString(2,scat);
						st.setString(3,keyword);
						int count=st.executeUpdate();
						System.out.println(count);
					}
					i++;
				}
				System.out.println("");
			}
			file.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
