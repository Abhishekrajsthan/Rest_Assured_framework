package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class get_data {
	
	public static ArrayList<String> get_excel_data(String test_sheet_name, String test_case_name) throws IOException
	{
		ArrayList<String> Array_data = new ArrayList<String>();
		FileInputStream Fis = new FileInputStream("C:\\Users\\Abhishek\\Desktop\\New folder\\Rest Assured\\RA\\test_data.xlsx"); // file locater
		XSSFWorkbook workbook = new XSSFWorkbook(Fis); // file acess
		int count = workbook.getNumberOfSheets();
		for(int i=0; i<count; i++)
		{
			String Sheetname = workbook.getSheetName(i); // fetch sheet name
			if (Sheetname.equalsIgnoreCase(test_sheet_name))
			{
				XSSFSheet sheet = workbook.getSheetAt(i); // acess perticular sheet
				Iterator<Row> row = sheet.iterator(); // to iterat row wise
				Row firstrow = row.next(); // iteratr start form -1 to come to 0 use this
				Iterator<Cell> cell = firstrow.cellIterator(); // cell wise iterat
				int j = 0;
				int tc_colloum = 0;
				while(cell.hasNext()) //read all cell value
				{
					Cell cellvalue = cell.next();
					if(cellvalue.getStringCellValue().equalsIgnoreCase("tc_name"))
					{
						tc_colloum = j;
					}
					j++; // to find exact value that we need in a loop
				}
				while (row.hasNext())
				{
					Row rowdata = row.next();
					if (rowdata.getCell(tc_colloum).getStringCellValue().equalsIgnoreCase(test_case_name))
					{
						Iterator<Cell> datacellvalue = rowdata.cellIterator();
						while(datacellvalue.hasNext())
						{
							Cell dataofcell  = datacellvalue.next();
							try 
							{
								String testData = dataofcell.getStringCellValue();
								Array_data.add(testData);
							}
							catch(IllegalStateException e)
							{
								int testdata = (int) dataofcell.getNumericCellValue();
								String SDRdata = Integer.toString(testdata);
								Array_data.add(SDRdata);
								
							}
						}	
					}
				}
			}
			
		}
		return Array_data;
	}

}
