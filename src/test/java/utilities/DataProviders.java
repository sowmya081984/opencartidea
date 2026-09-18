package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path=".\\testData\\Opencart_TestData.xlsx";
		ExcelUtility excel=new ExcelUtility(path);
		
		int totalrows=excel.getRowCount("Sheet1");
		int totalcols=excel.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0; j<totalcols;j++) {
				logindata[i-1][j]=excel.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}

}
