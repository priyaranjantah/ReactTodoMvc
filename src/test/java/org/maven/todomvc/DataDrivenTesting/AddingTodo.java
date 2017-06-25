package org.maven.todomvc.DataDrivenTesting;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by user on 6/23/2017.
 */
@Test
public class AddingTodo {
    WebDriver driver;
    public String appurl = "http://todomvc.com/examples/react/#/";
    public String actual = null;
    public String expected = null;

    @BeforeClass
    public void launchBrowser() {
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\ReactTodoMvc\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\IdeaProjects\\ReactTodoMvc\\geckodriver.exe");
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(appurl);
        System.out.println("Opening the React todo website");
    }


    @BeforeTest
    public void OpeningBrowser() {
        System.out.println("Opening React todo Browser");
    }


    @Test(priority = 0)
    public void verifyHomepageTitle() throws InterruptedException {
        Thread.sleep(5000);
        driver.getPageSource();
        String my_title = driver.getTitle();
        System.out.println("Title is :" + my_title);
        String expected_title = "React � TodoMVC";
        Assert.assertTrue(driver.getTitle().contains("React � TodoMVC"));
        Assert.assertEquals(my_title, expected_title);
        System.out.println("Page title is verified");
    }

    @Test(priority = 1)
    public void ReadExcel() throws InterruptedException, IOException {
        File src = new File("D:\\IdeaProjects\\ReactTodoMvc\\src\\test\\java\\org\\maven\\todomvc\\DataDrivenTesting\\TestData.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);
        String data1 = sheet1.getRow(0).getCell(0).getStringCellValue();
        System.out.println("Data from Excel is :" + data1);
        String data2 = sheet1.getRow(1).getCell(0).getStringCellValue();
        System.out.println("Data from Excel is :" + data2);


        driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']")).click();
        driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']")).sendKeys(data1);
        driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']")).sendKeys(data2);
        driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']")).sendKeys(Keys.ENTER);


       
    }

    @Test(priority = 2)
    public void view_list_todo_element() throws IOException {
        driver.findElements(By.xpath("//ul[@class='todo-list']"));
        TakesScreenshot tis = (TakesScreenshot) driver;
        File source = tis.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/todos1.png"));
        System.out.println("Screenshot captured");
        List<WebElement> links = driver.findElements(By.xpath("//ul[@class='todo-list']"));
        int total_counts = links.size();

        for (int i = 0; i < total_counts; i++) {
            WebElement element = links.get(i);
            String text = element.getAttribute("innerHTML");
            boolean status = element.isEnabled();
            System.out.println("link name:" + text);
            System.out.println("link status is: " + status);
        }
    }


    @Test(priority = 3)
    public void edit_a_todo_element() {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//input[@value='test2'])"));
        actions.doubleClick(element).perform();
        element.clear();
        element.sendKeys("test10");
        element.sendKeys(Keys.TAB);
        element.sendKeys(Keys.ENTER);

    }

    @Test(priority = 4)
    public void filtering_a_todo_element() {
        driver.findElement(By.xpath("//span[@class='todo-count']")).click();//count number of elements
        driver.findElement(By.xpath("//a[contains(@class,'selected')]")).click(); //filter all elements
        driver.findElement(By.xpath("//a[contains(@href,'active')]")).click(); //filter active elements
        driver.findElement(By.xpath("//a[contains(@href, 'completed')]")).click(); //filter completed elements
    }

    @Test(priority = 5)
    public void deleting_a_todo_element() {
        List<WebElement> checkbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (int i = 0; i < checkbox.size(); i++) {
            WebElement ele = checkbox.get(i);
            String id = ele.getAttribute("type");
            if (id.equalsIgnoreCase("test1")) {
                ele.click();
            }
        }
        driver.findElement(By.xpath("//button[@class='clear-completed']")).click();

    }

    @Test(priority = 6)
    public void delete_all_element() {
        driver.findElement(By.xpath("//input[@class='toggle-all'][@type='checkbox']")).click();
        driver.findElement(By.xpath("//button[@class='clear-completed']")).click();
    }

    @AfterTest
    public void terminateBrowser() {
        System.out.println("closing the browser");
    }
    @AfterClass
    public void Quit() {
        driver.quit();

    }
}

