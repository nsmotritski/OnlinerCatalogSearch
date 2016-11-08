package OnlinerCatalogSearch;

import OnlinerCatalogSearch.Forms.OnlinerTVSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OnlinerCatalogSearch {
    FileInputStream fis;
    Properties property = new Properties();
    WebDriver driver;
    private String url;
    private int webDriverTimeout;
    private String domain;
    private String login;
    private String password;
    private String user;


    @BeforeTest
    @Parameters({"domain","login","password","user"})
    public void doBeforeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/resources/chromedriver.exe");
        //reading properties from the properties file
        try {
            fis = new FileInputStream("src/resources/TestSuite.properties");
            property.load(fis);

            url = property.getProperty("url");
            webDriverTimeout = Integer.parseInt(property.getProperty("webDriverTimeout"));
            domain = property.getProperty("domain");
            login = property.getProperty("login");
            password = property.getProperty("password");
            user = property.getProperty("user");
        } catch (IOException e) {
            System.err.println("Error: File not found!");
        }
        System.out.println("EmailLoginTest: before test");
        System.out.println("URL: " + url
                + ", DOMAIN: " + domain
                + ", LOGIN: " + login
                + ", PASSWORD: " + password);
        //launching the browser and navigating to the web page
        System.out.println(this.url);
        this.driver = new ChromeDriver();
        this.driver.get(url);
        Thread.sleep(2500);
    }

    @AfterTest
    public void doAfterTest() {
        System.out.println("EmailLoginTest: after test");
        driver.quit();
    }

    @Test
    public void TestResultsMatch() throws InterruptedException {
        System.out.println("EmailLoginTest: test");
        OnlinerTVSearchPage onlinerTVSearchPage = new OnlinerTVSearchPage();

        WebElement manufacturerFilter = driver.findElement(By.xpath("//span[@class='schema-filter__checkbox-text' and .='Samsung']"));
        manufacturerFilter.click();
        WebElement maxPriceFilter = driver.findElement(By.xpath(".//*[@id='schema-filter']//input[@placeholder='до']"));
        maxPriceFilter.sendKeys("1000");
        WebElement releasedAfterYearFilter = driver.findElement(By.xpath(".//*[@id='schema-filter']//input[@placeholder='2011']"));
        releasedAfterYearFilter.sendKeys("2013");
        WebElement diagonalFromFilter = driver.findElement(By.xpath("//span[.='Диагональ']/../following-sibling::div/div/div/select[contains(@data-bind,'from')]"));
        diagonalFromFilter.sendKeys("29");
        WebElement diagonalToFilter = driver.findElement(By.xpath("//span[.='Диагональ']/../following-sibling::div/div/div/select[contains(@data-bind,'to')]"));
        diagonalToFilter.sendKeys("32");
        //onlinerTVSearchPage.waitForPageLoaded(driver,15);
        Thread.sleep(2500);

        //creating list of search items
        List<WebElement> searchItemList = new ArrayList<WebElement>();
        searchItemList = driver.findElements(By.xpath(".//div[@id='schema-products']/div"));
        for (WebElement e:searchItemList) {
            try {
                String title = e.findElement(By.xpath(".//span[contains(@data-bind,'product.full_name')]")).getText();
                String description = e.findElement(By.xpath(".//span[contains(@data-bind,'product.description')]")).getText();
                System.out.println(title);
                System.out.println(description);
            }
            catch (NoSuchElementException exception) {
            }

        }
    }
}