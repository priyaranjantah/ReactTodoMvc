package org.maven.todomvc.DataDrivenTesting;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by user on 6/23/2017.
 */
public class AddingTodo {
    public String appurl = "http://todomvc.com/examples/react/#/";
    WebDriver driver;
    public String actual = null;
    public String expected = null;

    @BeforeClass
    public void beforelogging() {
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver","D:\\IdeaProjects\\ReactTodoMvc\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","D:\\IdeaProjects\\ReactTodoMvc\\geckodriver.exe");

        driver.manage().window().maximize();
        driver.get(appurl);
        System.out.println("Opening the React todo website");
    }

    @BeforeTest
    public void OpeningBrowser() {
    System.out.println("Opening todos Website");
    }
	@BeforeMethod
	public void verifyHomepageTitle(){
		Thread.sleep(5000);
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("React • TodoMVC"));
        System.out.println("Page title is verified - User is able view the page successfully");
    }

    @Test
    public void ReadExcel() throws InterruptedException, IOException {
        File src = new File("D:\\IdeaProjects\\ReactTodoMvc\\src\\test\\java\\org\\maven\\todomvc\\DataDrivenTesting\\TestData.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);
        String data1 = sheet1.getRow(0).getCell(0).getStringCellValue();
        System.out.println("Data from Excel is :" + Username);
        String data2 = sheet1.getRow(1).getCell(1).getStringCellValue();
        System.out.println("Data from Excel is :" + Password);

        driver.findElement(By.id(//input[@placeholder='What needs to be done?'])).click();
        driver.findElement(By.id(//input[@placeholder='What needs to be done?'])).sendKeys(data1); 
        driver.findElement(By.id(//input[@placeholder='What needs to be done?'])).sendKeys(data2);
		driver.findElement(By.id(//input[@placeholder='What needs to be done?'])).sendKeys(data3);
		driver.findElement(By.id(//input[@placeholder='What needs to be done?'])).sendKeys(data4);
		driver.findElement(By.id(//input[@placeholder='What needs to be done?'])).sendKeys(data5);
        
		//capture screenshot
        TakesScreenshot tis = (TakesScreenshot) driver;
        File source = tis.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/todos.png"));
        System.out.println("Screenshot captured");
		}
		
	    @Test
        public void view_list_todo_element() {
	    List<WebElement> links = driver.findElements(By.xpath("//ul[@class='todo-list']));
        int total_counts = links.size();

        for (int i = 0; i < total_counts; i++) {
            WebElement element = links.get(i);
            String text = element.getAttribute("label");
            boolean status = element.isEnabled();
            System.out.println("link name:" + text);
            System.out.println("link status is: " + status);
          
         @Test
         public void deleting_a_todo_element(){
		 List<WebElement> checkbox=driver.findElement(By.xpath("//input[@type='checkbox']"));
		 for(int i=0; i,checkbox.size(); i++){
		 WebElement ele = checkbox.get(i);
		 String id = ele.getAttribute("type");
		 if(if.equalsIgnoreCase("test1)){
		 ele.click();
        }
    }
	driver.findElements(By.xpath("//button[@class='clear-completed'])).click
	
	
	 @Test
     public void edit_a_todo_element(){
	 WebElement element =  driver.findElement(By.xpath("//input[@value='test2']));
	((JavascriptExecutor) driver).executeScript("edit a todo element;", element);
	 doubleClick(element);
	 element.clear;
	 element.sendkeys("test10");
	 element.sendkeys(KEYS.ENTER);
	 
	
    @AfterTest
    public void terminateBrowser() {
    }

    @AfterClass
    public void Quit() {
//        driver.quit();
    }
}
