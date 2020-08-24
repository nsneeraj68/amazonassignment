package Frameworks.DataDrivenFramework;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
public class FirstAutomateProgram {
WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    @BeforeTest
public void initialization(){
    // To set the path of the Firefox driver.
System.setProperty("webdriver.gecko.driver", "C:\\Users\\Neeraj Sharma\\eclipse-workspace\\DataDrivenFramework\\driver\\geckodriver.exe");
driver = new FirefoxDriver();
    
    // To launch facebook
    driver.get("https://mc.s11.exacttarget.com/cloud/#app/Email");
    // To maximize the browser
    driver.manage().window().maximize();
    // implicit wait
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
  
@Test
public void SalesforceLoginLogout() throws IOException{
// Import excel sheet.
File src=new File("C:\\Users\\Neeraj Sharma\\eclipse-workspace\\DataDrivenFramework\\Excel\\TestData.xlsx");   
// Load the file.
FileInputStream fis = new FileInputStream(src);
// Load he workbook.
workbook = new XSSFWorkbook(fis);
// Load the sheet in which data is stored.
sheet= workbook.getSheetAt(0);
for(int i=1; i<=sheet.getLastRowNum(); i++){
/*I have added test data in the cell A2 as "testemailone@test.com" and B2 as "password"
Cell A2 = row 1 and column 0. It reads first row as 0, second row as 1 and so on
and first column (A) as 0 and second column (B) as 1 and so on*/
// Import data for Email.
cell = sheet.getRow(i).getCell(0);
cell.setCellType(Cell.CELL_TYPE_STRING);
driver.findElement(By.id("username")).clear();
driver.findElement(By.id("username")).sendKeys(cell.getStringCellValue());
driver.findElement(By.id("submit-btn")).click();
// Import data for password.
cell = sheet.getRow(i).getCell(1);
cell.setCellType(Cell.CELL_TYPE_STRING);
driver.findElement(By.id("password")).clear();         
driver.findElement(By.id("password")).sendKeys(cell.getStringCellValue());
// To click on Login button
driver.findElement(By.id("submit-btn")).click();
// To click on Account settings dropdown
/*driver.findElement(By.xpath("//div[text()='Account Settings']")).click();
// To click on logout button
driver.findElement(By.xpath("//text()[.='Log Out']/ancestor::span[1]")).click();*/
}
}
}