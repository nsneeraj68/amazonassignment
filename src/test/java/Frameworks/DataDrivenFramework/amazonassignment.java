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
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
public class amazonassignment {
WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    @BeforeTest
public void initialization(){
    // To set the path of the Chrome driver.
System.setProperty("webdriver.chrome.driver", "C:\\Users\\Neeraj Sharma\\eclipse-workspace\\DataDrivenFramework\\driver\\chromedriver.exe");
driver = new ChromeDriver();

    // To launch amazon website
    driver.get("https://www.amazon.com/");
    // To maximize the browser
    driver.manage().window().maximize();
    // implicit wait
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
  
@Test
public void amazon() throws IOException{
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

//Click on Top right nav Arrow next to Hello so user gets the Login Page
driver.findElement(By.xpath("//span[@class='nav-line-2 nav-long-width']//span[@class='nav-icon nav-arrow']")).click();

// This code clear if any user value appears in username
driver.findElement(By.id("ap_email")).clear();

//This code enter the value into the username fetching from the Excel sheet
driver.findElement(By.id("ap_email")).sendKeys(cell.getStringCellValue());

//Click on continue button
driver.findElement(By.id("continue")).click();

// Import data for password.
cell = sheet.getRow(i).getCell(1);
/*cell.setCellType(Cell.CELL_TYPE_STRING);*/

//This code clear any random value from password textbox
driver.findElement(By.id("ap_password")).clear();       

//This code enter the value into password fetching from Excel sheet
driver.findElement(By.id("ap_password")).sendKeys(cell.getStringCellValue());

// To click on Login button
driver.findElement(By.id("signInSubmit")).click();
System.out.println("User has logged in succesfully");
driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


//This code lets user enter the "selenium books" text in searchbox
driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Selenium Books");

//clicks on search icon
driver.findElement(By.xpath("//div[@class='nav-search-submit nav-sprite']//input[@class='nav-input']")).click();
System.out.println("Selenium Books searched");
//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

//Click on Next button
driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
System.out.println("User clicked on Next button");

//Click on  sort By 
driver.findElement(By.xpath("//span[@id='a-autoid-1-announce']")).click();

// Select low to highest option
driver.findElement(By.xpath("//a[@id='s-result-sort-select_1']")).click();

//Click on first item displayed on page with lowest price
driver.findElement(By.className("s-image")).click();

//Click on "Paperback"
driver.findElement(By.xpath("//a[@id='a-autoid-6-announce']")).click();

//Click on Buy Now button
driver.findElement(By.xpath("//input[@id='buy-now-button']")).click();

//Click on Continue button if payment method is already added else write code for selecting the payment method as well if its new user.
driver.findElement(By.xpath("//span[@id='pp-79FWYM-91']//input[@name='ppw-widgetEvent:SetPaymentPlanSelectContinueEvent']")).click();

//Select the checkbox skip for now
driver.findElement(By.xpath("//span[@id='skip-for-now']//i[@class='a-icon a-icon-radio']")).click();

//Click on continue
driver.findElement(By.xpath("//input[@class='a-button-input']")).click();

//Place you order -final step
//driver.findElement(By.xpath("//input[@name='placeYourOrder1']")).click();

//Clicks on Logout button.
driver.findElement(By.xpath("//i[@class='hm-icon nav-sprite']")).click();
driver.findElement(By.xpath("//a[@class='hmenu-item'][contains(text(),'Sign Out')]")).click();

//To close the Browser
   
driver.close();

}
}
}