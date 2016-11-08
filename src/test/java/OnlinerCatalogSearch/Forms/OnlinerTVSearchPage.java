package OnlinerCatalogSearch.Forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class OnlinerTVSearchPage {
    private WebElement manufacturerFilter;
    private WebElement maxPriceFilter;
    private WebElement releasedAfterYearFilter;
    private WebElement diagonalFromFilter;
    private WebElement diagonalToFilter;

    public void OnlinerTVSearchPage (WebDriver driver) {
        this.manufacturerFilter = driver.findElement(By.xpath("//span[@class='schema-filter__checkbox-text' and .='Samsung']"));
        this.maxPriceFilter = driver.findElement(By.xpath(".//*[@id='schema-filter']//input[@placeholder='до']"));
        this.releasedAfterYearFilter =
        this.diagonalFromFilter =
        this. diagonalToFilter =
    }




    public void waitForPageLoaded (WebDriver driver, int webDriverTimeout) {
        driver.manage().timeouts().implicitlyWait(webDriverTimeout, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id='schema-tags']/div[4]/span")).isDisplayed();
    }
}
