package Frameworks.DataDrivenFramework;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit; 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
public class MeddoApointmentBooking {
WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    @BeforeTest
public void initialization(){
    // To set the path of the Firefox driver.
System.setProperty("webdriver.gecko.driver", "C:\\Users\\Neeraj Sharma\\eclipse-workspace\\DataDrivenFramework\\driver\\geckodriver.exe");
driver = new FirefoxDriver();

//Click on signin buton
driver.findElement(By.xpath("//button[@class='jss117 btn-outline-light']")).click();
driver.findElement(By.id("mobile")).sendKeys("9591008397");
driver.findElement(By.xpath("//button[@class='jss115']")).click();


driver.findElement(By.id("otp")).sendKeys("");




    // To launch Salesforce Marketing Cloud App
    driver.get("https://www.meddo.in/login");
    // To maximize the browser
    driver.manage().window().maximize();
    // implicit wait
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
  
@Test
public void Meddoonlinebooking() throws IOException{
// Import excel sheet.
File src=new File("C:\\Users\\Neeraj Sharma\\eclipse-workspace\\DataDrivenFramework\\Excel\\TestData.xlsx");   
// Load the file.
FileInputStream fis = new FileInputStream(src);
// Load the workbook.
workbook = new XSSFWorkbook(fis);
// Load the sheet in which data is stored.
sheet= workbook.getSheetAt(0);
for(int i=1; i<=sheet.getLastRowNum(); i++){
// Import data for Email.
cell = sheet.getRow(i).getCell(0);
/*cell.setCellType(Cell.CELL_TYPE_STRING);*/
driver.findElement(By.id("username")).clear();
driver.findElement(By.id("username")).sendKeys(cell.getStringCellValue());
driver.findElement(By.id("submit-btn")).click();
// Import data for password.
cell = sheet.getRow(i).getCell(1);
/*cell.setCellType(Cell.CELL_TYPE_STRING);*/
driver.findElement(By.id("password")).clear();         
driver.findElement(By.id("password")).sendKeys(cell.getStringCellValue());

// To click on Login button
driver.findElement(By.id("submit-btn")).click();
System.out.println("User has logged in succesfully");
driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


//Navigate to Overview Page
driver.findElement(By.xpath("//a[contains(text(),'Overview')]")).click();
System.out.println("User clicked on Overview link");
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


//navigate to content page
driver.findElement(By.xpath(".//a[text()='Content']")).click();
System.out.println("User clicked on Content link");
driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);



//Navigate to Subscribers Page
driver.findElement(By.linkText("Subscribers")).click();
System.out.println("User clicked on Subscribers link");
//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

//Navigate to Admin Page
driver.findElement(By.xpath("//a[contains(text(),'Admin')]")).click();
System.out.println("User clicked on Admin link");
//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

//Mouse hover the Profile settings section
driver.findElement(By.xpath("//div[@class='mc-header-menu mc-user-info']")).click();

//Clicks on Logout button.
driver.findElement(By.linkText("Logout")).click();


//To close the Browser
     
driver.close();

}
}
}